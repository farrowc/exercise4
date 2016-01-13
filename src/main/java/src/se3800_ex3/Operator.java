package se3800_ex3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles taking a mathematical command and outputing a result for it.
 * This class also handles the history.
 *
 */
public class Operator {

	/**
	 * A list of the valid mathematical commands
	 */
	private ArrayList<String> validCommands;

	/**
	 * A list of the old commands in the history
	 */
	private ArrayList<String> oldCommands;

	/**
	 * A list of the old results in the history
	 */
	private ArrayList<Double> oldResults;

	/**
	 * Constructor that adds the possible commands to the list of valid commands
	 * @author farrowc
	 */
	public Operator(){
		validCommands = new ArrayList<String>();
		oldCommands = new ArrayList<String>();
		oldResults = new ArrayList<Double>();		
		validCommands.add("add");
		validCommands.add("sub");
		validCommands.add("mul");
		validCommands.add("div");
	}

	/**
	 * Parses a command as a string and returns the value
	 * @param command A string that is in the form of one of the following:
	 * add x y z...: adds a list of numbers and returns their sum
	 * sub x y: subtracts a list of numbers from the first number and returns their difference
	 * mul x y: multiplies a list of numbers and returns their product
	 * div x y: divides a list of numbers from the first number and returns their quotient
	 * one of the above commands can be substituted in for x or y by putting the command in its place with paranthesis around it.
	 * ie. div (add 2 2) 2 would return 2
	 * @return The result of the command
	 * @throws Exception if the command is invalid
	 */
	public Double parseCommandAndGetResult(String command) throws Exception{
		Double result = null;
		String usedCommand = command.substring(0, 3);

		//We are checking to see if the command is valid
		if(validCommands.contains(usedCommand)){
			//The command type is valid, so now we check to see if the values entered are valid
			ArrayList<Double> values = new ArrayList<Double>();

			//We have to tell the parser where to start, so we start at index 4 in the string
			int startCharacterIndex = 4;

			//Checking to see if an equation was substituted in for X, where x is an entered number, equation, or history reference
			boolean endReached = false;
			while(!endReached){
				if(command.charAt(startCharacterIndex)=='('){
					//It was, so now we must find it
					int numberOfSubCommands = 1;
					int end = startCharacterIndex+1;
					while(end<command.length() && numberOfSubCommands!=0){
						if(command.charAt(end)=='('){
							//We found another sub command
							numberOfSubCommands++;
							end++;
						}
						else if(command.charAt(end)==')'){
							//We found the end of a sub command
							numberOfSubCommands--;
							if(numberOfSubCommands!=0){
								end++;
							}
						}else{
							end++;
						}
					}
					if(end == command.length() && numberOfSubCommands>0){
						throw new Exception();
					}
					//Now we have found the end of the 'X' command, so we will parse it
					String cmd = command.substring(startCharacterIndex+1,end);
					values.add(parseCommandAndGetResult(cmd));
					startCharacterIndex = end + 2;
					if(end == command.length()){
						endReached = true;
					}					
				}
				else if(command.charAt(startCharacterIndex)=='!'){
					//X is not a command, but it is a history call
					int end = command.indexOf(" ",startCharacterIndex+1);
					//This is the end of x, now we will send it to be parsed as a command
					if(end > startCharacterIndex){
						//This check is to make sure that there was a space after x
						String cmd = command.substring(startCharacterIndex,end);
						values.add(getResultFromHistory(cmd));
					}
					else if(end < startCharacterIndex){
						//This check is to make sure that there was a space after x
						String cmd = command.substring(startCharacterIndex,command.length());
						values.add(getResultFromHistory(cmd));
					}
					if(end == command.length() || end < startCharacterIndex){
						endReached = true;
					}
					startCharacterIndex = end + 1;
				
				}
				else{
					//X is not a command or history call, so we will treat it as a normal number
					//Since index 4 is the beginning of X, we will find the end of x by finding the next space
					int end = command.indexOf(" ",startCharacterIndex+1);
					//This is the end of x, now we will parse x into a double
					if(end > startCharacterIndex){
						//This check is to make sure that there was a space after x
						String value = command.substring(startCharacterIndex,end);
						values.add(Double.parseDouble(value));
					}
					else if(end < startCharacterIndex){
						//This check is to make sure that there was a space after x
						String value = command.substring(startCharacterIndex,command.length());
						values.add(Double.parseDouble(value));
					}
					if(end == command.length() || end < startCharacterIndex){
						endReached = true;
					}
					startCharacterIndex = end + 1;
				}
			}

			//Now we have all of the values that were entered.  Now we do a mathamatical operation on them based on what command was entered
			if(command.substring(0,3).equals("add")){
				result = new Double(0);
				for(Double value : values){
					result += value;
				}
			}else if(command.substring(0,3).equals("sub")){
				result = new Double(values.get(0));
				values.remove(0);
				for(Double value : values){
					result -= value;
				}
			}else if(command.substring(0,3).equals("mul")){
				result = new Double(values.get(0));
				values.remove(0);
				for(Double value : values){
					result *= value;
				}
			}
			else if(command.substring(0,3).equals("div")){
				result = new Double(values.get(0));
				values.remove(0);
				for(Double value : values){
					if(value == 0){
						throw new Exception();
					}
					else{
						result /= value;
					}
				}
			}

		}
		if(result == null){
			throw new Exception();
		}
		//We have the result, so now let's return it!
		return result;
	}
	/**
	 * @author thouse
	 * @param cmd the command issued to operate
	 * @return
	 * @throws Exception 
	 * Grabs result from backend of list so if user
	 * inputs !1 or !0 the latest result will be used
	 */
	public Double getResultFromHistory(String cmd) throws Exception
	{
		int resultWanted;
		int index = cmd.indexOf("!");
		String tmp = cmd.substring(index+1);
		Scanner in = new Scanner(tmp);
		resultWanted = in.nextInt();
		in.close();
			return oldResults.get(resultWanted-1);
	}

	/**
	 * This method adds a command and its result to the history
	 * @param command A command that was entered
	 * @param result A Double that was a result from an the command that was entered
	 */
	public void addCommandAndResult(String command,Double result){
		oldCommands.add(command);
		oldResults.add(result);
	}

	/**
	 * Clears the history of the commands and results
	 */
	public void clearHistory(){
		oldCommands.clear();
		oldResults.clear();
	}

	/**
	 * This method gets all of the old commands
	 * @return A list of the old commands
	 */
	public ArrayList<String> getOldCommands(){
		return oldCommands;
	}

	/**
	 * This method gets all of the old results
	 * @return A list of the old results
	 */
	public ArrayList<Double> getOldResults(){
		return oldResults;
	}

}
