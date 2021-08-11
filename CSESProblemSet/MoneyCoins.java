import java.io.*;
import java.util.*;

/**
 * You have n
 * n
 * coins with certain values. Your task is to find all money sums you can create using these coins.
 * <p>
 * Input
 * <p>
 * The first input line has an integer n
 * n
 * : the number of coins.
 * <p>
 * The next line has n
 * n
 * integers x1,x2,…,xn
 * x
 * 1
 * ,
 * x
 * 2
 * ,
 * …
 * ,
 * x
 * n
 * : the values of the coins.
 * <p>
 * Output
 * <p>
 * First print an integer k
 * k
 * : the number of distinct money sums. After this, print all possible sums in increasing order.
 * <p>
 * Constraints
 * 1≤n≤100
 * 1
 * ≤
 * n
 * ≤
 * 100
 * <p>
 * 1≤xi≤1000
 * 1
 * ≤
 * x
 * i
 * ≤
 * 1000
 * <p>
 * Example
 * <p>
 * Input:
 * 4
 * 4 2 5 2
 * <p>
 * Output:
 * 9
 * 2 4 5 6 7 8 9 11 13
 */
public class MoneyCoins {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {

        int count = fastReader.intNext();

        int[] coins = new int[count];
        int sum = 0;

        for (int i = 0; i < count; i++) {
            coins[i] = fastReader.intNext();
            sum += coins[i];
        }

        boolean[][] dp = new boolean[count + 1][sum + 1];
        int total = 0;

        dp[0][0] = true;
        for (int coin = 1; coin <= count; coin++) {
            int price = coins[coin - 1];
            dp[coin][0] = true;
            for (int num = 0; num <= sum; num++) {
                dp[coin][num] = dp[coin - 1][num];
                if (num - price >= 0) {
                    dp[coin][num] |= (dp[coin - 1][num - price]);

                }
            }
        }


        for (int i = 1; i <= sum; i++) {
            if (dp[count][i]) {
                total++;
                sbr.append(i).append(" ");
            }
        }
        println(total + " ");
        print((sbr));

    }

    private static void solve2() throws IOException {
        int count = fastReader.intNext();
        int[] coins = new int[count];
        int sum = 0;

        for (int i = 0; i < count; i++) {
            coins[i] = fastReader.intNext();
            sum += coins[i];
        }

        boolean[]dp = new boolean[sum + 1];
        dp[0] = true;
        for (int coin = 0; coin < count; coin++) {
            for (int num = sum; num >= 0; num--) {

                if( num >= coins[coin] ){
                    dp[num] |= dp[num - coins[coin]];
                }
            }
        }

        int total = 0;

        for (int i = 1; i <= sum; i++) {
            if (dp[i]) {
                total++;
                sbr.append(i).append(" ");
            }
        }
        println(total + " ");
        print((sbr));
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

       // solve();
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
}


