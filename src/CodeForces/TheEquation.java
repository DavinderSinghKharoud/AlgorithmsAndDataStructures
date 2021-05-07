package CodeForces;

import java.io.*;
import java.util.*;

public class TheEquation implements Runnable {

    void solve() throws IOException {
        long a = rl(), b = rl(), c = rl(), minx = rl(), maxx = rl(), miny = rl(), maxy = rl();
        println((findAllSol(a, b, -1 * c, minx, maxx, miny, maxy)));
    }

    static GCDS eucleGcds(long a, long b) {
        if (b == 0) {
            return new GCDS(1, 0, a);
        }
        GCDS curr = eucleGcds(b, a % b);
        long x = curr.y;
        long y = curr.x - curr.y * (a / b);
        curr.x = x;
        curr.y = y;
        return curr;
    }

    /*******************************************/
    static GCDS findAnySolution(long a, long b, long c) {
        GCDS curr = eucleGcds(Math.abs(a), Math.abs(b));
        if (c % curr.gcd != 0)
            return null;
        curr.x *= (c / curr.gcd);
        curr.y *= (c / curr.gcd);
        if (a < 0)
            curr.x = -curr.x;
        if (b < 0)
            curr.y = -curr.y;
        return curr;
    }

    /**************************************************/
    static void shiftSol(GCDS obj, long a, long b, long count) {
        obj.x += count * b;
        obj.y -= count * a;
    }

    static long findAllSol(long a, long b, long c, long minx, long maxx, long miny, long maxy) {
        if (a == 0 && b == 0) {
            if (c == 0) return (maxx - minx + 1) * (maxy - miny + 1);
            else return 0;
        }
        GCDS curr = findAnySolution(a, b, c);
        if (curr == null)
            return 0;
        a /= curr.gcd;
        b /= curr.gcd;

        int sign_a = (a > 0) ? 1 : -1;
        int sign_b = (b > 0) ? 1 : -1;

        shiftSol(curr, a, b, (minx - curr.x) / b);
        if (curr.x < minx) {
            shiftSol(curr, a, b, sign_b);
        }
        if (curr.x > maxx)
            return 0;
        long lxl = curr.x;

        shiftSol(curr, a, b, (maxx - curr.x) / b);
        if (curr.x > maxx) {
            shiftSol(curr, a, b, -sign_b);
        }
        long rxl = curr.x;

        shiftSol(curr, a, b, -(miny - curr.y) / a);
        if (curr.y < miny) {
            shiftSol(curr, a, b, -sign_a);
        }
        if (curr.y > maxy)
            return 0;
        long lx2 = curr.x;

        shiftSol(curr, a, b, -(maxy - curr.y) / a);
        if (curr.y > maxy)
            shiftSol(curr, a, b, sign_a);
        long rx2 = curr.x;

        if (lx2 > rx2)
            lx2 = lx2 ^ rx2 ^ (rx2 = lx2);
        long lx = Math.max(lxl, lx2);
        long rx = Math.min(rxl, rx2);
        if (lx > rx)
            return 0;

        return (rx - lx) / Math.abs(b) + 1;
    }

    static class GCDS {
        long x, y, gcd;

        public GCDS(long x, long y, long gcd) {
            this.y = y;
            this.x = x;
            this.gcd = gcd;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new TheEquation(), "1").start();
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

    static void shuffle(int[] aa) {
        int n = aa.length;
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
    }

    static void shuffle(int[][] aa) {
        int n = aa.length;
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

    static int ri() throws IOException {
        return read.intNext();
    }

    static long rl() throws IOException {
        return Long.parseLong(read.read());
    }

    static String rs() throws IOException {
        return read.read();
    }

    static char rc() throws IOException {
        return rs().charAt(0);
    }

    static double rd() throws IOException {
        return read.doubleNext();
    }
}
