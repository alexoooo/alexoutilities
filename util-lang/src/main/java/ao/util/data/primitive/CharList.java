package ao.util.data.primitive;

import java.util.AbstractList;
import java.util.Arrays;
import java.io.Serializable;

/**
 * Date: Jan 16, 2009
 * Time: 5:13:04 AM
 */
public class CharList
        extends    AbstractList<Character>
        implements Serializable
{
	//-------------------------------------------------------------------------
	private static final long serialVersionUID = 2L;
//    private static final char DEFAULT_VALUE    = 0;
	
	
    //-------------------------------------------------------------------------
    private char values[] = new char[ 16 ];
    private int  size     = 0;


    //-------------------------------------------------------------------------
    @Override
    public boolean add(Character value)
    {
        add((char) value);
        return true;
    }

    public void add(char value)
    {
        ensureSize( size + 1 );
        values[ size - 1 ] = value;
    }


    //-------------------------------------------------------------------------
    @Override
    public Character set(int index, Character value)
    {
        return set(index, (char) value);
    }

    public char set(int index, char value)
    {
        char previousValue = getChar( index );

        ensureSize( index + 1 );
        values[index] = value;

        return previousValue;
    }


    //-------------------------------------------------------------------------
    @Override
    public Character get(int index)
    {
        return getChar( index );
    }

    public char getChar(int index)
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
    public char[] toCharArray()
    {
        return Arrays.copyOf(values, size);
    }

    
    //-------------------------------------------------------------------------
    private void ensureSize(int cap)
    {
        if (values.length < cap)
        {
            int newLength = Math.max(cap, values.length * 2);
            values = Arrays.copyOf(values, newLength);
        }

        size = Math.max(size, cap);
    }
}
