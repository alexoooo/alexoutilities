package ao.util.math.stats;

import java.math.BigInteger;

/**
 * Functions from Combinatorics.
 */
public class Combo
{
    //--------------------------------------------------------------------
    /**
     * The colexicographical index (Bollobas 1986) of a set of integers
     *  x = {x1..xk} ? {0..n ? 1}, with xi < xj whenever i < j, is
     *  colex(x) = sum for i = 0 to k: (xi choose i).
     * This index has the important property that for a given n,
     *  each of the (n choose k) sets of size k has a distinct
     *  colexicographical index. Furthermore, these indices are compactly
     *  encoded as the integers from 0 to (n choose k) - 1.
     *
     * @param set of ascending numbers to encody (see above).
     * @return colexicographical index
     */
    public static int colex(int... set)
    {
        int colex = 0;
        for (int i = 0; i < set.length; i++)
        {
            colex += choose(set[i], i + 1);
        }
        return colex;
    }

    public static int colex(int xA, int xB)
    {
        return smallChoose(xA, 1) +
               smallChoose(xB, 2);
    }

    public static int colex(int xA, int xB, int xC)
    {
        return smallChoose(xA, 1) +
               smallChoose(xB, 2) +
               smallChoose(xC, 3);
    }


    //--------------------------------------------------------------------
    public static long choose(int n, int r)
    {
        if (isTrivialChoose(n, r)) return 0;

        if (n < 21)
        {
            long nFact = factorial(n);
            long rFact = factorial(r);
            return nFact / (rFact * factorial(n - r));
        }
        else
        {
            return calcBigChoose(n, r).longValue();
        }
    }

    public static BigInteger bigChoose(int n, int r)
    {
        return isTrivialChoose(n, r)
               ? BigInteger.ZERO
                 //BigInteger.valueOf(
                 //   trivialChoose(n, r))
               : calcBigChoose(n, r);
    }
    private static BigInteger calcBigChoose(int n, int r)
    {
        BigInteger nFact = bigFactorial(n);
        BigInteger rFact = bigFactorial(r);
        return nFact.divide(
                   rFact.multiply(bigFactorial(n - r)) );
    }

    public static int smallChoose(int n, int r)
    {
        return isTrivialChoose(n, r)
               ? 0
               : smallFactorial(n) /
                 (smallFactorial(r) *
                  smallFactorial(n - r) );
    }

    private static boolean isTrivialChoose(int n, int r)
    {
        return r < 0 || r > n;
    }


    //--------------------------------------------------------------------
    private static final int SMALL_FACTORIALS[] =
            {1, 1, 2, 6, 24, 120, 720, 5040, 40320,
             362880, 3628800, 39916800, 479001600};

    private static final long LONG_FACTORIALS[] =
            {1, 1, 2, 6, 24, 120, 720, 5040, 40320,
             362880, 3628800, 39916800, 479001600,
             1307674368000L,      20922789888000L,
             355687428096000L,    6402373705728000L,
             121645100408832000L, 2432902008176640000L};


    public static long factorial(int n)
    {
        return LONG_FACTORIALS[ n ];

//        long fact = 1;
//        for (int i = n; i > 1; i--)
//        {
//            fact *= i;
//        }
//        return fact;
    }

    public static BigInteger bigFactorial(int n)
    {
        BigInteger fact = BigInteger.ONE;
        for (int i = n; i > 1; i--)
        {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }

    public static int smallFactorial(int n)
    {
        return SMALL_FACTORIALS[ n ];

//        int fact = 1;
//        for (int i = n; i > 1; i--)
//        {
//            fact *= i;
//        }
//        return fact;
    }
}
