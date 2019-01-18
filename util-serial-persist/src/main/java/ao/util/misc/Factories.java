package ao.util.misc;

import ao.util.serial.Cloner;
import ao.util.serial.Prototype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: aostrovsky
 * Date: 14-Jul-2009
 * Time: 10:51:45 AM
 */
public class Factories
{
    //--------------------------------------------------------------------
    private Factories() {}


    //--------------------------------------------------------------------
    public static <T> Factory<T> newConstant(final T value)
    {
        return new FactoryImpl<T>() {
			private static final long serialVersionUID = 1L;

			public T newInstance() {
                return value;
            }
        };
    }


    //--------------------------------------------------------------------
    public static <T extends Prototype<T>>
            Factory<T> newPrototype(final T value)
    {
        return new FactoryImpl<T>() {
        	private static final long serialVersionUID = 1L;
        	
            public T newInstance() {
                return value.prototype();
            }
        };
    }


    //--------------------------------------------------------------------
    public static <T> Factory<T> newPrototype(final T value)
    {
        return new FactoryImpl<T>() {
        	private static final long serialVersionUID = 1L;
        	
            public T newInstance() {
                return Cloner.clone( value );
            }
        };
    }


    //--------------------------------------------------------------------
    public static <T> Factory<T> newDefault(final Class<T> clazz)
    {
        return new FactoryImpl<T>() {
        	private static final long serialVersionUID = 1L;
        	
            public T newInstance() {
                try {
                    return clazz.newInstance();
                } catch (Exception e) {
                    throw new Error( e );
                }
            }
        };
    }


    //--------------------------------------------------------------------
    public static <T> Factory<T[]> newArrayClone(final T value[]) {
        return new FactoryImpl<T[]>() {
        	private static final long serialVersionUID = 1L;
        	
            public T[] newInstance() {
                return value.clone();
            }
        };
    }
    public static Factory<int[]> newArrayClone(final int value[]) {
        return new FactoryImpl<int[]>() {
        	private static final long serialVersionUID = 1L;
        	
            public int[] newInstance() {
                return value.clone();
            }
        };
    }
    public static Factory<double[]> newArrayClone(final double value[]) {
        return new FactoryImpl<double[]>() {
        	private static final long serialVersionUID = 1L;
        	
            public double[] newInstance() {
                return value.clone();
            }
        };
    }
    public static <T> Factory<List<T>> newArrayList() {
        return new FactoryImpl<List<T>>() {
        	private static final long serialVersionUID = 1L;
        	
            @Override public List<T> newInstance() {
                return new ArrayList<T>();
            }
        };
    }


    //--------------------------------------------------------------------
    public static <K, V> Factory<Map<K, V>> newHashMap() {
        return new FactoryImpl<Map<K, V>>() {
        	private static final long serialVersionUID = 1L;
        	
            public Map<K, V> newInstance() {
                return new HashMap<K, V>();
            }
        };
    }


    //--------------------------------------------------------------------
    public static interface SerializableFactory<T>
            extends Factory<T>, Serializable {}
    
    public abstract static class FactoryImpl<T>
    		implements SerializableFactory<T>
    {
		private static final long serialVersionUID = 1L;

		@Override public String toString() {
    		return "Factory: " + newInstance();
    	}
    }
}
