import java.io.*;
import java.util.*;

/**
 * Given n
 * n
 * integers, your task is to report for each integer the number of its divisors.
 * <p>
 * For example, if x=18
 * x
 * =
 * 18
 * , the correct answer is 6
 * 6
 * because its divisors are 1,2,3,6,9,18
 * 1
 * ,
 * 2
 * ,
 * 3
 * ,
 * 6
 * ,
 * 9
 * ,
 * 18
 * .
 * <p>
 * Input
 * <p>
 * The first input line has an integer n
 * n
 * : the number of integers.
 * <p>
 * After this, there are n
 * n
 * lines, each containing an integer x
 * x
 * .
 * <p>
 * Output
 * <p>
 * For each integer, print the number of its divisors.
 * <p>
 * Constraints
 * 1≤n≤105
 * 1
 * ≤
 * n
 * ≤
 * 10
 * 5
 * <p>
 * 1≤x≤106
 * 1
 * ≤
 * x
 * ≤
 * 10
 * 6
 * <p>
 * Example
 * <p>
 * Input:
 * 3
 * 16
 * 17
 * 18
 * <p>
 * Output:
 * 5
 * 2
 * 6
 */
@SuppressWarnings("unchecked")
public class CountingDivisors implements Runnable {

    void solve() throws IOException {
        int q = read.intNext();

        while (q-- > 0) {
            int n = read.intNext();

            sbr.append(find(n)).append(' ');
        }
        print(sbr.toString());
    }

    int find(int n) {
        if (n ==  1) return 1;
        int res = 2;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (i * i == n) {
                res++;
            } else if (n % i == 0) {
                res += 2;
            }
        }
        
        return res;
    }


    //Time complexity O(n Log(n))
    private void solve2() throws IOException {
        int q = read.intNext();
        int limit = (int) 1e6 + 1;
        int[] count = new int[limit];
        Arrays.fill(count, 1);
        for (int i = 2; i < limit; i++) {

            for (int j = i; j < limit; j += i) {
                count[j]++;
            }
        }
        while (q-- > 0) {
            int n = read.intNext();

            sbr.append(count[n]).append(' ');
        }
        print(sbr.toString());
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new CountingDivisors(), "1").start();
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


