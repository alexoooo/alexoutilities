package ao.util.data.iter;

import java.io.Closeable;

/**
 * User: alex
 * Date: 10-May-2010
 * Time: 11:56:52 PM
 */
public interface CloseableIterable<T>
        extends Closeable,
                Iterable<T>
{
    //-------------------------------------------------------------------------
    @Override
    public CloseableIterator<T> iterator();

    @Override
    public void close();


    //-------------------------------------------------------------------------
    public static class Impl
    {
        private Impl() {}

//        public static <T> CloseableIterable<T>
//                fromIterator(final CloseableIterator<T> iterator)
//        {
//            return new CloseableIterable<T>() {
//                private boolean isFirst = true;
//
//                @Override
//                public synchronized CloseableIterator<T> iterator()
//                {
//                    if (! isFirst) {
//                        throw new Error("Concurrent Iteration Exception");
//                    }
//                    isFirst = false;
//
//                    return iterator;
//                }
//
//                @Override
//                public synchronized void close()
//                {
//                    iterator.close();
//                }
//            };
//        }

        public static <T> CloseableIterable<T> empty()
        {
            return new CloseableIterable<T>() {
                @Override
                public CloseableIterator<T> iterator()
                {
                    return CloseableIterator.Impl.empty();
                }

                @Override public void close() {}
            };
        }
    }
}
