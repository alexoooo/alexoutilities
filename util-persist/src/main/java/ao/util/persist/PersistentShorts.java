package ao.util.persist;

import ao.util.data.primitive.ShortList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * can read text files (as opposed to binary)
 */
public class PersistentShorts
{
    //--------------------------------------------------------------------
    private PersistentShorts() {}


    //--------------------------------------------------------------------
    public static short[] retrieve(String fileName)
    {
        return retrieve( new File(fileName) );
    }
    public static short[] retrieve(File fromFile)
    {
        try
        {
            return doRetrieve( fromFile );
        }
        catch (Exception e)
        {
            throw new Error( e );
        }
    }
    private static short[] doRetrieve(File cacheFile) throws Exception
    {
        if (! cacheFile.canRead()) return null;

        short[] cached = new short[ (int)(cacheFile.length() / 2) ];

        DataInputStream cache =
                new DataInputStream(
                        new BufferedInputStream(
                                new FileInputStream(cacheFile),
                                1048576));
        for (int i = 0; i < cached.length; i++)
        {
            cached[ i ] = cache.readShort();
        }
        cache.close();

        return cached;
    }


    //--------------------------------------------------------------------
    public static void persist(short vals[], String fileName)
    {
        persist( vals, new File(fileName) );
    }
    public static void persist(short vals[], File toFile)
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
            short vals[], File cacheFile) throws Exception
    {
        //noinspection ResultOfMethodCallIgnored
        cacheFile.createNewFile();

        DataOutputStream cache =
                new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(cacheFile)));
        for (Short val : vals)
        {
            cache.writeShort( val );
        }
        cache.close();
    }


    //--------------------------------------------------------------------
    public static short[] asArray(String fromTextFile)
    {
        return asArray( new File(fromTextFile) );
    }
    public static short[] asArray(File fromTextFile)
    {
        try
        {
            return doRetrieveCachedText(fromTextFile);
        }
        catch (Exception e)
        {
            throw new Error( e );
        }
    }

    public static List<Short> asList(String fileTextName)
    {
        return asList( new File(fileTextName) );
    }
    public static List<Short> asList(File fileTextName)
    {
        short[] asArray = asArray(fileTextName);
        List<Short> asList = new ArrayList<Short>( asArray.length );
        for (short val : asArray)
        {
            asList.add( val );
        }
        return asList;
    }


    //--------------------------------------------------------------------
    private static short[] doRetrieveCachedText(
            File fileName) throws Exception
    {
        File cachedFile = new File(fileName + ".cache");

        short[] cached = retrieve(cachedFile);
        if (cached != null) return cached;

        short[] fromText = doRetrieveText(fileName);
        persist(fromText, cachedFile);
        return fromText;
    }

    private static short[] doRetrieveText(File fromFile) throws Exception
    {
        BufferedReader reader =
                new BufferedReader(new FileReader( fromFile ));

        StringBuilder num  = new StringBuilder();
        ShortList     nums = new ShortList();

        String line;
        while ((line = reader.readLine()) != null)
        {
            if (line.length() == 0 || line.charAt(0) == '#') continue;

            for (int i = 0; i < line.length(); i++)
            {
                char charAtI = line.charAt( i );
                if (Character.isDigit(charAtI))
                {
                    num.append( charAtI );
                }
                else if (num.length() > 0)
                {
                    nums.add( Short.parseShort(num.toString()) );
                    num = new StringBuilder();
                }
            }
            if (num.length() > 0)
            {
                nums.add( Short.parseShort(num.toString()) );
                num = new StringBuilder();
            }
        }
        reader.close();

        return nums.toShortArray();
    }
}