import java.io.*;
import java.util.*;

/**
 * Given an array of n
 * n
 * integers, your task is to process q
 * q
 * queries of the following types:
 * update the value at position k
 * k
 * to u
 * u
 * <p>
 * what is the maximum prefix sum in range [a,b]
 * [
 * a
 * ,
 * b
 * ]
 * ?
 * Input
 * <p>
 * The first input line has two integers n
 * n
 * and q
 * q
 * : the number of values and queries.
 * <p>
 * The second line has n
 * n
 * integers x1,x2,…,xn
 * x
 * 1
 * ,
 * x
 * 2
 * ,
 * …
 * ,
 * x
 * n
 * : the array values.
 * <p>
 * Finally, there are q
 * q
 * lines describing the queries. Each line has three integers: either "1
 * 1
 * k
 * k
 * u
 * u
 * " or "2
 * 2
 * a
 * a
 * b
 * b
 * ".
 * <p>
 * Output
 * <p>
 * Print the result of each query of type 2.
 * <p>
 * Constraints
 * 1≤n,q≤2⋅105
 * 1
 * ≤
 * n
 * ,
 * q
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 * <p>
 * −109≤xi,u≤109
 * −
 * 10
 * 9
 * ≤
 * x
 * i
 * ,
 * u
 * ≤
 * 10
 * 9
 * <p>
 * 1≤k≤n
 * 1
 * ≤
 * k
 * ≤
 * n
 * <p>
 * 1≤a≤b≤n
 * 1
 * ≤
 * a
 * ≤
 * b
 * ≤
 * n
 * <p>
 * Example
 * <p>
 * Input:
 * 8 4
 * 1 2 -1 3 1 -5 1 4
 * 2 2 6
 * 1 4 -2
 * 2 2 6
 * 2 3 4
 * <p>
 * Output:
 * 5
 * 2
 * 0
 */
public class PrefixSumQueries {

	/**
	 * For finding out the maximum prefix sum, we will require two things, one being the sum and the other prefix sum. The merging will return two things, sum of the ranges and the prefix sum that will store the max(prefix.left, prefix.sum + prefix.right) in the segment tree.
	 * */
    static long[][] seg = new long[1 << 19][2];
    static int[] arr;

    static void solve() throws IOException {

        int n = read.intNext(), q = read.intNext();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = read.intNext();
        }

        //constructTree(0, n - 1, 0);

        for (int i = 0; i < n; i++) {
            update(0, n - 1, 0, i, arr[i]);
        }
        
