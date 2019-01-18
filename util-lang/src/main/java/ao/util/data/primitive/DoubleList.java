package ao.util.data.primitive;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;

/**
 * User: alex
 * Date: 30-Jun-2009
 * Time: 8:21:45 PM
 */
public class DoubleList
        extends    AbstractList<Double>
        implements Serializable
{
	//-------------------------------------------------------------------------
	private static final long   serialVersionUID = 2L;
	private static final double DEFAULT_VALUE    = 0;

	
    //-------------------------------------------------------------------------
    private double values[];
    private int    size = 0;


    //-------------------------------------------------------------------------
    public DoubleList()
    {
        this( 16 );
    }

    public DoubleList(int initialSize)
    {
        this(initialSize, 0);
    }
    public DoubleList(int initialSize, int initialPosition)
    {
        values = new double[ initialSize ];
        size   = initialPosition;
    }

    public DoubleList(double... copyValues)
    {
        values = copyValues;
    }


    //-------------------------------------------------------------------------
    @Override
    public boolean add(Double value)
    {
        add((double) value);
        return true;
    }

    public void add(double value)
    {
        ensureSize( size + 1 );
        values[ size - 1 ] = value;
    }


    //-------------------------------------------------------------------------
    public void increment(int index, double by)
    {
        ensureSize( index + 1 );
        values[index] += by;
    }

    
    //-------------------------------------------------------------------------
    @Override
    public Double set(int index, Double value)
    {
        return set(index, (double) value);
    }

    public double set(int index, double value)
    {
        double previousValue = getDouble( index );

        ensureSize( index + 1 );
        values[index] = value;

        return previousValue;
    }


    //-------------------------------------------------------------------------
    @Override
    public Double get(int index)
    {
        return getDouble(index);
    }

    public double getDouble(int index)
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
    public double[] toDoubleArray()
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
