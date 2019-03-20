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
	
	/**
	 * Exhibits the start menu and receives player input
	 * @throws IOException
	 */
	public void startMenu() throws IOException {
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
	
	/**
	 * Displays the New Game menu and receives input from the player
	 * @throws IOException
	 */
	public void newGameMenu() throws IOException {
	    Menu.showNewGameMenu();
	    String input;
	    
	    do {
		    System.out.print("\nPlease press a key to select an option: ");
	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
		    	playSingleGame(0);
	    	} else if (input.equals("2")) {
	    		playSingleGame(1);
	    	} else if (input.equals("0")) {
		    	startMenu();
	    	} else {
	    		System.out.print("Invalid input.\n");	    		
	    	}
	    } while (!(input.equals("1") || input.equals("2") || input.equals("0")));
	}
	
	/**
	 * Displays the High Score menu and receives input from the player
	 * @throws IOException
	 */
	public void highScoreMenu() throws IOException {
	    Menu.showHighScoreMenu();
	    String input;
	    
	    do {
		    System.out.print("\nPlease press a key to select an option: ");
	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
	    		highScoresTable(0);
	    	} else if (input.equals("2")) {
	    		highScoresTable(1);
	    	} else if (input.equals("0")) {
	    		startMenu();
	    	} else {
	    		System.out.print("Invalid input.\n");	    		
	    	}
	    } while (!(input.equals("1") || input.equals("2") || input.equals("0")));
	}
	
	/**
	 * Runs through a round of the game, receiving player input until the word
	 * is guessed (triggering a win game method) or the player runs out of tries
	 * and loses (triggering a lose game method).
	 * @param gameNumber	an integer indicating if the ongoing game is a single-word
	 * game (value 0) or a round of a 5-word game (values 1-5)
	 * @throws IOException
	 */
	public void playSingleGame(int gameNumber) throws IOException {
		String message = "let's play!";
		Menu.showGameRound(updateWord(this.word, this.guessedLetters), this.guessedLetters, this.triesLeft, message, gameNumber);
		
		while (this.triesLeft > 0 && !this.wordHasBeenFound) {
			System.out.println("\nPlease enter a letter or guess the entire word");
			System.out.print("or write 'resetgame' to return to start menu or 'quitgame' to quit the program): ");
			String playerInput = this.gameScanner.next();
			
			//player enters a word
			if (playerInput.length() > 1) {
				if (wordIsGuessed(playerInput)) {
					this.wordHasBeenFound = true;
					this.playerScore.onRightWordGuess(this.triesLeft);
					//wins a single-word game or the final round of a 5-word game
					if (gameNumber == 0 || gameNumber == 5) {
						winGame(playerInput.toUpperCase(), this.triesLeft, this.playerScore, gameNumber);						
					//wins round 1-4 of a 5-word game
					} else if (gameNumber > 0 && gameNumber < 5) {
						win5WordRound(playerInput.toUpperCase(), this.triesLeft, this.playerScore, gameNumber);
					}				
				} else if (playerInput.equals("resetgame")) {
					if(wantsToReset()) {
						resetGame();
						startMenu();
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
							//wins a single-word game
							if (gameNumber == 0 || gameNumber == 5) {
								winGame(updateWord(this.word, this.guessedLetters), this.triesLeft, this.playerScore, gameNumber);								
							//wins round 1-4 of a 5-word game
							} else if (gameNumber > 0 && gameNumber < 5) {
								win5WordRound(updateWord(this.word, this.guessedLetters), this.triesLeft, this.playerScore, gameNumber);
							}
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
			Menu.showGameRound(updateWord(this.word, this.guessedLetters), this.guessedLetters, this.triesLeft, message, gameNumber);
		}
		//player runs out of tries (i.e., player looses)
		if (this.triesLeft == 0) {
			if (gameNumber == 0 || gameNumber == 5) {
				loseGame();
			} else {
				lose5WordRound(gameNumber, this.playerScore);
			}
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
	
	/**
	 * Returns true if the player's input matches the current game word
	 * @param guess		The previous input of the player
	 * @return	true if the player's input matches the current game, false otherwise
	 */
	public boolean wordIsGuessed(String guess) {
		return guess.equalsIgnoreCase(this.word);
	}
	
	/**
	 * Returns true if the player confirms he/she wants to reset, false otherwise
	 * @return true if the player confirms he/she wants to reset, false otherwise
	 */
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
	
	/**
	 * Resets the game (reinitialises class variables to the beginning state and
	 * obtains a new game word)
	 * @throws IOException
	 */
	public void resetGame() throws IOException {
		Word newGameWord = new Word();
		this.word = newGameWord.getWord();
		this.playerScore = new Score(newGameWord.getBonusPoints());
		this.triesLeft = 9;
		this.guessedLetters.clear();
		this.wordHasBeenFound = false;
	}
	
	/**
	 * Returns true if the player confirms he/she wants to quit, false otherwise
	 * @return true if the player confirms he/she wants to quit, false otherwise
	 */
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
	
	/**
	 * Terminates the application (includes closing the game Scanner instance)
	 */
	public void quitGame() {
		System.out.println("\nBye bye!");
		this.gameScanner.close();
		System.exit(0);
	}
	
	/**
	 * Displays the win game board. If the player has reached a new high score for
	 * the current game type, invokes methods to register the high score. Receives
	 * input from the player for further navigation.
	 * @param word			the current game word
	 * @param triesLeft		the number of remaining tries
	 * @param playerScore	the current score
	 * @param gameNumber	an integer indicating if the ongoing game is a single-word
	 * game (value 0) or a round of a 5-word game (values 1-5)
	 * @throws IOException
	 */
	public void winGame(String word, int triesLeft, Score playerScore, int gameNumber) throws IOException {
		boolean isHighScore = playerScore.isHighScore(gameNumber);
		Menu.showWinGame(word, triesLeft, playerScore, isHighScore, gameNumber);
		String input;
	    
		if(isHighScore) {
			System.out.print("Please enter your nickname to register your highscore: ");
			input = this.gameScanner.next();
			Score.registerHighScore(input, playerScore, gameNumber);
			System.out.println();
		}
		
		resetGame();
		
	    do {
		    System.out.println("Please press a key to select an option: ");
		    System.out.println("1 - Return to Start menu");
		    System.out.println("2 - View High Scores");
		    System.out.println("0 - Quit the application");
		    System.out.print("-> ");

	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
		    	startMenu();
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

	/**
	 * Displays the win game board after a single round of a 5-word game, and
	 * receives input from the player for further navigation
	 * @param word			the current game word
	 * @param triesLeft		the number of remaining tries
	 * @param playerScore	the current score
	 * @param gameNumber	an integer indicating if the ongoing game is a single-word
	 * game (value 0) or a round of a 5-word game (values 1-5)
	 * @throws IOException
	 */
	public void win5WordRound(String word, int triesLeft, Score playerScore, int gameNumber) throws IOException {
		Menu.showWinGame(word, triesLeft, playerScore, false, gameNumber);
		String input;

		do {
		    System.out.println("Please press a key to select an option: ");
		    System.out.println("1 - Continue to round " + (gameNumber + 1) + " of 5");
		    System.out.println("2 - Leave the current 5-word game and return to the Start menu");
		    System.out.println("0 - Quit the application");
		    System.out.print("-> ");

	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
	    		startNew5WordRound(playerScore, (gameNumber + 1));
	    	} else if (input.equals("2")) {
				if(wantsToReset()) {
					resetGame();
					startMenu();
				} else {
					input = "-1";
					continue;
				}
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
	
	/**
	 * Starts a new round of a 5-word game, passing the current score to the 
	 * next round
	 * @param currentScore	the current score
	 * @param nextRound		the number of the next round
	 * @throws IOException
	 */
	public void startNew5WordRound(Score currentScore, int nextRound) throws IOException {
		resetGame();
		this.playerScore = new Score(this.playerScore.getScore() + currentScore.getScore());
		playSingleGame(nextRound);
	}
	
	/**
	 * Displays the lose game after a single-word game and receives input from
	 * the player for further navigation.
	 * @throws IOException
	 */
	public void loseGame() throws IOException {
		Menu.showLoseGame(this.word, 0, this.playerScore);
	    resetGame();
		
		String input;
	    
	    do {
		    System.out.println("Please press a key to select an option: ");
		    System.out.println("1 - Return to Start menu");
		    System.out.println("0 - Quit the application");
		    System.out.print("-> ");
		    
	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
		    	startMenu();
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
	
	/**
	 * Displays the lose game board after a round of a 5-word game and receives
	 * input from the player for further navigation.
	 * @param gameNumber	an integer indicating if the ongoing game is a single-word
	 * game (value 0) or a round of a 5-word game (values 1-5)
	 * @param currentScore	the current score
	 * @throws IOException
	 */
	public void lose5WordRound(int gameNumber, Score currentScore) throws IOException {
		Menu.showLoseGame(this.word, gameNumber, currentScore);
		String input;

		do {
		    System.out.println("Please press a key to select an option: ");
		    System.out.println("1 - Continue to round " + (gameNumber + 1) + " of 5");
		    System.out.println("2 - Leave the current 5-word game and return to the Start menu");
		    System.out.println("0 - Quit the application");
		    System.out.print("-> ");

	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
	    		startNew5WordRound(currentScore, (gameNumber + 1));
	    	} else if (input.equals("2")) {
				if(wantsToReset()) {
					resetGame();
					startMenu();
				} else {
					input = "-1";
					continue;
				}
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
		
	/**
	 * Builds and displays the high scores table the player wants to see (either
	 * single-game high scores or 5-word games high scores), and receives input
	 * from the player for further navigation.
	 * @param gameNumber	an integer indicating if the ongoing game is a single-word
	 * game (value 0) or a round of a 5-word game (values 1-5)
	 * @throws IOException
	 */
	public void highScoresTable(int gameNumber) throws IOException {
		Menu.showHighScoreTable(gameNumber);
		String input;
		
	    do {
		    System.out.println("Please press a key to select an option: ");
		    System.out.println("1 - Return to Start menu");
		    System.out.println("2 - Return to High Scores menu");
		    System.out.println("0 - Quit the application");
		    System.out.print("-> ");
		    
	    	input = this.gameScanner.next();
	    	if (input.equals("1")) {
		    	startMenu();
	    	} else if (input.equals("2")){
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
	
}