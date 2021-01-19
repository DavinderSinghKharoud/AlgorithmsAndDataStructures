import java.io.*;
import java.util.*;

public class HamiltonianFlights {

    static List<List<Integer>> arr = new ArrayList<>();

    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }
        while (m-- > 0) {
            int a = fastReader.intNext() - 1, b = fastReader.intNext() - 1;
            arr.get(a).add(b);
        }

        boolean[] vis = new boolean[n];
        Deque<Integer> deque = new ArrayDeque<>();
        vis[0] = true;
        deque.add(0);

        print(dfs(0, n, vis, deque));

    }


    static long dfs(int node, int n, boolean[] vis, Deque<Integer> deque) {
        if (deque.size() == n && deque.peekLast() == n - 1) return 1;

        long total = 0;
        for (int i = 0; i < arr.get(node).size(); i++) {
            int v = arr.get(node).get(i);
            if (v == n - 1 && deque.size() != n - 1) continue;
            if (!vis[v]) {
                vis[v] = true;
                deque.addLast(v);
                total = (total + dfs(v, n, vis, deque)) % mod;
                deque.pollLast();
                vis[v] = false;
            }
        }
        return total;
    }

    private static void solve2() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();
        int[][] dp = new int[1 << n][n];
        int[][] arr = new int[n][n];

        while (m-- > 0) {
            int a = fastReader.intNext() - 1, b = fastReader.intNext() - 1;
            arr[a][b]++;
        }

//        // bottom-up
        dp[1][0] = 1;
        for (int i = 1; i < (1 << n) - 1; i += 2) {
            if ((i & (1 << n - 1)) != 0) {
                continue;
            }
            for (int j = 0; j < n - 1; ++j) {
                if ((i & (1 << j)) == 0 || dp[i][j] == 0) { //Check if subset contains the node
                    continue;
                }
                for (int k = 0, end = n; k < end; ++k) {
                    if (arr[j][k] != 0) {
                        if ((i & (1 << k)) != 0)
                            continue;
                        int newLoc = i | (1 << k);
                        dp[newLoc][k] += arr[j][k] * dp[i][j];
                        dp[newLoc][k] %= mod;
                    }
                }
            }
        }
        print(dp[(1 << n) - 1][n - 1]);

    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        // solve();
        solve2();
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


