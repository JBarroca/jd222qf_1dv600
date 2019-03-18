import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hangman {

	private Score playerScore;
	private String word;
	private int triesLeft = 9;
	private boolean wordHasBeenFound= false;
	private ArrayList<String> guessedLetters = new ArrayList<>();
	private Scanner gameScanner = new Scanner(System.in);
	
	public Hangman() {
		Word gameWord = new Word();
		this.word = gameWord.getWord();
		this.playerScore = new Score(gameWord.getBonusPoints());
	}
	
	public Hangman(String wordString, int score) {
		this.word = wordString;
		this.playerScore = new Score(score);
	}
	
	public void startProgram() throws IOException {
		System.out.println("inside startProgram()");
		
	    Menu.showStartMenu();
	    String input;
	    
	    do {
		    System.out.print("\nPlease press a key to select an option: ");
	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
		    	newGameMenu();
	    	} else if (input.equals("2")) {
		    	highScoreMenu();
	    	} else if (input.equals("0")) {
		    	if(wantsToQuit()) {
					quitGame();
		    	} else {
		    		input = "-1";
		    		continue;
		    	}
	    	} else {
	    		System.out.print("Invalid input.\n");	    		
	    	}
	    } while (!(input.equals("1") || input.equals("2") || input.equals("0")));  
	}
	
	public void resetGame() throws IOException {
		System.out.println("inside resetGame()");
		
		Word newGameWord = new Word();
		this.word = newGameWord.getWord();
		this.playerScore = new Score(newGameWord.getBonusPoints());
		this.triesLeft = 9;
		this.guessedLetters.clear();
		this.wordHasBeenFound = false;
		startProgram();
	}
	
	public void quitGame() {
		System.out.println("\nBye bye!");
		this.gameScanner.close();
		System.exit(0);
	}
	
	public void playSingleGame() throws IOException {
		System.out.println("inside playSingleGame()");
		
		String message = "let's play!";
		Menu.showGameRound(updateWord(this.word, this.guessedLetters), this.guessedLetters, this.triesLeft, message);
		
		while (this.triesLeft > 0 && !this.wordHasBeenFound) {
			System.out.println("\nPlease enter a letter or guess the entire word");
			System.out.print("or write 'resetgame' to return to start menu or 'quitgame' to quit the program): ");
			String playerInput = this.gameScanner.next();
			
			//player enters a word
			if (playerInput.length() > 1) {
				if (wordIsGuessed(playerInput)) {
					this.wordHasBeenFound = true;
					this.playerScore.onRightWordGuess(this.triesLeft);
					winGame(playerInput.toUpperCase(), this.triesLeft, this.playerScore);
				} else if (playerInput.equals("resetgame")) {
					if(wantsToReset()) {
						resetGame();
					} else {
						continue;
					}
				} else if (playerInput.equals("quitgame")) {
					if(wantsToQuit()) {
			    		quitGame();
			    	} else {
			    		continue;
			    	}
				} else {
					message = "Nope, that's not the correct word!";
					this.playerScore.onWrongWordGuess();
					this.triesLeft--;
				}
			//player enters a single Character
			} else {
				//input is a valid letter
				if (inputIsValid(playerInput, this.guessedLetters)) {
					//letter exists in word
					if(this.word.contains(playerInput)) {
						this.playerScore.onRightTry();
						this.guessedLetters.add(playerInput);
						Collections.sort(this.guessedLetters);
						//check for completed word
						if(wordIsGuessed(updateWord(this.word, this.guessedLetters))) {
							this.wordHasBeenFound = true;
							winGame(updateWord(this.word, this.guessedLetters), this.triesLeft, this.playerScore);
						} else {
							message = ":) Nice guess!";							
						}
					//letter does not exist in word
					} else {
						this.playerScore.onWrongTry();
						this.guessedLetters.add(playerInput);
						Collections.sort(this.guessedLetters);
						message = ":( Nope, no " + playerInput + "'s in this word!";
						this.triesLeft--;								
					}					
				//invalid input
				} else {
					message = "Enter a valid letter that you haven't tried before.";
				}
			}
			System.out.println("Aqui 1");
			Menu.showGameRound(updateWord(this.word, this.guessedLetters), this.guessedLetters, this.triesLeft, message);
		}
		//player runs out of tries (i.e., player looses)
		if (this.triesLeft == 0) {
			loseGame();
		}
	}
	
	/**
	 * Returns the hidden game word replacing every unguessed character as a hyphen.
	 * If the player guesses a letter, changes the hyphen back to that letter.
	 * @param gameWord			The game's current word 
	 * @param guessedLetters	an ArrayList containing every previously guessed letter
	 * @return					A String representation of the game word according
	 * to the letters that the Player has guessed so far.
	 */
	public String updateWord(String gameWord, ArrayList<String> guessedLetters) {
		System.out.println("inside updateWord()");
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < gameWord.length(); i++) {
			if(guessedLetters.contains(Character.toString(gameWord.charAt(i)))) {
				sb.append(Character.toUpperCase(gameWord.charAt(i)));
			} else {
				sb.append("-");
			}
		}
		return sb.toString();	
	}
	
	/**
	 * Returns true if the player writes a letter and if that letter has not 
	 * been guessed before. Returns false otherwise.
	 * @param input				a String of length 1 written in the console by the Player
	 * @param guessedLetters	an ArrayList containing every previously guessed letter
	 * @return					true if input is a letter and it has not been previously
	 * guessed, false otherwise
	 */
	public boolean inputIsValid(String input, ArrayList<String> guessedLetters) {
		return (Character.isLetter(input.charAt(0)) && !guessedLetters.contains(input));
	}	
	
	public boolean wordIsGuessed(String guess) {
		return guess.equalsIgnoreCase(this.word);
	}
	
	public void winGame(String word, int triesLeft, Score playerScore) throws IOException {
		
		boolean isHighScore = playerScore.isHighScore();
		Menu.showWinGame(word, triesLeft, playerScore, isHighScore);
		String input;
	    
		if(isHighScore) {
			System.out.print("Please enter your nickname to register your highscore: ");
			input = this.gameScanner.next();
			Score.registerHighScore(input, playerScore);			
		}
		
	    do {
		    System.out.println("\nPlease press a key to select an option: ");
		    System.out.println("1 - Return to Start menu");
		    System.out.println("2 - View High Scores");
		    System.out.println("0 - Quit the application");
		    System.out.print("-> ");

	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
		    	resetGame();
	    	} else if (input.equals("2")) {
	    		highScoreMenu();
	    	} else if (input.equals("0")) {
		    	if(wantsToQuit()) {
		    		quitGame();
		    	} else {
		    		input = "-1";
		    		continue;		    		
		    	}
	    	} else {
	    		System.out.print("Invalid input.\n");
	    		continue;
	    	}
	    } while (!(input.equals("1") || input.equals("2") || input.equals("0")));
	}

	public void loseGame() throws IOException {
		Menu.showLoseGame(this.word);
	    String input;
	    
	    do {
		    System.out.println("\nPlease press a key to select an option: ");
		    System.out.println("1 - Return to Start menu");
		    System.out.println("2 - View High Scores");
		    System.out.println("0 - Quit the application");
		    System.out.print("-> ");
		    
	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
		    	resetGame();
	    	} else if (input.equals("0")) {
	    		if(wantsToQuit()) {
	    			quitGame();
		    	} else {
		    		input = "-1";
		    		continue;		    		
		    	}
    		} else {
	    		System.out.print("Invalid input.\n");	    		
    		}
	    } while (!(input.equals("1") || input.equals("0")));
	}	
	
	public boolean wantsToReset() {
		System.out.print("Do you really want to end this game and return to the start menu? (\"y\" = yes, \"n\" = no): ");
		boolean wantsToReset = false;
		String input;
		
		do {
			input = this.gameScanner.next();
			if (input.equals("y")) {
				wantsToReset = true;										
			} else if (input.equals("n")) {
				wantsToReset = false;
			} else {
				System.out.print("Invalid input.\nDo you really want to end this game and return to the start menu? (\"y\" = yes, \"n\" = no): ");
			}
		} while (!(input.equals("y") || input.equals("n")));
		return wantsToReset;
	}

	public boolean wantsToQuit() {
		System.out.print("Do you really want to quit the application? (\"y\" = yes, \"n\" = no): ");
		boolean wantsToQuit = false;
		String input;
		
		do {
			input = this.gameScanner.next();
			if (input.equals("y")) {
				wantsToQuit = true;
			} else if (input.equals("n")) {
				wantsToQuit = false;
			} else {
				System.out.print("Invalid input. Do you really want to quit the application? (\"y\" = yes, \"n\" = no): ");
			}
		} while (!(input.equals("y") || input.equals("n")));
		return wantsToQuit;	
	}
		
	public void newGameMenu() throws IOException {
	    Menu.showNewGameMenu();
	    String input;
	    
	    do {
		    System.out.print("\nPlease press a key to select an option: ");
	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
		    	playSingleGame();
	    	} else if (input.equals("2")) {
		    	System.out.println("To be implemented");
	    	} else if (input.equals("0")) {
		    	resetGame();
	    	} else {
	    		System.out.print("Invalid input.\n");	    		
	    	}
	    } while (!(input.equals("1") || input.equals("2") || input.equals("0")));
	}
	
	public void highScoreMenu() throws IOException {
	    Menu.showHighScoreMenu();
	    String input;
	    
	    do {
		    System.out.print("\nPlease press a key to select an option: ");
	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
		    	System.out.println("To be implemented");
	    	} else if (input.equals("2")) {
	    		System.out.println("To be implemented");
	    	} else if (input.equals("0")) {
	    		resetGame();
	    	} else {
	    		System.out.print("Invalid input.\n");	    		
	    	}
	    } while (!(input.equals("1") || input.equals("2") || input.equals("0")));
	}
	
}