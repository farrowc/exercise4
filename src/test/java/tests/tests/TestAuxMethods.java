package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import se3800_ex3.Operator;
/**
 * 
 * @author thouse
 *	Test methods that aren't part of the core functionality
 */
public class TestAuxMethods {
	private Operator operator = new Operator();
	/**
	 * Test to validate that a command and a result
	 * are added to their respective lists
	 * @throws Exception
	 */
	@Test
	public void testAddCmdResults() throws Exception {
		int cmdSize=0, resSize = 0;
		//verify the lists are first empty
		if(cmdSize != operator.getOldCommands().size() || resSize != operator.getOldResults().size())
			fail("Both lists should be empty since no operation has been executed");
		operator.addCommandAndResult("add 1 5", 6.0);
		cmdSize++;
		resSize++;
		if(cmdSize != operator.getOldCommands().size() || resSize != operator.getOldResults().size())
			fail("Both lists should have one element an operation has been executed");		
	}
	/**
	 * Tests that clearing the history empties 
	 * both the command and results list
	 */
	@Test
	public void testClear() {
		operator.clearHistory();
		if(0 != operator.getOldCommands().size() || 0 != operator.getOldResults().size())
			fail("Both lists should be empty since the history was cleared");
	}
	
	/*
	 * Tests the parser ability to get the proper result from the history
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testParser() throws Exception{
		operator.addCommandAndResult("add 1 2", 3.0);
		Double result = operator.parseCommandAndGetResult("add !1 2");
		Assert.assertEquals("Failed to get expected result of 5",(Double)5.0,result);
	}

}
