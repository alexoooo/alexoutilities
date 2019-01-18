package ao.util.parse.flat;

import ao.util.data.iter.CloseableIterator;
import ao.util.io.AoFiles;
import ao.util.io.Streams;
import ao.util.misc.AoThrowables;
import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User: aostrovsky
 * Date: 20-Jul-2009
 * Time: 11:12:34 AM
 *
 * Doesn't work for complicated CVS cases, for a proper Comma Separated Value
 *  (CSV) library check out OpenCSV: http://opencsv.sourceforge.net/
 *
 */
public class Csv
{
//    //-------------------------------------------------------------------------
//    public static void main(String[] args)
//    {
//        String sample = "";
//
//        sample += "hello,world\n";
//        sample += "\"foo,bar\",baz\n";
////        sample += "\"a,b,c\",\"a\"\"b\"\"c\"\n";
//
//
//        System.out.println("Parsing:\n" + sample);
//        for (String line : sample.split("(\r|\n)+"))
//        {
//            System.out.println("In: " + line);
//
//            for (String col : fromCvs( line ))
//            {
//                System.out.println( col );
//            }
//        }
//    }


    //-------------------------------------------------------------------------
    private Csv() {}


    //-------------------------------------------------------------------------
//    private static final Pattern CVS_PATTERN = Pattern.compile(
//            "\"([^\"]+?)\",?|([^,]+),?|,");


    //-------------------------------------------------------------------------
    private static final Pattern SAFE_CHARS_PAT = Pattern.compile(
            "^[\\d\\w ]*$");


    //-------------------------------------------------------------------------
    public static String toCsv(Iterable<?> row)
    {
        List<Object> asList = new ArrayList<Object>();
        for (Object cell : row) {
            asList.add( cell );
        }
        return toCsv(asList.toArray(
                new Object[ asList.size() ]));
    }
    
    public static String toCsv(Object... row)
    {
        StringBuilder str = new StringBuilder();

        boolean isFirst = true;
        for (Object val : row)
        {
            if (! isFirst) {
                str.append(',');
            }
            str.append(encode(
                    val.toString() ));
            isFirst = false;
        }

        return str.toString();
    }


    //-------------------------------------------------------------------------
//    private static List<String> fromCvs(String line)
//    {
//        List<String> list = new ArrayList<String>();
//        Matcher m = CVS_PATTERN.matcher(line);
//
//        while (m.find())
//        {
//            String match = m.group();
//            if (match == null)
//            {
//                break;
//            }
//
//            if (match.endsWith(",")) {  // trim trailing ,
//                match = match.substring(0, match.length() - 1);
//            }
//
//            if (match.startsWith("\"")) { // assume also ends with
//                match = match.substring(1, match.length() - 1);
//            }
//
//            if (match.length() == 0)
//            {
//                match = null;
//            }
//
//            list.add(match);
//        }
//
//        return list;
//    }

//    public static int fromCsv(
//            String data, int offset, List<String> into)
//    {
//        StringBuilder field   = new StringBuilder();
//        boolean       inQuote = false;
//        for (int i = offset; i < data.length(); i++)
//        {
//            char c = data.charAt( i );
//
////            if
//
//        }
//
//        return -1;
//    }


    //-------------------------------------------------------------------------
    public static CloseableIterator<List<String>> parse(String csvFile)
    {
        return parse(new File( csvFile ));
    }

    public static CloseableIterator<List<String>> parse(File csvFile)
    {
        return parse(AoFiles.read( csvFile ),
                     ',', '"', '\\');
    }

    public static CloseableIterator<List<String>> parse(
            Reader csvStream,
            char   delimiter,
            char   quote,
            char   escape)
    {
        final CSVReader reader = new CSVReader(
                csvStream, delimiter, quote, escape, 0, false);

        return new CloseableIterator<List<String>>() {
            private List<String> next;

            @Override
            public void close()
            {
                Streams.close( reader );
            }

            @Override
            public boolean hasNext()
            {
                computeNextIfMissing();
                return (next != null);
            }

            @Override
            public List<String> next()
            {
                computeNextIfMissing();

                List<String> temp;
                temp = next;
                next = null;
                return temp;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }

            private void computeNextIfMissing()
            {
                if (next != null) {
                    return;
                }

                try
                {
                    String[] parsed = reader.readNext();
                    next = (parsed == null
                            ? null : Arrays.asList( parsed ));
                }
                catch (IOException e)
                {
                    throw AoThrowables.wrap( e );
                }
            }
        };
    }


    //-------------------------------------------------------------------------
    public static String encode(String csvField)
    {
        if (SAFE_CHARS_PAT.matcher(csvField).matches()) {
            return csvField;
        }

        StringBuilder str = new StringBuilder();
        str.append('"');
        str.append( csvField.replaceAll("\"", "\"\"") );
        str.append('"');
        return str.toString();
    }
}
