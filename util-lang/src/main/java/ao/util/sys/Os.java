package ao.util.sys;

/**
 * User: alex
 * Date: 2-May-2010
 * Time: 1:08:51 PM
 *
 * See: http://lopica.sourceforge.net/os.html
 *
 */
public enum Os
{
    //-------------------------------------------------------------------------
    WIN_XP   ("Windows XP"   , true , false),
    WIN_VISTA("Windows Vista", true , false),
    WIN_7    ("Windows 7"    , true , false),
    LINUX    ("Linux"        , false, true )
    ;


    //-------------------------------------------------------------------------
    public static Os HOST = detect();

    private static Os detect()
    {
        String osName = System.getProperty("os.name");
        if (osName == null) return null;

        for (Os os : Os.values())
        {
            if (os.name.equals( osName ))
            {
                return os;
            }
        }

        return null;
    }


    //-------------------------------------------------------------------------
    private Os(
            String  name,
            boolean isWindows,
            boolean isUnixLike)
    {
        this.name       = name;
        this.isWindows  = isWindows;
        this.isUnixLike = isUnixLike;
    }


    //-------------------------------------------------------------------------
    private final String  name;
    private final boolean isWindows;
    private final boolean isUnixLike;


    //-------------------------------------------------------------------------
//    public String  name      () {  return name      ;  }
    public boolean isWindows () {  return isWindows ;  }
    public boolean isUnixLike() {  return isUnixLike;  }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return name;
    }
}
