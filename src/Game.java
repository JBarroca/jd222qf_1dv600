import java.util.Scanner;

public class Game {

  private String word;
  private String playerName;

  public Game() {
    showStartMenu();
    showHighScoreMenu();
    showNewGameMenu();
  }

  private void showStartMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("---- Welcome to the world's best Hangman game ever! ----\n");
    menu.append("Please press a key to select an option:\n\n");
    menu.append("========== START ===========\n");
    menu.append("|| 1 - Play a new game    ||\n");
    menu.append("||                        ||\n");
    menu.append("|| 2 - View high scores   ||\n");
    menu.append("||                        ||\n");
    menu.append("|| 3 - Quit application   ||\n");
    menu.append("============================\n");
    System.out.println(menu.toString());

    Scanner scan = new Scanner(System.in);
    if (scan.next() == "1") {
      scan.close();
      showNewGameMenu();
    } else if (scan.next() == "2") {
      scan.close();
      showHighScoreMenu();
    } else if (scan.next() == "3") {
      scan.close();
      System.out.println("CLEAR!");
    }    
  }

  private void showNewGameMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("Please press a key to select an option:\n\n");
    menu.append("========= NEW GAME ==========\n");
    menu.append("|| 1 - Play a single game  ||\n");
    menu.append("||                         ||\n");
    menu.append("|| 2 - Play a 5-word game  ||\n");
    menu.append("||                         ||\n");
    menu.append("|| 0 - Back to main menu   ||\n");
    menu.append("=============================\n");
    System.out.println(menu.toString());
  }

  private void showHighScoreMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("Please press a key to select an option:\n\n");
    menu.append("============ HIGH SCORES =============\n");
    menu.append("|| 1 - Show single game highscores  ||\n");
    menu.append("||                                  ||\n");
    menu.append("|| 2 - Show 5-word game highscores  ||\n");
    menu.append("||                                  ||\n");
    menu.append("|| 0 - Back to main menu            ||\n");
    menu.append("======================================\n");
    System.out.println(menu.toString());
  }


}