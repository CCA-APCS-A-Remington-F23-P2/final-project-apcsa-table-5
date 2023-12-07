import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

public class Pipes extends MovingThing {
    private int xCenter;
    private int yCenter;
    private final int PIPE_GAP = 60;
    private final int SCENE_WIDTH = 600;
    private final int SCENE_HEIGHT = 450;
    private Image topImage;
    private Image botImage;
    public static final String TOP_PIPE_PNG = "topPipe.png";

    public static final String BOT_PIPE_PNG = "botPipe.png";

    public Pipes(int x, int y) {
        super(x, y);
        setWidth(112);
        setCenter(SCENE_WIDTH / 2, SCENE_HEIGHT / 2);
        try {
            URL topUrl = getClass().getResource(TOP_PIPE_PNG);
            URL botUrl = getClass().getResource(BOT_PIPE_PNG);
            topImage = ImageIO.read(topUrl);
            botImage = ImageIO.read(botUrl);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Pipes(int x, int y, int cx, int cy) {
        super(x, y);
        setWidth(112);
        setCenter(cx, cy);
        try {
            URL topUrl = getClass().getResource(TOP_PIPE_PNG);
            URL botUrl = getClass().getResource(BOT_PIPE_PNG);
            topImage = ImageIO.read(topUrl);
            botImage = ImageIO.read(botUrl);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getXCenter() {
        return xCenter;
    }

    public void setXCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    public int getYCenter() {
        return yCenter;
    }

    public void setYCenter(int yCenter) {
        this.yCenter = yCenter;
    }

    public void setCenter(int x, int y) {
        xCenter = x;
        yCenter = y;
    }

    @Override
    public void move(String direction) {

    }

    @Override
    public void draw(Graphics window) {
        window.drawImage(topImage, getX(), 0, topImage.getWidth(null), getYCenter() - PIPE_GAP, null);
        window.drawImage(botImage, getX(), getYCenter() + PIPE_GAP, botImage.getWidth(null), SCENE_HEIGHT - getYCenter() - PIPE_GAP, null);

    }
}