package ao.util.io;

import ao.util.misc.AoThrowables;
import java.io.*;
import org.apache.log4j.Logger;

/**
 * Date: Oct 17, 2008
 * Time: 3:13:25 PM
 */
public class Slurpy
{
    //-------------------------------------------------------------------------
    private static final Logger LOG =
            Logger.getLogger( Slurpy.class );


    //-------------------------------------------------------------------------
    private Slurpy() {}


    //-------------------------------------------------------------------------
    public static boolean write(
            String contents, String fileName)
    {
        return write( contents, new File( fileName ) );
    }

    public static boolean write(
            String contents, File outFile)
    {
        try
        {
            boolean closeFailed[] = {false};
            doWrite(contents, outFile, closeFailed);
            return !closeFailed[0];
        }
        catch (IOException e)
        {
            LOG.warn("Unable to write", e);
            return false;
        }
    }

    private static void doWrite(
            String  contents,
            File    outFile,
            boolean closeFailed[])
                throws IOException
    {
        Dirs.pathTo( outFile );

        OutputStream out = null;
        try
        {
            out = new FileOutputStream( outFile );
            out.write( contents.getBytes() );
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    closeFailed[0] = true;
                    LOG.warn("Unable to close file", e);
                }
            }
        }
    }


    //-------------------------------------------------------------------------
    public static String read(String contentsOf)
    {
        return read( new File( contentsOf ) );
    }

    public static String read(File contentsOf)
    {
        if (! contentsOf.canRead()) return null;

        InputStream fis = null;
        try
        {
            fis = new BufferedInputStream(
                    new FileInputStream(contentsOf));
            return read( fis );
        }
        catch (Throwable e)
        {
            throw AoThrowables.wrap( e );
        }
        finally
        {
            if (fis != null)
            {
                try {
                    fis.close();
                } catch (IOException e) {
                    LOG.error("Unable to close file: " + contentsOf, e);
                }
            }
        }
    }
    
    public static String read(InputStream contents)
    {
        try
        {
            return doSlurpString( contents );
        }
        catch (Throwable e)
        {
            throw AoThrowables.wrap( e );
        }
    }

    private static String doSlurpString(
            InputStream contents) throws IOException
    {
        StringBuilder str = new StringBuilder();

        for (int in; (in = contents.read()) != -1;)
        {
            str.append((char) in);
        }

        return str.toString();
    }
}
