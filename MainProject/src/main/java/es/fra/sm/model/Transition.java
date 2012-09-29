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

	public STATE getStart() {
		return start;
	}

	public void setStart(final STATE start) {
		this.start = start;
	}

	public STATE getEnd() {
		return end;
	}

	public void setEnd(final STATE end) {
		this.end = end;
	}

	public InputToStateMachine getInput() {
		return input;
	}

	public void setInput(final InputToStateMachine input) {
		this.input = input;
	}

	@Override
	public String toString() {
		return "Transition [start=" + start + ", end=" + end + ", input="
				+ input + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		Transition<?> other = (Transition<?>) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
}
