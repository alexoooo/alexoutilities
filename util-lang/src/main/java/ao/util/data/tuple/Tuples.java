package ao.util.data.tuple;

/**
 * User: alex
 * Date: 10-May-2010
 * Time: 10:29:15 PM
 */
public class Tuples
{
    //-------------------------------------------------------------------------



    //-------------------------------------------------------------------------
    private Tuples() {}


    //-------------------------------------------------------------------------
    public static <A, B> TwoTuple<A, B> create(A first, B second)
    {
        return new TwoTuple<A, B>(first, second);
    }

    public static <A, B, C> ThreeTuple<A, B, C> create(
            A first, B second, C third)
    {
        return new ThreeTuple<A, B, C>(first, second, third);
    }



    //-------------------------------------------------------------------------

}
