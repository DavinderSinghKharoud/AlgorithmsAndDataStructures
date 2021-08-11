import java.io.*;
import java.util.*;

/**
 * You are given an n×n
 * n
 * ×
 * n
 *  grid representing the map of a forest. Each square is either empty or has a tree. Your task is to process q
 * q
 *  queries of the following types:
 * Change the state (empty/tree) of a square.
 * How many trees are inside a rectangle in the forest?
 * Input
 *
 * The first input line has two integers n
 * n
 *  and q
 * q
 * : the size of the forest and the number of queries.
 *
 * Then, there are n
 * n
 *  lines describing the forest. Each line has n
 * n
 *  characters: . is an empty square and * is a tree.
 *
 * Finally, there are q
 * q
 *  lines describing the queries. The format of each line is either "1
 * 1
 * y
 * y
 *  x
 * x
 * " or "2 y1
 * y
 * 1
 * x1
 * x
 * 1
 * y2
 * y
 * 2
 *  x2
 * x
 * 2
 * ".
 *
 * Output
 *
 * Print the answer to each query of the second type.
 *
 * Constraints
 * 1≤n≤1000
 * 1
 * ≤
 * n
 * ≤
 * 1000
 *
 * 1≤q≤2⋅105
 * 1
 * ≤
 * q
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * 1≤y,x≤n
 * 1
 * ≤
 * y
 * ,
 * x
 * ≤
 * n
 *
 * 1≤y1≤y2≤n
 * 1
 * ≤
 * y
 * 1
 * ≤
 * y
 * 2
 * ≤
 * n
 *
 * 1≤x1≤x2≤n
 * 1
 * ≤
 * x
 * 1
 * ≤
 * x
 * 2
 * ≤
 * n
 *
 * Example
 *
 * Input:
 * 4 3
 * .*..
 * *.**
 * **..
 * ****
 * 2 2 2 3 4
 * 1 3 3
 * 2 2 2 3 4
 */
public class ForestQueriesII {

    static int[][] bit;
    static char[][] arr;
    static int n;

    static void solve() throws IOException {

        n = read.intNext();
        int q = read.intNext();
        bit = new int[n + 1][n + 1];
        arr = new char[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            String curr = read.read();

            for (int j = 0; j < curr.length(); j++) {
                char c = curr.charAt(j);
                if (c == '*') {
                    update(i + 1, j + 1, 1);
                    arr[i][j] = '*';
                } else {
                    arr[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < q; i++) {
            int type = read.intNext();
            if (type == 1) {
                int x = read.intNext(), y = read.intNext();
                update(x, y, (arr[x - 1][y - 1] == '*') ? -1 : 1);
                if (arr[x - 1][y - 1] == '*') {
                    arr[x - 1][y - 1] = '.';
                } else {
                    arr[x - 1][y - 1] = '*';
                }
            } else {
                int y1 = read.intNext(), x1 = read.intNext(), y2 = read.intNext(), x2 = read.intNext();
                
                println( query(y2, x2) - query(y1 - 1, x2) - query( y2, x1 - 1) + query(y1 - 1, x1 - 1));
               }
        }

    }

    static int query(int x, int y) {
        int res = 0;

        for (int i = x; i > 0; i -= (i & -i)) {
            for (int j = y; j > 0; j -= (j & -j)) {
                res += bit[i][j];
            }
        }

        return res;
    }

    static void update(int x, int y, int value) {

        for (int i = x; i <= n; i += (i & -i)) {

            for (int j = y; j <= n; j += (j & -j)) {
                bit[i][j] += value;
            }
        }

    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
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


