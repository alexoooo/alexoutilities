package ao.util.async;

import java.util.concurrent.CountDownLatch;

/**
 * User: aostrovsky
 * Date: 2-Sep-2009
 * Time: 3:47:49 PM
 */
public class BlockingReference<T>
{
    //--------------------------------------------------------------------
    private final CountDownLatch IS_SET;
    private       T              val;


    //--------------------------------------------------------------------
    public BlockingReference()
    {
        IS_SET = new CountDownLatch(1);
    }


    //--------------------------------------------------------------------
    public void set(T value)
    {
        synchronized (this) {
            assert val == null
                   : "can only be set once";

            val = value;
        }

        IS_SET.countDown();
    }


    //--------------------------------------------------------------------
    public void waitForSet()
    {
        try
        {
            IS_SET.await();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    
    //--------------------------------------------------------------------
    public T get() {
        return val;
    }


    //--------------------------------------------------------------------
    public T getWhenSet() {
        waitForSet();
        return val;
    }
}
