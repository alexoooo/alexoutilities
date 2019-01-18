package ao.util.pass;

/**
 * User: alex
 * Date: 26-May-2010
 * Time: 6:01:34 PM
 *
 * A one-shot processor, as opposed to the multi-processing
 *      Listener and Traverser.
 */
public interface Transformer<I, O>
{
    //-------------------------------------------------------------------------
    public O process(I input);
}
