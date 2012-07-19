package es.fra.sm.model;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import es.fra.sm.qm.Formula;
import es.fra.sm.qm.Term;

public abstract class ExcitationTable {

	abstract TermValue[] excitationFuntion(TermValue currentState, TermValue nextState);

	public Formula[] getFormulas(Map<String, MooreState> states) {


		// We need log2 (N) flip-flops
		final int flipFlops = (int) Math.ceil((Math.log(states.size()) / Math.log(2)));
		// And will be (flipflops * multiplier) functions
		// ussually flipflop * 1 or fp * 2

		final int multiplier = this.functionsByFlipFlop();
		final int num_exc_formulas = flipFlops * multiplier;
		final Formula[] formulas  = new Formula[num_exc_formulas];
		for (int i = 0; i < num_exc_formulas; i++) {
			formulas[i] = new Formula();
		}
		final Set<Entry<String, MooreState>> mooreStates = states.entrySet();

		for (final Entry<String, MooreState> entry : mooreStates) {
			final MooreState value = entry.getValue();
			final TermValue[] code = value.getCodeValue();
			final Set<MooreTransition> transitions = value.getTransitions();
			for (final MooreTransition mooreTransition : transitions) {
				final String next = mooreTransition.getNextState();
				final TermValue[] nextCode = states.get(next).getCodeValue();
				final List<TermValue> inputs = mooreTransition.getInputValue();
				final Term[] terms = this.calculateTerms (code,nextCode,inputs);
				// La lista de terminos debe de coincidir con la lista de formulas
				for (int i = 0; i < terms.length; i++) {
					final Term term = terms[i];
					formulas[i].addTerm(term);

				}


			}

		}


		//
		// for (final TableRow row : rows) {
		// final String startState = row.getStartState().toUpperCase();
		// final String finalState = row.getFinalState().toUpperCase();
		// final TermValue[] codeStart = states.get(startState);
		// final TermValue[] codeFinal = states.get(finalState);
		// final List<TermValue> inputs = row.getInputValue();
		// final List<TermValue> outputs = row.getOutputValue();
		// for (int i = 0; i < codeFinal.length; i++) {
		// final TermValue termStart = codeStart[i];
		// final TermValue termFinal = codeFinal[i];
		// final List<Term> terms = this.calculateTerm(termStart,
		// termFinal, inputs, outputs);
		// this.addToFormula(i, terms);
		//
		// }
		// }

		// TODO Auto-generated method stub
		return null;
	}

	private Term[] calculateTerms(TermValue[] code, TermValue[] nextCode,
			List<TermValue> inputs) {

		// TODO Auto-generated method stub
		for (int i = 0; i < code.length; i++) {
			final TermValue currentState = code[i];
			final TermValue nextState = nextCode[i];
			final TermValue[] func = this.excitationFuntion(currentState, nextState);



		}
		return null;
	}


	abstract int functionsByFlipFlop();

}
