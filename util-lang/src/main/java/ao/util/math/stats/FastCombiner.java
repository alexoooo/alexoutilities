package ao.util.math.stats;

/**
 * trying to optimize Combiner for maximum speed.
 */
public class FastCombiner<T>
{
    //--------------------------------------------------------------------
    private T   items[];
    private int n;
    private int startAt;

    
    //--------------------------------------------------------------------
    public FastCombiner(T toCombine[])
    {
        this(toCombine, toCombine.length, 0);
    }
    public FastCombiner(T toCombine[], int n)
    {
        this(toCombine, n, 0);
    }
    public FastCombiner(
            T toCombine[], int n, int continueAt)
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
    public void combine(CombinationVisitor1<T> v1)
    {
        for (int a = startAt; a < n; a++)
        {
            v1.visit( items[a] );
        }
    }

    public void combine(CombinationVisitor2<T> v2)
    {
        for (int a = startAt; a < n; a++)
        {
            T itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                v2.visit( itemA , items[b] );
            }
        }
    }

    public void combine(CombinationVisitor3<T> v3)
    {
        for (int a = startAt; a < n; a++)
        {
            T itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                T itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    v3.visit( itemA, itemB, items[c] );
                }
            }
        }
    }

    public void combine(CombinationVisitor4<T> v4)
    {
        for (int a = startAt; a < n; a++)
        {
            T itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                T itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        v4.visit( itemA, itemB, itemC, items[d]);
                    }
                }
            }
        }
    }

    public void combine(CombinationVisitor5<T> v5)
    {
        for (int a = startAt; a < n; a++)
        {
            T itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                T itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        T itemD = items[d];
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

    public void combine(CombinationVisitor6<T> v6)
    {
        for (int a = startAt; a < n; a++)
        {
            T itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                T itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        T itemD = items[d];
                        for (int e = d + 1; e < n; e++)
                        {
                            T itemE = items[e];
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

    public void combine(CombinationVisitor7<T> v7)
    {
        for (int a = startAt; a < n; a++)
        {
            T itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                T itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        T itemD = items[d];
                        for (int e = d + 1; e < n; e++)
                        {
                            T itemE = items[e];
                            for (int f = e + 1; f < n; f++)
                            {
                                T itemF = items[f];
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

    public void combine(CombinationVisitor8<T> v8)
    {
        for (int a = startAt; a < n; a++)
        {
            T itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                T itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        T itemD = items[d];
                        for (int e = d + 1; e < n; e++)
                        {
                            T itemE = items[e];
                            for (int f = e + 1; f < n; f++)
                            {
                                T itemF = items[f];
        {
            for (int g = f + 1; g < n; g++)
            {
                T itemG = items[g];
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

    public void combine(CombinationVisitor10<T> v10)
    {
        for (int a = startAt; a < n; a++)
        {
            T itemA = items[a];
            for (int b = a + 1; b < n; b++)
            {
                T itemB = items[b];
                for (int c = b + 1; c < n; c++)
                {
                    T itemC = items[c];
                    for (int d = c + 1; d < n; d++)
                    {
                        T itemD = items[d];
                        for (int e = d + 1; e < n; e++)
                        {
                            T itemE = items[e];
                            for (int f = e + 1; f < n; f++)
                            {
                                T itemF = items[f];
        {
            for (int g = f + 1; g < n; g++)
            {
                T itemG = items[g];
                for (int h = g + 1; h < n; h++)
                {
                    T itemH = items[h];
                    for (int i = h + 1; i < n; i++)
                    {
                        T itemI = items[i];
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
    public static interface CombinationVisitor1<T>
    {
        public void visit(T itemA);
    }
    public static interface CombinationVisitor2<T>
    {
        public void visit(
                T itemA, T itemB);
    }
    public static interface CombinationVisitor3<T>
    {
        public void visit(
                T itemA, T itemB, T itemC);
    }
    public static interface CombinationVisitor4<T>
    {
        public void visit(
                T itemA, T itemB, T itemC, T itemD);
    }
    public static interface CombinationVisitor5<T>
    {
        public void visit(
                T itemA, T itemB, T itemC, T itemD, T itemE);
    }
    public static interface CombinationVisitor6<T>
    {
        public void visit(
                T itemA, T itemB, T itemC,
                T itemD, T itemE, T itemF);
    }
    public static interface CombinationVisitor7<T>
    {
        public void visit(
                T itemA, T itemB, T itemC,
                T itemD, T itemE, T itemF, T itemG);
    }
    public static interface CombinationVisitor8<T>
    {
        public void visit(
                T itemA, T itemB, T itemC, T itemD,
                T itemE, T itemF, T itemG, T itemH);
    }
    public static interface CombinationVisitor10<T>
    {
        public void visit(
                T itemA, T itemB, T itemC, T itemD, T itemE,
                T itemF, T itemG, T itemH, T itemI, T itemJ);
    }
//    public static interface CombinationVisitor12<T>
//            extends CombinationVisitor
//    {
//        public void visit(
//                T itemA, T itemB, T itemC, T itemD,
//                T itemE, T itemF, T itemG, T itemH,
//                T itemI, T itemJ, T itemK, T itemL,
//                int continueAt);
//    }
//    public static interface CombinationVisitor14<T>
//            extends CombinationVisitor
//    {
//        public void visit(
//                T itemA, T itemB, T itemC, T itemD,
//                T itemE, T itemF, T itemG, T itemH,
//                T itemI, T itemJ, T itemK, T itemL,
//                T itemM, T itemN,
//                int continueAt);
//    }
//    public static interface CombinationVisitor16<T>
//            extends CombinationVisitor
//    {
//        public void visit(
//                T itemA, T itemB, T itemC, T itemD,
//                T itemE, T itemF, T itemG, T itemH,
//                T itemI, T itemJ, T itemK, T itemL,
//                T itemM, T itemN, T itemO, T itemP,
//                int continueAt);
//    }
//    public static interface CombinationVisitor18<T>
//            extends CombinationVisitor
//    {
//        public void visit(
//                T itemA, T itemB, T itemC, T itemD,
//                T itemE, T itemF, T itemG, T itemH,
//                T itemI, T itemJ, T itemK, T itemL,
//                T itemM, T itemN, T itemO, T itemP,
//                T itemQ, T itemR,
//                int continueAt);
//    }


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
        Integer ints[] = { 1,  2,  3,  4,  5,  6,  7,  8,  9, 10,
                          11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                          21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                          31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                          41, 42, 43, 44, 45, 46, 47, 48, 48, 50};

        long start = System.currentTimeMillis();
        
        TestVisitor tv = new TestVisitor(ints);
        FastCombiner<Integer> fc = new FastCombiner<Integer>(ints);
        fc.combine(tv);
        System.out.println("i: " + tv.i());
        System.out.println("took " + (System.currentTimeMillis() - start));
    }
    private static class TestVisitor
            implements CombinationVisitor5<Integer>
    {
        private Integer vals[];
        public TestVisitor(Integer ints[])
        {
            vals = ints.clone();
        }

        private int i = 0;
        public void visit(Integer itemA, Integer itemB, Integer itemC,
                          Integer itemD, Integer itemE)
        {
            swap(vals, itemA - 1, vals.length - 1);
            swap(vals, itemB - 1, vals.length - 2);
            swap(vals, itemC - 1, vals.length - 3);
            swap(vals, itemD - 1, vals.length - 4);
            swap(vals, itemE - 1, vals.length - 5);

            NestedVisitor nv = new NestedVisitor();

            FastCombiner<Integer> nfc =
                    new FastCombiner<Integer>(vals, vals.length - 5);
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
            implements CombinationVisitor2<Integer>
    {
        private int i = 0;
        public void visit(Integer itemA, Integer itemB)
        {
            i++;
        }
        public int i() {  return i;  }
    }
    private static void swap(Integer vals[], int i, int j)
    {
        Integer tmp = vals[i];
        vals[i] = vals[j];
        vals[j] = tmp;
    }
}



