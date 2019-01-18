package ao.util.data.iter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: alex
 * Date: 2-Jun-2010
 * Time: 6:17:11 PM
 */
public abstract class AbstractCloseableIterable<T>
        implements CloseableIterable<T>
{
    //-------------------------------------------------------------------------
    private final List<CloseableIterator<T>> iterators =
            new ArrayList<CloseableIterator<T>>();


    //-------------------------------------------------------------------------
    @Override
    public final CloseableIterator<T> iterator()
    {
        CloseableIterator<T> iterator =
                nextIterator();

        iterators.add( iterator );

        return iterator;
    }

    protected abstract CloseableIterator<T> nextIterator();
    

    //-------------------------------------------------------------------------
    @Override
    public void close()
    {
        for (CloseableIterator<T> itr : iterators)
        {
            itr.close();
        }
    }
}
