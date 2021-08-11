import java.io.*;
import java.util.*;

/**
 * You know that an array has n
 * n
 *  integers between 1
 * 1
 *  and m
 * m
 * , and the difference between two adjacent values is at most 1
 * 1
 * .
 *
 * Given a description of the array where some values may be unknown, your task is to count the number of arrays that match the description.
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the array size and the upper bound for each value.
 *
 * The next line has n
 * n
 *  integers x1,x2,…,xn
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
 * : the contents of the array. Value 0
 * 0
 *  denotes an unknown value.
 *
 * Output
 *
 * Print one integer: the number of arrays modulo 109+7
 * 10
 * 9
 * +
 * 7
 * .
 *
 * Constraints
 * 1≤n≤105
 * 1
 * ≤
 * n
 * ≤
 * 10
 * 5
 *
 * 1≤m≤100
 * 1
 * ≤
 * m
 * ≤
 * 100
 *
 * 0≤xi≤m
 * 0
 * ≤
 * x
 * i
 * ≤
 * m
 *
 * Example
 *
 * Input:
 * 3 5
 * 2 0 2
 *
 * Output:
 * 3
 *
 * Explanation: The arrays [2,1,2]
 * [
 * 2
 * ,
 * 1
 * ,
 * 2
 * ]
 * , [2,2,2]
 * [
 * 2
 * ,
 * 2
 * ,
 * 2
 * ]
 *  and [2,3,2]
 * [
 * 2
 * ,
 * 3
 * ,
 * 2
 * ]
 *  match the description.
 */
public class ArrayDescription {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int)1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {
			
		int len = fastReader.intNext();
		int bound = fastReader.intNext();
		
		int[] arr = new int[len];
		
		for(int i =0; i < len; i++ ){
			arr[i] = fastReader.intNext();
		}
		
		int[][] dp = new int[len][bound + 1];
		
		if( arr[0] == 0 ){ //If starting is zero then try to place all the numbers
			Arrays.fill(dp[0], 1);
		}else{
			dp[0][arr[0]] = 1;
		}
		
		for(int i = 1; i < len; i++ ){
			int curr = arr[i];
			
			if( curr == 0 ){
				//Try to place all the numbers 
				
				for(int place = 1; place <= bound; place++ ){
					for(int k: new int[]{place - 1, place, place + 1}){
						if( k >= 1 && k <= bound ){ //If it is inside the boundaries
							dp[i][place] = ( dp[i][place] + dp[i - 1][k]) % mod;
						}
					}
				}
			}else{
				
				for(int k: new int[]{curr - 1, curr, curr + 1}){
					if( k >= 1 && k <= bound ){
						dp[i][curr] = (dp[i][curr] + dp[i - 1][k]) % mod;
					}
				}
			}
		}
		
		
		
		int res = 0;
		for(int i = 1; i <= bound; i++ ){
			res = (res + dp[len - 1][i]) % mod;
		}
		
		print( res );
		
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


