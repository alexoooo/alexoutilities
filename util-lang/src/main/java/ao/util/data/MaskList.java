package ao.util.data;

import java.util.AbstractList;
import java.util.List;

/**
 * User: alex
 * Date: 23-Jun-2009
 * Time: 8:54:26 PM
 */
public class MaskList<T> extends AbstractList<T>
{
    //--------------------------------------------------------------------
    private final List<T> DELEGET;
    private final int     HIDE;


    //--------------------------------------------------------------------
    public MaskList(List<T> subtractFrom, int subtractIndex)
    {
        DELEGET = subtractFrom;
        HIDE    = subtractIndex;
    }


    //--------------------------------------------------------------------
    @Override public T get(int i) {
        return DELEGET.get(mask(i));
    }

    @Override public int size() {
        return DELEGET.size() -
                offset(DELEGET.size());
    }


    //--------------------------------------------------------------------
    private int mask(int index)
    {
        return index + offset(index);
    }

    private int offset(int atIndex)
    {
        return (atIndex > HIDE ? 1 : 0);
    }
}
