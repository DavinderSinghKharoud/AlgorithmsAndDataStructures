import java.io.*;
import java.util.*;

/**
 * FindGreatestCommonDivisor game consists of n
 * n
 *  rooms and m
 * m
 *  teleporters. At the beginning of each day, you start in room 1
 * 1
 *  and you have to reach room n
 * n
 * .
 *
 * You can use each teleporter at most once during the game. How many days can you play if you choose your routes optimally?
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the number of rooms and teleporters. The rooms are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * After this, there are m
 * m
 *  lines describing the teleporters. Each line has two integers a
 * a
 *  and b
 * b
 * : there is a teleporter from room a
 * a
 *  to room b
 * b
 * .
 *
 * There are no two teleporters whose starting and ending room are the same.
 *
 * Output
 *
 * First print an integer k
 * k
 * : the maximum number of days you can play the game. Then, print k
 * k
 *  route descriptions according to the example. You can print any valid solution.
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
 * 6 7
 * 1 2
 * 1 3
 * 2 6
 * 3 4
 * 3 5
 * 4 6
 * 5 6
 *
 * Output:
 * 2
 * 3
 * 1 2 6
 * 4
 * 1 3 4 6
 */
public class DistinctRoutes {

    static int n, m;
    static List<List<Edge>> arr = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();
    static Edge[] parent;

    static void solve() throws IOException {

        n = fastReader.intNext();
        m = fastReader.intNext();
        parent = new Edge[n];

        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }
        while (m-- > 0) {
            int a = fastReader.intNext() - 1, b = fastReader.intNext() - 1, w = 1;
            Edge a1 = new Edge(a, b, null, w);
            Edge b1 = new Edge(b, a, a1, 0);
            b1.isBack = true;
            a1.rev = b1;

            arr.get(a).add(a1);
            arr.get(b).add(b1);
        }

        int total = 0;
        while (true) {
            boolean[] vis = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            vis[0] = true;

            while (!queue.isEmpty()) {
                int u = queue.poll();

                for (Edge adj : arr.get(u)) {
                    int v = adj.v;
                    if (!vis[v] && adj.cap > 0) {
                        vis[v] = true;
                        parent[v] = adj;
                        if (v == n - 1) break;
                        queue.add(v);
                    }
                }
            }

            if (!vis[n - 1]) break;
            int min = dmax, end = n - 1;

            while (end > 0) {
                min = min(parent[end].cap);
                end = parent[end].u;
            }

            total += min;
            List<Integer> currRes = new ArrayList<>();
            end = n - 1;
            while (end > 0) {
                parent[end].cap -= min;
                parent[end].rev.cap += min;
                currRes.add(end);
                end = parent[end].u;
            }
            currRes.add(0);
            res.add(currRes);
        }


//		if( res.size() > 0 ){
//			println( res.size() );
//			for(int i = 0; i < res.size() ; i++ ){
//				List<Integer> curr = res.get(i);
//				println(curr.size() );
//				for(int j = curr.size() - 1; j >= 0; j-- ){
//					print( (curr.get(j) + 1) + " ");
//				}
//				println(" ");
//			}
//		}else println(0);

        if (total == 0) print(0);
        else {
            println(total);

            for (int i = 0; i < total; i++) {
                boolean[] vis = new boolean[n];
                vis[0] = true;
                List<Integer> temp = new ArrayList<>();
                dfs(0, vis, temp);
            }

        }
    }

    static void dfs(int node, boolean[] vis, List<Integer> res) {

        if (node == vis.length - 1) {
            println(res.size() + 1);
            for (int num : res) {
                print((num + 1) + " ");
            }
            println(vis.length);
            return;
        }

        vis[node] = true;
        res.add(node);

        for (Edge adj : arr.get(node)) {
            if (adj.cap != 0 || vis[adj.v] || adj.isBack) continue;
            dfs(adj.v, vis, res);
            adj.cap = 1;
            return;
        }


    }

    static class Edge {
        int u, v, cap;
        Edge rev;
        boolean isBack = false;


        public Edge(int u, int v, Edge rev, int cap) {
            this.u = u;
            this.v = v;
            this.rev = rev;
            this.cap = cap;
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


