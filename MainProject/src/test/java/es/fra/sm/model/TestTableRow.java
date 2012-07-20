package es.fra.sm.model;

import static es.fra.sm.model.TermValue.DontCare;
import static es.fra.sm.model.TermValue.One;
import static es.fra.sm.model.TermValue.Zero;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class TestTableRow {


	@Test
	public void testCompareTo() {
		assertTrue("A0".compareTo("C0") < 0);
		assertTrue(One.compareTo(Zero) > 0);
		assertTrue(DontCare.compareTo(Zero) < 0);
		final TableRow table = new TableRow("A0","B0");
		table.addInput(Zero);
		table.addInput(One);
		table.addOutput(Zero);
		table.addOutput(One);
		final TableRow table1 = new TableRow("A0","B0");
		table1.addInput(Zero);
		table1.addInput(One);
		table1.addOutput(Zero);
		table1.addOutput(One);
		assertEquals(table, table1);
		assertTrue(table.compareTo(table1) == 0);
		table1.setFinalState("C0");
		assertTrue(table.compareTo(table1) < 0);
		table1.setFinalState("A0");
		assertTrue(table.compareTo(table1) > 0);

		table.setStartState("B0");
		table.setFinalState("B0");

		table1.setStartState("A0");
		table1.setFinalState("B0");

		assertTrue(table.compareTo(table1) > 0);

		table.setStartState("A0");
		table.addInput(Zero);
		assertTrue(table.compareTo(table1) > 0);
		table1.addInput(One);
		assertTrue(table.compareTo(table1) < 0);
	}

}
