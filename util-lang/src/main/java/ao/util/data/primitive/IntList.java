package ao.util.data.primitive;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;

/**
 * Date: Oct 15, 2008
 * Time: 2:40:42 PM
 */
public class IntList
        extends    AbstractList<Integer>
        implements Serializable
{
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 2L;
	private static final int  DEFAULT_VALUE    = 0;

	
    //-------------------------------------------------------------------------
    public static IntList addTo(IntList intList, int value)
    {
        IntList list =
                (intList == null)
                ? new IntList()
                : intList;

        list.add( value );

        return list;
    }
    public static int sizeOf(IntList intList)
    {
        return (intList == null)
               ? 0
               : intList.size();
    }

    public static int sizeSum(IntList[] lists)
    {
        if (lists == null) return -1;

        int sum = 0;
        for (IntList list : lists)
        {
            sum += (list == null
                    ? 0
                    : list.size());
        }
        return sum;
    }


    //-------------------------------------------------------------------------
    private int values[] = new int[ 16 ];
    private int size     = 0;


    //-------------------------------------------------------------------------
    @Override
    public boolean add(Integer value)
    {
        add( (int) value );
        return true;
    }

    public void add(int value)
    {
        ensureSize( size + 1 );
        values[ size - 1 ] = value;
    }


    //-------------------------------------------------------------------------
    public int increment(int index)
    {
        return increment(index, 1);
    }

    public int increment(int index, int by)
    {
        ensureSize(index + 1);
        values[ index ] += by;
        return values[ index ];
    }


    //-------------------------------------------------------------------------
    @Override
    public Integer set(int index, Integer value)
    {
        return set(index, (int) value);
    }

    public int set(int index, int value)
    {
        int previous = getInt( index );

        ensureSize( index + 1);
        values[ index ] = value;

        return previous;
    }


    //-------------------------------------------------------------------------
    @Override
    public Integer get(int index)
    {
        return getInt( index );
    }

    public int getInt(int index)
    {
        return index >= values.length
               ? DEFAULT_VALUE : values[ index ];
    }


    //-------------------------------------------------------------------------
    @Override
    public int size()
    {
        return size;
    }


    //-------------------------------------------------------------------------
    public int[] toIntArray()
    {
        return Arrays.copyOf(values, size);
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return Arrays.toString( toIntArray() );
    }


    //-------------------------------------------------------------------------
    private void ensureSize(int cap)
    {
        if (values.length < cap)
        {
            int newLength = Math.max(cap, values.length * 2);
            values        = Arrays.copyOf(values, newLength);
        }

        size = Math.max(size, cap);
    }
}
