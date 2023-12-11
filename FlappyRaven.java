import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FlappyRaven extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 450;
    private String p1;
    private String p2;
    private File db;

    public FlappyRaven(String player1Name, String player2Name, File db) {
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


        Sky theGame = new Sky();
        theGame.setFocusable(true);

        getContentPane().add(theGame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //helpers
    public boolean exists(String p) {
        Scanner data;
        try {
            data = new Scanner(db);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (data.hasNextLine()) {
            if (data.nextLine().equals(p)) {
                data.close();
                return true;
            }
        }
        data.close();
        return false;
    }

    private void newPlayer(String p) {
        Scanner data;
        try {
            data = new Scanner(db);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // add code to add playr and a 0 high score here
        data.close();
    }

    public static void main(String[] args, File db) {
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