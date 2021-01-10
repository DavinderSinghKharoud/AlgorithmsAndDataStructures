import java.io.*;
import java.util.*;

/**
 * You play a game consisting of n
 * n
 *  rooms and m
 * m
 *  tunnels. Your initial score is 0
 * 0
 * , and each tunnel increases your score by x
 * x
 *  where x
 * x
 *  may be both positive or negative. You may go through a tunnel several times.
 *
 * Your task is to walk from room 1
 * 1
 *  to room n
 * n
 * . What is the maximum score you can get?
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the number of rooms and tunnels. The rooms are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * Then, there are m
 * m
 *  lines describing the tunnels. Each line has three integers a
 * a
 * , b
 * b
 *  and x
 * x
 * : the tunnel starts at room a
 * a
 * , ends at room b
 * b
 * , and it increases your score by x
 * x
 * . All tunnels are one-way tunnels.
 *
 * You can assume that it is possible to get from room 1
 * 1
 *  to room n
 * n
 * .
 *
 * Output
 *
 * Print one integer: the maximum score you can get. However, if you can get an arbitrarily large score, print −1
 * −
 * 1
 * .
 *
 * Constraints
 * 1≤2500≤n
 * 1
 * ≤
 * 2500
 * ≤
 * n
 *
 * 1≤5000≤m
 * 1
 * ≤
 * 5000
 * ≤
 * m
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
 * −109≤x≤109
 * −
 * 10
 * 9
 * ≤
 * x
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 4 5
 * 1 2 3
 * 2 4 -1
 * 1 3 -2
 * 3 4 7
 * 1 4 4
 *
 * Output:
 * 5
 */
public class HighScore {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;static int dmax = Integer.MAX_VALUE;static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();
        List<List<int[]>> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }

        while (m-- > 0) {
            int a = fastReader.intNext();
            int b = fastReader.intNext();
            int w = fastReader.intNext();
            a--;
            b--;
            arr.get(a).add(new int[]{b, -1 * w});
        }

        Set<Integer> set = new HashSet<>(); //To get the nodes that are in negative cycle

        long[] dis = new long[n];

        if (bellmanFord(arr, set, dis)) {
            print(dis[n - 1]* -1);
        } else {
            boolean[] visited = new boolean[n];

            for (int node : set) {
                if (!visited[node]) dfs(visited, arr, node);
            }

            if (visited[0] || visited[n - 1]) print(-1); //As there is a negative cycle
            else print(dis[n - 1] * -1);
        }


    }

    static void dfs(boolean[] visited, List<List<int[]>> arr, int node) {
        visited[node] = true;

        for (int[] adj : arr.get(node)) {
            if (!visited[adj[0]]) dfs(visited, arr, adj[0]);
        }
    }

    static boolean bellmanFord(List<List<int[]>> arr, Set<Integer> set, long[] dis) {

        Arrays.fill(dis, Long.MAX_VALUE);

        int n = dis.length;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] inQueue = new boolean[dis.length];
        int[] count = new int[n];
        boolean isCycle = false;
        dis[0] = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            inQueue[curr] = false;

            for (int[] adj : arr.get(curr)) {
                //Relax the vertex if possible
                if (dis[adj[0]] > dis[curr] + adj[1]) {
                    dis[adj[0]] = dis[curr] + adj[1];
                    if (!inQueue[adj[0]]) {
                        count[adj[0]]++;
                        if (count[adj[0]] >= n) {//Only used to check if it has negative cycles
                            set.add(adj[0]);
                            isCycle = true;
                        } else {
                            queue.add(adj[0]);
                            inQueue[adj[0]] = true;
                        }
                    }
                }
            }
        }
        return !isCycle;
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


