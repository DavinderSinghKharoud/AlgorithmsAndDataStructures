package CodeForces;

import java.io.*;
import java.util.*;

public class
JuryMeeting implements Runnable {
    int lim = (2 * 100_000) + 1;
    int[] fac = new int[lim];

    void solve() {
        int t = ri();
        mod = 998244353;
        factorial();
        outer:
        while (t-- > 0) {
            int n = ri();
            int[] arr = iArr(n);
            for (int i = 0; i < n; i++) {
                arr[i] = ri();
            }
            int max = Arrays.stream(arr).max().getAsInt();
            boolean secondMax = false;
            int k = 0, maxCount = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] == max - 1) {
                    secondMax = true;
                    k++;
                } else if (arr[i] == max)
                    maxCount++;
            }
            if (maxCount > 1) {
                println(fac[n]);
                continue;
            }
            if (n == 1 && arr[0] == 1) {
                println(1);
                continue;
            }
            if (!secondMax) {
                println(0);
                continue;
            }
            int ans = 1, sub = 1;
            for (int i = 1; i <= n; i++) {
                ans = mul(ans, i);
                if (i != k + 1) sub = mul(sub, i);
            }
            ans -= sub;
            ans += mod;
            ans %= mod;

            println(ans);

        }
    }

    void factorial() {
        fac[0] = fac[1] = 1;
        for (int i = 2; i < lim; i++) {
            fac[i] = (int) (((long) i * fac[i - 1]) % mod);
        }
    }

    int mul(int a, int b) {
        return (int) (1L * a * b % mod);
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new JuryMeeting(), "1").start();
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

        public String rline() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = scan();
            while (n != '\n') {
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

    static String rline() {
        try {
            return read.rline();
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
