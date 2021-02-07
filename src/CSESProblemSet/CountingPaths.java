import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class CountingPaths {
    int n, dp[][];
    long[] depths, ans, count;
    ArrayDeque<Integer> tree[];

    void solve() throws IOException {
        n = read.intNext();
        int q = read.intNext();

        tree = new ArrayDeque[n];
        dp = new int[n][20];
        count = new long[n];
        ans = new long[n];
        depths = new long[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayDeque<>();
        }

        for (int i = 1; i < n; i++) {
            int a = read.intNext() - 1, b = read.intNext() - 1;
            tree[a].add(b);
            tree[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] vis = new boolean[n];
        int depth = 2;
        vis[0] = true;
        depths[0] = 1;
        while ( !queue.isEmpty()){
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                int curr = queue.poll();
                boolean isChild =false;
                for(int child: tree[curr]){
                    if( !vis[child]){
                        dp[child][0] = curr;
                        depths[child] = depth;
                        vis[child] = true;
                        queue.add(child);
                        isChild = true;
                    }


                }
                if(isChild) depth++;

            }


        }

        //dfs(0, -1, 1);

		for(int i = 1; i < 20; i++ ){
			for(int node = 1; node < n; node++ ){
				dp[node][i] = dp[dp[node][i - 1]][i - 1];
			}
		}

        while (q-- > 0) {
            int a = read.intNext() - 1, b = read.intNext() - 1;

            int lcm = findLcm(a, b);

            count[a]++;
            count[b]++;
            --count[lcm];
            if (lcm != 0) {
                --count[dp[lcm][0]];
            }

        }

        dfs2(0, -1);

        for (int i = 0; i < n; i++) {
            sbr.append(ans[i]).append(' ');
        }

        print(sbr);

    }

    void dfs2(int pos, int parent) {

        for (int child : tree[pos]) {
            if (child != parent) {
                dfs2(child, pos);
                count[pos] += count[child];
            }
        }

        ans[pos] = count[pos];
    }

    int findLcm(int a, int b) {
        if (depths[a] > depths[b]) {//Swap the nodes
            a = a ^ b ^ (b = a);
        }

        //Equalize depth if needed
        for (int i = 19; i >= 0; i--) {
            if ((depths[b] - (1 << i)) >= depths[a]) {
                b = dp[b][i];
            }
        }

        if (a == b) return a;

        for (int i = 19; i >= 0; i--) {
            if ((dp[a][i] ^ dp[b][i]) != 0) {
                a = dp[a][i];
                b = dp[b][i];
            }
        }

        return dp[a][0];
    }

    void dfs(int pos, int parent, int depth) {
        depths[pos] = depth++;

        for (int child : tree[pos]) {
            if (child != parent) {
                dfs(child, pos, depth);
                dp[child][0] = pos;
            }
        }
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        new CountingPaths().solve();
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


