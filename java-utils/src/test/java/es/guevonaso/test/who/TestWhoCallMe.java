package es.guevonaso.test.who;

import junit.framework.TestCase;

import es.guevonaso.who.WhoCallMe;

public class TestWhoCallMe extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetFullInfo() {
		String invoker = getInvoker();
		assertTrue(invoker.lastIndexOf(getName()) != -1);
	}
	
	private String getInvoker() {
		WhoCallMe i = new WhoCallMe(TestWhoCallMe.class, "getInvoker");
		return i.getFullInfo();
	}
	
}
