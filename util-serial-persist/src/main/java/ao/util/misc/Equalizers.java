package ao.util.misc;

import ao.util.serial.Serializer;

import java.util.Arrays;

/**
 * User: alex
 * Date: 19-Jul-2009
 * Time: 5:38:29 PM
 */
public class Equalizers
{
    //--------------------------------------------------------------------
    private Equalizers() {}


    //--------------------------------------------------------------------
    private static Equalizer<double[]> doubleArray =
            new Equalizer<double[]>() {
                public boolean equal(double[] a, double[] b) {
                    return Arrays.equals(a, b);
                }

                public int hash(double[] of) {
                    return Arrays.hashCode(of);
                }
            };
    public static Equalizer<double[]> doubleArray() {
        return doubleArray;
    }


    //--------------------------------------------------------------------
    public static <T> Equalizer<T> equals() {
        return new Equalizer<T>() {
            public boolean equal(T a, T b) {
                return a.equals( b );
            }

            public int hash(T of) {
                return of.hashCode();
            }
        };
    }


    //--------------------------------------------------------------------
    public static <T> Equalizer<T> serial() {
        return new Equalizer<T>() {
            public boolean equal(T a, T b) {
                return Serializer.toXml(a).equals(
                        Serializer.toXml(b));
            }

            public int hash(T of) {
                return Serializer.toXml(of).hashCode();
            }
        };
    }
}
