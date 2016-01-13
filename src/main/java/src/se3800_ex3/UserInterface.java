package se3800_ex3;

import java.util.Scanner;

/**
 * This class handles the main user interface for the calculator
 * @author farrowc
 *
 */
public class UserInterface {
	
	/**
	 * Creates the user interface that is used by the calculator program
	 * @author farrowc
	 */
	
	/**
	 * The Operator object that is used by the calculator
	 */
	static private Operator operator = new Operator();
	
	/**
	 * The boolean that is true if the user enters the quit command
	 */
	static private boolean doneCalculating = false;
	
	/**
	 * The main user interface that is run.  Asks the user to enter a command, then prints results of the command and then loops.
	 * Loop is ended by entering the "quit" command.
	 */
	static void startInterface(){
		//First begin the loop
		while(!doneCalculating){
			
			//Create a Scanner object to read the input from the user and ask him/her for a command
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter a command: add x y z a | sub x y z a | mul x y z a | div x y z a | history | clear | quit");
			System.out.println("Mathamatical commands can be substituted in for a number in the form (add x y z)");
			
			//Finally we get their input and then select the command to be run
			String command = scanner.nextLine();
			selectAndRunCommand(command);
		}
		
	}
	
	/**
	 * A method designed to help the interface select what command should be run
	 * @param command The command entered by the user
	 */
	public static void selectAndRunCommand(String command){
		try {
			//Check to see if the user wants to quit
			if(command.equals("quit")){
				doneCalculating = true;
			}
			//Check to see if the user wants to view history
			else if(command.equals("history")){
				int i = 0;
				for(String oldCommand: operator.getOldCommands()){
					System.out.println(oldCommand+" = "+operator.getOldResults().get(i));
					i++;
				}
			}
			//Check to see if the user wants to clear the history
			else if(command.equals("clear")){
				operator.clearHistory();
			}
			//If neither of these, parse the command as a mathematical operation
			else{
				Double result = operator.parseCommandAndGetResult(command);
				System.out.println(result);
				operator.addCommandAndResult(command, result);
				}
		} catch (Exception e) {
			System.out.println("There was an error in processing your command, please make sure the syntax is correct");
		}
	}
	
}
