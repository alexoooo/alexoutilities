package ao.util.persist.fs;

import ao.util.io.Dirs;
import ao.util.math.crypt.MD5;
import ao.util.persist.PersistentObjects;
import ao.util.serial.Serializer;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

public class DirMap<K, V>
		extends AbstractMap<K, V>
{
	//--------------------------------------------------------------------
	private static final Logger LOG =
        	Logger.getLogger(DirMap.class);
	
	private static final String ENTRY_FILE_SUFFIX = ".entry";
	
	
	//--------------------------------------------------------------------
	public static void main(String[] args) {
		Map<Object, Number> m =
				new DirMap<Object, Number>("test/dir-map");
		
		m.put("hello", 1337);
		m.put("world", 12345);
		m.put("blah!", Math.random());
		m.put(Math.random(), Math.random());
		
		System.out.println(m);
		m.clear();
	}
	
	
	//--------------------------------------------------------------------
	private final File      dir;
	private final Map<K, V> backing;
	
	
	//--------------------------------------------------------------------
	public DirMap(String backingDir) {
		this(new File(backingDir));
	}
	
	public DirMap(File backingDir)
	{
		dir   = Dirs.get(backingDir);
		backing = new HashMap<K, V>();
		
		reLoad();
	}
	
	
	//--------------------------------------------------------------------
	private void reLoad()
	{
		backing.clear();
		
		for (File f : dir.listFiles()) {
			if (f.isDirectory() ||
					! f.getName().endsWith( ENTRY_FILE_SUFFIX )) {
				continue;
			}
			
			Map.Entry<K, V> entry = read(f);
			if (entry != null) {
				backing.put(entry.getKey(), entry.getValue());
			} else {
                if (f.delete()) {
                    LOG.info("Purged " + f);
                } else {
                    LOG.error("Unable to purge " + f);
                }
            }
		}
	}
	
	
	//--------------------------------------------------------------------
//	private Map.Entry<K, V> read(K key)
//	{
//		return read( encodeFile(key) );
//	}
	
	private Map.Entry<K, V> read(File entryFile)
	{
        try {
		    return PersistentObjects.retrieve(entryFile);
        } catch (Throwable ignored) {
            return null;
        }
	}
	
	
	//--------------------------------------------------------------------
	private boolean delete(Object key)
	{
        return encodeFile(key).delete();
	}
	
	
	//--------------------------------------------------------------------
	private void write(K key, V value)
	{
		write(encodeFile(key), key, value);
	}
	
	private void write(File entryFile, K key, V value)
	{
		PersistentObjects.persist(
				new Entry(key, value), entryFile);
	}
	
	
	//--------------------------------------------------------------------
	private File encodeFile(Object key) {
		return encodeFile(encodeKey(key));
	}
	
	private File encodeFile(String keyCode) {
		return new File(dir, keyCode + ENTRY_FILE_SUFFIX);
	}
	
	private String encodeKey(Object key) {
		return MD5.hexDigest(Serializer.toXml(key));
	}
	
	
	//--------------------------------------------------------------------
	@Override public Set<Map.Entry<K, V>> entrySet()
	{
		return new EntrySet();
	}
	
	
	//--------------------------------------------------------------------
	@Override public V put(K key, V value)
	{
		V prev = backing.put(key, value);
		write(key, value);
		return prev;
	}
	
	@Override public boolean containsKey(Object key) {
		return backing.containsKey(key);
	}
	
	@Override public V get(Object key) {
		return backing.get(key);
	}
	
	public V remove(Object key) {
		delete(key);
		return backing.remove(key);
	}
	
	
	//--------------------------------------------------------------------
	private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
		@Override
		public Iterator<Map.Entry<K, V>> iterator() {
			return new Iterator<Map.Entry<K, V>>() {
				private final Iterator<Map.Entry<K, V>>
						itr = backing.entrySet().iterator(); 
				private       Map.Entry<K, V> prev = null;
				
				@Override
				public boolean hasNext() {
					return itr.hasNext();
				}

				@Override
				public Map.Entry<K, V> next() {
					return (prev = itr.next());
				}

				@Override
				public void remove() {
					itr.remove();
					delete( prev.getKey() );
				}
			};
		}
		
		@Override
		public int size() {
			return backing.size();
		}
	}
	
	
	//--------------------------------------------------------------------
	private class Entry implements Map.Entry<K, V>
	{
		private final K key;
		private       V value;
		
		public Entry(K key, V value) {
			this.key   = key;
			this.value = value;
		}
		
		@Override public K getKey() {
			return key;
		}

		@Override public V getValue() {
			return value;
		}

		@Override public V setValue(V val) {
			V prev = put(key, val);
			value = val;
			return prev;
		}
		
		@Override public String toString() {
			return key + " => " + value;
		}
	}
}
