# Java Utils - SQL
# SQL Utils #

Clases que he creado como utilidad para trabajar con sentencias SQL

# Detalles #
## Clases Where y OrWhere ##

Utilidad que permite construir criterios de consultas empleando prepareStament indicando las columnas a emplear y pasándole el elemento para setear el prepare. Con esto nos ahorramos tener que ir contando a mano el numero de elementos que llevamos establecidos y las comprobaciones  elementales para excluirlo en caso de que sea null.

Un ejemplo de uso podria ser :
```
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
  
```
Con este ejemplo creamos dos consultas SQL, el where se construye  una sola vez ignorando los valores "null" y se establecen el el prepareStatement sin que se nos olvide ningun valor.

La clase Where emplea criterios de condicion "AND" de forma que cada elemento incluido se construye concatenando AND :
> _criterio_ AND _criterio_ AND _criterio_ <br></li></ul>

Podemos concatenar condiciones AND y OR usando la clase <i>OrWhere</i>:<br>
<pre><code>  OrWhere or = new OrWhere();<br>
  or.put("nombre", Where.LIKE, "PACO");<br>
  or.put("apellidos", Where.LIKE, "PACO");<br>
  Where and = new Where ();<br>
  and.put ("edad",Where.GT,18);<br>
  and.put (or);<br>
</code></pre>

Construimos una consulta de tipo :<br>
<pre><code>  where edad &gt; 18 and (nombre like "%paco%" or apellidos like "%paco%")<br>
</code></pre>

Interesante ver algo parecido en la version 3 de <a href='http://www.mybatis.org/java.html'>MyIbatis</a> (<a href='http://mybatis.googlecode.com/svn/trunk/doc/en/'>PDF de referencia</a> )