package es.fra.sm.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import es.fra.sm.qm.Formula;
import es.fra.sm.service.StateCoder;

public class TableMoore implements Serializable {

	/**
	 * 
	 */
	private static final long				serialVersionUID	= -3606005936775088837L;
//	private final Set<TableRow>				rows				= new TreeSet<>();
//	private final Map<String, MooreState>	states				= new TreeMap<String, MooreState>();
//
//	public Collection<TableRow> getRows() {
//		return this.rows;
//	}
//
//	public void addRow(TableRow row) {
//		// Lets use add to overwrite rows
//		this.rows.add(row);
//		// Add state
//		MooreState state = this.states.get(row.getStartState());
//		if (state == null) {
//			state = new MooreState(row.getStartState());
//			this.states.put(row.getStartState(), state);
//		}
//		final MooreTransition transition = new MooreTransition();
//		transition.setInputValue(row.getInputValue());
//		transition.setNextState(row.getFinalState());
//		state.addTransition(transition);
//		final OutputFromStateMachine output = new OutputFromStateMachine(
//				row.getOutputValue());
//		state.setOutput(output);
//
//	}
//
//	public MooreState getState(String name) {
//		return this.getStates().get(name);
//	}
//
//	public Map<String, MooreState> getStates() {
//		// TODO optimize code generation. Only one time if already calculated.
//		// TODO check if table is empty
//		final TermValue[][] codedStated;
//
//		final Set<String> keys = this.states.keySet();
//
//		codedStated = StateCoder.codeStates(keys);
//		int i = 0;
//		for (final String string : keys) {
//			this.states.get(string).setCodeValue(codedStated[i++]);
//		}
//		return this.states;
//	}
//
//	public Formula[] generateExcitationFormulas(ExcitationTable excitation) {
//		// Lets calculate excitation tables
//		return excitation.getFormulas(this.getStates());
//
//	}
}
