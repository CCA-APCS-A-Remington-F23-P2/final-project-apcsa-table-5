import javax.swing.*;
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.Component;
import java.util.Scanner;
import java.io.File;
import java.io.*;

public class FlappyRaven extends JFrame
{

  private static final int WIDTH = 600;
  private static final int HEIGHT = 450; 
  private String p1;
  private String p2;

  public FlappyRaven(String player1Name, String player2Name)
  {
    super("FLAPPYRAVEN");
    setSize(WIDTH,HEIGHT);

    p1 = player1Name;
    p2 = player2Name;

    if(!exists(p1)){
      newPlayer(p1);
    }
    if(!exists(p2)){
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
  public static boolean exists(String p){
    Scanner data = new Scanner(new File("Database.txt"));
    while(data.hasNextLine()){
      if(data.nextLine().equals(p)){
        data.close();
        return true;
      }
    }
    data.close();
    return false;
  }

  public static newPlayer(String p){
    Scanner data = new Scanner(new File("Database.txt"));
    // add code to add playr and a 0 high score here
    data.close();
  }

  public static void main( String args[] )
  {
    Scanner s = new Scanner(System.in);
    System.out.println("Welcome to Flappy Bird!!");
    System.out.println("Enter the name for player 1:");
    String player1 = s.next();
    System.out.println("Enter the name for player 1::");
    String player2 = s.next();
    S.close();
    
    System.out.println("Starting the game...");

      FlappyRaven run = new FlappyRaven(player1, player2);
  }
}