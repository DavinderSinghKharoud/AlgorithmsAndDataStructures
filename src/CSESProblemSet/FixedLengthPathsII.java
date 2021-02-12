import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class FixedLengthPathsII implements Runnable {


    int n, k1, k2, subtree[], tree[][], max_depth, count[], bit[];

    long ans;
    boolean visited[];

    void solve() throws IOException {

        n = read.intNext();
        k1 = read.intNext();
        k2 = read.intNext();

        subtree = iArr(n + 1);
        bit = iArr(n + 1);
        count = iArr(n + 1);
        visited = new boolean[n + 1];

        CreateTree ctree = new CreateTree(n);

        for (int i = 1; i < n; i++) {
            int a = read.intNext(), b = read.intNext();
            ctree.addEdge(a, b, i);
        }

        tree = ctree.create();
        update(0, 1);
//        count[0] = 1;
        centroidDecomposition(1);

        println(ans);
    }


    void centroidDecomposition(int node) {
        getSubtreeSize(node, -1);
        int centroid = getCentroid(subtree[node] >> 1, node, -1);

        //put centroid to true
        visited[centroid] = true;
        max_depth = 0;
        for (int child : tree[centroid]) {
            if (!visited[child]) {
                getCount(child, centroid, false, 1);
                getCount(child, centroid, true, 1);
            }
        }


        for (int i = 1; i <= max_depth; i++) {
            update( i, -query(i, i));
        }
        // Arrays.fill(count, 1, max_depth + 1, 0);
        for (int child : tree[centroid]) {
            if (!visited[child]) {
                centroidDecomposition(child);
            }
        }
    }

    void getCount(int node, int parent, boolean filling, int depth) {
        if (depth > k2) return;

        max_depth = max(max_depth, depth);
        if (filling) {
            update(depth, 1);
//            count[depth]++;
        } else {
            ans += query(max(0, k1 - depth), k2 - depth);
//            for (int i = k1; i <= k2; i++) {
//                ans += (i - depth < 0) ? count[0] : count[i - depth];
//            }
        }

        for (int child : tree[node]) {
            if (!visited[child] && child != parent) {
                getCount(child, node, filling, depth + 1);
            }
        }
    }

    int getCentroid(int desired, int node, int parent) {
        for (int child : tree[node]) {
            if (child != parent && !visited[child] && subtree[child] >= desired) {
                return getCentroid(desired, child, node);
            }
        }

        return node;
    }

    void getSubtreeSize(int node, int parent) {
        subtree[node] = 1;
        for (int child : tree[node]) {
            if (!visited[child] && child != parent) {
                getSubtreeSize(child, node);
                subtree[node] += subtree[child];
            }
        }
    }

    long query(int l, int r) {
        long ans = 0;
        for (r++; r > 0; r -= r & -r) ans += bit[r];
        for (; l > 0; l -= l & -l) ans -= bit[l];
        return ans;
    }

    void update(int node, long value) {
        for (node++; node <= n; node += node & -node) {
            bit[node] += value;
        }

    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new FixedLengthPathsII(), "1").start();
    }


    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;

    static class CreateTree {
        int[] count, from, to;
        int len;

        public CreateTree(int len) {
            count = iArr(len + 1);
            from = iArr(len + 1);
            to = iArr(len + 1);
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
            for (int i = 1; i < len; ++i) {
                arr[from[i]][--count[from[i]]] = to[i];
                arr[to[i]][--count[to[i]]] = from[i];
            }
            return arr;
        }
    }

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


    static void print(Object object) {
        out.print(object);
    }

    static void println(Object object) {
        out.println(object);
    }


    static int[] iArr(int len) {
        return new int[len];
    }

    static long[] lArr(int len) {
        return new long[len];
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