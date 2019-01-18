package ao.util.math.stats.summary;

import ao.util.data.Arrs;
import ao.util.data.primitive.DoubleList;
import ao.util.text.Txt;
import java.util.Collections;

/**
 * User: aostrovsky
 * Date: 8-Feb-2010
 * Time: 10:07:10 AM
 */
public class PercentileSummary
        implements SummaryStatistic
{
    //--------------------------------------------------------------------
	private static final double[] percentiles = {
		    0.25, 0.5, 0.75, 0.90, 0.99, 0.999, 0.9999, 0.99999};


	//--------------------------------------------------------------------
	private DoubleList all = new DoubleList();
	private double     sum = 0;
	private double     max = Double.NEGATIVE_INFINITY;


	//--------------------------------------------------------------------
	public PercentileSummary() {}


	//--------------------------------------------------------------------
    @Override
    public void add(double value)
    {
		sum += value;
		max = Math.max(max, value);
		all.add(value);
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
	public String toCsv() {
		if (all.isEmpty()) {
			return ",,,," +
					Txt.nTimes(",", percentiles.length - 1);
		}

		Collections.sort(all);

		double[] percentileValues =
			new double[ percentiles.length ];

//        for (DoubleIterator itr = all.iterator())

		for (int i = 0; i < percentiles.length; i++) {
			percentileValues[i] =
				percentile(percentiles[i]);
		}

		return sum 				  + "," +
			   all.size() 		  + "," +
			   max 				  + "," +
			  (sum / all.size())  + "," +
			   Arrs.join(percentileValues, ",");
	}

	// see http://en.wikipedia.org/wiki/Quartile
	private double percentile(double percent)
	{
		double lPoint  = all.size() * percent;
		int    whole   = (int) lPoint;
		double decimal = lPoint - whole;
		if (Math.abs(decimal) < 0.0000001) {
			return (all.get(whole - 1) +
					all.get(whole    )) / 2.0;
		} else {
			return all.get(whole);
		}
	}
}
