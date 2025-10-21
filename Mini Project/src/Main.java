import javax.swing.JFrame;

public class Main
{
    public static void main(String[] args)
    {
        CardLayoutFrame frame = new CardLayoutFrame();
        frame.setSize(800,600);
        frame.setTitle("Nerd % Trivia Game by John & Ella");
        LabelFrame labelFrame = new LabelFrame(); // create LabelFrame
        labelFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        labelFrame.setSize( 500, 500 ); // set frame size
        labelFrame.setVisible( true ); // display frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Set the location of this frame by some component. null means center
        frame.setVisible(true);
    }
}
