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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>
 * Utilidad que permite construir criterios de consultas empleando
 * prepareStament indicando las columnas a emplear y pasándole el elemento para
 * setear el prepare. Con esto nos ahorramos tener que ir contando a mano el
 * numero de elementos que llevamos establecidos y las comprobaciones
 * elementales para excluirlo en caso de que sea null.
 * </p>
 * <p>
 * Un ejemplo de uso podria ser : <code>
  
  String select = "SELECT id, login, nombre, apellidos FROM usuarios ";
  String count = "SELECT COUNT(*) FROM usuarios ";
  Where crit = new Where ();
  
  crit.put("nombre", Where.LIKE, ejemplo.getNombre(),true);
  crit.put("apellidos", Where.LIKE, ejemplo.getApellidos(),true);
  crit.put("login", Where.LIKE, ejemplo.getLogin(),true);
  String where = " WHERE "  + crit.getWhere();
  select = select + where;
  count = count + where;
  PreparedStatement stm;
  //Ejecutamos la cuenta
  stm = conex.prepareStatement(count);
  crit.setValues(stm);
  ResultSet rs = stm.executeQuery();
  // Y el select completo.
  stm = conex.prepareStatement(select);
  crit.setValues(stm);
  rs = stm.executeQuery();
  
 *  </code> Con este ejemplo creamos dos consultas SQL, el where se construye
 * una sola vez ignorando los valores "null" y se establecen el el prepares
 * Statement sin que se nos olvide ningun valor.
 * </p>
 * <p>
 * Esta clase emplea criterios de condicion "AND" de forma que cada elemento
 * incluido se construye concatenando AND : <br>
 * [criterio] AND [criterio] AND [criterio] <br>
 * 
 * Para concatenar emplentado otro separador ver : {@link OrWhere}
 * <ul>
 * <li><b>Versión 1.0 </b>: Versión inicial.</li>
 * </ul>
 * 
 * @author Fernando Rosado
 * @version 1.0
 * 
 */
public class Where {
	// Constantes
	// private static final Log log = LogFactory.getLog(Where.class);
	/**
	 * Constante que indica que la comprobacion es de tipo IGUALDAD 
	 * <pre>
	 * X = ? 
	 * </pre>
	 */
	public static final int EQ = 0;
	/**
	 * Constante que indica que la comprobación es de tipo NO IGUAL
	 * <pre>
	 * X != ?
	 * </pre>
	 * 
	 */
	public static final int NEQ = 1;
	/**
	 * Constante que indica que la comprobación es de tipo MAYOR QUE
	 * <pre>
	 * X > ?
	 * </pre>
	 * 
	 */
	public static final int GT = 2;
	/**
	 * Constante que indica que la comprobación es de tipo MENOR QUE
	 * <pre>
	 * X < ?
	 * </pre>
	 * 
	 */
	public static final int LT = 3;
	/**
	 * Constante que indica que la comprobación es de tipo LIKE
	 * <pre>
	 * X LIKE ?
	 * </pre>
	 * 
	 */
	public static final int LIKE = 4;
	/**
	 * Constante que indica que la comprobación es de tipo MAYOR O IGUAL
	 * <pre>
	 * X >= ?
	 * </pre>
	 * 
	 */
	public static final int GT_EQUAL = 5;
	/**
	 * Constante que indica que la comprobación es de tipo MENOR O IGUAL QUE
	 * <pre>
	 * X <= ?
	 * </pre>
	 * 
	 */
	public static final int LT_EQUAL = 6;
	/**
	 * Constante que indica que la comprobación es de tipo DISTINTO
	 * <pre>
	 * X <> ?
	 * </pre>
	 * 
	 */
	public static final int DISTINCT = 7;
	private static final int NONE = 8;
	/**
	 * No añade comprobación, para idcar que la operacion a sustituir es aplicable 
	 * para una sentencia SQL completa
	 * <pre>
	 * X IN (
	 *     SELECT Y FROM Z
	 *     WHERE Z.A = ?
	 * )
	 * </pre> 
	 */
	public static final int SQL = 9;
	
	private static final String[] COMP = new String[10];
	static  {
		Where.COMP[Where.EQ] = " = ?";
		Where.COMP[Where.NEQ] = " != ?";
		Where.COMP[Where.GT] = " > ?";
		Where.COMP[Where.LT] = " < ?";
		Where.COMP[Where.LIKE] = " LIKE ?";
		Where.COMP[Where.GT_EQUAL] = " >= ?";
		Where.COMP[Where.LT_EQUAL] = " <= ?";
		Where.COMP[Where.DISTINCT] = " <> ?";
		Where.COMP[Where.NONE] = " ";
		Where.COMP[Where.SQL] = " ";
	}
	// -------------------------------------
	// Propiedades
	protected ArrayList<String> columnas;
	protected ArrayList<Integer> condiciones;
	protected ArrayList<Object> valores;
	protected StringBuilder where;
	
	//---------------------------------
	// Constructores
	public Where () {
		
		this.columnas = new ArrayList<String> ();
		this.valores = new ArrayList<Object> ();
		this.condiciones = new ArrayList<Integer> ();
	}
	
