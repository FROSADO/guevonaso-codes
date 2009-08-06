/**
 * 
 * Copyright (C) 2008 Fernando Rosado Altamirano This file is part of
 * 'guevonaso-codes' http://code.google.com/p/guevonaso-codes/
 * 
 * This code is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License version 3 only, as
 * published by the Free Software Foundation.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License version 3 for
 * more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * version 3 along with this work. If not, see <http://www.gnu.org/licenses/>.
 */

package es.guevonaso.ant;

import java.io.File;
import java.util.Vector;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

/**
 * Find the last file and set property with this file name
 * 
 * @author frosado
 */
public class LastFile extends Task {
	
	
	private String property;
	private Vector<FileSet> filesets = new Vector<FileSet>();
	
	public void addFileset(FileSet fileset) {
		filesets.add(fileset);
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	public String getProperty() {
		return property;
	}
	
	
	/**
	 * 
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		validate(); // 1
		long newest = 0;
		File last = null;
		for (FileSet fs : filesets) {
			if (fs.getProject() == null) {
				fs = (FileSet) fs.clone();
				fs.setProject(getProject());
			}
			File dir = fs.getDir();
			log("searching last file in " + dir);
			DirectoryScanner ds = fs.getDirectoryScanner(getProject());
			String[] includedFiles = ds.getIncludedFiles();
			for (String filename : includedFiles) {
				// log("--> " + filename);
				File currentFile = new File(dir, filename);
				if (!currentFile.isDirectory()) {
					
					long lastModified = currentFile.lastModified();
					if (lastModified > newest) {
						newest = lastModified;
						last = currentFile;
					}
					
				}
			}
			if (last == null) {
				log("No file was found", Project.MSG_WARN);
			} else {
				String value;
				value = last.getAbsolutePath();
				log("Find the last file " + last.getName(), Project.MSG_INFO);
				log("Setting propery '" + getProperty() + "' to value '"
						+ value + "'", Project.MSG_INFO);
				getProject().setProperty(getProperty(), value);
			}
		}
		
		
	}
	
	protected void validate() {
		
		if (property == null) {
			throw new BuildException("property not set", getLocation());
		}
		if (filesets.size() < 1) {
			throw new BuildException("You must specify a fileset",
					getLocation());
		}
	}
	
}
