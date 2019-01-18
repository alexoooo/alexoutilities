package ao.util.serial;

import org.apache.log4j.Logger;
import org.jboss.serial.io.JBossObjectInputStream;
import org.jboss.serial.io.JBossObjectOutputStream;

import java.io.*;

/**
 * Does NOT support Method objects, or any other
 *  reflection system objects.
 * Objects must have a no-arg constructors (which can be private).
 */
class JbossSerializer
{
    //-----------------------------------------------------------------
	private static final Logger LOG =
        	Logger.getLogger(JbossSerializer.class);
	
    private JbossSerializer() {}


    //-----------------------------------------------------------------
    public static byte[] toBytesFast(Object obj)
    {
        ByteArrayOutputStream out =
                new ByteArrayOutputStream(16 * 1024);
        toBytesFast(obj, out);
        return out.toByteArray();
    }
    
    public static void toBytesFast(
            Object obj, OutputStream outputStream)
    {
        try
        {
            ObjectOutputStream out =
                    new JBossObjectOutputStream(outputStream);
            out.writeObject(obj);
        }
        catch (Exception e)
        {
            throw new Error( e );
        }
    }


    //-----------------------------------------------------------------
    public static Object fromBytesFast(byte objectAsByteArray[])
    {
        return fromBytesFast(
                objectAsByteArray, 0, objectAsByteArray.length);
    }
    public static Object fromBytesFast(
            byte objectAsByteArray[],
            int  offset,
            int  length)
    {
        try
        {
            ObjectInputStream in = new JBossObjectInputStream(
                    new ByteArrayInputStream(
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
