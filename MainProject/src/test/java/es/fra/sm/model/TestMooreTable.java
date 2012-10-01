package es.fra.sm.model;

import org.junit.Assert;
import org.junit.Test;

import es.fra.sm.StateMachineException;

import static es.fra.sm.model.TermValue.*;

public class TestMooreTable {

	@Test
	public void basicMooreCreate() {
		TableMoore table = new TableMoore();
		// Vamos a definir los estados.

		{
			// Primera transicion
			// Q0 -> Q0 : I=0, O=0

			OutputFromStateMachine output = new OutputFromStateMachine("O",
					Zero);
			// Primera prueba. El inicio es el final pero con estados diferentes
			StateMoore start = new StateMoore("Q0", 0, output);
			StateMoore end = new StateMoore("Q0", 0, output);

			InputToStateMachine input = new InputToStateMachine("I", Zero);
			TransitionMoore t1 = new TransitionMoore(start, end, input);
			table.addTransition(t1);
		}
		{
			// Q0 -> Q1 : I=1, O=1
			InputToStateMachine input = new InputToStateMachine("I", One);
			OutputFromStateMachine output1 = new OutputFromStateMachine("O",
					One);
			OutputFromStateMachine output0 = new OutputFromStateMachine("O",
					Zero);
			StateMoore end = new StateMoore("Q1", 1, output1);
			// Cuidado. Ahora nos encontramos con un posible problema. Dos
			// estados q0 con diferentes salidas?
			StateMoore start = new StateMoore("Q0", 0, output1);
			// Segunda transicion, cambio de Q0 a Q1 cuando entra un 1

			TransitionMoore t2 = new TransitionMoore(start, end, input);
			try {
				table.addTransition(t2);
				Assert.fail("Esperada una excepción de validacion. Q0 already registered with output = 0");
			} catch (StateMachineException e) {
				System.out.println("Excepcion capturada");
				start = new StateMoore("Q0", 0, output0);
				t2 = new TransitionMoore(start, end, input);
				table.addTransition(t2);
			}

		}
		// Tercera transicion
		{
			// Q1 -> Q1 : I=1, O=1
			InputToStateMachine input = new InputToStateMachine("I", One);
			OutputFromStateMachine output1 = new OutputFromStateMachine("O",
					One);

			StateMoore end = new StateMoore("Q1", 1, output1);
			// Cuidado. Ahora nos encontramos con un posible problema. Dos
			// estados q0 con diferentes salidas?
			StateMoore start = new StateMoore("Q1", 1, output1);
			// Segunda transicion, cambio de Q0 a Q1 cuando entra un 1

			TransitionMoore t3 = new TransitionMoore(start, end, input);

			table.addTransition(t3);

		}
		// Cuarta transicion
		{
			// Q1 -> Q0 : I=0, O=0
			InputToStateMachine input = new InputToStateMachine("I", Zero);
			OutputFromStateMachine output0 = new OutputFromStateMachine("O",
					Zero);
			OutputFromStateMachine output1 = new OutputFromStateMachine("O",
					One);

			StateMoore start = new StateMoore("Q1", 1, output1);
			// Cuidado. Ahora nos encontramos con un posible problema. Dos
			// estados q0 con diferentes salidas?
			StateMoore end = new StateMoore("Q0", 0, output0);
			// Segunda transicion, cambio de Q0 a Q1 cuando entra un 1

			TransitionMoore t4 = new TransitionMoore(start, end, input);

			table.addTransition(t4);

		}

	}
	// @Test
	// public void basicTableTest() {
	//
	// final MooreTable table = new MooreTable();
//		final TableRow[] row = new TableRow[] {//@formatter:off
//				new TableRow("A", "A", new TermValue[] { Zero },new TermValue[] { Zero }), //
//				new TableRow("A", "B", new TermValue[] { One },	new TermValue[] { Zero }), //
//				new TableRow("B", "B", new TermValue[] { Zero },new TermValue[] { One }), //
//				new TableRow("B", "A", new TermValue[] { One },	new TermValue[] { One }) //
//
//		};//@formatter:on
	// // Qn | Qn+1 | D
	// // 0 | 0 | 0
	// // 0 | 1 | 1
	// // 1 | 0 | 0
	// // 1 | 1 | 1
	//
	// // State | Next | Input | Output | D Excitation
	// // A (0)| A(0)| 0 | 0 | 0
	// // A (0)| B(1)| 1 | 0 | 1 --> -AI --> 01
	// // B (1)| B(1)| 0 | 1 | 1 --> B-I --> 10
	// // B (1)| A(0)| 1 | 1 | 0
	// //
	// for (final TableRow tableRow : row) {
	// table.addRow(tableRow);
	// }
	// // Let's verify output
	// final Map<String, MooreState> states = table.getStates();
	// // Expected estates :
	// final List<MooreState> expected1 = this.expected1();
	// for (final MooreState expected : expected1) {
	// Assert.assertEquals(expected, states.get(expected.getName()));
	// }
	//
	// // LEt's verify excitation tables
	// final Formula[] formulas = table
	// .generateExcitationFormulas(new DExcitationTable());
	// // First lets verify size . 2 States, D FlipFlop --> 1 Excitation
	// // Formula
	// // D0 = 1,2
	//
//		//@formatter:off
//		final Formula[] expected = new Formula[] {
//				new Formula("D0",
//						new Term(Zero,One), // 1
//						new Term(One,Zero)  // 2
//						)
//		};
//		//@formatter:on
	// Assert.assertEquals(1, formulas.length);
	// Assert.assertArrayEquals(expected, formulas);
	// formulas[0].reduceToPrimeImplicants();
	// formulas[0].reducePrimeImplicantsToSubset();
	//
	// }
	//
	// private List<MooreState> expected1() {
	// final MooreState a = new MooreState("A");
	// a.setOutput(new MooreOutput(new TermValue[] { Zero }));
	// a.setCodeValue(new TermValue[] { Zero });
	//
	// // ------------------
	// final MooreTransition taa = new MooreTransition();
	// taa.setInputValue(new TermValue[] { Zero });
	// taa.setNextState("A");
	// final MooreTransition tab = new MooreTransition();
	// tab.setInputValue(new TermValue[] { One });
	// tab.setNextState("B");
	// // -------------
	//
	// a.addTransition(taa);
	// a.addTransition(tab);
	//
	// final MooreState b = new MooreState("B");
	// b.setOutput(new MooreOutput(new TermValue[] { One }));
	// b.setCodeValue(new TermValue[] { One });
	// final MooreTransition tbb = new MooreTransition();
	// tbb.setInputValue(new TermValue[] { Zero });
	// tbb.setNextState("B");
	// final MooreTransition tba = new MooreTransition();
	// tba.setInputValue(new TermValue[] { One });
	// tba.setNextState("A");
	//
	// b.addTransition(tbb);
	// b.addTransition(tba);
	//
	// final ArrayList<MooreState> states = new ArrayList<>();
	// states.add(a);
	// states.add(b);
	// return states;
	// }
	//
	// /**
	// * This test verify that four states works.
	// */
	// @Test
	// public void fourStatesTest() {
	//
	// final MooreTable table = new MooreTable();
//		final TableRow[] row = new TableRow[] {//@formatter:off
//				new TableRow("Q0", "Q0", new TermValue[] { Zero },new TermValue[] { Zero }), //
//				new TableRow("Q0", "Q1", new TermValue[] { One },	new TermValue[] { Zero }), //
//				new TableRow("Q1", "Q1", new TermValue[] { Zero },new TermValue[] { One }), //
//				new TableRow("Q1", "Q2", new TermValue[] { One },	new TermValue[] { One }), //
//				new TableRow("Q2", "Q2", new TermValue[] { Zero },new TermValue[] { Zero }), //
//				new TableRow("Q2", "Q3", new TermValue[] { One },	new TermValue[] { Zero }), //
//				new TableRow("Q3", "Q3", new TermValue[] { Zero },new TermValue[] { One }), //
//				new TableRow("Q3", "Q0", new TermValue[] { One },	new TermValue[] { One }) //
//
//
//		};//
//			// Qn | Qn+1 | D
//			// 0 | 0 | 0
//			// 0 | 1 | 1
//			// 1 | 0 | 0
//			// 1 | 1 | 1
//
//		// State | Next | Input | Output | Excitation
//		// qo(00) qo(00) 	0 		0 		00
//		// qo(00) q1(01) 	1 		0 		01 --> D1 001
//		// q1(01) q1(01) 	0 		1 		01 --> D1 010
//		// q1(01) q2(11) 	1 		1 		11 -->D0D1 011
//		// q2(11) q2(11) 	0 		0 		11 -->D0D1 110
//		// q2(11) q3(10) 	1 		0 		10 -->D0 111
//		// q3(10) q3(10) 	0 		1 		10 -->D0 100
//		// q3(10) qo(00) 	1 		1 		00
//		//
//		// D0 = 3,4,6,7 D1 = 1,2,3,6
//		//@formatter:on
	// for (final TableRow tableRow : row) {
	// table.addRow(tableRow);
	// }
	// // Let's verify output
	// final Map<String, MooreState> states = table.getStates();
	// // Expected estates :
	// final List<MooreState> expected4 = this.expected4();
	// for (final MooreState expected : expected4) {
	// Assert.assertEquals(expected, states.get(expected.getName()));
	// }
	// // LEt's verify excitation tables
	// final Formula[] formulas = table
	// .generateExcitationFormulas(new DExcitationTable());
	//
//		//@formatter:off
//		// TODO Â¿Porque este orden?
//		final Formula[] expected = new Formula[] {
//			new Formula("D0",
//				new Term(Zero,One,One), // 3
//				new Term(One,One,Zero),// 6
//				new Term(One,One,One), // 7
//			new Term(One,Zero,Zero)  // 4
//			),
//			new Formula("D1",
//				new Term (Zero,Zero,One),// 1
//				new Term (Zero,One,Zero),// 2
//				new Term(Zero,One,One), // 3
//				new Term(One,One,Zero) // 6
//		)
//		};
//		//@formatter:on
	// Assert.assertEquals(2, formulas.length);
	// Assert.assertArrayEquals(expected, formulas);
	// formulas[0].reduceToPrimeImplicants();
	// formulas[0].reducePrimeImplicantsToSubset();
	//
	// }
	//
	// private List<MooreState> expected4() {
	// final List<MooreState> results = new ArrayList<>();
	// MooreState mooreState = new MooreState("Q0");
	// MooreTransition t;
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { Zero });
	// t.setNextState("Q0");
	// mooreState.addTransition(t);
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { One });
	// t.setNextState("Q1");
	// mooreState.addTransition(t);
	// mooreState.setOutput(new MooreOutput(new TermValue[] { Zero }));
	// mooreState.setCodeValue(new TermValue[] { Zero, Zero });
	// results.add(mooreState);
	// // --- Q1
	// mooreState = new MooreState("Q1");
	//
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { Zero });
	// t.setNextState("Q1");
	// mooreState.addTransition(t);
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { One });
	// t.setNextState("Q2");
	// mooreState.addTransition(t);
	// mooreState.setOutput(new MooreOutput(new TermValue[] { One }));
	// mooreState.setCodeValue(new TermValue[] { Zero, One });
	// results.add(mooreState);
	// // --- Q2
	// mooreState = new MooreState("Q2");
	//
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { Zero });
	// t.setNextState("Q2");
	// mooreState.addTransition(t);
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { One });
	// t.setNextState("Q3");
	// mooreState.addTransition(t);
	// mooreState.setOutput(new MooreOutput(new TermValue[] { Zero }));
	// mooreState.setCodeValue(new TermValue[] { One, One });
	// results.add(mooreState);
	// // --- Q3
	// mooreState = new MooreState("Q3");
	//
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { Zero });
	// t.setNextState("Q3");
	// mooreState.addTransition(t);
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { One });
	// t.setNextState("Q0");
	// mooreState.addTransition(t);
	// mooreState.setOutput(new MooreOutput(new TermValue[] { One }));
	// mooreState.setCodeValue(new TermValue[] { One, Zero });
	// results.add(mooreState);
	// return results;
	// }
	//
	// @Test
	// public void fourStatesNotOrderedTest() {
	//
	// final MooreTable table = new MooreTable();
