import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private String playerName;
	private int playerScore;
	private String word;
	private int triesLeft;
	private ArrayList<String> guessedLetters;
	private Scanner gameScanner = new Scanner(System.in);

	public Game() {
		this.playerScore = 0;
		this.word = new Word().getWord();
		this.triesLeft = 10;
		this.guessedLetters = new ArrayList<>();
		
		showStartMenu();
		this.gameScanner.close();
	}

	private void playSingleGame() {
		while (this.triesLeft > 0 && !wordIsGuessed()) {
			StringBuilder gameBoard = new StringBuilder();
			gameBoard.append("========================================== SINGLE GAME ===================================\n");
			gameBoard.append("||                  ||                                                                    \n");
			gameBoard.append("||                  || CURRENT WORD: " + showWord() + "                                   \n");
			gameBoard.append("||                  ||                                                                    \n");
			gameBoard.append("||    (hangman)     ||                                                                    \n");
			gameBoard.append("||                  || Previous letters: " + this.guessedLetters.toString() + "           \n");
			gameBoard.append("||                  ||                                                                    \n");
			gameBoard.append("||                  || Number of tries left: " + this.triesLeft + "                       \n");
			gameBoard.append("||                  ||                                                                    \n");
			gameBoard.append("||                  ||                                                                    \n");
			gameBoard.append("==========================================================================================\n");
			System.out.println(gameBoard);
			
			System.out.print("Please guess a letter: ");
			String nextLetter = this.gameScanner.next();
			
			if (inputIsValid(nextLetter)) {
				this.guessedLetters.add(nextLetter);
			} else {
				System.out.println("Invalid input. Please enter a single, previously unselected letter.");
				continue;
			}

			if (!this.word.contains(nextLetter)) {
				this.triesLeft--;							
			}
		}
		if (wordIsGuessed()) {
			StringBuilder victoryBoard = new StringBuilder();
			victoryBoard.append("========================================== SINGLE GAME ===================================\n");
			victoryBoard.append("||                  ||                                                                    \n");
			victoryBoard.append("||                  || CURRENT WORD: " + showWord() + "                                   \n");
			victoryBoard.append("||                  ||                                                                    \n");
			victoryBoard.append("||    (hangman)     ||                                                                    \n");
			victoryBoard.append("||                  ||     ********************** YOU WON!!!! **********************      \n");
			victoryBoard.append("||                  ||                                                                    \n");
			victoryBoard.append("||                  || Number of tries left: " + this.triesLeft + "                       \n");
			victoryBoard.append("||                  ||                                                                    \n");
			victoryBoard.append("||                  ||                                                                    \n");
			victoryBoard.append("==========================================================================================\n");
			System.out.println(victoryBoard);
			System.out.println("(score to be implemented)");
		} else {
			System.out.println("YOU LOSE! Word was \"" + this.word + "\"");
		}
	}
  
	private boolean inputIsValid(String input) {
		return (input.length() == 1 && Character.isLetter(input.charAt(0)) && !this.guessedLetters.contains(input));
	}
	
	private String showWord() {
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
  
	private boolean wordIsGuessed() {
		return showWord().equalsIgnoreCase(this.word);
	}
	
  
	private void showStartMenu() {
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
	    System.out.print(menu.toString());
    
	    String input = this.gameScanner.next();
	    switch (input) {
	    case "1":
	    	showNewGameMenu();
	    	break;
	    case "2":
	    	showHighScoreMenu();
	    	break;
	    case "3":
	    	System.out.println("QUIT!");
	    	break;
		default:
			System.out.println("Invalid input.");
			showStartMenu();
	    }
	    
	}

	private void showNewGameMenu() {
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
	    System.out.print(menu.toString());
	    
	    String input = this.gameScanner.next();
	    switch (input) {
	    case "1":
	    	playSingleGame();
	    	break;
	    case "2":
	    	System.out.println("To be implemented");
	    	break;
	    case "0":
	    	showStartMenu();
	    	break;
		default:
			System.out.println("Invalid input.");
			showNewGameMenu();
	    }
	}

	private void showHighScoreMenu() {
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
	    System.out.print(menu.toString());
	    
	    String input = this.gameScanner.next();
	    switch (input) {
	    case "1":
	    	System.out.println("To be implemented");
	    	break;
	    case "2":
	    	System.out.println("To be implemented");
	    	break;
	    case "0":
	    	showStartMenu();
	    	break;
		default:
			System.out.println("Invalid input.");
			showHighScoreMenu();
	    }	    
	}

}