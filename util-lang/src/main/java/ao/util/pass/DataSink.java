package ao.util.pass;

/**
 * User: alex
 * Date: 26-May-2010
 * Time: 6:03:53 PM
 */
public interface DataSink<I>
{
    //-------------------------------------------------------------------------
    public void process(I input) throws Exception;
}
