package es.fra.sm.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class InputToStateMachine implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6969497288032209984L;
	private List<TermValue>		values;
	private String				label;

	public InputToStateMachine(List<TermValue> values) {
		this.values = values;
	}

	public InputToStateMachine(List<TermValue> values, String label) {
		this(values);
		this.label = label;
	}

	public InputToStateMachine(TermValue... values) {
		this(Arrays.asList(values));
	}

	public InputToStateMachine( String label,TermValue... values) {
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
		final InputToStateMachine other = (InputToStateMachine) obj;
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
		return "InputToStateMachine [values=" + this.values + ", label="
				+ this.label + "]";
	}

}
