package ao.util.data.primitive;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;

/**
 * Date: Jan 15, 2009
 * Time: 2:39:47 PM
 */
public class ShortList
        extends    AbstractList<Short>
        implements Serializable
{
	//-------------------------------------------------------------------------
	private static final long  serialVersionUID = 2L;
//    private static final short DEFAULT_VALUE    = 0;

	
    //-------------------------------------------------------------------------
    private short values[] = new short[ 16 ];
    private int   size     = 0;


    //-------------------------------------------------------------------------
    @Override
    public void add(int index, Short value)
    {
        if (index == size) {
            add( value );
        } else {
            int length = size - index;
            ensureSize( size + 1 );
            System.arraycopy(
                    values,
                    index,
                    values,
                    index + 1,
                    length);
            set(index, value);
        }
    }

    @Override
    public boolean add(Short value)
    {
        add( (short) value );
        return true;
    }

    public void add(short value)
    {
        ensureSize( size + 1 );
        values[ size - 1 ] = value;
    }


    //-------------------------------------------------------------------------
    @Override
    public Short set(int index, Short value)
    {
        return set(index, (short) value);
    }

    public short set(int index, short value)
    {
        short previous = getShort( index );

        ensureSize( index + 1 );
        values[ index ] = value;

        return previous;
    }


    //-------------------------------------------------------------------------
    @Override
    public Short get(int index)
    {
        return getShort( index );
    }

    public short getShort(int index)
    {
        return values[ index ];
    }


    //-------------------------------------------------------------------------
    @Override
    public Short remove(int index)
    {
        Short current = get(index);

        if (index != (size - 1))
        {
            int length = size - index;
            System.arraycopy(
                    values,
                    index + 1,
                    values,
                    index,
                    length);
        }

        size--;

        return current;
    }


    //-------------------------------------------------------------------------
    @Override
    public int size()
    {
        return size;
    }


    //-------------------------------------------------------------------------
    public short[] toShortArray()
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
