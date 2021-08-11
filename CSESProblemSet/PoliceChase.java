import java.io.*;
import java.util.*;

/**
 * Kaaleppi has just robbed a bank and is now heading to the harbor. However, the police wants to stop him by closing some streets of the city.
 *
 * What is the minimum number of streets that should be closed so that there is no route between the bank and the harbor?
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the number of crossings and streets. The crossings are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * . The bank is located at crossing 1
 * 1
 * , and the harbor is located at crossing n
 * n
 * .
 *
 * After this, there are m
 * m
 *  lines that describing the streets. Each line has two integers a
 * a
 *  and b
 * b
 * : there is a street between crossings a
 * a
 *  and b
 * b
 * . All streets are two-way streets, and there is at most one street between two crossings.
 *
 * Output
 *
 * First print an integer k
 * k
 * : the minimum number of streets that should be closed. After this, print k
 * k
 *  lines describing the streets. You can print any valid solution.
 *
 * Constraints
 * 2≤n≤500
 * 2
 * ≤
 * n
 * ≤
 * 500
 *
 * 1≤m≤1000
 * 1
 * ≤
 * m
 * ≤
 * 1000
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
 * 4 5
 * 1 2
 * 1 3
 * 2 3
 * 3 4
 * 1 4
 *
 * Output:
 * 2
 * 3 4
 */
public class PoliceChase {


    static void solve() throws IOException {

        int n = fastReader.intNext(), m = fastReader.intNext();

        Edge[] parent = new Edge[2 * n + 2];
        List<Edge> edges = new ArrayList<>(m);

        List<List<Edge>> arr = new ArrayList<>(2 * n + 2);
        for (int i = 0; i < 2 * n + 1; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = fastReader.intNext() - 1, b = fastReader.intNext() - 1, w = 1;

            Edge a1 = new Edge(a, b, w, null);
            Edge b1 = new Edge(b, a, w, a1);
            a1.rev = b1;

            edges.add(a1);
            edges.add(b1);
            arr.get(a).add(a1);
            arr.get(b).add(b1);
        }

        long res = 0;
        while (true) {
            //BFS
            boolean[] vis = new boolean[n];
            vis[0] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);

            while (!queue.isEmpty()) {
                int u = queue.poll();

                for (Edge edge : arr.get(u)) {
                    int v = edge.v;
                    if (edge.cap > 0 && !vis[v]) {
                        vis[v] = true;
                        parent[v] = edge;
                        queue.add(v);
                    }
                }
            }

            if (!vis[n - 1]) break;

            int end = n - 1;
            long min = parent[end].cap;
            while (end > 0) {
                min = Math.min(min, parent[end].cap);
                end = parent[end].u;
            }

            res += min;
            end = n - 1;
            //Decrease the flow
            while (end > 0) {
                parent[end].cap -= min;
                parent[end].rev.cap += min;
                end = parent[end].u;
            }
        }

        println(res);
        boolean[] vis = new boolean[n];
        dfs(arr, vis, 0);


        for (Edge edge : edges) {
            if( edge.cap > 0  && !vis[edge.u] && vis[edge.v]){
                println( (edge.v + 1) + " " + (edge.u + 1));
            }
        }
    }


    private static void dfs(List<List<Edge>> arr, boolean[] vis, int node) {
        vis[node] = true;

        for (Edge adj : arr.get(node)) {
           if( !vis[adj.v] && adj.cap > 0 ) dfs( arr, vis, adj.v);
        }
    }

    static class Edge {
        int u, v;
        Edge rev;
        long cap;

        public Edge(int u, int v, long cap, Edge rev) {
            this.u = u;
            this.v = v;
            this.cap = cap;
            this.rev = rev;
        }

    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
        out.close();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
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


