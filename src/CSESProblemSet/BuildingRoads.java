import java.io.*;
import java.util.*;

/**
 * Byteland has n
 * n
 * cities, and m
 * m
 * roads between them. The goal is to construct new roads so that there is a route between any two cities.
 * <p>
 * Your task is to find out the minimum number of roads required, and also determine which roads should be built.
 * <p>
 * Input
 * <p>
 * The first input line has two integers n
 * n
 * and m
 * m
 * : the number of cities and roads. The cities are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 * <p>
 * After that, there are m
 * m
 * lines describing the roads. Each line has two integers a
 * a
 * and b
 * b
 * : there is a road between those cities.
 * <p>
 * A road always connects two different cities, and there is at most one road between any two cities.
 * <p>
 * Output
 * <p>
 * First print an integer k
 * k
 * : the number of required roads.
 * <p>
 * Then, print k
 * k
 * lines that describe the new roads. You can print any valid solution.
 * <p>
 * Constraints
 * 1≤n≤105
 * 1
 * ≤
 * n
 * ≤
 * 10
 * 5
 * <p>
 * 1≤m≤2⋅105
 * 1
 * ≤
 * m
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 * <p>
 * 1≤a,b≤n
 * 1
 * ≤
 * a
 * ,
 * b
 * ≤
 * n
 * <p>
 * Example
 * <p>
 * Input:
 * 4 2
 * 1 2
 * 3 4
 * <p>
 * Output:
 * 1
 * 2 3
 */
public class BuildingRoads {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        int[] parent = new int[n + 1];
        int[] size = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        Arrays.fill(size, 1);

        while (m-- > 0) {
            int a = fastReader.intNext();
            int b = fastReader.intNext();

            int parent1 = find(a, parent);
            int parent2 = find(b, parent);

            if (parent1 != parent2) {

                if (size[parent1] > size[parent2]) {
                    parent[parent2] = parent1;
                    size[parent1] += size[parent2];
                } else {
                    parent[parent1] = parent2;
                    size[parent2] += size[parent1];
                }
            }


        }

        boolean[] res = new boolean[n + 1];
        List<Integer> lst = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            int p = find(i, parent);
            res[p] = true;
        }

        for (int i = 0; i < res.length; i++) {
            if (res[i]) {
                lst.add(i);
            }
        }


        if (lst.size() == 1) {
            print(0);
        } else {
            println(lst.size() - 1);

            for (int i = 0; i < lst.size(); i++) {

                if (i < lst.size() - 1) {
                    sbr.append(lst.get(i)).append(" ");
                    sbr.append(lst.get(i + 1)).append("\n");
                }
            }
            print(sbr);
        }

    }

    static int find(int node, int[] parent) {
        if (parent[node] == node) return node;

        return parent[node] = find(parent[node], parent);
    }

    private static void solve2() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        List<List<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>());
        }
        while (m-- > 0) {
            int a = fastReader.intNext();
            int b = fastReader.intNext();
            --a;
            --b;
            nodes.get(a).add(b);
            nodes.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (!visited[i]) {
                res.add(i + 1);
                dfs(nodes, i, visited);
            }
        }

        println(res.size() - 1);
        for (int i = 1; i < res.size(); i++) {
            println(res.get(0) + " " + res.get(i));
        }
    }

    private static void dfs(List<List<Integer>> nodes, int node, boolean[] visited) {
        visited[node] = true;

        for (Integer adjacent : nodes.get(node)) {
            if (!visited[adjacent]) dfs(nodes, adjacent, visited);
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        //solve();
        solve2();
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


