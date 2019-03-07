import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class HangmanTests {

	@Test
	public void shouldReturnFalseIfNumber() {
		Hangman sut = new Hangman();
		String input = "4";
		ArrayList<String> guessedLetters = new ArrayList<>();
		boolean expected = false;
		
		//exercising sut
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
		
		//exercising sut
		boolean actual = sut.inputIsValid(input, guessedLetters);
		
		assertEquals(actual, expected);
	}
		
	@Test
	public void shouldNotRevealLettersIfIncorrectlyGuessed() {
		String gameWord = "forest";
		Hangman sut = new Hangman(gameWord);
		ArrayList<String> guessedLetters = new ArrayList<>();
		guessedLetters.add("a");
		guessedLetters.add("u");
		guessedLetters.add("p");
		guessedLetters.add("k");
		guessedLetters.add("y");
		guessedLetters.add("z");
		guessedLetters.add("l");
		
		String expected = "------";
		
		//exercising sut
		String actual = sut.updateWord(gameWord, guessedLetters);
		
		assertEquals(actual, expected);	
	}
	
	@Test
	public void shouldRevealLettersIfCorrectlyGuessed() {
		String gameWord = "rainbow";
		Hangman sut = new Hangman(gameWord);
		ArrayList<String> guessedLetters = new ArrayList<>();
		guessedLetters.add("r"); //correct
		guessedLetters.add("x");
		guessedLetters.add("z");
		guessedLetters.add("n"); //correct
		guessedLetters.add("e");
		guessedLetters.add("u");
		guessedLetters.add("i"); //correct
		
		String expected = "R-IN---";
		
		//exercising sut
		String actual = sut.updateWord(gameWord, guessedLetters);
		
		assertEquals(actual, expected);	
	}
}
