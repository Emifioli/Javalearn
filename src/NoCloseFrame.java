import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NoCloseFrame extends JFrame {
    public static void main( String[] arg ) {
        new NoCloseFrame();
    }

    public NoCloseFrame() {
        super( "No Close Frame!" );
        setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
        setSize( 300, 300 );
        setVisible( true );
        addWindowListener( new AreYouSure() );
    }

    private class AreYouSure extends WindowAdapter {
        public void windowClosing( WindowEvent e ) {
            int option = JOptionPane.showOptionDialog(
                    NoCloseFrame.this,
                    "Are you sure you want to quit?",
                    "Exit Dialog", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null,
                    null );
            if( option == JOptionPane.YES_OPTION ) {
                System.exit( 0 );
            }
        }
    }
}
