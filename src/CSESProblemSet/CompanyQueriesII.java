import java.io.*;
import java.util.*;

/**
 * A company has n
 * n
 *  employees, who form a tree hierarchy where each employee has a boss, except for the general director.
 *
 * Your task is to process q
 * q
 *  queries of the form: who is the lowest common boss of employees a
 * a
 *  and b
 * b
 *  in the hierarchy?
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and q
 * q
 * : the number of employees and queries. The employees are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * , and employee 1
 * 1
 *  is the general director.
 *
 * The next line has n−1
 * n
 * −
 * 1
 *  integers e2,e3,…,en
 * e
 * 2
 * ,
 * e
 * 3
 * ,
 * …
 * ,
 * e
 * n
 * : for each employee 2,3,…,n
 * 2
 * ,
 * 3
 * ,
 * …
 * ,
 * n
 *  their boss.
 *
 * Finally, there are q
 * q
 *  lines describing the queries. Each line has two integers a
 * a
 *  and b
 * b
 * : who is the lowest common boss of employees a
 * a
 *  and b
 * b
 * ?
 *
 * Output
 *
 * Print the answer for each query.
 *
 * Constraints
 * 1≤n,q≤2⋅105
 * 1
 * ≤
 * n
 * ,
 * q
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * 1≤ei≤i−1
 * 1
 * ≤
 * e
 * i
 * ≤
 * i
 * −
 * 1
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
 * 5 3
 * 1 1 3 3
 * 4 5
 * 2 5
 * 1 4
 *
 * Output:
 * 3
 * 1
 * 1
 */
@SuppressWarnings("unchecked")
//Time Complexity O(n + qLogn)
public class CompanyQueriesII {

    int mxN = (int) 2e5;
    int[][] dp = new int[mxN][20];
    ArrayDeque<Integer>[] tree;
    int[] depth;

    void solve() throws IOException {
        int n = read.intNext(), q = read.intNext();

        tree = new ArrayDeque[n];
        depth = new int[n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
            tree[i] = new ArrayDeque();
        }

        for (int i = 1; i < n; i++) {
            int p = read.intNext() - 1;
            dp[i][0] = p;
            tree[p].add(i);
        }

        dfs(0, 1);

        for (int i = 1; i < 20; i++) {
            for (int node = 1; node < n; node++) {
                dp[node][i] = (dp[node][i - 1] == -1) ? -1 : dp[dp[node][i - 1]][i - 1];
            }
        }

        while (q-- > 0) {
            int a = read.intNext() - 1, b = read.intNext() - 1;
            if (a == b) {
                sbr.append(a + 1).append(' ');
            } else if (a == 0 || b == 0) {
                sbr.append(1).append(' ');
            } else {    //We can also use binary search to find the exact k value then move both nodes(Time complexity O(Logn)^2

                int depth1 = depth[a], depth2 = depth[b];
                //First we need to check if both are at the same level
                if (depth1 != depth2) {

                    if (depth2 < depth1) {//Swap
                        int temp = a;
                        a = b;
                        b = temp;
                        depth1 = depth[a];
                        depth2 = depth[b];
                    }

                    int diff = depth2 - depth1;
                    b = equalizeLevel(b, diff);
                }

                if (a == b) {//If both becomes equal
                    sbr.append(a + 1).append(' ');
                    continue;
                }

                //Now move up util both reach the last node of Lowest Common Ancestor

                int[] updated = getUpdate(a, b);
                a = updated[0];
                b = updated[1];
                if (a == b) {
                    sbr.append(a + 1).append(' ');
                } else {
                    sbr.append(dp[a][0] + 1).append(' ');
                }

            }
        }

        print(sbr.toString());

    }

    private int[] getUpdate(int a, int b) {
        int parent1 = 0, parent2 = 0;
        for (int i = 19; i >= 0; i--) {
            parent1 = dp[a][i];
            parent2 = dp[b][i];
            if (parent1 == -1 || parent2 == -1) continue;
            if (parent1 != parent2) {
                return getUpdate(parent1, parent2);
            }
        }

        return new int[]{parent1, parent2};
    }

    int equalizeLevel(int a, int k) {

        for (int i = 19; i >= 0; i--) {
            if ((k >> i & 1) != 0) {
                a = dp[a][i];
            }
        }
        return a;
    }

    void dfs(int pos, int dep) {
        depth[pos] = dep;
        for (int child : tree[pos]) {
            dfs(child, dep + 1);
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        CompanyQueriesII object = new CompanyQueriesII();
        object.solve();
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


