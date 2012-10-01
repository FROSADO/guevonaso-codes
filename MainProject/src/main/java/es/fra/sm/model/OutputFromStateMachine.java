package es.fra.sm.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class OutputFromStateMachine implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4103774244428070841L;
	private List<TermValue>		values;
	private String				label;

	public OutputFromStateMachine(List<TermValue> values) {
		super();
		this.values = values;
	}

	public OutputFromStateMachine(List<TermValue> values, String label) {
		this(values);
		this.label = label;
	}

	public OutputFromStateMachine(TermValue... values) {
		this(Arrays.asList(values));
	}

	public OutputFromStateMachine(String label, TermValue... values) {
		this(Arrays.asList(values), label);
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
		final OutputFromStateMachine other = (OutputFromStateMachine) obj;
		if (this.label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!this.label.equals(other.label)) {
			return false;
		}
		if (this.values == null) {
			if (other.values != null) {
				return false;
			}
		} else if (!this.values.equals(other.values)) {
			return false;
		}
		return true;
	}

	public String getLabel() {
		return this.label;
	}

	public List<TermValue> getValues() {
		return this.values;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.label == null) ? 0 : this.label.hashCode());
		result = (prime * result)
				+ ((this.values == null) ? 0 : this.values.hashCode());
		return result;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setValues(List<TermValue> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "OutputFromStateMachine [values=" + this.values + ", label="
				+ this.label + "]";
	}

}
