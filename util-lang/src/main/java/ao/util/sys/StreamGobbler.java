package ao.util.sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
* User: alex
* Date: 2-May-2010
* Time: 12:52:27 PM
*/
class StreamGobbler extends Thread
{
    //--------------------------------------------------------------------------
    private final InputStream   is;


    // needs to be a Buffer (as opposed to a Builder) because of the
    //  required synchronization
    private final StringBuffer str;



    //--------------------------------------------------------------------------
    public StreamGobbler(InputStream is)
    {
        this.is = is;

        str = new StringBuffer();
    }


    //--------------------------------------------------------------------------
    public void run()
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader    br  = new BufferedReader(isr);

            String line;
            while ( (line = br.readLine()) != null)
            {
                str.append(line).append("\n");
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }


    //--------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return str.toString();
    }
}
