import java.io.*;
import java.util.*;

/**
 * Your task is to find the k
 * k
 *  shortest flight routes from Syrjälä to Metsälä. FindGreatestCommonDivisor route can visit the same city several times.
 *
 * Note that there can be several routes with the same price and each of them should be considered (see the example).
 *
 * Input
 *
 * The first input line has three integers n
 * n
 * , m
 * m
 * , and k
 * k
 * : the number of cities, the number of flights, and the parameter k
 * k
 * . The cities are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * . City 1 is Syrjälä, and city n
 * n
 *  is Metsälä.
 *
 * After this, the input has m
 * m
 *  lines describing the flights. Each line has three integers a
 * a
 * , b
 * b
 * , and c
 * c
 * : a flight begins at city a
 * a
 * , ends at city b
 * b
 * , and its price is c
 * c
 * . All flights are one-way flights.
 *
 * You may assume that there are at least k
 * k
 *  distinct routes from Syrjälä to Metsälä.
 *
 * Output
 *
 * Print k
 * k
 *  integers: the prices of the k
 * k
 *  cheapest routes sorted according to their prices.
 *
 * Constraints
 * 2≤n≤105
 * 2
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
 * 1≤k≤10
 * 1
 * ≤
 * k
 * ≤
 * 10
 *
 * Example
 *
 * Input:
 * 4 6 3
 * 1 2 1
 * 1 3 3
 * 2 3 2
 * 2 4 6
 * 3 2 8
 * 3 4 1
 *
 * Output:
 * 4 4 7
 *
 * Explanation: The cheapest routes are 1→3→4
 * 1
 * →
 * 3
 * →
 * 4
 *  (price 4
 * 4
 * ), 1→2→3→4
 * 1
 * →
 * 2
 * →
 * 3
 * →
 * 4
 *  (price 4
 * 4
 * ) and 1→2→4
 * 1
 * →
 * 2
 * →
 * 4
 *  (price 7
 * 7
 * ).
 */
public class FlightRoutes {
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

        int k = fastReader.intNext();

        List<List<int[]>> lst = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            lst.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = fastReader.intNext();
            int b = fastReader.intNext();
            int w = fastReader.intNext();
            --a;
            --b;
            lst.get(a).add(new int[]{b, w});
        }

        List<List<Long>> dis = new ArrayList<>();
        lmax = (long) 1e9;

        for (int i = 0; i < n; i++) {
            dis.add(new ArrayList<>());
        }
        PriorityQueue<long[]> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        queue.add(new long[]{0, 0});

        while (!queue.isEmpty()) {
            long[] curr = queue.poll();
            int i = (int) curr[0];
            if (dis.get(i).size() >= k) continue;
            dis.get(i).add(curr[1]);

            for (int[] adj : lst.get(i)) {
                queue.add(new long[]{adj[0], curr[1] + adj[1]});
            }
        }

        for (int i = 0; i < k; i++) {
            print(dis.get(n - 1).get(i) + " ");
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


