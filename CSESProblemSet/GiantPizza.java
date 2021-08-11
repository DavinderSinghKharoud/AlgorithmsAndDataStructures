import java.io.*;
import java.util.*;

/**
 * Time limit: 1.00 s Memory limit: 512 MB
 * Uolevi's family is going to order a large pizza and eat it together. A total of n
 * n
 * family members will join the order, and there are m
 * m
 * possible toppings. The pizza may have any number of toppings.
 * <p>
 * Each family member gives two wishes concerning the toppings of the pizza. The wishes are of the form "topping x
 * x
 * is good/bad". Your task is to choose the toppings so that at least one wish from everybody becomes true (a good topping is included in the pizza or a bad topping is not included).
 * <p>
 * Input
 * <p>
 * The first input line has two integers n
 * n
 * and m
 * m
 * : the number of family members and toppings. The toppings are numbered 1,2,…,m
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * m
 * .
 * <p>
 * After this, there are n
 * n
 * lines describing the wishes. Each line has two wishes of the form "+ x
 * x
 * " (topping x
 * x
 * is good) or "- x
 * x
 * " (topping x
 * x
 * is bad).
 * <p>
 * Output
 * <p>
 * Print a line with m
 * m
 * symbols: for each topping "+" if it is included and "-" if it is not included. You can print any valid solution.
 * <p>
 * If there are no valid solutions, print "IMPOSSIBLE".
 * <p>
 * Constraints
 * 1≤n,m≤105
 * 1
 * ≤
 * n
 * ,
 * m
 * ≤
 * 10
 * 5
 * <p>
 * 1≤x≤m
 * 1
 * ≤
 * x
 * ≤
 * m
 * <p>
 * Example
 * <p>
 * Input:
 * 3 5
 * + 1 + 2
 * - 1 + 3
 * + 4 - 2
 * <p>
 * Output:
 * - + + + -
 */
public class GiantPizza {


    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        List<List<Integer>> adj1 = new ArrayList<>(), adj2 = new ArrayList<>();

        boolean[] vis = new boolean[2 * m + 2];

        for (int i = 0; i < 2 * m + 2; i++) {
            adj1.add(new ArrayList<>());
            adj2.add(new ArrayList<>());
        }

        while (n-- > 0) {
            String s1 = fastReader.read();
            int a = fastReader.intNext();
            a *= 2;
            String s2 = fastReader.read();
            int b = fastReader.intNext();
            b *= 2;

            //For a V b ==>( ~a - > b) & ( ~b -> a )
            //Positive number is 2 * i and negative number is 2 * i + 1
            //Any even number after ^ operation add the one
            if (s1.equals("-")) a ^= 1;
            if (s2.equals("-")) b ^= 1;
            adj1.get(a ^ 1).add(b);
            adj1.get(b ^ 1).add(a);


        }

        for (int i = 0; i < adj1.size(); i++) {
            for (int j : adj1.get(i)) {
                adj2.get(j).add(i);
            }
        }


        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < adj1.size(); i++) {
            if (!vis[i]) dfs1(st, adj1, i, vis);
        }

        Arrays.fill(vis, false);
        int[] dp = new int[2 * m + 2];

        int group = 1;
        while (!st.isEmpty()) {
            int curr = st.pop();
            if (!vis[curr]) {
                dfs2(adj2, vis, curr, group++, dp);
            }
        }


        for (int i = 2; i <= 2 * m + 1; i += 2) {
            if (dp[i] == dp[i + 1]) {
                print("IMPOSSIBLE");
                return;
            }

            sbr.append((dp[i] > dp[i + 1]) ? '+' : '-').append(" ");

        }

        print(sbr);


    }

    static void dfs2(List<List<Integer>> adj, boolean[] vis, int node, int group, int[] ans) {
        vis[node] = true;

        for (int x : adj.get(node)) {
            if (!vis[x]) dfs2(adj, vis, x, group, ans);
        }
        ans[node] = group;

    }

    static void dfs1(Stack<Integer> st, List<List<Integer>> adj, int node, boolean[] vis) {
        vis[node] = true;
        for (int x : adj.get(node)) {
            if (!vis[x]) dfs1(st, adj, x, vis);
        }

        st.add(node);
    }

    static int getNode(int n) {
        return (n > 0) ? (2 * (n - 1) + 1) : (2 * (-n - 1));
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


