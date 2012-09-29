package es.fra.sm.model;

public class TransitionMealy extends Transition<StateMealy> {

	private OutputFromStateMachine	output;

	public TransitionMealy(StateMealy start, StateMealy end,
			InputToStateMachine input,OutputFromStateMachine output) {
		super(start, end, input);
		this.output = output;
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7938062405215406187L;

	public OutputFromStateMachine getOutput() {
		return output;
	}

	public void setOutput(OutputFromStateMachine output) {
		this.output = output;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((output == null) ? 0 : output.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransitionMealy other = (TransitionMealy) obj;
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MealyTransition [output=" + output + ", toString()="
				+ super.toString() + "]";
	}

}
