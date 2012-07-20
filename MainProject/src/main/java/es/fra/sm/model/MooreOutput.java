package es.fra.sm.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MooreOutput implements Serializable {


	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3416586942145147067L;

	public MooreOutput(TermValue[] outputValues) {
		this(Arrays.asList(outputValues));
	}

	public MooreOutput(List<TermValue> outputValues) {
		super();
		this.outputValues = outputValues;
	}

	public MooreOutput() {
		super();
		// TODO Auto-generated constructor stub
	}

	private List<TermValue>	outputValues;

	public List<TermValue> getOutputValues() {
		return this.outputValues;
	}

	public void setOutputValues(List<TermValue> outputValues) {
		this.outputValues = outputValues;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.outputValues == null) ? 0 : this.outputValues
						.hashCode());
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
		final MooreOutput other = (MooreOutput) obj;
		if (this.outputValues == null) {
			if (other.outputValues != null) {
				return false;
			}
		} else if (!this.outputValues.equals(other.outputValues)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "MooreOutput [outputValues=" + this.outputValues + "]";
	}

}
