import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame; // provides basic window features
import javax.swing.JLabel; // displays text and images
import javax.swing.SwingConstants; // common constants used with Swing
import javax.swing.Icon; // interface used to manipulate images
import javax.swing.ImageIcon; // loads images

public class LabelFrame extends JFrame
{


    public JLabel label3; // JLabel with added text and icon

    // LabelFrame constructor adds JLabels to JFrame
    public LabelFrame()
    {
        super( "Custom Image" );
        setLayout( new BorderLayout() ); // set frame layout

        // JLabel constructor with string, Icon and alignment arguments
        Icon WelcomingImage = new ImageIcon( getClass().getResource( "openImage.jpg" ) );

        label3 = new JLabel(); // JLabel constructor no arguments
        label3.setText( "Label with icon and text at bottom" );
        label3.setIcon( WelcomingImage ); // add icon to JLabel
        label3.setHorizontalTextPosition( SwingConstants.CENTER );
        label3.setVerticalTextPosition( SwingConstants.BOTTOM );
        label3.setToolTipText( "This is label3" );
        add( label3 ); // add label3 to JFrame




//        add( label2 ); // add label3 to JFrame

    } // end LabelFrame constructor


} // end class LabelFrame

