package ao.util.sys;

/**
 * User: alex
 * Date: 2-May-2010
 * Time: 12:39:19 PM
 */
public class Execution
{
    //-------------------------------------------------------------------------
    private final String command;
    private final int    exitStatus;
    private final String output;
    private final String error;


    //-------------------------------------------------------------------------
    public Execution(
            String command,
            int    exitStatus,
            String output,
            String error)
    {
        assert command != null;

        this.command    = command;
        this.exitStatus = exitStatus;
        this.output     = output;
        this.error      = error;
    }


    //-------------------------------------------------------------------------
    public String command()
    {
        return command;
    }

    public int exitStatus()
    {
        return exitStatus;
    }

    public String output()
    {
        return output;
    }

    public String error()
    {
        return error;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return command + " -> " + output + " | " + error + " = " + exitStatus;
    }


    //-------------------------------------------------------------------------
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Execution)) return false;

        Execution execution = (Execution) o;

        return exitStatus == execution.exitStatus &&
               command.equals(execution.command) &&
               !(error != null
                 ? !error.equals(execution.error)
                 : execution.error != null) &&
               !(output != null
                 ? !output.equals(execution.output)
                 : execution.output != null);

    }

    @Override
    public int hashCode()
    {
        int result = command.hashCode();
        result = 31 * result + exitStatus;
        result = 31 * result + (output != null ? output.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        return result;
    }
}
