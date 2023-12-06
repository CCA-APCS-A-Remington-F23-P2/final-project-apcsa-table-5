import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Sky extends Canvas implements KeyListener, Runnable {
    private boolean[] keys;
    private BufferedImage back;
    private Image background;

    public Sky() {
        keys = new boolean[3];

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
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            keys[1] = false;
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