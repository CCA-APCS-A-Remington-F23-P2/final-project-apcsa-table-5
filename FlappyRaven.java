import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

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
        //add code to do stuff if they already exist?
        // if (exists(p1)) {
          
        // }
        // if (exists(p2)) {
          
        // }

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

    public static void main(String[] args, String db) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Flappy Raven!");
        System.out.println("Enter the name for player 1:");
        String player1 = s.next();
        System.out.println("Enter the name for player 2:");
        String player2 = s.next();
        s.close();

        System.out.println("Starting the game...");

        new FlappyRaven(player1, player2, db);
    }
}