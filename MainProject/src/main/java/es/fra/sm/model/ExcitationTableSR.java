package es.fra.sm.model;

public class ExcitationTableSR extends ExcitationTable {

	@Override
	TermValue[] excitationFuntion(TermValue currentState, TermValue nextState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String functionLabel(int i) {

		return (i % 2) == 0 ? "R" : "S";
	}

	@Override
	int functionsByFlipFlop() {
		// TODO Auto-generated method stub
		return 2;
	}

}
