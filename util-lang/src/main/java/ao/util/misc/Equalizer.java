package ao.util.misc;

/**
 * User: alex
 * Date: 19-Jul-2009
 * Time: 5:37:04 PM
 */
public interface Equalizer<T>
{
    //-------------------------------------------------------------------------
    public boolean equal(T a, T b);

    public int     hash(T of);
}
