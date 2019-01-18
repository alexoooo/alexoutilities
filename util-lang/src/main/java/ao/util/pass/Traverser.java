package ao.util.pass;

/**
 * Inverted iterator.
 *
 * Date: Dec 23, 2008
 * Time: 6:16:26 PM
 */
public interface Traverser<T>
{
    public void traverse(T instance);
}
