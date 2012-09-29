package es.fra.sm.model;

import java.io.Serializable;

public abstract class State implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6981747368193209882L;
	private String	label;
	private Integer	position;

	// TODO El estado como una lista de terminos puede se calculado. ¿Es mejor
	// almacenarlo?
	// private TermValue[] stateAsListOfTerms;

	public State(String label, Integer position) {
		this.label = label;
		this.position = position;
	}

	public String getLabel() {
		return label;
	}

	public Integer getPosition() {
		return position;
	}

	/**
	 * Basándose en el valor de "posicion". Se calcula el valor del estado como
	 * una lista de terminos por defecto en codificacion GRAY
	 * 
	 * @return
	 */
	// TODO Siempre es GRAY.
	public TermValue[] getStateAsListOfTerms() {
		// TODO Podriamos almacenar esto para ahorrar tiempo.

		String grayValue = Integer.toBinaryString((position >> 1) ^ position);
		char[] bitValues = grayValue.toCharArray();
		TermValue[] output = new TermValue[bitValues.length];
		for (int i = 0; i < bitValues.length; i++) {
			char termValue = bitValues[i];
			output[i] = TermValue.getValue(termValue);
		}
		return output;

	}

}