        for (int i = 0; i < q; i++) {
            int type = read.intNext();
            if (type == 1) {
                update(0, n - 1, 0, read.intNext() - 1, read.intNext());
            } else {
                long[] curr = query(0, n - 1, 0, read.intNext() - 1, read.intNext() - 1);
                println((curr[0] < 0) ? 0 : curr[0]);
            }
        }

    }

    static long[] query(int l, int h, int pos, int ql, int qh) {

        if (l >= ql && h <= qh) {
            return seg[pos];
        }

        int mid = (h + l) / 2;

        if (ql > mid) return query(mid + 1, h, 2 * pos + 2, ql, qh);
        if (qh <= mid) return query(l, mid, 2 * pos + 1, ql, qh);

        long[] a = query(l, mid, 2 * pos + 1, ql, qh);
        long[] b = query(mid + 1, h, 2 * pos + 2, ql, qh);

        long[] curr = new long[2];
        curr[1] = a[1] + b[1];

        curr[0] = max(a[0], a[1] + b[0]);
        return curr;
    }

    static void update(int l, int h, int pos, int tar, int value) {
        if (tar < l || tar > h) return;
        if (l == h) {
            seg[pos][0] = value;
            seg[pos][1] = value;
            return;
        }
        int mid = (h + l) / 2;

        update(l, mid, 2 * pos + 1, tar, value);
        update(mid + 1, h, 2 * pos + 2, tar, value);

        seg[pos][1] = seg[2 * pos + 1][1] + seg[2 * pos + 2][1];
        seg[pos][0] = max(seg[2 * pos + 1][0], seg[2 * pos + 1][1] + seg[2 * pos + 2][0]);
    }

    static void constructTree(int l, int h, int pos) {
        if (l == h) {
            seg[pos][0] = arr[l];
            seg[pos][1] = arr[l];
            return;
        }

        int mid = (h + l) / 2;

        constructTree(l, mid, 2 * pos + 1);
        constructTree(mid + 1, h, 2 * pos + 2);

        seg[pos][1] = seg[2 * pos + 1][1] + seg[2 * pos + 2][1];
        seg[pos][0] = max(seg[2 * pos + 1][0], seg[2 * pos + 1][1] + seg[2 * pos + 2][0]);
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
        out.close();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;

    static class Reader {
        private byte[] buf = new byte[1024];
        private int index;
        private InputStream in;
        private int total;

        public Reader() {
            in = System.in;
        }

        public int scan() throws IOException {
            if (total < 0)
                throw new InputMismatchException();
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0)
                    return -1;
            }
            return buf[index++];
        }

        public int intNext() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else throw new InputMismatchException();
            }
            return neg * integer;
        }

        public double doubleNext() throws IOException {
            double doub = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n) && n != '.') {
                if (n >= '0' && n <= '9') {
                    doub *= 10;
                    doub += n - '0';
                    n = scan();
                } else throw new InputMismatchException();
            }
            if (n == '.') {
                n = scan();
                double temp = 1;
                while (!isWhiteSpace(n)) {
                    if (n >= '0' && n <= '9') {
                        temp /= 10;
                        doub += (n - '0') * temp;
                        n = scan();
                    } else throw new InputMismatchException();
                }
            }
            return doub * neg;
        }

        public String read() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            while (!isWhiteSpace(n)) {
                sb.append((char) n);
                n = scan();
            }
            return sb.toString();
        }

        private boolean isWhiteSpace(int n) {
            if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
                return true;
            return false;
        }
    }

    static void shuffle(int[] aa, int n) {
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
    }

    static void shuffle(int[][] aa, int n) {
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int first = aa[i][0];
            int second = aa[i][1];
            aa[i][0] = aa[j][0];
            aa[i][1] = aa[j][1];
            aa[j][0] = first;
            aa[j][1] = second;
        }
    }

    /**
     * Tree Multiset utility class *
     */
    static class TMultiset<T extends Number> extends TreeMap<T, Integer> {
        private int size = 0;

        void add(T value) {
            Integer count = get(value);
            size++;
            if (count == null) {
                put(value, 1);
            } else {
                put(value, count + 1);
            }
        }

        @SuppressWarnings(value = "unchecked")
        @Override
        public Integer remove(Object key) {
            if (!containsKey(key)) {
                return null;
            }

            size--;

            Integer value = get(key);
            if (value > 1) {
                return put((T) key, value - 1);
            }

            return super.remove(key);
        }

        @java.lang.Override
        public int size() {
            return size;
        }
    }

    /**
     * It is a HashMap
     */
    static class HMap<T> extends HashMap<T, Integer> {
        void add(T key) {
            Integer count = get(key);
            put(key, count == null ? 1 : count + 1);
        }

        @SuppressWarnings(value = "unchecked")
        @Override
        public Integer remove(Object key) {
            if (!containsKey(key)) return null;

            int count = get(key);
            if (count > 1) return put((T) key, count - 1);

            return super.remove(key);
        }
    }

    static final class Comparators {
        public static final Comparator<int[]> pairIntArr =
                (x, y) -> x[0] == y[0] ? compare(x[1], y[1]) : compare(x[0], y[0]);

        private static final int compare(final int x, final int y) {
            return Integer.compare(x, y);
        }
    }


    static void print(Object object) {
        out.print(object);
    }

    static void println(Object object) {
        out.println(object);
    }


    static int min(Object... objects) {
        int min = Integer.MAX_VALUE;

        for (Object num : objects) {
            min = Math.min(min, (Integer) num);
        }
        return min;
    }

    static long max(Long... objects) {
        long max = Long.MIN_VALUE;
        for (Object num : objects) {
            max = Math.max(max, (Long) num);
        }
        return max;
    }

    static int max(Integer... objects) {
        int max = Integer.MIN_VALUE;

        for (Object num : objects) {
            max = Math.max(max, (Integer) num);
        }
        return max;
    }
}


