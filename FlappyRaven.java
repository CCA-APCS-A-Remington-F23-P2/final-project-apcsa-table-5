import javax.swing.*;
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.Component;
import java.util.Scanner;

public class FlappyRaven extends JFrame
{

    private static final int WIDTH = 600;
    private static final int HEIGHT = 450; 

    public FlappyRaven()
    {
        super("FLAPPYRAVEN");
        setSize(WIDTH,HEIGHT);

        Sky theGame = new Sky();
        theGame.setFocusable(true);

        getContentPane().add(theGame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main( String args[] )
    {
      Scanner s = new Scanner(System.in);
      System.out.println("Welcome to Flappy Bird!!");
      System.out.println("Enter the name for player 1:");
      int player1 = s.next();
      System.out.println("Enter the name for player 1::");
      int player2 = s.next();
      
      System.out.println("Starting the game...");

        FlappyRaven run = new FlappyRaven(player1, player2);
    }
}