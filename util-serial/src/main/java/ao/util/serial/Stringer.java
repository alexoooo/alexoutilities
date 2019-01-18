package ao.util.serial;

/**
 * 
 */
public class Stringer
{
    //--------------------------------------------------------------------
    private Stringer() {}


    //--------------------------------------------------------------------
    public static String toString(Object of)
    {
        return Serializer.toXml( of );
    } 
}