//		final TableRow[] row = new TableRow[] {//@formatter:off
//				new TableRow("A0", "A0", new TermValue[] { Zero },new TermValue[] { Zero }), //
//				new TableRow("S 2", "S 2", new TermValue[] { Zero },new TermValue[] { Zero }), //
//				new TableRow("State 1", "State 1", new TermValue[] { Zero },new TermValue[] { One }), //
//				new TableRow("State 3", "State 3", new TermValue[] { Zero },new TermValue[] { One }), //
//				new TableRow("State 1", "S 2", new TermValue[] { One },	new TermValue[] { One }), //
//				new TableRow("S 2", "State 3", new TermValue[] { One },	new TermValue[] { Zero }), //
//				new TableRow("A0", "State 1", new TermValue[] { One },	new TermValue[] { Zero }), //
//				new TableRow("State 3", "A0", new TermValue[] { One },	new TermValue[] { One }) //
//
//			
//		};
//		// Qn | Qn+1 | D
//		// 0 | 0 | 0
//		// 0 | 1 | 1
//		// 1 | 0 | 0
//		// 1 | 1 | 1
//		// Table coded by alphabetic order
//		// State | Next | Input | Output | Excitation
//		// A0(00) A0(00) 	0 		0 		00
//		// A0(00) St1(11) 	1 		0 		11 -->D0D1 001
//		// St1(11) St1(11) 	0 		1 		11 -->D0D1 110
//		// St1(11) S2(01) 	1 		1 		01 -->D1   111
//		// S2(01) S2(01) 	0 		0 		01 -->D1 010
//		// S2(01) St3(10) 	1 		0 		10 -->D0 011
//		// St3(10) St3(10) 	0 		1 		10 -->D0 100
//		// St3(10) A0(00) 	1 		1 		00
//		//
//		// D0 = 1,6,3,4 D1 = 1,6,7,2
//
//		//@formatter:on
	// for (final TableRow tableRow : row) {
	// table.addRow(tableRow);
	// }
	// // Let's verify output
	// final Map<String, MooreState> states = table.getStates();
	// // Expected estates :
	// final List<MooreState> expected4 = this.expected4NotOrder();
	// final Iterator<MooreState> itExpected = expected4.iterator();
	// while (itExpected.hasNext()) {
	// final MooreState expected = itExpected.next();
	// final MooreState mooreState = states.get(expected.getName());
	// Assert.assertEquals(expected, mooreState);
	//
	// }
	// // LEt's verify excitation tables
	// final Formula[] formulas = table
	// .generateExcitationFormulas(new DExcitationTable());
	//
