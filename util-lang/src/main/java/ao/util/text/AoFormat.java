package ao.util.text;

import java.text.DecimalFormat;

/**
 * User: alex
 * Date: 23-Apr-2009
 * Time: 3:18:48 PM
 */
public class AoFormat
{
    //-------------------------------------------------------------------------
    private AoFormat() {}


    //-------------------------------------------------------------------------
    private static final DecimalFormat DECIMAL_FORMAT =
            new DecimalFormat("#,###");

    private static final int MILLIS_IN_SECOND = 1000;
    private static final int MILLIS_IN_MINUTE = 60 * MILLIS_IN_SECOND;
    private static final int MILLIS_IN_HOUR   = 60 * MILLIS_IN_MINUTE;


    //-------------------------------------------------------------------------
    public static String hhmmss(long millis)
    {
        long[] hhmmss = hhmmssComponents( millis );
        return twoDigit( hhmmss[0] ) + ":" +
               twoDigit( hhmmss[1] ) + ":" +
               twoDigit( hhmmss[2] );
    }

    public static String time(long millis)
    {
        long[] hhmmss = hhmmssComponents( millis );

        StringBuilder str = new StringBuilder();
        if (hhmmss[0] > 0) {
            str.append( hhmmss[0] ).append(":")
               .append( twoDigit(hhmmss[1]) ).append(":")
               .append( twoDigit(hhmmss[2]) );
        } else {
            if (hhmmss[1] > 0) {
                str.append( hhmmss[1] ).append(":")
                   .append( twoDigit(hhmmss[2]) );
            } else {
                if (hhmmss[2] > 0) {
                    str.append( hhmmss[2] ).append(" s");
                } else {
                    str.append( millis    ).append(" ms");
                }
            }
        }
        return str.toString();
    }

    private static long[] hhmmssComponents(long millis)
    {
        long hourMod = (millis % MILLIS_IN_HOUR);
        return new long[]{
                millis / MILLIS_IN_HOUR,
                hourMod / MILLIS_IN_MINUTE,
               (hourMod % MILLIS_IN_MINUTE) / MILLIS_IN_SECOND};
    }


    //-------------------------------------------------------------------------
    public static String twoDigit(long value)
    {
        return (value < 10)
               ? "0" + value
               : Long.toString(value);
    }


    //-------------------------------------------------------------------------
    public static String decimal(long value)
    {
        return DECIMAL_FORMAT.format(value);
    }
}
