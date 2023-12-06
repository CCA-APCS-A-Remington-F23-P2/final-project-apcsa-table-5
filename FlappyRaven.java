import javax.swing.*;

public class FlappyRaven extends JFrame
{

    private static final int WIDTH = 600;
    private static final int HEIGHT = 450; 

    public FlappyRaven()
    {
        super("FLAPPYRAVEN");
        setSize(WIDTH,HEIGHT);

        Sky theGame = new Sky();
        theGame.setFocusable(true);

        getContentPane().add(theGame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main( String args[] )
    {
        FlappyRaven run = new FlappyRaven();
    }
}