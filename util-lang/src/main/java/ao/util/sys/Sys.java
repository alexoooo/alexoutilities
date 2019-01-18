package ao.util.sys;

/**
 * User: alex
 * Date: 2-May-2010
 * Time: 12:50:19 PM
 */
public class Sys
{
    //-------------------------------------------------------------------------
    private Sys() {}


    //-------------------------------------------------------------------------
    public static void main(String[] args)
    {
        System.out.println(
                exec("ls", "-l"));
    }


    //-------------------------------------------------------------------------
    public static Execution exec(
            String... commandAndArguments)
    {
        if (os().isWindows())
        {
            return ExecUtils.winExec(commandAndArguments);
        }
        else if (os().isUnixLike())
        {
            return ExecUtils.unixExec(commandAndArguments);
        }
        else
        {
            return null;
        }
    }


    //-------------------------------------------------------------------------
    public static Os os()
    {
        return Os.HOST;
    }


    //-------------------------------------------------------------------------
    public static String workingDirectory()
    {
        return System.getProperty("user.dir");
    }
}
