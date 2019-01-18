package ao.util.math.rand;

import java.util.List;
import java.util.Random;

/**
 * speed test.
 */
public class RandFast
{
    //--------------------------------------------------------------------
    private static final Random RAND = new Random();


    //--------------------------------------------------------------------
    private RandFast() {}


    //--------------------------------------------------------------------
    public static Random rand()
    {
        return RAND;
    }


    //--------------------------------------------------------------------
    public static <T> T fromArray(T arr[])
    {
        if (arr == null || arr.length == 0) return null;
        return arr[ RAND.nextInt(arr.length) ];
    }

    public static <T> T fromList(List<T> list)
    {
        if (list == null || list.isEmpty()) return null;
        return list.get(
                RAND.nextInt(list.size()) );
    }

    
    //--------------------------------------------------------------------
    public static double nextDouble()
    {
        return RAND.nextDouble();
    }
    public static double nextDouble(double upTo)
    {
        return upTo * RAND.nextDouble();
    }
    public static double nextDouble(double from, double upTo)
    {
        return from + nextDouble(upTo - from);
    }

    public static long nextLong()
    {
        return RAND.nextLong();
    }

    public static int nextInt(int n)
    {
        return (n > 0)
                ?  RAND.nextInt( n)
                : (n < 0)
                  ? -RAND.nextInt(-n)
                  : 0;
    }

    public synchronized static int nextInt()
    {
        return RAND.nextInt();
    }

    public static double nextGaussian()
    {
        return RAND.nextGaussian();
    }

    public static float nextFloat()
    {
        return RAND.nextFloat();
    }

    public static void nextBytes(byte[] bytes)
    {
        RAND.nextBytes(bytes);
    }

    public static boolean nextBoolean()
    {
        return RAND.nextBoolean();
    }
    public static boolean nextBoolean(int onceEvery)
    {
        return RAND.nextDouble() < (1.0/(double)onceEvery);
    }
    public static boolean nextBoolean(double probability)
    {
        return RAND.nextDouble() < probability;
    }
}
