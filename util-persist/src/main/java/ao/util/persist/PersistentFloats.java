package ao.util.persist;

import java.io.*;

/**
 * Date: Jan 22, 2009
 * Time: 3:34:46 PM
 */
public class PersistentFloats
{
    //--------------------------------------------------------------------
    private static final int BUFFER_SIZE = 1048576;


    //--------------------------------------------------------------------
    private PersistentFloats() {}


    //--------------------------------------------------------------------
    public static void retrieve(
            String fromFile,
            float  rowsColumns[][])
    {
        retrieve(new File(fromFile), rowsColumns);
    }
    public static void retrieve(
            File  fromFile,
            float rowsColumns[][])
    {
        try {
            doRetrieve(fromFile, rowsColumns);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void doRetrieve(
            File  fromFile,
            float rowsColumns[][])
                    throws IOException
    {
        if (! fromFile.canRead()) return;

        DataInputStream cache =
                new DataInputStream(
                        new BufferedInputStream(
                                new FileInputStream(fromFile),
                                BUFFER_SIZE));
        for (int row = 0; row < rowsColumns.length; row++) {
            for (int col = 0; col < rowsColumns[row].length; col++) {
                rowsColumns[row][col] = cache.readFloat();
            }
        }
        cache.close();
    }


    //--------------------------------------------------------------------
    public static float[] retrieve(String fromFile)
    {
        return retrieve( new File(fromFile) );
    }
    public static float[] retrieve(File fromFile)
    {
        try
        {
            return doRetrieve(fromFile);
        }
        catch (Exception e)
        {
            throw new Error( e );
        }
    }

    private static float[] doRetrieve(File cacheFile) throws Exception
    {
        if (! cacheFile.canRead()) return null;

        float[] cached = new float[ (int)(cacheFile.length() / 4) ];

        DataInputStream cache =
                new DataInputStream(
                        new BufferedInputStream(
                                new FileInputStream(cacheFile),
                                BUFFER_SIZE));
        for (int i = 0; i < cached.length; i++)
        {
            cached[ i ] = cache.readFloat();
        }
        cache.close();

        return cached;
    }


    //--------------------------------------------------------------------
    public static void persist(
            float vals[][], String fileName)
    {
        persist( vals, new File(fileName) );
    }
    public static void persist(
            float vals[][], File toFile)
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
            float vals[][], File cacheFile) throws Exception
    {
        //noinspection ResultOfMethodCallIgnored
        cacheFile.createNewFile();

        DataOutputStream cache =
                new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(cacheFile)));
        for (float[] col : vals) {
            for (float val : col) {
                cache.writeFloat( val );
            }
        }

        cache.close();
    }


    //--------------------------------------------------------------------
    public static void persist(
            float vals[], String fileName)
    {
        persist( vals, new File(fileName) );
    }
    public static void persist(
            float vals[], File toFile)
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
            float vals[], File cacheFile) throws Exception
    {
        //noinspection ResultOfMethodCallIgnored
        cacheFile.createNewFile();

        DataOutputStream cache =
                new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(cacheFile)));
        for (float val : vals)
        {
            cache.writeFloat( val );
        }
        cache.close();
    }
}
