import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Score {

	private int score;
	
	public Score(int wordScore) {
		this.score = wordScore;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void onRightTry() {
		this.score += 20;
	}
	
	public void onWrongTry() {
		if (this.score - 20 < 0) {
			this.score = 0;
		} else {
			this.score -= 20;
		}
	}
	
	public void onRightWordGuess() {
		this.score += 100;
	}
	
	public void onWrongWordGuess() {
		if (this.score - 40 < 0) {
			this.score = 0;
		} else {
			this.score -= 40;
		}
	}
	
	public boolean isHighScore() throws IOException {
		File highScoreFile = getHighScoreFile();
		ArrayList<Integer> previousScores = getScoresList(highScoreFile);
		return isTop5Score(previousScores, this.score);
	}
	
	public File getHighScoreFile() throws IOException {
		File file = new File("resources/highscore.txt");
		if (file.isFile()) {
			return file;
		} else {
			file.createNewFile();
			return file;
		}
	}
	
	public ArrayList<Integer> getScoresList(File scoreFile) throws FileNotFoundException {
		ArrayList<Integer> scoreList = new ArrayList<>();
		Scanner scoreScanner = new Scanner(scoreFile);
		while (scoreScanner.hasNextInt()) {
			scoreList.add(scoreScanner.nextInt());
		}
		scoreScanner.close();
		Collections.sort(scoreList);
		return scoreList;
	}
	
	public boolean isTop5Score(ArrayList<Integer> scoreList, int score) {
		if (scoreList.size() < 5) {
			return true;
		} else {
			return score > scoreList.get(4);
		}
	}
	
	public void registerHighScore(String playerName, int score) {
		try {
			PrintWriter scoreWriter = new PrintWriter(getHighScoreFile());
			scoreWriter.print(playerName);
			scoreWriter.println(score);
			scoreWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
