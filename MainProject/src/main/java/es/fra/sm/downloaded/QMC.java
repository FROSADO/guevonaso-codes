package es.fra.sm.downloaded;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class QMC extends JFrame implements ActionListener, ClipboardOwner{ // Declaración de la clase que extiende a JFrame para que tenga interfaz gráfica e implementa ActionListener para que los objetos puedan responder a las acciones del usuario así como ClipBoardOwner para poder copiar objetos al porta-papeles.
	
	/*Declaración de los objetos globales de la clase.*/
	
	JFrame frame = new JFrame("QMC"); // Se crea el frame de la aplicación.
	
	JPanel panel1; // Pánel que contiene alradio button de los minitérminos.
	JPanel panel2; // Pánel que contiene alradio button de los maxitérminos.
	JPanel panel3; // Pánel que contiene alradio button de término por término.
	JPanel panel4; // Pánel que contiene alradio button cargar la tabla desde archivo.
	JPanel panel5; // Pánel que contiene a los 4 páneles anteriores (a todos los radio buttons).
	JPanel panel6; // Pánel que contiene al label de suma de minitérminos y su text field.
	JPanel panel7; // Pánel que contiene al label de poducto de maxitérminos y su text field.
	JPanel panel8; // Pánel que contiene al label de términi por término y su text field.
	JPanel panel9; // Pánel que contiene al text field del archivo de entada.
	JPanel panel10; // Pánel que contiene a los 4 páneles anteriores.
	JPanel panel11; // Pánel que contiene a los 4 botones de los controles de inserción de datos.
	JPanel panel12; // Pánel que contiene a los páneles 5, 10 y 11.
	JPanel panel13; // Pánel que contiene al button de "Reset".
	JPanel panel14; // Pánel que contiene a los dos páneles anteriores  (controles de inserción de datos).
	JPanel panel15; // Pánel que contiene al botón de minimizar, el text field de la ecuación reducida y el botón de copiar.
	JPanel panel16; // Pánel que contiene el text field del archivo de los procedimientos de salida y el botón para guardar dichos procedimientos.
	JPanel panel17; // Pánel que contiene a los dos páneles anteriores (controles de minimización).
	JScrollPane panel18; // Pánel "scrollable" que contiene el text area donde se muestra la tabla de verdad.
	JPanel panel19; // Pánel que contiene al pánel anterior.
	JPanel panel20; // Pánel que contiene al text field del archivo de salida de la tabla de verdad y el botón para guardar la tabla.
	JPanel panel21; // Pánel que contiene a los dos páneles anteriores (controles de la tabla de verdad).
	
	JRadioButton invisible = new JRadioButton(); // Radio button invisible que sirve para poder "deseleccionar" los demás radio buttons que sí son visibles.
	
	JRadioButton minisrb = new JRadioButton("Minitérminos"); // Radio button de la inserción por minitérminos.
	JLabel suma = new JLabel("\u03a3m:");
	JTextField ministx = new JTextField("4,0,2,3,7,12",9); // Text field donde se capturan los minitérminos.
	JButton minisbt = new JButton("Llenar"); // Button de la inserción por minitérminos.
	
	JRadioButton maxisrb = new JRadioButton("Maxitérminos"); // Radio button de la inserción por maxitérminos.
	JLabel prod = new JLabel("\u03a0M:");
	JTextField maxistx = new JTextField("4,1,4,6,9,15",9); // Text field donde se capturan los maxitérminos
	JButton maxisbt = new JButton("Llenar"); // Button de la inserción por maxitérminos.
	
	JRadioButton termrb = new JRadioButton("Término por término"); // Radio button de la inserción término por término.
	JLabel term = new JLabel("# de variables:");
	JTextField termtx = new JTextField("0",3); // Text field donde se capturan el número de variables de involucradas.
	JButton termbt = new JButton("Introducir"); // Button de la inseción término por término.
	
	JRadioButton filerb = new JRadioButton("Tabla desde archivo"); // Radio button de la inserción desde archivo.
	JTextField filetx = new JTextField("Archivo a cargar",11); // Text field que muestra la ruta del archivo cargado.
	JButton filebt = new JButton("Cargar"); // Button de la inserción desde archivo.
	
	JButton resetbt = new JButton("Reset"); // Button que resetea el programa a sus valores iniciales.
		
	JButton minimizarbt = new JButton("Minimizar"); // Button para mininimar la tabla de verdad
	JTextField ecuaciontx = new JTextField(15); // Text field donde se muesra la ecuación ya minimizada.
	JButton copiarbt = new JButton("Copiar"); // Button para copiar la ecuación minimizada al porta-papeles.
	
	JTextField procedimsalidatx = new JTextField("Archivo a guardar",12); // Text field que muestra la ruta del archivo donde se guardaron los procedimientos.
	JButton procedimsalidabt = new JButton("Procedimientos a archivo"); // Button para guardar en un archivo los procedimientos de minimización.
	
	JTextArea tablavddarea = new JTextArea(10,20); // Text area donde se muestra la tabla de verdad.
	JTextField tablasalidatx = new JTextField("Archivo a guardar",12); // Text field donde se muestra la ruta del archivo donde se guardó la tabla de verdad.
	JButton tablasalidabt = new JButton("Guardar"); // Button para guardar el tabla de verdad en un archivo.
	
	ButtonGroup botones; // Grupo de botones que relaciona lógicamente los radio buttons creados, para que sean mutuamente excluyentes.
	PrintWriter aproced; // Objeto global con el que se imprimirá al archivo que contiene los procedimientos realizados para la simplificación.
	JFileChooser selector = new JFileChooser(); // Objeto con el que se selecciona un archivo en la véntana de diálogo.
	ExampleFileFilter filtro = new ExampleFileFilter(); // Objeto de tipo "ExampleFileFilter" con el que se filtran los archivos mostrados en la ventana de diálogo"
    ImageIcon icono = new ImageIcon("icono.gif"); // Objeto que contiene al ícono del programa.
	
	
	private static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //Declaración del objeto para captura desde el teclado.
    
    
    int numvar; // Variable que guarda el número de variables de la tabla.
    int filas; // Variable que guarda el número de filas de la tabla de verdad (combinaciones posibles).
    int filasp; // Variable que guarda el número de filas que tiene el arreglo de la tabla de pesos.
    int pmax = 0; // Variable guarda el peso máximo de la tabla de verdad.
    int contagreg; // Variable que cuenta cuantos términos se agregaron al vector "comparaciones" durante una comparación;
    char[][] tablavdd; // Arreglo de CHARS donde se guardan los valores de la tabla.
    char[] vars; // Arreglo que guarda las variables como letras.
    int [][] tablap; // Arreglo de enteros que continene la tabla de pesos, en la primera columna guarda el índice de la tabla de verdad y en la seguna el peso del término correspondiente.
    
    Vector comparaciones = new Vector(1,1); // Vector que contiene todas las comparaciones realizadas entre los términos que pudieron compararse. Guarda los términos con los guiones correspondientes.
    Vector indices = new Vector(1,1); // Vector que guarda las divisiones entre las distintas tablas de comparación.
    Vector terms = new Vector(1,1); // Vector que contiene los índices de las comparaciones realizadas, este vector sirve para saber que términos no se pudieron comparar y mandarlos al vector "termsfinal".
    Vector termsfinal = new Vector(1,1); // Vector que contiene los términos que forman parte de la ecuación minimizada.
    String ecufinal=""; // String que contiene la ecuación resultante de la minimización.
    
    
    /*Constructor que inicializa la tabla de verdad y objetos necesarios.*/
    
    public QMC() { 
    
    	super("Quine-McCluskey"); // Título del programa que se despliega en la barra de título.
    
    	/*Con esto se pone el logo del programa en el text area*/
    
    	tablavddarea.setText("\n" + "                  Quine - McCluskey" +
    	"\n" + "                  ___    __  __    ____" + "\n" +
    	"                /   _    " + "\\ " + "|  " + "  \\" + "/     | /  ___ |" + "\n" + 
    	"                |  |   |    |   |" + "\\" + "/|    |    |   " + "\n" + 
    	"                |  |_ |    |   |  |    |    |___ " + "\n" + 
    	"                " + "\\  " + "__ " + "\\" + "_" + "\\ " + "_|  | _ |" + "\\  " + "____|" + "\n\n" + 
    	"            All rights reserved 2005  ®");
    	
    	/*Con esto se agregan los Radio Buttons a un "grupo" para que sólo uno de ellos pueda estar seleccionado a la vez".*/
    	
    	botones = new ButtonGroup();
    	botones.add(invisible);
	    botones.add(minisrb);
    	botones.add(maxisrb);
	    botones.add(termrb);
    	botones.add(filerb);
	    
    	/*Alineación de los objetos dentro de los páneles e inserción de los mismos.*/
    	
	    panel1 = new JPanel(new BorderLayout());
    	panel1.add(minisrb, BorderLayout.WEST);
    	
	    panel2 = new JPanel(new BorderLayout());
    	panel2.add(maxisrb, BorderLayout.WEST);
	    
    	panel3 = new JPanel(new BorderLayout());
    	panel3.add(termrb, BorderLayout.WEST);
    
    	panel4 = new JPanel(new BorderLayout());
    	panel4.add(filerb, BorderLayout.WEST);
    	
    	panel5 = new JPanel(new GridLayout(4,1));
    	panel5.add(panel1);
    	panel5.add(panel2);
    	panel5.add(panel3);
    	panel5.add(panel4);
	       
	    panel6 = new JPanel(new FlowLayout());
	    panel6.add(suma);
	    panel6.add(ministx);
    	
    	panel7 = new JPanel(new FlowLayout());
    	panel7.add(prod);
    	panel7.add(maxistx);
	    
    	panel8 = new JPanel(new FlowLayout());
    	panel8.add(term);
    	panel8.add(termtx);
	    
	    panel9 = new JPanel(new FlowLayout());
	    panel9.add(filetx);
	    
	    panel10 = new JPanel(new GridLayout(4,1));
	    panel10.add(panel6);
	    panel10.add(panel7);
	    panel10.add(panel8);
	    panel10.add(panel9);
	    
	    panel11 = new JPanel(new GridLayout(4,1));
	    panel11.add(minisbt);
	    panel11.add(maxisbt);
    	panel11.add(termbt);
    	panel11.add(filebt);
    	
    	panel12 = new JPanel(new GridLayout(1,3));
    	panel12.add(panel5);
    	panel12.add(panel10);
    	panel12.add(panel11);
		    
	    panel13 = new JPanel(new FlowLayout());
    	panel13.add(resetbt);
	  
	    panel14 = new JPanel(new BorderLayout());
	    panel14.add(panel12, BorderLayout.NORTH);
	    panel14.add(panel13, BorderLayout.SOUTH);
	    panel14.setBorder(BorderFactory.createTitledBorder("Inserción de datos"));
	    
	    panel15 = new JPanel(new FlowLayout());
	    panel15.add(minimizarbt);
	    panel15.add(ecuaciontx);
	    panel15.add(copiarbt);
	    
	    panel16 = new JPanel(new FlowLayout());
	    panel16.add(procedimsalidatx);
    	panel16.add(procedimsalidabt);
	    
	    panel17 = new JPanel(new GridLayout(2,1));
	    panel17.add(panel15);
	    panel17.add(panel16);
	    panel17.setBorder(BorderFactory.createTitledBorder("Minimización"));
	    
	    panel18 = new JScrollPane(tablavddarea);
	    
	    panel19 = new JPanel(new FlowLayout());
	    panel19.add(panel18);
	    
	    panel20 = new JPanel(new FlowLayout());
	    panel20.add(tablasalidatx);
	    panel20.add(tablasalidabt);
	    
	    panel21 = new JPanel(new BorderLayout());
	    panel21.add(panel19, BorderLayout.NORTH);
	    panel21.add(panel20, BorderLayout.SOUTH);
	    panel21.setBorder(BorderFactory.createTitledBorder("Tabla de verdad"));
	    
	    /*Alineación de los text fields para que el texto se muestre centrado.*/
	    
	    filetx.setHorizontalAlignment(0);
	    procedimsalidatx.setHorizontalAlignment(0);
    	tablasalidatx.setHorizontalAlignment(0);
	    
	    /*Cambio del color de los campos desactivados para mejor visualización*/
	    
	    ministx.setDisabledTextColor(Color.LIGHT_GRAY);
	    maxistx.setDisabledTextColor(Color.LIGHT_GRAY);
	    termtx.setDisabledTextColor(Color.LIGHT_GRAY);
	    filetx.setDisabledTextColor(Color.LIGHT_GRAY);
	    ecuaciontx.setDisabledTextColor(Color.BLACK);
	    procedimsalidatx.setDisabledTextColor(Color.LIGHT_GRAY);
    	tablavddarea.setDisabledTextColor(Color.WHITE);
    	tablasalidatx.setDisabledTextColor(Color.LIGHT_GRAY);
    	tablavddarea.setBackground(Color.BLACK);
	    
	    /*Aquí se deshabilitan los controles al inicio de la ejecucución.*/
	    
	    suma.setEnabled(false);
    	ministx.setEnabled(false);
	    minisbt.setEnabled(false);
    	prod.setEnabled(false);
    	maxistx.setEnabled(false);
    	maxisbt.setEnabled(false);
    	term.setEnabled(false);
    	termtx.setEnabled(false);
    	termbt.setEnabled(false);
    	filetx.setEnabled(false);
    	filebt.setEnabled(false);
    	resetbt.setEnabled(false);
    	minimizarbt.setEnabled(false);
    	ecuaciontx.setEnabled(false);
    	copiarbt.setEnabled(false);
    	procedimsalidatx.setEnabled(false);
    	procedimsalidabt.setEnabled(false);
    	tablavddarea.setEnabled(false);
    	tablasalidatx.setEnabled(false);
    	tablasalidabt.setEnabled(false);
    	
	    /*Aquí se añaden los TollTipText's a cada unos de los objetos gráficos.*/
    	
	    minisrb.setToolTipText("Seleccione esta opción para introducir los minitérminos");
    	ministx.setToolTipText("Introduzca los minitérminos separados por comas, el primer dígito corresponde al número de variables (3,0,1,2...)");
    	minisbt.setToolTipText("Carga los minitérminos introducidos");
    	maxisrb.setToolTipText("Seleccione esta opción para introducir los maxitérminos");
    	maxistx.setToolTipText("Introduzca los maxitérminos separados por comas, el primer dígito corresponde al número de variables (3,0,1,2...)");
    	maxisbt.setToolTipText("Carga los maxitérminos introducidos");
    	termrb.setToolTipText("Seleccione esta opción para introducir la tabla de verdad, término por término");
    	termtx.setToolTipText("Introduzca el número de variables involucradas (1 a 19)");
    	termbt.setToolTipText("Presione para comenzar a introducir los términos");
    	filerb.setToolTipText("Seleccione esta opción para cargar la tabla de verdad desde una archivo de texto existente");
    	filetx.setToolTipText("Ruta del archivo cargado");
    	filebt.setToolTipText("Carga el archivo de texto que contiene la tabla de verdad");
    	resetbt.setToolTipText("Borra todos los datos introducidos");
    	minimizarbt.setToolTipText("Minimiza la tabla de verdad introducida usando el método de Quine-McCluskey");
    	ecuaciontx.setToolTipText("Ecuación resultante de la minimización");
    	copiarbt.setToolTipText("Copia la ecuación resultante al porta-papeles");
    	procedimsalidatx.setToolTipText("Ruta del archivo guardado que contiene los procedimientos realizados");
    	procedimsalidabt.setToolTipText("Guarda los procedimientos realizados en el programa en un archivo de texto");
    	tablavddarea.setToolTipText("Aquí se muestra la tabla de verdad introducida");
    	tablasalidatx.setToolTipText("Ruta del archivo guardado que contiene la tabla de verdad");
    	tablasalidabt.setToolTipText("Guarda la tabla de verdad introducida en un archivo de texto");
    	
	    /*Aquí se ponen a escuchar a todos los objetos que lo requieren.*/
    	
    	minisrb.addActionListener(this);
    	minisbt.addActionListener(this);
    	maxisrb.addActionListener(this);
    	maxisbt.addActionListener(this);
    	termrb.addActionListener(this);
    	termbt.addActionListener(this);
    	filerb.addActionListener(this);
    	filebt.addActionListener(this);
    	minimizarbt.addActionListener(this);
    	resetbt.addActionListener(this);
    	copiarbt.addActionListener(this);
    	procedimsalidabt.addActionListener(this);
    	tablasalidabt.addActionListener(this);
    	
    	/*Aquí se agrgan los tres páneles principales al frame.*/
	    
    	getContentPane().add(panel14, BorderLayout.NORTH);
    	getContentPane().add(panel17, BorderLayout.CENTER);
    	getContentPane().add(panel21, BorderLayout.SOUTH);
    	
    	/*Se modifica el filtro para que sólo muestre archivos "txt".*/
	    
	    filtro.addExtension("txt");
	    filtro.setDescription("Archivos de texto ASCII");
	    selector.setFileFilter(filtro);
    	
	    setIconImage(icono.getImage()); // Se agrega el ícono a la aplicación.
		setSize(440,560); // Se define el tamaño de la aplicación.
		setResizable(false); // Se bloquea el tamaño para que no pueda ser modificado por el usuario.
		setLocation(420,120); // Se posiciona la ventana en la pantalla.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Se define la acción al cerrar la ventana.
    }
    
    
    /*Método main de la clase.*/
    
    public static void main(String args []) throws IOException{
    	setDefaultLookAndFeelDecorated(true); // Ee define que el el look del programa sea el default de java.
		QMC qmc = new QMC(); // Creamos un objeto de tipo QMC para que el programa funcione.
		qmc.setVisible(true); // Hacemos visible el frame.
    }
    
    
    /*Método que llena la tabla de verdad a partir de los minitérminos introducidos por el usuario.*/
    
    public void llenarMinis(){
    	tablavddarea.setText(""); // Aquí se limpia el text area cada vez que se inicia el método.
    	String minis = ministx.getText(); // String que recoge los minitérminos introducidos en ministx.
    	if(minis.length()<3){ // Condición 	que valida que se introduza algo en el text field, de lo contrario manda un mensaje de error.
    		JOptionPane.showMessageDialog(null,"Número de argumentos no válido, por favor indique el número de variables y al menos un minitérmino.","# de argumentos no válido!",2); // Si la longitud de la cadena minis es menor que 3 despliega un mensaje de error y termina el método.
    	}
    	else{
    		StringTokenizer st = new StringTokenizer(minis,","); // Declaración de un objeto de tipo StringTokenizer para separar los minitérminos introducidos por el usuario.
	    	int numminis = st.countTokens()-1; // Variable que almacena el número de "tokens" que tiene la cadena "minis" -1 (se resta 1, porque el primer token es el número de variables).
    		int [] miniterminos = new int[numminis]; // Arreglo de enteros que con capacidad igual al número de minitérminos introducidos.
    		String variables = st.nextToken(); // La cadena variables obtiene el primer token, es decir, el número de variables con el que se trabajará.
	    	char var = variables.charAt(0); // Variable que recoje el primer caracter del String variables.
    		if(!(Character.isDigit(var))){ // Condición que valida que se escriba un número en el text field "ministx" para el número de variables..
    			JOptionPane.showMessageDialog(null,"No se ha introducido un número, introduzca un número válido.","# de variables no válido!",2); // Si var no es un dígito, despliega un mensaje de error y termina el método.
	    	}
    		else{
    			numvar =  Integer.parseInt(variables); // numvar es una variable global, aquí recibe el valor como entero de la cadena variables.
  		  		if(numvar==0){ // Condición que valida que el número de variables sea distinto de cero.
    				JOptionPane.showMessageDialog(null,"El número de variables no puede ser igual a 0.","# de variables no válido!",2);	// Si numvar es igual a cero, muestra un mensaje de error y termina el método.
	    		}
	    		else{ // Si numvar es distinto de cero, se ejecuta: 
    				defineTabla(); // Se manda llamar al método defineTabla, pues ya se cuenta con el número de variables involucradas.
    				for(int x=0; x<numminis; x++){ // Ciclo con el que se llena el arreglo de enteros "miniterminos" con los términos introducidos.
    					String termino = st.nextToken(); // Cadena que recoge el siguiente token (el siguiente término).
	    				miniterminos[x] = Integer.parseInt(termino); // Aquí se guarda el minitérmino en el arreglo "miniterminos", en la posición dada por "x".
	    			}
			    	for(int y=0; y<filas; y++){ // Ciclo con el que se recorre la tabla de verdad y se busca en el arreglo "miniterminos" la fila donde existe un minitérmino y se guarda en la tabla de verdad.
    					for(int z=0; z<numminis; z++){ // Ciclo que recorre el arreglo "miniterminos".
    						if(y == miniterminos[z]){ // Condicion que checa si el número de la fila de la tabla de verdad está dentro del arreglo "miniterminos".
    							tablavdd[y][numvar] = '1'; // Si la condición anterior se cumple, se guarda en la fila de la tabla de verdad correspondiente, en la columna "numvar" un 1.
    						}
		    				else{ // Si la condición anterior no se cumple, se ejecutan las siguientes acciones.
		    					if(tablavdd[y][numvar] != '1'){ // Si la tabla de verdad en la fila correspondiente es distinto de 1, se guada un 0, de lo contratio no se hace nada, pues significa que ya se ha guardado un 1.
    								tablavdd[y][numvar] ='0';
    							}
	    					}
    					}
	    			}	
    				imprimeTabla(); // Se manda llamar al método "imprimeTabla" que imprime la tabla ya llena en "tablavddarea".
	    			tablasalidabt.setEnabled(true);
	    			minimizarbt.setEnabled(true);
    			}
    		}
    	}
    }
    
    
    /*Método que llena la tabla de verdad a partir de los miaxtérminos introducidos por el usuario.*/
        
    public void llenarMaxis(){
    	tablavddarea.setText(""); // Aquí se limpia el text area cada vez que se inicia el método.
    	String maxis = maxistx.getText(); // String que recoge los minitérminos introducidos en maxistx.
    	if(maxis.length()<3){ // Condición 	que valida que se introduza algo en el text field, de lo contrario manda un mensaje de error.
    		JOptionPane.showMessageDialog(null,"Número de argumentos no válido, por favor indique el número de variables y al menos un maxitérmino.","# de argumentos no válido!",2); // Si la longitud de la cadena maxis es menor que 3 despliega un mensaje de error y termina el método.
    	}
    	else{
    		StringTokenizer st = new StringTokenizer(maxis,","); // Declaración de un objeto de tipo StringTokenizer para separar los maxitérminos introducidos por el usuario.
    		int nummaxis = st.countTokens()-1; // Variable que almacena el número de "tokens" que tiene la cadena "maxis" -1 (se resta 1, porque el primer token es el número de variables).
    		int [] maxiterminos = new int[nummaxis]; // Arreglo de enteros que con capacidad igual al número de maxitérminos introducidos.
    		String variables = st.nextToken(); // La cadena variables obtiene el primer token, es decir, el número de variables con el que se trabajará.
    		char var = variables.charAt(0); // Variable que recoje el primer caracter del String variables.
    		if(!(Character.isDigit(var))){ // Condición que valida que se escriba un número en el text field "maxistx" para el número de variables..
    			JOptionPane.showMessageDialog(null,"No se ha introducido un número, introduzca un número válido.","# de variables no válido!",2); // Si var no es un dígito, despliega un mensaje de error y termina el método.
	    	}
    		else{
    			numvar =  Integer.parseInt(variables); // numvar es una variable global, aquí recibe el valor como entero de la cadena variables.
	    		if(numvar==0){ // Condición que valida que el número de variables sea distinto de cero.
	    			JOptionPane.showMessageDialog(null,"El número de variables no puede ser igual a 0.","# de variables no válido!",2);	// Si numvar es igual a cero, muestra un mensaje de error y termina el método.
    			}
    			else{ // Si numvar es distinto de cero, se ejecuta:
    				defineTabla(); // Se manda llamar al método defineTabla, pues ya se cuenta con el número de variables involucradas.
    				for(int x=0; x<nummaxis; x++){ // Ciclo con el que se llena el arreglo de enteros "maxiterminos" con los términos introducidos.
		    			String termino = st.nextToken(); // Cadena que recoge el siguiente token (el siguiente término).
			    		maxiterminos[x] = Integer.parseInt(termino); // Aquí se guarda el maxitérmino en el arreglo "maxiterminos", en la posición dada por "x".
    				}
	    			for(int y=0; y<filas; y++){// Ciclo con el que se recorre la tabla de verdad y se busca en el arreglo "maxiterminos" la fila donde existe un maxitérmino y se guarda en la tabla de verdad.
    					for(int z=0; z<nummaxis; z++){ // Ciclo que recorre el arreglo "maxiterminos".
    						if(y == maxiterminos[z]){  // Condicion que checa si el número de la fila de la tabla de verdad está dentro del arreglo "maxiterminos".
    							tablavdd[y][numvar] = '0'; // Si la condición anterior se cumple, se guarda en la fila de la tabla de verdad correspondiente, en la columna "numvar" un 0.
	    					}
	    					else{ // Si la condición anterior no se cumple, se ejecutan las siguientes acciones.
		    					if(tablavdd[y][numvar] != '0'){ // Si la tabla de verdad en la fila correspondiente es distinto de 0, se guada un 1, de lo contratio no se hace nada, pues significa que ya se ha guardado un 0.
    								tablavdd[y][numvar] ='1';
    							}
    						}
    					}
    				}
	    			imprimeTabla(); // Se manda llamar al método "imprimeTabla" que imprime la tabla ya llena en "tablavddarea".
    				tablasalidabt.setEnabled(true);
    				minimizarbt.setEnabled(true);
    			}
    		}
    	}
    }
    
    
    /*Método que llena la tabla de verdad a partir de los términos individuales que introduzca por el usuario.*/
    
    public void llenarTerm(){
    	tablavddarea.setText(""); // Aquí se limpia el text area cada vez que se inicia el método.
    	String variables = termtx.getText(); // String que recoge el número de variables introducido en termtx.
    	if(variables.length()<1){ // Condición 	que valida que se introduza algo en el text field, de lo contrario manda un mensaje de error.
    		JOptionPane.showMessageDialog(null,"Número de argumentos no válido, por favor indique el número de variables.","# de argumentos no válido!",2); // Si la longitud de la cadena variables es menor que 0 despliega un mensaje de error y termina el método.
    	}
    	else{
    	   	char var = variables.charAt(0); // Variable que recoje el primer caracter del String variables.
    		if(!(Character.isDigit(var))){ // Condición que valida que se escriba un número en el text field "termtx".
    			JOptionPane.showMessageDialog(null,"No se ha introducido un número, introduzca un número válido.","# de variables no válido!",2); // Si var no es un dígito, despliega un mensaje de error y termina el método.
	    	}
    		else{
    			numvar =  Integer.parseInt(variables); // numvar es una variable global, aquí recibe el valor como entero de la cadena variables.
    			if(numvar==0){ // Condición que valida que el número de variables no sea cero.
    				JOptionPane.showMessageDialog(null,"El número de variables no puede ser igual a 0.","# de variables no válido!",2);	// Si numvar es igual a cero, despliega un mensaje de error y termina el método.
		    	}
    			else{ // Si numvar es distinto de cero se ejecuta:
    				defineTabla(); // Se manda llamar al método defineTabla, pues ya se cuenta con el número de variables involucradas.
    				for(int x=numvar-1; x>=0; x--){ // Con este ciclo se imprimen el nombre de las variables como la primera fila dentro del text area.
            			tablavddarea.append(vars[x]+" "); // Impresión en el text area.
			        }
    			    tablavddarea.append("Y\n"); // Aquí se imprime una "Y" que identifica la columna de valores para cada combinación.
	    			String numero = ""; // String que guardará la combinación en binario de la fila.
    				for(int x=0; x<filas; x++){ // Ciclo con el que se recorren todas las filas de la tabla.
        	    		for(int y=0; y<numvar; y++){ // Ciclo con el que se forma el número de fila a partir de los dígitos de la tabla de verdad.
	            		    tablavddarea.append(tablavdd[x][y]+ " "); // Aquí se va imprimienda cada uno de los dígitos de la combinación en el text area.
    	            		numero = numero + tablavdd[x][y]; // Aquí la cadena número va aumentando hasta tener todos los dígitos de la combinación correspondiete.
		    	        }
    		    	    String res = null; // Aquí se crea un String y se iguala a "null". Este string guardará el resultado para cada una de la combinaciones de la tabla.
        		    	boolean valido = false; // Aquí se crea un objeto de tipo de boolean y se iguala a "false". Esta variable determinará si el resultado introducido para las combinaciones es o no válido.
	        	    	while (valido == false){ // Ciclo que se repite mientras "valido" sea igual a false.
		        	    	res = JOptionPane.showInputDialog(null, "Introduce un valor para la combinación " + x + ": " + numero, "Combinación " + x +": " + numero,3); // Aquí se muestra un recuadro en el que el usuario introduce el valor para la combinación correspondiente.
    		        		if(res == null || res == "" || res.length()<=0){ // Este if valida que si no se escribe nada, automáticamente se tome la respuesta como un 1.
	        		        	tablavdd[x][numvar] = '0'; // Con esto se guarda un 0 en la fila "x" de la tabla de verdad, en la columna "numvar".
    	        		    	valido = true; // La variable "valido" cambia a true para que se deje de preguntar la combinación actual y pueda pasar a la siguiente.
            				}
		            		else if(res.charAt(0) == '0' || res.charAt(0) == '1'){ // Este else if valida que si se escribe un 1 ó un 0 se tome como respuesta válida.
    		        			tablavdd[x][numvar] = res.charAt(0); // Aquí se extrae el primer caracter que será el valor para la combinación.
	    		        		valido = true; // La variable "valido" cambia a true para que se deje de preguntar la combinación actual y pueda pasar a la siguiente.
    	        			}
        	    		}
            			tablavddarea.append(tablavdd[x][numvar]+"\n"); // Aquí se imprime en el text area el resultado de la combinación correspondiente y un salto de línea.
	            		numero = ""; // Aquí se resetea el valor de la variable "numero" para que se vuelva a ejecutar el ciclo correctamente.
	    			}
    				tablasalidabt.setEnabled(true);
    				minimizarbt.setEnabled(true);
    			}
	    	}
   		}

    }
    
    
    /*Método que llena la tabla de verdad a partir de un archivo de texto existente.*/
    
    public void llenarArchivo(String direccion) throws Exception{
    	tablavddarea.setText(""); // Aquí se limpia el text area cada vez que se inicia el método.
    	char digito; // Variable que contiene cada uno de los bits de las combinaciones y el resultado para depositarlo en la tabla de verdad.
    	BufferedReader entrada = new BufferedReader(new FileReader(direccion)); // Se crea un objeto para leer el archivo que contiene la tabla de verdad.
		String linea = entrada.readLine(); // La variable linea contiene la linea actual del archivo, aquí se iguala a la primera línea que contiene el archivo (los nombres de las variables).
		StringTokenizer variables = new StringTokenizer(linea); // Se crea un objeto StringTokenizer para que se desplace por la primera línea del archivo y se puedan contar el número de variables.
		numvar = variables.countTokens()-1; // Aquí se manda a numvar el número de "tokens" que tiene la primera línea del archivo menos 1, que equivale al número de variables que tiene la tabla de verdad.
		filas = (int)Math.pow(2,numvar); // filas es una variable global que se iguala al resultado de 2^# de variables.
    	tablavdd = new char[filas][numvar+1]; // Aquí se inicializa el arreglo "tablavdd" con filas igual a "filas" y columnas igual "numvar+1".
    	vars = new char[numvar]; // Aquí se inicializa al arreglo "vars" con un número de casillas igual a "numvar".
		llenaVars(); // Se manda llamar al método llenaVars, se manda llamar desde aquí debido a que el método defineTabla no se llama al llenar la tabla desde archivo.
		linea = entrada.readLine(); // Al string linea se le manda la siguiente linea del archivo (la combinación 0).
		for(int x=0; x<filas; x++){ // Ciclo que recorre todas las líneas del archivo para leer las combinaciones y su resultado.
			StringTokenizer valores = new StringTokenizer(linea); // Se crea un objeto StringTokenizer que recibe la linea actual del archivo.
			for(int y=0; y<numvar+1; y++){ // Ciclo con el que se lee cada uno de los bits de las combinaciones y el resultado de las mismas.
				digito = valores.nextToken().charAt(0); // Se guarda en "digito" el valor de cada bit de la linea actual y el resultado de dicha combinación.
				tablavdd[x][y] = digito; // Se transfiere a la tabla de verdad el valor de "digito" en la posición correspondiente.
			}
			linea = entrada.readLine(); // Se lee la siguiente línea del archivo y se deposita en la variable "linea".
		}
		entrada.close(); // Se cierra el archivo de donde se leyó la tabla de verdad.
		imprimeTabla(); // Se manda llamar al método imprimeTabla para que se despliegue la tabla de verdad en el text area.
		tablasalidabt.setEnabled(true); // Se habilita el botón "tablasalidabt".
		minimizarbt.setEnabled(true); // Se habilita el botón "minimizarbt".
    }
    
    
    /*Método que define las propiedades de la tabla de verdad*/
    
    public void defineTabla(){
    	filas = (int)Math.pow(2,numvar); // filas es una variable global que se iguala al resultado de 2^# de variables.
    	tablavdd = new char[filas][numvar+1]; // Aquí se inicializa el arreglo "tablavdd" con filas igual a "filas" y columnas igual "numvar+1".
    	vars = new char[numvar]; // Aquí se inicializa al arreglo "vars" con un número de casillas igual a "numvar".
    	llenaVars(); // Se  manda llamar al método llenaVars.
    	llenaTabla(); // Se manda llamar al método llenaTabla.
    }
    
    
    /*Método que llena el arreglo de las variables, empezando en "A", caracter 65 de ASCII.*/
    
    public void llenaVars(){
        for(int x=0; x<numvar; x++){ // Ciclo que llena todas las casillas del arreglo "vars" con letras desde la 'A'.
            vars[x] = (char)(65 + x); // Aquí se guarda la letra en código ASCII correspondiente, sumando a 65 el valor de x.
        }
    }
    
    
    /*Método que llena la tabla de verdad con todas las combinaciones posibles.*/
    
    public void llenaTabla(){
        for(int x=0; x<filas; x++){ // Ciclo con el que se llena cada fila de la tabla.
            String numero = Integer.toBinaryString(x); // Se crea un string que contiene el número de fila en binario.
            int numceros = numvar - numero.length(); // Aquí se guarda el número de ceros que debe tener a la izquierda cada número.
            for(int y=0; y<numceros; y++){ // Ciclo con el que se agregan los ceros a la izquierda del número en binario.
                numero = '0' + numero;
            }
            char [] temp = numero.toCharArray(); // Se crea un arreglo temporal que guarda el número como chars.
            for(int z=0; z<numvar; z++){ // Ciclo con el que se transfiere cada dígito del arreglo temporal a su respectiva celda dentro de la tabla.
            tablavdd[x][z] = temp[z];
            }
        }
    }
    
    
    /*Método que imprime la tabla de verdad en el text area.*/
    
    public void imprimeTabla() { 
    	for(int x=numvar-1; x>=0; x--){ // Con este ciclo se imprimen el nombre de las variables como la primera fila.
        	tablavddarea.append(vars[x]+" "); // Impresión en el text area.
        }
        tablavddarea.append("Y\n"); // Aquí se imprime una "Y" que identifica la columna de valores para cada combinación.
        for(int x=0; x<filas; x++){ // Ciclo con el que se imprimen cada una de las filas de la tabla de verdad.
        	for(int y=0; y<=numvar; y++){ // Ciclo con el que se imprimen cada una de las columnas de la tabla de verdad.
            	tablavddarea.append(tablavdd[x][y]+ " "); // Impresión en pantalla.
            }
            tablavddarea.append("\n"); // Impresión de un salto de línea en pantalla.
        }
    }
    
    
    /*Método que guarda la tabla de verdad en un archivo de texto*/
    
    public void guardarTabla(String direccion) throws Exception{
    	PrintWriter salida = new PrintWriter(new FileWriter(direccion)); // Objeto que crea el archivo a escribir.
        for(int x=numvar-1; x>=0; x--){ // Con este ciclo se imprimen el nombre de las variables como la primera fila.
            salida.print(vars[x]+" "); // Impresión en el archivo.
        }
        salida.print("Y"); // Impresión de la "Y" en el archivo.
        salida.println(); // Impresión de un salto de línea en el archivo.
        for(int x=0; x<filas; x++){ // Ciclo con el que se imprimen cada una de las filas de la tabla de verdad.
            for(int y=0; y<=numvar; y++){ // Ciclo con el que se imprimen cada una de las columnas de la tabla de verdad.
                salida.print(tablavdd[x][y]+ " "); // Impresión en el archivo.
            }
            salida.println(); // Impresión de un salto de línea en el archivo.
        }
        salida.close(); // Se cierra el archivo de texto creado.
    }
    
    
    /*Método con el que se empieza la minimización de la función introducida.*/
    
    public void minimizar(){
    	minimizarbt.setEnabled(false); // Se desabilita el botón de minimizar.
    	borraVariables(); // Se manda llamar al método borraVariables para que se remuevan los elementos de los vectores.
    	llenaPesos(); // Se manda llamar al método llenaPesos que llenal la tabla de pesos.
    }
    
    
    /*Método que llena la tabla de pesos.*/
    
    public void llenaPesos(){
    	pmax=0; // Se resetea el valor de "pmax" a cero.
    	filasp = 0; // Se resetea el valor de "filasp" a cero.
        int cont = 0; // Variable que guarda el peso temporal de cada término cuando se revisa.
        int filap =0; // Variable que guarda la fila de la tabla de pesos en la que se guarda el peso correspondiente y su combinación.
        for(int x=0; x<filas; x++){ // Ciclo que recorre la tabla de verdad en busca de los términos que valen 1.
            if(tablavdd[x][numvar] == '1') filasp++; // Si el término correspondiente vale 1 se incrementa filasp.
        }
        tablap = new int[filasp][2]; // Aquí se inicializa el arreglo tablap con el número de filas que obtiene de filasp.
        while(filap < filasp){ // Ciclo que compara que la tabla de pesos aún no esté completamente llena.
        	for(int z=0; z<filas; z++){ // Ciclo que recorre la tabla de verdad en busca de los términos que valen 1.
        		if(tablavdd[z][numvar] == '1'){
        			for(int v=0; v<numvar; v++){ // Ciclo que se ejecuta si el término correspondiente vale 1. Este ciclo determina el peso del término en cuestión.
        				if(tablavdd[z][v] == '1') cont++;
        			}
        			if(cont == pmax){ // Condición que se ejecuta si el término vale 1, y determina si el peso de dicho término es igual al peso máximo en ese momento.
        				tablap[filap][0] = pmax; // Si la condición se cumple guarda en la tabla de pesos en la fila "filap" y en la columna 0 el peso máximo hasta ese momento.
        				tablap[filap][1] = z; // Si la condición se cumple guarda en la tabla de pesos en la fila "filap" y en la columna 1 el índice de la tabla de verdad que tiene ese peso.
        				filap++; // Incrementa la fila en la que se guardarán los datos en la tabla de pesos (tablap).
        			}
        		}
        		cont=0; // Se resetea el valor de "cont" a cero para que se cuente el peso del siguiente término que vale 1.
        	}
        	pmax++; // Se incrementa "pmax" una vez que se ha llenado la tabla de pesos con los términos que tienen el peso máximo actual.
        }
        pmax--; // Se resta a "pmax" 1 para que guarde verdaderamente el peso máximo existente dentro de la tabla.
        if(filasp == 0){ // Condición que comprueba si "filasp" vale 0, esto significa que no hay términos con peso mayor que cero, y por tanto la ecuación resultante es 0.
        	ecufinal = "0"; // Se asigna a ecufinal "0".
        	ecuaciontx.setText("Y = " + ecufinal); // Se manda al text field "ecuaciontx" Y = más ecufinal que vale 0.
        }
        else{ // Si la condición no se cumple, es decir, si al menos un término tuvo un peso mayor que cero:
        	comparaPesos(); // Se manda llamar al método comparaPesos que continúa con el proceso de minimización.
        }
    }
    
    
    /*Método que realiza la primer tabla de comparación entre los términos que valen 1.*/
    
    public void comparaPesos(){
    	indices.add(0); // Se agrega al vector "indices" el valor "0" ya que es este valor que se empiezan a agregar los términos comparados en el vector comparaciones.
       	int pesoa = tablap[0][0]; // Variable que guarda el peso inicial de la comparación en curso.
    	int filapa = 0; // Variable que guarda la fila que tiene el peso actual que se está comparando.
    	int filaa= 0; // Variable que guarda la fila actual de contra la que se está comparando e peso actual.
    	int cambio=0; // Variable que guarda el número de cambios que hay entre dos términos.
    	int indice1 = 0; // Variable que guarda el número del término que tiene el peso actual.
    	int indice2 = 0; // Variable que guarda el número del término que tiene un peso igual al peso actual más uno.
    	char temp1 [] = new char[numvar]; // Arreglo de caracteres que guarda el término del peso actual.
    	char temp2 [] = new char[numvar]; // Arreglo de caracteres que guarda el término contra el que se está comparando el peso actual.
    	for(int q=0; q<filasp; q++){ // Ciclo exterior que recorre todas las filas de la tabla de pesos para que se realicen las comparaciones con los términos inferiores.	
    		for(int x=filaa; x<filasp; x++){ // Ciclo interior que recorre todas las filas de la tabla de pesos a partir de la fila actual para que se realicen las comparaciones contra la fila que tiene el peso actual.
    			for(int y=0; y<numvar; y++){ // Ciclo que extrae los bits de la tabla de verdad correspondientes al índice 1 y los deposita en el arreglo temp1.
    				indice1 = tablap[filaa][1]; // Se asigna a "indice1" el valor del término de la tabla de pesos correspondiente a la fila actual.
    				temp1[y] = tablavdd[indice1][y]; // Se asigna al arreglo "temp1" el término correspondiente de la tabla de verdad que contiene "indice1".
    			}
    			if(tablap[x][0] == pesoa+1){ // Condición que revisa si la fila actual de la tabla de pesos tiene un peso igual al peso actual más 1.
   					for(int z=0; z<numvar; z++){ // Si la condición se cumple se ejecuta este ciclo con el que se obtienen los bits del término que tiene un peso consecutivo al actual.
   						indice2 = tablap[x][1]; // Se guarda en "indice2" el número del término que tiene un peso consecutivo al actual.
   						temp2[z] = tablavdd[indice2][z]; // Se guarda en el arreglo "temp2" los bits del término que indica "indice2".
	   				}
   					for(int v=0; v<numvar; v++){ // Una vez que se tiene los dos términos que pueden ser comparados porque tienen pesos consecutivos se revisa el número de cambios que hay entre los dos con este ciclo.
   						if(temp1[v]!=temp2[v]){ // Condición que compara si en la posición "v" hay un cambio entre los dos términos.
   							cambio++; // Si existe un cambio se incremente "cambio".
   							temp2[v] = '-'; // Además si existe un cambio se cambia en "temp2" en la posición donde hubo un cambio el 1 ó 0 por un '-'.
		   				}
   					}
   					if(cambio == 1){ // Condición que revisa que durante el ciclo anterior "cambio" sólo haya aumentado hasta 1, es decir que sólo haya habido una sola diferencia entre los dos términos.
   						terms.add(indice1); // Si la condición se cumple se añade al vector terms el valor de "indice1".
   						terms.add(indice2); // Si la condición se cumple se añade al vector terms el valor de "indice2".
		   				char temp3[] =new char[numvar]; // Además si la condición se cumple se crea un arreglo de chars que contiene una copia de "temp2" ya con los guiones.
		   				for(int k=0; k<numvar; k++){ // Con este ciclo se hace una copia de "temp2" celda por celda.
		   					temp3[k] = temp2[k]; // Aquí se deposita el caracter correspondiente de "temp2" en "temp3" en la posición k.
		   				}
		   				comparaciones.add(temp3); // Finalmente, si "cambio" fue igual a 1 se añade el arreglo temp3 al vector comparaciones.
	   				}
	   				cambio=0; // Se resetea "cambio" a cero para que en la siguiente comparación el número de cambios sea el correcto.
   				}
   			}
   			if(filapa<filasp-1){ // Condición que valida que la fila del peso actual sea menor que el número de filas de la tabla de pesos menos 1. Esto se hace para que no se intente comparar una fila más abajo de la tabla de comparaciones.
   				if(tablap[filapa+1][0] == pesoa){ // Si la condición anterior se cumple se revisa que la fila siguiente a la fila del peso actual tenga un peso idéntico.
   				filaa++; // Si la condición se cumple se incrementa la fila actual.
   				filapa++; // Se incrementa la fila del peso actual.
   				}
   				else{ // Si la condición no se cumple se realiza:
   					pesoa = tablap[filapa+1][0]; // Se actualiza "pesoa" al valor del peso de la fila siguiente a fila peso actual.
   					filaa++; // Se incrementa la fila actual.
   					filapa++; // Se incrementa la fila del peso actual.
   				}
   			}	
    	}
    	for(int t=0; t<filasp; t++){ // Ciclo que recorre la tabla de pesos para comparar entre que términos de la tabla hubo comparaciones exitosas, para que aquellos que nunca se hayan comparado se agreguen al vector "termsfinal", ya que aparecerán sin duda en la ecuación final.
    		if(!terms.contains(tablap[t][1])){ // Condición que compara que el vector terms no contenga el término de la fila t.
    			char termino[] = new char[numvar]; // Si el vector terms no contuvo a dicho término se crea un arreglo de chars que contendrá los bits de ese término.
    			for(int u=0; u<numvar; u++){ // Ciclo con el que se deposita cada bit del término que no se comparó al arreglo termino.
    				termino[u] = tablavdd[tablap[t][1]][u]; // Se asigna el bit correspondiente en la posición u al arreglo termino.
    			}
    			termsfinal.add(termino); // Se agrega al vector terms final el arreglo termino.
    		}
    	}
    	if(comparaciones.isEmpty() == false){ // Condición que revisa si el vector comparaciones tiene al menos una comparación.
    		comparaMas(); // Si la condición anterior se cumple se manda llamar al método comparaMas.
    	}
    	else{ // Si el vector comparaciones sí está vacío se manda llamar al método miniterminos.
    		miniterminos();
    	}
    }
    
    
    /*Método que realizan las demás comparaciones si es que se pueden realizar.*/
    
    public void comparaMas(){
    	contagreg = 1; // Se asigna a "contagreg" un 1 para que al menos realice una comparación.
    	int pesoa; // Variable que guarda el peso inicial de la comparación en curso.
    	int pesofa = 0; // Variable que guarda el peso de la fila actual con la que se se está comparando.	
    	int filapa = 0; // Variable que guarda la fila que contiene el peso actual que se está comparando.
    	int filaa= 0; // Variable que guarda la fila actual contra la que se está comparando.
    	int cambio=0; // variable que guarda el número de cambios entre los dos términos comparados.
    	char temp1 []= new char[numvar]; // Arreglo temporal con el que se obtiene el peso inicial de la comparación.
    	char temp2 []= new char[numvar]; // Arreglo temporal que contiene los bits del término de la fila con el peso actual.
    	while(contagreg>0){ // Ciclo que controla el número de tablas de comparación que se realizan. Mientras se haya agregado al menos 1 término al vector comparaciones se sigue realizando el ciclo.
    		pesoa=0; // Se asigna a "pesoa" un cero, se resetea.
    		terms.removeAllElements(); // Se borran todos los términos del vector "terms".
    		contagreg=0; // Se resetea "contagreg" a cero para que se cuenten cuantos términos son agregados realmente.
    		temp1 = (char [])comparaciones.elementAt((Integer)indices.lastElement()); // Se asigna al arreglo "temp1" el arreglo que contiene el vector comparaciones en la fila donde comienza la presenta comparación. Esto se hace por medio del contenido del vector "indices" que guarda la separación entre las tablas de comparación.
    		for(int x=0; x<numvar; x++){ // Ciclo con el que se obtiene el peso del término "temp1".
    			if(temp1[x]=='1'){ // Si existe un 1 en la posición x del arreglo se incrementa pesoa.
    				pesoa++;
    			}
    		}
    		filapa = (Integer)indices.lastElement(); // Se asigna a "filapa" el valor de la última posición del vector "indices".
    		filaa = filapa; // Se iguala "filaa" con "filapa"
    		int numcomp = comparaciones.size(); // Variable que guarda el tamaño del vector comparaciones.
    		indices.add(numcomp); // Se agrega al vector "indices" el valor de "numcomp".
    		int veces = (Integer)indices.lastElement()- (Integer)indices.elementAt(indices.size()-2); // Variable que guarda cuantas veces se realizará el ciclo exterior de la comparación.
    		int veces2 = veces+filaa; // Variable que guarda cuntas veces se realizará el ciclo interior de las comparaciones.
    		for(int y=0; y<veces; y++){ // Ciclo exterior de las comparaciones. Se ciclo se realiza el número de veces que contenga la variable "veces".
    			temp2 = (char [])comparaciones.elementAt(filapa); // Se asigna a "temp2" el arreglo que se encuentra en el vector comparaciones en la fila correspondiente a la fila peso actual.
    			for(int p=filaa; p<veces2; p++){ // Ciclo interior de las comparaciones que se ejecuta a partir de la fila actual hasta el valor de veces2.
    				char temp3 []= new char[numvar]; // Se crea un arreglo temporal que contiene los bits del término de la fila actual (p).
    				temp3 = (char [])comparaciones.elementAt(p); // Se asigna a temp3 el arreglo que contiene el vector comparaciones en la posición "p".
    				for(int z=0; z<numvar; z++){ // Ciclo con el que se obtiene el peso del término temp3.
    					if(temp3[z]=='1'){ // Si temp3 tiene un 1 en la posición z se incrementa "pesofa".
    						pesofa++;
    					}
    				}
    				if(pesofa == pesoa+1){ // Condición que revisa si el peso de la fila actual (peso de temp3) es igual al peso actual más 1.
    					for(int v=0; v<numvar; v++){ // Si la condición anterior se cumple se ejecuta este ciclo con el que se cuentan los cambios entre los dos términos.
   							if(temp2[v]!=temp3[v]){ // Si hay un cambio entre los dos términos en la posición v se incrementa "cambio".
   								cambio++;
		   					}
   						}
   						if(cambio == 1){ // Una vez que se obtuvo el número de cambios entre los dos términos, se comprube que dichos cambios hayan sido sólamente 1.
   							char temp4 [] = new char[numvar]; // Si sólo hubo un cambio se crea un arreglo temporal que contendrá una copia de temp3 pero con los guiones correspondientes.
   							for(int k=0; k<numvar; k++){ // Con este ciclo se copia cada bit del arreglo temp3 a temp4.
		   						temp4[k] = temp3[k]; // Se asigna el valor de temp3 en la posición k a temp4 en la misma posición.
		   					}
   							for(int v=0; v<numvar; v++){ // Ciclo que vuelve a checar las posiciones de los términos temp2 y temp3 en busca de cambios.
   								if(temp2[v]!=temp3[v]){ // Si hay un cambio en la posición v entre los dos términos se guarda en temp4 en la posición v un '-'.
   									temp4[v] = '-';
		   						}
		   					}
   							terms.add(filapa); // Si sólo hubo un cambio también se agrega al vector terms el valor de la filapa.
   							terms.add(p); // Se agrega al vector terms el valor de p (fila actual).
   							comparaciones.add(temp4); // Se agrega al vector comparaciones el arreglo temp4.
   							contagreg++; // Se incrementa contagreg en 1.
		   				}
	   					cambio=0; // Se resetea el valor de "cambio" a cero para calcular el número de cambios de las siguientes comparaciones de manera correcta.
	   				}
	   				pesofa=0; // Se resetea el peso de la fila actual a cero para que se vuelva a calcular su peso de forma correcta.
	   			}
	   			if(filapa<veces2-1){ // Se comprueba que la fila del peso actual sea menor que "veces2" menos 1. Esto se hace para que no se intente comparar una fila más abajo de la actual tabla de comparaciones.
	   				int pesofs = 0; // Variable que guarda el peso de la fila siguiente a la fila del peso actual.
	   				char temp5 []= new char[numvar]; // Se crea un arreglo temporal que ayuda a calcular el peso de la fila siguiente a la fila del peso actual.
	   				temp5 = (char [])comparaciones.elementAt(filapa+1); // Se asigna a "temp5" el arreglo del vector comparaciones en la posición filapa más 1.
    				for(int e=0; e<numvar; e++){ // Ciclo que recorre las posiciones del arreglo temp5 en busca de 1's para calcular su peso.
    					if(temp5[e]=='1'){ // Si temp5 en la posición e tiene un 1 se incrementa pesofs en 1.
    						pesofs++;
		    			}
    				}		 
   					if(pesofs == pesoa){ // Condición que verifica si "pesofs" es igual a "pesoa".
   						filaa++; // Si la condición es válida se incrementa "filaa".
   						filapa++; // Se incrementa "filapa".
   					}
   					else{ // Si la condición no se cumple:
   						pesoa = pesofs; // Se asigna a "pesoa" el valor de "pesofs".
   						filaa++; // Se incrementa "filaa".
	   					filapa++; // Se incrementa "filapa".
   					}
   				}
   			}
   			for(int t=((Integer)indices.elementAt(indices.size()-2)); t<((Integer)indices.lastElement()); t++){ // Ciclo que recorre la tabla de comparación realizada para buscar que términos no se compararon satisfactoriamente y se manden al vector "termsfinal".
   				if(!terms.contains(comparaciones.indexOf(comparaciones.elementAt(t)))){ // Condición que comprueba que el vector terms no contenga el índice del vector comparaciones en la posición t.
    				termsfinal.add(comparaciones.elementAt(t)); // Si terms no contiene dicho índice, se agrega a termsfinal el término en la posición t del vetor comparaciones.
    			}
    		}
		}
		miniterminos();	// Se manda llamar al método miniterminos.
    }
    
    
    /*Método que extrae los minitérminos que forman parte de la ecuación resultante, eliminando los términos repetidos.*/
        
    public void miniterminos(){
    	String ecu = ""; // Se crea un String que contendrá la ecuación resultante mientras se forma.
    	int cambios=0; // Variable que guardará el número de cambios entre los términos que se encuentran dentro de "termsfinal".
    	for(int x=0; x<termsfinal.size(); x++){ // Ciclo que recorre el vector termsfinal en busca de términos repetidos.
    		char tempmini1 [] = new char[numvar]; // Se crea un arreglo que contendrá el término del vector termsfinal en la posición x.
    		tempmini1 = (char [])termsfinal.elementAt(x); // Se asigna a tempmini1 el arreglo de termsfinal en la posición x.
    		for(int y=x+1; y<termsfinal.size();y++){ // Ciclo que recorre los términos inferiores a tempmini1 para ver si hay cambios entre ellos.
    			char tempmini2 [] = new char[numvar]; // Se crea un arreglo que contendrá el término del vector termsfinal en la posición y.
    			tempmini2 = (char [])termsfinal.elementAt(y); // Se asigna a tempmini2 el arreflo de termsfinal en la posición y.
    			for(int v=0; v<numvar; v++){ // Ciclo que recorre las posiciones de ambos arreglos en busca de cambios.
    				if(tempmini1[v]!=tempmini2[v]){ // Condición que comprueba que hay cambios entre los dos términos en la posición v.
    					cambios++; // Si hay un cambio se incrementa "cambios".
    				}
    			}
    			if(cambios==0){ // Si al terminar el ciclo anterior "cambios" sigue valiendo cero, significa que los dos términos son iguales, es decir están repetidos.
    				termsfinal.removeElementAt(y); // Si "cambios" vale cero, se elimina del vector termsfinal el término en la posición y, es decir el término repetido.
    				y--; // Se decrementa y para que cuando se vuelva a ejecutar el ciclo se recorran todos los términos del vector
    			}
    			else{ // Si "cambios" no vale cero significa que los dos términos son distintos y sólo se resetea "cambios" a cero.
    				cambios=0;
    			}
    		}
    	}
    	for(int p=0; p<termsfinal.size(); p++){ // Ciclo que recorre termsfinal (que ya no tiene elementos repetidos) para obtener la ecuación resultante en términos de las variables introducidas.
    		char afinal [] = new char[numvar]; // Se crea un arreglo que contendrá los términos de termsfinal.
    		afinal = (char [])termsfinal.elementAt(p); // Se asigna a afinal el término de termsfinal en la posición p.
    		for(int r=0; r<numvar; r++){ // Ciclo con el que se recorren las posiciones del arreglo afinal.
    			if(afinal[r] == '0'){ // Condición que comprueba si el arreglo "afinal" en la posición r tiene un cero.
    				ecu = ecu + vars[numvar-1-r]+"'"; // Si la condición se cumple, se agrega al String ecu lo que contenga más la varible que equivale a la posición r del arreglo vars más una apóstrofe para indicar que la variable está negada.
    			}
    			else if(afinal[r] == '1'){ // Condición que comprueba si el arreglo "afinal" en la posición r tiene un uno.
    				ecu = ecu + vars[numvar-1-r]; // Si la condición se cumple, se agrega al String ecu lo que contenga más la varible que equivale a la posición r del arreglo vars, así se indicca que la variable no está negada.
    			}
    		}
    		ecu = ecu + " + "; // Se agrega a ecu un signo '+' para indicar la suma de los términos.
    	}
    	int cont = ecu.length(); // Variable que guarda la longitu de ecu.
    	ecu = ecu.substring(0,cont-3); // Se redefine ecu a sí misma pero sin los dos últimos caracteres que son un signo '+' y un espacio (' ').
    	if(ecu.length() == 0){ // Condición que compara si la longitud del ecu es igual a cero, es decir, si no se agregó ninguna variable.
    		ecu = "1"; // Si la condición anterior se cumple, significa que todos los términos eran unos, y por tanto la ecuación es igual a 1.
    	}
    	ecufinal = ecu; // La variable global ecufinal se iguala a ecu.
    	ecuaciontx.setText("Y = " + ecufinal); // Se manda al text area "ecuaciontx" "Y =" más el contenido de ecufinal.
    	copiarbt.setEnabled(true); // Se habilita el botón "copiarbt".
    	procedimsalidabt.setEnabled(true); // Se habilita el botón "procedimsalidabt".
    }
    
    
    /*Método que copia la ecuación resultante al porta-papeles.*/
    
    public void copiar(){
    	String cliptext = ecuaciontx.getText(); // variable guarda el contenido del text area "ecuaciontx".
		Toolkit tk = this.getToolkit(); // Se crea un objeto de tipo Toolkit que recibo al Toolkit del programa.
		Clipboard cb = tk.getSystemClipboard(); // Se crea un objeto de tipo Clipboard que recibe el porta-papeles del "sistema".
		cb.setContents(new StringSelection(cliptext), this); // se deposita en cb (objeto de tipo Clipboard) la variable cliptext.
    }
    
    
    /*Método necesario para poder hacer la copia de un objeto al porta-papeles.*/
    
    public void lostOwnership(Clipboard cb, Transferable tr){
    }
    
    
    /*Método que manda a un archivo los procedimientos realizados durante la simplificación.*/
    
    public void guardarProcedimientos(String direccion) throws Exception{
    	aproced = new PrintWriter(new FileWriter(direccion)); // Se asigna a "apreced" la dirección del archivo sobre el que escriben los procedimientos realizados.
    	int numtablas = indices.size(); // Variable que guarda el número de tablas de comparación que se realizaron.
    	int tablaa = 0; // Variable que guarda la tabla actual que se va a imprimir.
    	/*Impresión sobre el archivo del logo del programa.*/
    	aproced.println();
    	aproced.println();
    	aproced.print("                                   Quine - McCluskey");
    	aproced.println();
    	aproced.println();
    	aproced.println();
    	aproced.print("        QQQQQQQQQ           MMMMMMMM               MMMMMMMM             CCCCCCCCCCCCC");
    	aproced.println();
    	aproced.print("      QQ:::::::::QQ         M:::::::M             M:::::::M          CCC::::::::::::C");
    	aproced.println();
    	aproced.print("    QQ:::::::::::::QQ       M::::::::M           M::::::::M        CC:::::::::::::::C");
    	aproced.println();
    	aproced.print("   Q:::::::QQQ:::::::Q      M:::::::::M         M:::::::::M       C:::::CCCCCCCC::::C");
    	aproced.println();
    	aproced.print("   Q::::::O   Q::::::Q      M::::::::::M       M::::::::::M      C:::::C       CCCCCC");
    	aproced.println();
    	aproced.print("   Q:::::O     Q:::::Q      M:::::::::::M     M:::::::::::M      C:::::C              ");
    	aproced.println();
    	aproced.print("   Q:::::O     Q:::::Q      M:::::::M::::M   M::::M:::::::M      C:::::C              ");
    	aproced.println();
    	aproced.print("   Q:::::O     Q:::::Q      M::::::M M::::M M::::M M::::::M      C:::::C              ");
    	aproced.println();
    	aproced.print("   Q:::::O     Q:::::Q      M::::::M  M::::M::::M  M::::::M      C:::::C              ");
    	aproced.println();
    	aproced.print("   Q:::::O     Q:::::Q      M::::::M   M:::::::M   M::::::M      C:::::C              ");
    	aproced.println();
    	aproced.print("   Q:::::O  QQQQ:::::Q      M::::::M    M:::::M    M::::::M      C:::::C              ");
    	aproced.println();
    	aproced.print("   Q::::::O Q::::::::Q      M::::::M     MMMMM     M::::::M      C:::::C       CCCCCC");
    	aproced.println();
    	aproced.print("   Q:::::::QQ::::::::Q      M::::::M               M::::::M       C:::::CCCCCCCC::::C");
    	aproced.println();
    	aproced.print("    QQ::::::::::::::Q       M::::::M               M::::::M        CC:::::::::::::::C");
    	aproced.println();
    	aproced.print("      QQ:::::::::::Q        M::::::M               M::::::M          CCC::::::::::::C");
    	aproced.println();
    	aproced.print("        QQQQQQQQ::::QQ      MMMMMMMM               MMMMMMMM             CCCCCCCCCCCCC");
    	aproced.println();
    	aproced.print("                Q:::::Q                                    ");
    	aproced.println();
    	aproced.print("                 QQQQQQ                                    ");
    	aproced.println();
    	aproced.println();
    	aproced.println();
    	aproced.print("                              All rights reserved 2005  ®");
    	aproced.println();
    	aproced.println();
    	aproced.println();
    	aproced.println();
    	/*Impresión sobre el archivo.*/
    	aproced.print("Procedimientos realizados para la simplificación de la tabla de verdad introducida.");
    	aproced.println();
    	aproced.println();
    	aproced.print("La tabla de verdad a la que se le aplicó el método de Quine-McCluskey es:");
    	aproced.println();
    	aproced.println();
    	for(int x=numvar-1; x>=0; x--){ // Con este ciclo se imprimen el nombre de las variables como la primera fila.
            aproced.print(vars[x]+" "); // Impresión en el archivo.
        }
        aproced.print("Y"); // Impresión de la "Y" en el archivo.
        aproced.println(); // Impresión de un salto de línea en el archivo.
        for(int x=0; x<filas; x++){ // Ciclo con el que se imprimen cada una de las filas de la tabla de verdad.
            for(int y=0; y<=numvar; y++){ // Ciclo con el que se imprimen cada una de las columnas de la tabla de verdad.
                aproced.print(tablavdd[x][y]+ " "); // Impresión en el archivo.
            }
            aproced.println(); // Impresión de un salto de línea en el archivo.
        }
        aproced.println();
        aproced.print("**************************************************"); // Impresión de una fila de asteriscos en el archivo como división.
        aproced.println();
        aproced.println();
        aproced.print("La tabla de pesos es:"); // Impresión que indica que a continuación se muestra la tabla de pesos.
        aproced.println();
    	aproced.println();
    	aproced.print("Peso ");
    	aproced.print("Término");
    	aproced.println();
    	aproced.println();
        for(int x=0; x<filasp; x++){ // Ciclo que recorre la tabla de pesos.
        	aproced.print(" " + tablap[x][0] + "     "); // Se imprime en el archivo el peso de la fila.
        	aproced.print(tablap[x][1]); // Se imprime en el archivo el término con dicho peso.
        	aproced.println();
        }
        aproced.println();
        aproced.print("**************************************************"); // Impresión de una fila de asteriscos en el archivo como división.
        aproced.println();
        aproced.println();
        while(tablaa < numtablas-1){ // Ciclo que compara si tablaa es menor que numtablas menos 1.
        	aproced.print("La tabla de comparación " + (tablaa+1) + " es:"); // Se imprime el título que indida de qué tabla de comparación se trata.
        	aproced.println();
    		aproced.println();
    		char temp1[] = new char[numvar]; // Arreglo temporal que guarda el término de la tabla de comparaciones que se esté imprimiendo.
    		for(int x=(Integer)indices.elementAt(tablaa); x<(Integer)indices.elementAt(tablaa+1); x++){ // Ciclo que recorre el vector comparaciones desde donde empieza la tabla actual que se está imprimiendo, hasta donde acaba dicha tabla.
    			temp1 = (char [])comparaciones.elementAt(x); // Se deposita en temp1 el término que contiene el vector comparaciones en la posición x.
    			for(int q=0; q<numvar; q++){ // Ciclo que recorre el término bit por bit para imprimirlo.
    				aproced.print(temp1[q]+" "); // Se imprime el bit del término en la posición q.
    			}
    			aproced.println();
    		}
    		aproced.println();
    		aproced.print("**************************************************"); // Impresión de una fila de asteriscos en el archivo como división.
    		aproced.println();
    		aproced.println();
    		tablaa++; // Se aumenta tablaa en 1.
        }
        aproced.print("Los implicantes primos son:"); // Impresión del título que indica que los implicantes primos se muestras a continuación.
        aproced.println();
        aproced.println();
        char temp2[] = new char[numvar]; // Arreglo temporal que guarda el término del vector termsfinal que se está imprimiendo.
        for(int x=0; x<termsfinal.size(); x++){ // Ciclo que recorre el vector termsfinal.
    		temp2 = (char [])termsfinal.elementAt(x); // Se de`posita en temp2 el término del vector termsfinal en la posición x.
    		for(int q=0; q<numvar; q++){ // Ciclo que recorre el término bit por bit para imprimirlo.
    			aproced.print(temp2[q]+" "); // Se imprime el bit del término en la posición q.
    		}
    		aproced.println();
    	}
    	aproced.println();
    	aproced.print("**************************************************"); // Impresión de una fila de asteriscos en el archivo como división.
    	aproced.println();
    	aproced.println();
        aproced.print("La ecuación resultante de la simplificación es:"); // Impresión del título que indica que a continuación se muestra la ecuación resultante.
        aproced.println();
        aproced.println();
        aproced.print("Y = " + ecufinal); // Se imprime en el archivo la ecuación resultante, precedida de " Y = ".
        aproced.close(); // Se cierra el archivo.
    }
    
    
    /*Método que resetea todos los campos del programa a su valor inicial*/
    
    public void reset(){
    	invisible.setSelected(true); // Como no hay un comando para "deseleccionar" todos los botones de un grupo, se selecciona un botón que no aparece en la pantalla pero que sí pertenece al grupo y listo.
    	ministx.setText("4,0,2,3,7,12");
    	maxistx.setText("4,1,4,6,9,15");
    	termtx.setText("0");
    	filetx.setText("Archivo a cargar");
    	ecuaciontx.setText("");
    	procedimsalidatx.setText("Archivo a guardar");
    	tablavddarea.setText("\n" + "                  Quine - McCluskey" +
    	"\n" + "                  ___    __  __    ____" + "\n" +
    	"                /   _    " + "\\ " + "|  " + "  \\" + "/     | /  ___ |" + "\n" + 
    	"                |  |   |    |   |" + "\\" + "/|    |    |   " + "\n" + 
    	"                |  |_ |    |   |  |    |    |___ " + "\n" + 
    	"                " + "\\  " + "__ " + "\\" + "_" + "\\ " + "_|  | _ |" + "\\  " + "____|" + "\n\n" + 
    	"            All rights reserved 2005  ®");
    	tablasalidatx.setText("Archivo a guardar");
    	suma.setEnabled(false);
    	ministx.setEnabled(false);
	    minisbt.setEnabled(false);
    	prod.setEnabled(false);
	    maxistx.setEnabled(false);
    	maxisbt.setEnabled(false);
	    term.setEnabled(false);
    	termtx.setEnabled(false);
	    termbt.setEnabled(false);
    	filetx.setEnabled(false);
	    filebt.setEnabled(false);
    	resetbt.setEnabled(false);
	    minimizarbt.setEnabled(false);
    	ecuaciontx.setEnabled(false);
	    copiarbt.setEnabled(false);
    	procedimsalidatx.setEnabled(false);
	    procedimsalidabt.setEnabled(false);
    	tablavddarea.setEnabled(false);
	    tablasalidatx.setEnabled(false);
    	tablasalidabt.setEnabled(false);
    }    
    
    
    /*Método que vacía los vectores utilizados y resetea "ecufinal".*/
    
    public void borraVariables(){
    	comparaciones.removeAllElements(); // Se remueven todos los elementos que contiene el vector comparaciones.
    	indices.removeAllElements(); // Se remueven todos los elementos que contiene el vector indices.
    	terms.removeAllElements(); // Se remueven todos los elementos que ocntiene el vector termns.
    	termsfinal.removeAllElements(); // Se remueven todos los elementos que contiene el vector termsfinal.
    	ecufinal="";
    }
    
    
    /*Método actionPerformed de la clase que recoje las acciones realizadas por el usuario.*/
    
    public void actionPerformed(ActionEvent e){
    	int respuesta; // Declaración de una variable entera que recogerá el valor de los cuadros de diálogo de abrir y guardar archivos.
    	if(e.getSource().equals(minisrb)){ // Condición que valida si el botón de minitérminos fue presionado. Si es cierto, se desactivan los objetos distintos a esa opción.
    		suma.setEnabled(true);
    		ministx.setEnabled(true);
    		minisbt.setEnabled(true);
    		prod.setEnabled(false);
    		maxistx.setEnabled(false);
    		maxisbt.setEnabled(false);
    		term.setEnabled(false);
    		termtx.setEnabled(false);
    		termbt.setEnabled(false); 
    		filebt.setEnabled(false);
    		resetbt.setEnabled(true);
    	}
    	if(e.getSource().equals(minisbt)){ // Condición que valida si el botón "minisbt" fue presionado, en cuyo caso se manda llamar al método llenarMinis.
    		llenarMinis();    		
    	}
    	if(e.getSource().equals(maxisrb)){ // Condición que valida si el botón de maxitérminos fue presionado. Si es cierto, se desactivan los objetos distintos a esa opción.
    		suma.setEnabled(false);
    		ministx.setEnabled(false);
    		minisbt.setEnabled(false);   
    		prod.setEnabled(true);
    		maxistx.setEnabled(true);
    		maxisbt.setEnabled(true);
    		term.setEnabled(false);
    		termtx.setEnabled(false);
    		termbt.setEnabled(false);
    		filebt.setEnabled(false);
    		resetbt.setEnabled(true);
    	}
    	if(e.getSource().equals(maxisbt)){ // Condición que valida si el botón "maxisbt" fue presionado, en cuyo caso se manda llamar al método llenarMaxis.
    		llenarMaxis();    		
    	}
    	if(e.getSource().equals(termrb)){ // Condición que valida si el botón de término por término fue presionado. Si es cierto, se desactivan los objetos distintos a esa opción.
    		suma.setEnabled(false);
    		ministx.setEnabled(false);
    		minisbt.setEnabled(false);
    		prod.setEnabled(false);
    		maxistx.setEnabled(false);
    		maxisbt.setEnabled(false);
    		term.setEnabled(true);
    		termtx.setEnabled(true);
    		termbt.setEnabled(true);
    		filebt.setEnabled(false);
    		resetbt.setEnabled(true);
    	}
    	if(e.getSource().equals(termbt)){ // Condición que valida si el botón "termbt" fue presionado, en cuyo caso se manda llamar al método llenarTerm.
    		llenarTerm();    		
    	}
    	if(e.getSource().equals(filerb)){ // Condición que valida si el botón de tabla desde archivo fue presionado. Si es cierto, se desactivan los objetos distintos a esa opción.
    		suma.setEnabled(false);
    		ministx.setEnabled(false);
    		minisbt.setEnabled(false);
    		prod.setEnabled(false);
    		maxistx.setEnabled(false);
    		maxisbt.setEnabled(false);
    		term.setEnabled(false);
    		termtx.setEnabled(false);
    		termbt.setEnabled(false);
    		filebt.setEnabled(true);
    		resetbt.setEnabled(true);
    	}
    	if(e.getSource().equals(filebt)){ // Condición que valida si el botón "filebt" fue presionado, en cuyo caso se despliega una ventana de apertura de archivo y se manda llamar al método llenarArchivo.
    		selector.setDialogTitle("Cargar tabla de verdad");
			selector.setApproveButtonText("Cargar");
			selector.setApproveButtonToolTipText("Carga el archivo que contiene la tabla de verdad");
			File archivo = new File(".txt");
			selector.setSelectedFile(archivo);
			respuesta = selector.showOpenDialog(this);
			if (!(respuesta == selector.CANCEL_OPTION)) {
				filetx.setText("" + selector.getSelectedFile());
				try{
					llenarArchivo(filetx.getText());
				}
				catch (Exception ex){
				}
			}
			filebt.setEnabled(true); 		
    	}
    	if(e.getSource().equals(minimizarbt)){ // Condición que valida si el botón "minimizarbt" fue presionado, en cuyo caso se manda llamar al método minimizar.
    		minimizar();
    	}
    	if(e.getSource().equals(copiarbt)){ // Condición que valida si el botón "copiarbt" fue presionado, en cuyo caso se manda llamar al método copiar.
    		copiar();    		
    	}
    	if(e.getSource().equals(procedimsalidabt)){ // Condición que valida si el botón "procedimsalidabt" fue presionado, en cuyo caso se manda llamar al método guardarProcedimientos.
    		selector.setDialogTitle("Guardar procedimientos realizados");
    		File archivo = new File("procedimientos.txt");
			selector.setSelectedFile(archivo);
    		respuesta = selector.showSaveDialog(this);
    		if (!(respuesta == selector.CANCEL_OPTION)) {
				procedimsalidatx.setText("" + selector.getSelectedFile());
				try{
					guardarProcedimientos(procedimsalidatx.getText());
				}
			 	catch (Exception ex){
				}
			}
    	}
    	if(e.getSource().equals(tablasalidabt)){ // Condición que valida si el botón "tablasalidabt" fue presionado, en cuyo caso se despliega una ventana de apertura de archivo y se manda llamar al método guardarTabla.
    		selector.setDialogTitle("Guardar tabla de verdad");
    		File archivo = new File("tabla.txt");
			selector.setSelectedFile(archivo);
    		respuesta = selector.showSaveDialog(this);
    		if (!(respuesta == selector.CANCEL_OPTION)) {
				tablasalidatx.setText("" + selector.getSelectedFile());
				try{
					guardarTabla(tablasalidatx.getText());
				}
			 	catch (Exception ex){
				}
			}
    	}
    	if(e.getSource().equals(resetbt)){ // Condición que valida si el botón "resetbt" fue presionado, en cuyo caso se manda llamar al método reset.
    		reset();
    	}
    }

}