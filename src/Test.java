
import java.io.*;
import java.util.*;

/**
 * You are given a tree consisting of n
 * n
 * nodes. The nodes are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * . Each node has a value.
 * <p>
 * Your task is to process following types of queries:
 * change the value of node s
 * s
 * to x
 * x
 * <p>
 * find the maximum value on the path between nodes a
 * a
 * and b
 * b
 * .
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
 * " or "2 a
 * a
 * b
 * b
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
 * 2 4 1 3 3
 * 1 2
 * 1 3
 * 2 4
 * 2 5
 * 2 3 5
 * 1 2 2
 * 2 3 5
 * <p>
 * Output:
 * 4 3
 */
@SuppressWarnings("unchecked")
public class Test implements Runnable {

    int n, dvalue[], dp[][], tree[][], depth[], size[];

    int values[], chainHead[], position[], chains[];
    int pos = 0, chainId = 0;
    SegmentTree seg;

    void solve() throws IOException {

        n = read.intNext();
        int q = read.intNext();
        dvalue = iArr(n);
        size = iArr(n);
        chainHead = iArr(n);
        values = iArr(n);
        position = iArr(n);
        chains = iArr(n);

        for (int i = 0; i < n; i++) {
            dvalue[i] = read.intNext();
        }

        depth = iArr(n);
        dp = new int[n][20];

        CreateTree ctree = new CreateTree(n - 1);

        for (int i = 0; i < n - 1; i++) {
            ctree.addEdge(read.intNext() - 1, read.intNext() - 1, i);
        }

        //Create a tree
        tree = ctree.create();

        //IterativeDfs();
        hd(0, -1);

        seg = new SegmentTree(values);


        while (q-- > 0) {
            int type = read.intNext();
            if (type == 1) {
                seg.update(position[read.intNext() - 1], read.intNext());
            } else {
                int a = read.intNext() - 1, b = read.intNext() - 1, lca = getlca(a, b);

                sbr.append(max(query(a, lca), query(b, lca))).append(' ');

            }
        }

        print(sbr.toString());
    }


    int query(int from, int to) {
        int ans = -1;

        while (chains[from] != chains[to]) {
            ans = max(ans, seg.query(position[chainHead[chains[from]]], position[from]));
            from = dp[chainHead[chains[from]]][0];
        }

        ans = max(ans, seg.query(position[to], position[from]));
        return ans;
    }


    //Method to do heavy light decomposition
    void hd(int node, int parent) {
        int heavyChild = -1, heavySize = 0;
        chains[node] = chainId; //chain of node
        position[node] = pos; //the position in segment tree
        values[pos] = dvalue[node];//Set the edge weight
        pos++;

        for (int child : tree[node]) {
            if (child != parent) {
                if (size[child] > heavySize) {
                    heavyChild = child;
                    heavySize = size[child];
                }
            }
        }

        if (heavyChild != -1) {
            //it is not a leaf
            hd(heavyChild, node);
        }

        for (int child : tree[node]) {
            if (child != parent && child != heavyChild) {
                chainId++;
                chainHead[chainId] = child;
                hd(child, node);
            }
        }
    }

    int getlca(int a, int b) {

        if (a == b) return a;

        if (depth[a] > depth[b]) { //swap the nodes
            a = a ^ b ^ (b = a);
        }

        for (int i = 19; i >= 0; i--) {
            if ((depth[b] - (1 << i)) >= depth[a]) {
                b = dp[b][i];
            }
        }

        if (a == b) return a;

        for (int i = 19; i >= 0; i--) {
            if ((dp[a][i] ^ dp[b][i]) != 0) {
                a = dp[a][i];
                b = dp[b][i];
            }
        }

        if (a == b) return a;

        return dp[a][0];

    }

    void dfs(int pos, int d, int parent) {
        depth[pos] = d++;
        size[pos] = 1;
        //Prepare a Binary lifting array
        for (int i = 1; i < 20; i++) {
            dp[pos][i] = dp[dp[pos][i - 1]][i - 1];
        }

        for (int child : tree[pos]) {
            if (child != parent) {
                dp[child][0] = pos;
                dfs(child, d, pos);
                size[pos] += size[child];
            }
        }
    }

    public void IterativeDfs() {

        int dep = 1;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        ArrayDeque<Node> queue2 = new ArrayDeque<>();
        queue.add(new Node(0, -1));

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node curr = queue.pollFirst();
                size[curr.index] = 1;
                depth[curr.index] = dep;

                //Prepare a Binary lifting array
                for (int j = 1; j < 20; j++) {
                    dp[curr.index][j] = dp[dp[curr.index][j - 1]][j - 1];
                }

                queue2.add(curr);
                for (int child : tree[curr.index]) {
                    if (curr.parent == -1 || child != curr.parent) {
                        dp[child][0] = curr.index;
                        queue.add(new Node(child, curr.index));
                    }
                }
            }
            dep++;
        }

        while (!queue2.isEmpty()) {
            Node curr = queue2.pollLast();
            if (curr.parent != -1) {
                size[curr.parent] += size[curr.index];
            }
        }
    }

    static class Node {
        int index;
        int parent;

        public Node(int index, int parent) {
            this.index = index;
            this.parent = parent;
        }
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new Test(), "1", 1 << 26).start();
    }


    public class SegmentTree {

        int[] seg;
        int[] arr;
        int len;

        public SegmentTree(int[] arr) {
            len = arr.length;
            this.arr = arr;
            seg = new int[len << 1];
            construct();
        }

        private void construct() {

            //Assign values to leaves of the segment Tree
            for (int i = 0; i < len; i++) {
                seg[len + i] = arr[i];
            }

            //Compute sum
            for (int i = len - 1; i >= 1; i--) {
                int pos = i << 1;
                seg[i] = Math.max(seg[pos], seg[pos + 1]);
            }
        }

        public void update(int target, int value) {
            target += len;

            seg[target] = value;

            while (target > 1) {
                //Move up by one level
                target >>= 1;
                int pos = target << 1;
                seg[target] = Math.max(seg[pos], seg[pos + 1]);
            }

        }

        public int query(int l, int r) {
            l += len;
            r += len;

            int res = Integer.MIN_VALUE;
            while (l <= r) {

                //If left index is odd
                if ((l & 1) == 1) {
                    res = Math.max(res, seg[l]);
                    l++; //make it even
                }

                //If right index is even
                if ((r & 1) == 0) {
                    res = Math.max(res, seg[r]);
                    r--;
                }

                //Move to the next higher level
                l >>= 1;
                r >>= 1;
            }
            return res;
        }
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