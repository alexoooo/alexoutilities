package ao.util.math.stats;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * User: alex
 * Date: 18-Oct-2009
 * Time: 7:10:23 PM
 */
public class Exhauster<T>
        implements Iterator<T[]>,
                   Iterable<T[]>,
                   Serializable
{
    //--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
    //--------------------------------------------------------------------
    private T    items[];
    private int  a[];
    private int  n;
    private long numLeft;
    private long total;

    private T scratchpad[]; // used for type safety


    //--------------------------------------------------------------------
    @SuppressWarnings("unused")
	private Exhauster() {} // for JBoss Serialization

    public Exhauster(T toExhaust[], int n)
    {
        assert 0 < n && n < toExhaust.length;

        this.n     = n;
        this.items = toExhaust;
        scratchpad = Arrays.copyOf(items, n);

        a     = new int[n];
        total = (long) Math.pow(toExhaust.length, n);

        reset();
    }

    private void reset()
    {
        for (int i = 0; i < a.length; i++)
        {
            a[i] = 0;
        }
        numLeft = total;
    }


    //--------------------------------------------------------------------
    public boolean hasNext()
    {
        return numLeft > 0;
    }


    //--------------------------------------------------------------------
//    @SuppressWarnings("unchecked")
//    public T[] nextElement()
//    {
//        if (! hasNext()) return null;
//
//        for (int i = 0; i < r; i++)
//        {
//            scratchpad[i] = items[ a[i] ];
//        }
//
//        moveIndex();
//        return scratchpad.clone();
//    }
    public T[] next()
    {
        if (! hasNext()) return null;

        for (int i = 0; i < n; i++)
        {
            scratchpad[i] = items[ a[i] ];
        }

        moveIndex();
        return scratchpad.clone();
    }


    //--------------------------------------------------------------------
    public Iterator<T[]> iterator()
    {
        return this;
    }
    public void remove()
    {
        throw new UnsupportedOperationException();
    }


    //--------------------------------------------------------------------
    // Generate next combination (algorithm from Rosen p. 286)
    private void moveIndex()
    {
        if (--numLeft == 0) return;

        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == (items.length - 1)) {
                a[i] = 0;
            } else {
                a[i]++;
                break;
            }
        }
    }


    //--------------------------------------------------------------------
    public static void main(String args[])
    {
        String vals[] = {"a", "b", "c", "d", "e"};

        for (String[] combo : new Exhauster<String>(vals, 3))
        {
            System.out.println(
                    Arrays.toString( combo ));
        }
    }
}

