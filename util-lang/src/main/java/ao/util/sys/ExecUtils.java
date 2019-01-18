package ao.util.sys;

import ao.util.data.Arrs;
import java.util.Arrays;
import org.apache.log4j.Logger;

/**
 * User: alex
 * Date: 2-May-2010
 * Time: 1:25:25 PM
 */
class ExecUtils
{
    //-------------------------------------------------------------------------
    private ExecUtils() {}

    private static final Logger LOG =
            Logger.getLogger(ExecUtils.class);


    //-------------------------------------------------------------------------
    private static String[]  winPrefix = {"cmd.exe", "/C"};
    private static String[] unixPrefix = {}; // "/bin/bash", "-c"


    //-------------------------------------------------------------------------
    public static Execution winExec(String... commandAndArguments)
    {
        return exec(winPrefix, commandAndArguments);
    }

    public static Execution unixExec(String... commandAndArguments)
    {
        return exec(unixPrefix, commandAndArguments);
    }


    //-------------------------------------------------------------------------
    public static Execution exec(
            String[]  systemCommandPrefix,
            String... commandAndArguments)
    {
        try
        {
            return doExec(systemCommandPrefix,
                          commandAndArguments);
        }
        catch (Exception e)
        {
            throw new Error( e );
        }
    }

    private static Execution doExec(
            String[]  systemCommandPrefix,
            String... commandAndArguments) throws Exception
    {
        String[] cmd =
                Arrs.contact(String.class,
                            systemCommandPrefix,
                            commandAndArguments);

        Runtime rt = Runtime.getRuntime();
        LOG.debug("Executing: " + Arrays.toString( commandAndArguments ));
        Process process = rt.exec(cmd);

        // any error message?
        StreamGobbler errorGobbler =
                new StreamGobbler(process.getErrorStream());

        // any output?
        StreamGobbler outputGobbler =
                new StreamGobbler(process.getInputStream());

        // kick them off
        errorGobbler.start();
        outputGobbler.start();

        int status = process.waitFor();

        return new Execution(
                Arrays.toString(commandAndArguments),
                status,
                outputGobbler.toString(),
                errorGobbler.toString());
    }
}
