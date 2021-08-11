import java.io.*;
import java.util.*;

/**
 * Given an integer, your task is to find the number, sum and product of its divisors. As an example, let us consider the number 12
 * 12
 * :
 * the number of divisors is 6
 * 6
 *  (they are 1
 * 1
 * , 2
 * 2
 * , 3
 * 3
 * , 4
 * 4
 * , 6
 * 6
 * , 12
 * 12
 * )
 * the sum of divisors is 1+2+3+4+6+12=28
 * 1
 * +
 * 2
 * +
 * 3
 * +
 * 4
 * +
 * 6
 * +
 * 12
 * =
 * 28
 *
 * the product of divisors is 1⋅2⋅3⋅4⋅6⋅12=1728
 * 1
 * ⋅
 * 2
 * ⋅
 * 3
 * ⋅
 * 4
 * ⋅
 * 6
 * ⋅
 * 12
 * =
 * 1728
 *
 * Since the input number may be large, it is given as a prime factorization.
 *
 * Input
 *
 * The first line has an integer n
 * n
 * : the number of parts in the prime factorization.
 *
 * After this, there are n
 * n
 *  lines that describe the factorization. Each line has two numbers x
 * x
 *  and k
 * k
 *  where x
 * x
 *  is a prime and k
 * k
 *  is its power.
 *
 * Output
 *
 * Print three integers modulo 109+7
 * 10
 * 9
 * +
 * 7
 * : the number, sum and product of the divisors.
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
 * 2≤x≤106
 * 2
 * ≤
 * x
 * ≤
 * 10
 * 6
 *
 * each x
 * x
 *  is a distinct prime
 * 1≤k≤109
 * 1
 * ≤
 * k
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 2
 * 2 2
 * 3 1
 *
 * Output:
 * 6 28 1728
 */
@SuppressWarnings("unchecked")
public class DivisorAnalysis implements Runnable {

    void solve() throws IOException {
        int n = read.intNext();

        long product = 1, sum = 1, count = 1, count2 = 1;

        //Number of divisors {summation from 1 to k ( power + 1)
        //Sum of divisors { Gp of a ^ b}
        //Product of divisors { n ^ (total factors count/2) if odd then * sqrt(total factors count)

        while (n-- > 0) {
            long x = read.intNext(), k = read.intNext();

            count = (count * (k + 1)) % mod;

            sum = sum * (expo(x, k + 1) - 1) % mod * expo(x - 1, mod - 2) % mod; //use modular multiplicative inverse
            sum %= mod;

            product = expo(product, k + 1) * expo(expo(x, (k * (k + 1)) / 2), count2) % mod;
            count2 = count2 * (k + 1) % (mod - 1);
        }

        println(count);
        println(sum);
        print(product);

    }


    long expo(long x, long pow) {
        long ans = 1;

        while (pow > 0) {
            if ((pow & 1) == 1) {
                ans = (ans * x) % mod;
            }
            x = (x * x) % mod;
            pow >>= 1;
        }

        return ans;
    }

    private void solve2() throws IOException {
        int n = read.intNext();

        long product = 1, sum = 1, count = 1, count2 = 1;

        //Number of divisors {summation from 1 to k ( power + 1)
        //Sum of divisors { Gp of a ^ b}
        //Product of divisors { n ^ (total factors count/2) if odd then * sqrt(total factors count)
		int[][] arr = new int[n][2];

		for(int i = 0; i < n; i++){
			
			int x = read.intNext(), k = read.intNext();
			arr[i] = new int[]{x, k};
			
			count = (count * ( k + 1) ) % mod;
			
			sum = sum * (expo(x, k + 1) - 1) % mod * (expo(x - 1, mod - 2)) % mod;
			sum %= mod;
			
			count2 = ( count2 * (k + 1)) % (mod - 1);
		}
		
		println(count);
		println(sum);
		
		//Find the product ( a^ (count of divisors)/2 )
		
		//First check if all the powers are even because if they are then we do not need to divide the powers by 2 at the end. we can do that before
		boolean isSquare = true;
		for(int i = 0; i < n; i++ ){
			if( (arr[i][1] & 1 ) == 1) {
				isSquare = false;
				break;
			}
		}
		
		if( isSquare ){
			
			for(int i = 0; i < n; i++ ){
				product = product * (expo(arr[i][0], arr[i][1] >> 1)) % mod;
				product %= mod;
				
			}
			
			print( expo(product, count2) % mod);
		}else{
			//Find the first odd then decrease it by one to cancel out the ( a ^ (b/2) ) the power divide by 2
			
			for(int i = 0; i < n; i++ ){
				product = product * expo(arr[i][0], arr[i][1]) % mod;
			}
			
			count2 = 1;
			int i = 0;
			for(; i < n; i++ ){
				int power = arr[i][1] + 1;
				
				if( (power & 1) == 0 ){
					count2 *= ( power >> 1);
					count2 %= (mod - 1);
					i++; break;
				}
				
				count2 *= power;
				count2 %= (mod - 1);
			}
			
			for(;i < n; i++ ){
				count2 *= (arr[i][1] + 1);
				count2 %= (mod - 1);
			}
			
			print( expo(product, count2) % mod);
		}
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new DivisorAnalysis(), "1").start();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;

    @Override
    public void run() {
        try {
            //solve();
            solve2();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
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


    static int[] iArr(int len) {
        return new int[len];
    }

    static long[] lArr(int len) {
        return new long[len];
    }

    static long min(long a, long b) {
        return Math.min(a, b);
    }

    static int min(int a, int b) {
        return Math.min(a, b);
    }

    static long max(Long a, Long b) {
        return Math.max(a, b);
    }

    static int max(int a, int b) {
        return Math.max(a, b);
    }
}


