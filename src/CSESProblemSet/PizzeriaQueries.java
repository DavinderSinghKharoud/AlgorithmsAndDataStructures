import java.io.*;
import java.util.*;

/**
 * There are n
 * n
 *  buildings on a street, numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * . Each building has a pizzeria and an apartment.
 *
 * The pizza price in building k
 * k
 *  is pk
 * p
 * k
 * . If you order a pizza from building a
 * a
 *  to building b
 * b
 * , its price (with delivery) is pa+|a−b|
 * p
 * a
 * +
 * |
 * a
 * −
 * b
 * |
 * .
 *
 * Your task is to process two types of queries:
 * The pizza price pk
 * p
 * k
 *  in building k
 * k
 *  becomes x
 * x
 * .
 * You are in building k
 * k
 *  and want to order a pizza. What is the minimum price?
 * Input
 *
 * The first input line has two integers n
 * n
 *  and q
 * q
 * : the number of buildings and queries.
 *
 * The second line has n
 * n
 *  integers p1,p2,…,pn
 * p
 * 1
 * ,
 * p
 * 2
 * ,
 * …
 * ,
 * p
 * n
 * : the initial pizza price in each building.
 *
 * Finally, there are q
 * q
 *  lines that describe the queries. Each line is either "1 k
 * k
 *  x
 * x
 * " or "2 k
 * k
 * ".
 *
 * Output
 *
 * Print the answer for each query of type 2.
 *
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
 *
 * 1≤pi,x≤109
 * 1
 * ≤
 * p
 * i
 * ,
 * x
 * ≤
 * 10
 * 9
 *
 * 1≤k≤n
 * 1
 * ≤
 * k
 * ≤
 * n
 *
 * Example
 *
 * Input:
 * 6 3
 * 8 6 4 5 7 5
 * 2 2
 * 1 5 1
 * 2 2
 *
 * Output:
 * 5
 * 4
 * Range Queries
 *
 * ...
 * List Removals
 * Salary Queries
 * Prefix Sum Queries
 * Pizzeria Queries
 * Subarray Sum Queries
 * Distinct Values Queries
 * Increasing Array Queries
 * Forest Queries II
 * ...
 * Your submissions
 *
 * 2021-01-29 09:03:28
 * 2021-01-29 08:51:43
 * 2021-01-29 08:47:16
 * 2021-01-29 07:15:54
 */
public class PizzeriaQueries {

    static int[] seg1 = new int[1 << 19], seg2 = new int[1 << 19];
    static int[] arr;

    static void solve() throws IOException {

        int n = read.intNext(), q = read.intNext();

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = read.intNext();
        }

        for (int i = 0; i < n; i++) {
            update1(0, n - 1, 0, i, arr[i]);
            update2(0, n - 1, 0, i, arr[i]);
        }

        for (int i = 0; i < q; i++) {
            int type = read.intNext();
            int index = read.intNext() - 1;
            if (type == 1) {
                int val = read.intNext();
                update1(0, n - 1, 0, index, val);
                update2(0, n - 1, 0, index, val);
            } else {
                long a = query(0, n - 1, 0, index, n - 1, seg1);
                long b = query(0, n - 1, 0, 0, index, seg2);
                println(Math.min(a - (index), b + (index)));
            }
        }
    }


    static long query(int l, int h, int pos, int ql, int qh, int[] tree) {
        if ( l > qh || h < ql) return Long.MAX_VALUE;
        if (l >= ql && h <= qh) {
            return tree[pos];
        }

        int mid = (h + l) / 2;

        return Math.min(query(l, mid, 2 * pos + 1, ql, qh, tree), query(mid + 1, h, 2 * pos + 2, ql, qh, tree));

    }

    static void update1(int l, int h, int pos, int tar, int value) {
        if (tar < l || tar > h) return;
        if (l == h) {
            seg1[pos] = tar + value;
            return;
        }

        int mid = (h + l) / 2;
        update1(l, mid, 2 * pos + 1, tar, value);
        update1(mid + 1, h, 2 * pos + 2, tar, value);

        seg1[pos] = min(seg1[2 * pos + 1], seg1[2 * pos + 2]);
    }

    static void update2(int l, int h, int pos, int tar, int value) {
        if (tar < l || tar > h) return;
        if (l == h) {
            seg2[pos] = value - tar;
            return;
        }

        int mid = (h + l) / 2;
        update2(l, mid, 2 * pos + 1, tar, value);
        update2(mid + 1, h, 2 * pos + 2, tar, value);

        seg2[pos] = min(seg2[2 * pos + 1], seg2[2 * pos + 2]);
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

    static int max(Object... objects) {
        int max = Integer.MIN_VALUE;

        for (Object num : objects) {
            max = Math.max(max, (Integer) num);
        }
        return max;
    }
}


