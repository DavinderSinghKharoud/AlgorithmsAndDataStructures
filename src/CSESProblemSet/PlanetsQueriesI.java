import java.io.*;
import java.util.*;

/**
 * You are playing a game consisting of n
 * n
 *  planets. Each planet has a teleporter to another planet (or the planet itself).
 *
 * Your task is to process q
 * q
 *  queries of the form: when you begin on planet x
 * x
 *  and travel through k
 * k
 *  teleporters, which planet will you reach?
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and q
 * q
 * : the number of planets and queries. The planets are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * The second line has n
 * n
 *  integers t1,t2,…,tn
 * t
 * 1
 * ,
 * t
 * 2
 * ,
 * …
 * ,
 * t
 * n
 * : for each planet, the destination of the teleporter. It is possible that ti=i
 * t
 * i
 * =
 * i
 * .
 *
 * Finally, there are q
 * q
 *  lines describing the queries. Each line has two integers x
 * x
 *  and k
 * k
 * : you start on planet x
 * x
 *  and travel through k
 * k
 *  teleporters.
 *
 * Output
 *
 * Print the answer to each query.
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
 * 1≤ti≤n
 * 1
 * ≤
 * t
 * i
 * ≤
 * n
 *
 * 1≤x≤n
 * 1
 * ≤
 * x
 * ≤
 * n
 *
 * 0≤k≤109
 * 0
 * ≤
 * k
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 4 3
 * 2 1 1 4
 * 1 2
 * 3 4
 * 4 1
 *
 * Output:
 * 1
 * 2
 * 4
 */
public class PlanetsQueriesI {

    static void solve() throws IOException {
			
			int n = fastReader.intNext();
			int q = fastReader.intNext();
			
			int lg = 30;
			int[][] dp = new int[n + 1][lg];
			
			for(int i = 1; i <= n; i++ ){
				int k = fastReader.intNext();
				
				dp[i][0] = k;
			}
			
			for(int i = 1; i < lg; i++ ){
				for(int j = 1; j<=n; j++ ){
					dp[j][i] = dp[ dp[j][i - 1]][i - 1];
				}
			}
			
			while( q-- > 0 ){
				int a = fastReader.intNext();
				int b = fastReader.intNext();
				
				for(int i = 0; i < lg; i++ ){
					if( ((1 << i) & b) != 0 ){
						a = dp[a][i];
					}
				}
				
				println(a);
			}
			
			
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
    static int dmax = Integer.MAX_VALUE;static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;static long lmin = Long.MIN_VALUE;

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


