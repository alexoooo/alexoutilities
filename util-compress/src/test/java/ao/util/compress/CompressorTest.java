package ao.util.compress;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * User: Mable
 * Date: Mar 28, 2010
 * Time: 6:54:21 PM
 */
public class CompressorTest
{
    //-------------------------------------------------------------------------
    private final String uncompressedData;
    

    //-------------------------------------------------------------------------
    public CompressorTest()
    {
        uncompressedData = generatePredictableData();
    }


    //-------------------------------------------------------------------------
    public String generatePredictableData()
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++)
        {
            for (int d = 0; d < 10; d++)
            {
                str.append(d);
            }
            str.append('\n');
        }
        return str.toString();
    }


    //-------------------------------------------------------------------------
    @Test
    public void testCompression()
    {
        byte[] compressed =
                Compressor.deflate(
                        uncompressedData);

        assertTrue(
                compressed.length < uncompressedData.length(),
                "Compression should not increase size of these data");
    }


    //-------------------------------------------------------------------------
    @Test
    public void testDecompression()
    {
        assertEquals(
                Compressor.inflateStr(
                        Compressor.deflate(
                                uncompressedData)),
                uncompressedData,
                "Compression should be loss-less");
    }
}
