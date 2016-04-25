
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class randCFL 
{	
	//MAIN
	public static void main(String[] args) 
	{
		//attempt to read in a file
		File file;
		boolean ARG = false;
		Scanner input = null;
		String line; //line to parse from when reading file


		String mappings[][];
		String start = "";
		ArrayList <ArrayList<String>> rules = new ArrayList <ArrayList<String>>();
		char delimeter = '\t';

		//have a variable that can be passed through the command line for number of print statements
		int num = 1; //defaults to 1

		//System.out.println("cmd args len: " + args.length); //temp
		

		
		//if arguments are the correct size
		// ===================================================================================================== //
		if(args.length > 0)
		{
			if (args.length == 3) 
			{
				if(args[0].equalsIgnoreCase("-t"))
				{
					ARG = true; //set argument to true, run the program differently if true
					//read in file
					try 
					{
						file = new File(args[1]); //read in starting at the 2nd argument
						input = new Scanner(file);
					} catch (IOException e) {
						System.out.println("Error reading in file, exiting...");
						//e.printStackTrace();
						System.exit(0);
					}//catch

					//now read in command line arguements for numbers to repeat
					try
					{
						num = Integer.parseInt(args[2]);
					} catch(Exception e) {
						System.out.println("Error with command line arguements, exiting...");
						//e.printStackTrace();
						System.exit(0);
					}//catch
					//System.out.println("successfully read in args[0]: " + args[0]); //temp
				}//if args[0] is equal to -t
			}//if arguements length is 3
			else if(args.length == 2) //IF ARGS.LEN == 2
			{
				if(args[0].equalsIgnoreCase("-t"))
				{
					System.out.println("args: " + args[0]);
					ARG = true; //set argument to true, run the program differently if true
					//read in file
					try 
					{
						file = new File(args[1]); //read in starting at the 2nd argument
						input = new Scanner(file);
					} catch (IOException e) {
						System.out.println("Error reading in file, exiting...");
						//e.printStackTrace();
						System.exit(0);
					}//catch
					// System.out.println("successfully read in args[0]: " + args[0]); //temp
				}//if args[0] is equal to -t
				else //if formatted like "grammar 1"
				{
					//read in file
					try 
					{
						file = new File(args[0]); //read in starting at the 2nd argument
						input = new Scanner(file);
					} catch (IOException e) {
						System.out.println("Error reading in file, exiting...");
						//e.printStackTrace();
						System.exit(0);
					}//catch
					//now read in command line arguements for numbers to repeat
					try
					{
						num = Integer.parseInt(args[1]);
					} catch(Exception e) {
						System.out.println("Error with command line arguements, exiting...");
						//e.printStackTrace();
						System.exit(0);
					}//catch
				}//else
			}//args.length == 2
			else if(args.length == 1) //IF ARGUMENTS.LEN == 1
			{
				//read in file
				try 
				{
					file = new File(args[0]); //read in starting at the 1st argument
					input = new Scanner(file);
				} catch (IOException e) {
					System.out.println("Error reading in file, exiting...");
					//e.printStackTrace();
					System.exit(0);
				}//catch	
				//System.out.println("successfully read in args[0]: " + args[0]); //temp
			}//else if args.len == 1
		}//if arguments.len are correct length
		else
		{
			System.out.println("Error, incorrect amount of arguments, exiting...");
			System.exit(0); //exit program
		}//if args.len is not a correct length
		//DONE -- command line args checking
		// ===================================================================================================== //





		//MARK: handle the rest of the program here now
		int counter = 0;
		while(input.hasNextLine())
		{
			line = input.nextLine();
			

			String in = line.substring(0,line.indexOf(delimeter)).trim();
			String out = line.substring(line.indexOf(delimeter)).trim();

			if (in.equalsIgnoreCase("START"))
				start = out;
			else
			{
				//push onto 2D array of in/out
				rules.add(new ArrayList<String>());
				rules.get(counter).add(in); //first element in nested arraylists: input
				rules.get(counter).add(out); //second element in nested arraylists: output
				counter++;
			}
			//System.out.println(line);
		}//while

		//exit the program if there is no start state
		if (start.equals("")) 
		{
			System.out.println("There is no start state, exiting the program...");
			System.exit(0);	
		}

		//TODO: remove this -- it's for testing purposes
		/*
		for (ArrayList x : rules) 
			System.out.print(x.get(0) + ": " + x.get(1) + "\n");
		System.out.println("Start state: " + start);
		*/

		//loop the number of times specified to print the statement
		for (int i = 0; i < num ; i++) {
			System.out.println(processString(start, rules, ARG, ""));
		}
		

	}//main


	/**
	* @param list: arraylist to check for input
	* @param x: string to look for
	* @return boolean that specifies whether or not the string is in the list	
	**/
	//TODO: maybe return an arraylist of the indices that the rule occurs in
	public static boolean containsInput(ArrayList <ArrayList<String>> list, String x)
	{
		for (ArrayList i : list) 
		{
			if (i.get(0).equals(x)) 
				return true;
		}//foreach
		return false;
	}//containsInput


	//TODO: create a recursive way to go through and change each part
	public static String processString(String input, ArrayList <ArrayList<String>> list, boolean cmdArgs, String completeOutput)
	{
		char del = ' '; //delimeter
		//String returnString = ""; //string to return as input for next iteration
		boolean contains = false;
		System.out.println(input);
		String fragment = input.substring(0, input.indexOf(del)).trim();;
		System.out.println("fragment = " + fragment);

		//base case: if no variable is found in the completeInput string the return the input string
		

		for (int i = 0; i < list.size() ; i++) 
		{
			if (!completeOutput.contains((String)list.get(i).get(0)) && !input.contains((String)list.get(i).get(0)) && i == list.size()-1) 
			{
				System.out.println("entered base case");
				return completeOutput;
			}//if
		}//for


		for (ArrayList i : list) 
		{
			if (i.get(0).equals(fragment)) 
			{
				//System.out.println(i.get(1)); //prints out replaced string from the output
				fragment = (String)i.get(1);	
				System.out.println("fragment now set to: " + fragment);
				completeOutput += fragment;
				//returnString += input.substring(input.indexOf(del));
				input = input.substring(input.indexOf(del)).trim();
				System.out.println("input: " + input);
				System.out.println("completeOutput after change: " + completeOutput);
				break;
				//return returnString;
			}
		}
		
		System.out.println("entering next iteration");
		return processString(input, list, cmdArgs, completeOutput);
	}//processString
}//class
