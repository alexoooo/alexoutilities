package ao.util.math;

/**
 * Date: Jan 20, 2009
 * Time: 5:59:20 PM
 */
public class Calc
{
    //-------------------------------------------------------------------------
    private Calc() {}


    //-------------------------------------------------------------------------
    public static void main(String[] args)
    {
        for (short i = 0; i < 256; i++) {
            System.out.println(
                    unsigned((byte) i));
        }
    }


    //-------------------------------------------------------------------------
    public static double square(double x)
    {
        return x * x;
    }

    public static int square(int x)
    {
        return x * x;
    }


    //-------------------------------------------------------------------------
    /**
     *
     * @param a first number to multiply
     * @param b second number to multiply (by first number)
     * @return a * b, but correctly handling the case of
     *          Infinity * 0 = 0
     */
    public static double multiply(double a, double b)
    {
        return Double.isInfinite( a ) && b == 0 ||
               Double.isInfinite( b ) && a == 0
                 ? 0 : a * b;
    }


    //-------------------------------------------------------------------------
    public static long unsigned(int value)
    {
        return value >= 0
               ? value
               : (long)(Integer.MAX_VALUE + value) +
                 (long)(Integer.MAX_VALUE        ) + 2;
    }


    //-------------------------------------------------------------------------
    public static short unsigned(byte value)
    {
        return value >= 0
               ? value
               : (short)((short)(Byte.MAX_VALUE + value) +
                         (short)(Byte.MAX_VALUE        ) + 2);
    }


    //-------------------------------------------------------------------------
    public static int signedPart(long unsignedInteger)
    {
        return (int) (unsignedInteger - Integer.MAX_VALUE - 1);
    }


    //-------------------------------------------------------------------------
    public static char toChar(byte big, byte little)
    {
        return (char)(((big << 8) & 0x0000FF00) |
                      (little     & 0x000000FF));
    }

    public static char toChar(byte[] bytes, int offset)
    {
        return (char)(((bytes[offset    ] << 8) & 0x0000FF00) |
                      ( bytes[offset + 1]       & 0x000000FF));
    }


    //-------------------------------------------------------------------------
    public static int toInt(
            byte bigBig,    byte bigLittle,
            byte littleBig, byte littleLittle)
    {
        return ((bigBig    << 24) & 0xFF000000) |
               ((bigLittle << 16) & 0x00FF0000) |
               ((littleBig << 8 ) & 0x0000FF00) |
               ( littleLittle     & 0x000000FF);
    }

    public static int toInt(
            byte[] bytes, int offset)
    {
        return ((bytes[offset    ] << 24) & 0xFF000000) |
               ((bytes[offset + 1] << 16) & 0x00FF0000) |
               ((bytes[offset + 2] << 8 ) & 0x0000FF00) |
               ((bytes[offset + 3]      ) & 0x000000FF);
    }


    //-------------------------------------------------------------------------
    public static long toLong(
            byte hiBigBig,    byte hiBigLittle,
            byte hiLittleBig, byte hiLittleLittle,
            byte loBigBig,    byte loBigLittle,
            byte loLittleBig, byte loLittleLittle)
    {
        return (((long) hiBigBig       << 56) & 0xFF00000000000000L) |
               (((long) hiBigLittle    << 48) & 0x00FF000000000000L) |
               (((long) hiLittleBig    << 40) & 0x0000FF0000000000L) |
               (((long) hiLittleLittle << 32) & 0x000000FF00000000L) |
               ((       loBigBig       << 24) &         0xFF000000L) |
               ((       loBigLittle    << 16) &         0x00FF0000L) |
               ((       loLittleBig    << 8 ) &         0x0000FF00L) |
               (        loLittleLittle        &         0x000000FFL);
    }

    public static long toLong(
            byte[] bytes, int offset)
    {
        return (((long) bytes[offset    ] << 56) & 0xFF00000000000000L) |
               (((long) bytes[offset + 1] << 48) & 0x00FF000000000000L) |
               (((long) bytes[offset + 2] << 40) & 0x0000FF0000000000L) |
               (((long) bytes[offset + 3] << 32) & 0x000000FF00000000L) |
               ((       bytes[offset + 4] << 24) &         0xFF000000L) |
               ((       bytes[offset + 5] << 16) &         0x00FF0000L) |
               ((       bytes[offset + 6] << 8 ) &         0x0000FF00L) |
               ((       bytes[offset + 7]      ) &         0x000000FFL);
    }


    //-------------------------------------------------------------------------
    public static double toDouble(
            byte hiBigBig,    byte hiBigLittle,
            byte hiLittleBig, byte hiLittleLittle,
            byte loBigBig,    byte loBigLittle,
            byte loLittleBig, byte loLittleLittle)
    {
        return Double.longBitsToDouble(toLong(
                hiBigBig, hiBigLittle, hiLittleBig, hiLittleLittle,
                loBigBig, loBigLittle, loLittleBig, loLittleLittle));
    }

    public static double toDouble(
            byte[] bytes, int offset)
    {
        return Double.longBitsToDouble(
                 toLong(bytes, offset));
    }
}
