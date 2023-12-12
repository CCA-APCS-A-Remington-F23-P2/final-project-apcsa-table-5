import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FlappyRaven extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 450;
    private String p1;
    private String p2;
    private String db = "Database.txt";

    public FlappyRaven(String player1Name, String player2Name, String db) {
        super("FLAPPYRAVEN");
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

        Sky theGame = new Sky();
        theGame.setFocusable(true);

        getContentPane().add(theGame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //helpers
    public boolean exists(String playerName) {
      Scanner scanner = new Scanner(db);
        while(scanner.hasNextLine()) {
          if(scanner.nextLine().contains(playerName)) {
            scanner.close();
              return true;
            }
            scanner.close();
            return false;
          }
      scanner.close();
      return false;
    }

    private void newPlayer(String playerName) {
        try {
          FileWriter filewriter = new FileWriter(db, true);
          filewriter.write(playerName + " " + 0 + "\n");
          filewriter.close();
        } catch (IOException e) {
          System.out.println("An error occurred.");
        }
    }

    public static void main(String[] args, String db) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Flappy Bird!!");
        System.out.println("Enter the name for player 1:");
        String player1 = s.next();
        System.out.println("Enter the name for player 1::");
        String player2 = s.next();
        s.close();

        System.out.println("Starting the game...");

        new FlappyRaven(player1, player2, db);
    }
}