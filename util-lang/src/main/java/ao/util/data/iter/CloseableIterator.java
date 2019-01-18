package ao.util.data.iter;

import java.io.Closeable;
import java.util.Iterator;

/**
 * User: alex
 * Date: 10-May-2010
 * Time: 11:55:55 PM
 */
public interface CloseableIterator<T>
        extends Closeable,
                Iterator<T>
{
    //-------------------------------------------------------------------------
    @Override
    public void close();


    //-------------------------------------------------------------------------
    public static class Impl
    {
        private Impl() {}

        public static <T> CloseableIterator<T> empty()
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
}
