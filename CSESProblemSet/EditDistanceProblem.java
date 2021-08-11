import java.io.*;
import java.util.*;

/**
 * The edit distance between two strings is the minimum number of operations required to transform one string into the other.
 *
 * The allowed operations are:
 * Add one character to the string.
 * Remove one character from the string.
 * Replace one character in the string.
 * For example, the edit distance between LOVE and MOVIE is 2, because you can first replace L with M, and then add I.
 *
 * Your task is to calculate the edit distance between two strings.
 *
 * Input
 *
 * The first input line has a string that contains n
 * n
 *  characters between A–Z.
 *
 * The second input line has a string that contains m
 * m
 *  characters between A–Z.
 *
 * Output
 *
 * Print one integer: the edit distance between the strings.
 *
 * Constraints
 * 1≤n,m≤5000
 * 1
 * ≤
 * n
 * ,
 * m
 * ≤
 * 5000
 *
 * Example
 *
 * Input:
 * LOVE
 * MOVIE
 *
 * Output:
 * 2
 */
public class EditDistanceProblem {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {

        String first = fastReader.read();
        String second = fastReader.read();

        if (first.length() == 0 && first.length() == second.length()) print(0);
        if (first.length() == 0) print(second.length());
        if (second.length() == 0) print(first.length());

        int[][] dp = new int[first.length() + 1][second.length() + 1];

        for (int row = 0; row <= first.length(); row++) {
            dp[row][0] = row;
        }

        for (int col = 0; col <= second.length(); col++) {
            dp[0][col] = col;
        }

        for (int row = 0; row < first.length(); row++) {
            char c1 = first.charAt(row);
            for (int col = 0; col < second.length(); col++) {
                char c2 = second.charAt(col);
                if (c1 == c2) {
                    dp[row + 1][col + 1] = dp[row][col];
                } else {
                    dp[row + 1][col + 1] = Math.min(dp[row][col + 1], Math.min(dp[row][col], dp[row + 1][col])) + 1;
                }
            }
        }
        print(dp[first.length()][second.length()]);
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
}


