package ao.util.data.tuple;

/**
* User: alex
* Date: 19-May-2010
* Time: 8:41:42 PM
*/
public class TwoTuple<A, B>
{
    //-------------------------------------------------------------------------
    private final A a;
    private final B b;


    //-------------------------------------------------------------------------
    public TwoTuple(A first, B second)
    {
        a = first;
        b = second;
    }


    //-------------------------------------------------------------------------
    public A first()
    {
        return a;
    }
    public B second()
    {
        return b;
    }


    //-------------------------------------------------------------------------
    @Override public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append('(');
        toInnerString(str);
        str.append(')');
        return str.toString();
    }


    //-------------------------------------------------------------------------
    protected void toInnerString(StringBuilder str)
    {
        str.append(a).append(", ").append(b);
    }
}
