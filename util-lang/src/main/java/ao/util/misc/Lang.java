package ao.util.misc;

import java.util.Arrays;

/**
 * User: alex
 * Date: 6-Jun-2010
 * Time: 10:45:06 PM
 */
public class Lang
{
    //-------------------------------------------------------------------------
    private Lang() {}


    //-------------------------------------------------------------------------
    public static <T> T firstNotNull(T... values)
    {
        return firstNotNull(
                Arrays.asList( values ));
    }

    public static <T> T firstNotNull(Iterable<T> values)
    {
        for (T value : values)
        {
            if (value != null)
            {
                return value;
            }
        }
        return null;
    }
}
