package ao.util.math.stats;

/**
 * A FastCombiner optimized for integers.
 * No major performance differance :(
 */
public class FastIntCombiner
{
    //--------------------------------------------------------------------
    private int items[];
    private int n;
    private int startAt;

    //--------------------------------------------------------------------
    public FastIntCombiner(int toCombine[])
    {
        this(toCombine, toCombine.length, 0);
    }
    public FastIntCombiner(int toCombine[], int n)
    {
        this(toCombine, n, 0);
    }
    public FastIntCombiner(
            int toCombine[], int n, int continueAt)
    {
        assert n <= toCombine.length &&
                0 <= continueAt// &&
                     //continueAt < toCombine.length
                : "n: " + n + ", continueAt: " + continueAt +
                    ", length: " + toCombine.length;

        this.n     = n;
        this.items = toCombine;
        startAt    = continueAt;
    }


    //--------------------------------------------------------------------
    // 7, 4, 3, 2
    public void combine(CombinationVisitor1 v1)
    {
        for (int a = startAt; a < n; a++)
        {
            v1.visit( items[a] );
        }
    }

    public void combine(CombinationVisitor2 v2)
    {
        for (int a = startAt; a < n; a++)
        {
            int itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                v2.visit( itemA , items[b] );
            }
        }
    }

    public void combine(CombinationVisitor3 v3)
    {
        for (int a = startAt; a < n; a++)
        {
            int itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                int itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    v3.visit( itemA, itemB, items[c] );
                }
            }
        }
    }

    public void combine(CombinationVisitor4 v4)
    {
        for (int a = startAt; a < n; a++)
        {
            int itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                int itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    int itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        v4.visit( itemA, itemB, itemC, items[d]);
                    }
                }
            }
        }
    }

    public void combine(CombinationVisitor5 v5)
    {
        for (int a = startAt; a < n; a++)
        {
            int itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                int itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    int itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        int itemD = items[d];
                        for (int e = d + 1; e < n; e++)
                        {
                            v5.visit( itemA, itemB,
                                      itemC, itemD, items[e]);
                        }
                    }
                }
            }
        }
    }

    public void combine(CombinationVisitor6 v6)
    {
        for (int a = startAt; a < n; a++)
        {
            int itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                int itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    int itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        int itemD = items[d];
                        for (int e = d + 1; e < n; e++)
                        {
                            int itemE = items[e];
                            for (int f = e + 1; f < n; f++)
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

    public void combine(CombinationVisitor7 v7)
    {
        for (int a = startAt; a < n; a++)
        {
            int itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                int itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    int itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        int itemD = items[d];
                        for (int e = d + 1; e < n; e++)
                        {
                            int itemE = items[e];
                            for (int f = e + 1; f < n; f++)
                            {
                                int itemF = items[f];
                                for (int g = f + 1; g < n; g++)
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

    public void combine(CombinationVisitor8 v8)
    {
        for (int a = startAt; a < n; a++)
        {
            int itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                int itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    int itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        int itemD = items[d];
                        for (int e = d + 1; e < n; e++)
                        {
                            int itemE = items[e];
                            for (int f = e + 1; f < n; f++)
                            {
                                int itemF = items[f];
        {
            for (int g = f + 1; g < n; g++)
            {
                int itemG = items[g];
                for (int h = g + 1; h < n; h++)
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

    public void combine(CombinationVisitor10 v10)
    {
        for (int a = startAt; a < n; a++)
        {
            int itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                int itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    int itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        int itemD = items[d];
                        for (int e = d + 1; e < n; e++)
                        {
                            int itemE = items[e];
                            for (int f = e + 1; f < n; f++)
                            {
                                int itemF = items[f];
        {
            for (int g = f + 1; g < n; g++)
            {
                int itemG = items[g];
                for (int h = g + 1; h < n; h++)
                {
                    int itemH = items[h];
                    for (int i = h + 1; i < n; i++)
                    {
                        int itemI = items[i];
                        for (int j = i + 1; j < n; j++)
                        {
                            v10.visit( itemA, itemB, itemC, itemD,
                                       itemE, itemF, itemG, itemH,
                                       itemI, items[j]);
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
        }
    }


    //--------------------------------------------------------------------
    public static interface CombinationVisitor1
    {
        public void visit(int itemA);
    }
    public static interface CombinationVisitor2
    {
        public void visit(
                int itemA, int itemB);
    }
    public static interface CombinationVisitor3
    {
        public void visit(
                int itemA, int itemB, int itemC);
    }
    public static interface CombinationVisitor4
    {
        public void visit(
                int itemA, int itemB, int itemC, int itemD);
    }
    public static interface CombinationVisitor5
    {
        public void visit(
                int itemA, int itemB, int itemC, int itemD, int itemE);
    }
    public static interface CombinationVisitor6
    {
        public void visit(
                int itemA, int itemB, int itemC,
                int itemD, int itemE, int itemF);
    }
    public static interface CombinationVisitor7
    {
        public void visit(
                int itemA, int itemB, int itemC,
                int itemD, int itemE, int itemF, int itemG);
    }
    public static interface CombinationVisitor8
    {
        public void visit(
                int itemA, int itemB, int itemC, int itemD,
                int itemE, int itemF, int itemG, int itemH);
    }
    public static interface CombinationVisitor10
    {
        public void visit(
                int itemA, int itemB, int itemC, int itemD, int itemE,
                int itemF, int itemG, int itemH, int itemI, int itemJ);
    }


    //--------------------------------------------------------------------
    public static void main(String args[])
    {
        for (int i = 0; i < 20; i++)
        {
            doMain();
        }
    }
    public static void doMain()
    {
        int ints[] = { 1,  2,  3,  4,  5,  6,  7,  8,  9, 10,
                       11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                       21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                       31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                       41, 42, 43, 44, 45, 46, 47, 48, 48, 50};

        long start = System.currentTimeMillis();

        TestVisitor tv = new TestVisitor(ints);
        FastIntCombiner fc = new FastIntCombiner(ints);
        fc.combine(tv);
        System.out.println("i: " + tv.i());

        System.out.println("took " + (System.currentTimeMillis() - start));
    }
    private static class TestVisitor
            implements CombinationVisitor5
    {
        private int vals[];
        public TestVisitor(int ints[])
        {
            vals = ints.clone();
        }

        private int i = 0;
        public void visit(int itemA, int itemB, int itemC,
                          int itemD, int itemE)
        {
            swap(vals, itemA - 1, vals.length - 1);
            swap(vals, itemB - 1, vals.length - 2);
            swap(vals, itemC - 1, vals.length - 3);
            swap(vals, itemD - 1, vals.length - 4);
            swap(vals, itemE - 1, vals.length - 5);

            NestedVisitor nv = new NestedVisitor();

            FastIntCombiner nfc =
                    new FastIntCombiner(vals, vals.length - 5);
            nfc.combine( nv );

            i += nv.i();

            swap(vals, itemA - 1, vals.length - 1);
            swap(vals, itemB - 1, vals.length - 2);
            swap(vals, itemC - 1, vals.length - 3);
            swap(vals, itemD - 1, vals.length - 4);
            swap(vals, itemE - 1, vals.length - 5);
        }
        public int i() {  return i;  }
    }
    private static class NestedVisitor
            implements CombinationVisitor2
    {
        private int i = 0;
        public void visit(int itemA, int itemB)
        {
            i++;
        }
        public int i() {  return i;  }
    }
    private static void swap(int vals[], int i, int j)
    {
        int tmp = vals[i];
        vals[i] = vals[j];
        vals[j] = tmp;
    }
}
