package ao.util.math.stats;

/**
 * Recombine the elements of a list in every way possible,
 *  resulting in s^n combinations.
 */
public class FastExhauster<T>
{
    //--------------------------------------------------------------------
    private T   items[];
    private int n;


    //--------------------------------------------------------------------
    public FastExhauster(T toCombine[])
    {
        this(toCombine, toCombine.length);
    }
    public FastExhauster(
            T toCombine[], int n)
    {
        assert n <= toCombine.length ;

        this.n     = n;
        this.items = toCombine;
    }


    //--------------------------------------------------------------------
    // 7, 4, 3, 2
    public void combine(FullVisitor1<T> v1)
    {
        for (int a = 0; a < n; a++)
        {
            v1.visit( items[a] );
        }
    }

    public void combine(FullVisitor2<T> v2)
    {
        for (int a = 0; a < n; a++)
        {
            T itemA = items[a];
            for (int b = 0; b < n; b++)
            {
                v2.visit( itemA , items[b] );
            }
        }
    }

    public void combine(FullVisitor3<T> v3)
    {
        for (int a = 0; a < n; a++)
        {
            T itemA = items[a];
            for (int b = 0; b < n; b++)
            {
                T itemB = items[b];
                for (int c = 0; c < n; c++)
                {
                    v3.visit( itemA, itemB, items[c] );
                }
            }
        }
    }

    public void combine(FullVisitor4<T> v4)
    {
        for (int a = 0; a < n; a++)
        {
            T itemA = items[a];
            for (int b = 0; b < n; b++)
            {
                T itemB = items[b];
                for (int c = 0; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = 0; d < n; d++)
                    {
                        v4.visit( itemA, itemB, itemC, items[d]);
                    }
                }
            }
        }
    }

    public void combine(FullVisitor5<T> v5)
    {
        for (int a = 0; a < n; a++)
        {
            T itemA = items[a];
            for (int b = 0; b < n; b++)
            {
                T itemB = items[b];
                for (int c = 0; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = 0; d < n; d++)
                    {
                        T itemD = items[d];
                        for (int e = 0; e < n; e++)
                        {
                            v5.visit( itemA, itemB,
                                      itemC, itemD, items[e]);
                        }
                    }
                }
            }
        }
    }

    public void combine(FullVisitor6<T> v6)
    {
        for (int a = 0; a < n; a++)
        {
            T itemA = items[a];
            for (int b = 0; b < n; b++)
            {
                T itemB = items[b];
                for (int c = 0; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = 0; d < n; d++)
                    {
                        T itemD = items[d];
                        for (int e = 0; e < n; e++)
                        {
                            T itemE = items[e];
                            for (int f = 0; f < n; f++)
                            {
                                v6.visit( itemA, itemB, itemC,
                                          itemD, itemE, items[f]);
                            }
                        }
                    }
                }
            }
        }
    }

    public void combine(FullVisitor7<T> v7)
    {
        for (int a = 0; a < n; a++)
        {
            T itemA = items[a];
            for (int b = 0; b < n; b++)
            {
                T itemB = items[b];
                for (int c = 0; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = 0; d < n; d++)
                    {
                        T itemD = items[d];
                        for (int e = 0; e < n; e++)
                        {
                            T itemE = items[e];
                            for (int f = 0; f < n; f++)
                            {
                                T itemF = items[f];
                                for (int g = 0; g < n; g++)
                                {
                                    v7.visit( itemA, itemB, itemC,
                                              itemD, itemE, itemF, items[g]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void combine(FullVisitor8<T> v8)
    {
        for (int a = 0; a < n; a++)
        {
            T itemA = items[a];
            for (int b = 0; b < n; b++)
            {
                T itemB = items[b];
                for (int c = 0; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = 0; d < n; d++)
                    {
                        T itemD = items[d];
                        for (int e = 0; e < n; e++)
                        {
                            T itemE = items[e];
                            for (int f = 0; f < n; f++)
                            {
                                T itemF = items[f];
        {
            for (int g = 0; g < n; g++)
            {
                T itemG = items[g];
                for (int h = 0; h < n; h++)
                {
                    v8.visit( itemA, itemB, itemC, itemD,
                              itemE, itemF, itemG, items[h]);
                }
            }
        }
                            }
                        }
                    }
                }
            }
        }
    }


    //--------------------------------------------------------------------
    public static interface FullVisitor1<T>
    {
        public void visit(T itemA);
    }
    public static interface FullVisitor2<T>
    {
        public void visit(
                T itemA, T itemB);
    }
    public static interface FullVisitor3<T>
    {
        public void visit(
                T itemA, T itemB, T itemC);
    }
    public static interface FullVisitor4<T>
    {
        public void visit(
                T itemA, T itemB, T itemC, T itemD);
    }
    public static interface FullVisitor5<T>
    {
        public void visit(
                T itemA, T itemB, T itemC, T itemD, T itemE);
    }
    public static interface FullVisitor6<T>
    {
        public void visit(
                T itemA, T itemB, T itemC,
                T itemD, T itemE, T itemF);
    }
    public static interface FullVisitor7<T>
    {
        public void visit(
                T itemA, T itemB, T itemC,
                T itemD, T itemE, T itemF, T itemG);
    }
    public static interface FullVisitor8<T>
    {
        public void visit(
                T itemA, T itemB, T itemC, T itemD,
                T itemE, T itemF, T itemG, T itemH);
    }


    //--------------------------------------------------------------------
    public static void main(String args[])
    {
//        for (int i = 0; i < 20; i++)
//        {
            doMain();
//        }
    }
    public static void doMain()
    {
        Integer ints[] = { 1,  2,  3,  4,  5,  6,  7,  8,  9, 10,
                          11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                          21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                          31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                          41, 42, 43, 44, 45, 46, 47, 48, 48, 50};

        long start = System.currentTimeMillis();

        TestVisitor tv = new TestVisitor();
        FastExhauster<Integer> fc = new FastExhauster<Integer>(ints);
        fc.combine(tv);
        System.out.println("took " + (System.currentTimeMillis() - start));
    }
    private static class TestVisitor
            implements FullVisitor2<Integer>
    {
        public void visit(Integer itemA, Integer itemB)
        {
            System.out.println(itemA + "\t" + itemB);
        }
    }
}