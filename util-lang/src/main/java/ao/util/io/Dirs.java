package ao.util.io;

import java.io.File;

/**
 * Date: Jan 14, 2009
 * Time: 4:43:49 PM
 */
public class Dirs
{
    //-------------------------------------------------------------------------
    private Dirs() {}


    //-------------------------------------------------------------------------
    public static File working()
    {
        return new File(
                System.getProperty("user.dir"));
    }


    //-------------------------------------------------------------------------
    public static File get(String dirName, String subdirName)
    {
        return get( new File(dirName), subdirName );
    }
    public static File get(File dir, String subdirName)
    {
        return get( new File(dir, subdirName) );
    }


    //-------------------------------------------------------------------------
    public static File get(String dirName)
    {
        return (dirName == null || dirName.isEmpty())
               ? null : get( new File(dirName) );
    }

    public static File get(File dir)
    {
        //noinspection ResultOfMethodCallIgnored
        dir.mkdirs();
        return dir;
    }


    //-------------------------------------------------------------------------
    public static File pathTo(File dir)
    {
        return pathTo( dir.toString() );
    }

    public static File pathTo(String dirName)
    {
        return get(AoFiles.path( dirName ));
    }


    //-------------------------------------------------------------------------
    public static boolean isEmpty(String dir)
    {
        return isEmpty(new File(dir));
    }

    public static boolean isEmpty(File dir)
    {
        if (dir == null) return true;
        assert dir.isDirectory();

        String subs[] = dir.list();
        return subs != null &&
               subs.length == 0;
    }


    //-------------------------------------------------------------------------
    public static boolean deleteTree(String fileOrDir)
    {
        return deleteTree(new File(fileOrDir));
    }

    public static boolean deleteTree(File fileOrDir)
    {
        if (! fileOrDir.exists()) return false;

        if (fileOrDir.isDirectory())
        {
            File[] subFiles = fileOrDir.listFiles();
            for(File toDelete : subFiles)
            {
                deleteTree(toDelete);
            }
        }

        return fileOrDir.delete();
    }
}
