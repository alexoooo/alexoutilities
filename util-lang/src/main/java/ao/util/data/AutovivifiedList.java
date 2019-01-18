package ao.util.data;

import ao.util.misc.Factories;
import ao.util.misc.Factory;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Date: Sep 21, 2008
 * Time: 1:03:07 PM
 */
public class AutovivifiedList<T> extends AbstractList<T>
{
    //--------------------------------------------------------------------
    public static void main(String[] args) {
        AutovivifiedList<Integer> test =
                new AutovivifiedList<Integer>((Integer) 3);
        test.get(3);
    }


    //--------------------------------------------------------------------
    private final boolean      EXTEND_ON_GET;
    private final Factory<T>   DEFAULT;
    private final ArrayList<T> DELEGET;


    //--------------------------------------------------------------------
    public AutovivifiedList(
            Factory<T> defaultValue,
            boolean    extendOnGet,
            int        initialSize)
    {
        DEFAULT       = defaultValue;
        DELEGET       = new ArrayList<T>(initialSize);
        EXTEND_ON_GET = extendOnGet;
    }
    public AutovivifiedList(T defaultValue, int initialSize)
    {
        this(Factories.newConstant(defaultValue), true, initialSize);
    }
    public AutovivifiedList(Factory<T> defaultValue)
    {
        this(defaultValue, true, 16);
    }
    public AutovivifiedList(T defaultValue)
    {
        this(defaultValue, 16);
    }
    public AutovivifiedList(int initialSize)
    {
        this((T) null, initialSize);
    }
    public AutovivifiedList()
    {
        this((T) null );
    }


    //--------------------------------------------------------------------
    public T get(int index)
    {
        if (index < DELEGET.size()) {
            return DELEGET.get( index );
        } else {
            if (EXTEND_ON_GET) {
                extend(index);

                T addend = DEFAULT.newInstance();
                DELEGET.add( addend );
                return addend;
            } else {
                return DEFAULT.newInstance();
            }
        }
    }


    //--------------------------------------------------------------------
    @Override
    public boolean add(T element)
    {
        set(size(), element);
        return true;
    }


    //--------------------------------------------------------------------
    @Override
    public T set(int index, T element)
    {
        T val = get(index);

        extend(index + 1);
        DELEGET.set(index, element);

        return val;
    }

    private void extend(int upToSize) {
        DELEGET.ensureCapacity(upToSize);
        while (upToSize > DELEGET.size())
        {
            DELEGET.add( DEFAULT.newInstance() );
        }
    }


    //--------------------------------------------------------------------
    @Override
    public void clear()
    {
        DELEGET.clear();
    }


    //--------------------------------------------------------------------
    @Override
    public int size()
    {
        return DELEGET.size();
    }
}
