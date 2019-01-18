package ao.util.serial;

import org.apache.log4j.Logger;
import java.io.*;


/**
 * Supports any object that implements Serializable.
 */
class StandardSerializer
{
    //-----------------------------------------------------------------
	private static final Logger LOG =
    		Logger.getLogger(StandardSerializer.class);
	
    private StandardSerializer() {}


    //-----------------------------------------------------------------
    public static byte[] toBytes(Object obj)
    {
        ByteArrayOutputStream out =
                new ByteArrayOutputStream(16 * 1024);
        toBytes(obj, out);
        return out.toByteArray();
    }
    public static void toBytes(
            Object obj, OutputStream outputStream)
    {
        try
        {
            ObjectOutputStream out =
                    new ObjectOutputStream(outputStream);
            out.writeObject(obj);
        }
        catch (Exception e)
        {
            throw new Error( e );
        }
    }


    //-----------------------------------------------------------------
    public static Object fromBytes(byte objectAsByteArray[])
    {
        return fromBytes(
                objectAsByteArray, 0, objectAsByteArray.length);
    }
    public static Object fromBytes(
            byte objectAsByteArray[],
            int  offset,
            int  length)
    {
        try
        {
            ObjectInputStream in =
                    new ObjectInputStream(new ByteArrayInputStream(
                            objectAsByteArray, offset, length));
            return in.readObject();
        }
        catch (Exception e)
        {
//        	LOG.debug("Unable to thaw, returning null: " +
//                        Exceptions.unrollMessages( e ));
            LOG.debug("Unable to thaw, returning null: " +
                        e.getMessage());
            return null;
        }
    }
}
