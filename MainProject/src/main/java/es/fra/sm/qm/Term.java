package es.fra.sm.qm;

import java.util.Arrays;

import es.fra.sm.model.TermValue;

public class Term implements Cloneable {

	private final TermValue[]	varVals;

	public Term(int size) {
		this.varVals = new TermValue[size];
	}
	public Term(TermValue ... varVals) {
		this.varVals = varVals;
	}

	public int getNumVars() {
		return this.varVals.length;
	}

	public void setVarOn (int pos,TermValue value) {
		// TODO check array size..?
		this.varVals[pos] = value;
	}



	@Override
	public String toString() {

		final StringBuilder result = new StringBuilder("{");
		for (final TermValue value : this.varVals) {
			result.append(value);
			// result.append(" ");
		}
		return result.append("}").toString();
	}

	/**
	 * We can combine two terms if:
	 * <ul>
	 * <li>
	 * They are identical except for one position,</li>
	 * <li>
	 * * and That one position is 0 in one term and 1 in the other.</li>
	 * </ul>
	 * For example:
	 * 
	 * <pre>
	 * {0, 0, DontCare, 1}, {0, 1, DontCare, 1} -> {0, DontCare, DontCare, 1}
	 * </pre>
	 * 
	 * @param term
	 * @return a new Term combined (doesn't modify the current one)
	 */
	public Term combine(Term term) {
		int diffVarNum = -1; // The position where they differ
		for (int i = 0; i < this.varVals.length; i++) {
			if (this.varVals[i] == term.varVals[i]) {
				continue;
			}
			if (diffVarNum == -1) {
				diffVarNum = i;
			} else {
				// They're different in at least two places
				return null;
			}
		}

		if (diffVarNum == -1) {
			// They're identical
			return null;
		}
		// Let's return a new Term with
		final TermValue[] resultVars = this.varVals.clone();
		resultVars[diffVarNum] = TermValue.DontCare;
		return new Term(resultVars);
	}

	@Override
	protected Term clone() throws CloneNotSupportedException {

		return new Term(this.varVals.clone());
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public int countValues(TermValue value) {

		int result = 0;
		for (final TermValue varVal : this.varVals) {
			if (varVal == value) {
				result++;
			}
		}
		return result;
	}

	/**
	 * To determine if a term A implies another term B, we need only ensure that
	 * all variables set to 0 or 1 in A are set the same in B; for example:
	 * 
	 * <pre>
	 * {DontCare, 1, 0, DontCare} implies {0, 1, 0, DontCare}
	 * </pre>
	 * 
	 * @param term
	 * @return
	 */
	boolean implies(Term term) {
		for (int i = 0; i < this.varVals.length; i++) {
			if ((this.varVals[i] != TermValue.DontCare)
					&& (this.varVals[i] != term.varVals[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + Arrays.hashCode(this.varVals);
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
		final Term other = (Term) obj;
		if (!Arrays.equals(this.varVals, other.varVals)) {
			return false;
		}
		return true;
	}

}
