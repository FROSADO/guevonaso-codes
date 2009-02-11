/*
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

package es.guevonaso.dates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Fernando Rosado
 * @date 11/02/2009
 * 
 */

public class DateTimeUtils {
	// Constantes
	// Patrones de fecha que se admiten. El elemento 0 será
	// considerado "por defecto"
	private static final String[]	patronesFecha	= { "dd/MM/yyyy",
	"yyyy-MM-dd"							};
	
	// -------------------------------------
	// Propiedades
	
	// ---------------------------------
	// Constructores
	
	// ---------------------------------
	// Métodos
	
	/**
	 * A partir de un Date podemos obtener la hora de ejecucion
	 * 
	 * @param timestamp
	 * @return String con la hora en formato HH:MM
	 */
	public static String hora(Date time) {
		return DateTimeUtils.fecha(time, "HH:mm");
	}
	
	/**
	 * A partir de un Date obtenemos la fecha en formato dd/MM/yyyy
	 * 
	 * @param time
	 * @return Fecha en formato yyyy-MM-dd
	 */
	public static String fecha(Date time) {
		return DateTimeUtils.fecha(time, null);
	}
	
	/**
	 * A partir de un Date y un patron obtenemos una fecha
	 * 
	 * @param val
	 * @param patron
	 * @return Date por el patrón.
	 */
	public static String fecha(Date time, String patronArg) {
		if (time == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat();
		if (patronArg != null) {
			format.applyLocalizedPattern(patronArg);
			
			return format.format(time);
		}
		
		format.applyLocalizedPattern(DateTimeUtils.patronesFecha[0]);
		return format.format(time);
		
	}
	
	/**
	 * Recibe un String que representa uan hora (HH:mm) y devuelve el
	 * equivalente en forma de Date de esta.
	 * 
	 * @param horaPrevista
	 * @return Time segun el formato HH:mm
	 * @throws ParseException
	 */
	public static Date getTime(String horaPrevista) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("HH:mm");
		format.setLenient(false);
		return format.parse(horaPrevista);
		
	}
	
	/**
	 * @param fechaPrevista
	 *            segun alguno de los formatos válidos.
	 * @return Fecha segun alguno de los formatos válidos.
	 * @throws ParseException
	 */
	public static Date getDate(String fechaPrevista)
	throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat();
		Date date;
		ParseException e = null;
		for (String patron : DateTimeUtils.patronesFecha) {
			try {
				format.applyPattern(patron);
				format.setLenient(false);
				date = format.parse(fechaPrevista);
				if (fechaPrevista.length() == patron.length()) {
					return new Date(date.getTime());
				}
				
				// Error en tamaño
				throw new ParseException(
						"La longitud de la fecha no coincide con la del patron",
						0);
			} catch (ParseException ex) {
				e = ex;
			}
		}
		throw e;
		
	}
	
}