//		//@formatter:off
//		// D0 = 1,6,3,4 D1 = 1,6,7,2
//				// TODO ï¿½Porque este orden?
//				final Formula[] expected = new Formula[] {
//					new Formula("D0",
//						new Term(Zero,Zero,One), // 1
//						new Term(Zero,One,One), // 3
//						new Term(One,One,Zero),// 6
//						new Term(One,Zero,Zero)  // 4
//					),
//					new Formula("D1",
//						new Term (Zero,Zero,One),// 1
//						new Term (Zero,One,Zero),// 2
//						new Term(One,One,Zero), // 6
//						new Term(One,One,One) // 7
//				)
//				};
//				//@formatter:on
	// Assert.assertEquals(2, formulas.length);
	// Assert.assertArrayEquals(expected, formulas);
	// formulas[0].reduceToPrimeImplicants();
	// System.out.println(formulas[0]);
	// formulas[0].reducePrimeImplicantsToSubset();
	// List<Term> reduced = formulas[0].getTermList();
	// System.out.println(formulas[0]);
	// formulas[1].reduceToPrimeImplicants();
	// System.out.println(formulas[1]);
	// formulas[1].reducePrimeImplicantsToSubset();
	// reduced = formulas[1].getTermList();
	// System.out.println(formulas[1]);
	//
	// }
	//
	// private List<MooreState> expected4NotOrder() {
	// final List<MooreState> results = new ArrayList<>();
	// MooreState mooreState = new MooreState("A0");
	// MooreTransition t;
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { Zero });
	// t.setNextState("A0");
	// mooreState.addTransition(t);
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { One });
	// t.setNextState("State 1");
	// mooreState.addTransition(t);
	// mooreState.setOutput(new MooreOutput(new TermValue[] { Zero }));
	// mooreState.setCodeValue(new TermValue[] { Zero, Zero });
	// results.add(mooreState);
	//
	// // --- Q2
	// mooreState = new MooreState("S 2");
	//
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { Zero });
	// t.setNextState("S 2");
	// mooreState.addTransition(t);
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { One });
	// t.setNextState("State 3");
	// mooreState.addTransition(t);
	// mooreState.setOutput(new MooreOutput(new TermValue[] { Zero }));
	// mooreState.setCodeValue(new TermValue[] { Zero, One });
	// results.add(mooreState);
	// // --- State 1
	// mooreState = new MooreState("State 1");
	//
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { Zero });
	// t.setNextState("State 1");
	// mooreState.addTransition(t);
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { One });
	// t.setNextState("S 2");
	// mooreState.addTransition(t);
	// mooreState.setOutput(new MooreOutput(new TermValue[] { One }));
	// mooreState.setCodeValue(new TermValue[] { One, One });
	// results.add(mooreState);
	// // --- State 3
	// mooreState = new MooreState("State 3");
	//
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { Zero });
	// t.setNextState("State 3");
	// mooreState.addTransition(t);
	// t = new MooreTransition();
	// t.setInputValue(new TermValue[] { One });
	// t.setNextState("A0");
	// mooreState.addTransition(t);
	// mooreState.setOutput(new MooreOutput(new TermValue[] { One }));
	// mooreState.setCodeValue(new TermValue[] { One, Zero });
	// results.add(mooreState);
	// return results;
	// }
	//
	// @Test
	// public void testSampleOne() {
	// final MooreTable table = new MooreTable();
