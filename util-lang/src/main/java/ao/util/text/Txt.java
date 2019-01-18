package ao.util.text;

import java.util.Iterator;

/**
 *
 */
public class Txt
{
    //--------------------------------------------------------------------
    private Txt() {}


    //--------------------------------------------------------------------
    public static String nTimes(String s, int n) {
		StringBuilder buf = new StringBuilder(s.length() * n);
		for (int i = 0; i < n; i++) {
			buf.append(s);
		}
		return buf.toString();
	}


    //--------------------------------------------------------------------
    public static Iterable<String> toString(
            final Iterable<?> items)
    {
        return new Iterable<String>() {
            @Override public Iterator<String> iterator() {
                final Iterator<?> itr = items.iterator();
                return new Iterator<String>() {
                    @Override public boolean hasNext() {
                        return itr.hasNext();
                    }

                    @Override public String next() {
                        return itr.next().toString();
                    }

                    @Override public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public static String[] toString(
            Object[] items)
    {
        String[] strings = new String[ items.length ];
        for (int i = 0, itemsLength = items.length;
                 i < itemsLength; i++) {
            strings[ i ] = items[ i ].toString();
        }
        return strings;
    }
}
