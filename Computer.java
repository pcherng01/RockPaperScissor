import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

/**
 * A computer class, its job is the take input from user 
 * and determine if it beats the user based on its prediction
 * using the history of user's inputs.
 * Uses HashMap to keep track of a different patterns that
 * have resulted(the key), and number of times the user chose that
 * pattern.
 * @author LeafCherngchaosil
 *
 */
public class Computer implements Serializable {
	
	/**
	 * Use to keep track of rounds
	 */
	private int mRounds;
	
	/**
	 * Use to store pattern and number of occurences
	 */
	HashMap<Pattern,Integer> mHashMap;
	/**
	 * Use to store the history of user's pattern
	 */
	String mUserPattern;
	
	/**
	 * Create a new Computer object with rounds of 0
	 */
	public Computer()
	{
		// = new Pattern();
		mHashMap = new HashMap<Pattern,Integer>();
		mUserPattern = "";
		mRounds = 0;
	}
	
	/**
	 * This method takes the input from user as char
	 * and determine if it beats the user
	 * @param userInput - The user input as character
	 * @return 1 if computer wins, -1 if user wins, 0 if tie
	 */
	public int returnWinner(char userInput)
	{
		mRounds++;
		// If round less than 5, randomly picks r,p,s
		if(mRounds <= 4){
			mUserPattern += userInput;
			Random aRandom = new Random();
			// 1 - rock, 2 - paper, 3 - scissor
			int randomInt = aRandom.nextInt(3) + 1;
			switch(randomInt){
			case 1: return whoWins('r',userInput);
			case 2: return whoWins('p',userInput);
			case 3: return whoWins('s',userInput);
			}
		}
		else{
			int whoWinz = whoWins(makePrediction().charAt(0),userInput);
			mUserPattern += userInput;
			return whoWinz;
		}
		return 0;
	}
	
	/**
	 * Make prediction based on user input
	 * @return - A weapon that will possibly beat user as String
	 */
	public String makePrediction()
	{
		Random rand = new Random();
		String lookFor = mUserPattern.substring(mUserPattern.length()-3);
		Pattern rPattern = new Pattern(lookFor+'r');
		Pattern pPattern = new Pattern(lookFor+'p');
		Pattern sPattern = new Pattern(lookFor+'s');
		int r = (mHashMap.get(rPattern) == null) ? 0 : mHashMap.get(rPattern);
		int p = (mHashMap.get(pPattern) == null) ? 0 : mHashMap.get(pPattern);
		int s = (mHashMap.get(sPattern) == null) ? 0 : mHashMap.get(sPattern);
		if ( r > s && r > p){
			return "p";
		}
		else if ( s > r && s > p){
			return "r";
		}
		else if ( p > r && p > s){
			return "s";
		}
		else if ( p == r ) {
			int choice = rand.nextInt(2) + 1;
			if ( choice == 1 ){
				return "p";
			}
			else {
				return "s";
			}
		}
		else if ( p == s ){
			int choice = rand.nextInt(2) + 1;
			if ( choice == 1 ){
				return "r";
			}
			else {
				return "s";
			}
		}
		else {
			int choice = rand.nextInt(2) + 1;
			if ( choice == 1 ){
				return "p";
			}
			else{
				return "r";
			}
		}
		
	}
	
	/**
	 * Computer will process the input by storing it in the hashMap
	 * @param userInput - the input of the user
	 */
	public void processInput(String userInput)
	{
		if(userInput.length() >= 4){
			Pattern aPattern = new Pattern(userInput.substring(userInput.length()-4));
			if(mHashMap.containsKey(aPattern)){
				mHashMap.put(aPattern,mHashMap.get(aPattern)+1);
			}
			mHashMap.put(aPattern, 1);
		}
	}
	
	/**
	 * Takes in two params and determine who wins the round
	 * @param pComputer - Input from Computer
	 * @param pUser - Input from User
	 * @return 0 if tie, 1 if computer wins, -1 if user wins
	 */
	public int whoWins(char pComputer, char pUser)
	{
		if(pComputer == 'r' && pUser == 's')
			return 1;
		if(pComputer == 'p' && pUser == 'r')
			return 1;
		if(pComputer == 's' && pUser == 'p')
			return 1;
		else if( pComputer == pUser)
			return 0;
		return -1;
	}
}
