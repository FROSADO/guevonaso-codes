package es.fra.sm.model;

public class StateMoore extends State {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= -607910879597545678L;

	private OutputFromStateMachine	output;

	public StateMoore(String label, Integer position,
			OutputFromStateMachine output) {
		super(label, position);
		this.output = output;
	}

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
		StateMoore other = (StateMoore) obj;
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StateMoore [output=" + output + ", toString()="
				+ super.toString() + "]";
	}

}
