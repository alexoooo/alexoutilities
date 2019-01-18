package ao.util.pass;

/**
 *
 */
public interface Filter<T>
{
    //------------------------------------------------------------------------
    public boolean accept(T instance);
}
