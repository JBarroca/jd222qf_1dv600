import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {

	private String playerName; 	//to be implemented
	private int playerScore;	//to be implemented
	private String word;
	private int triesLeft;
	private boolean wordFound= false;
	private ArrayList<String> guessedLetters;
	private Scanner gameScanner = new Scanner(System.in);

	public Game() {
		this.playerScore = 0;
		this.word = new Word().getWord();
		this.triesLeft = 10;
		this.guessedLetters = new ArrayList<>();
		
		startGame();
		this.gameScanner.close();
	}

	private void startGame() {
	    System.out.print(buildStartMenu());
    
	    String input = this.gameScanner.next();
	    switch (input) {
	    case "1":
	    	newGameMenu();
	    	break;
	    case "2":
	    	highScoreMenu();
	    	break;
	    case "3":
	    	System.out.println("QUIT!");
	    	break;
		default:
			System.out.println("Invalid input.");
			startGame();
	    }    
	}
	
	private void playSingleGame() {
		while (this.triesLeft > 0 && !this.wordFound) {
			System.out.println(buildGameRoundBoard(updateWord()));
			System.out.print("Please guess a letter: ");
			String nextLetter = this.gameScanner.next();
			
			//player enters a word
			if (nextLetter.length() > 1) {
				if (wordIsGuessed(nextLetter)) {
					this.wordFound = true;
					winGame(nextLetter.toUpperCase());
					// TODO: IMPLEMENT BONUS FOR GUESSING ENTIRE WORD					
				} else {
					System.out.println("\nNope, that's not the correct word!");
					this.triesLeft--;
				}
			//player enters a single Character
			} else {
				if (inputIsValid(nextLetter)) {
					this.guessedLetters.add(nextLetter);
					Collections.sort(this.guessedLetters);
				} else {
					System.out.println("\nYou've already tried that letter - choose a new letter.");
					continue;
				}	
				if (!this.word.contains(nextLetter)) {
					System.out.println("\n :( Nope, no " + nextLetter + "'s in this word!");
					this.triesLeft--;							
				} else {
					System.out.println("\n :) Nice guess!");
				}
			}
			
		}
		//word is guessed by finding all the letters
		if (!this.wordFound && wordIsGuessed(updateWord())) {
			this.wordFound = true;
			winGame(updateWord());
		}
		//player looses (runs out of tries)
		if (this.triesLeft == 0) {
			System.out.println("YOU LOSE! Word was \"" + this.word + "\"");
		}
	}
  
	private String updateWord() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.word.length(); i++) {
			if(this.guessedLetters.contains(Character.toString(this.word.charAt(i)))) {
				sb.append(Character.toUpperCase(this.word.charAt(i)));
			} else {
				sb.append("-");
			}
		}
		return sb.toString();	
	}
	
	/**
	 * Returns true if the player's input is a letter which has not been previously 
	 * selected
	 * @param input
	 * @return
	 */
	private boolean inputIsValid(String input) {
		return (Character.isLetter(input.charAt(0)) && !this.guessedLetters.contains(input));
	}
	
	private boolean wordIsGuessed(String guess) {
		return guess.equalsIgnoreCase(this.word);
	}
	
	private void winGame(String word) {
		System.out.println(buildWinGameBoard(word));
		System.out.println("(score to be implemented)");
		System.out.println("(return to main menu to be implemented)");
	}

	private void newGameMenu() {
	    System.out.print(buildNewGameMenu());
	    
	    String input = this.gameScanner.next();
	    switch (input) {
	    case "1":
	    	playSingleGame();
	    	break;
	    case "2":
	    	System.out.println("To be implemented");
	    	break;
	    case "0":
	    	startGame();
	    	break;
		default:
			System.out.println("Invalid input.");
			newGameMenu();
	    }
	}
	
	private void highScoreMenu() {
	    System.out.print(buildHighScoreMenu());
	    
	    String input = this.gameScanner.next();
	    switch (input) {
	    case "1":
	    	System.out.println("To be implemented");
	    	break;
	    case "2":
	    	System.out.println("To be implemented");
	    	break;
	    case "0":
	    	startGame();
	    	break;
		default:
			System.out.println("Invalid input.");
			highScoreMenu();
	    }	    
	}
	
	private String buildStartMenu() {
		StringBuilder menu = new StringBuilder();
	    menu.append("---- Welcome to the world's best Hangman game ever! ----\n\n");
	    menu.append("========== START ===========\n");
	    menu.append("|| 1 - Play a new game    ||\n");
	    menu.append("||                        ||\n");
	    menu.append("|| 2 - View high scores   ||\n");
	    menu.append("||                        ||\n");
	    menu.append("|| 3 - Quit application   ||\n");
	    menu.append("============================\n");
	    menu.append("\nPlease press a key to select an option: ");
	    return menu.toString();
	}

	private String buildNewGameMenu() {
	    StringBuilder menu = new StringBuilder();
	    menu.append("\n");
	    menu.append("========= NEW GAME ==========\n");
	    menu.append("|| 1 - Play a single game  ||\n");
	    menu.append("||                         ||\n");
	    menu.append("|| 2 - Play a 5-word game  ||\n");
	    menu.append("||                         ||\n");
	    menu.append("|| 0 - Back to main menu   ||\n");
	    menu.append("=============================\n");
	    menu.append("\nPlease press a key to select an option: ");
	    return menu.toString();
	}
	
	private String buildHighScoreMenu() {
	    StringBuilder menu = new StringBuilder();
	    menu.append("\n");
	    menu.append("============ HIGH SCORES =============\n");
	    menu.append("|| 1 - Show single game highscores  ||\n");
	    menu.append("||                                  ||\n");
	    menu.append("|| 2 - Show 5-word game highscores  ||\n");
	    menu.append("||                                  ||\n");
	    menu.append("|| 0 - Back to main menu            ||\n");
	    menu.append("======================================\n");
	    menu.append("\nPlease press a key to select an option: ");
	    return menu.toString();
	}
	
	private String buildGameRoundBoard(String word) {
		StringBuilder gameBoard = new StringBuilder();
		gameBoard.append("========================================== SINGLE GAME ===================================\n");
		gameBoard.append("||                  ||                                                                    \n");
		gameBoard.append("||                  || CURRENT WORD: " + word + "                                         \n");
		gameBoard.append("||                  ||                                                                    \n");
		gameBoard.append("||    (hangman)     ||                                                                    \n");
		gameBoard.append("||                  || Previous letters: " + this.guessedLetters.toString() + "           \n");
		gameBoard.append("||                  ||                                                                    \n");
		gameBoard.append("||                  || Number of tries left: " + this.triesLeft + "                       \n");
		gameBoard.append("||                  ||                                                                    \n");
		gameBoard.append("||                  ||                                                                    \n");
		gameBoard.append("==========================================================================================\n");
		return gameBoard.toString();
	}
	
	private String buildWinGameBoard(String word) {
		StringBuilder victoryBoard = new StringBuilder();
		victoryBoard.append("========================================== SINGLE GAME ===================================\n");
		victoryBoard.append("||                  ||                                                                    \n");
		victoryBoard.append("||                  || CURRENT WORD: " + word + "                                         \n");
		victoryBoard.append("||                  ||                                                                    \n");
		victoryBoard.append("||    (hangman)     ||                                                                    \n");
		victoryBoard.append("||                  ||     ********************** YOU WON!!!! **********************      \n");
		victoryBoard.append("||                  ||                                                                    \n");
		victoryBoard.append("||                  || Number of tries left: " + this.triesLeft + "                       \n");
		victoryBoard.append("||                  ||                                                                    \n");
		victoryBoard.append("||                  ||                                                                    \n");
		victoryBoard.append("==========================================================================================\n");
		return victoryBoard.toString();
	}
	
}