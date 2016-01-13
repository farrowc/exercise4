package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import se3800_ex3.Operator;
/**
 * This class tests the getResultFromHistory method in the Operator class.
 * @author farrowc
 *
 */
public class TestHistoryFromCommand {
	
	/**
	 * The operator that we will be using to test.
	 */
	private Operator operator;
	
	/**
	 * This test tests if we can get the first item from the history.
	 */
	@Test
	public void testFirstItem(){
		operator = new Operator();
		operator.addCommandAndResult("add 2 1", 3.0);
		operator.addCommandAndResult("add 2 3", 5.0);
		operator.addCommandAndResult("add 1 1", 2.0);
		try {
			Double result = operator.getResultFromHistory("!1");
			if(result!=3.0){
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/**
	 * This test tests if we can get the second item from the history.
	 */
	@Test
	public void testSecondItem(){
		operator = new Operator();
		operator.addCommandAndResult("add 2 1", 3.0);
		operator.addCommandAndResult("add 2 3", 5.0);
		operator.addCommandAndResult("add 1 1", 2.0);
		try {
			Double result = operator.getResultFromHistory("!2");
			if(result!=5.0){
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/**
	 * This test tests if we can get the tenth item from the history.
	 */
	@Test
	public void testTenthItem(){
		operator = new Operator();
		operator.addCommandAndResult("add 2 1", 3.0);
		operator.addCommandAndResult("add 2 3", 5.0);
		operator.addCommandAndResult("add 1 1", 2.0);
		operator.addCommandAndResult("add 1 13", 14.0);
		operator.addCommandAndResult("add 1 12", 13.0);
		operator.addCommandAndResult("add 1 11", 12.0);
		operator.addCommandAndResult("add 1 10", 11.0);
		operator.addCommandAndResult("add 1 9", 10.0);
		operator.addCommandAndResult("add 1 5", 6.0);
		operator.addCommandAndResult("add 1 6", 7.0);
		operator.addCommandAndResult("add 1 8", 9.0);
		operator.addCommandAndResult("add 1 3", 4.0);
		operator.addCommandAndResult("add 1 1", 2.0);
		try {
			Double result = operator.getResultFromHistory("!10");
			if(result!=7.0){
				fail();
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	/**
	 * This test tests if the method throws an exception if we try to grab a negative history item
	 */
	@Test
	public void testNegativeItem(){
		operator = new Operator();
		operator.addCommandAndResult("add 2 1", 3.0);
		operator.addCommandAndResult("add 2 3", 5.0);
		operator.addCommandAndResult("add 1 1", 2.0);
		try {
			
			Double result = operator.getResultFromHistory("!-1");
			fail();
		} catch (Exception e) {
		}
	}
}
