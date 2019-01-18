package ao.util.io;

import ao.util.data.tuple.Tuples;
import ao.util.data.tuple.TwoTuple;
import ao.util.misc.AoThrowables;
import ao.util.misc.Mapper;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * User: alex
 * Date: 11-May-2010
 * Time: 12:23:08 AM
 */
public class OutputSplitter
        implements Closeable
{
    //-------------------------------------------------------------------------
    private final String                  header;
    private final Map<File, OutputStream> outs;
    private final Map<String, File>       fileCache;
    private final Mapper<String, File>    keyToFile;
    private final boolean                 append;


    //-------------------------------------------------------------------------
    public OutputSplitter(
            Mapper<String, File> fileMap)
    {
        this(fileMap, null, false);
    }

    public OutputSplitter(
            Mapper<String, File> fileMap,
            boolean              appendToFiles)
    {
        this(fileMap, null, appendToFiles);
    }

    public OutputSplitter(
            Mapper<String, File> fileMap,
            String               commonHeader)
    {
        this(fileMap, commonHeader, false);
    }

    public OutputSplitter(
            Mapper<String, File> fileMap,
            String               commonHeader,
            boolean              appendToFiles)
    {
        keyToFile = fileMap;
        header    = commonHeader;
        append    = appendToFiles;
        outs      = new HashMap<File, OutputStream>();
        fileCache = new HashMap<String, File>();
    }


    //-------------------------------------------------------------------------
    public File write(String key, String value)
    {
        return write(key, value.getBytes());
    }

    public File write(String key, byte[] value)
    {
        return write(key, value, 0, value.length);
    }

    public File write(
            String key, byte[] value, int offset, int length)
    {
        TwoTuple<File, OutputStream> out;
        try {
            out = out(key);
            out.second().write( value, offset, length );
        } catch (IOException e) {
            throw AoThrowables.wrap( e );
        }
        return out.first();
    }

    public File write(String key, int byteValue)
    {
        TwoTuple<File, OutputStream> out;
        try {
            out = out(key);
            out.second().write( byteValue );
        } catch (IOException e) {
            throw AoThrowables.wrap( e );
        }
        return out.first();
    }


    //-------------------------------------------------------------------------
    private TwoTuple<File, OutputStream> out(String key) throws IOException
    {
        File file = fileCache.get( key );

        if (file == null)
        {
            file = keyToFile.map( key );
            fileCache.put( key, file );
        }

        OutputStream out = outs.get( file );

        if (out == null)
        {
            boolean writeHeader =
                    (header != null &&
                     ! (file.exists() && append));

            out = AoFiles.out(file, append);
            outs.put(file, out);

            if (writeHeader)
            {
                out.write( header.getBytes() );
            }
        }

        return Tuples.create(file, out);
    }


    //-------------------------------------------------------------------------
    @Override
    public void close()
    {
        try
        {
            for (OutputStream out : outs.values())
            {
                out.close();
            }
        }
        catch (IOException e)
        {
            throw AoThrowables.wrap( e );
        }
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return outs.keySet().toString();
    }
}
