package es.fra.sm.model;

import java.io.Serializable;

public abstract class State implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6981747368193209882L;
	private final String		label;
	private final Integer		position;

	// TODO El estado como una lista de terminos puede se calculado. ¿Es mejor
	// almacenarlo?
	// private TermValue[] stateAsListOfTerms;

	public State(String label, Integer position) {
		this.label = label;
		this.position = position;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final State other = (State) obj;
		if (this.label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!this.label.equals(other.label)) {
			return false;
		}
		if (this.position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!this.position.equals(other.position)) {
			return false;
		}
		return true;
	}

	public String getLabel() {
		return this.label;
	}

	public Integer getPosition() {
		return this.position;
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

		final String grayValue = Integer.toBinaryString((this.position >> 1)
				^ this.position);
		final char[] bitValues = grayValue.toCharArray();
		final TermValue[] output = new TermValue[bitValues.length];
		for (int i = 0; i < bitValues.length; i++) {
			final char termValue = bitValues[i];
			output[i] = TermValue.getValue(termValue);
		}
		return output;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.label == null) ? 0 : this.label.hashCode());
		result = (prime * result)
				+ ((this.position == null) ? 0 : this.position.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "State [label=" + this.label + ", position=" + this.position
				+ "]";
	}
}
