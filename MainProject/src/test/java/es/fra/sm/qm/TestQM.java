package es.fra.sm.qm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import es.fra.sm.model.TermValue;

public class TestQM {

	private static final int	TEST_FILES	= 6;
	private static final int	ITERATIONS	= 10000;

	public static Term readTerm(InputStream reader) throws IOException {
		int c = '\0';
		final ArrayList<TermValue> t = new ArrayList<TermValue>();
		while ((c != '\n') && (c != -1)) {
			c = reader.read();
			if (c == '0') {
				t.add(TermValue.Zero);
			} else if (c == '1') {
				t.add(TermValue.One);
			} else if (c == 'X') {
				t.add(TermValue.DontCare);
			}
		}
		if (t.size() > 0) {
			final TermValue[] resultBytes = new TermValue[t.size()];
			for (int i = 0; i < t.size(); i++) {
				resultBytes[i] = t.get(i);
			}
			return new Term(resultBytes);
		} else {
			return null;
		}
	}

	public static Formula readFormula(InputStream reader) throws IOException {
		final ArrayList<Term> terms = new ArrayList<Term>();
		Term term;
		while ((term = readTerm(reader)) != null) {
			terms.add(term);
		}
		return new Formula(terms);
	}

	@Test
	public void testSampleFiles() throws IOException {
		for (int x = 1; x < TestQM.TEST_FILES; x++) {
			System.out.println("Sample " + x + " : ");
			final InputStream sample = this.getClass().getResourceAsStream(
					"Sample" + x + ".txt");
			final Formula f = readFormula(sample);
			final long start1 = System.nanoTime();
			f.reduceToPrimeImplicants();
			final long end1 = System.nanoTime();
			// System.out.println(f);
			final long start2 = System.nanoTime();
			f.reducePrimeImplicantsToSubset();
			final long end2 = System.nanoTime();
			// System.out.println(f);
			System.out.println(" t1  : " + (end1 - start1) + "ns t2 : "
					+ (end2 - start2) + "ns");
			// -------------- Verify Result

			final InputStream result = this.getClass().getResourceAsStream(
					"Result" + x + ".txt");
			final Formula r = readFormula(result);

			Assert.assertEquals(r.getTermList(), f.getTermList());

		}

	}

}
