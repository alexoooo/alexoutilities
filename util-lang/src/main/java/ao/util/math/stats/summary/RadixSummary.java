package ao.util.math.stats.summary;

import ao.util.data.Arrs;
import ao.util.data.primitive.IntList;
import ao.util.text.Txt;

/**
 * User: aostrovsky
 * Date: 8-Feb-2010
 * Time: 10:37:37 AM
 */
public class RadixSummary
        implements SummaryStatistic
{
    //--------------------------------------------------------------------
    private static final double[] percentiles = {
		    0.25, 0.5, 0.75, 0.90, 0.99, 0.999, 0.9999, 0.99999};


    //--------------------------------------------------------------------
    private final double precision;

//    private final IntList positiveHistogram = new IntList();
//    private final IntList negativeHistogram = new IntList();
    private final IntList histogram = new IntList();

    private double     sum   = 0;
    private long       count = 0;
	private double     max   = Double.NEGATIVE_INFINITY;


    //--------------------------------------------------------------------
    public RadixSummary(double percentilePrecision)
    {
        assert percentilePrecision > 0.0000000001;
        precision = percentilePrecision;
    }


    //--------------------------------------------------------------------
    private int indexOf(double value)
    {
        return (int) Math.round(value / precision);
    }

    private double valueOf(int index)
    {
        return index * precision;
    }


    //--------------------------------------------------------------------
    @Override
    public void add(double measure)
    {
        assert measure >= 0;

        sum += measure;
        max  = Math.max(max, measure);
        count++;

        histogram.increment( indexOf( measure ) );
//        System.out.println(count + "\t" + addUpInc());
    }


    //--------------------------------------------------------------------
    public static String header()
    {
        StringBuilder str = new StringBuilder();
		for (double percentile : percentiles) {
			str.append(percentile * 100)
			   .append("%,");
		}
		str.deleteCharAt(str.length() - 1);

		return "Sum,Count,Max,Mean," + str;
    }

    @Override
    public String csvHeader()
    {
        return header();
    }

    @Override
    public String toCsv()
    {
        if (count == 0) {
			return ",,,," +
					Txt.nTimes(",", percentiles.length - 1);
		}

//        System.out.println(histogram.toString());
//        System.out.println(addUpInc());

		double[] percentileValues =
			new double[ percentiles.length ];

        int  nextPercentile = 0;
        long seenCount      = 0;
        for (int index = 0;
                 index < histogram.size();
                 index++)
        {
            seenCount += histogram.getInt( index );

            long percentileStart = Math.round(
                    percentiles[ nextPercentile ] * count);
            if (seenCount > percentileStart)
            {
                percentileValues[ nextPercentile ] =
                        valueOf(index);

                nextPercentile++;
                if (nextPercentile >= percentiles.length)
                {
                    break;
                }
            }
        }

        for (; nextPercentile < percentiles.length;
               nextPercentile++)
        {
            percentileValues[ nextPercentile ] = max;
        }

		return sum 			 + "," +
			   count 		 + "," +
			   max 		     + "," +
			  (sum / count)  + "," +
			   Arrs.join(percentileValues, ",");
    }

//    private long addUpInc()
//    {
//        long total = 0;
//        for (int i = 0; i < histogram.size(); i++)
//        {
//            total += histogram.getInt( i );
//        }
//        return total;
//    }
}
