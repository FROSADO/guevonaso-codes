## Español ##
Esta clase está basada en la clase _org.apache.log4j.spi.LocationInfo_ del framework de apache _Log4j_. Esta es una versión con menos funcionalidad creada a modo de muestra de como poder obtener desde que clase y método se invoca un método.
El modo de uso es el siguiente. En el método que queremos averiguar desde donde se llama creamos una nueva instancia de la clase WhoCallMe.

```
  private void sampleMethod () {
      WhoCallMe info = new WhoCallMe (this.getClass(),"sampleMethod");
      String fullInfo;
      fullInfo = info.getFullInfo();
      System.out.println (fullInfo);
  }
```

De esta manera obtendremos en _fullInfo_ la pila de todas las llamadas hasta el método que hemos indicado.

## English ##
This class is based in the class _org.apache.log4j.spi.LocationInfo_ from _Log4J_ apache framework. This is a less functionality version of the class created to show how to obtain the name and method who invoke the current method.
Is easy to use it:
```
  private void sampleMethod () {
      WhoCallMe info = new WhoCallMe (this.getClass(),"sampleMethod");
      String fullInfo;
      fullInfo = info.getFullInfo();
      System.out.println (fullInfo);
  }
```

You obtain in _fullInfo_ the stack of all class invoked until the current method ("sampleMethod").