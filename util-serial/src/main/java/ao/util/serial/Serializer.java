package ao.util.serial;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public class Serializer
{
    //-----------------------------------------------------------------
    private Serializer() {}


    //-----------------------------------------------------------------
    /**
     * Supports ANY object.
     *
     * @param obj to serialize
     * @return object as XML
     */
    public static String toXml(Object obj)
    {
        return XstreamSerializer.toXml(obj);
    }

    public static void toXml(Object obj, OutputStream output)
    {
        XstreamSerializer.toXml(obj, output);
    }

    public static Object fromXml(String objectAsXml)
    {
        return XstreamSerializer.fromXml(objectAsXml);
    }

    public static Object fromXml(InputStream objectAsXml)
    {
        return XstreamSerializer.fromXml(objectAsXml);
    }


    //-----------------------------------------------------------------
    /**
     * Supports any object that implements Serializable.
     * @param obj to serialize.
     * @return serialized.
     */
    public static byte[] toBytes(Object obj)
    {
        return StandardSerializer.toBytes(obj);
    }

    public static void toBytes(Object obj, OutputStream out)
    {
        StandardSerializer.toBytes(obj, out);
    }

    public static Object fromBytes(byte objectAsByteArray[])
    {
        return StandardSerializer.fromBytes(objectAsByteArray);
    }

    public static Object fromBytes(
            byte objectAsByteArray[], int offset, int length)
    {
        return StandardSerializer.fromBytes(
                objectAsByteArray, offset, length);
    }


    //-----------------------------------------------------------------
    /**
     * Does NOT support Method objects, or any other
     *  reflection system objects.
     * Objects must have a no-arg constructors (which can be private).
     *
     * @param obj to serialize
     * @return serialized
     */
    public static byte[] toBytesFast(Object obj)
    {
        return JbossSerializer.toBytesFast(obj);
    }

    public static void toBytesFast(
            Object obj, OutputStream out)
    {
        JbossSerializer.toBytesFast(obj, out);
    }

    public static Object fromBytesFast(
            byte objectAsByteArray[])
    {
        return JbossSerializer.fromBytesFast(objectAsByteArray);
    }

    public static Object fromBytesFast(
            byte objectAsByteArray[], int offset, int length)
    {
        return JbossSerializer.fromBytesFast(
                objectAsByteArray, offset, length);
    }
}
