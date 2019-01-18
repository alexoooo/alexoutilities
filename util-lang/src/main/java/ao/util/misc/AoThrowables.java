package ao.util.misc;

/**
 * User: aostrovsky
 * Date: 3-Feb-2010
 * Time: 3:02:28 PM
 */
public class AoThrowables
{
    //--------------------------------------------------------------------
    private AoThrowables() {}


    //--------------------------------------------------------------------
    public static RuntimeException wrap(Throwable exception)
    {
        propagateIfInstanceOf(
                exception, Error.class);

        propagateIfInstanceOf(
                exception, RuntimeException.class);

        throw new RuntimeException( exception );
    }

    public static <T extends Throwable> void
            propagateIfInstanceOf(
                    Throwable throwable,
                    Class<T> declaredType
            ) throws T
    {
        if (declaredType.isInstance(throwable))
        {
            throw declaredType.cast(throwable);
        }
    }


    //--------------------------------------------------------------------
    public static String unrollMessages(Throwable throwable)
    {
        StringBuilder str = new StringBuilder();

        for (Throwable cursor = throwable;
                       cursor != null;
                       cursor = cursor.getCause())
        {
            if (cursor.getMessage() != null)
            {
                if (str.length() > 0)
                {
                    str.append( " | " );
                }

                str.append( cursor.getMessage() );
            }
        }

        if (str.length() == 0)
        {
            str.append("N/A");
        }

        return str.toString();
    }
}
