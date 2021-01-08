import java.io.*;
import java.util.*;

/**
 * Syrjälä's network has n
 * n
 *  computers and m
 * m
 *  connections. Your task is to find out if Uolevi can send a message to Maija, and if it is possible, what is the minimum number of computers on such a route.
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the number of computers and connections. The computers are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * . Uolevi's computer is 1
 * 1
 *  and Maija's computer is n
 * n
 * .
 *
 * Then, there are m
 * m
 *  lines describing the connections. Each line has two integers a
 * a
 *  and b
 * b
 * : there is a connection between those computers.
 *
 * Every connection is between two different computers, and there is at most one connection between any two computers.
 *
 * Output
 *
 * If it is possible to send a message, first print k
 * k
 * : the minimum number of computers on a valid route. After this, print an example of such a route. You can print any valid solution.
 *
 * If there are no routes, print "IMPOSSIBLE".
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
 * Example
 *
 * Input:
 * 5 5
 * 1 2
 * 1 3
 * 1 4
 * 2 3
 * 5 4
 *
 * Output:
 * 3
 * 1 4 5
 */
public class MessageRoute {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;


    static class Pair {
        int node;
        int distance;
        Pair parent;

        public Pair(int node, int distance, Pair parent) {
            this.node = node;
            this.distance = distance;
            this.parent = parent;
        }
    }

    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        List<List<Integer>> lst = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            lst.add(new ArrayList<>());
        }

        while (m-- > 0) {
            int a = fastReader.intNext();
            int b = fastReader.intNext();
            --a;
            --b;
            lst.get(a).add(b);
            lst.get(b).add(a);

        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0, null));
        boolean[] visited = new boolean[n];
        visited[0] = true;

        boolean isFound = false;
        Pair des = null;
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            if (curr.node == n - 1) {
                des = curr;
                isFound = true;
            } else {

                for (int adj : lst.get(curr.node)) {
                    if (visited[adj]) continue;
                    visited[adj] = true;
                    queue.add(new Pair(adj, curr.distance + 1, curr));
                }

            }

            if (isFound) break;
        }

        if (isFound) {

            List<Integer> res = new ArrayList<>();

            while (des != null) {
                res.add(des.node);
                des = des.parent;
            }

            println(res.size());
            for (int i = res.size() - 1; i >= 0; i--) {
                print((res.get(i) + 1) + " ");
            }

        } else {
            print("IMPOSSIBLE");
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


