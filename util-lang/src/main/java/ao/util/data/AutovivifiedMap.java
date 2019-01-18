package ao.util.data;

import ao.util.misc.Factories;
import ao.util.misc.Factory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: aostrovsky
 * Date: 14-Jul-2009
 * Time: 10:45:35 AM
 * 
 * Does not support null keys or values.
 * 
 */
@SuppressWarnings("unchecked")
public class AutovivifiedMap<K, V> implements Map<K, V>
{
    //--------------------------------------------------------------------
    public static void main(String[] args) {
//    	AutovivifiedMap<String, String> m =
//    		new AutovivifiedMap<String, String>("n/a");
//    	System.out.println( m.get("hello") );
    	
    	Map<String, Map<String, String>> twoDim = twoDim();
//    	System.out.println( twoDim.get("hello") );
//    	twoDim.get("hello").get("world");
    	
//    	Map<String, Map<String, String>> twoDim =
//    		new AutovivifiedMap<String, Map<String, String>>(
//    				new HashMap<String, Map<String, String>>(),
//    				new Factory<Map<String, String>>() {
//                        @Override public Map<String, String> newInstance() {
//                            return new AutovivifiedMap<String, String>(
//                            		new HashMap<String, String>(),
//                            		Factories.newConstant("n/a"));
//                        }}
//    		);
    	
    	System.out.println( twoDim.get("hello") );
    	System.out.println( twoDim.get("hello").get("world") );
    	
    	
//        @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
//        AutovivifiedMap<Integer, int[]> m =
//                new AutovivifiedMap<Integer, int[]>(
//                        Factories.newArrayClone(new int[1]));
//
//        for (int i = 0; i < 100; i++)
//        {
//            m.get(i % 10)[0]++;
//        }
    }


    //--------------------------------------------------------------------
    public static <K1, K2, V> Map<K1, Map<K2, V>> twoDim()
    {
        return new AutovivifiedMap<K1, Map<K2,V>>(
                new Factory<Map<K2, V>>() {
                    @Override public Map<K2, V> newInstance() {
                        return new AutovivifiedMap<K2,V>();
                    }});
    }


    //--------------------------------------------------------------------
    private final Factory<V> DEFAULT;
    private final Map<K, V>  DELEGET;


    //--------------------------------------------------------------------
    public AutovivifiedMap()
    {
        this(new HashMap<K,V>());
    }
    public AutovivifiedMap(
            V defaultValue)
    {
        this(new HashMap<K,V>(),
             Factories.newConstant(defaultValue));
    }
    public AutovivifiedMap(
            Factory<V> defaultValue)
    {
        this(new HashMap<K,V>(), defaultValue);
    }
    public AutovivifiedMap(
            Map<K, V> deleget)
    {
        this(deleget, Factories.<V>newConstant(null));
    }
    public AutovivifiedMap(
            Map<K, V>  deleget,
            Factory<V> defaultValue)
    {
        DELEGET = deleget;
        DEFAULT = defaultValue;
    }
    
    
    //--------------------------------------------------------------------
    public int size() {
        return DELEGET.size();
    }

    public boolean isEmpty() {
        return DELEGET.isEmpty();
    }

    public boolean containsKey(Object key) {
        return DELEGET.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return DELEGET.containsValue(value);
    }

    public V get(Object key) {
//    	System.out.println(
//    			DELEGET.get(key));
    	
        //noinspection SuspiciousMethodCalls
    	V existing = DELEGET.get(key);
        if (existing != null) {
            return existing;
        } else {
            V auto = DEFAULT.newInstance();
            DELEGET.put((K) key, auto);
            return auto;
        }
    }

    public V put(K key, V value) {
        return DELEGET.put(key, value);
    }

    public V remove(Object key) {
        return DELEGET.remove(key);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        DELEGET.putAll(m);
    }

    public void clear() {
        DELEGET.clear();
    }

    public Set<K> keySet() {
        return DELEGET.keySet();
    }

    public Collection<V> values() {
        return DELEGET.values();
    }

    public Set<Entry<K, V>> entrySet() {
        return DELEGET.entrySet();
    }


    //--------------------------------------------------------------------
    @Override public String toString()
    {
        return DELEGET.toString();
    }
}
