package ao.util.compress;

import ao.util.data.tuple.Tuples;
import ao.util.data.tuple.TwoTuple;
import ao.util.io.AoFiles;
import ao.util.io.FileInfo;
import ao.util.io.Streams;
import ao.util.misc.AoThrowables;
import ao.util.pass.DataSink;
import ao.util.pass.Filter;
import ao.util.pass.Traverser;
import com.ice.tar.TarEntry;
import com.ice.tar.TarInputStream;
import net.contrapunctus.lzma.LzmaInputStream;
import net.contrapunctus.lzma.LzmaOutputStream;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Uses lzma,  compression.
 */
public class Compressor
{
    //-------------------------------------------------------------------------
    private static final Logger LOG =
            Logger.getLogger( Compressor.class );

    private Compressor() {}

    public static void main(String[] args)
    {
        unTar("/home/alex/Downloads/ideaIC-9.0.2.tar",
              "/home/alex/Downloads/untar-test");
    }


    //-------------------------------------------------------------------------
    public static boolean unTar(
            String tarball, String intoDirectory)
    {
        InputStream in = AoFiles.in(tarball);
        try {
            return unTar(in, new File(intoDirectory));
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                LOG.warn("Unable to close " + tarball, e);
            }
        }
    }

    public static boolean unTar(
            InputStream tarball, File intoDirectory)
    {
        try
        {
            return doUntar(tarball, intoDirectory.toString());
        }
        catch (IOException e)
        {
            LOG.debug("Could not untar " +
                        tarball + " into " + intoDirectory, e);
            return false;
        }
    }

    private static boolean doUntar(
            final InputStream tarball,
            final String      intoDirectory)
                    throws IOException
    {
        return unTar(
                tarball,
                new Filter<FileInfo>() {
                    @Override public boolean accept(FileInfo instance) {
                        return ! instance.isDirectory() &&
                                instance.path().endsWith(".txt");
                    }
                },
                new Traverser<TwoTuple<FileInfo, InputStream>>() {
                    @Override public void traverse(
                            TwoTuple<FileInfo, InputStream> instance) {
                        Streams.copyAndClose(
                                instance.second(),
                                AoFiles.out(
                                    intoDirectory + "/" +
                                    instance.first().path()));
                    }
                });
    }

    public static boolean unTar(
            InputStream                            tarball,
            Filter   <FileInfo>                    filter,
            Traverser<TwoTuple<FileInfo, InputStream>> traverser)
    {
        try
        {
            return doUnTar(tarball, filter, traverser);
        }
        catch (Exception e)
        {
            throw AoThrowables.wrap( e );
        }
    }

    private static boolean doUnTar(
            InputStream                            tarball,
            Filter   <FileInfo>                    filter,
            Traverser<TwoTuple<FileInfo, InputStream>> traverser)
                    throws Exception
    {
        final TarInputStream tin =
                new TarInputStream( tarball );

        try
        {
            TarEntry tarEntry = tin.getNextEntry();

            while (tarEntry != null)
            {
                FileInfo info = new FileInfo(
                        tarEntry.getName(),
                        tarEntry.getSize(),
                        tarEntry.isDirectory(),
                        tarEntry.getModTime().getTime());

                if (filter.accept( info ))
                {
                    InputStream in = Streams.asInput(
                            new DataSink<OutputStream>() {
                        @Override public void process(
                                OutputStream input) throws Exception {
                            tin.copyEntryContents( input );
                        }});

                    traverser.traverse(
                            Tuples.create(
                                    info, in));
                }

                tarEntry = tin.getNextEntry();
            }
        }
        finally
        {
            tin.close();
        }

        return true;
    }


    //-------------------------------------------------------------------------
    public static OutputStream gzip(
            String uncompressedFilePath)
    {
        return gzip( AoFiles.out(uncompressedFilePath) );
    }

    public static OutputStream gzip(File uncompressed)
    {
        return gzip( AoFiles.out(uncompressed) );
    }

    public static OutputStream gzip(OutputStream uncompressed)
    {
        return AoFiles.newOuputFilter(
                GZIPOutputStream.class, uncompressed);
    }


    public static OutputStream lzma(
            String uncompressedFilePath)
    {
        return lzma( AoFiles.out(uncompressedFilePath) );
    }

    public static OutputStream lzma(File uncompressed)
    {
        return lzma( AoFiles.out(uncompressed) );
    }

    public static OutputStream lzma(OutputStream uncompressed)
    {
        return AoFiles.newOuputFilter(
                LzmaOutputStream.class, uncompressed);
    }
    

    //-------------------------------------------------------------------------
    public static InputStream unGzip(
            String compressedFilePath)
    {
        return unGzip( AoFiles.in(compressedFilePath) );
    }

    public static InputStream unGzip(File compressed)
    {
        return unGzip( AoFiles.in(compressed) );
    }

    public static InputStream unGzip(InputStream compressed)
    {
        return AoFiles.newInputFilter(
                GZIPInputStream.class, compressed);
    }


    public static InputStream unLzma(
            String compressedFilePath)
    {
        return unLzma( AoFiles.in(compressedFilePath) );
    }

    public static InputStream unLzma(File compressed)
    {
        return unLzma( AoFiles.in(compressed) );
    }

    public static InputStream unLzma(InputStream compressed)
    {
        return AoFiles.newInputFilter(
                LzmaInputStream.class, compressed);
    }


    //-------------------------------------------------------------------------
    public static byte[] deflate(
            byte[]                              data,
            Class<? extends FilterOutputStream> filter)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream      lzma =
                AoFiles.newOuputFilter( filter, baos );

        try
        {
            lzma.write( data );
            lzma.close();
            return baos.toByteArray();
        }
        catch (IOException e)
        {
            throw new Error( e );
        }
    }

    public static byte[] deflate(byte data[])
    {
        return deflate(data, LzmaOutputStream.class);
    }

    public static byte[] deflate(String data)
    {
        return deflate(data.getBytes());
    }

    
    //-------------------------------------------------------------------------
    public static byte[] inflate(
            byte[]                             compressedData,
            Class<? extends FilterInputStream> filter)
    {
        ByteArrayInputStream bais =
                new ByteArrayInputStream( compressedData );
        InputStream          lzma =
                AoFiles.newInputFilter(filter, bais);

        try
        {
            byte         buffer[]     = new byte[ 1024 * 32 ];
            List<byte[]> bufferBuffer = new ArrayList<byte[]>();

            int size;
            while ((size = lzma.read(buffer)) != -1)
            {
                bufferBuffer.add(
                        Arrays.copyOf(buffer, size));
            }

            if (bufferBuffer.size() == 0)
            {
                return new byte[0];
            }
            else if (bufferBuffer.size() == 1)
            {
                return bufferBuffer.get(0);
            }
            else
            {
                int lastLength = bufferBuffer.get(
                                bufferBuffer.size() - 1 ).length;
                int totalSize =
                        (bufferBuffer.size() - 1) * buffer.length +
                        lastLength;
                byte inflated[] = new byte[ totalSize ];
                for (int i = 0; i < bufferBuffer.size(); i++)
                {
                    System.arraycopy(
                            bufferBuffer.get(i),
                            0,
                            inflated,
                            i * buffer.length,
                            bufferBuffer.get(i).length);
                }
                return inflated;
            }
        }
        catch (IOException e)
        {
            throw new Error( e );
        }
    }

    public static byte[] inflate(byte compressedData[])
    {
        return inflate(compressedData, LzmaInputStream.class);
    }

    public static String inflateStr(byte compressedData[])
    {
        return new String(inflate(compressedData));
    }
}