	//---------------------------------
	// Métodos
	/**
	 * Añade un conjunto de criterios
	 * @param crit Otro criterio
	 */
	public void put (Where crit) {
		if (crit != null) {
			final String string = crit.getWhere();
			this.reallyPut(string, crit, Where.SQL, true);
		}
	}
	/**
	 * Añade una sentencia SQL sin parametros de sustitución
	 * @param sql
	 */
	public void put (String sql ) {
		this.reallyPut(sql, null, Where.SQL,false);
		
	}
	/**
	 * Añade una sentencia SQL sustituyendo todos los valores 
	 * indicados en el array. 
	 * @param sql
	 * @param values
	 */
	public void put (String sql, Object[] values) {
		this.reallyPut(sql, values, Where.SQL, false);
	}
	/**
	 * 
	 * @param columna Sobre la que aplicar la condicion
	 * @param condicion Constante de tipo de condicion a añadir
	 * @param valor valor a sustituir
	 */
	public void put (String columna,int condicion,String valor) {
		if (valor != null && !"".equals(valor)) {
			this.reallyPut(columna, valor, condicion,false);
		}
	}
	
	/**
	 * Igual que {@link #put(String, int, Object, boolean)} asumiendo
	 * que el valor de <i>ignoreNull</i> es <b>false</b>
	 * @param columna Sobre la que aplicar la condicion
	 * @param condicion Constante de tipo de condicion a añadir
	 * @param valor valor a sustituir
	 * 
	 * @see #put(String, int, Object, boolean)
	 */
	public void put (String columna, int condicion,Object valor) {
		if (valor != null) {
			this.reallyPut(columna, valor, condicion,false);
		}
	}
	
	/**
	 * 
	 * @param columna Sobre la que aplicar la condicion
	 * @param condicion Constante de tipo de condicion a añadir
	 * @param valor valor a sustituir
	 * @param ignoreNull si se indica <b>true</b> el valor es ignorado si vale == null 
	 */
	public void put (String columna, int condicion, Object valor, boolean ignoreNull) {
		this.reallyPut(columna, valor, condicion, ignoreNull);
	}
	
	/**
	 * @see #put(String, int, Object, boolean)
	 * @param columna
	 * @param condicion
	 * @param valor
	 */
	public void put (String columna, int condicion, int valor) {
		this.reallyPut(columna,Integer.valueOf(valor), condicion,false);
	}
	
	/**
	 * @param columna
	 * @param valor
	 * @param condicion
	 */
	private void reallyPut(String columna, Object valor, int condicion,boolean ignoreNull) {
		final String cond = columna + Where.COMP[condicion] ;
		if (ignoreNull && valor == null) {
			// Como es null y tal, lo ignoramos
			// log.debug("      como es null lo ignoramos para " + cond);
		} else {
			// log.debug("      Columna = " + cond + "   valor : " + valor );
			this.columnas.add(cond);
			this.valores.add(valor);
			this.condiciones.add(condicion);
		}	
		
	}
	/**
	 * Construye las condiciones que se añaden al where.
	 * @return lo que se añade al where :  "(x = ? and y = ?)"
	 */
	public String getWhere() {
		if (this.where == null) {
			this.where = new StringBuilder(" ");
			for (Iterator<String> iter = this.columnas.iterator(); iter.hasNext();) {
				String columna = iter.next();
				this.where.append(columna);
				if (iter.hasNext()) {
					this.where.append(" AND ");
				}
			}
		}
		return this.where.toString();
	}
	
	
	public int setValues (PreparedStatement stm) throws SQLException {
		return this.setValues(stm, 1);
	}
	/**
	 * Devuelve el siguiente elemento a establecer, asi si la consulta es : 
	 * <code>
	 * SELECT * FROM TABLA WHERE x=? 
	 * </code>
	 * devuelve 2. (ha establecido el 1)<br />
	 * Esto es debido a que se debe de usar un criteria por conjunto de condiciones AND.
	 *  
	 * 
	 * @param stm
	 * @param inicial Por que elemento empezar. 
	 * @return numero de elemento a establecer tras establecer sus valores.
	 * @throws SQLException
	 */
	public int setValues (PreparedStatement stm, int inicial) throws SQLException {
		int cont = inicial;
		for (int i = 0; i < this.valores.size(); i++) {
			Object obj = this.valores.get(i);
			int condicion = this.condiciones.get(i);
			// if (log.isDebugEnabled()) {
			// log.debug ("      Establecemos parametro "+ cont + " a valor " + obj+ " para " + columnas.get(i));
			//}
			if (obj != null) {
				if ((condicion == Where.LIKE)&&(obj instanceof String)) {
					
					final String sValor = "%" + ((String)obj).trim() + "%"; 
					stm.setString(cont++, sValor);
				} else if (condicion == Where.NONE) {
					// No establecemos valor. SQL completo
				} else if (condicion == Where.SQL) {
					
					if ((obj instanceof Object[])) {
						
						Object[] objs = (Object[]) obj;
						for (int x = 0; x < objs.length; x++) {
							if (objs[x] != null) {
								stm.setObject(cont++, objs[x]);
							} else {
								stm.setNull(cont++, Types.VARCHAR);
							}
						}
					}else if (obj instanceof Where) {
						cont = ((Where)obj).setValues(stm, cont);
						
					} else {
						stm.setObject(cont++, obj);
					}
					
					
				} else {
					stm.setObject(cont++, obj);
				}
			} else  {
				// log.debug("      Valor nulo");
				// Por lo que he averiguado (ver DbUtils de commons)
				// este null solo falla en oracle y poco más
				if (condicion == Where.SQL) {
					String col = this.columnas.get(i);
					// log.debug("      Es SQL");
					if (col.contains("?")) {
						// log.debug ("      Establecemos valor a nulo");
						stm.setNull(cont++, Types.VARCHAR);
					}
				} else if (condicion == Where.NONE) {
					// NONE, no hay valor
				} else {
					// log.debug("      NO ES SQL");
					stm.setNull(cont++, Types.VARCHAR);
				}
			}
			
		}
		return cont;
	}
	
	
}
