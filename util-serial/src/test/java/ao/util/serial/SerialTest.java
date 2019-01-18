package ao.util.serial;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

/**
 *
 */
public class SerialTest
{
    //-------------------------------------------------------------------------
    public SerialTest() {}


    //-------------------------------------------------------------------------
    @Test
    public void xmlFidelityTest()
    {
        Map<String, String> obj = new HashMap<>();
        obj.put("hello", "world");

        assertEquals(
                Serializer.fromXml(
                        Serializer.toXml(
                                obj)),
                obj,
                "Serialization should change nothing");

        assertEquals(
                Cloner.clone(obj), obj,
                "Cloned contents should equal");

        assertNotSame(
                Cloner.clone(obj), obj,
                "Cloned contents should not refer to the same memory address");
    }


    //-------------------------------------------------------------------------
    public static void main(String[] args)
    {
//        Object obj = MersenneTwister.class.getMethods()[2];
//        Object obj = new Combiner<Object>(
//                            new Object[]{1, 2, 3, 4, 5}, 2);
        Object obj = new HashMap<String, String>(){{
                         put("hello", "world");
                     }};

//        System.out.println();
//        System.out.println(Serializer.toBytes(obj).length);
//        System.out.println(Serializer.toBytesFast(obj).length);
//        System.out.println(Serializer.toXml(obj).length());
//
//        System.out.println();
//        System.out.println(Compressor.deflate(Serializer.toBytes(obj)).length);
//        System.out.println(Compressor.deflate(Serializer.toBytesFast(obj)).length);
//        System.out.println(Compressor.deflate(Serializer.toXml(obj)).length);
//
//        System.out.println();
//        System.out.println(
//                Compressor.inflate(
//                        Compressor.deflate(
//                                Serializer.toBytes(obj))).length);
//        System.out.println(
//                Compressor.inflate(
//                        Compressor.deflate(
//                                Serializer.toBytesFast(obj))).length);
//        System.out.println(
//                Compressor.inflateStr(
//                        Compressor.deflate(
//                                Serializer.toXml(obj))).length());


        // warm-up
        for (int i = 0; i < 15000; i++)
        {
            Cloner.clone(obj);
        }

        for (int x = 0; x < 100; x++)
        {
            long before = System.currentTimeMillis();
            for (int i = 2000; i > 0; i--)
            {
                Cloner.clone(obj);
            }
            long after = System.currentTimeMillis();
            System.out.println((after - before));
        }
    }
}
