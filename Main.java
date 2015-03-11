import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File aFile = new File("Data.dat");
		if(aFile.exists()){
			System.out.println("Choose your level(Beginner | Veteran): ");
			Scanner sc = new Scanner(System.in);
			Computer theComputer = new Computer();
			String userInput = sc.nextLine();
			while(!validInputChoosingFile(userInput)){
				System.out.println("Invalid input, please try again: ");
				userInput = sc.nextLine();
			}
			if(userInput.equals("Beginner")){
				theComputer = new Computer();
			}else{
				try{
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(aFile)	);
					theComputer = (Computer) in.readObject();
					in.close();
				}catch(IOException e){
					System.out.println("Error processing file.");
				}catch(ClassNotFoundException e){
					System.out.println("Could not find class.");
				}
			}
			boolean quit = false;
			int userScore = 0;
			int computerScore = 0;
			String userPattern = "";
			while(!quit){
				System.out.println("Welcome to the Rock,Paper,Scissor.");
				System.out.println("Please choose your move: r,p,s, type \'quit\' to quit: ");
				Scanner sc2  = new Scanner(System.in);
				String input = sc2.nextLine();
				while(!validInput(input)){
					System.out.println("Invalid input, please try again: ");
					input = sc2.nextLine();
				}
				userPattern += input;
				if(input.equals("quit"))
					break;
				else{
					int winner = theComputer.returnWinner(input.charAt(0));
					theComputer.processInput(userPattern);
					switch(winner){
					// computer wins
					case 1:
						System.out.println("Computer wins.");
						computerScore++;
						break;
						// user wins
					case -1:
						System.out.println("User wins.");
						userScore++;
						break;
						// Tie
					case 0:
						System.out.println("Tie.");
						break;
					}
					System.out.println("Computer - "+computerScore+" User - "+userScore);
				}
			}
			// After the user type quit
			boolean writeFile = false;
			Scanner sc3 = new Scanner(System.in);
			System.out.println("Would you like the save the file? (Y/N): ");
			String userInputYN = sc3.nextLine();
			while( !validInputForSaveFile(userInputYN)){
				System.out.println("Invalid input, please try again: ");
				userInputYN = sc3.nextLine();
			}
			if(userInputYN.charAt(0) == 'Y'){
				try{
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(aFile));
					out.writeObject(theComputer);
					out.close();
				}catch(IOException e){
					System.out.println("Error processing file.");
				}
			}
		}
		else {
			// if the file doesn't exist, start over
			boolean quit = false;
			int userScore = 0;
			int computerScore = 0;
			String userPattern = "";
			Computer theComputer = new Computer();
			while(!quit){
				System.out.println("Welcome to the Rock,Paper,Scissor.");
				System.out.println("Please choose your move: r,p,s, type \'quit\' to quit: ");
				Scanner sc  = new Scanner(System.in);
				String input = sc.nextLine();
				while(!validInput(input)){
					System.out.println("Invalid input, please try again: ");
					input = sc.nextLine();
				}
				userPattern += input;
				if(input.equals("quit"))
					break;
				else{
					int winner = theComputer.returnWinner(input.charAt(0));
					theComputer.processInput(userPattern);
					switch(winner){
					// computer wins
					case 1:
						System.out.println("Computer wins.");
						computerScore++;
						break;
						// user wins
					case -1:
						System.out.println("User wins.");
						userScore++;
						break;
						// Tie
					case 0:
						System.out.println("Tie.");
						break;
					}
					System.out.println("Computer - "+computerScore+" User - "+userScore);
				}
			}
			// After the user type quit
			Scanner sc = new Scanner(System.in);
			System.out.println("Would you like the save the file? (Y/N): ");
			String userInputYN = sc.nextLine();
			while( !validInputForSaveFile(userInputYN)){
				System.out.println("Invalid input, please try again: ");
				userInputYN = sc.nextLine();
			}
			if(userInputYN.charAt(0) == 'Y'){
				try{
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(aFile));
					out.writeObject(theComputer);
					out.close();
				}catch(IOException e){
					System.out.println("Error processing file.");
				}
			}
		}
		
		
		
	}	
	
	/**
	 * Check for valid input when choosing weapon
	 * @param pString - the user input
	 * @return - true if valid, false otherwise
	 */
	public static boolean validInput(String pString)
	{
		if(pString.equals("quit"))
			return true;
		else if(pString.length() != 1)
			return false;
		else if(pString.charAt(0) != 'r' && pString.charAt(0) != 'p' && pString.charAt(0) != 's'){
			return false;
		}else 
			return true;
	}
	
	/**
	 * Check for valid input when saving file
	 * @param pString - The choice input
	 * @return - true if valid input, false otherwise
	 */
	public static boolean validInputForSaveFile(String pString)
	{
		if(pString.length() != 1)
			return false;
		else if(pString.charAt(0) != 'Y' && pString.charAt(0) != 'N')
			return false;
		return true;
	}
	
	public static boolean validInputChoosingFile(String pString)
	{
		if( pString.equals("Beginner") || pString.equals("Veteran"))
			return true;
		else
			return false;
	}
	

}
