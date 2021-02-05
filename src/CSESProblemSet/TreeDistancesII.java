import java.io.*;
import java.util.*;

/**
 * You are given a tree consisting of n
 * n
 *  nodes.
 *
 * Your task is to determine for each node the sum of the distances from the node to all other nodes.
 *
 * Input
 *
 * The first input line contains an integer n
 * n
 * : the number of nodes. The nodes are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * Then there are n−1
 * n
 * −
 * 1
 *  lines describing the edges. Each line contains two integers a
 * a
 *  and b
 * b
 * : there is an edge between nodes a
 * a
 *  and b
 * b
 * .
 *
 * Output
 *
 * Print n
 * n
 *  integers: for each node 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * , the sum of the distances.
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
 * 1≤a,b≤n
 * 1
 * ≤
 * a
 * ,
 * b
 * ≤
 * n
 *
 * Example
 *
 * Input:
 * 5
 * 1 2
 * 1 3
 * 3 4
 * 3 5
 *
 * Output:
 * 6 9 5 8 8
 */
@SuppressWarnings("unchecked")
public class TreeDistancesII {

    long[] total, res;
    static int n;
    int[] count;
    ArrayDeque<Integer>[] tree;

    void solve() throws IOException {

        n = read.intNext();
        tree = new ArrayDeque[n];
        total = new long[n];
        res = new long[n];
        count = new int[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int a = read.intNext() - 1, b = read.intNext() - 1;
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(0, -1);

        dfs2(0, -1, total[0]);

        for (int i = 0; i < n; i++) {
            sbr.append(res[i]).append(" ");
        }
        print(sbr.toString());
    }

    private void dfs2(int pos, int parent, long partial_sum) {
        res[pos] = partial_sum;

        for (int child : tree[pos]) {
            if (child != parent) {

                dfs2(child, pos, partial_sum - count[child] + (n - count[child]));
            }
        }
    }

    void dfs(int pos, int parent) {
        count[pos] = 1;
        for (int child : tree[pos]) {
            if (child != parent) {
                dfs(child, pos);
                total[pos] += total[child] + count[child];
                count[pos] += count[child];
            }
        }

    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        TreeDistancesII object = new TreeDistancesII();
        object.solve();
        out.close();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder(n + 1);
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


    static long min(Long... objects) {
        long min = Integer.MAX_VALUE;

        for (Long num : objects) {
            min = Math.min(min, num);
        }
        return min;
    }

    static int min(Integer... objects) {
        int min = Integer.MAX_VALUE;

        for (Integer num : objects) {
            min = Math.min(min, num);
        }
        return min;
    }

    static long max(Long... objects) {
        long max = Long.MIN_VALUE;

        for (Long num : objects) {
            max = Math.max(max, num);
        }
        return max;
    }

    static int max(Integer... objects) {
        int max = Integer.MIN_VALUE;

        for (Integer num : objects) {
            max = Math.max(max, num);
        }
        return max;
    }
}


