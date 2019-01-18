package ao.util.persist.fs;

import ao.util.data.primitive.IntList;
import ao.util.io.Dirs;
import ao.util.persist.PersistentInts;
import ao.util.persist.PersistentObjects;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

public class DirCollection<T> extends AbstractCollection<T>
{
	//--------------------------------------------------------------------
	private static final Logger LOG =
			Logger.getLogger(DirCollection.class);
	
    private static final String NEXT_INDEX_FILE = "nextIndex.int";
    private static final String LIST_SIZE_FILE  = "size.int";
    
    
    //--------------------------------------------------------------------
    public static void main(String[] args)
    {
		Collection<Double> coll =
			new DirCollection<Double>("test/dir-coll");
		
		coll.add( Math.random() );
		
		System.out.println(coll);
		
		coll.clear();
	}
    
    
    //--------------------------------------------------------------------
    private final File    dir;
    private       int     nextIndex;
    private       int     size;
    private       boolean listChanged;
    private       int[]   cachedIndexNames;
    
    
	//--------------------------------------------------------------------
    public DirCollection(String directory) {
    	this(new File(directory));
    }
	public DirCollection(File directory) {
		dir = Dirs.get( directory );

        nextIndex   = retrieveNumber( NEXT_INDEX_FILE );
        size        = retrieveNumber( LIST_SIZE_FILE  );
        listChanged = false;
        
        purge();
	}
	
	
	//--------------------------------------------------------------------
	private void purge()
    {
        int purged = 0;
        for (int index : indexNames()) {
            try {
                retrieveObject(index);
            } catch (Throwable t) {
                LOG.trace("purging " + file(index));
                removeIndex(index);
                purged++;
            }
        }
        
        if (purged > 0) {
            LOG.info("Purged " + purged + " from " + dir);
        }
    }
	
	
	//--------------------------------------------------------------------
    private int retrieveNumber(String name)
    {
        int[] storedNumber = PersistentInts.retrieve(file(name));
        return (storedNumber == null ? 0 : storedNumber[0]);
    }

    private void persistNumber(int value, String name)
    {
        PersistentInts.persist(
                new int[]{value}, file(name));
    }

    private T retrieveObject(int index)
    {
        // .<T> is mandatory in order to compile
        return PersistentObjects.<T>retrieve( file(index) );
    }

    private void persistObject(int index, T value)
    {
        PersistentObjects.persist(
                value, file(index));
    }

    private File file(int index) {
    	return file(String.valueOf(index));
    }
    private File file(String name) {
        return new File(dir, name);
    }
    
    
    //--------------------------------------------------------------------
    private int[] indexNames()
    {
        if (! listChanged && cachedIndexNames != null) {
            return cachedIndexNames;
        }
        
        IntList names = new IntList();
        for (File sib : dir.listFiles()) {
        	if (sib.isDirectory()) continue;
        	
        	try {
        		names.add(
    				Integer.parseInt( sib.getName() ));
        	} catch (NumberFormatException ignored) {}
        }
        
        int[] indexes = names.toIntArray();
        Arrays.sort(indexes);
        cachedIndexNames = indexes;
        return cachedIndexNames;
    }
	
    
    //--------------------------------------------------------------------
    private boolean removeIndex(int index)
    {
        if (! file(index).delete()) {
            LOG.error("Could not delete file " + index);
            return false;
        } else {
            persistNumber(--size, LIST_SIZE_FILE);

            listChanged = true;
            LOG.debug("removed: nextIndex = " + nextIndex +
                                  ", size = "  + size);
            return true;
        }
    }
    
    
	
	//--------------------------------------------------------------------
	@Override
	public boolean add(T value) {
		persistObject(nextIndex++, value);
        persistNumber(nextIndex, NEXT_INDEX_FILE);
        persistNumber(++size, LIST_SIZE_FILE);

        listChanged = true;
        LOG.debug("added: nextIndex = " + nextIndex +
                            ", size = "  + size);        
		return true;
	}
	
	
	//--------------------------------------------------------------------
	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}
	
	
	//--------------------------------------------------------------------
	@Override
	public int size() {
		return size;
	}
	
	
	//--------------------------------------------------------------------
	private class Itr implements Iterator<T>
	{
		private int   itrNextIndex  = 0;
		private int[] itrIndexNames = indexNames();
		
		@Override
		public boolean hasNext() {
			return itrNextIndex < itrIndexNames.length;
		}
		
		@Override
		public T next() {
			return retrieveObject( itrIndexNames[ itrNextIndex++ ] );
		}
		
		@Override
		public void remove() {
			removeIndex( itrIndexNames[ itrNextIndex - 1 ] );
		}
	}
	
	
	//--------------------------------------------------------------------
	public List<T> asList() {
		return new AbstractList<T>() {
			@Override
			public T get(int index) {
				return retrieveObject(
						 indexNames()[ index ] );
			}

			@Override
			public int size() {
				return size;
			}
		};
	}
}
