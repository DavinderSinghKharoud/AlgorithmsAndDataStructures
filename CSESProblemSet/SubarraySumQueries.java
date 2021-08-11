import java.io.*;
import java.util.*;

/**
 * There is an array consisting of n
 * n
 *  integers. Some values of the array will be updated, and after each update, your task is to report the maximum subarray sum in the array.
 *
 * Input
 *
 * The first input line contains integers n
 * n
 *  and m
 * m
 * : the size of the array and the number of updates. The array is indexed 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * The next line has n
 * n
 *  integers: x1,x2,…,xn
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
 * : the initial contents of the array.
 *
 * Then there are m
 * m
 *  lines describing the changes. Each line has two integers k
 * k
 *  and x
 * x
 * : the value at position k
 * k
 *  becomes x
 * x
 * .
 *
 * Output
 *
 * After each update, print the maximum subarray sum. Empty subarrays (with sum 0
 * 0
 * ) are allowed.
 *
 * Constraints
 * 1≤n,m≤2⋅105
 * 1
 * ≤
 * n
 * ,
 * m
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * −109≤xi≤109
 * −
 * 10
 * 9
 * ≤
 * x
 * i
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
 * −109≤x≤109
 * −
 * 10
 * 9
 * ≤
 * x
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 5 3
 * 1 2 -3 5 -1
 * 2 6
 * 3 1
 * 2 -2
 *
 * Output:
 * 9
 * 13
 * 6
 */
public class SubarraySumQueries {

    static Node[] dp =  new Node[1 << 19];
    static void solve() throws IOException {

        int n = read.intNext(), q = read.intNext();
        int[] arr = new int[n];
        //dp = new Node[1 << 19];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new Node();
        }

        for (int i = 0; i < n; i++) {
            arr[i] = read.intNext();
        }
        consturctTree(0, n - 1, 0, arr);
//        for (int i = 0; i < n; i++) {
//            update(0, n - 1, 0, i, read.intNext());
//        }


        for (int i = 0; i < q; i++) {
            int a = read.intNext() - 1, b = read.intNext();

            update(0, n - 1, 0, a, b);

            long res = query(0, n - 1, 0, 0, n - 1);
            println(max(0l, res));
        }
    }

    static void consturctTree(int l, int h, int pos, int[] arr){
        if( l == h ){
            dp[pos].maxSum = dp[pos].sum = dp[pos].suffix = dp[pos].prefix = arr[l];
        }else{

            int mid = (h + l) / 2;

            consturctTree(l, mid, 2 * pos + 1, arr);
            consturctTree(mid + 1, h, 2 * pos + 2, arr);

            dp[pos].sum = dp[2 * pos + 1].sum + dp[2 * pos + 2].sum;
            dp[pos].prefix = Math.max(dp[2 * pos + 1].prefix, dp[2 * pos + 1].sum + dp[2 * pos + 2].prefix);
            dp[pos].suffix = Math.max(dp[2 * pos + 2].suffix, dp[2 * pos + 1].suffix + dp[2 * pos + 2].sum);
            dp[pos].maxSum = Math.max(Math.max(dp[2 * pos + 1].maxSum, dp[2 * pos + 2].maxSum), dp[2 * pos + 1].suffix + dp[2 * pos + 2].prefix);

        }
    }
    static long query(int l, int h, int pos, int ql, int qh) {
        if (l > qh || h < ql) return Long.MIN_VALUE;

        if (l >= ql && h <= qh) return dp[pos].maxSum;

        int mid = (h + l) / 2;

        return (long) max(query(l, mid, 2 * pos + 1, ql, qh), query(mid + 1, h, 2 * pos + 2, ql, qh));

    }

    static void update(int l, int h, int pos, int tar, int value) {
        if (tar < l || tar > h) return;
        if (l == h) {
            dp[pos].prefix = value;
            dp[pos].suffix = value;
            dp[pos].sum = dp[pos].maxSum = value;
            return;
        }

        int mid = (h + l) / 2;

        update(l, mid, 2 * pos + 1, tar, value);
        update(mid + 1, h, 2 * pos + 2, tar, value);

        dp[pos].sum = dp[2 * pos + 1].sum + dp[2 * pos + 2].sum;
        dp[pos].prefix = Math.max(dp[2 * pos + 1].prefix, dp[2 * pos + 1].sum + dp[2 * pos + 2].prefix);
        dp[pos].suffix = Math.max(dp[2 * pos + 2].suffix, dp[2 * pos + 1].suffix + dp[2 * pos + 2].sum);
        dp[pos].maxSum = Math.max(Math.max(dp[2 * pos + 1].maxSum, dp[2 * pos + 2].maxSum), dp[2 * pos + 1].suffix + dp[2 * pos + 2].prefix);

    }


    static class Node {
        long prefix, suffix, sum, maxSum;

        public Node() {
            prefix = 0;
            suffix = 0;
            sum = 0;
            maxSum = 0;
        }
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

    static Object max(Object... objects) {
        boolean isInteger = objects[0] instanceof Integer;
        if (isInteger) {
            int max = Integer.MIN_VALUE;

            for (Object num : objects) {
                max = Math.max(max, (Integer) num);
            }
            return max;
        }else{
            long max = Long.MIN_VALUE;

            for (Object num : objects) {
                max = Math.max(max, (Long) num);
            }
            return max;
        }

    }
}


