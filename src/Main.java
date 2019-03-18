import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		/*
		Score scr = new Score(2);
					
		Score.registerHighScore("tobias", scr.getScore());
		Score.registerHighScore("jaquim", 315);
		Score.registerHighScore("akjh", 1091);
		Score.registerHighScore("buuu", 1);
		Score.registerHighScore("labu", 163);
		Score.registerHighScore("lakshmi", 999);
		
		Score.readHighScoreFile(Score.getHighScoreFile());
		
		System.out.println(scr.isHighScore());
		*/
		
		Hangman game = new Hangman();
		game.startProgram();
	}

}
