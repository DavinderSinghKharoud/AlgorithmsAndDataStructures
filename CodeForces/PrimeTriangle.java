package CodeForces;

import java.io.*;
import java.util.*;

public class PrimeTriangle implements Runnable {

    int limit = (int) 1e8;

    void solve() {
        int[] primes = getPrimes(limit);
        int t = ri();
        while (t-- > 0) {
            int n = ri();
            int index = Arrays.binarySearch(primes, n);
            if (index < 0) {
                println(-1);
            } else {

                int i = search(1, primes.length, index + 1);
                int x = i + 1;
                long y = ((long) i * (i + 1)) / 2;
                y = index - y + 1;
                println(x + " " + y);
            }
        }
    }

    int search(int l, int r, int val) {
        while (l < r) {
            int mid = (r + l + 1) >> 1;
            long count = (1L * mid * (mid + 1)) / 2;
            if (count <= val) {
                l = mid;
            } else
                r = mid - 1;
        }
        if( (1L * l * (l + 1))/2 == val){
            return l - 1;
        }
        return l;
    }

    int[] getPrimes(int n) {
        int size = 10000;

        List<Integer> lst = new ArrayList<>();
        int sqrt = (int) Math.sqrt(n);
        boolean[] isPrime = new boolean[sqrt + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= sqrt; i++) {
            if (isPrime[i]) {
                lst.add(i);
                for (int j = i * i; j <= sqrt; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int[] primes = iArr(lst.size());
        for (int i = 0; i < lst.size(); i++) {
            primes[i] = lst.get(i);
        }

        boolean[] block = new boolean[size];
        int countRes = 0;
        for (int k = 0; k * size <= n; k++) {
            Arrays.fill(block, true);
            int start = k * size;
            for (int p : primes) {
                int startIndex = (start + p - 1) / p;
                int j = max(startIndex, p) * p - start;
                for (; j < size; j += p) {
                    block[j] = false;
                }
            }
            if (k == 0) {
                block[0] = block[1] = false;
            }
            for (int i = 0; i < size && start + i <= n; i++) {
                if (block[i])
                    countRes++;
            }
        }

        int[] res = new int[countRes];
        int index = 0;
        for (int k = 0; k * size <= n; k++) {
            Arrays.fill(block, true);
            int start = k * size;
            for (int p : primes) {
                int startIndex = (start + p - 1) / p;
                int j = max(startIndex, p) * p - start;
                for (; j < size; j += p) {
                    block[j] = false;
                }
            }
            if (k == 0) {
                block[0] = block[1] = false;
            }
            for (int i = 0; i < size && start + i <= n; i++) {
                if (block[i])
                    res[index++] = start + i;
            }
        }
        return res;
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new PrimeTriangle(), "1").start();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;
    static int[] dprimes = new int[]{1, 11, 101, 1087, 99991, 100001, 1000003, 15485863, 999999937};

    @Override
    public void run() {
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
                } else
                    throw new InputMismatchException();
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
                } else
                    throw new InputMismatchException();
            }
            if (n == '.') {
                n = scan();
                double temp = 1;
                while (!isWhiteSpace(n)) {
                    if (n >= '0' && n <= '9') {
                        temp /= 10;
                        doub += (n - '0') * temp;
                        n = scan();
                    } else
                        throw new InputMismatchException();
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

    // Gives strict lowerBound that previous number would be smaller than the target
    int lowerBound(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] >= val) {
                r = mid;
            } else
                l = mid + 1;
        }
        return l;
    }

    // Gives strict upperBound that next number would be greater than the target
    int upperBound(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (r + l + 1) >> 1;
            if (arr[mid] <= val) {
                l = mid;
            } else
                r = mid - 1;
        }
        return l;
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

    static int ri() {
        try {
            return read.intNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(2);
        return -1;
    }

    static long rl() {
        try {
            return Long.parseLong(read.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(2);
        return -1;
    }

    static String rs() {
        try {
            return read.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(2);
        return "";
    }

    static char rc() {
        return rs().charAt(0);
    }

    static double rd() {
        try {
            return read.doubleNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(2);
        return -1;
    }
}
