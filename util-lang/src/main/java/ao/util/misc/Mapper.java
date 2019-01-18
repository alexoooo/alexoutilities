package ao.util.misc;

/**
 * User: aostrovsky
 * Date: 24-Jun-2009
 * Time: 12:23:23 PM
 */
public interface Mapper<From, To>
{
    //--------------------------------------------------------------------
    public To map(From from);
}
