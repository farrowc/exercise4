package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import se3800_ex3.Operator;
import se3800_ex3.UserInterface;

/**
 * 
 * @author thouse
 * Tests Good commands
 */
public class TestGoodCommands 
{
	private Operator operator = new Operator();
	/**
	 * Tests the system recognizes a legal operation
	 * and returns the expected result
	 */
	@Test
	public void testGoodCommand1() {
		String command = "add 1 5";
		try {
			Assert.assertEquals("Failed the expected result is 6", (Double)operator.parseCommandAndGetResult(command),(Double)6.0);
		} catch (Exception e) {
			fail("Failed to execute addition operation");
		}
	}
	/**
	 * Tests the system recognizes a legal operation
	 * and returns the expected result
	 */
	@Test
	public void testGoodCommand2() {
		String command = "mul 1 5";
		try {
			Assert.assertEquals("Failed the expected result is 5", (Double)operator.parseCommandAndGetResult(command),(Double)5.0);
		} catch (Exception e) {
			fail("Failed to execute multiplication operation");
		}
	}
	/**
	 * Tests the system recognizes a legal operation
	 * and returns the expected result
	 */
	@Test
	public void testGoodCommand3() {
		String command = "sub 1 5";
		try {
			Assert.assertEquals("Failed the expected result is -4", (Double)operator.parseCommandAndGetResult(command),(Double)(-4.0));
		} catch (Exception e) {
			fail("Failed to execute subtraction operation");
		}
	}
	/**
	 * Tests the system recognizes a legal operation
	 * and returns the expected result
	 */
	@Test
	public void testGoodCommand4() {
		String command = "div 1 5";
		try {
			Assert.assertEquals("Failed the expected result is 0.2", (Double)operator.parseCommandAndGetResult(command),(Double)0.2);
		} catch (Exception e) {
			fail("Failed to execute division operation");
		}
	}
	/**
	 * Tests the system can read in a list of commands
	 */
	@Test
	public void testGoodCommand5() {
		String command = "add 1 5 7";
		try {
			Assert.assertEquals("Failed the expected result is 13", (Double)operator.parseCommandAndGetResult(command),(Double)13.0);
		} catch (Exception e) {
			fail("Failed to execute addition operation");
		}
	}

	/**
	 * Tests the system recognizes a legal operation
	 * and returns the expected result
	 * Here the extra feature of having a subcommand
	 * issued as param and executed with its' result
	 * returned for use
	 */
	@Test
	public void testGoodCommand6() {
		String command = "div (add 2 2) 2";
		try {
			Assert.assertEquals("Failed the expected result is 2", (Double)operator.parseCommandAndGetResult(command),(Double)2.0);
		} catch (Exception e) {
			fail("Failed to execute addition and multiplication operation");
		}
	}
	/**
	 * Test the UserInterface acceptable commands
	 */
	@Test
	public void testGoodCommand7() {
		UserInterface ui=new UserInterface();
		String command = "div (add 2 2) 2";
		ui.selectAndRunCommand(command);
		command = "history";
		ui.selectAndRunCommand(command);
	}
	/**
	 * Test the UserInterface acceptable commands
	 */
	@Test
	public void testGoodCommand8() {
		String command = "quit";
		try {
			UserInterface.selectAndRunCommand(command);
		} catch (Exception e) {
			fail("Failed to execute quit operation");
		}
	}
	/**
	 * Test the UserInterface acceptable commands
	 */
	@Test
	public void testGoodCommand9() {
		UserInterface ui=new UserInterface();
		String command = "div (add 2 2) 2";
		ui.selectAndRunCommand(command);
		command="clear";
		try {
			ui.selectAndRunCommand(command);
		} catch (Exception e) {
			fail("Failed to execute clear operation");
		}
	}
	/**
	 * Test the UserInterface acceptable commands
	 */
	@Test
	public void testGoodCommand10() {
		UserInterface ui=new UserInterface();
		String command = "div (add (mul 1 1) 2) 2";
		ui.selectAndRunCommand(command);
		command="clear";
		try {
			ui.selectAndRunCommand(command);
		} catch (Exception e) {
			fail("Failed to execute clear operation");
		}
	}


}
