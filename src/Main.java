import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Score scr = new Score(250);
		System.out.println(scr.getScore());
		scr.onRightTry();
		System.out.println(scr.getScore());
		scr.onRightWordGuess();
		System.out.println(scr.getScore());
		
		
		File scoreFile = scr.getHighScoreFile();
	
		System.out.println(scr.isHighScore());
		
		scr.registerHighScore("tobias", scr.getScore());
		
		/*
		Hangman game = new Hangman();
		game.startProgram();
		*/
	}

}
