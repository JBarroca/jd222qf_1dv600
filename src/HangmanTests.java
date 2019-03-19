import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class HangmanTests {

	@Test
	public void shouldReturnFalseIfNumber() {
		Hangman sut = new Hangman();
		String input = "4";
		ArrayList<String> guessedLetters = new ArrayList<>();
		boolean expected = false;		
		boolean actual = sut.inputIsValid(input, guessedLetters);
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void shouldReturnTrueIfUnguessedLetter() {
		Hangman sut = new Hangman();
		String input = "b";
		ArrayList<String> guessedLetters = new ArrayList<>();
		guessedLetters.add("a");
		guessedLetters.add("c");
		guessedLetters.add("i");
		guessedLetters.add("z");
		boolean expected = true;		
		boolean actual = sut.inputIsValid(input, guessedLetters);
		
		assertEquals(actual, expected);
	}
		
	@Test
	public void shouldNotRevealLettersIfIncorrectlyGuessed() {
		String gameWord = "forest";
		Hangman sut = new Hangman(gameWord, 0);
		ArrayList<String> guessedLetters = new ArrayList<>();
		guessedLetters.add("a");
		guessedLetters.add("u");
		guessedLetters.add("p");
		guessedLetters.add("k");
		guessedLetters.add("y");
		guessedLetters.add("z");
		guessedLetters.add("l");
		String expected = "------";
		String actual = sut.updateWord(gameWord, guessedLetters);
		
		assertEquals(actual, expected);	
	}
	
	@Test
	public void shouldRevealLettersIfCorrectlyGuessed() {
		String gameWord = "rainbow";
		Hangman sut = new Hangman(gameWord, 0);
		ArrayList<String> guessedLetters = new ArrayList<>();
		guessedLetters.add("r"); //correct
		guessedLetters.add("x");
		guessedLetters.add("z");
		guessedLetters.add("n"); //correct
		guessedLetters.add("e");
		guessedLetters.add("u");
		guessedLetters.add("i"); //correct
		String expected = "R-IN---";		
		String actual = sut.updateWord(gameWord, guessedLetters);
		
		assertEquals(actual, expected);	
	}
	
	@Test
	public void shouldAdd20ToScore() {
		Score score = new Score(30);
		score.onRightTry();
		int expected = 50;
		int actual = score.getScore();
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void shouldDecrease20ToScoreNoNegatives() {
		Score score = new Score(30);
		score.onWrongTry();
		int expected = 30 - 20;
		int actual = score.getScore();
		
		assertEquals(actual, expected);
		
		//testing that score does not become <0
		Score score2 = new Score(5);
		score2.onWrongTry();
		int expected2 = 0;
		int actual2 = score2.getScore();
		
		assertEquals(actual2, expected2);
	}
	
	@Test
	public void shouldAddBonusToScore() {
		Score score = new Score(50);
		int triesLeft = 5;
		score.onRightWordGuess(triesLeft);
		int expected = 50 + (100 + 5*10);
		int actual = score.getScore();
		
		assertEquals(actual, expected);	
	}
	
	@Test
	public void shouldDecrease40ToScoreNoNegatives() {
		Score score = new Score(100);
		score.onWrongWordGuess();
		int expected = 100 - 40;
		int actual = score.getScore();
		
		assertEquals(actual, expected);
		
		//testing that score does not become <0
		Score score2 = new Score(20);
		score2.onWrongWordGuess();
		int expected2 = 0;
		int actual2 = score2.getScore();
		
		assertEquals(actual2, expected2);
	}
	
	@Test
	public void shouldReturnTrueIfNewHighScore() {
		ArrayList<Integer> mockScores = new ArrayList<>();
		
		//testing with no previous scores (should always be true)
		Score testScore = new Score(1);
		boolean expected = true;
		boolean actual = testScore.isTop5Score(mockScores, testScore.getScore());
		assertEquals(actual, expected);
		
		//testing with <5 previous scores (should always be true)
		mockScores.add(300);
		mockScores.add(320);
		mockScores.add(340);
		mockScores.add(360);
			//method Score.isTop5Score() receives a descending-sorted ArrayList
		Collections.sort(mockScores);
		Collections.reverse(mockScores);

		Score testScore2 = new Score(50);
		boolean expected2 = true;
		boolean actual2 = testScore2.isTop5Score(mockScores, testScore2.getScore());
		assertEquals(actual2, expected2);
		
		//testing for score higher than every other score		
		mockScores.add(380);
		mockScores.add(400);
		Collections.sort(mockScores);
		Collections.reverse(mockScores);
		
		Score testScore3 = new Score(999);
		boolean expected3 = true;
		boolean actual3 = testScore3.isTop5Score(mockScores, testScore3.getScore());
		assertEquals(actual3, expected3);
		
		//testing for 5th highest score
		Score testScore4 = new Score(330);
		boolean expected4 = true;
		boolean actual4 = testScore4.isTop5Score(mockScores, testScore4.getScore());
		assertEquals(actual4, expected4);
	}
	
	@Test
	public void shouldReturnFalseIfNotNewHighScore() {
		ArrayList<Integer> mockScores = new ArrayList<>();
		mockScores.add(300);
		mockScores.add(320);
		mockScores.add(340);
		mockScores.add(360);
		mockScores.add(380);
		mockScores.add(400);
			//method Score.isTop5Score() receives a descending-sorted ArrayList
		Collections.sort(mockScores);
		Collections.reverse(mockScores);
		
		//testing for a score lower than the 5th highest
		Score testScore = new Score(310);
		boolean expected = false;
		boolean actual = testScore.isTop5Score(mockScores, testScore.getScore());
		assertEquals(actual, expected);
		
		//testing for a score equal to the 5th highest
		Score testScore2 = new Score(320);
		boolean expected2 = false;
		boolean actual2 = testScore2.isTop5Score(mockScores, testScore2.getScore());
		assertEquals(actual2, expected2);
		
	}
}
