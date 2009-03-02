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
package es.guevonaso.who;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * This class is used for trace the invoker of the current method. 
 * Is based in the class "org.apache.log4j.spi.LocationInfo" from Log4J with less
 * functionality. This class is used with learn pourpose. 
 * @author Fernando Rosado
 * @date 02/03/2009
 *
 */
public class WhoCallMe implements Serializable {
	private static StringWriter sw = new StringWriter();
	private static PrintWriter pw = new PrintWriter(WhoCallMe.sw);
	private final String fullInfo;
	private static final String NA = "?";
	public final static String LINE_SEP = System.getProperty("line.separator");
	public final static int LINE_SEP_LEN = LINE_SEP.length();
	
	public WhoCallMe(Class<?> me, String methodName) {
		this(new Throwable(), me.getName() + "." + methodName);
	}
	public WhoCallMe(Class<?> me) {
		this(new Throwable(), me.getName());
	}
	
	private WhoCallMe(Throwable t, String fullLocation) {
		if (t == null || fullLocation == null) {
			fullInfo = NA;
			return;
		}
		
		String s;
		// Protect against multiple access to sw.
		synchronized (sw) {
			t.printStackTrace(pw);
			s = sw.toString();
			sw.getBuffer().setLength(0);
		}
		// System.out.println("s is ["+s+"].");
		int ibegin, iend;
		
		
		// This method of searching may not be fastest but it's safer
		// than counting the stack depth which is not guaranteed to be
		// constant across JVM implementations.
		ibegin = s.lastIndexOf(fullLocation);
		if (ibegin == -1) {
			fullInfo = NA;
			return;
		}
		//		
		ibegin = s.indexOf(LINE_SEP, ibegin);
		if (ibegin == -1) {
			fullInfo = NA;
			return;
		}
		ibegin += LINE_SEP_LEN;
		fullInfo = s.substring(ibegin);
	}
	public String getFullInfo() {
		return fullInfo;
	}
}
