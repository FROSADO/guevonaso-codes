package es.fra.sm.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MooreState implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4434578611438117151L;
	private String					name;
	private TermValue[]				codeValue;

	private MooreOutput				output;

	private Set<MooreTransition>	transitions;

	public MooreState(String stateName) {
		this.name = stateName;

	}

	public Set<MooreTransition> getTransitions() {
		if (this.transitions == null) {
			this.transitions = new HashSet<>();
		}
		return this.transitions;
	}

	public String getName() {
		return this.name;
	}

	public MooreOutput getOutput() {
		return this.output;
	}

	public boolean addTransition(MooreTransition input) {
		return this.getTransitions().add(input);
	}

	public void setTransitions(Set<MooreTransition> inputs) {
		this.transitions = inputs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOutput(MooreOutput output) {
		this.output = output;
	}

	public TermValue[] getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(TermValue[] codeValue) {
		this.codeValue = codeValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + Arrays.hashCode(this.codeValue);
		result = (prime * result)
				+ ((this.name == null) ? 0 : this.name.hashCode());
		result = (prime * result)
				+ ((this.output == null) ? 0 : this.output.hashCode());
		result = (prime * result)
				+ ((this.transitions == null) ? 0 : this.transitions.hashCode());
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
		final MooreState other = (MooreState) obj;
		if (!Arrays.equals(this.codeValue, other.codeValue)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.output == null) {
			if (other.output != null) {
				return false;
			}
		} else if (!this.output.equals(other.output)) {
			return false;
		}
		if (this.transitions == null) {
			if (other.transitions != null) {
				return false;
			}
		} else if (!this.transitions.equals(other.transitions)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "MooreState [name=" + this.name + ", codeValue="
				+ Arrays.toString(this.codeValue) + ", output=" + this.output
				+ ", transitions=" + this.transitions + "]";
	}

}
