package es.fra.sm.model;

import java.io.Serializable;

public class Transition<STATE extends State> implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -9095772383675466983L;

	private STATE				start;
	private STATE				end;
	private InputToStateMachine	input;

	public Transition(final STATE start, final STATE end,
			final InputToStateMachine input) {
		super();
		this.start = start;
		this.end = end;
		this.input = input;
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
		final Transition<?> other = (Transition<?>) obj;
		if (this.end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!this.end.equals(other.end)) {
			return false;
		}
		if (this.input == null) {
			if (other.input != null) {
				return false;
			}
		} else if (!this.input.equals(other.input)) {
			return false;
		}
		if (this.start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!this.start.equals(other.start)) {
			return false;
		}
		return true;
	}

	public STATE getEnd() {
		return this.end;
	}

	public InputToStateMachine getInput() {
		return this.input;
	}

	public STATE getStart() {
		return this.start;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.end == null) ? 0 : this.end.hashCode());
		result = (prime * result)
				+ ((this.input == null) ? 0 : this.input.hashCode());
		result = (prime * result)
				+ ((this.start == null) ? 0 : this.start.hashCode());
		return result;
	}

	public void setEnd(final STATE end) {
		this.end = end;
	}

	public void setInput(final InputToStateMachine input) {
		this.input = input;
	}

	public void setStart(final STATE start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "Transition [start=" + this.start + ", end=" + this.end
				+ ", input=" + this.input + "]";
	}
}
