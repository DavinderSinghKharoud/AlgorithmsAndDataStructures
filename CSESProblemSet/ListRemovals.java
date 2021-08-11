import java.io.*;
import java.util.*;

/**
 * You are given a list consisting of n
 * n
 *  integers. Your task is to remove elements from the list at given positions, and report the removed elements.
 *
 * Input
 *
 * The first input line has an integer n
 * n
 * : the initial size of the list. During the process, the elements are numbered 1,2,…,k
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * k
 *  where k
 * k
 *  is the current size of the list.
 *
 * The second line has n
 * n
 *  integers x1,x2,…,xn
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
 * : the contents of the list.
 *
 * The last line has n
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
 * : the positions of the elements to be removed.
 *
 * Output
 *
 * Print the elements in the order they are removed.
 *
 * Constraints
 * 1≤n≤2⋅105
 * 1
 * ≤
 * n
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * 1≤xi≤109
 * 1
 * ≤
 * x
 * i
 * ≤
 * 10
 * 9
 *
 * 1≤pi≤n−i+1
 * 1
 * ≤
 * p
 * i
 * ≤
 * n
 * −
 * i
 * +
 * 1
 *
 * Example
 *
 * Input:
 * 5
 * 2 6 1 4 2
 * 3 1 3 1 1
 *
 * Output:
 * 1 2 2 6 4
 *
 * Explanation: The contents of the list are [2,6,1,4,2]
 * [
 * 2
 * ,
 * 6
 * ,
 * 1
 * ,
 * 4
 * ,
 * 2
 * ]
 * , [2,6,4,2]
 * [
 * 2
 * ,
 * 6
 * ,
 * 4
 * ,
 * 2
 * ]
 * , [6,4,2]
 * [
 * 6
 * ,
 * 4
 * ,
 * 2
 * ]
 * , [6,4]
 * [
 * 6
 * ,
 * 4
 * ]
 * , [4]
 * [
 * 4
 * ]
 *  and []
 * [
 * ]
 * .
 */
public class ListRemovals {

    static int[] dp, arr;

    static void solve() throws IOException {

        int n = read.intNext();

        dp = new int[1 << 19];
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = read.intNext();
        }

        constructTree(0, n - 1, 0);

        for (int i = 0; i < n; i++) {
            int tar = read.intNext();

            println( arr[update(0, n - 1, 0, tar)]);

        }
    }

    static int update(int l, int h, int pos, int target) {
        dp[pos]--;

        if( h - l == 0){
            return l;
        }

        int mid = l + (h - l)/2;
        if( dp[2 * pos + 1] >= target){
            return update(l, mid, 2 * pos + 1, target);
        }
        return update(mid + 1, h, 2 * pos + 2, target - dp[2 * pos + 1]);
    }

    static void constructTree(int l, int h, int pos) {

        if (l == h) {
            dp[pos] = 1;
            return;
        }

        int mid = l + (h - l) / 2;

        constructTree(l, mid, 2 * pos + 1);
        constructTree(mid + 1, h, 2 * pos + 2);

        dp[pos] = dp[2 * pos + 1] + dp[2 * pos + 2];
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


