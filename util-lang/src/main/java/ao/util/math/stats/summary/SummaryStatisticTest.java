package ao.util.math.stats.summary;

import ao.util.math.rand.Rand;

/**
 * User: aostrovsky
 * Date: 8-Feb-2010
 * Time: 10:35:34 AM
 */
public class SummaryStatisticTest
{
    //--------------------------------------------------------------------
    public static void main(String[] args)
    {
        SummaryStatistic statA = new PercentileSummary();
        SummaryStatistic statB = new RadixSummary(0.01);
        
        for (int i = 0; i < 1000000; i++)
        {
            double value = Rand.nextDouble() * 10;
            statA.add( value );
            statB.add( value );
        }

        System.out.println( statA.csvHeader() );
        System.out.println( statA.toCsv()     );

        System.out.println();
        
        System.out.println( statB.csvHeader() );
        System.out.println( statB.toCsv()     );
    }
}
