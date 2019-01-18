package ao.util.data.tuple;

/**
* User: alex
* Date: 19-May-2010
* Time: 8:41:59 PM
*/
public class ThreeTuple<A, B, C>
        extends TwoTuple<A, B>
{
    //-------------------------------------------------------------------------
    private final C c;


    //-------------------------------------------------------------------------
    public ThreeTuple(A first, B second, C third)
    {
        super(first, second);
        
        c = third;
    }


    //-------------------------------------------------------------------------
    public C third()
    {
        return c;
    }


    //-------------------------------------------------------------------------
    @Override
    protected void toInnerString(StringBuilder str)
    {
        super.toInnerString( str );
        str.append(", ").append( c );
    }
}
