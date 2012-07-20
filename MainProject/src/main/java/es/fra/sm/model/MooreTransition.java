package es.fra.sm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MooreTransition implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8119148351898838072L;
	private List<TermValue>		inputValue;
	private String				nextState;

	public List<TermValue> getInputValue() {
		if (this.inputValue == null) {
			this.inputValue = new ArrayList<>();
		}
		return this.inputValue;
	}

	public void setInputValue(TermValue[] values) {
		this.setInputValue(Arrays.asList(values));

	}

	public void setInputValue(List<TermValue> value) {
		this.inputValue = value;
	}

	public String getNextState() {
		return this.nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.inputValue == null) ? 0 : this.inputValue.hashCode());

		result = (prime * result)
				+ ((this.nextState == null) ? 0 : this.nextState.hashCode());
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
		final MooreTransition other = (MooreTransition) obj;
		if (this.inputValue == null) {
			if (other.inputValue != null) {
				return false;
			}
		} else if (!this.inputValue.equals(other.inputValue)) {
			return false;
		}
		if (this.nextState == null) {
			if (other.nextState != null) {
				return false;
			}
		} else if (!this.nextState.equals(other.nextState)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MooreTransition [inputValue=" + this.inputValue
				+ ", nextState=" + this.nextState + "]";
	}

}
