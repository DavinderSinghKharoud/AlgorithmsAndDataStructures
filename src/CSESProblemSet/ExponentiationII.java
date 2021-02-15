import java.io.*;
import java.util.*;

/**
 * Your task is to efficiently calculate values abc
 * a
 * b
 * c
 *  modulo 109+7
 * 10
 * 9
 * +
 * 7
 * .
 *
 * Input
 *
 * The first input line has an integer n
 * n
 * : the number of calculations.
 *
 * Afther this, there are n
 * n
 *  lines, each containing three integers a
 * a
 * , b
 * b
 *  and c
 * c
 * .
 *
 * Output
 *
 * Print each value abc
 * a
 * b
 * c
 *  modulo 109+7
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
 * 0≤a,b,c≤109
 * 0
 * ≤
 * a
 * ,
 * b
 * ,
 * c
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 3
 * 3 7 1
 * 15 2 2
 * 3 4 5
 *
 * Output:
 * 2187
 * 50625
 * 763327764
 */
@SuppressWarnings("unchecked")
public class ExponentiationII implements Runnable {

    void solve() throws IOException {
		
		int q = read.intNext();
		
		while(q--> 0){
            sbr.append( find2(read.intNext(), (int) find3(read.intNext(), read.intNext()))).append(' ');
		}
		
		print(sbr.toString());
    }


    long find2(int a, int b) {

        if (b == 0) return 1;
        if (b == 1) return a;

        long res = find2(a, b >> 1);
        res = (res * res) % mod;
        if ((b & 1) == 1) {
            //Odd
            res = (res * a) % mod;
        }
        return res;
    }

    long find3(int a, int b) {

        if (b == 0) return 1;
        if (b == 1) return a;

        long res = find3(a, b >> 1);
        res = (res * res)    % (mod - 1);
        if ((b & 1) == 1) {
            //Odd
            res = (res * a) % (mod - 1);
        }
        return res;
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new ExponentiationII(), "1").start();
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
            solve();
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


