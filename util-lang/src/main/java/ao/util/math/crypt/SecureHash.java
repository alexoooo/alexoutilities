package ao.util.math.crypt;

import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Date: Nov 16, 2008
 * Time: 4:02:12 PM
 */
public interface SecureHash extends Serializable
{
    //--------------------------------------------------------------------
    void feed(File file);
    void feed(File file, long upTo);


    //--------------------------------------------------------------------
    void feed(byte value);

    void feed(int value);

    void feed(long value);

    void feed(byte[] values);

    void feed(char[] values);


    //--------------------------------------------------------------------
    byte[] digest();

    BigInteger bigDigest();

    String hexDigest();
}
