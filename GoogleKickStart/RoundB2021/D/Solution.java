package GoogleKickStart.RoundB2021.D;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Solution implements Runnable {

    int limit = 5 * (int) 1e4 + 1;
    ArrayList<long[]> tree[];
    long gcd = 0;

    void solve() throws IOException {
        int t = ri();
        for (int tt = 1; tt <= t; tt++) {
            tree = new ArrayList[limit];
            int n = ri() - 1, q = ri();
            while (n-- > 0) {
                int x = ri(), y = ri();
                long l = ri();
                long toll = rl();
                if (tree[x] == null) {
                    tree[x] = new ArrayList<>();
                }
                if (tree[y] == null) {
                    tree[y] = new ArrayList<>();
                }
                tree[x].add(new long[]{y, l, toll});
                tree[y].add(new long[]{x, l, toll});
            }

            sbr = new StringBuilder();
            sbr.append(" ");
            while (q-- > 0) {
                int curr = ri(), w = ri();
                gcd = 0;
                traverse(curr, w, new boolean[limit]);
                sbr.append(gcd).append(" ");

            }
            sbr.deleteCharAt(sbr.length() - 1);
            gPrint(tt, sbr.toString());
        }
    }

    boolean traverse(int node, int weight, boolean[] vis) {
        if (vis[node])
            return false;
        vis[node] = true;
        if (node == 1)
            return true;
        for (long[] adj : tree[node]) {
            if (traverse((int) adj[0], weight, vis)) {
                if (adj[1] <= weight) {
                    gcd = findGcd(gcd, adj[2]);
                }
                return true;
            }
        }
        return false;
    }

    long findGcd(long a, long b) {
        if (a < b)
            a = a ^ b ^ (b = a);
        return gcd(a, b);
    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    ArrayList<Node> tree2[];
    ArrayDeque<int[]> queries[];
    long[] ans;
    int[] count;
    int limitL = (int) 1e5 * 2;

    public void solve2() throws IOException {
        int t = ri();
        for (int tt = 1; tt <= t; tt++) {
            int n = ri(), q = ri();
            SegmentTree seg = new SegmentTree(limitL + 1);
            tree2 = new ArrayList[n];
            Arrays.setAll(tree2, o -> new ArrayList<>());
            n--;
            while (n-- > 0) {
                int a = ri() - 1, b = ri() - 1, l = ri();
                long toll = rl();
                tree2[a].add(new Node(b, l, toll));
                tree2[b].add(new Node(a, l, toll));
            }

            queries = new ArrayDeque[tree2.length];
            for (int i = 0; i < q; i++) {
                int node = ri() - 1, w = ri();
                if (queries[node] == null)
                    queries[node] = new ArrayDeque<>();
                queries[node].add(new int[]{w, i});
            }

            ans = new long[q];

            while (queries[0] != null && !queries[0].isEmpty()) {
                int[] query = queries[0].poll();
                ans[query[1]] = 0;
            }

            count = new int[limitL + 1];
            preOrder(0, new boolean[tree2.length], seg);

            sbr = new StringBuilder(" ");
            for (long num : ans) {
                sbr.append(num).append(" ");
            }

            sbr.deleteCharAt(sbr.length() - 1);
            gPrint(tt, sbr.toString());
        }
    }

    void preOrder(int node, boolean[] vis, SegmentTree seg) {
        vis[node] = true;
        for (Node adj : tree2[node]) {
            if (!vis[adj.node]) {
                seg.update(adj.weight, adj.toll);
                preOrder(adj.node, vis, seg);
                seg.update(adj.weight, 0);
            }
        }

        if (queries[node] != null) {
            while (!queries[node].isEmpty()) {
                int[] query = queries[node].poll();
                ans[query[1]] = seg.query(1, query[0]);
            }
        }

    }

    class Node {
        int node, weight;
        long toll;

        public Node(int a, int w, long tax) {
            toll = tax;
            weight = w;
            node = a;
        }
    }

    public class SegmentTree {

        long[] arr;
        int[] nodes;
        int n;

        public SegmentTree(int len) {
            arr = new long[(getSize(len) * 2 + 1)];
            n = len;
            // this.nodes = nodes;
            // construct(0, n - 1, 0);
        }

        void construct(int l, int h, int pos) {
            if (l == h) {
                arr[pos] = nodes[l];
            } else {
                int mid = l + (h - l) / 2;
                int p = pos << 1;
                construct(l, mid, p + 1);
                construct(mid + 1, h, p + 2);
                arr[pos] = Math.max(arr[p + 1], arr[p + 2]);
            }
        }

        void update(int node, long value) {
            update(0, n - 1, 0, node, value);
        }

        private void update(int l, int h, int pos, int node, long value) {
            if (l == h) {
                arr[pos] = value;
            } else {
                int mid = l + (h - l) / 2;
                int p = pos << 1;
                if (node <= mid) {
                    update(l, mid, p + 1, node, value);
                } else {
                    update(mid + 1, h, p + 2, node, value);
                }

                arr[pos] = findGcd(arr[p + 1], arr[p + 2]);
            }
        }

        long query(int a, int b) {
            return query(0, n - 1, 0, a, b);
        }

        private long query(int l, int h, int pos, int a, int b) {
            if (l > b || h < a)
                return 0;
            if (l >= a && h <= b) {
                return arr[pos];
            }

            int mid = l + (h - l) / 2;
            int p = pos << 1;

            return findGcd(query(l, mid, p + 1, a, b), query(mid + 1, h, p + 2, a, b));
        }

        int getSize(int len) {
            if (len < 2)
                return 1;

            if ((len & (len - 1)) == 0)
                return len;

            while ((len & (len - 1)) != 0) {
                len = len & (len - 1);
            }

            return len << 1;
        }
    }

    /************************************************************************************************************************************************/
    static void gPrint(int i, Object obj) {
        if (obj instanceof String) {
            println(String.format("Case #%d: %s", i, obj));
        } else {
            println(String.format("Case #%d: %d", i, obj));
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Solution(), "1").start();
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
            // solve();
            solve2();
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
                } else
                    throw new InputMismatchException();
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
                } else
                    throw new InputMismatchException();
            }
            if (n == '.') {
                n = scan();
                double temp = 1;
                while (!isWhiteSpace(n)) {
                    if (n >= '0' && n <= '9') {
                        temp /= 10;
                        doub += (n - '0') * temp;
                        n = scan();
                    } else
                        throw new InputMismatchException();
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

    static void shuffle(int[] aa) {
        int n = aa.length;
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
    }

    static void shuffle(int[][] aa) {
        int n = aa.length;
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int first = aa[i][0];
            int second = aa[i][1];
            int third = aa[i][2];
            aa[i][0] = aa[j][0];
            aa[i][1] = aa[j][1];
            aa[i][2] = aa[j][2];
            aa[j][0] = first;
            aa[j][1] = second;
            aa[j][2] = third;
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

    static int ri() throws IOException {
        return read.intNext();
    }

    static long rl() throws IOException {
        return Long.parseLong(read.read());
    }

    static String rs() throws IOException {
        return read.read();
    }

    static char rc() throws IOException {
        return rs().charAt(0);
    }

    static double rd() throws IOException {
        return read.doubleNext();
    }
}
