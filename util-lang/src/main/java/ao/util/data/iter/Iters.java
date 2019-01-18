package ao.util.data.iter;

import java.util.Iterator;

/**
 * User: alex
 * Date: 2-Jun-2010
 * Time: 6:11:03 PM
 */
public class Iters
{
    //-------------------------------------------------------------------------
    private Iters() {}


    //-------------------------------------------------------------------------
    public static <T> Iterable<T> asIterable(
            final Iterator<T> iterator)
    {
        return new Iterable<T>()
        {
            private boolean isFirst = true;

            @Override public synchronized Iterator<T> iterator()
            {
                if (! isFirst)
                {
                    throw new IllegalStateException(
                            "Can only be called a single time");
                }

                return iterator;
            }
        };
    }


    //-------------------------------------------------------------------------
    public static <T> CloseableIterable<T> asIterable(
            final CloseableIterator<T> iterator)
    {
        return new CloseableIterable<T>()
        {
            private boolean isFirst = true;

            @Override public synchronized
                    CloseableIterator<T> iterator()
            {
                if (! isFirst)
                {
                    throw new IllegalStateException(
                            "Can only be called a single time");
                }

                return iterator;
            }

            @Override
            public void close()
            {
                iterator.close();
            }
        };
    }


    //-------------------------------------------------------------------------
    public static <T> CloseableIterable<T> emptyCloseableIterable()
    {
        return new CloseableIterable<T>() {
            @Override
            public CloseableIterator<T> iterator()
            {
                return emptyCloseableIterator();
            }

            @Override public void close() {}
        };
    }

    public static <T> CloseableIterator<T> emptyCloseableIterator()
    {
        return new CloseableIterator<T>() {
            @Override public void    close  () {               }
            @Override public boolean hasNext() { return false; }
            @Override public T       next   () { return null ; }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
