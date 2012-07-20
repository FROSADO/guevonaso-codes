package es.fra.sm.model;

import static es.fra.sm.model.TermValue.One;
import static es.fra.sm.model.TermValue.Zero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import es.fra.sm.qm.Formula;
import es.fra.sm.qm.Term;

public class TestMooreTable {

	@Test
	public void basicTableTest() {

		final MooreTable table = new MooreTable();
		final TableRow[] row = new TableRow[] {//@formatter:off
				new TableRow("A", "A", new TermValue[] { Zero },new TermValue[] { Zero }), //
				new TableRow("A", "B", new TermValue[] { One },	new TermValue[] { Zero }), //
				new TableRow("B", "B", new TermValue[] { Zero },new TermValue[] { One }), //
				new TableRow("B", "A", new TermValue[] { One },	new TermValue[] { One }) //

		};//@formatter:on

		// State | Next | Input | Output | D Excitation
		// A  (0)|  A(0)| 0     |  0     | 0
		// A  (0)|  B(1)| 1     |  0     | 1 --> -AI --> 01 
		// B  (1)|  B(1)| 0     |  1     | 1 --> B-I --> 10
		// B  (1)|  A(0)| 1     |  1     | 0
		// 
		for (final TableRow tableRow : row) {
			table.addRow(tableRow);
		}
		// Let's verify output
		final Map<String,MooreState> states = table.getStates();
		// Expected estates :
		final List<MooreState> expected1 = this.expected1();
		for (final MooreState expected : expected1) {
			Assert.assertEquals(expected, states.get(expected.getName()));
		}

		// LEt's verify excitation tables
		final Formula[] formulas = table.generateExcitationFormulas(new DExcitationTable());
		// First lets verify size . 2 States, D FlipFlop --> 1 Excitation Formula
		// D0 = 1,2  

		//@formatter:off
		final Formula[] expected = new Formula[] {
				new Formula("D0",
						new Term(Zero,One), // 1
						new Term(One,Zero)  // 2
						)
		};
		//@formatter:on
		Assert.assertEquals(1,formulas.length);
		Assert.assertArrayEquals(expected, formulas);
		formulas[0].reduceToPrimeImplicants();
		formulas[0].reducePrimeImplicantsToSubset();

	}

	private List<MooreState> expected1() {
		final MooreState a = new MooreState("A");
		a.setOutput(new MooreOutput(new TermValue[] { Zero }));
		a.setCodeValue(new TermValue[] { Zero });

		// ------------------
		final MooreTransition taa = new MooreTransition();
		taa.setInputValue(new TermValue[] { Zero });
		taa.setNextState("A");
		final MooreTransition tab = new MooreTransition();
		tab.setInputValue(new TermValue[] { One });
		tab.setNextState("B");
		// -------------

		a.addTransition(taa);
		a.addTransition(tab);

		final MooreState b = new MooreState("B");
		b.setOutput(new MooreOutput(new TermValue[] { One }));
		b.setCodeValue(new TermValue[] { One });
		final MooreTransition tbb = new MooreTransition();
		tbb.setInputValue(new TermValue[] { Zero });
		tbb.setNextState("B");
		final MooreTransition tba = new MooreTransition();
		tba.setInputValue(new TermValue[] { One });
		tba.setNextState("A");

		b.addTransition(tbb);
		b.addTransition(tba);

		final ArrayList<MooreState> states = new ArrayList<>();
		states.add(a);
		states.add(b);
		return states;
	}
	/**
	 * This test verify that four states works. 
	 */
	@Test
	public void fourStatesTest() {

		final MooreTable table = new MooreTable();
		final TableRow[] row = new TableRow[] {//@formatter:off
				new TableRow("Q0", "Q0", new TermValue[] { Zero },new TermValue[] { Zero }), //
				new TableRow("Q0", "Q1", new TermValue[] { One },	new TermValue[] { Zero }), //
				new TableRow("Q1", "Q1", new TermValue[] { Zero },new TermValue[] { One }), //
				new TableRow("Q1", "Q2", new TermValue[] { One },	new TermValue[] { One }), //
				new TableRow("Q2", "Q2", new TermValue[] { Zero },new TermValue[] { Zero }), //
				new TableRow("Q2", "Q3", new TermValue[] { One },	new TermValue[] { Zero }), //
				new TableRow("Q3", "Q3", new TermValue[] { Zero },new TermValue[] { One }), //
				new TableRow("Q3", "Q0", new TermValue[] { One },	new TermValue[] { One }) //


		};//@formatter:on

		// State | Next | Input | Output | Excitation
		//	q0		q0		0		0		
		//	q0		q1		1		0
		//	q1		q1		0		1		
		//	q1		q2		1		1		
		//	q2		q2		0		0
		//	q2		q3		1		0
		//	q3		q3		0		1
		//	q3		q0		1		1
		//			




		for (final TableRow tableRow : row) {
			table.addRow(tableRow);
		}
		// Let's verify output
		final Map<String,MooreState> states = table.getStates();
		// Expected estates :
		final List<MooreState> expected4 = this.expected4();
		for (final MooreState expected : expected4) {
			Assert.assertEquals(expected, states.get(expected.getName()));
		}

	}


