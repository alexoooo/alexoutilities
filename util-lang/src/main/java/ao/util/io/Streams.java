package ao.util.io;

import ao.util.data.iter.CloseableIterable;
import ao.util.data.iter.CloseableIterator;
import ao.util.data.iter.Iters;
import ao.util.misc.AoThrowables;
import ao.util.pass.DataSink;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;

/**
 * User: alex
 * Date: 26-May-2010
 * Time: 6:42:44 PM
 */
public class Streams
{
    //-------------------------------------------------------------------------
    private static final Logger LOG =
            Logger.getLogger( Streams.class );


    private Streams() {}


    //-------------------------------------------------------------------------
    public static void main(String[] args)
    {
        InputStream in = asInput(new DataSink<OutputStream>() {
            @Override public void process(OutputStream output) throws Exception
            {
                output.write(0);
                output.write(1);
                output.write(2);
            }});

        System.out.println(
                Arrays.toString( toByteArray(in) ));
    }


    //-------------------------------------------------------------------------
    public static final InputStream DUMMY_INPUT =
            new InputStream()
            {
                @Override
                public int read() throws IOException
                {
                    return -1;
                }
            };

    public static final OutputStream DUMMY_OUTPUT =
            new OutputStream()
            {
                @Override
                public void write(int i) throws IOException
                {
                    // ignore
                }
            };


    //-------------------------------------------------------------------------
    public static void copy(
            InputStream in, OutputStream out)
    {
        try
        {
            doCopy(in, out);
        }
        catch (Throwable e)
        {
            throw AoThrowables.wrap( e );
        }
    }

    public static void copyAndClose(
            InputStream in, OutputStream out)
    {
        try
        {
            doCopyAndClose(in, out);
        }
        catch (Throwable e)
        {
            throw AoThrowables.wrap( e );
        }
    }

    private static void doCopyAndClose(
                InputStream  in,
                OutputStream out
            ) throws IOException
    {
        try
        {
            doCopy(in, out);
        }
        finally
        {
            in .close();
            out.close();
        }
    }

    private static void doCopy(
                InputStream  in,
                OutputStream out
            ) throws IOException
    {
        int    i;
        byte[] buf = new byte[8 * 1024];

        while ((i = in.read(buf)) != -1)
        {
            out.write(buf, 0, i);
        }
    }


    //-------------------------------------------------------------------------
    public static byte[] toByteArray(InputStream in)
    {
        ByteArrayOutputStream cache =
                new ByteArrayOutputStream();

        copy(in, cache);

        return cache.toByteArray();
    }


    //-------------------------------------------------------------------------
    public static InputStream asInput(
            DataSink<OutputStream> outputFeeder)
    {
        try
        {
            return getInput( outputFeeder );
        }
        catch (IOException e)
        {
            throw AoThrowables.wrap( e );
        }
    }

    private static InputStream getInput(
                final DataSink<OutputStream> outputFeeder
            ) throws IOException
    {
        final PipedInputStream  in  = new PipedInputStream();
        final PipedOutputStream out = new PipedOutputStream( in );

        new Thread() {
            @Override public void run() {
                try {
                    outputFeeder.process( out );
                }
                catch (Throwable e)
                {
                    throw AoThrowables.wrap( e );
                }
                finally {
                    try
                    {
                        out.close();
                    }
                    catch (IOException e)
                    {
                        LOG.warn("Unable to close " + out, e);
                    }
                }
            }
        }.start();

        return in;
    }


    //-------------------------------------------------------------------------
    public static void close(Closeable closeable)
    {
        try
        {
            closeable.close();
        }
        catch (IOException e)
        {
            throw AoThrowables.wrap( e ); 
        }

//        try
//        {
//            closeable.close();
//            return true;
//        }
//        catch (IOException e)
//        {
//            return false;
//        }
    }


    //-------------------------------------------------------------------------
    public static CloseableIterable<String> readLines(
            final InputStream input)
    {
        return Iters.asIterable(new CloseableIterator<String>()
            {
                private final BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader( input ));

                private String nextLine;

                @Override
                public synchronized void close()
                {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw AoThrowables.wrap( e );
                    }
                }

                @Override
                public synchronized boolean hasNext()
                {
                    if (nextLine != null) {
                        return true;
                    }

                    try {
                        nextLine = reader.readLine();
                    } catch (IOException e) {
                        throw AoThrowables.wrap( e );
                    }

                    if (nextLine == null) {
                        close();
                    }
                    return (nextLine != null);
                }

                @Override
                public synchronized String next()
                {
                    hasNext();
                    return nextLine;
                }

                @Override
                public synchronized void remove() {
                    throw new UnsupportedOperationException();
                }
            });
    }
}
