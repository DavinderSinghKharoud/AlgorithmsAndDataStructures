import java.io.*;
import java.util.*;

/**
 * There are n
 * n
 *  boys and m
 * m
 *  girls in a school. Next week a school dance will be organized. FindGreatestCommonDivisor dance pair consists of a boy and a girl, and there are k
 * k
 *  potential pairs.
 *
 * Your task is to find out the maximum number of dance pairs and show how this number can be achieved.
 *
 * Input
 *
 * The first input line has three integers n
 * n
 * , m
 * m
 *  and k
 * k
 * : the number of boys, girls, and potential pairs. The boys are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * , and the girls are numbered 1,2,…,m
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * m
 * .
 *
 * After this, there are k
 * k
 *  lines describing the potential pairs. Each line has two integers a
 * a
 *  and b
 * b
 * : boy a
 * a
 *  and girl b
 * b
 *  are willing to dance together.
 *
 * Output
 *
 * First print one integer r
 * r
 * : the maximum number of dance pairs. After this, print r
 * r
 *  lines describing the pairs. You can print any valid solution.
 *
 * Constraints
 * 1≤n,m≤500
 * 1
 * ≤
 * n
 * ,
 * m
 * ≤
 * 500
 *
 * 1≤k≤1000
 * 1
 * ≤
 * k
 * ≤
 * 1000
 *
 * 1≤a≤n
 * 1
 * ≤
 * a
 * ≤
 * n
 *
 * 1≤b≤m
 * 1
 * ≤
 * b
 * ≤
 * m
 *
 * Example
 *
 * Input:
 * 3 2 4
 * 1 1
 * 1 2
 * 2 1
 * 3 1
 *
 * Output:
 * 2
 * 1 2
 * 3 1
 */
public class SchoolDance {

    static int nxM = 2500;

    static void solve() throws IOException {
        int n = fastReader.intNext(), m = fastReader.intNext(), q = fastReader.intNext();

        Edge[] parent = new Edge[nxM];
        Edge[] resParent = new Edge[nxM];

        List<List<Edge>> arr = new ArrayList<>(nxM);
        for (int i = 0; i < nxM; i++) {
            arr.add(new ArrayList<>());
        }


        for (int i = 0; i < q; i++) {
            int a = fastReader.intNext() - 1, b = fastReader.intNext() - 1 + n, w = 1;

            Edge a1 = new Edge(a, b, w, null);
            Edge b1 = new Edge(b, a, 0, a1);
            a1.rev = b1;

            arr.get(a).add(a1);
            arr.get(b).add(b1);
        }


        int source = nxM - 2;
        int sink = nxM - 1;
        //Add the source and sink to the graph

        for (int boy = 0; boy < n; boy++) {
            Edge a1 = new Edge(source, boy, 1, null);
            Edge b1 = new Edge(boy, source, 0, a1);
            a1.rev = b1;

            arr.get(source).add(a1);
            arr.get(boy).add(b1);
        }

        for (int girl = n; girl < n + m; girl++) {
            Edge a1 = new Edge(girl, sink, 1, null);
            Edge b1 = new Edge(sink, girl, 0, a1);
            a1.rev = b1;
            arr.get(girl).add(a1);
            arr.get(sink).add(b1);
        }

        long res = 0;
        while (true) {
            //BFS
            boolean[] vis = new boolean[nxM];
            vis[source] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);

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

            if (!vis[sink]) break;

            int end = sink;
            long min = parent[end].cap;
            while (end != source) {
                min = Math.min(min, parent[end].cap);
                end = parent[end].u;
            }

            res += min;
            end = sink;
            //Decrease the flow
            while (end != source) {
                parent[end].cap -= min;
                parent[end].rev.cap += min;
                resParent[end] = parent[end];
                end = parent[end].u;
            }
        }

        println(res);
        for (int girl = n; girl < n + m; girl++) {
            if( resParent[girl] != null){
                println( (resParent[girl].u + 1) + " " + (resParent[girl].v - n + 1));

            }
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


