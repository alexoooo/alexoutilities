package ao.util.persist;

import ao.util.async.Publisher;
import ao.util.async.Throttle;
import ao.util.io.Dirs;
import ao.util.math.rand.Rand;
import ao.util.serial.Serializer;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * User: aostrovsky
 * Date: 16-Jun-2009
 * Time: 6:57:40 PM
 */
public class PersistentObjects
{
    //--------------------------------------------------------------------
    private static final Logger LOG =
            Logger.getLogger(PersistentObjects.class);

    @SuppressWarnings("serial")
	public static void main(String[] args) {
        File storeFile = new File(Dirs.get(
            "test"), "PersistentObjects.obj");

        Map<String, Object> dictionary =
                new TreeMap<String, Object>()
                {{
                    put("a", Rand.nextDouble());
                    put("b", 'x');
                    put("c", new Throttle(420));
                    put("d", new Publisher<Object>());
                    put("e", "test");
                }}
                ;
//        dictionary.put("a", Rand.nextDouble());
//        dictionary.put("b", new Condition(true));
////        dictionary.put("c", new Throttle(420));
//        dictionary.put("d", new Publisher());
//        dictionary.put("e", "test");

        persist(dictionary, storeFile);

        System.out.println(
                dictionary.equals(
                        retrieve(storeFile)));
    }


    //--------------------------------------------------------------------
    private PersistentObjects() {}


    //--------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public static <T> T retrieve(String fromFile)
    {
        return (T) retrieve(new File(fromFile));
    }

    @SuppressWarnings("unchecked")
    public static <T> T retrieve(File fromFile)
    {
        byte data[] = PersistentBytes.retrieve( fromFile );
        if (data == null) return null;
        return (T) Type.fromData(data).retrieve(data);
    }

//    @SuppressWarnings("unchecked")
//    public static <T> T retrieve(String fromString)
//    {
//
//    }


    //--------------------------------------------------------------------
    public static void persist(
            Object value, String toFile)
    {
        persist(value, new File(toFile));
    }

    public static void persist(
            Object value, File toFile)
    {
        try {
            doPersist(value, toFile);
        } catch (IOException e) {
            throw new Error( e );
        }
    }

    // try fast, [then default,] then xml
    private static void doPersist(
            Object value, File toFile) throws IOException
    {
//        try {
            if (value instanceof Serializable ||
                    isDefaultConstructed(value))
            {
                doPersist(value, toFile, Type.FAST);
                if (retrieve(toFile) != null)
                {
                    return;
                }
            }
//        }
//        catch (NoClassDefFoundError cnf) {
//            throw cnf; // JBoss classes not included
//        }
//        catch (Error ignored) {}

        doPersist(value, toFile, Type.XML);
    }

    private static void doPersist(
            Object value, File toFile, Type type) throws IOException
    {
        LOG.debug("persisting " + toFile +
                  " of type "   + type   +
                  ": "          + toString(value));

        ByteArrayOutputStream out =
                new ByteArrayOutputStream(16 * 1024);

        type.persist(value, out);

        PersistentBytes.persist(
                out.toByteArray(), toFile);
    }

    private static String toString(Object value) {
    	if (value instanceof Collection<?>) {
    		return "Collection of size " + ((Collection<?>) value).size();
    	} else if (value instanceof Map<?, ?>) {
    		return "Map of size " + ((Map<?, ?>) value).size();
    	} else {
    		return String.valueOf( value );
    	}
    }
    
    

    //--------------------------------------------------------------------
    private static boolean isDefaultConstructed(Object value)
    {
//        try {
//            value.getClass().getConstructor();
//            return false;
//        } catch (NoSuchMethodException success) {
//            return true;
//        }

        for (Constructor<?> ctor : value.getClass().getConstructors())
        {
            if (ctor.getParameterTypes().length == 0)
            {
                return true;
            }
        }
        return false;
    }


    //--------------------------------------------------------------------
    private static enum Type
    {
        //----------------------------------------------------------------
        STANDARD {
            @Override public void persist(Object obj, OutputStream out)
                    throws IOException {
                out.write( Type.STANDARD.ordinal() );
                Serializer.toBytes(obj, out);
            }
            @Override public Object retrieve(byte[] rawData) {
//                System.out.println("standard ret");
                return Serializer.fromBytes(
                            rawData, 1, rawData.length - 1);
            }},
        FAST {
            @Override public void persist(Object obj, OutputStream out)
                    throws IOException {
                out.write( Type.FAST.ordinal() );
                Serializer.toBytesFast(obj, out);
            }
            @Override public Object retrieve(byte[] rawData) {
//                System.out.println("fast ret");
                return Serializer.fromBytesFast(
                            rawData, 1, rawData.length - 1);
            }},
        XML {
            @Override public void persist(Object obj, OutputStream out)
                    throws IOException {
                out.write( Type.XML.ordinal() );
                Serializer.toXml(obj, out);
            }
            @Override public Object retrieve(byte[] rawData) {
//                System.out.println("xml ret");
                return Serializer.fromXml(
                        new ByteArrayInputStream(
                                rawData, 1, rawData.length - 1));
            }}
        ;

        public static final Type VALUES[] = values();


        //----------------------------------------------------------------
        public static Type fromData(byte data[])
        {
            return Type.VALUES[ data[0] ];
        }


        //----------------------------------------------------------------
        public abstract void persist(Object obj, OutputStream out)
                throws IOException;
        public abstract Object retrieve(byte offsetData[]);
    }
}
