package es.fra.pfc.qm;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BasicTest {

	private Term[] ones;
	private Term oneTerm;

	@Before
	public void setup() throws CloneNotSupportedException {
		oneTerm = new Term(new TermValue[] { TermValue.One, TermValue.One, TermValue.One });
		ones = new Term[] { // ---
		(Term) oneTerm.clone(), // 1
				(Term) oneTerm.clone(), // 2
				(Term) oneTerm.clone() // 3
		};

	}

	@Test
	public void testToString() {

		Formula f = new Formula(ones);
		System.out.println(f.toString());
		assertNotNull(f.toString());

	}

	@Test
	public void testClone() throws CloneNotSupportedException {
		Object clone = oneTerm.clone();
		assertNotSame("Clone failed", clone, oneTerm);
	}

	@Test
	public void testCombine() throws CloneNotSupportedException {
		
		assertNull(oneTerm.combine(oneTerm.clone()));
		Term otherTerm = new Term(new TermValue[] { TermValue.One, TermValue.Zero,
				TermValue.One });
		Term result = oneTerm.combine(otherTerm);
		System.out.println(oneTerm + " , " + otherTerm + " --> " +result);
		otherTerm = new Term(new TermValue[] { TermValue.Zero, TermValue.One,
				TermValue.One });
		result = oneTerm.combine(otherTerm);
		System.out.println(oneTerm + " , " + otherTerm + " --> " +result);
		
	}
	
	
}
