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

    private boolean dead;

    public Raven() {
        this(10, 10, 10, 10);
    }

    public Raven(int x, int y) {
        this(x, y, 10, 10);
    }

    public Raven(int x, int y, int w, int h) {
        super(x, y, w, h);
        dead = false;
    }

    // all ctors call this ctor
    public Raven(int x, int y, int w, int h, String img) {
        super(x, y, w, h);
        dead = false;
        imageName = img;

        try {
            URL url = getClass().getResource(imageName);
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void move(String direction) {
        if (direction.equals("DOWN")) {
            setY(getY() + 1);
        } else if (direction.equals("UP")) {
            setY(getY() - 50);
        }
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
    }

    public String toString() {
        return super.toString();
    }
}
