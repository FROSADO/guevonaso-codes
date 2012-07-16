package es.fra.sm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.fra.sm.qm.Formula;
import es.fra.sm.service.StateCoder;

public class StateTable implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3606005936775088837L;
	private final Set<TableRow>	rows				= new HashSet<>();

	public Collection<TableRow> getRows() {
		return this.rows;
	}

	public void addRow(TableRow row) {
		// Lets use add to overwrite rows
		this.rows.add(row);
	}

	public Formula[] generateFormulas(ExcitationTable table) {
		// TODO check if table is empty
		final TermValue[][] codedStated;
		final HashMap<String, TermValue[]> stateCodified = new HashMap<>();

		final List<String> states = new ArrayList<String>();
		for (final TableRow row : this.rows) {
			states.add(row.getStartState().toUpperCase());
		}

		codedStated = StateCoder.codeStates(states);
		int i = 0;
		for (final String string : states) {
			if (!stateCodified.containsKey(string)) {
				stateCodified.put(string, codedStated[i++]);
			}

		}

		// We will need a minimum 'Log2(N)' formulas, more for RS and JK
		final Formula[] result = table.getFormulas(this.rows, stateCodified);

		return result;

	}
}
