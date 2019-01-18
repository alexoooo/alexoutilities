package ao.util.ui;

import ao.util.io.Dirs;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * User: alex
 * Date: 29-Apr-2010
 * Time: 11:37:06 PM
 */
public class SwingUtils
{
    //-------------------------------------------------------------------------
    private SwingUtils() {}


    //-------------------------------------------------------------------------
    public static void main(String[] args)
    {

    }


    //-------------------------------------------------------------------------
    public static JFrame defaultFrame = null;


    //-------------------------------------------------------------------------
    public static synchronized JFrame defaultFrame()
    {
        return defaultFrame( Dirs.working().toString() );
    }

    public static synchronized JFrame
            defaultFrame(final String title)
    {
        if (defaultFrame != null)
        {
            final JFrame asFinal = defaultFrame;
            SwingUtilities.invokeLater(new Runnable() {
                @Override public void run() {
                    asFinal.setTitle( title );
                }});
            return defaultFrame;
        }

        defaultFrame = new JFrame( title );
        defaultFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        defaultFrame.setLocationRelativeTo( null );
        defaultFrame.pack();
        defaultFrame.setVisible( true );

        return defaultFrame;
    }

//    public static synchronized void
//            setDefaultFrameTitle(final String title)
//    {
//        if (defaultFrame == null)
//        {
//            defaultFrame( title );
//        }
//        else
//        {
//            final JFrame asFinal = defaultFrame;
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override public void run() {
//                    asFinal.setTitle( title );
//                }});
//        }
//    }
}
