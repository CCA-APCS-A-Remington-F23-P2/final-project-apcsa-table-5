import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;

public class Pipes extends MovingThing {
    private int xCenter;
    private int yCenter;
    private int speed = -1;
    private int pipeGap = 60;
    private final int SCENE_WIDTH = 600;
    private final int SCENE_HEIGHT = 450;
    private Image topImage;
    private Image botImage;
    public static final String TOP_PIPE_PNG = "topPipe.png";
    public static final String BOT_PIPE_PNG = "botPipe.png";

    private int startX;
    private int startY;

    public Pipes(int x, int y) {
        super(x, y);
        setCenter(SCENE_WIDTH / 2, SCENE_HEIGHT / 2);
        startX = x;
        startY = y;
        try {
            URL topUrl = getClass().getResource(TOP_PIPE_PNG);
            URL botUrl = getClass().getResource(BOT_PIPE_PNG);
            topImage = ImageIO.read(topUrl);
            botImage = ImageIO.read(botUrl);
        } catch (Exception e) {
            System.out.println(e);
        }
        setWidth(topImage.getWidth(null));
        setHeight(SCENE_HEIGHT);
    }

    public Pipes(int x, int y, int cx, int cy) {
        super(x, y);
        setCenter(cx, cy);
        startX = x;
        startY = y;
        try {
            URL topUrl = getClass().getResource(TOP_PIPE_PNG);
            URL botUrl = getClass().getResource(BOT_PIPE_PNG);
            topImage = ImageIO.read(topUrl);
            botImage = ImageIO.read(botUrl);
        } catch (Exception e) {
            System.out.println(e);
        }
        setWidth(topImage.getWidth(null));
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

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPipeGap(int gap) {
        pipeGap = gap;
    }

    public int getPipeGap(){
      return pipeGap;
    }

    public int getWidth() {
        return topImage.getWidth(null);
    }

    @Override
    public void move(String direction) {
        if (getX() <= -topImage.getWidth(null)) {
            int maxYCenter = SCENE_HEIGHT - 120;
            int minYCenter = 120;
            int minGap = 50;
            int maxGap = 100;
            setX(SCENE_WIDTH);
            setYCenter((int) ((Math.random() * (maxYCenter - minYCenter)) + minYCenter));
            setPipeGap((int) ((Math.random() * (maxGap - minGap)) + minGap));
        }

        setX(getX() + speed);
        xCenter = getX();
    }

    @Override
    public void draw(Graphics window) {
        window.drawImage(topImage, getX(), getYCenter() - pipeGap - topImage.getHeight(null), topImage.getWidth(null), topImage.getHeight(null), null);
        window.drawImage(botImage, getX(), getYCenter() + pipeGap, botImage.getWidth(null), botImage.getHeight(null), null);
    }

    @Override
    public boolean didCollide(MovingThing a) {
        return super.didCollide(a) && (a.getY() <= getYCenter() - pipeGap || a.getY() >= getYCenter() + pipeGap);
    }

    public void reset(){
      setPos(startX,startY);
      setSpeed(-1);
    }
}