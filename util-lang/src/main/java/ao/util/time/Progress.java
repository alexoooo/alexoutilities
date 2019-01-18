package ao.util.time;

import ao.util.text.AoFormat;
import ao.util.ui.SwingUtils;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * Date: Feb 16, 2009
 * Time: 12:23:46 PM
 */
public class Progress
{
    //--------------------------------------------------------------------
    public static void main(String[] args)
    {
//        long     c = 10L * 1000 * 1000 * 1000;
//        Progress p = new Progress(c);
//
//        for (long i = 0; i < c; i++) {
//            p.checkpoint();
//        }

        indeterminate("this is a somewhat longish title");
    }


    //-------------------------------------------------------------------------
    public static JFrame indeterminate(String title)
    {
        JFrame frame = SwingUtils.defaultFrame( title );

        JProgressBar progress = new JProgressBar();
        progress.setIndeterminate( true );

        frame.getContentPane().add( progress );

        frame.setSize(new Dimension(400, 150));                                           
        frame.setLocationRelativeTo( null );

        return frame;
    }


    //--------------------------------------------------------------------
    private final long MINOR_PROGRESS;
    private final long MAJOR_PROGRESS;

    private       long count     = 0;
    private       long prevCheck = 0;


    //--------------------------------------------------------------------
    public Progress(long outOf)
    {
        assert outOf != 0;

        if (outOf > 0)
        {
            MAJOR_PROGRESS =
                    Math.max(outOf / 50, 50);
            MINOR_PROGRESS = MAJOR_PROGRESS / 50;
        }
        else
        {
            MINOR_PROGRESS = -outOf;
            MAJOR_PROGRESS = MINOR_PROGRESS * 50;
        }
    }


    //--------------------------------------------------------------------
    public void checkpoint(int progress)
    {
        for (int i = 0; i < progress; i++)
        {
            checkpoint();
        }
    }

    public void checkpoint()
    {
        if (count == 0)
        {
            checkpointInitial();
        }
        else
        {
            checkpointOngoing();
        }

        count++;
    }


    //--------------------------------------------------------------------
    private void checkpointInitial()
    {
        System.out.print(".");
        prevCheck = System.currentTimeMillis();
    }

    private void checkpointOngoing()
    {
        if ( count      % MINOR_PROGRESS == 0)
        {
            System.out.print(".");
        }

        if ((count + 1) % MAJOR_PROGRESS == 0)
        {
            long progress = count + 1;
            long duration = (System.currentTimeMillis() - prevCheck);

            String        increment = AoFormat.decimal(MAJOR_PROGRESS);
            String        total     = AoFormat.decimal(progress);

            System.out.println(
                    " "            + increment               +
                    " in "         + AoFormat.hhmmss(duration) +
                    ", totalling " + total);
            prevCheck = System.currentTimeMillis();
        }
    }
}
