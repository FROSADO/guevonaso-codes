package es.fra.sm.model;

import static es.fra.sm.model.TermValue.DontCare;
import static es.fra.sm.model.TermValue.One;
import static es.fra.sm.model.TermValue.Zero;

import org.junit.Assert;
import org.junit.Test;

public class TestTableRow {

	@Test
	public void testCompareTo() {
		Assert.assertTrue("A0".compareTo("C0") < 0);
		Assert.assertTrue(One.compareTo(Zero) > 0);
		Assert.assertTrue(DontCare.compareTo(Zero) < 0);
		final TableRow table = new TableRow("A0", "B0");
		table.addInput(Zero);
		table.addInput(One);
		table.addOutput(Zero);
		table.addOutput(One);
		final TableRow table1 = new TableRow("A0", "B0");
		table1.addInput(Zero);
		table1.addInput(One);
		table1.addOutput(Zero);
		table1.addOutput(One);
		Assert.assertEquals(table, table1);
		Assert.assertTrue(table.compareTo(table1) == 0);
		table1.setFinalState("C0");
		Assert.assertTrue(table.compareTo(table1) < 0);
		table1.setFinalState("A0");
		Assert.assertTrue(table.compareTo(table1) > 0);

		table.setStartState("B0");
		table.setFinalState("B0");

		table1.setStartState("A0");
		table1.setFinalState("B0");

		Assert.assertTrue(table.compareTo(table1) > 0);

		table.setStartState("A0");
		table.addInput(Zero);
		Assert.assertTrue(table.compareTo(table1) > 0);
		table1.addInput(One);
		Assert.assertTrue(table.compareTo(table1) < 0);
	}

}