//		final TableRow[] row = new TableRow[] {//@formatter:off
//			new TableRow("A", "A", new TermValue[] { Zero,Zero },new TermValue[] { Zero }), //
//			new TableRow("A", "B", new TermValue[] { Zero,One },new TermValue[] { Zero }), //
//			new TableRow("A", "B", new TermValue[] { One,Zero },new TermValue[] { Zero }), //
//			new TableRow("B", "C", new TermValue[] { Zero,Zero },new TermValue[] { One }), //
//			new TableRow("B", "B", new TermValue[] { Zero,One },new TermValue[] { One }), //
//			new TableRow("B", "B", new TermValue[] { One,Zero },	new TermValue[] { One }), //
//			new TableRow("C", "C", new TermValue[] { Zero,Zero },	new TermValue[] { One }), //
//			new TableRow("C", "D", new TermValue[] { Zero,One },new TermValue[] { One }), //
//			new TableRow("C", "D", new TermValue[] { One,Zero },	new TermValue[] { One }), //
//			new TableRow("D", "A", new TermValue[] { Zero,Zero },	new TermValue[] { One }), //
//			new TableRow("D", "D", new TermValue[] { Zero,One },new TermValue[] { One }), //
//			new TableRow("D", "D", new TermValue[] { One,Zero },	new TermValue[] { One }) //
//	};//
//	
//	//@formatter:on
	// for (final TableRow tableRow : row) {
	// table.addRow(tableRow);
	// }
	// Formula[] formulas = table.generateExcitationFormulas(new
	// DExcitationTable());
	// System.out.println(formulas[0]);
	// formulas[0].reduceToPrimeImplicants();
	// System.out.println(formulas[0]);
	// formulas[0].reducePrimeImplicantsToSubset();
	// System.out.println(formulas[0]);
	// System.out.println("------------------------------------------------------------------");
	// System.out.println(formulas[1]);
	// formulas[1].reduceToPrimeImplicants();
	// System.out.println(formulas[1]);
	// formulas[1].reducePrimeImplicantsToSubset();
	// System.out.println(formulas[1]);
	//
	// }

}
