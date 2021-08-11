import java.io.*;
import java.util.*;

/**
 * Consider a money system consisting of n
 * n
 *  coins. Each coin has a positive integer value. Your task is to calculate the number of distinct ordered ways you can produce a money sum x
 * x
 *  using the available coins.
 *
 * For example, if the coins are {2,3,5}
 * {
 * 2
 * ,
 * 3
 * ,
 * 5
 * }
 *  and the desired sum is 9
 * 9
 * , there are 3
 * 3
 *  ways:
 * 2+2+5
 * 2
 * +
 * 2
 * +
 * 5
 *
 * 3+3+3
 * 3
 * +
 * 3
 * +
 * 3
 *
 * 2+2+2+3
 * 2
 * +
 * 2
 * +
 * 2
 * +
 * 3
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and x
 * x
 * : the number of coins and the desired sum of money.
 *
 * The second line has n
 * n
 *  distinct integers c1,c2,…,cn
 * c
 * 1
 * ,
 * c
 * 2
 * ,
 * …
 * ,
 * c
 * n
 * : the value of each coin.
 *
 * Output
 *
 * Print one integer: the number of ways modulo 109+7
 * 10
 * 9
 * +
 * 7
 * .
 *
 * Constraints
 * 1≤n≤100
 * 1
 * ≤
 * n
 * ≤
 * 100
 *
 * 1≤x≤106
 * 1
 * ≤
 * x
 * ≤
 * 10
 * 6
 *
 * 1≤ci≤106
 * 1
 * ≤
 * c
 * i
 * ≤
 * 10
 * 6
 *
 * Example
 *
 * Input:
 * 3 9
 * 2 3 5
 *
 * Output:
 * 3
 */
public class CoinCombinationsII {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int)1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {
			
			int count = fastReader.intNext();
			int target = fastReader.intNext();
			
			int[] coins = new int[count];
			
			for(int i = 0; i < count; i++ ){
				coins[i] = fastReader.intNext();
			}
			
			int[] dp = new int[target + 1];
			
            dp[0] = 1;
			for(int coin: coins ){
				for(int num = 1; num <= target; num++ ){
					if( num - coin >= 0  ){
						if( dp[num - coin] > 0 ){
							dp[num] = (dp[num] + dp[num - coin]) % mod;
						}
					}
				}
			}
			
			print(dp[target]);
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
        out.close();
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {

            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;

        }

        public String next() throws IOException {

            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);

        }

        public int intNext() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long longNext() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double doubleNext() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
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
