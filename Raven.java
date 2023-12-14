import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.List;

public class Raven extends MovingThing {
    private Image image;
    private String imageName;
    private final int SCENE_WIDTH = 600;
    private final int SCENE_HEIGHT = 450;
    private final int PIPE_SPEED = -1;
    private boolean dead;
    private int flashAlpha = 255;
    private String playerName;

    // Physics
    private double velocity = 0;
    private final double GRAVITY = 0.05;

    private int startX;
    private int startY;

    public Raven() {
        this(10, 10, 60, 60);
    }

    public Raven(int x, int y) {
        this(x, y, 60, 60);
    }

    public Raven(int x, int y, int w, int h) {
        super(x, y, w, h);
        dead = false;
    }

    // all ctors call this ctor
    public Raven(int x, int y, int w, int h, String img, String playerName) {
        super(x, y, w, h);
        dead = false;
        imageName = img;
        startX = x;
        startY = y;
        this.playerName = playerName;

        try {
            URL url = getClass().getResource(imageName);
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
        }
    }

    public String getName() {
      return playerName;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void move() {
        if (isDead()) {
            setX(getX() + PIPE_SPEED);
            velocity = 2;
        }
        // Clamp bird Y between 0 and the scene height - bird height with padding
        setY(Math.max(0, Math.min(SCENE_HEIGHT - getHeight() - 20, getY() + (int) velocity)));

        // Make sure the bird doesn't get stuck during movement
        if ((int) (velocity + GRAVITY) == 0) {
            velocity = 1;
        }
        velocity += GRAVITY;
    }

    @Override
    public void move(String direction) {
        // Nothing
    }

    public void flap() {
        velocity = -3;
    }

    public boolean isHit(List<Pipes> pipes) {
        boolean hit = false;
        for (Pipes pipe : pipes) {
            if (pipe.didCollide(this)) hit = true;
        }
        return hit;
    }


    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
        if (isDead()) {
            window.setColor(new Color(255,255,255, flashAlpha));
            window.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
            flashAlpha = Math.max(0, --flashAlpha);
        }
    }

    public void reset(){
      setPos(startX,startY);
    }

    public String toString() {
        return super.toString();
    }
}
