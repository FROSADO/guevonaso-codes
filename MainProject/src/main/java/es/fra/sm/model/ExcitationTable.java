package es.fra.sm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import es.fra.sm.qm.Formula;
import es.fra.sm.qm.Term;

public abstract class ExcitationTable {

	abstract TermValue[] excitationFuntion(TermValue currentState,
			TermValue nextState);

//	public Formula[] getFormulas(Map<String, MooreState> states) {
//
//		// We need log2 (N) flip-flops
//		final int flipFlops = (int) Math.ceil((Math.log(states.size()) / Math
//				.log(2)));
//		// And will be (flipflops * multiplier) functions
//		// ussually flipflop * 1 or fp * 2
//
//		final int multiplier = this.functionsByFlipFlop();
//		final int num_exc_formulas = flipFlops * multiplier;
//		final Formula[] formulas = new Formula[num_exc_formulas];
//		for (int i = 0; i < num_exc_formulas; i++) {
//			formulas[i] = new Formula(this.functionLabel(i));
//		}
//		final Set<Entry<String, MooreState>> mooreStates = states.entrySet();

//		for (final Entry<String, MooreState> entry : mooreStates) {
//			final MooreState value = entry.getValue();
//			final TermValue[] code = value.getCodeValue();
//			final List<MooreTransition> transitions = value.getTransitions();
//			for (final MooreTransition mooreTransition : transitions) {
//				final String next = mooreTransition.getNextState();
//				final TermValue[] nextCode = states.get(next).getCodeValue();
//				final List<TermValue> inputs = mooreTransition.getInputValue();
//				final Term[] terms = this
//						.calculateTerms(code, nextCode, inputs);
//				// La lista de terminos debe de coincidir con la lista de
//				// formulas
//				for (int i = 0; i < terms.length; i++) {
//					final Term term = terms[i];
//					formulas[i].addTerm(term);
//
//				}
//
//			}
//
//		}

		// ----
//		return formulas;
//	}

	protected abstract String functionLabel(int i);

	/**
	 * Esta funcion devolverá la lista de terminos en función del número de
	 * funciones necesarias. Por ejemplo: Q JK JK 00 00 00 01 00 00 ...etc --> 4
	 * Funciones de exitacion J0K0 J1K1
	 * 
	 * 
	 * @param code
	 * @param nextCode
	 * @param inputs
	 * @return
	 */
	private Term[] calculateTerms(TermValue[] code, TermValue[] nextCode,
			List<TermValue> inputs) {

		final List<Term> result = new ArrayList<>();
		for (int i = 0; i < code.length; i++) {
			final TermValue currentState = code[i];
			final TermValue nextState = nextCode[i];
			final TermValue[] func = this.excitationFuntion(currentState,
					nextState);
			for (final TermValue excFunc : func) {
				if (excFunc == TermValue.One) {
					// It's Term. Let's generate a term with all code and inputs
					final Term term = new Term();
					term.addAll(code);
					term.addAll(inputs);
					result.add(term);
				} else {
					result.add(null);
				}
			}

		}
		return result.toArray(new Term[result.size()]);
	}

	abstract int functionsByFlipFlop();

}
