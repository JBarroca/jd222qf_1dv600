import java.util.ArrayList;

public class Menu {

	public Menu() {}
	
	public static void showGameRound(String gameWord, ArrayList<String> guessedLetters, int triesLeft, String message) {
		StringBuilder gameBoard = new StringBuilder();
		gameBoard.append("\n");
		gameBoard.append("========================================== SINGLE GAME ===================================\n");
		gameBoard.append("||                  ||                                                                    \n");
		gameBoard.append("||                  || CURRENT WORD: " + gameWord + "                                     \n");
		gameBoard.append("||                  ||                                                                    \n");
		gameBoard.append("||                  ||       -- " + message + " --                                        \n");
		gameBoard.append("||    (hangman)     ||                                                                    \n");
		gameBoard.append("||                  || Previous letters: " + guessedLetters.toString() + "                \n");
		gameBoard.append("||                  ||                                                                    \n");
		gameBoard.append("||                  || Number of tries left: " + triesLeft + "                            \n");
		gameBoard.append("||                  ||                                                                    \n");
		gameBoard.append("==========================================================================================");
		System.out.println(fillVoidSpace() + gameBoard.toString());
	}
	
	public static void showWinGame(String gameWord, int triesLeft, Score score, boolean isHighScore) {
		String highScoreMessage = "";
		if (isHighScore) {
			highScoreMessage = "NEW HIGHSCORE!";
		}
		StringBuilder victoryBoard = new StringBuilder();
		victoryBoard.append("\n");
		victoryBoard.append("========================================== SINGLE GAME ===================================\n");
		victoryBoard.append("||                  ||                                                                    \n");
		victoryBoard.append("||                  ||     ********************** YOU WON!!!! **********************      \n");
		victoryBoard.append("||                  ||                                                                    \n");
		victoryBoard.append("||                  || You found " + gameWord + " in " + (10 - triesLeft) + " tries.      \n");
		victoryBoard.append("||    (hangman)     ||                                                                    \n");
		victoryBoard.append("||                  || Your score: " + score.getScore() + "                               \n");
		victoryBoard.append("||                  ||                                                                    \n");
		victoryBoard.append("||                  || " + highScoreMessage + "                                           \n");
		victoryBoard.append("||                  ||                                                                    \n");
		victoryBoard.append("==========================================================================================\n");
		System.out.println(fillVoidSpace() + victoryBoard.toString());
	}
	
	public static void showLoseGame(String gameWord) {
		StringBuilder loseBoard = new StringBuilder();
		loseBoard.append("\n");
		loseBoard.append("========================================== SINGLE GAME ===================================\n");
		loseBoard.append("||                  ||                                                                    \n");
		loseBoard.append("||                  ||     ********************** YOU LOSE :( **********************      \n");
		loseBoard.append("||                  ||                                                                    \n");
		loseBoard.append("||                  || Word was " + gameWord.toUpperCase() + "                            \n");
		loseBoard.append("||    (hangman)     ||                                                                    \n");
		loseBoard.append("||                  ||                                                                    \n");
		loseBoard.append("||                  ||                                                                    \n");
		loseBoard.append("||                  ||                                                                    \n");
		loseBoard.append("||                  ||                                                                    \n");
		loseBoard.append("==========================================================================================\n");
		System.out.println(fillVoidSpace() + loseBoard.toString());
	}
	
	public static void showStartMenu() {
		StringBuilder menu = new StringBuilder();
		menu.append("---- Welcome to the world's best Hangman game ever! ----\n");
		menu.append("\n");
	    menu.append("========== START ===========\n");
	    menu.append("|| 1 - Play a new game    ||\n");
	    menu.append("||                        ||\n");
	    menu.append("|| 2 - View high scores   ||\n");
	    menu.append("||                        ||\n");
	    menu.append("|| 0 - Quit application   ||\n");
	    menu.append("============================\n");
	    System.out.println(fillVoidSpace() + menu.toString());
	}
	
	public static void showNewGameMenu() {
	    StringBuilder menu = new StringBuilder();
	    menu.append("\n");
	    menu.append("========= NEW GAME ==========\n");
	    menu.append("|| 1 - Play a single game  ||\n");
	    menu.append("||                         ||\n");
	    menu.append("|| 2 - Play a 5-word game  ||\n");
	    menu.append("||                         ||\n");
	    menu.append("|| 0 - Back to main menu   ||\n");
	    menu.append("=============================\n");
	    System.out.println(fillVoidSpace() + menu.toString());
	}
	
	public static void showHighScoreMenu() {
	    StringBuilder menu = new StringBuilder();
	    menu.append("\n");
	    menu.append("============ HIGH SCORES =============\n");
	    menu.append("|| 1 - Show single game highscores  ||\n");
	    menu.append("||                                  ||\n");
	    menu.append("|| 2 - Show 5-word game highscores  ||\n");
	    menu.append("||                                  ||\n");
	    menu.append("|| 0 - Back to main menu            ||\n");
	    menu.append("======================================\n");
	    System.out.println(fillVoidSpace() + menu.toString());
	}
	
	public static String fillVoidSpace() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 50; i++) {
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
}
