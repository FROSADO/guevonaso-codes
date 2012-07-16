package es.fra.sm.model;

import junit.framework.Assert;

import org.junit.Test;

public class TestMooreTable {

	@Test
	public void basicTableTest() {
		// // A list of rows
		//
		// final StateTable<Integer, Integer> table = new StateTable<Integer,
		// Integer>();
		//
		// final TableRow<Integer, Integer> row1 = new TableRow<>("Q0", "Q1",
		// new Integer[] { 1, 2, 3 }, new Integer[] { 2, 3, 1 });
		// final TableRow<Integer, Integer> row2 = new TableRow<>("Q1", "Q1",
		// new Integer[] { 1, 2, 3 }, new Integer[] { 2, 3, 1 });
		// final TableRow<Integer, Integer> row3 = new TableRow<>("Q2", "Q1",
		// new Integer[] { 1, 2, 3 }, new Integer[] { 2, 3, 1 });
		// final TableRow<Integer, Integer> row4 = new TableRow<>("Q3", "Q1",
		// new Integer[] { 1, 2, 3 }, new Integer[] { 2, 3, 1 });
		// final TableRow<Integer, Integer> row5 = new TableRow<>("Q4", "Q1",
		// new Integer[] { 1, 2, 3 }, new Integer[] { 2, 3, 1 });
		// final TableRow<Integer, Integer> row6 = new TableRow<>("Q5", "Q1",
		// new Integer[] { 1, 2, 3 }, new Integer[] { 2, 3, 1 });
		//
		// table.addRow(row1);
		// table.addRow(row1);
		// // Now only a row must be
		// assertTrue(table.getRows().size() == 1);
		//
		// table.addRow(row2);
		// table.addRow(row3);
		// table.addRow(row4);
		// table.addRow(row5);
		// table.addRow(row6);
		// assertTrue(table.getRows().size() == 6);

		Assert.fail("Not implemented yet");
	}

	@Test
	public void generateFormulas() {
		// We need log2 (N) flip-flops
		for (int i = 2; i < 10; i++) {
			final double flipFlops = (Math.log(i) / Math.log(2));
			System.out.println(i + ": " + flipFlops + "  " + (int) flipFlops
					+ "   " + Math.ceil(flipFlops));
		}

	}
}
