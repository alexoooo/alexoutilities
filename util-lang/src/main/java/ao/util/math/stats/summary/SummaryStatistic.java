package ao.util.math.stats.summary;

import ao.util.text.Tabular;

/**
 * User: aostrovsky
 * Date: 8-Feb-2010
 * Time: 10:04:43 AM
 */
public interface SummaryStatistic
        extends Tabular
{
    //--------------------------------------------------------------------
    public void add(double measure);
}
