package ao.util.time;

import ao.util.text.AoFormat;

/**
 * User: alex
 * Date: 23-Apr-2009
 * Time: 3:06:00 PM
 */
public class Stopwatch
{
    //--------------------------------------------------------------------
    private final long start;


    //--------------------------------------------------------------------
    public Stopwatch()
    {
        start = System.currentTimeMillis();
    }


    //--------------------------------------------------------------------
    public String timing()
    {
        return AoFormat.hhmmss(timingMillis());
    }

    public long timingMillis()
    {
        return System.currentTimeMillis() - start;
    }


    //--------------------------------------------------------------------
    @Override public String toString()
    {
        return timing();
    }
}
