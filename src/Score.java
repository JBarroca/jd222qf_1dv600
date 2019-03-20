import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Score {

	private int score;
	
	public Score(int wordScore) {
		this.score = wordScore;
	}
	
	/**
	 * Returns the numerical value associated with this Score
	 * @return	integer representing the current Score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Adds 20 to the current score after a correct guess.
	 */
	public void onRightTry() {
		this.score += 20;
	}
	
	/**
	 * Subtracts 20 to the current score after a wrong guess. If the score is
	 * less than 20, the score becomes 0.
	 */
	public void onWrongTry() {
		if (this.score - 20 < 0) {
			this.score = 0;
		} else {
			this.score -= 20;
		}
	}
	
	/**
	 * After the player correctly guesses the entire hidden word, adds a bonus to
	 * the score of 100 plus 10 points for each remaining try.
	 * @param triesLeft		the number of tries remaining in the game
	 */
	public void onRightWordGuess(int triesLeft) {
		this.score += (100 + triesLeft*10);
	}
	
	/**
	 * After the player unsuccessfully tries to guess the entire hidden word,
	 * subtracts 40 from the current score. If it's less than 40, becomes 0.
	 */
	public void onWrongWordGuess() {
		if (this.score - 40 < 0) {
			this.score = 0;
		} else {
			this.score -= 40;
		}
	}
	
	/**
	 * Returns true if the current game score is on the top 5 highest scores.
	 * @return	true if the current game score is on the top 5 highest scores
	 * @throws IOException
	 */
	public boolean isHighScore() throws IOException {
		File highScoreFile = getHighScoreFile();
		ArrayList<Integer> previousScores = getScoresList(highScoreFile);
		return isTop5Score(previousScores, this.score);
	}
	
	/**
	 * If a highscores file already exists, returns a File object referencing it.
	 * If none is found, creates the file and returns the File object referencing it.
	 * @return	File object referencing the high scores text file.
	 * @throws IOException
	 */
	public static File getHighScoreFile() throws IOException {
		File file = new File("resources/highscore.txt");
		if (file.exists()) {
			return file;
		} else {
			file.createNewFile();
			return file;
		}
	}
	
	/**
	 * Returns a sorted ArrayList containing every previous score registered
	 * @param scoreFile	File object referencing the highscores text file
	 * @return	a sorted ArrayList containing every previous score	
	 * @throws FileNotFoundException	if no high scores text file is found
	 */
	public ArrayList<Integer> getScoresList(File scoreFile) throws FileNotFoundException {
		ArrayList<Integer> scoreList = new ArrayList<>();
		
		Map<String, Integer> scoresMap = readHighScoreFile(scoreFile);
		for(Map.Entry<String, Integer> entry : scoresMap.entrySet()) {
			scoreList.add(entry.getValue());
		}
		System.out.println("scoreList: " + scoreList.toString());
		return scoreList;
	}
	
	/**
	 * Returns true if the current score is, at least, the 5th highest recorded.
	 * @param scoreList	a sorted ArrayList containing every previous score
	 * @param score		the current game score
	 * @return			true if <code>score</code> is in the top 5 highest scores
	 */
	public boolean isTop5Score(ArrayList<Integer> scoreList, int score) {
		if (scoreList.size() < 5) {
			return true;
		} else {
			return score > scoreList.get(4);
		}
	}
	
	/**
	 * Enters the current score in the high scores file by writing the player's
	 * name and current game score.
	 * @param playerName	the Player's specified name
	 * @param score			the score to be registered
	 */
	public static void registerHighScore(String playerName, Score score) {
		try {			
			PrintWriter scoreWriter = new PrintWriter(new BufferedWriter(new FileWriter("resources/highscore.txt", true)));
			scoreWriter.print(playerName + " ");
			scoreWriter.println(score.getScore());
			scoreWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Highscore successfully registered as " + playerName + "!");
	}
	
	/**
	 * Creates and returns a Map containing every pair player:score recorded in
	 * the high scores file, sorted according to scores.
	 * @param highScoresFile	File object referencing the high scores text file
	 * @return	a Map (player:score), sorted according to scores
	 */
	public static Map<String, Integer> readHighScoreFile(File highScoresFile) {
		HashMap<String, Integer> scoresMap1 = new HashMap<String, Integer>();
		
		try {
			Scanner scoresScanner = new Scanner(highScoresFile);
			while(scoresScanner.hasNext()) {
				scoresMap1.put(scoresScanner.next(), scoresScanner.nextInt());
			}
			scoresScanner.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// sorting the Map according to scores 
		Map<String, Integer> scoresMapSorted = sortByScore(scoresMap1);
	
		// print the sorted map
        for (Map.Entry<String, Integer> en : scoresMapSorted.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 
		return scoresMapSorted;
	}
	
    
	/**
	 * Sorts a map containing (player:score) pairs according to score
	 * adapted from: https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/ 
	 * @param hm	The hashmap to be sorted
	 * @return		A sorted HashMap of players:scores
	 */
	public static HashMap<String, Integer> sortByScore(HashMap<String, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
	
}
