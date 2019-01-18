package ao.util.io;

import java.util.Comparator;

/**
 * User: alex
 * Date: 26-May-2010
 * Time: 7:21:26 PM
 */
public class FileInfo
{
    //--------------------------------------------------------------------
    private final String   path;
    private final long     size;
    private final boolean  isDirectory;
    private final long     modified;


    //--------------------------------------------------------------------
    public FileInfo(
            String   filePath,
            long     fileSize,
            boolean  fileIsDirectory,
            long     modifiedTime)
    {
        path        = filePath;
        size        = fileSize;
        isDirectory = fileIsDirectory;
        modified    = modifiedTime;
    }


    //--------------------------------------------------------------------
    public long modified()
    {
        return modified;
    }

    public String path()
    {
        return path;
    }

    public long size()
    {
        return size;
    }

    public boolean isDirectory()
    {
        return isDirectory;
    }


    //--------------------------------------------------------------------
    public boolean isBetween(
            long fromInclusive,
            long upToInclusive)
    {
        if (fromInclusive > 0) {
            if ((modified - fromInclusive) < 0) return false;
        }

        if (upToInclusive > 0) {
            if ((modified - upToInclusive) > 0) return false;
        }

        return true;
    }


    //--------------------------------------------------------------------
    @Override public String toString()
    {
        return path;
    }

    public String toDetailedString()
    {
    	return (isDirectory ? "[D] " : "[F] '")
				+ path + "' (" + size + " | " + modified + ")";
    }


    //--------------------------------------------------------------------
    public static class ModifiedTimeComparator
            implements Comparator<FileInfo>
    {
        public int compare(FileInfo a, FileInfo b)
        {
            return Long.signum(
                     a.modified - b.modified);
        }
    }
}
