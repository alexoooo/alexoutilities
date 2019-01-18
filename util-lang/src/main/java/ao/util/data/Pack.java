package ao.util.data;

import java.util.Arrays;

/**
 * User: alex
 * Date: 31-Mar-2009
 * Time: 1:35:57 PM
 */
public class Pack
{
    //--------------------------------------------------------------------
    public static void main(String[] args) {
        double test[][] = {
                {0, 1, 2, -1},
                {3, 4, 5, -2},
                {6, 7, 8, -3}};

        System.out.println(
                Arrays.deepToString(test));
        System.out.println(
                Arrays.toString(flatten(test)));
        System.out.println(
                Arrays.deepToString(
                        square(flatten(test), test[0].length)));

//        double test[] = PersistentDoubles.retrieve(
//                "/home/alex/proj/holdem/lookup/bucket/" +
//                    "odds_homo;6.144.432.1296/info/preflop/fold.double");
//        System.out.println(
//                Arrays.deepToString(
//                        square(test, 15)));

    }



    //--------------------------------------------------------------------
    private Pack() {}


    //--------------------------------------------------------------------
    public static double[] flatten(double square[][])
    {
        double flat[] = new double[ square.length * square[0].length ];
        int i = 0;
        for (double[] plane : square) {
            for (double r : plane) {
                flat[ i++ ] = r;
            }
        }
        return flat;
    }

    public static float[] flatten(float square[][])
    {
        float[] flat = new float[ square.length * square[0].length ];
        int i = 0;
        for (float[] plane : square) {
            for (float r : plane) {
                flat[ i++ ] = r;
            }
        }
        return flat;
    }

    public static int[] flatten(int square[][])
    {
        int[] flat = new int[ square.length * square[0].length ];
        int i = 0;
        for (int[] plane : square) {
            for (int r : plane) {
                flat[ i++ ] = r;
            }
        }
        return flat;
    }


    //--------------------------------------------------------------------
    public static double[][] square(double flat[], int secondDim)
    {
        double[][] square = new double[ flat.length / secondDim ]
                                      [ secondDim               ];
        for (int i = 0; i < flat.length; i++) {
            square[ i / secondDim ]
                  [ i % secondDim ] = flat[i];
        }
        return square;
    }
    public static float[][] square(float flat[], int secondDim)
    {
        float[][] square = new float[ flat.length / secondDim ]
                                    [ secondDim               ];
        for (int i = 0; i < flat.length; i++) {
            square[ i / secondDim ]
                  [ i % secondDim ] = flat[i];
        }
        return square;
    }
    public static int[][] square(int flat[], int secondDim)
    {
        int[][] square = new int[ flat.length / secondDim ]
                                [ secondDim               ];
        for (int i = 0; i < flat.length; i++) {
            square[ i / secondDim ]
                  [ i % secondDim ] = flat[i];
        }
        return square;
    }
}
