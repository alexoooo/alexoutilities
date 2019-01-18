package ao.util.async;

import ao.util.text.AoFormat;
import ao.util.time.Sched;

/**
 * User: aostrovsky
 * Date: 9-Jul-2009
 * Time: 12:57:19 PM
 */
public class Throttle
{
    //--------------------------------------------------------------------
    public static void main(String[] args)
    {
//        long prev = System.nanoTime();
//        for (int i = 0; i < 1000000; i++)
//        {
//            long now = System.nanoTime();
//            System.out.println(
//                    now - prev);
//            prev = now;
//        }
        long oncePer = 10000;

        Throttle t = new Throttle(5000);
        for (long i = 0; i < 1000 * 1000 * 1000; i++)
        {
            t.process();

            if (i % oncePer == 0)
            {
                System.out.println(
                        System.currentTimeMillis());
            }
        }
    }


    //--------------------------------------------------------------------
    private long maxPerSec;
    private long milestoneNanos;
    private long processed;


    //--------------------------------------------------------------------
    public Throttle(long perSecond)
    {
        limit(perSecond);
    }


    //--------------------------------------------------------------------
    public void limit(long perSecond)
    {
        maxPerSec      = (perSecond == -1
                          ? Integer.MAX_VALUE : perSecond);
        milestoneNanos = System.nanoTime();
    }


    //--------------------------------------------------------------------
    public void process()
    {
        long now, nanoDelta;
        while (true)
        {
            now       = System.nanoTime();
            nanoDelta = (now - milestoneNanos);

            if ((procsPerSecond(nanoDelta) <= maxPerSec)) break;
            Sched.sleep(1); // unpredictable
        }

        processed++;
    }


    //--------------------------------------------------------------------
    private long procsPerSecond(long nanoDelta)
    {
        if (nanoDelta == 0) {
            return processed;
        }

        double perNano = (double) processed / (double) nanoDelta;
        return (long) Math.ceil(perNano * 1000000000);
    }


    //--------------------------------------------------------------------
    @Override public String toString()
    {
        return (maxPerSec == Integer.MAX_VALUE)
               ? "unlimited"
               : AoFormat.decimal(maxPerSec) + " B/s";
    }


    //--------------------------------------------------------------------
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Throttle throttle = (Throttle) o;

        return maxPerSec      == throttle.maxPerSec      &&
               milestoneNanos == throttle.milestoneNanos &&
               processed      == throttle.processed;

    }

    @Override public int hashCode() {
        int result = (int) (maxPerSec ^ (maxPerSec >>> 32));
        result = 31 * result +
                (int) (milestoneNanos ^ (milestoneNanos >>> 32));
        result = 31 * result +
                (int) (processed ^ (processed >>> 32));
        return result;
    }
}
