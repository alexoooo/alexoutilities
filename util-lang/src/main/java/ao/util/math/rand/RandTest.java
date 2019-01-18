package ao.util.math.rand;

/**
 *
 */
public class RandTest
{
    private RandTest() {}

    public static void main(String[] args)
    {
        System.out.println("Rand1 fast");

//        Random rand = new MtRandom();
        // warmup
        for (int i = 0; i < 50000; i++)
        {
            Rand.nextInt();
        }

        for (int x = 0; x < 20; x++)
        {
            long before = System.currentTimeMillis();
            for (int i = 100 * 1000 * 1000; i > 0; i--)
            {
                Rand.nextInt();
            }
            long after = System.currentTimeMillis();
            System.out.println((after - before));
        }
    }
}
