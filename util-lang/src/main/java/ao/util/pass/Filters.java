package ao.util.pass;

import java.util.regex.Pattern;

/**
 * Date: Jan 21, 2009
 * Time: 2:40:14 PM
 */
public class Filters
{
    //--------------------------------------------------------------------
    private Filters() {}


    //--------------------------------------------------------------------
    public static <T> Filter<T> any()
    {
        return new Filter<T>() {
            public boolean accept(T instance) {
                return true;
            }
        };
    }
    

    //--------------------------------------------------------------------
    public static <T> Filter<T> and(
            final Filter<T> a, final Filter<T> b)
    {
        return new Filter<T>() {
            public boolean accept(T instance) {
                return a.accept(instance) && b.accept(instance);
            }
        };
    }


    //--------------------------------------------------------------------
    public static <T> Filter<T> or(
            final Filter<T> a, final Filter<T> b)
    {
        return new Filter<T>() {
            public boolean accept(T instance) {
                return a.accept(instance) || b.accept(instance);
            }
        };
    }


    //--------------------------------------------------------------------
    public static <T> Filter<T> not(final Filter<T> a)
    {
        return new Filter<T>() {
            public boolean accept(T instance) {
                return !a.accept(instance);
            }
        };
    }
    

    //--------------------------------------------------------------------
    public static Filter<String> regex(String regex)
    {
        return regex(Pattern.compile(regex));
    }

    public static Filter<String> regex(final Pattern regex)
    {
        return new Filter<String>() {
            public boolean accept(String instance) {
                return regex.matcher(instance).matches();
            }
        };
    }
}
