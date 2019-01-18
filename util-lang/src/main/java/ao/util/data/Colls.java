package ao.util.data;

import ao.util.misc.Mapper;
import ao.util.pass.Filter;
import java.util.*;


/**
 * User: aostrovsky
 * Date: 12-Jun-2009
 * Time: 9:35:02 AM
 */
public class Colls
{
    //--------------------------------------------------------------------
    private Colls() {}


    //--------------------------------------------------------------------
    public static String join(Iterable<?> items, String delimiter)
    {
        if (items == null) return "";

        StringBuilder str     = new StringBuilder();
        boolean       isFirst = true;

        for (Object item : items)
        {
            if (isFirst) {
                isFirst = false;
            } else {
                str.append(delimiter);
            }

            str.append( item );
        }
        
        return str.toString();
    }


    //--------------------------------------------------------------------
    public static <T> List<T> filter(
            Iterable<T> items, Filter<T> filter)
    {
        List<T> filtered = new ArrayList<T>();

        for (T item : items)
        {
            if (filter.accept(item))
            {
                filtered.add( item );
            }
        }

        return filtered;
    }


    //--------------------------------------------------------------------
    public static <F, T> List<T> map(
            Iterable<F> items, Mapper<F, T> mapper)
    {
        List<T> mapped = new ArrayList<T>();

        for (F item : items)
        {
            mapped.add( mapper.map(item) );
        }

        return mapped;
    }


    //--------------------------------------------------------------------
    private static final Comparator<String>
                    caseInsensitiveComparator =
            new Comparator<String>() {
                @Override public int compare(String a, String b) {
                    return a.toLowerCase().compareTo( b.toLowerCase() );
                }
            };

    public static <V> Map<String, V> newCaseInsensitiveMap()
    {
        return new TreeMap<String,V>( caseInsensitiveComparator );
    }

    public static Set<String> newCaseInsensitiveSet()
    {
        return new TreeSet<String>  ( caseInsensitiveComparator );
    }
}
