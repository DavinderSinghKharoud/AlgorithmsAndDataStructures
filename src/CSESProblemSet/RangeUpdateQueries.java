import java.io.*;
import java.util.*;

/**
 * Given an array of n
 * n
 *  integers, your task is to process q
 * q
 *  queries of the following types:
 * increase each value in range [a,b]
 * [
 * a
 * ,
 * b
 * ]
 *  by u
 * u
 *
 * what is the value at position k
 * k
 * ?
 * Input
 *
 * The first input line has two integers n
 * n
 *  and q
 * q
 * : the number of values and queries.
 *
 * The second line has n
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
 * : the array values.
 *
 * Finally, there are q
 * q
 *  lines describing the queries. Each line has three integers: either "1
 * 1
 * a
 * a
 * b
 * b
 *  u
 * u
 * " or "2
 * 2
 *  k
 * k
 * ".
 *
 * Output
 *
 * Print the result of each query of type 2.
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
 * 1≤xi,u≤109
 * 1
 * ≤
 * x
 * i
 * ,
 * u
 * ≤
 * 10
 * 9
 *
 * 1≤k≤n
 * 1
 * ≤
 * k
 * ≤
 * n
 *
 * 1≤a≤b≤n
 * 1
 * ≤
 * a
 * ≤
 * b
 * ≤
 * n
 *
 * Example
 *
 * Input:
 * 8 3
 * 3 2 4 5 1 1 5 3
 * 2 4
 * 1 2 5 1
 * 2 4
 *
 * Output:
 * 5
 * 6
 */
//Segment Tree with Lazy Propagation
public class RangeUpdateQueries {

	static long[] dp, lazy;
	
    static void solve() throws IOException {
		
		int n = read.intNext(), q = read.intNext();
		
		int len = nextPow(n);
		
		dp = new long[ 2 * len + 1];
		lazy = new long[2 * len + 1];
		
		int[] arr = new int[n];
		
		for(int i = 0;i < n; i++ ){
			arr[i] = read.intNext();
		}
		
		constructTree(arr, 0, n - 1, 0);
		
		
		while( q--> 0 ){
			int t = read.intNext();
			
			if( t == 2){
				find( 0, n - 1, 0, read.intNext() - 1);
			}else{
				
				update(0, n - 1, 0, read.intNext() - 1, read.intNext() - 1, read.intNext() );
			}
		}
    }
    
    static void find( int l, int h, int pos, int target ){
		
		if( l > h ) return;
		
		if( lazy[pos] != 0 ){
			dp[pos] += lazy[pos];
			if( l != h ){
				lazy[2 * pos + 1] += lazy[pos];
				lazy[2 * pos + 2] += lazy[pos];
			}
			lazy[pos] = 0;
		}
		
		if( target < l || target > h ) return;
		
		if( l == h ) {
			println(dp[pos]);
			return;
		}
		
		int mid = l + ( h - l)/2;
		
		find(l, mid, 2 * pos + 1, target);
		find(mid + 1, h, 2 * pos + 2, target);
		
		dp[pos] = dp[2 * pos + 1] + dp[2 * pos + 2];
		
	}
	
	static int nextPow(int n) {
        if (n == 0) return 1;

        if (n > 0 && (n & (n - 1)) == 0) return n;

        while ((n & (n - 1)) > 0) {
            n = (n & (n - 1));
        }

        return n << 1; //next power of two
    }
    
    static void update( int l , int h, int pos, int ql, int qh, int value){
		
		//Check the propagation
		if( lazy[pos] != 0 ){
			dp[pos] += lazy[pos];
			if( l != h ){// If it is not a leaf node
				lazy[2 * pos + 1] += lazy[pos];
				lazy[2 * pos + 2] += lazy[pos];
				
			}
			lazy[pos] = 0; 
		}
		
		//No overlapp
		if( ql > h || qh < l ) return;
		
		//Total overlapp
		if( l >= ql && h <= qh ){
		    dp[pos] += (long) (h - l + 1) * value;
			if( l != h){
				//not a leaf node
				lazy[2 * pos + 1] += value;
				lazy[2 * pos + 2] += value;
			}
			return;
		}
		
		int mid = l + ( h - l)/2;
		
		update(l, mid, 2 * pos + 1, ql, qh, value);
		update(mid + 1, h, 2 * pos + 2, ql , qh , value);
		
		dp[pos] = dp[2 * pos + 1] + dp[2 * pos + 2];
	}
    
    static void constructTree(int[] arr, int l, int h, int pos ){
		
		if( l == h ){
			dp[pos] = arr[l];
			return;
		}
		
		int mid = l + (h - l)/2;
		
		constructTree(arr, l, mid, 2 * pos + 1);
		constructTree(arr, mid + 1, h, 2 * pos + 2);
		
		dp[pos] = dp[2 * pos + 1] + dp[2 * pos + 2];
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


