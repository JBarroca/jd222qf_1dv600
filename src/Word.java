import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Word {
  
  private String word;
  private int difficultyScore;

  public Word() {
    try {
      this.word = getWord();
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    System.out.println(this.word);
  }
  
  private String getWord() throws FileNotFoundException {
    Scanner scan = new Scanner(new File("resources/nounsEnglish.txt"));
    ArrayList<String> words = new ArrayList<>();

    while(scan.hasNextLine()) {
      words.add(scan.nextLine());
    }
    scan.close();
    
    Random rand = new Random();
    int randomIndex = rand.nextInt(words.size());
    
    return words.get(randomIndex);
  }

}
