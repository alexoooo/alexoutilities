package ao.util.data;

import ao.util.math.rand.Rand;
import ao.util.pass.Filter;
import ao.util.pass.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Arrs
{
    //------------------------------------------------------------------------
    private Arrs() {}

    public static void main(String[] args) {
        String[] test = new String[]{
                "1", "a", "b", "2", "3", "0", "x"};

        List<String> numList = filter(test, Filters.regex("\\d+"));
        System.out.println(numList);

        String[] numArr = filter(
                String.class, test, Filters.regex("\\d+"));
        System.out.println(Arrays.toString( numArr ));
    }


    //------------------------------------------------------------------------
    public static int[] sequence(int upToNotIncluding)
    {
        int sequence[] = new int[ upToNotIncluding ];
        for (int i = 0; i < sequence.length; i++)
        {
            sequence[ i ] = i;
        }
        return sequence;
    }


    //------------------------------------------------------------------------
    public static void swap(Object values[], int indexA, int indexB)
    {
        Object temp;
        
        temp             = values[ indexA ];
        values[ indexA ] = values[ indexB ];
        values[ indexB ] = temp;
    }

    public static void swap(int values[], int indexA, int indexB)
    {
        int temp;

        temp             = values[ indexA ];
        values[ indexA ] = values[ indexB ];
        values[ indexB ] = temp;
    }

    public static void swap(double values[], int indexA, int indexB)
    {
        double temp;

        temp             = values[ indexA ];
        values[ indexA ] = values[ indexB ];
        values[ indexB ] = temp;
    }


    //------------------------------------------------------------------------
    public static int indexOf(Object values[], Object val)
    {
        return Arrays.asList(values).indexOf( val );
    }

    public static int indexOf(int values[], int val)
    {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == val) return i;
        }
        return -1;
    }

    public static int indexOf(char values[], int val)
    {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == val) return i;
        }
        return -1;
    }

    
    //------------------------------------------------------------------------
    public static String join(double values[], String with)
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < values.length; i++)
        {
            if (i != 0) str.append( with );
            str.append( values[i] );
        }
        return str.toString();
    }

    public static String join(int values[], String with)
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < values.length; i++)
        {
            if (i != 0) str.append( with );
            str.append( values[i] );
        }
        return str.toString();
    }

    public static String join(byte[] vals, String with)
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < vals.length; i++)
        {
            if (i != 0) str.append( with );
            str.append( vals[i] );
        }
        return str.toString();
    }

    public static String join(Object values[], String with)
    {
        StringBuilder str = new StringBuilder();

        if (values.length > 0) {
            str.append( values[0] );
        }

        for (int i = 1; i < values.length; i++)
        {
            str.append( with );
            str.append( values[i] );
        }
        return str.toString();
    }


    //------------------------------------------------------------------------
    /**
     * see http://en.wikipedia.org/wiki/Fisher–Yates_shuffle#Example_Implementations
     *  
     * @param values array to be shuffled in-place
     */
    public static void shuffle(double[] values)
    {
        for (int i = values.length; i > 1; i--) {
            Arrs.swap(values, i-1, Rand.nextInt(i));
        }
    }

    public static void shuffle(Object[] values)
    {
        for (int i = values.length; i > 1; i--) {
            Arrs.swap(values, i-1, Rand.nextInt(i));
        }
    }

    
    //------------------------------------------------------------------------
    public static <T> List<T> filter(T[] values, Filter<T> filter)
    {
        List<T> accepted = new ArrayList<T>();
        for (T value : values) {
            if (filter.accept( value )) {
                accepted.add( value );
            }
        }
        return accepted;
    }

    public static <T> T[] filter(
            Class<T> clazz, T[] values, Filter<T> filter)
    {
        List<T> asList  = filter(values, filter);

        @SuppressWarnings("unchecked")
        T[]     asArray = (T[]) java.lang.reflect.Array
                            .newInstance(clazz, asList.size());
        return filter(values, filter).toArray( asArray );
    }


    //------------------------------------------------------------------------
    public static <T> T[] contact(
            Class<T> clazz, T[] a, T[] b)
    {
        @SuppressWarnings("unchecked")
//        T[] cat = (T[]) new Object[a.length + b.length];
        T[] cat = (T[]) java.lang.reflect.Array
                .newInstance(clazz, a.length + b.length);

        System.arraycopy(a, 0, cat, 0, a.length);
        System.arraycopy(b, 0, cat, a.length, b.length);

        return cat;
    }
}
