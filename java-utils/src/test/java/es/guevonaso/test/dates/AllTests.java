package es.guevonaso.test.dates;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for es.guevonaso.test.dates");
		//$JUnit-BEGIN$
		suite.addTestSuite(DateTimeUtilsTest.class);
		//$JUnit-END$
		return suite;
	}
	
}
