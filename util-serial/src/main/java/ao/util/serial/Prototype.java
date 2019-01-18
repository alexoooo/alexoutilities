package ao.util.serial;

/**
 *
 */
public interface Prototype<P extends Prototype<P>>
{
    public P prototype();
}
