package es.fra.sm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransitionMoore extends Transition<StateMoore> implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8119148351898838072L;

	public TransitionMoore(StateMoore start, StateMoore end,
			InputToStateMachine input) {
		super(start, end, input);

	}

	
	

}
