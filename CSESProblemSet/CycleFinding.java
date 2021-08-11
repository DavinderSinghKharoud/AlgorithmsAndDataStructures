import java.io.*;
import java.util.*;

/**
 * You are given a directed graph, and your task is to find out if it contains a negative cycle, and also give an example of such a cycle.
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the number of nodes and edges. The nodes are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * After this, the input has m
 * m
 *  lines describing the edges. Each line has three integers a
 * a
 * , b
 * b
 * , and c
 * c
 * : there is an edge from node a
 * a
 *  to node b
 * b
 *  whose length is c
 * c
 * .
 *
 * Output
 *
 * If the graph contains a negative cycle, print first "YES", and then the nodes in the cycle in their correct order. If there are several negative cycles, you can print any of them. If there are no negative cycles, print "NO".
 *
 * Constraints
 * 1≤n≤2500
 * 1
 * ≤
 * n
 * ≤
 * 2500
 *
 * 1≤m≤5000
 * 1
 * ≤
 * m
 * ≤
 * 5000
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
 * −109≤c≤109
 * −
 * 10
 * 9
 * ≤
 * c
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 4 5
 * 1 2 1
 * 2 4 1
 * 3 1 1
 * 4 1 -3
 * 4 3 -2
 *
 * Output:
 * YES
 * 1 2 4 1
 */
public class CycleFinding {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;

    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        int[][] arr = new int[m][3];

        for (int i = 0; i < m; i++) {
            int a = fastReader.intNext();
            int b = fastReader.intNext();
            int w = fastReader.intNext();
            --a;
            --b;

            arr[i] = new int[]{a, b, w};

        }

        long inf = (long) 1e9;
        int[] parent = new int[n];
        long[] dis = new long[n];
        Arrays.fill(dis, inf);
        dis[0] = 0;

        int last = -1;
        for (int count = 0; count < n; count++) {

            last = -1;
            for (int[] edge : arr) {

                if (dis[edge[1]] > dis[edge[0]] + edge[2]) {
                    dis[edge[1]] = Math.max(-1 * inf, dis[edge[0]] + edge[2]);
                    last = edge[1];
                    parent[edge[1]] = edge[0];
                }
            }
        }

        if (last == -1) {
            print("NO");
        } else {

            println("YES");
            List<Integer> lst = new ArrayList<>();
            int start = last;

            for (int i = 0; i < n; i++) {
                start = parent[start];
            }

            for (int curr = start; ; curr = parent[curr]) {
                lst.add(curr);
                if (curr == start && lst.size() > 1) {
                    break;
                }
            }

            for (int index = lst.size() - 1; index >= 0; index--) {
                print((lst.get(index) + 1) + " ");
            }


        }
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
        out.close();
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


