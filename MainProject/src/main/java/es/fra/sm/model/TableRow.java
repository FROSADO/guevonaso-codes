package es.fra.sm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableRow implements Serializable, Comparable<TableRow> {
	/**
	 * 
	 */
	private static final long		serialVersionUID	= -753486461051131800L;

	private String					startState;

	private String					finalState;

	private ArrayList<TermValue>	inputValue;

	private ArrayList<TermValue>	outputValue;

	public TableRow() {

	}

	public TableRow(String startState, String finalState) {
		this.startState = startState;
		this.finalState = finalState;
	}

	public TableRow(String startState, String finalState, TermValue[] inputs,
			TermValue[] outputs) {
		this(startState, finalState);
		for (final TermValue i : inputs) {
			this.getInputValue().add(i);
		}
		for (final TermValue o : outputs) {
			this.getOutputValue().add(o);
		}

	}

	public void addInput(TermValue input) {
		this.getInputValue().add(input);
	}

	public void addOutput(TermValue output) {
		this.getOutputValue().add(output);
	}

	@Override
	public int compareTo(TableRow other) {
		if (this == other) {
			return 0;
		}
		int compareTo = this.fieldCompare(this.startState, other.startState);
		if (compareTo != 0) {
			return compareTo;
		}
		compareTo = this.fieldCompare(this.finalState, other.finalState);
		if (compareTo != 0) {
			return compareTo;
		}
		// First Let's compare only the size

		compareTo = this.getInputValue().size() - other.getInputValue().size();
		if (compareTo != 0) {
			return compareTo;
		}
		for (int i = 0; i < this.getInputValue().size(); i++) {
			final TermValue thisValue = this.getInputValue().get(i);
			final TermValue otherValue = other.getInputValue().get(i);
			compareTo = thisValue.compareTo(otherValue);
			if (compareTo != 0) {
				return compareTo;
			}

		}

		compareTo = this.getOutputValue().size()
				- other.getOutputValue().size();
		if (compareTo != 0) {
			return compareTo;
		}
		for (int i = 0; i < this.getOutputValue().size(); i++) {
			final TermValue thisValue = this.getOutputValue().get(i);
			final TermValue otherValue = other.getOutputValue().get(i);
			compareTo = thisValue.compareTo(otherValue);
			if (compareTo != 0) {
				return compareTo;
			}

		}
		return 0;

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
		final TableRow other = (TableRow) obj;
		if (this.finalState == null) {
			if (other.finalState != null) {
				return false;
			}
		} else if (!this.finalState.equals(other.finalState)) {
			return false;
		}
		if (this.inputValue == null) {
			if (other.inputValue != null) {
				return false;
			}
		} else if (!this.inputValue.equals(other.inputValue)) {
			return false;
		}
		if (this.outputValue == null) {
			if (other.outputValue != null) {
				return false;
			}
		} else if (!this.outputValue.equals(other.outputValue)) {
			return false;
		}
		if (this.startState == null) {
			if (other.startState != null) {
				return false;
			}
		} else if (!this.startState.equals(other.startState)) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private <T> int fieldCompare(Comparable<T> one, Comparable<T> other) {
		if (one != null) {
			if (other != null) {
				return one.compareTo((T) other);
			} else {
				return +1;
			}
		} else {
			if (other == null) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	public String getFinalState() {
		return this.finalState;
	}

	public List<TermValue> getInputValue() {
		if (this.inputValue == null) {
			this.inputValue = new ArrayList<TermValue>();
		}
		return this.inputValue;
	}

	public List<TermValue> getOutputValue() {
		if (this.outputValue == null) {
			this.outputValue = new ArrayList<TermValue>();
		}
		return this.outputValue;
	}

	// ----------------------------------------------------------------------

	public String getStartState() {
		return this.startState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.finalState == null) ? 0 : this.finalState.hashCode());
		result = (prime * result)
				+ ((this.inputValue == null) ? 0 : this.inputValue.hashCode());
		result = (prime * result)
				+ ((this.outputValue == null) ? 0 : this.outputValue.hashCode());
		result = (prime * result)
				+ ((this.startState == null) ? 0 : this.startState.hashCode());
		return result;
	}

	public void setFinalState(String finalState) {
		this.finalState = finalState;
	}

	public void setStartState(String startState) {
		this.startState = startState;
	}

}
