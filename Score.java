import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
  
public class Score
{
  private int xPos;
  private int yPos;

  private int score;


  private Color color;

  public Score(int x, int y, Color col)
  {
    xPos = x;
    yPos = y;
    color = col;
    score = 0;

  }
  //add the other set methods Hint: check the Locateable interface


  public void setColor(Color col)
  {
    color = col;

  }

  public void setPos( int x, int y){
    xPos = x;
    yPos = y;
  }
  public void setX( int x ){
    xPos = x;
  }
  public void setY( int y ){
    yPos = y;
  }
  public void setScore(int s){
    score = s;
  }

  public void draw(Graphics window)
  {
    //uncomment after you write the set and get methods
    // window.setColor(Color.black);
    // window.fillRect(xPos,yPos-10,xPos+100,yPos+50);
    
    window.setFont(new Font( "SansSerif", Font.PLAIN, 30 ));

    window.setColor(color);
    window.drawString("" + score, xPos,yPos);
  }




  public boolean equals(Object obj)
  {
    Score other = (Score) obj;
    return (other.getX() == getX() && other.getY() == getY()  && other.getColor() == getColor());

  }   

  //add the other get methods Hint: check the Locateable interface
  public int getX(){
    return xPos;
  }
  public int getY(){
    return yPos;
  }
  public Color getColor(){
    return color;
  }
  public int getScore(){
    return score;
  }

  //add a toString() method  - x , y , width, height, color
  public String toString(){
    return getX() + " " + getY() + " "  + getColor();
  }
}