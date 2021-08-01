package CodeForces;


import java.io.*;
import java.util.*;

public class MaximizeTheIntersections implements Runnable {

    void solve() {
        int t = ri();
        while (t-- > 0) {
            int n = 2 * ri(), k = ri();
            boolean[] vis = new boolean[2 * n + 1];
            List<Chord> lst = new ArrayList<Chord>();
            for (int i = 0; i < k; i++) {
                int a = ri(), b = ri();
                vis[a] = true;
                vis[b] = true;
                lst.add(new Chord(a, b));
            }
            // Connect all the chords optimally
            List<Integer> unused = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (!vis[i])
                    unused.add(i);
            }

            for (int i = 0; i < unused.size() / 2; i++) {
                lst.add(new Chord(unused.get(i), unused.get(i + unused.size() / 2)));
            }

            int ans = 0;
            vis = new boolean[lst.size()];
            for (int i = 0; i < lst.size(); i++) {
                if (!vis[i]) {
                    vis[i] = true;
                    // Find all the intersected chords

                    for (int j = 0; j < lst.size(); j++) {
                        if (!vis[j]) {
                            // Check if it intersects
                            if (intersect(lst.get(i).a, lst.get(i).b, lst.get(j).a, lst.get(j).b)) {
                                ans++;
                            }
                        }
                    }
                }
            }
            println(ans);
        }
    }

    boolean intersect(int a, int b, int c, int d) {
        if (a > b) a = a ^ b ^ (b = a);
        if (c > d) c = c ^ d ^ (d = c);
        if (c < a || c > b) {
            return d >= a && d <= b;
        } else {
            return d <= a || d >= b;
        }
    }

    static class Chord {
        int a, b;

        public Chord(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new MaximizeTheIntersections(), "1").start();
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
