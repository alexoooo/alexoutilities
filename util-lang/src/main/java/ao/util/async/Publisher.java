package ao.util.async;

import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

/**
 * created: Apr 1, 2005  12:47:58 AM
 *
 * Idea inspired by page 100 of "Holub on Patterns"
 *
 * Need to upgrade this to be 100% asynchronouse using Jave 1.5 threading
 * libraries if more performance is necessary.
 */
public class Publisher<T>
{
    //--------------------------------------------------------------------
    public static interface Distributor<T>
    {
        void deliverTo( T subscriber ); // the Visitor pattern's "visit" method.
    }


    //--------------------------------------------------------------------
    private volatile Node subscribers = null;

    public synchronized void publish( final Distributor<T> deliveryAgent )
    {
        if ( 0.01 > Math.random() )
        {
            cleanUpGarbageCollectedSubscribers();
        }

        for (Node cursor  = subscribers;
                  cursor != null;
                  cursor  = cursor.next)
        {
            cursor.accept( deliveryAgent );
        }
    }

    public synchronized void subscribe( final T subscriber )
    {
        subscribe( subscriber, false );
    }
    public synchronized void subscribe(
            final T subscriber,
            final boolean useWeakReference)
    {
        subscribers =
                new Node( subscriber, subscribers, useWeakReference );
    }

    // being unsynchronized might cause a subscriber to recieve a
    //  message after is canceld the subscription.
    public synchronized void cancelSubscription( final T subscriber )
    {
        subscribers = subscribers.remove( subscriber );
    }

    private synchronized void cleanUpGarbageCollectedSubscribers()
    {
        subscribers =
                (subscribers == null
                 ? null
                 : subscribers.removeGarbageCollectedSubscribers() );
    }


    //--------------------------------------------------------------------
    private class Node
    {
        private final WeakReference<T> subscriberRef;
        private final T subscriberVar;

        private final Node next;


//        public Node( final T subscriber, final Node next )
//        {
//            this( subscriber, next, false );
//        }

        public Node(final T subscriber,
                    final Node    next,
                    final boolean useWeakReference )
        {
            this.next = next;

            if (useWeakReference && subscriber != null)
            {
                this.subscriberRef = new WeakReference<T>( subscriber );
                this.subscriberVar = null;
            }
            else
            {
                this.subscriberRef = null;
                this.subscriberVar = subscriber;
            }
        }

        public T subscriber()
        {
            if (subscriberVar != null) return subscriberVar;
            if (subscriberRef != null) return subscriberRef.get();
            return null;
        }

//        public Node next()
//        {
//            return next;
//        }

        private boolean isSubscriberWeak()
        {
            return subscriberRef != null;
        }


        public Node remove( final Object target )
        {
            assert target != null;

            if (target.equals( subscriber() ))
            {
                return next;
            }

            if ( next == null )
            {
                throw new NoSuchElementException( target.toString() );
            }

            return new Node(subscriber(),
                            next.remove( target ),
                            isSubscriberWeak());
        }

        public Node removeGarbageCollectedSubscribers()
        {
            final Node tail = nextWithSubscribersGarbageCollected();

            if (subscriber() == null) return tail;
            return new Node(subscriber(), tail, isSubscriberWeak());
        }

        private Node nextWithSubscribersGarbageCollected()
        {
            if (next == null) return null;
            return next.removeGarbageCollectedSubscribers();
        }


        public void accept( final Distributor<T> deliveryAgent )
        {
            final T subscriber = subscriber();
            if ( subscriber != null )
            {
                deliveryAgent.deliverTo( subscriber );
            }
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return !(next != null ? !next.equals(node.next)
                                  : node.next != null) &&
                   !(subscriberRef != null
                           ? !subscriberRef.equals(node.subscriberRef)
                           : node.subscriberRef != null) &&
                   !(subscriberVar != null
                           ? !subscriberVar.equals(node.subscriberVar)
                           : node.subscriberVar != null);

        }

        @Override public int hashCode() {
            int result = subscriberRef != null
                    ? subscriberRef.hashCode() : 0;
            result = 31 * result + (subscriberVar != null
                    ? subscriberVar.hashCode() : 0);
            result = 31 * result + (next != null
                    ? next.hashCode() : 0);
            return result;
        }
    }


    //--------------------------------------------------------------------
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher<?> publisher = (Publisher<?>) o;
        return !(subscribers != null
                ? !subscribers.equals(publisher.subscribers)
                : publisher.subscribers != null);

    }

    @Override public int hashCode() {
        return subscribers != null ? subscribers.hashCode() : 0;
    }


    //--------------------------------------------------------------------
    public static void main(String args[])
    {
        TestPublisher p = new TestPublisher();
        p.addListener(new TestListener());
        p.publish();
    }

    private static class TestPublisher
    {
        private Publisher<TestListener> publisher =
                new Publisher<TestListener>();

        public void addListener(TestListener l)
        {
            publisher.subscribe(l);
        }

        public void publish()
        {
            publisher.publish(new Distributor<TestListener>() {
                public void deliverTo(TestListener subscriber) {
                    subscriber.listen();
                }
            });
        }
    }

    private static class TestListener
    {
        public void listen() {System.out.println("listening");}
    }
}