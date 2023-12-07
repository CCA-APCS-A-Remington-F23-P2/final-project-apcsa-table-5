import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class Sky extends Canvas implements KeyListener, Runnable {
    private boolean[] keys;
    private BufferedImage back;
    private Image background;
    private Raven ravenRed;
    private Raven ravenBlack;

    private boolean canPressQ = true;
    private boolean canPressP = true;

    private ArrayList<Pipes> pipes;

    public Sky() {
        keys = new boolean[3];
        pipes = new ArrayList<>();
        pipes.add(new Pipes(400, 0));
        pipes.add(new Pipes(900, 0));

        ravenBlack = new Raven(150, 200, 60, 60, "raven1.png");
        ravenRed = new Raven(50, 200, 60, 60, "raven2.png");

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
        }

        ravenBlack.draw(graphToBack);
        ravenRed.draw(graphToBack);

        if(keys[0]){
          if(canPressQ){
            ravenRed.move("UP");
            canPressQ = false;
          }
        }
        if(keys[1]){
          if(canPressP){
            ravenBlack.move("UP");
            canPressP = false;
          }
        }

        ravenBlack.move("DOWN");
        ravenRed.move("DOWN");

        twoDGraph.drawImage(back, null, 0, 0);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            keys[2] = false;
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