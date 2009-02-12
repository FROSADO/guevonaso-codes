/*
 * 
 * Copyright (C) 2008 Fernando Rosado Altamirano
 * This file is part of 'guevonaso-codes' 
 * http://code.google.com/p/guevonaso-codes/
 *
 * This code is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU Lesser General Public License version 3 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License 
 * version 3 for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * version 3 along with this work.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.guevonaso.test.dates;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

import es.guevonaso.dates.DateTimeUtils;

/**
 * @author Fernando Rosado
 * @date 11/02/2009
 * 
 */
public class DateTimeUtilsTest extends TestCase {
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testHora() {
		Date ahora = new Date();
		String hora = DateTimeUtils.hora(ahora);
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(ahora);
		String[] div = hora.split(":");
		Assert.assertNotNull(div);
		Assert.assertNotNull(div);
		Assert.assertTrue(div.length > 1);
		Assert.assertEquals(Integer.valueOf(div[0]), Integer.valueOf(calendario
				.get(Calendar.HOUR_OF_DAY)));
		Assert.assertEquals(Integer.valueOf(div[1]), Integer.valueOf(calendario
				.get(Calendar.MINUTE)));
		
	}
	
	public void testFechaDate() {
		Date ahora = new Date();
		String fecha = DateTimeUtils.fecha(ahora);
		System.out.println(fecha);
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(ahora);
		String[] divs = fecha.split("/");
		
		assertNotNull(divs);
		assertTrue(divs.length > 2);
		Assert.assertEquals(Integer.valueOf(divs[0]), Integer
				.valueOf(calendario.get(Calendar.DAY_OF_MONTH)));
		Assert.assertEquals(Integer.valueOf(divs[1]), Integer
				.valueOf(calendario.get(Calendar.MONTH) + 1));
		Assert.assertEquals(Integer.valueOf(divs[2]), Integer.valueOf(String
				.valueOf(calendario.get(Calendar.YEAR))));
	}
	
}
