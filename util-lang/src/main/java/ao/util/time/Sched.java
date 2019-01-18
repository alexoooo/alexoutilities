package ao.util.time;

import ao.util.math.rand.Rand;

/**
 * User: aostrovsky
 * Date: 9-Jun-2009
 * Time: 2:52:16 PM
 */
public class Sched
{
    //-------------------------------------------------------------------------
    private Sched() {}


    //-------------------------------------------------------------------------
    public static boolean sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
            return true;
        }
        catch (InterruptedException ignored)
        {
            Thread.currentThread().interrupt();
            return false;
        }
    }


    //-------------------------------------------------------------------------
    public static boolean sleepUpto(long millis)
    {
        return sleep( (long) Rand.nextDouble(millis) );
    }
}
