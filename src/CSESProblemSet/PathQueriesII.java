import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class PathQueriesII implements Runnable {

    int n, max[][], dp[][], dvalue[], tree[][], depth[];

    void solve() throws IOException {

        n = read.intNext();
        int q = read.intNext();
        dvalue = iArr(n);
        for (int i = 0; i < n; i++) {
            dvalue[i] = read.intNext();
        }

        depth = iArr(n);
        dp = new int[n][20];
        max = new int[n][20];

        CreateTree ctree = new CreateTree(n - 1);

        for (int i = 0; i < n - 1; i++) {
            ctree.addEdge(read.intNext() - 1, read.intNext() - 1, i);
        }

        //Create a tree
        tree = ctree.create();
        dfs(0, 1, -1);

        //Prepare a Binary lifting array
        for (int i = 1; i < 20; i++) {
            for (int node = 1; node < n; node++) {
                dp[node][i] = dp[dp[node][i - 1]][i - 1];

                max[node][i] = max(max[node][i - 1], max[max[node][i - 1]][i - 1]);
            }

        }

        while (q-- > 0) {
            int type = read.intNext();
            if (type == 1) {

                int i = read.intNext() - 1, v = read.intNext();

                update(i, v, dp[i][0]);
            } else {
                int a = read.intNext() - 1, b = read.intNext() - 1;
                sbr.append(getMax(a, b)).append(' ');
            }
        }

        print(sbr.toString());


    }

    int getMax(int a, int b) {

        int res = dmin;
        //We need to find the lca
        if (a == b) return dvalue[a];

        if (depth[a] > depth[b]) { //swap the nodes
            a = a ^ b ^ (b = a);
        }

        for (int i = 19; i >= 0; i--) {
            if ((depth[b] - (1 << i)) >= depth[a]) {
                res = max[b][i];
                b = dp[b][i];
            }
        }

        if (a == b) return res;

        for (int i = 19; i >= 0; i--) {
            if ((dp[a][i] ^ dp[b][i]) != 0) {
                res = max(res, max(max[a][i], max[b][i]));
                a = dp[a][i];
                b = dp[b][i];
            }
        }

        if (a == b) return res;

        return max(max[a][0], max[b][0]);

    }


    void update(int index, int value, int parent) {
        dvalue[index] = value;
        max[index][0] = max(value, dvalue[parent]);
        for (int i = 1; i < 20; i++) {
            max[index][i] = max(dvalue[dp[index][i - 1]], dvalue[dp[dp[index][i - 1]][i - 1]]);
        }
    }

    void dfs(int pos, int d, int parent) {
        depth[pos] = d++;

        for (int child : tree[pos]) {
            if (child != parent) {

                dp[child][0] = pos;
                max[child][0] = max(dvalue[child], dvalue[pos]);
                dfs(child, d, pos);
            }
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new PathQueriesII(), "1", 1 << 26).start();
    }

    static class CreateTree {
        int[] count, from, to;
        int len;

        public CreateTree(int len) {
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


