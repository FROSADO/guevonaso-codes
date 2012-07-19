package es.fra.sm.qm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.fra.sm.model.TermValue;

public class Formula implements Cloneable {
	private List<Term>	termList;
	private List<Term>	originalTermList;

	public Formula() {

	}

	public Formula(final List<Term> termList) {
		this.termList = termList;
	}

	public Formula(Term[] terms) {
		this(Arrays.asList(terms));
	}

	@Override
	protected Formula clone() throws CloneNotSupportedException {
		final List<Term> copy = new ArrayList<Term>(this.getTermList());
		return new Formula(copy);
	}

	private boolean[][] createImpliesTable() {
		final int numPrimeImplicants = this.getTermList().size();
		final int numOriginalTerms = this.originalTermList.size();
		final boolean[][] table = new boolean[numPrimeImplicants][numOriginalTerms];
		for (int impl = 0; impl < numPrimeImplicants; impl++) {
			for (int term = 0; term < numOriginalTerms; term++) {
				table[impl][term] = this.getTermList().get(impl)
						.implies(this.originalTermList.get(term));
			}
		}
		return table;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Term>[][] createTermTable() {
		final int numVars = this.getTermList().get(0).getNumVars();
		final ArrayList<Term>[][] table = new ArrayList[numVars + 1][numVars + 1];
		for (int dontKnows = 0; dontKnows <= numVars; dontKnows++) {
			for (int ones = 0; ones <= numVars; ones++) {
				table[dontKnows][ones] = new ArrayList<Term>();
			}
		}
		for (int i = 0; i < this.getTermList().size(); i++) {
			final int dontCares = this.getTermList().get(i)
					.countValues(TermValue.DontCare);
			final int ones = this.getTermList().get(i)
					.countValues(TermValue.One);
			table[dontCares][ones].add(this.getTermList().get(i));
		}
		return table;
	}

	private int extractEssentialImplicant(boolean[][] table) {
		for (int term = 0; term < table[0].length; term++) {
			int lastImplFound = -1;
			for (int impl = 0; impl < table.length; impl++) {
				if (table[impl][term]) {
					if (lastImplFound == -1) {
						lastImplFound = impl;
					} else {
						// This term has multiple implications
						lastImplFound = -1;
						break;
					}
				}
			}
			if (lastImplFound != -1) {
				this.extractImplicant(table, lastImplFound);
				return lastImplFound;
			}
		}
		return -1;
	}

	private void extractImplicant(boolean[][] table, int impl) {
		for (int term = 0; term < table[0].length; term++) {
			if (table[impl][term]) {
				for (int impl2 = 0; impl2 < table.length; impl2++) {
					table[impl2][term] = false;
				}
			}
		}
	}

	private int extractLargestImplicant(boolean[][] table) {
		int maxNumTerms = 0;
		int maxNumTermsImpl = -1;
		for (int impl = 0; impl < table.length; impl++) {
			int numTerms = 0;
			for (int term = 0; term < table[0].length; term++) {
				if (table[impl][term]) {
					numTerms++;
				}
			}
			if (numTerms > maxNumTerms) {
				maxNumTerms = numTerms;
				maxNumTermsImpl = impl;
			}
		}
		if (maxNumTermsImpl != -1) {
			this.extractImplicant(table, maxNumTermsImpl);
			return maxNumTermsImpl;
		}
		return -1;
	}

	// <<generate new terms with combine() while updating prime implicant
	// list>>=
	private void generate() {
		final int numVars = this.getTermList().get(0).getNumVars();
		final ArrayList<Term>[][] table = this.createTermTable();
		for (int dontKnows = 0; dontKnows <= (numVars - 1); dontKnows++) {
			for (int ones = 0; ones <= (numVars - 1); ones++) {
				final ArrayList<Term> left = table[dontKnows][ones];
				final ArrayList<Term> right = table[dontKnows][ones + 1];
				final ArrayList<Term> out = table[dontKnows + 1][ones];
				for (int leftIdx = 0; leftIdx < left.size(); leftIdx++) {
					for (int rightIdx = 0; rightIdx < right.size(); rightIdx++) {
						final Term combined = left.get(leftIdx).combine(
								right.get(rightIdx));
						if (combined != null) {
							if (!out.contains(combined)) {
								out.add(combined);
							}
							this.updatePrimeImplicantList(left, right, leftIdx,
									rightIdx, combined);
						}
					}
				}
			}
		}
	}

	public List<Term> getTermList() {
		if (this.termList == null) {
			this.termList = new ArrayList<>();
		}
		return this.termList;
	}

	public void addTerm(Term term) {
		if (term != null) {
			this.getTermList().add(term);
		}
	}

	public void reducePrimeImplicantsToSubset() {
		// create implies table
		final boolean[][] table = this.createImpliesTable();
		final ArrayList<Term> newTermList = new ArrayList<Term>();
		boolean done = false;
		int impl;
		while (!done) {
			impl = this.extractEssentialImplicant(table);
			if (impl != -1) {
				newTermList.add(this.getTermList().get(impl));
			} else {
				impl = this.extractLargestImplicant(table);
				if (impl != -1) {
					newTermList.add(this.getTermList().get(impl));
				} else {
					done = true;
				}
			}
		}
		this.termList = newTermList;
		this.originalTermList = null;
	}

	public void reduceToPrimeImplicants() {
		this.originalTermList = new ArrayList<Term>(this.getTermList());
		this.generate();

	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder(this.getTermList()
				.size() + 20);
		result.append(this.getTermList().size());
		result.append(" terms ");
		if (!this.getTermList().isEmpty()) {
			result.append(this.getTermList().get(0).getNumVars());
			result.append(" variables \r\n");
		}

		for (final Term term : this.getTermList()) {
			result.append(term);
			result.append("\r\n");
		}
		return result.toString();
	}

	private void updatePrimeImplicantList(ArrayList<Term> left,
			ArrayList<Term> right, int leftIdx, int rightIdx, Term combined) {
		this.getTermList().remove(left.get(leftIdx));
		this.getTermList().remove(right.get(rightIdx));
		if (!this.getTermList().contains(combined)) {
			this.getTermList().add(combined);
		}

	}
}
