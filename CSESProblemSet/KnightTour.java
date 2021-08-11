import java.io.*;
import java.util.*;

/**
 * Time limit: 1.00 s Memory limit: 512 MB
 * Given a starting position of a knight on an 8×8
 * 8
 * ×
 * 8
 *  chessboard, your task is to find a sequence of moves such that it visits every square exactly once.
 *
 * On each move, the knight may either move two steps horizontally and one step vertically, or one step horizontally and two steps vertically.
 *
 * Input
 *
 * The only line has two integers x
 * x
 *  and y
 * y
 * : the knight's starting position.
 *
 * Output
 *
 * Print a grid that shows how the knight moves (according to the example). You can print any valid solution.
 *
 * Constraints
 * 1≤x,y≤8
 * 1
 * ≤
 * x
 * ,
 * y
 * ≤
 * 8
 *
 * Example
 *
 * Input:
 * 2 1
 *
 * Output:
 * 8 1 10 13 6 3 20 17
 * 11 14 7 2 19 16 23 4
 * 26 9 12 15 24 5 18 21
 * 49 58 25 28 51 22 33 30
 * 40 27 50 59 32 29 52 35
 * 57 48 41 44 37 34 31 62
 * 42 39 46 55 60 63 36 53
 * 47 56 43 38 45 54 61 64
 */
public class KnightTour {

    static int[][] dp = new int[8][8];
    static int n, m;
    static int[] di = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dj = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};

    static void solve() throws IOException {

        n = fastReader.intNext() - 1;
        m = fastReader.intNext() - 1;

        //dfs( m , n , 1);
        boolean state = dfs2(m, n, 1);
        if(state){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    print(dp[i][j] + " ");
                }
                print("\n");
            }
        }
    }

    private static boolean dfs2(int row, int col, int moves) {
        dp[row][col] = moves;
        if (moves == 64) return true;
        List<int[]> pos = new ArrayList<>(8);

        for (int i = 0; i < 8; i++) {
            int mRow = row + di[i];
            int mCol = col + dj[i];
            if (isValid(mRow, mCol)) {
                pos.add(new int[]{getDegree(mRow, mCol), mRow, mCol});
            }
        }

        pos.sort(Comparator.comparingInt(o -> o[0]));
        for (int[] edge : pos) {
            if (dfs2(edge[1], edge[2], moves + 1)) return true;
        }

        dp[row][col] = 0;
        return false;

    }

    private static int getDegree(int mRow, int mCol) {
        int total = 0;

        for (int i = 0; i < 8; i++) {
            if (isValid(mRow + di[i], mCol + dj[i])) total++;
        }
        return total;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < 8 && col < 8 && dp[row][col] == 0;
    }

    //Time Limit Exceeded
    static void dfs(int row, int col, int move) {

        dp[row][col] = move;
        // System.out.println(move);
        if (move >= 63) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    print(dp[i][j] + " ");
                }
                print('\n');
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            int mRow = row + di[i], mCol = col + dj[i];
            if (mRow >= 0 && mCol >= 0 && mRow < 8 && mCol < 8 && dp[mRow][mCol] == 0) {
                dfs(mRow, mCol, move + 1);
            }
        }

        dp[row][col] = 0;
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


