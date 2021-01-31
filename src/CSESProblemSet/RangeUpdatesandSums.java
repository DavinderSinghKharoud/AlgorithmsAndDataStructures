import java.io.*;
import java.util.*;

/**
 * Your task is to maintain an array of n
 * n
 *  values and efficiently process the following types of queries:
 * Increase each value in range [a,b]
 * [
 * a
 * ,
 * b
 * ]
 *  by x
 * x
 * .
 * Set each value in range [a,b]
 * [
 * a
 * ,
 * b
 * ]
 *  to x
 * x
 * .
 * Calculate the sum of values in range [a,b]
 * [
 * a
 * ,
 * b
 * ]
 * .
 * Input
 *
 * The first input line has two integers n
 * n
 *  and q
 * q
 * : the array size and the number of queries.
 *
 * The next line has n
 * n
 *  values t1,t2,…,tn
 * t
 * 1
 * ,
 * t
 * 2
 * ,
 * …
 * ,
 * t
 * n
 * : the initial contents of the array.
 *
 * Finally, there are q
 * q
 *  lines describing the queries. The format of each line is one of the following: "1 a
 * a
 * b
 * b
 *  x
 * x
 * ", "2 a
 * a
 * b
 * b
 *  x
 * x
 * ", or "3 a
 * a
 *  b
 * b
 * ".
 *
 * Output
 *
 * Print the answer to each sum query.
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
 * 1≤ti,x≤106
 * 1
 * ≤
 * t
 * i
 * ,
 * x
 * ≤
 * 10
 * 6
 *
 * 1≤a≤b≤n
 * 1
 * ≤
 * a
 * ≤
 * b
 * ≤
 * n
 *
 * Example
 *
 * Input:
 * 6 5
 * 2 3 1 1 5 3
 * 3 3 5
 * 1 2 4 2
 * 3 3 5
 * 2 2 4 5
 * 3 3 5
 *
 * Output:
 * 7
 * 11
 * 15
 */
public class RangeUpdatesandSums {

    Node[] seg;
    int[] arr;
    int n;


    void solve() throws IOException {

        n = read.intNext();
        int q = read.intNext();
        int len = nextPowOfTwo(n);
        arr = new int[n];
        seg = new Node[2 * len + 1];

        for (int i = 0; i < seg.length; i++) {
            seg[i] = new Node();
        }
        for (int i = 0; i < n; i++) {
            arr[i] = read.intNext();
        }

        constructTree(0, n - 1, 0);


        for (int i = 0; i < q; i++) {
            int type = read.intNext();
            if (type == 3) {
                println(query(0, n - 1, read.intNext() - 1, read.intNext() - 1, 0));
            } else if (type == 2) {
                update2(0, n - 1, read.intNext() - 1, read.intNext() - 1, read.intNext(), 0);
            } else {
                update1(0, n - 1, read.intNext() - 1, read.intNext() - 1, read.intNext(), 0);
            }

        }
    }

    //Reset the value in range
    void update2(int l, int h, int ql, int qh, long value, int pos) {


        if (l > qh || h < ql) return; //No Overlapp

        if (l >= ql && h <= qh) {
            seg[pos].sum = (h - l + 1) * value;
            seg[pos].setAll = value;
            seg[pos].increment = 0;
            seg[pos].setAllValid = true;

        } else {
            updateAllValues(l, h, pos);
            int mid = (h + l) / 2;
            update2(l, mid, ql, qh, value, 2 * pos + 1);
            update2(mid + 1, h, ql, qh, value, 2 * pos + 2);
            updateAllValues(l, mid, 2 * pos + 1);
            updateAllValues(mid + 1, h, 2 * pos + 2);
            seg[pos].sum = seg[2 * pos + 1].sum + seg[2 * pos + 2].sum;
        }

    }


    //increase the value in range
    void update1(int l, int h, int ql, int qh, int value, int pos) {

        if (l > qh || h < ql) return; //No Overlapp

        if (l >= ql && h <= qh) {
            seg[pos].increment += value;
        } else {
            updateAllValues(l, h, pos);
            int mid = (h + l) / 2;
            update1(l, mid, ql, qh, value, 2 * pos + 1);
            update1(mid + 1, h, ql, qh, value, 2 * pos + 2);
            updateAllValues(l, mid, 2 * pos + 1);
            updateAllValues(mid + 1, h, 2 * pos + 2);
            seg[pos].sum = seg[2 * pos + 1].sum + seg[2 * pos + 2].sum;
        }

    }

    void updateAllValues(int l, int h, int pos) {
        if (seg[pos].setAllValid) {
            //reset everything
            seg[pos].sum = (h - l + 1) * seg[pos].setAll;
        }

        seg[pos].sum += (h - l + 1) * seg[pos].increment;
        if (l != h) {
            compose(pos, 2 * pos + 1);
            compose(pos, 2 * pos + 2);
        }
        seg[pos].reset();
    }

    private void compose(int pos, int child) {
        if (seg[pos].setAllValid) {
            seg[child].setAllValid = true;
            seg[child].setAll = seg[pos].setAll;
            seg[child].increment = seg[pos].increment;
        } else {
            seg[child].increment += seg[pos].increment;
        }
    }

    long query(int l, int h, int ql, int qh, int pos) {
        if (l > qh || h < ql) return 0;

        updateAllValues(l, h, pos);

        if (l >= ql && h <= qh) {
            return seg[pos].sum;
        }

        int mid = (h + l) / 2;

        return query(l, mid, ql, qh, 2 * pos + 1) + query(mid + 1, h, ql, qh, 2 * pos + 2);
    }

    void constructTree(int l, int h, int pos) {
        if (l == h) {
            seg[pos].sum = arr[l];
        } else {
            int mid = (h + l) / 2;

            constructTree(l, mid, 2 * pos + 1);
            constructTree(mid + 1, h, 2 * pos + 2);

            seg[pos].sum = seg[2 * pos + 1].sum + seg[2 * pos + 2].sum;
        }
    }

    static int nextPowOfTwo(int len) {
        if (len == 0) return 1;
        if ((len & (len - 1)) == 0)
            return len;

        while ((len & (len - 1)) != 0) {
            len = (len & (len - 1));
        }
        return len << 1;
    }

    static class Node {
        long sum, setAll, increment;
        boolean setAllValid = false;

        public Node() {
            sum = setAll = increment = 0;
        }

        void reset() {
            increment = 0;
            setAllValid = false;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        RangeUpdatesandSums object = new RangeUpdatesandSums();
        object.solve();

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


