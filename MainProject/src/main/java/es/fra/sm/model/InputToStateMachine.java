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

	public InputToStateMachine(List<TermValue> values, String label) {
		this(values);
		this.label = label;
	}

	public InputToStateMachine(List<TermValue> values) {
		this.values = values;
	}

	public InputToStateMachine(TermValue[] values, String label) {
		this(Arrays.asList(values), label);
	}

	public InputToStateMachine(TermValue[] values) {
		this(Arrays.asList(values));
	}

	public List<TermValue> getValues() {
		return values;
	}

	public void setValues(List<TermValue> values) {
		this.values = values;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "InputToStateMachine [values=" + values + ", label=" + label
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		InputToStateMachine other = (InputToStateMachine) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

}
