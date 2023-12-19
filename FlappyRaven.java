import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.nio.file.Files;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FlappyRaven extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 450;
    private String p1;
    private String p2;
    private String db;

    public FlappyRaven(String player1Name, String player2Name, String db) {
        super("Flappy Raven");
        setSize(WIDTH, HEIGHT);

        p1 = player1Name;
        p2 = player2Name;
        this.db = db;

        if (!exists(p1)) {
            newPlayer(p1);
        }
        if (!exists(p2)) {
            newPlayer(p2);
        }
        Sky theGame = new Sky(player1Name, player2Name);
        theGame.setFocusable(true);

        getContentPane().add(theGame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //helpers
    public boolean exists(String playerName) {
      BufferedReader reader;

      try {
        reader = new BufferedReader(new FileReader("Database.txt"));
        String line = reader.readLine();

        while (line != null) {
          if(line.equals(playerName)){
            return true;
          }
          // read next line
          line = reader.readLine() ;
        }

        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return false;
    }

    private void newPlayer(String playerName) {
        try {
          FileWriter filewriter = new FileWriter(db, true);
          filewriter.write(playerName + "\n" + 0 + "\n");
          filewriter.close();
        } catch (IOException e) {
          System.out.println("An error occurred.");
        }
    }



    // Database methods
  public static void updateDatabase(String playerName, Score score) {

      try {
          Path FILE_PATH = Paths.get("Database.txt");
          ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH));

          for (int i = 0; i < fileContent.size(); i++) {
              if (fileContent.get(i).contains(playerName)) {
                  String playerScoreStr = fileContent.get(i + 1);

                  int playerScore = Integer.parseInt(playerScoreStr);
                  if (playerScore < score.getScore()) {
                      fileContent.set(i + 1, "" + score.getScore());
                      break;
                  }
              }
          }
        System.out.println(getRankPName(1));
        System.out.println(getPlayerScore(getRankPName(1)));

          Files.write(FILE_PATH, fileContent);
      } catch (Exception e) {
          System.out.println("Error in writing");
      }


  }


  //Returns player of specifiied rank - rank 1 being highest
  public static String getRankPName(int rank) {
    try {
        Path FILE_PATH = Paths.get("Database.txt");
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH));

        ArrayList<String> rankedPlayers = new ArrayList<>();
        // ArrayList<Integer> rankedScores = new ArrayList<>();


      for (int i = fileContent.size()-1; i > 1; i--) {
        // Code to loop through fileContent, find highest score, add to rankedPlayers and rankedscores and then remove from fileContent
        int highestScore = 0;
        int highestScoreIndex = 1;
        for (int j = 1; j < fileContent.size(); j+=2) {
          int score = Integer.parseInt(fileContent.get(j));
          if (score > highestScore) {
            highestScore = score;
            highestScoreIndex = j;
          }
        }
        rankedPlayers.add(fileContent.remove(highestScoreIndex-1));
        // rankedScores.add(fileContent.remove(highestScoreIndex));
      }

      return rankedPlayers.get(rank-1);

    } catch (Exception e) {
        System.out.println("Error in writing");
        return "Error";
    }
  }

  public static int getPlayerScore(String playerName) {
      try {
        Path FILE_PATH = Paths.get("Database.txt");
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH));

        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).contains(playerName)) {
                String playerScoreStr = fileContent.get(i+1);

                int playerScore = Integer.parseInt(playerScoreStr);
                return playerScore;
            }
        }
        return -1;

      } catch (Exception e) {
          System.out.println("Error in writing");
        return -1;
    }
  }




    public static void main(String[] args, String db) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Flappy Raven!");
        System.out.println("Enter the name for player 1 (BLACK, P to jump):");
        String player1 = s.next();
        System.out.println("Enter the name for player 2 (RED, Q to jump):");
        String player2 = s.next();
        s.close();

        System.out.println("Starting the game...");

        new FlappyRaven(player1, player2, db);
    }
}

