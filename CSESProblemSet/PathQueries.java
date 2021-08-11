
import java.io.*;
import java.util.*;

/**
 * You are given a rooted tree consisting of n
 * n
 * nodes. The nodes are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * , and node 1
 * 1
 * is the root. Each node has a value.
 * <p>
 * Your task is to process following types of queries:
 * change the value of node s
 * s
 * to x
 * x
 * <p>
 * calculate the sum of values on the path from the root to node s
 * s
 * <p>
 * Input
 * <p>
 * The first input line contains two integers n
 * n
 * and q
 * q
 * : the number of nodes and queries. The nodes are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 * <p>
 * The next line has n
 * n
 * integers v1,v2,…,vn
 * v
 * 1
 * ,
 * v
 * 2
 * ,
 * …
 * ,
 * v
 * n
 * : the value of each node.
 * <p>
 * Then there are n−1
 * n
 * −
 * 1
 * lines describing the edges. Each line contains two integers a
 * a
 * and b
 * b
 * : there is an edge between nodes a
 * a
 * and b
 * b
 * .
 * <p>
 * Finally, there are q
 * q
 * lines describing the queries. Each query is either of the form "1 s
 * s
 * x
 * x
 * " or "2 s
 * s
 * ".
 * <p>
 * Output
 * <p>
 * Print the answer to each query of type 2.
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
 * 1≤a,b,s≤n
 * 1
 * ≤
 * a
 * ,
 * b
 * ,
 * s
 * ≤
 * n
 * <p>
 * 1≤vi,x≤109
 * 1
 * ≤
 * v
 * i
 * ,
 * x
 * ≤
 * 10
 * 9
 * <p>
 * Example
 * <p>
 * Input:
 * 5 3
 * 4 2 5 2 1
 * 1 2
 * 1 3
 * 3 4
 * 3 5
 * 2 4
 * 1 3 2
 * 2 4
 * <p>
 * Output:
 * 11
 * 8
 */
@SuppressWarnings("unchecked")
public class PathQueries implements Runnable {
    int n, dvalue[], dstart[], dend[], timer = 0;

    int[][] tree;
    long[] fen;

    void solve() throws IOException {
        n = read.intNext();
        int q = read.intNext();
        fen = lArr(n + 1);
        dstart = iArr(n);
        dend = iArr(n);
        dvalue = iArr(n);
        tree = new int[n][];

        for (int i = 0; i < n; i++) {
            dvalue[i] = read.intNext();
        }

        Tree obj = new Tree(n - 1);
        for (int i = 0; i < n - 1; ++i) {
            int a = read.intNext() - 1, b = read.intNext() - 1;
            obj.addEdge(a, b, i);

        }
        tree = obj.create();

        dfs(0, -1);

        for (int i = 0; i < n; i++) {
            update(dstart[i] + 1, dvalue[i]);
            update(dend[i] + 1, -dvalue[i]);
        }

        while (q-- > 0) {
            int type = read.intNext();
            if (type == 1) {
                int i = read.intNext() - 1, v = read.intNext();
                update(dstart[i] + 1, v - dvalue[i]);
                update(dend[i] + 1, dvalue[i] - v);
                dvalue[i] = v;
            } else {
                int i = read.intNext() - 1;
                sbr.append(query(dstart[i] + 1)).append(' ');
            }
        }
        println(sbr.toString());

    }

    private long query(int index) {
        long res = 0;

        for (; index > 0; index -= index & -index) {
            res += fen[index];
        }
        return res;
    }

    private void update(int index, int value) {
        for (; index <= n; index += index & -index) {
            fen[index] += value;
        }
    }

    private void dfs(int pos, int parent) {
        dstart[pos] = timer++;
        for (int child : tree[pos]) {
            if (child != parent) {
                dfs(child, pos);
            }
        }
        dend[pos] = timer;
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new PathQueries(), "Main").start();
    }

    static class Tree {
        int[] count, from, to;
        int len;

        public Tree(int len) {
            count = iArr(len + 1);
            from = iArr(len);
            to = iArr(len);
            this.len = len;
        }

        void addEdge(int a, int b, int index) {
            from[index] = a;
            to[index] = b;
            count[from[index]]++;
            count[to[index]]++;
        }

        int[][] create() {
            int[][] arr = new int[len + 1][];

            for (int i = 0; i < len + 1; i++) {
                arr[i] = new int[count[i]];
            }
            for (int i = 0; i < len; ++i) {
                arr[from[i]][--count[from[i]]] = to[i];
                arr[to[i]][--count[to[i]]] = from[i];
            }
            return arr;
        }
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;

    @Override
    public void run() {
        try {
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


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


    static final class Comparators {
        public static final Comparator<int[]> pairIntArr =
                (x, y) -> x[0] == y[0] ? compare(x[1], y[1]) : compare(x[0], y[0]);

        private static final int compare(final int x, final int y) {
            return Integer.compare(x, y);
        }
    }

    static int[] iArr(int len) {
        return new int[len];
    }

    static long[] lArr(int len) {
        return new long[len];
    }

    static void print(Object object) {
        out.print(object);
    }

    static void println(Object object) {
        out.println(object);
    }

    static long min(long a, long b) {
        return Math.min(a, b);
    }

    static int min(int a, int b) {
        return Math.min(a, b);
    }

    static long max(Long a, Long b) {
        return Math.max(a, b);
    }

    static int max(int a, int b) {
        return Math.max(a, b);
    }
}


