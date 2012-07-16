package es.fra.sm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableRow implements Serializable {
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

	public String getFinalState() {
		return this.finalState;
	}

	public void setFinalState(String finalState) {
		this.finalState = finalState;
	}

	public void setStartState(String startState) {
		this.startState = startState;
	}

	public String getStartState() {
		return this.startState;
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

	public void addInput(TermValue input) {
		this.getInputValue().add(input);
	}

	public void addOutput(TermValue output) {
		this.getOutputValue().add(output);
	}

	// ----------------------------------------------------------------------

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

}
