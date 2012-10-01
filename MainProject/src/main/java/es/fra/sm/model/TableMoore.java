package es.fra.sm.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import es.fra.sm.StateMachineException;

public class TableMoore implements Serializable {

	/**
	 * 
	 */
	private static final long				serialVersionUID	= -3606005936775088837L;
	private LinkedHashSet<TransitionMoore>	transitions			= new LinkedHashSet<TransitionMoore>();
	private HashMap<String, StateMoore>		states				= new HashMap<>();

	public Collection<TransitionMoore> getTransitions() {
		return transitions;
	}

	public void addTransition(TransitionMoore transition) throws StateMachineException {
		// Validate Transition
		final StateMoore start = transition.getStart();
		validateState(start);
		getStates().put(start.getLabel(), start);
		//--- 
		final StateMoore end = transition.getEnd();
		validateState(end);
		getStates().put(end.getLabel(), end);
		boolean newElement = getTransitions().add(transition);
		// TODO vamos a lanzar una excepcion para evitar añadir dos veces la
		// misma transicion
		if (!newElement) {
			throw new StateMachineException("Aready register "
					+ start + " ->" + end
					+ " Input [" + transition.getInput() + "] ");
		}
	}

	private void validateState(StateMoore start) {
		StateMoore current = getStates().get(start.getLabel());
		if (current != null) {
			if (!current.equals(start)) {
				throw new StateMachineException("Aready register state "
						+ current.getLabel() + " with output "+current.getOutput().getLabel()+"["
						+ current.getOutput().getValues()
						+ "]. Not avoided a state with different output "+start.getLabel()+ "["+start.getOutput()+"]");
			}
		}

	}

	public Map<String, StateMoore> getStates() {
		return states;
	}

	@Override
	public String toString() {
		return "TableMoore [transitions=" + transitions + "]";
	}

}
