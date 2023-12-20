import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Round {
    private int xPos;
    private int yPos;
    private int round;

    private Color color;

  

    public Round(int x, int y, Color col) {
        xPos = x;
        yPos = y;
        color = col;
        round = 2;

    }
    //add the other set methods Hint: check the Locateable interface


    public void setColor(Color col) {
        color = col;

    }

    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }

    public void setRound(int s) {
        round = s;
    }



    public void draw(Graphics window) {
      window.setFont(new Font("SansSerif", Font.PLAIN, 30));
      window.setColor(Color.BLACK);
      window.drawString("Round " + round, 250, 150);
      window.setFont(new Font("SansSerif", Font.PLAIN, 20));
      window.drawString("Press SPACE to start", 210, 200);
    }


  

  public void drawEnd(Graphics window, int score1, int score2){
    window.setFont(new Font("SansSerif", Font.PLAIN, 30));
    window.setColor(Color.BLACK);
    window.drawString("Game Over", 230, 50);
    window.setFont(new Font("SansSerif", Font.PLAIN, 20));
    window.drawString("Press SPACE to restart", 210, 400);
    window.setFont(new Font("SansSerif", Font.PLAIN, 30));
    if(score1 > score2){
      window.drawString("Player 1 Wins!", 210, 150);
    }
    else if(score1 < score2){
      window.drawString("Player 2 Wins!", 210, 150);
    }
    else{
      window.drawString("Tie!", 280, 150);
    }

    String player1 = FlappyRaven.getRankPName(1);
    String player2 = FlappyRaven.getRankPName(2);
    String player3 = FlappyRaven.getRankPName(3);

    window.setFont(new Font("SansSerif", Font.PLAIN, 20));
    window.drawString("LEADERBOARD", 250,200);
    window.drawString("Rank    Name    Score",210,250);
    window.drawString("   1        " + player1,210,275);
    window.drawString(" " + FlappyRaven.getPlayerScore(player1),385,275);
    window.drawString("   2        " + player2,210,300);
    window.drawString(" " + FlappyRaven.getPlayerScore(player2),385,300);
    window.drawString("   3        " + player3,210,325);
    window.drawString(" " + FlappyRaven.getPlayerScore(player3),385,325);
    
  }




  
    public boolean equals(Object obj) {
        Round other = (Round) obj;
        return (other.getX() == getX() && other.getY() == getY() && other.getColor() == getColor());

    }

    

    //add the other get methods Hint: check the Locateable interface
    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public Color getColor() {
        return color;
    }

    public int getRound() {
        return round;
    }

    public void reset() {
        setRound(2);

    }

    //add a toString() method  - x , y , width, height, color
    public String toString() {
        return getX() + " " + getY() + " " + getColor();
    }
}