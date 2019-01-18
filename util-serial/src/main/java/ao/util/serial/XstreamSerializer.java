package ao.util.serial;

import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Supports ANY object.
 */
class XstreamSerializer
{
    //-----------------------------------------------------------------
	private static final Logger LOG =
			Logger.getLogger(XstreamSerializer.class);
	
    private static final XStream XSTREAM = new XStream();


    //-----------------------------------------------------------------
    private XstreamSerializer() {}


    //-----------------------------------------------------------------
    public static String toXml(Object obj)
    {
        return XSTREAM.toXML(obj);
    }

    public static void toXml(Object obj, OutputStream output)
    {
        XSTREAM.toXML(obj, output);
    }


    //-----------------------------------------------------------------
    public static Object fromXml(String objectAsXml)
    {
    	try {
    		return XSTREAM.fromXML(objectAsXml);
    	} catch (Throwable t) {
    		LOG.warn("Unable to thaw", t);
    		return null;
    	}
    }

    public static Object fromXml(InputStream objectAsXml)
    {
    	try {
    		return XSTREAM.fromXML(objectAsXml);
    	} catch (Throwable t) {
//    		LOG.debug("Unable to thaw, returning null: " +
//                        Exceptions.unrollMessages( t ));
            LOG.debug("Unable to thaw, returning null: " +
                        t.getMessage());
    		return null;
    	}
    }
}
