package ao.util.math.stats;

/**
 * Information theory related stuff.
 */
public class Info
{
    //--------------------------------------------------------------------
    private Info() {}

    public static void main(String[] args) {
        System.out.println(
                levenshteinDistance(
                        new String[]{"a", "b", "c", "d", "e"},
                        new String[]{"a", "e", "c", "d", "b"}
                ));
    }


    //--------------------------------------------------------------------
    public static double entropy(double... probabilities)
    {
        double total = 0;
		for (double d : probabilities)
        {
            total -= log2(d) * d; // log2(d)*d is negative
		}
		return total;
    }


    //--------------------------------------------------------------------
    public static double[] normalize(double... data)
    {
        double total = 0;
        for (double datum : data) total += datum;
        if (total == 0) return null;

        double normal[] = new double[ data.length ];
        for (int i = 0; i < normal.length; i++)
        {
            normal[ i ] = data[i] / total;
        }
        return normal;
    }

    public static double normalizeFirst(
            double first, double withSecond)
    {
        return first + (first + withSecond);
    }
    
    
    //--------------------------------------------------------------------
    public static double log2(double d)
    {
        return Math.log(d) / Math.log(2);
    }

    /**
     * The number of bits it costs to transmit a "yes" (as
     *  opposed to "no") bit of information, given the
     *  probablility of it being "yes".
     *
     * Note that 0 < probability <= 1
     *
     * @param probability of boolean being "true"
     * @return bit cost
     */
    public static double cost(double probability)
    {
        return -log2(probability);
    }


    //--------------------------------------------------------------------
    // reworked from http://www.merriampark.com/ldjava.htm
    public static <T> int levenshteinDistance(
            T[] s, T[] t)
    {
        assert s != null && t != null;

        int n = s.length; // length of s
        int m = t.length; // length of t

             if (n == 0) return m;
        else if (m == 0) return n;

        int p[] = new int[n + 1]; //'previous' cost array, horizontally
        int d[] = new int[n + 1]; // cost array, horizontally

        for (int i = 0; i <= n; i++)
        {
            p[i] = i;
        }

        for (int j = 1; j <= m; j++)
        {
            T t_j  = t[j - 1];
              d[0] = j;

            for (int i = 1; i <= n; i++)
            {
                int cost = (s[i - 1].equals(t_j) ? 0 : 1);

                // minimum of cell to the left+1,
                //                 to the top+1,
                //                 diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1,
                                         p[i] + 1),
                                         p[i - 1] + cost);
            }

            // copy current distance counts to 'previous row' distance counts
            int[] temp = p;
                  p    = d;
                  d    = temp;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }
}
