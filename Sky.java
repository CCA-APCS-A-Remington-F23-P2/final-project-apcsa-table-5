import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;

import java.awt.Font;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Sky extends Canvas implements KeyListener, Runnable {
    private boolean[] keys;
    private BufferedImage back;
    private Image background;

    private Raven ravenBlack;
    private Raven ravenRed;


    private boolean canPressQ = true;
    private boolean canPressP = true;

    private boolean playing = true;
    private boolean paused = false;

    private Score redScore;
    private Score blackScore;

    private boolean blackInPipe = false;
    private boolean redInPipe = false;

    private int roundCount;

    private ArrayList<Pipes> pipes;

    public Sky(String player1Name, String player2Name) {
        keys = new boolean[3];
        pipes = new ArrayList<>();
        pipes.add(new Pipes(1000, 0));
        pipes.add(new Pipes(1350, 0));

        ravenBlack = new Raven(150, 200, 60, 60, "raven1.png", player1Name);
        ravenRed = new Raven(50, 200, 60, 60, "raven2.png", player2Name);

        redScore = new Score(150, 50, Color.RED);
        blackScore = new Score(450, 50, Color.BLACK);

        roundCount = 2;

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
                if ((pipe.getYCenter() + pipe.getPipeGap() > ravenBlack.getY() && pipe.getYCenter() - pipe.getPipeGap() < ravenBlack.getY())) {
                  if(pipe.getXCenter()-(pipe.getWidth()/2) < ravenBlack.getX() && pipe.getXCenter()+(pipe.getWidth()/2) > ravenBlack.getX()){
                    blackInPipe = true;
                  }
                }

                if ((pipe.getYCenter() + pipe.getPipeGap() > ravenRed.getY() && pipe.getYCenter() - pipe.getPipeGap() < ravenRed.getY())) {
                     if(pipe.getXCenter()-(pipe.getWidth()/2) < ravenRed.getX() && pipe.getXCenter()+(pipe.getWidth()/2) > ravenRed.getX()) {
                    redInPipe = true;
                  }
                }

              if(blackInPipe && pipe.getXCenter()+(pipe.getWidth()/2)<ravenBlack.getX()){
                blackInPipe = false;
                blackScore.setScore(blackScore.getScore() + 1);
              }
              if(redInPipe && pipe.getXCenter()+(pipe.getWidth()/2)<ravenRed.getX()){
                redInPipe = false;
                redScore.setScore(redScore.getScore() + 1);
              }
            }

          

          //speed code
            if (blackScore.getScore() != 0 && blackScore.getScore() % 10 == 0) {
                for (Pipes pipe : pipes) {
                    pipe.setSpeed(-(blackScore.getScore() + 10) / 10);
                }
                ravenBlack.setPipeSpeed(pipes.get(0).getSpeed());
                ravenRed.setPipeSpeed(pipes.get(0).getSpeed());
            }

            if (redScore.getScore() != 0 && redScore.getScore() % 10 == 0) {
                for (Pipes pipe : pipes) {
                    pipe.setSpeed(-(redScore.getScore() + 10) / 10);
                }
                ravenBlack.setPipeSpeed(pipes.get(0).getSpeed());
                ravenRed.setPipeSpeed(pipes.get(0).getSpeed());
            }
        }


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

        if (ravenRed.isHit(pipes)) {
            ravenRed.setDead(true);
            updateDatabase(ravenRed.getName(), redScore);
        }
        if (ravenBlack.isHit(pipes)) {
            ravenBlack.setDead(true);
            updateDatabase(ravenBlack.getName(), blackScore);
        }


        ravenBlack.move();
        ravenRed.move();

        redScore.draw(graphToBack);
        blackScore.draw(graphToBack);

        if (ravenBlack.isDead() && ravenRed.isDead() && (playing || paused)) {
            playing = false;
            paused = true;
            blackInPipe = false;
            redInPipe = false;
            if (roundCount <= 3) {


                window.setFont(new Font("SansSerif", Font.PLAIN, 30));
                window.setColor(Color.BLACK);
                window.drawString("Round " + roundCount, 250, 150);
                window.setFont(new Font("SansSerif", Font.PLAIN, 20));
                window.drawString("Press SPACE to start", 210, 200);
            } else {
                window.setFont(new Font("SansSerif", Font.PLAIN, 30));
                window.setColor(Color.BLACK);
                window.drawString("Game Over", 230, 150);
                window.setFont(new Font("SansSerif", Font.PLAIN, 20));
                window.drawString("Press SPACE to restart", 200, 200);
            }
        }

        twoDGraph.drawImage(back, null, 0, 0);
    }


    public void resetAll() {
        ravenBlack.reset();
        ravenRed.reset();
        for (Pipes pipe : pipes) {
            pipe.reset();
        }
    }

    public void hardReset() {
        ravenBlack.reset();
        ravenRed.reset();
        for (Pipes pipe : pipes) {
            pipe.reset();
        }
        blackScore.setScore(0);
        redScore.setScore(0);
        roundCount = 2;
    }

    public void updateDatabase(String playerName, Score score) {

        try {
            Path FILE_PATH = Paths.get("Database.txt");
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).contains(playerName)) {
                    String str = fileContent.get(i);
                    // String playerScoreStr = str.replaceAll("[^0-9]", "");
                    // int playerScore = Integer.parseInt(playerScoreStr);
                    fileContent.set(i, playerName + " " + score.getScore());
                    break;
                }
            }

            Files.write(FILE_PATH, fileContent);
        } catch (Exception e) {
            System.out.println("Error in writing");
        }


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
        if (e.getKeyCode() == KeyEvent.VK_SPACE && paused) {
            keys[2] = false;
            playing = true;
            paused = false;
            if (roundCount > 3) {
                hardReset();
            } else {
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