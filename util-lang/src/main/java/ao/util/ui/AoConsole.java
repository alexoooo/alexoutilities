package ao.util.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Easy input
 */
public class AoConsole
{
    //--------------------------------------------------------------------
    private static final BufferedReader IN =
            new BufferedReader(
                    new InputStreamReader(System.in) );


    //--------------------------------------------------------------------
    private AoConsole() {}


    //--------------------------------------------------------------------
    public static void write(Object object)
    {
        System.out.println( String.valueOf(object) );
    }

    public static void writeInLine(Object object)
    {
        System.out.print( String.valueOf(object) );
        System.out.flush();
    }


    //--------------------------------------------------------------------
    public static int integer(String message)
    {
        System.out.println(message);
        return integer();
    }
    public static int integer()
    {
        while (true)
        {
            try
            {
                String line = IN.readLine();
                return Integer.parseInt( line );
            }
            catch (IOException e)
            {
                throw new Error( e );
            }
            catch (NumberFormatException ignored)
            {
                System.out.println(
                        "Please enter an integer (e.g. -5, 0, 3)...");
            }
        }
    }


    //--------------------------------------------------------------------
    public static double real(String message)
    {
        System.out.println(message);
        return real();
    }
    public static double real()
    {
        while (true)
        {
            try
            {
                String line = IN.readLine();
                return Double.parseDouble( line );
            }
            catch (IOException e)
            {
                throw new Error( e );
            }
            catch (NumberFormatException ignored)
            {
                System.out.println(
                        "Please enter a real number " +
                            "(e.g. 3.14, 0, -2.7)...");
            }
        }
    }


    //--------------------------------------------------------------------
    public static String text(String message)
    {
        System.out.println(message);
        return text();
    }
    public static String text()
    {
        try
        {
            return IN.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    //--------------------------------------------------------------------
    public static void main(String[] args)
    {
        int    x = AoConsole.integer("give me a number!");
        double y = AoConsole.real("now give me a real number");
        System.out.println("x * y = " + (x * y));
    }
}
