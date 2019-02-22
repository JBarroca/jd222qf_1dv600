import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class Word {
  
  private String word;
  private int bonusPoints;

  public Word() {
    try {
      this.word = obtainWord();
      this.bonusPoints = calculateBonusPoints();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    /*
    System.out.println("word: " + this.word);
    System.out.println("index: " + this.bonusPoints);
    */
  }
  
  /**
   * Returns this object's word.
   * @return  this word
   */
  public String getWord() {
    return this.word;
  }

  /**
   * Returns this word's difficulty bonus points
   * @return  this word's bonus points
   */
  public int getBonusPoints() {
    return this.bonusPoints;
  }

  /**
   * Fetches a random String from a list of nouns in the English language to
   * be used as a word in the game.
   * @return  a String from a list of words
   * @throws FileNotFoundException  if no word list .txt file is found
   */
  private String obtainWord() throws FileNotFoundException {
    Scanner scan = new Scanner(new File("jd222qf_1dv600/resources/nounsEnglish.txt"));
    ArrayList<String> words = new ArrayList<>();
    while(scan.hasNextLine()) {
      words.add(scan.nextLine());
    }
    scan.close();
    int randomIndex = new Random().nextInt(words.size());
    return words.get(randomIndex);
  }

  /**
   * Calculates this word's difficulty bonus points, inversely proportional to
   * each letter's frequency in the english language. These frequencies are stored
   * in .txt files (one for first letters, another for remaining)
   * @return  this word's bonus points
   * @throws FileNotFoundException  if the txt files are not found
   */
  private int calculateBonusPoints() throws FileNotFoundException {
    //creating map of points for first letters
    Scanner scanFirstLetter = new Scanner(new File("jd222qf_1dv600/resources/firstLetterFreq.txt"));
    Map<String, Integer> firstLetterPoints = new Hashtable<String, Integer>();
    while(scanFirstLetter.hasNextLine()) {
      firstLetterPoints.put(scanFirstLetter.next(), (int) Math.round(1.0 / scanFirstLetter.nextDouble()*100));
    }
    scanFirstLetter.close();
    /*
    System.out.println("First letters' points:");
    for (Object key : firstLetterPoints.keySet()) {
      System.out.println(key.toString() + " : " + firstLetterPoints.get(key).toString());
    }
    */
    
    //creating map of points for remaining letters
    Scanner scanOthers = new Scanner(new File("jd222qf_1dv600/resources/letterFreq.txt"));
    Map<String, Integer> otherLetterPoints = new Hashtable<String, Integer>();
    while(scanOthers.hasNextLine()) {
      otherLetterPoints.put(scanOthers.next(), (int) Math.round(1.0 / scanOthers.nextDouble() * 100));
    }
    scanOthers.close();
    /*
    System.out.println("Other letters' points:");
    for (Object key : otherLetterPoints.keySet()) {
      System.out.println(key.toString() + " : " + otherLetterPoints.get(key).toString());
    }
    */

    char[] wordChars = this.word.toCharArray();
    int wordPoints = 0;
    for(int i = 0; i < wordChars.length; i++) {
      if (i == 0) {
        wordPoints += ((Number)firstLetterPoints.get(String.valueOf(wordChars[i]))).intValue();
      } else {
        wordPoints += ((Number)otherLetterPoints.get(String.valueOf(wordChars[i]))).intValue();
      }
    }
    return wordPoints;
  }

}
