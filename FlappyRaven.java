import javax.swing.JFrame;
import java.awt.Component;

public class FlappyRaven extends JFrame
{

    private static final int WIDTH = 400; //800
    private static final int HEIGHT = 300; //600

    public FlappyRaven()
    {
        super("FLAPPYRAVEN");
        setSize(WIDTH,HEIGHT);

        Sky theGame = new Sky();
        ((Component)theGame).setFocusable(true);

        getContentPane().add(theGame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}