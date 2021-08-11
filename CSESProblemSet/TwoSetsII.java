import java.io.*;
import java.util.*;

/**
 * Your task is to count the number of ways numbers 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 *  can be divided into two sets of equal sum.
 *
 * For example, if n=7
 * n
 * =
 * 7
 * , there are four solutions:
 * {1,3,4,6}
 * {
 * 1
 * ,
 * 3
 * ,
 * 4
 * ,
 * 6
 * }
 *  and {2,5,7}
 * {
 * 2
 * ,
 * 5
 * ,
 * 7
 * }
 *
 * {1,2,5,6}
 * {
 * 1
 * ,
 * 2
 * ,
 * 5
 * ,
 * 6
 * }
 *  and {3,4,7}
 * {
 * 3
 * ,
 * 4
 * ,
 * 7
 * }
 *
 * {1,2,4,7}
 * {
 * 1
 * ,
 * 2
 * ,
 * 4
 * ,
 * 7
 * }
 *  and {3,5,6}
 * {
 * 3
 * ,
 * 5
 * ,
 * 6
 * }
 *
 * {1,6,7}
 * {
 * 1
 * ,
 * 6
 * ,
 * 7
 * }
 *  and {2,3,4,5}
 * {
 * 2
 * ,
 * 3
 * ,
 * 4
 * ,
 * 5
 * }
 *
 * Input
 *
 * The only input line contains an integer n
 * n
 * .
 *
 * Output
 *
 * Print the answer modulo 109+7
 * 10
 * 9
 * +
 * 7
 * .
 *
 * Constraints
 * 1≤n≤500
 * 1
 * ≤
 * n
 * ≤
 * 500
 *
 * Example
 *
 * Input:
 * 7
 *
 * Output:
 * 4
 */
public class TwoSetsII {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int)1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {
		
		int len = fastReader.intNext();
		
		long sum = ((long) len * ( len + 1))/2;
		if( (sum & 1) == 1 ){
			print(0); return;
		}
		
		sum = sum/2;
		
		int[][] dp = new int[len + 1][(int)sum + 1];
		dp[0][0] = 1;
		for(int num = 1; num <= len; num++ ){
			
			for(int curr = 1; curr <= sum; curr++ ){
				dp[num][curr] = dp[num - 1][curr];
				
				if( curr - num >= 0 ){
					dp[num][curr] = ( dp[num][curr] + dp[num - 1][curr - num] ) % mod;
				}
			}
		}
		
		print( dp[len][(int) sum]);
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


