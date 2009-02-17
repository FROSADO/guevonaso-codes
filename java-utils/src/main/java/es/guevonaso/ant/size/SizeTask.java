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

package es.guevonaso.ant.size;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class SizeTask extends Task {
	private String property;
	protected File file;
	
	public SizeTask() {
		this.file = null;
		this.property = null;}
	
	public void setFile(String s) {
		this.file = new File(s);}
	
	public void setProperty(String s) {
		this.property = s;}
	
	@Override
	public void execute() throws BuildException {
		if (this.file == null) {
			throw new BuildException("File attribute must be set.");
		}
		if (this.file.exists()) {
			long lSize = this.size(this.file);
			String sSize = Long.toString( lSize / 1024L);
			this.getProject().setNewProperty(this.property, sSize);
		} else {
			throw new BuildException("File cannot be found.");
		}
	}
	private long size(File file) {
		long l = 0;
		if (file.isDirectory()) {
			File afile[] = file.listFiles();
			for (int i = 0; i < afile.length; i++) {
				l += this.size(afile[i]);
			}
		} else {
			l = file.length();
		}
		return l;
	}
}
