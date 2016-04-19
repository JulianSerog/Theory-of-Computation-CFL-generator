
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class randCFL 
{	
	//MAIN
	public static void main(String[] args) 
	{
		//attempt to read in a file
		File file;
		boolean ARG = false;
		Scanner input;
		String line; //line to parse from when reading file
		
		//if arguments are the correct size
		if(args.length > 0)
		{
			if(args.length == 2) //IF ARGS.LEN == 2
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

					System.out.println("successfully read in args[0]: " + args[0]); //TODO: take out
				}//if args[0] is equal to -t
				else //if incorrect grammar is read in
				{
					System.out.println("Error, issue with command line arguments, exiting...");
					System.exit(0);
				}
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
				System.out.println("successfully read in args[0]: " + args[0]); //TODO: take out
			}//else if args.len == 1
		}//if arguments.len are correct length
		else
		{
			System.out.println("Error, incorrect amount of arguments, exiting...");
			System.exit(0); //exit program
		}//if args.len is not a correct length


		//MARK: handle the rest of the program here now
		while(input.hasNextLine())
		{
			line = input.nextLine();
			System.out.println(line);
		}
		

	}//main
}//class
