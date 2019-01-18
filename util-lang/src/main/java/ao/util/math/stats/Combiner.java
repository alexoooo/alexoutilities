package ao.util.math.stats;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Ripped off from
 *  http://www.merriampark.com/comb.htm
 *
 * Note: NOT threadsafe.
 */
public class Combiner<T>
        implements Enumeration<T[]>,
                   Iterator<T[]>,
                   Iterable<T[]>,
                   Serializable
{
    //--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	
	//--------------------------------------------------------------------
    private T    items[];
    private int  a[];
    private int  n;
    private int  r = 0;
    private long numLeft;
    private long total;

    private T scratchpad[]; // used for type safety

    private boolean isFirstIterator = true;


    //--------------------------------------------------------------------
    @SuppressWarnings("unused")
	private Combiner() {} // for JBoss Serialization
    
    public Combiner(T toCombine[], int r)
    {
        this(toCombine, toCombine.length, r);
    }
    public Combiner(T toCombine[], int n, int r)
    {
        assert n >= r && r >= 0;

        this.n     = n;
        this.r     = r;
        this.items = toCombine;
        scratchpad = Arrays.copyOf(items, r);

        a     = new int[r];
        total = Combo.choose(n, r);

        reset();
    }

    private void reset()
    {
        for (int i = 0; i < a.length; i++)
        {
            a[i] = i;
        }
        numLeft = total;
    }


    //--------------------------------------------------------------------
    public boolean hasMoreElements()
    {
        return numLeft > 0;
    }
    public boolean hasNext()
    {
        return hasMoreElements();
    }


    //--------------------------------------------------------------------
//    @SuppressWarnings("unchecked")
    public T[] nextElement()
    {
        if (! hasMoreElements()) return null;

        for (int i = 0; i < r; i++)
        {
            scratchpad[i] = items[ a[i] ];
        }

        moveIndex();
        return scratchpad.clone();
    }
    public T[] next()
    {
        return nextElement();
    }


    //--------------------------------------------------------------------
    public Iterator<T[]> iterator()
    {
        if (! isFirstIterator)
        {
            return new Combiner<T>(items, n, r);
        }

        isFirstIterator = false;
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
//        if (numLeft-- == total) return;
//        numLeft--;

        int i = r - 1;
        while (a[i] == n - r + i) i--;

        a[i]++;
        for (int j = i + 1; j < r; j++)
        {
            a[j] = a[i] + j - i;
        }
    }


    //--------------------------------------------------------------------
    public static void main(String args[])
    {
        String vals[] = {"a", "b", "c", "d", "e"};

        for (String[] combo : new Combiner<String>(vals, 4, 3))
        {
            System.out.println(
                    Arrays.toString( combo ));
        }
    }
}

