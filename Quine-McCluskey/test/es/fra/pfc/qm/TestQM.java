package es.fra.pfc.qm;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;

import org.junit.Test;

public class TestQM {

	private static final int ITERATIONS = 10000;

	public static Term readTerm(InputStream reader) throws IOException {
		int c = '\0';
		ArrayList<TermValue> t = new ArrayList<TermValue>();
		while (c != '\n' && c != -1) {
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
			TermValue[] resultBytes = new TermValue[t.size()];
			for (int i = 0; i < t.size(); i++) {
				resultBytes[i] = t.get(i);
			}
			return new Term(resultBytes);
		} else {
			return null;
		}
	}

	public static Formula readFormula(InputStream reader) throws IOException {
		ArrayList<Term> terms = new ArrayList<Term>();
		Term term;
		while ((term = readTerm(reader)) != null) {
			terms.add(term);
		}
		return new Formula(terms);
	}

	@Test
	public void testSample1() throws IOException {
		InputStream sample1 = this.getClass().getResourceAsStream("Sample1.txt");
		Formula f = readFormula(sample1);
		System.out.println ("........................................................");
		System.out.println (f);
		f.reduceToPrimeImplicants();
		System.out.println (f);
		f.reducePrimeImplicantsToSubset();
		System.out.println (f);
		System.out.println ("........................................................");
		
	}
	
	@Test
	public void testSample2() throws IOException {
		InputStream sample1 = this.getClass().getResourceAsStream("Sample2.txt");
		Formula f = readFormula(sample1);
		System.out.println ("........................................................");
		System.out.println (f);
		long start1 = System.nanoTime();
		f.reduceToPrimeImplicants();
		long end1 = System.nanoTime();
		System.out.println (f);
		long start2 = System.nanoTime();
		f.reducePrimeImplicantsToSubset();
		long end2 = System.nanoTime();
		System.out.println (f);
		System.out.println (" t1  : " + (end1 - start1)+ "ns t2 : " + (end2 - start2)+"ns");
		System.out.println ("........................................................");
		
		
	}
	@Test
	public void testSample3() throws IOException {
		InputStream sample1 = this.getClass().getResourceAsStream("Sample3.txt");
		Formula f = readFormula(sample1);
		System.out.println ("........................................................");
		System.out.println (f);
		long start1 = System.nanoTime();
		f.reduceToPrimeImplicants();
		long end1 = System.nanoTime();
		System.out.println (f);
		long start2 = System.nanoTime();
		f.reducePrimeImplicantsToSubset();
		long end2 = System.nanoTime();
		System.out.println (f);
		System.out.println (" t1  : " + (end1 - start1)+ "ns t2 : " + (end2 - start2)+"ns");
		System.out.println ("........................................................");
		
		
	}
	
	@Test
	public void testSample4AndPerformance() throws IOException, CloneNotSupportedException {
		InputStream sample1 = this.getClass().getResourceAsStream("Sample4.txt");
		BufferedInputStream reader = new BufferedInputStream(sample1);
		Formula original = readFormula(reader);
		Formula f = original.clone();
		System.out.println ("........................................................");
		System.out.println (f);
		long start1 = System.nanoTime();
		f.reduceToPrimeImplicants();
		long end1 = System.nanoTime();
		System.out.println (f);
		long start2 = System.nanoTime();
		f.reducePrimeImplicantsToSubset();
		long end2 = System.nanoTime();
		System.out.println (f);
		System.out.println (" t1  : " + (end1 - start1)+ "ns t2 : " + (end2 - start2)+ "ns");
		System.out.println ("........................................................");
		
		// Let's measure 100 Iterations
		long[] measures = new long[ITERATIONS];
		for(int x=0;x<ITERATIONS;x++) {
			f = original.clone();
			start1 = System.nanoTime();
			f.reduceToPrimeImplicants();
			f.reducePrimeImplicantsToSubset();
			end1 = System.nanoTime();
			measures[x] = end1 - start1;
		}
		long median = 0;
		for (long l : measures) {
			median = median + l;
		}
		median = median / ITERATIONS;
		System.out.println (" media " + median);
		
	}
	
	@Test
	public void testSample5() throws IOException {
		InputStream sample1 = this.getClass().getResourceAsStream("Sample5.txt");
		Formula f = readFormula(sample1);
		System.out.println ("........................................................");
		System.out.println (f);
		long start1 = System.nanoTime();
		f.reduceToPrimeImplicants();
		long end1 = System.nanoTime();
		System.out.println (f);
		long start2 = System.nanoTime();
		f.reducePrimeImplicantsToSubset();
		long end2 = System.nanoTime();
		System.out.println (f);
		System.out.println (" t1  : " + (end1 - start1)+ "ns t2 : " + (end2 - start2)+"ns");
		System.out.println ("........................................................");
		
		
	}
	
}
