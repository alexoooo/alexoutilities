package ao.util.parse.flat;

import ao.util.data.iter.CloseableIterable;
import ao.util.data.iter.Iters;
import ao.util.io.AoFiles;
import java.util.List;

/**
 * User: alex
 * Date: 10-Jun-2010
 * Time: 9:38:30 PM
 */
public class CsvTest
{
    //-------------------------------------------------------------------------
    public static void main(String[] args)
    {
        CloseableIterable<List<String>> rows = Iters.asIterable(
                Csv.parse(AoFiles.resource(
                        CsvTest.class, "test.csv")));

        for (List<String> row : rows)
        {
            System.out.println( row );
        }

        rows.close();
    }
}
