import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	public static void showHighScoresSingle() {
		Map<String, Integer> highScores = new HashMap<>();
		try {
			highScores = Score.readHighScoreFile(Score.getHighScoreFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] names = new String[5];
		int[] scores = new int[5];
		
		int i = 0;
		for(Map.Entry<String, Integer> entry:highScores.entrySet()) {
			names[i] = entry.getKey();
			scores[i] = entry.getValue();
			i++;
			if (i == 5) {
				break;
			}
		}
		
		StringBuilder table = new StringBuilder();
		table.append("\n");
		table.append("========== HIGH SCORES (single games) ==========\n");
		table.append("||    -- POINTS --  -- PLAYER --                       \n");
		table.append("||                                                     \n");
		table.append("|| 1 - " + scores[0] + " points    - " + names[0] + "  \n");
		table.append("|| 2 - " + scores[1] + " points    - " + names[1] + "  \n");
		table.append("|| 3 - " + scores[2] + " points    - " + names[2] + "  \n");
		table.append("|| 4 - " + scores[3] + " points    - " + names[3] + "  \n");
		table.append("|| 5 - " + scores[4] + " points    - " + names[4] + "  \n");
		table.append("||                                                     \n");
		table.append("================================================\n");
		System.out.println(fillVoidSpace() + table.toString());
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
