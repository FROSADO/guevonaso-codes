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

package es.guevonaso.sql;

import java.util.Iterator;

/**
 * <p>
 * Modificacion de la clase {@link Where} que permite separar las condiciones
 * usando OR. Podemos concatenar condiciones AND y OR usando : <code>
  
  OrWhere or = new OrWhere();
  or.put("nombre", Where.LIKE, "PACO");
  or.put("apellidos", Where.LIKE, "PACO");
  Where and = new Where ();
  and.put ("edad",Where.GT,18);
  and.put (or);
  
  </code> Construimos una consulta de tipo <code>
  where edad > 18 and (nombre like "%paco%" or apellidos like "%paco%")
  </code>
 * 
 * </p>
 * <ul>
 * <li><b>Versión 1.0 </b>: Versión inicial.</li>
 * </ul>
 * 
 * @author Fernando Rosado
 * 
 * 
 */
public class OrWhere extends Where {
	
	/**
	 * Construye las condiciones que se añaden al where.
	 * 
	 * @return lo que se añade al where : "(x = ? OR y = ?)"
	 */
	@Override
	public String getWhere() {
		if (this.where == null) {
			this.where = new StringBuilder(" ");
			if (!this.columnas.isEmpty()) {
				this.where.append(" ( ");
				for (Iterator<String> iter = this.columnas.iterator(); iter.hasNext();) {
					String columna = iter.next();
					this.where.append(columna);
					if (iter.hasNext()) {
						this.where.append(" OR ");
					}
				}
				this.where.append(" ) ");
			} else {
				// TODO un poco chapucilla, pero funciona
				this.where.append(" true = true ");
			}
		}
		return this.where.toString();
	}
}
