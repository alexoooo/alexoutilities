package ao.util.data.primitive;

import java.util.AbstractList;
import java.util.Arrays;
import java.io.Serializable;

/**
 * Date: Jan 16, 2009
 * Time: 2:56:23 AM
 */
public class LongList
        extends    AbstractList<Long>
        implements Serializable
{
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 2L;
//    private static final long DEFAULT_VALUE    = 0;

	
    //-------------------------------------------------------------------------
    public static LongList addTo(LongList intList, long value)
    {
        LongList list =
                (intList == null)
                ? new LongList()
                : intList;

        list.add( value );

        return list;
    }

    public static int sizeOf(LongList intList)
    {
        return (intList == null)
               ? 0
               : intList.size();
    }

    public static int sizeSum(LongList[] lists)
    {
        if (lists == null) return -1;

        int sum = 0;
        for (LongList list : lists)
        {
            sum += (list == null
                    ? 0
                    : list.size());
        }
        return sum;
    }


    //-------------------------------------------------------------------------
    private long values[] = new long[ 16 ];
    private int  size     = 0;


    //-------------------------------------------------------------------------
    @Override
    public boolean add(Long value)
    {
        add( (long) value );
        return true;
    }

    public void add(long value)
    {
        ensureSize( size + 1 );
        values[ size - 1 ] = value;
    }


    //-------------------------------------------------------------------------
    @Override
    public Long set(int index, Long value)
    {
        return set(index, (long) value);
    }

    public long set(int index, long value)
    {
        long previous = getLong( index );

        ensureSize( index + 1 );
        values[ index ] = value;

        return previous;
    }


    //-------------------------------------------------------------------------
    @Override
    public Long get(int index)
    {
        return getLong(index);
    }

    public long getLong(int index)
    {
        return values[ index ];
    }


    //-------------------------------------------------------------------------
    @Override
    public int size()
    {
        return size;
    }


    //-------------------------------------------------------------------------
    public long[] toLongArray()
    {
        return Arrays.copyOf(values, size);
    }


    //-------------------------------------------------------------------------
    private void ensureSize(int cap)
    {
        if (values.length < cap)
        {
            int newLength =
                    Math.max(cap, values.length * 2);
            values = Arrays.copyOf(values, newLength);
        }

        size = Math.max(size, cap);
    }
}
