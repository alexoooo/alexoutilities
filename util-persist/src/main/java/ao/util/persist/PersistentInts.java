package ao.util.persist;

import ao.util.math.Calc;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 *
 */
public class PersistentInts
{
    //--------------------------------------------------------------------
//    private static final String testOut =
//            Dirs.get("test/out") + "/test.int";

    private static final int N_BYTE     = Integer.SIZE / 8;


    //--------------------------------------------------------------------
    public static void main(String[] args)
    {
//        int test[] = {-1, 0, Integer.MIN_VALUE, Integer.MAX_VALUE};
//        PersistentInts.persist(test, testOut);
//        if (! Arrays.equals(test,
//                PersistentInts.retrieve(testOut))) {
//            System.out.println("ERR!!");
//        }

//        {
//            int test[] = new int[ 100 ];
//            for (int i = 0; i < test.length; i++) {
//                test[ i ] = i;
//            }
//
//            PersistentInts.persist(test, testOut);
//        }
//
//        int confirm[] = PersistentInts.retrieve(testOut);
//        for (int i = 0; i < confirm.length; i++) {
//            if (confirm[ i ] != i) {
//                System.out.println(i);
//            }
//        }
    }


    //--------------------------------------------------------------------
    private PersistentInts() {}


    //--------------------------------------------------------------------
    public static int[] retrieve(String fromFile)
    {
        return retrieve( new File(fromFile) );
    }
    public static int[] retrieve(File fromFile)
    {
        try
        {
            if (fromFile == null || ! fromFile.canRead()) return null;

            // The only reason this check is done (as opposed to
            //   just using mmap all the time) is that there
            //   is a bug in windows when using mmap.  It is possible
            //   that I'm just misusing it.  The bug does not appear
            //   every time.  So pretty much, on windows, there is
            //   no guarantee that this method will work for files
            //   larger than one meg.
            return (fromFile.length() < 1024 * 1024)
                    ? doRetrieve    (fromFile)
                    : doRetrieveMmap(fromFile);
        }
        catch (Exception e)
        {
            throw new Error( e );
        }
    }

    private static int[] doRetrieveMmap(File cacheFile) throws Exception
    {
        int[] cached = new int[ (int)(cacheFile.length() / N_BYTE) ];
        FileInputStream f = new FileInputStream( cacheFile );
        try
        {
            FileChannel ch = f.getChannel( );

            int offset = Mmap.ints(cached, 0, ch);
            while (offset < cached.length)
            {
                offset = Mmap.ints(cached, offset, ch);
            }

            ch.close();
        }
        finally
        {
            f.close();
        }
        return cached;
    }

    private static int[] doRetrieve(File cacheFile) throws Exception
    {
        int[] cached = new int[ (int)(cacheFile.length() / N_BYTE) ];
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream( cacheFile ));
        try {
            byte[] buffer = new byte[ (int) cacheFile.length() ];
            if (in.read(buffer) != buffer.length) {
                throw new Error(
                        "PersistentInts did not read expected size");
            }
            for (int i = 0; i < cached.length; i++) {
                cached[ i ] = Calc.toInt(buffer, i * N_BYTE);
            }
        } finally {
            in.close();
        }
        return cached;
    }


    //--------------------------------------------------------------------
    public static void persist(
            int vals[], String fileName)
    {
        persist( vals, new File(fileName) );
    }
    public static void persist(
            int vals[], File toFile)
    {
        try
        {
            doPersist(vals, toFile);
        }
        catch (Exception e)
        {
            throw new Error( e );
        }
    }

    private static void doPersist(
            int vals[], File cacheFile) throws Exception
    {
        //noinspection ResultOfMethodCallIgnored
        cacheFile.createNewFile();

        DataOutputStream cache =
                new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(cacheFile)));
        for (int val : vals)
        {
            cache.writeInt( val );
        }
        cache.close();
    }
}
