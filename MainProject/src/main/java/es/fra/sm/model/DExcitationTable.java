package es.fra.sm.model;

import static es.fra.sm.model.TermValue.One;
import static es.fra.sm.model.TermValue.Zero;
public class DExcitationTable extends ExcitationTable {


	@Override
	int functionsByFlipFlop() {
		// Only one function
		return 1;
	}
	// Qn | Qn+1 | D
	// 0  |  0   | 0 
	// 0  |  1   | 1 
	// 1  |  0   | 0 
	// 1  |  1   | 1 
	// 

	@Override
	TermValue[] excitationFuntion(TermValue currentState, TermValue nextState) {
		switch (currentState) {
		case Zero:
			if (nextState == Zero) {
				return new TermValue[] {Zero};
			} else {
				return new TermValue[] {One};
			}
		case One:
			if (nextState == Zero) {
				return new TermValue[] {Zero};
			} else {
				return new TermValue[] {One};
			}
		default:
			// TODO ¿ERROR?
			return new TermValue[] {TermValue.DontCare};
		}
	}


}