	private List<MooreState> expected4() {
		final List<MooreState> results = new ArrayList<>();
		MooreState mooreState = new MooreState("Q0");
		MooreTransition t;
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { Zero });
		t.setNextState("Q0");
		mooreState.addTransition(t);
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { One });
		t.setNextState("Q1");
		mooreState.addTransition(t);
		mooreState.setOutput(new MooreOutput(new TermValue[] { Zero }));
		mooreState.setCodeValue(new TermValue[] {Zero,Zero});
		results.add(mooreState);
		// --- Q1
		mooreState = new MooreState("Q1");

		t = new MooreTransition();
		t.setInputValue(new TermValue[] { Zero });
		t.setNextState("Q1");
		mooreState.addTransition(t);
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { One });
		t.setNextState("Q2");
		mooreState.addTransition(t);
		mooreState.setOutput(new MooreOutput(new TermValue[] { One }));
		mooreState.setCodeValue(new TermValue[] {Zero,One});
		results.add(mooreState);
		// --- Q2
		mooreState = new MooreState("Q2");

		t = new MooreTransition();
		t.setInputValue(new TermValue[] { Zero });
		t.setNextState("Q2");
		mooreState.addTransition(t);
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { One });
		t.setNextState("Q3");
		mooreState.addTransition(t);
		mooreState.setOutput(new MooreOutput(new TermValue[] { Zero }));
		mooreState.setCodeValue(new TermValue[] {One,One});
		results.add(mooreState);
		// --- Q3
		mooreState = new MooreState("Q3");

		t = new MooreTransition();
		t.setInputValue(new TermValue[] { Zero });
		t.setNextState("Q3");
		mooreState.addTransition(t);
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { One });
		t.setNextState("Q0");
		mooreState.addTransition(t);
		mooreState.setOutput(new MooreOutput(new TermValue[] { One }));
		mooreState.setCodeValue(new TermValue[] {One,Zero});
		results.add(mooreState);
		return results;
	}
	@Test
	public void fourStatesNotOrderedTest() {

		final MooreTable table = new MooreTable();
		final TableRow[] row = new TableRow[] {//@formatter:off
				new TableRow("A0", "A0", new TermValue[] { Zero },new TermValue[] { Zero }), //
				new TableRow("S 2", "S 2", new TermValue[] { Zero },new TermValue[] { Zero }), //
				new TableRow("State 1", "State 1", new TermValue[] { Zero },new TermValue[] { One }), //
				new TableRow("State 1", "S 2", new TermValue[] { One },	new TermValue[] { One }), //
				new TableRow("State 3", "State 3", new TermValue[] { Zero },new TermValue[] { One }), //
				new TableRow("S 2", "State 3", new TermValue[] { One },	new TermValue[] { Zero }), //
				new TableRow("A0", "State 1", new TermValue[] { One },	new TermValue[] { Zero }), //
				new TableRow("State 3", "A0", new TermValue[] { One },	new TermValue[] { One }) //


		};//@formatter:on
		for (final TableRow tableRow : row) {
			table.addRow(tableRow);
		}
		// Let's verify output
		final Map<String,MooreState> states = table.getStates();
		// Expected estates :
		final List<MooreState> expected4 = this.expected4NotOrder();
		final Iterator<MooreState> itExpected = expected4.iterator();
		while (itExpected.hasNext()) {
			final MooreState expected = itExpected.next();
			final MooreState mooreState = states.get(expected.getName());
			Assert.assertEquals(expected,mooreState);

		}
	}
	private List<MooreState> expected4NotOrder() {
		final List<MooreState> results = new ArrayList<>();
		MooreState mooreState = new MooreState("A0");
		MooreTransition t;
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { Zero });
		t.setNextState("A0");
		mooreState.addTransition(t);
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { One });
		t.setNextState("State 1");
		mooreState.addTransition(t);
		mooreState.setOutput(new MooreOutput(new TermValue[] { Zero }));
		mooreState.setCodeValue(new TermValue[] {Zero,Zero});
		results.add(mooreState);

		// --- Q2
		mooreState = new MooreState("S 2");

		t = new MooreTransition();
		t.setInputValue(new TermValue[] { Zero });
		t.setNextState("S 2");
		mooreState.addTransition(t);
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { One });
		t.setNextState("State 3");
		mooreState.addTransition(t);
		mooreState.setOutput(new MooreOutput(new TermValue[] { Zero }));
		mooreState.setCodeValue(new TermValue[] {Zero,One});
		results.add(mooreState);
		// --- State 1
		mooreState = new MooreState("State 1");

		t = new MooreTransition();
		t.setInputValue(new TermValue[] { Zero });
		t.setNextState("State 1");
		mooreState.addTransition(t);
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { One });
		t.setNextState("S 2");
		mooreState.addTransition(t);
		mooreState.setOutput(new MooreOutput(new TermValue[] { One }));
		mooreState.setCodeValue(new TermValue[] {One,One});
		results.add(mooreState);
		// --- State 3
		mooreState = new MooreState("State 3");

		t = new MooreTransition();
		t.setInputValue(new TermValue[] { Zero });
		t.setNextState("State 3");
		mooreState.addTransition(t);
		t = new MooreTransition();
		t.setInputValue(new TermValue[] { One });
		t.setNextState("A0");
		mooreState.addTransition(t);
		mooreState.setOutput(new MooreOutput(new TermValue[] { One }));
		mooreState.setCodeValue(new TermValue[] {One,Zero});
		results.add(mooreState);
		return results;
	}

}
