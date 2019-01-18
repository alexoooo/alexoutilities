package ao.util.serial;

/**
 *
 */
public class Cloner
{
    //--------------------------------------------------------------------
    private Cloner() {}

    
    //--------------------------------------------------------------------
    /**
     * Supports the cloning of ANY object.
     *
     * @param obj object to clone.
     * @return deep clone of object.
     */
    @SuppressWarnings("unchecked")
    public static <T> T clone(T obj)
    {
        return (T) Serializer.fromXml(
                        Serializer.toXml(obj));
    }

    /**
     * Supports any object that implements Serializable.
     *
     * @param obj object to clone.
     * @return deep clone of object.
     */
    @SuppressWarnings("unchecked")
    public static <T> T cloneSerial(T obj)
    {
        return (T) Serializer.fromBytes(
                        Serializer.toBytes(obj));
    }


    /**
     * Does NOT support cloning Method objects, or any other
     *  reflection system objects.
     * Objects must have a no-arg constructors (which can be private).
     *
     * @param obj object to clone.
     * @return deep clone of object.
     */
    @SuppressWarnings("unchecked")
    public static <T> T cloneFast(T obj)
    {
        return (T) Serializer.fromBytesFast(
                        Serializer.toBytesFast(obj));
    }
}
