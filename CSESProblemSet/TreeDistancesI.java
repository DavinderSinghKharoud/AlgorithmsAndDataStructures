import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class TreeDistancesI {

    int[] dp, res;
    ArrayDeque<Integer>[] tree;

    //Time Limit Exceeded
    void solve() throws IOException {

        int n = read.intNext();
        dp = new int[n];
        res = new int[n];
        tree = new ArrayDeque[n];

        for (int i = 0; i < n; i++) {
            tree[i] = (new ArrayDeque<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int a = read.intNext() - 1, b = read.intNext() - 1;
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(0, -1);

        dfs2(0, -1, 0);

        for (int num : res) {
            sbr.append(num).append("\n");
        }
        print(sbr);
    }

    void dfs2(int pos, int parent, int partial_max) {


        res[pos] = max(dp[pos], partial_max);

        //Evaluate the max distance
        //Assume the pos node does not exist for the parent

        List<int[]> curr = new ArrayList<>();
        for (int child : tree[pos]) {
            if (child != parent) {
                curr.add(new int[]{dp[child] + 1, child});
            }
        }

        curr.add(new int[]{0, -1});
        curr.add(new int[]{partial_max, -1});

        curr.sort((o1, o2) -> Integer.compare(o2[0], o1[0]));

        for (int child : tree[pos]) {
            if (child != parent) {
                dfs2(child, pos, (curr.get(0)[1] == child) ? curr.get(1)[0] + 1 : curr.get(0)[0] + 1);
            }
        }

    }

    void dfs(int pos, int parent) {

        for (int child : tree[pos]) {
            if (child != parent) {
                dfs(child, pos);
                dp[pos] = max(dp[pos], dp[child] + 1);
            }
        }
    }


    int[] best1, best2; //Store the top two max distance from nodes

    private void solve2() throws IOException {
        int n = read.intNext();
        dp = new int[n];
        best1 = new int[n];
        best2 = new int[n];
        res = new int[n];
        tree = new ArrayDeque[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int a = read.intNext() - 1, b = read.intNext() - 1;
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs3(0, -1);

        dfs4(0, -1, 0);

        for (int num : res) {
            sbr.append(num).append("\n");
        }
        print(sbr);
    }

    private void dfs3(int pos, int parent) {
        for (int child : tree[pos]) {
            if (child != parent) {
                dfs3(child, pos);

                int childMax = dp[child] + 1;
                if (childMax > best2[pos]) {
                    best2[pos] = childMax;
                    if (best2[pos] > best1[pos]) {
                        int temp = best1[pos];
                        best1[pos] = best2[pos];
                        best2[pos] = temp;
                    }
                }
                dp[pos] = max(dp[pos], childMax);
            }
        }
    }

    private void dfs4(int pos, int parent, int partial_sum) {
        res[pos] = max(best1[pos], partial_sum);

        for (int child : tree[pos]) {
            if (child != parent) {
                int nextMax = max(partial_sum, best1[pos]);
                if (best1[pos] == dp[child] + 1) {//If max is in same path
                    nextMax = max(partial_sum, best2[pos]);
                }
                dfs4(child, pos, nextMax + 1);
            }
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        TreeDistancesI object = new TreeDistancesI();
        //object.solve();
        object.solve2();
        out.close();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
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


    static long min(Long... objects) {
        long min = Integer.MAX_VALUE;

        for (Long num : objects) {
            min = Math.min(min, num);
        }
        return min;
    }

    static int min(Integer... objects) {
        int min = Integer.MAX_VALUE;

        for (Integer num : objects) {
            min = Math.min(min, num);
        }
        return min;
    }

    static long max(Long... objects) {
        long max = Long.MIN_VALUE;

        for (Long num : objects) {
            max = Math.max(max, num);
        }
        return max;
    }

    static int max(Integer... objects) {
        int max = Integer.MIN_VALUE;

        for (Integer num : objects) {
            max = Math.max(max, num);
        }
        return max;
    }
}


