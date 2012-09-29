package es.fra.sm.model;

import java.io.Serializable;

public abstract class State implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6981747368193209882L;
	private String				label;
	private Integer				position;

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
	@Override
	public String toString() {
		return "State [label=" + label + ", position=" + position + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
}
