package es.fra.sm.model;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import es.fra.sm.qm.Formula;
import es.fra.sm.qm.Term;

public abstract class ExcitationTable {

	abstract boolean isMinTerm(TermValue currentState, TermValue nextState);

	public Formula[] getFormulas(Set<TableRow> rows,
			HashMap<String, TermValue[]> stateCodified) {
		// We need log2 (N) flip-flops
		final int flipFlops = (int) Math
				.ceil((Math.log(stateCodified.size()) / Math.log(2)));
		final int multiplier = this.functionsByFlipFlop();

		this.generateFormulas(flipFlops * multiplier);

		for (final TableRow row : rows) {
			final String startState = row.getStartState().toUpperCase();
			final String finalState = row.getFinalState().toUpperCase();
			final TermValue[] codeStart = stateCodified.get(startState);
			final TermValue[] codeFinal = stateCodified.get(finalState);
			final List<TermValue> inputs = row.getInputValue();
			final List<TermValue> outputs = row.getOutputValue();
			for (int i = 0; i < codeFinal.length; i++) {
				final TermValue termStart = codeStart[i];
				final TermValue termFinal = codeFinal[i];
				final List<Term> terms = this.calculateTerm(termStart,
						termFinal, inputs, outputs);
				this.addToFormula(i, terms);

			}
		}

		// TODO Auto-generated method stub
		return null;
	}

	private List<Formula> generateFormulas(int stateNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	private void addToFormula(int i, List<Term> terms) {
		// TODO Auto-generated method stub

	}

	private List<Term> calculateTerm(TermValue termStart, TermValue termFinal,
			List<TermValue> inputs, List<TermValue> outputs) {
		// TODO Auto-generated method stub
		return null;
	}

	abstract int functionsByFlipFlop();

}
