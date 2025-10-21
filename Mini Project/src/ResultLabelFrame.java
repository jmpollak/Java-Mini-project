import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame; // provides basic window features
import javax.swing.JLabel; // displays text and images
import javax.swing.SwingConstants; // common constants used with Swing
import javax.swing.Icon; // interface used to manipulate images
import javax.swing.ImageIcon; // loads images

public class ResultLabelFrame  extends JFrame {

    public JLabel label2; // JLabel with added text and icon

    public ResultLabelFrame() {
        super( "Custom Image" );
        setLayout( new BorderLayout() ); // set frame layout

        // JLabel constructor with string, Icon and alignment arguments
        Icon ResultsPercentages = new ImageIcon( getClass().getResource( "resultsImage.jpg" ) );

        label2 = new JLabel(); // JLabel constructor no arguments
        label2.setText("Label with icon and text at bottom");
        label2.setIcon(ResultsPercentages ); // add icon to JLabel
        label2.setHorizontalTextPosition(SwingConstants.CENTER );
        label2.setVerticalTextPosition(SwingConstants.BOTTOM );
        label2.setToolTipText("This is label2");

        label2.setSize(500,500); // set frame size
        label2.setVisible(true); // display frame

        add(label2);
    }
}
