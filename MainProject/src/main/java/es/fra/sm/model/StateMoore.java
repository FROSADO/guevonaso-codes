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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final StateMoore other = (StateMoore) obj;
		if (this.output == null) {
			if (other.output != null) {
				return false;
			}
		} else if (!this.output.equals(other.output)) {
			return false;
		}
		return true;
	}

	public OutputFromStateMachine getOutput() {
		return this.output;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result)
				+ ((this.output == null) ? 0 : this.output.hashCode());
		return result;
	}

	public void setOutput(OutputFromStateMachine output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "StateMoore [output=" + this.output + ", toString()="
				+ super.toString() + "]";
	}

}
