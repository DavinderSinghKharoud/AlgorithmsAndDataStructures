import java.io.*;
import java.util.*;

/**
 * There are n
 * n
 *  cities and m
 * m
 *  flight connections between them. Your task is to determine the length of the shortest route from Syrjälä to every city.
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the number of cities and flight connections. The cities are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * , and city 1
 * 1
 *  is Syrjälä.
 *
 * After that, there are m
 * m
 *  lines describing the flight connections. Each line has three integers a
 * a
 * , b
 * b
 *  and c
 * c
 * : a flight begins at city a
 * a
 * , ends at city b
 * b
 * , and its length is c
 * c
 * . Each flight is a one-way flight.
 *
 * You can assume that it is possible to travel from Syrjälä to all other cities.
 *
 * Output
 *
 * Print n
 * n
 *  integers: the shortest route lengths from Syrjälä to cities 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * Constraints
 * 1≤n≤105
 * 1
 * ≤
 * n
 * ≤
 * 10
 * 5
 *
 * 1≤m≤2⋅105
 * 1
 * ≤
 * m
 * ≤
 * 2
 * ⋅
 * 10
 * 5
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
 * 1≤c≤109
 * 1
 * ≤
 * c
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 3 4
 * 1 2 6
 * 1 3 2
 * 3 2 3
 * 1 3 4
 *
 * Output:
 * 0 5 2
 */
public class ShortestRoutesI {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        List<List<int[]>> lst = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            lst.add(new ArrayList<>());
        }

        while (m-- > 0) {
            int a = fastReader.intNext();
            int b = fastReader.intNext();
            int w = fastReader.intNext();
            --a;
            --b;
            lst.get(a).add(new int[]{b, w});
        }

        long[] res = new long[n];

        //We will use Dijkstra Algorithm to find the shortest path
        Arrays.fill(res, Long.MAX_VALUE);
        res[0] = 0;
        boolean[] visited = new boolean[n];
        PriorityQueue<long[]> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        queue.add(new long[]{0, 0});

        while (!queue.isEmpty()) {
            long[] curr = queue.poll();
            int node = (int) curr[0];
            if(curr[1] > res[node]) continue;
            if (visited[node]) continue;
            visited[node] = true;

            for (int[] adj : lst.get(node)) {
                if (!visited[adj[0]]) {
                    if (res[node] + adj[1] < res[adj[0]]) {
                        res[adj[0]] = res[node] + adj[1];
                        queue.add(new long[]{adj[0], res[adj[0]]});
                    }
                }

            }
        }

        for (int i = 0; i < n; i++) {
            sbr.append(res[i]).append(" ");
        }
        print(sbr);

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


