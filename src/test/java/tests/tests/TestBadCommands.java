package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se3800_ex3.Operator;

/**
 * 
 * @author thouse
 *	Tests a variety of bad commands 
 */
public class TestBadCommands {
	
	private Operator operator;
	private String badCommand;
	private String badArgs;

	@Before
	public void setUp() throws Exception {
		operator = new Operator();
		badCommand = "addition 10 5";
		badArgs = "add 5 j";
	}
	
	/**
	 * @author houset 
	 * @date 12/18/15
	 * Purpose: to verify that the application catches invalid commands
	 * and prompts the user as such
	 */
	@Test
	public void testBadCommand1() 
	{
		//changed condition to ==null, was unsure of parsing so command is valid
		try{
			operator.parseCommandAndGetResult(badCommand);
			fail("An illegal command was allowed to execute which puts the system in an error state\nCommand issued:\n"+badCommand);
		}catch(Exception e){
			//passed
			/*
			 * With how parsing works, addition will pass as add but the parser
			 * will try to parse the following chars as if they were digits which throws an exception
			 */
		}
	}
	/**
	 * @author houset 
	 * @date 12/18/15
	 * Purpose: to verify that the application catches invalid commands
	 * and prompts the user as such
	 */
	@Test
	public void testBadCommand2() 
	{
		badCommand="";
		try{
		operator.parseCommandAndGetResult(badCommand);
		fail("An illegal command was allowed to execute which puts the system in an error state\nCommand issued:\n"+badCommand);
		}catch(Exception e){
			//pass 
			//parser does a substring on a static length
			//so given a blank string causes and exception to break
		}
	}
	
	/**
	 * @author houset
	 * @date 12/18/15
	 * Purpose: to verify args given to an operation are numbers and not chars or symbols
	 */
	@Test
	public void testBadOperationArgs1()
	{
		try{
		operator.parseCommandAndGetResult(badArgs);
			fail("Non-numerical values were passed in as arguments which is not handled by the application.\nCommand issued:\n"+badCommand);
		}catch(Exception e){
			//pass
			//bad args includes a char being passed
			//instead of a digit so the parser throws
			//a number format exception
		}
	}
	/**
	 * @author houset
	 * @date 12/18/15
	 * Purpose: to verify args given to an operation are numbers and not chars or symbols
	 */
	@Test
	public void testBadOperationArgs2()
	{
		try{
			operator.parseCommandAndGetResult(badArgs);
				fail("Non-numerical values were passed in as arguments which is not handled by the application.\nCommand issued:\n"+badCommand);
			}catch(Exception e){
				//pass
				//bad args includes a char being passed
				//instead of a digit so the parser throws
				//a number format exception
			}
	}
	/**
	 * @author houset
	 * @throws Exception 
	 * @date 12/18/15
	 * Purpose: to verify args given to an operation are numbers and not chars or symbols
	 */
	@Test
	public void testBadOperationArgs3() throws Exception
	{
		badArgs = "add ( add 45 8)";
		try{
			operator.parseCommandAndGetResult(badArgs);
			fail("Non-numerical values were passed in as arguments which is not handled by the application.\nCommand issued:\n"+badCommand);
		}catch(Exception e){
			//passed
		}
	}
	/**
	 * @author houset
	 * @date 12/18/15
	 * Purpose: to verify args given to an operation are numbers and not chars or symbols
	 */
	@Test
	public void testBadOperationArgs4()
	{
		badArgs = "add (add 45 8 )";
		try{
		operator.parseCommandAndGetResult(badArgs);
			fail("Non-numerical values were passed in as arguments which is not handled by the application.\nCommand issued:\n"+badCommand);
		}catch(Exception e){
			//passed
			//the extra space between the 8 and )
			//throws off the parser thus throwing an exception
		}
	}
	/**
	 * @author houset
	 * @date 12/18/15
	 * Purpose: to verify args given to an operation are numbers and not chars or symbols
	 */
	@Test
	public void testBadOperationArgs5()
	{
		badArgs = "add (add45 8 )";
		try{
			operator.parseCommandAndGetResult(badArgs);
				fail("Non-numerical values were passed in as arguments which is not handled by the application.\nCommand issued:\n"+badCommand);
			}catch(Exception e){
				//passed
				//not adding a space between the add command and 45
				//throws off the parser thus throwing an exception
			}
	}
	/**
	 * @author houset
	 * @throws Exception 
	 * @date 12/18/15
	 * Purpose: to verify args given to an operation are numbers and not chars or symbols
	 */
	@Test
	public void testBadOperationArgs6() throws Exception
	{
		badArgs = "add (add 458)";
		try{
		operator.parseCommandAndGetResult(badArgs);
			fail("Non-numerical values were passed in as arguments which is not handled by the application.\nCommand issued:\n"+badCommand);
		}catch(Exception e){
			//command not properly formatted
			//so an exception id thrown
		}
	}
	/**
	 * @author houset
	 * @throws Exception 
	 * @date 12/18/15
	 * Purpose: to verify args given to an operation are numbers and not chars or symbols
	 */
	@Test
	public void testBadOperationArgs7() throws Exception
	{
		badArgs = "add (plus 458)";
		try{
		operator.parseCommandAndGetResult(badArgs);
			fail("Non-numerical values were passed in as arguments which is not handled by the application.\nCommand issued:\n"+badCommand);
		}catch(Exception e){
			//bad command given
			//plus isnt an acceptable command
			//the digits			
		}
	}
	/**
	 * @author houset
	 * @throws Exception 
	 * @date 12/18/15
	 * Purpose: to verify args given to an operation are numbers and not chars or symbols
	 */
	@Test
	public void testBadOperationArgs8() throws Exception
	{
		badArgs = "add ( add 4 5";
		try{
		operator.parseCommandAndGetResult(badArgs);
			fail("Non-numerical values were passed in as arguments which is not handled by the application.\nCommand issued:\n"+badCommand);
		}catch(Exception e){
			//pass
			//a bad command was issued
			//and the exception was thrown
		}
	}
	/**
	 * @author houset
	 * @date 12/18/15
	 * Purpose: to verify args given to an operation are numbers and not chars or symbols
	 */
	@Test
	public void testBadOperationArgs9()
	{
		badArgs = "add add 4 5)";
		try{
		operator.parseCommandAndGetResult(badArgs);
			fail("Non-numerical values were passed in as arguments which is not handled by the application.\nCommand issued:\n"+badCommand);
		}catch(Exception e){
			//passed
			//w/o the opening (
			//the parser expects a numerical value
			//thus parsing the command add as a number throws an exception
		}
	}
	
	@Test
	public void testDivBy0()
	{
		badArgs = "div 1 0";
		try{
		operator.parseCommandAndGetResult(badArgs);
			fail("Can't div by 0");
		}catch(Exception e){
			//passed
			//w/o the opening (
			//the parser expects a numerical value
			//thus parsing the command add as a number throws an exception
		}
	}

}
