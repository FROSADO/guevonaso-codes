package es.fra.sm.service;

import static es.fra.sm.model.TermValue.One;
import static es.fra.sm.model.TermValue.Zero;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import es.fra.sm.model.TermValue;

public class StateCoderTest {

	TermValue[][]	validGray		= new TermValue[][] {
			new TermValue[] { Zero, Zero, Zero, Zero }, // 0
			new TermValue[] { Zero, Zero, Zero, One }, // 1
			new TermValue[] { Zero, Zero, One, One }, // 2
			new TermValue[] { Zero, Zero, One, Zero },
			new TermValue[] { Zero, One, One, Zero },
			new TermValue[] { Zero, One, One, One },
			new TermValue[] { Zero, One, Zero, One },
			new TermValue[] { Zero, One, Zero, Zero },
			new TermValue[] { One, One, Zero, Zero }, // 8
			new TermValue[] { One, One, Zero, One } };	// 9
	TermValue[][]	validGrayThree	= new TermValue[][] {
			new TermValue[] { Zero, Zero }, // 0
			new TermValue[] { Zero, One }, // 1
			new TermValue[] { One, One } };			// 2

	@Test
	public void testCodeStates() {
		// Let's validate 10 states
		final String[] states = new String[] { "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9" };

		final TermValue[][] result = StateCoder.codeStates(Arrays
				.asList(states));
		Assert.assertNotNull(result);
		Assert.assertEquals(10, result.length);
		final int terms = result[0].length;
		for (int i = 0; i < result.length; i++) {
			final TermValue[] termValues = result[i];
			// Each value must be the same size
			Assert.assertEquals(terms, termValues.length);
			// for (final TermValue termValue : termValues) {
			// System.out.print(termValue);
			// }
			Assert.assertArrayEquals(this.validGray[i], termValues);
		}
	}

	@Test
	public void testCodeThreeStates() {
		// Let's validate 3 states
		final String[] states = new String[] { "0", "1", "2" };
		final TermValue[][] result = StateCoder.codeStates(Arrays
				.asList(states));
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.length);
		final int terms = result[0].length;
		for (int i = 0; i < result.length; i++) {
			final TermValue[] termValues = result[i];
			// Each value must be the same size
			Assert.assertEquals(terms, termValues.length);
			// for (final TermValue termValue : termValues) {
			// System.out.print(termValue);
			// }
			Assert.assertArrayEquals(this.validGrayThree[i], termValues);
		}
	}
}
