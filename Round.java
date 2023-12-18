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


  

    public void drawEnd(Graphics window){
      window.setFont(new Font("SansSerif", Font.PLAIN, 30));
      window.setColor(Color.BLACK);
      window.drawString("Game Over", 230, 150);
      window.setFont(new Font("SansSerif", Font.PLAIN, 20));
      window.drawString("Press SPACE to restart", 200, 200);
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