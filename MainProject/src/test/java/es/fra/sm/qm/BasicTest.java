package es.fra.sm.qm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import es.fra.sm.model.TermValue;

public class BasicTest {

	private Term[]	ones;
	private Term	oneTerm;

	@Before
	public void setup() throws CloneNotSupportedException {
		this.oneTerm = new Term(new TermValue[] { TermValue.One, TermValue.One,
				TermValue.One });
		this.ones = new Term[] { // ---
		this.oneTerm.clone(), // 1
				this.oneTerm.clone(), // 2
				this.oneTerm.clone() // 3
		};

	}

	@Test
	public void testToString() {

		final Formula f = new Formula(this.ones);
		System.out.println(f.toString());
		assertNotNull(f.toString());

	}

	@Test
	public void testClone() throws CloneNotSupportedException {
		final Object clone = this.oneTerm.clone();
		assertNotSame("Clone failed", clone, this.oneTerm);
	}

	@Test
	public void testCombine() throws CloneNotSupportedException {

		assertNull(this.oneTerm.combine(this.oneTerm.clone()));
		Term otherTerm = new Term(new TermValue[] { TermValue.One,
				TermValue.Zero, TermValue.One });
		Term result = this.oneTerm.combine(otherTerm);
		System.out.println(this.oneTerm + " , " + otherTerm + " --> " + result);
		otherTerm = new Term(new TermValue[] { TermValue.Zero, TermValue.One,
				TermValue.One });
		result = this.oneTerm.combine(otherTerm);
		System.out.println(this.oneTerm + " , " + otherTerm + " --> " + result);

	}

}
