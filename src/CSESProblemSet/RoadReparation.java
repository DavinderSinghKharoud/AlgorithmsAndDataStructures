import java.io.*;
import java.util.*;

public class RoadReparation {

    /*
    There are n
    n
     cities and m
    m
     roads between them. Unfortunately, the condition of the roads is so poor that they cannot be used. Your task is to repair some of the roads so that there will be a decent route between any two cities.

    For each road, you know its reparation cost, and you should find a solution where the total cost is as small as possible.

    Input

    The first input line has two integers n
    n
     and m
    m
    : the number of cities and roads. The cities are numbered 1,2,…,n
    1
    ,
    2
    ,
    …
    ,
    n
    .

    Then, there are m
    m
     lines describing the roads. Each line has three integers a
    a
    , b
    b
     and c
    c
    : there is a road between cities a
    a
     and b
    b
    , and its reparation cost is c
    c
    . All roads are two-way roads.

    Every road is between two different cities, and there is at most one road between two cities.

    Output

    Print one integer: the minimum total reparation cost. However, if there are no solutions, print "IMPOSSIBLE".

    Constraints
    1≤n≤105
    1
    ≤
    n
    ≤
    10
    5

    1≤m≤2⋅105
    1
    ≤
    m
    ≤
    2
    ⋅
    10
    5

    1≤a,b≤n
    1
    ≤
    a
    ,
    b
    ≤
    n

    1≤c≤109
    1
    ≤
    c
    ≤
    10
    9

    Example

    Input:
    5 6
    1 2 3
    2 3 5
    2 4 2
    3 4 8
    5 1 7
    5 4 4

    Output:
    14
     */

    //Time complexity O( nLog(m) )
    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        int[][] edges = new int[m][3];

        int[] link = new int[n];
        int[] size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < m; i++) {
            edges[i] = new int[]{fastReader.intNext(), fastReader.intNext(), fastReader.intNext()};
            edges[i][0]--;
            edges[i][1]--;
        }
        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));

        for (int i = 0; i < n; i++) {
            link[i] = i;
        }

        long cost = 0;

        for (int[] edge : edges) {
            int parent1 = find(link, edge[0]);
            int parent2 = find(link, edge[1]);
            if (parent1 == parent2) continue;

            cost += edge[2];

            if (size[parent1] >= size[parent2]) {
                link[parent2] = parent1;
                size[parent1] += size[parent2];
            } else {
                link[parent1] = parent2;
                size[parent2] += size[parent1];
            }
        }


        int parent = find(link, 0);
        for (int i = 1; i < n; i++) {
            if (parent != find(link, i)) {
                print("IMPOSSIBLE");
                return;
            }
        }
        print(cost);

    }

    static int find(int[] link, int node) {
        if (node == link[node]) return node;
        return link[node] = find(link, link[node]);
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


