import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Font;

public class Sky extends Canvas implements KeyListener, Runnable {
    private boolean[] keys;
    private BufferedImage back;
    private Image background;

    private Raven ravenBlack;
    private Raven ravenRed;

    private boolean canPressQ = true;
    private boolean canPressP = true;

    private boolean playing = true;

    private Score redScore;
    private Score blackScore;

    private int roundCount;


    private ArrayList<Pipes> pipes;

    public Sky() {
        keys = new boolean[3];
        pipes = new ArrayList<>();
        pipes.add(new Pipes(1000, 0));
        pipes.add(new Pipes(1350, 0));

        ravenBlack = new Raven(150, 200, 60, 60, "raven1.png");
        ravenRed = new Raven(50, 200, 60, 60, "raven2.png");

        redScore = new Score(150, 50, Color.RED);
        blackScore = new Score(450, 50, Color.BLACK);

        roundCount = 1;

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);

        try {
            URL url = getClass().getResource("bg.png");
            background = ImageIO.read(url);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // paint functions

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        if (playing) {
            // set up the double buffering to make the game animation nice and smooth
            Graphics2D twoDGraph = (Graphics2D) window;

            // take a snap shop of the current screen and same it as an image
            // that is the exact same width and height as the current screen
            if (back == null)
                back = (BufferedImage) (createImage(getWidth(), getHeight()));


            // create a graphics reference to the back ground image
            // we will draw all changes on the background image
            Graphics graphToBack = back.createGraphics();

            graphToBack.drawImage(background, 0, 0, getWidth(), getHeight(), null);


            for (Pipes pipe : pipes) {
                pipe.draw(graphToBack);
                pipe.move("LEFT");
            }

            ravenBlack.draw(graphToBack);
            ravenRed.draw(graphToBack);

            if (ravenRed.isHit(pipes)) ravenRed.setDead(true);
            if (ravenBlack.isHit(pipes)) ravenBlack.setDead(true);

            if (keys[0]) {
                if (canPressQ && !ravenRed.isDead()) {
                    ravenRed.flap();
                    canPressQ = false;
                }
            }


        

            if (keys[1]) {
                if (canPressP && !ravenBlack.isDead()) {
                    ravenBlack.flap();
                    canPressP = false;
                }
            }

            //scoring
            for (Pipes pipe : pipes) {
                if((pipe.getYCenter()+pipe.getPipeGap() > ravenBlack.getY() && pipe.getYCenter()-pipe.getPipeGap() < ravenBlack.getY()) 
                  && (pipe.getXCenter() < ravenBlack.getX() && pipe.getXCenter() + 2 > ravenBlack.getX())) {
                  blackScore.setScore(blackScore.getScore() + 1);
                  }

                if((pipe.getYCenter()+pipe.getPipeGap() > ravenRed.getY() && pipe.getYCenter()-pipe.getPipeGap() < ravenRed.getY()) 
                  && (pipe.getXCenter() < ravenRed.getX() && pipe.getXCenter() + 2 > ravenRed.getX())) {
                  redScore.setScore(redScore.getScore() + 1);
                  }
            }


            

            ravenBlack.move();
            ravenRed.move();

            redScore.draw(graphToBack);
            blackScore.draw(graphToBack);


            twoDGraph.drawImage(back, null, 0, 0);


            if(ravenBlack.isDead() && ravenRed.isDead() && playing){
              playing = false;
              if(roundCount<3){
              

                window.setFont(new Font( "SansSerif", Font.PLAIN, 30 ));
                window.setColor(Color.BLACK);
                window.drawString("Round " + roundCount, 250, 150);
                window.setFont(new Font( "SansSerif", Font.PLAIN, 20 ));
                window.drawString("Press SPACE to start", 200, 200);
              }
              else{
                window.setFont(new Font( "SansSerif", Font.PLAIN, 30 ));
                window.setColor(Color.BLACK);
                window.drawString("Game Over", 250, 150);
                window.setFont(new Font( "SansSerif", Font.PLAIN, 20 ));
                window.drawString("Press SPACE to restart", 200, 200);
              }
            }
        }

    }


    public void resetAll(){
      ravenBlack.reset();
      ravenRed.reset();
      ravenBlack.setDead(false);
      ravenRed.setDead(false);
      for(Pipes pipe : pipes){
        pipe.reset();
      }
    }

    public void hardReset(){
      ravenBlack.reset();
      ravenRed.reset();
      ravenBlack.setDead(false);
      ravenRed.setDead(false);
      for(Pipes pipe : pipes){
        pipe.reset();
      }
      blackScore.setScore(0);
      redScore.setScore(0);
      roundCount = 1;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[2] = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            keys[0] = false;
            canPressQ = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            keys[1] = false;
            canPressP = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[2] = false;
            playing = true;
            if(roundCount == 3){
              hardReset();
            }
            else{
              resetAll();
            }
            roundCount++;
        }
    }

    public void keyTyped(KeyEvent e) {
        // no code needed here
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}