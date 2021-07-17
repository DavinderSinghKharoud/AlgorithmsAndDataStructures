package GoogleKickStart.RoundE;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class FinalExam implements Runnable {

    TreeSet<Node> set;

    void solve() {
        int t = ri();
        for (int tt = 1; tt <= t; tt++) {
            print("Case #" + tt + ": ");
            int n = ri(), m = ri();
            set = new TreeSet<>(Comparator.comparingLong(o -> o.val));
            for (int i = 0; i < n; i++) {
                long a = rl(), b = rl();
                set.add(new Node(a, b));
                set.add(new Node(b, a));
            }

            for (int i = 0; i < m; i++) {
                long stud = rl();
                Node floor = set.floor(new Node(stud, -1));
                Node ceiling = set.ceiling(new Node(stud, -1));
                if (floor != null && ceiling != null) {
                    // Try both
                    if (floor.isBetw(stud)) {
                        // If it is in between
                        split(floor.val, floor.adj, stud);
                    } else if (ceiling.isBetw(stud)) {
                        split(ceiling.val, ceiling.adj, stud);
                    } else {
                        // It is not in the sequence
                        long diff1 = Math.abs(stud - floor.val), diff2 = Math.abs(stud - ceiling.val);
                        if (diff1 <= diff2) {
                            split(floor.val, floor.adj, stud);
                        } else
                            split(ceiling.val, ceiling.adj, stud);
                    }
                } else if (floor != null) {
                    split(floor.val, floor.adj, stud);
                } else {
                    split(ceiling.val, ceiling.adj, stud);
                }
            }
            println("");
        }
    }

    void split(long a, long b, long stud) {
        if (a > b)
            a = a ^ b ^ (b = a);
        set.remove(new Node(a, -1));
        set.remove(new Node(b, -1));
        // If it is in between
        if (stud >= a && stud <= b) {
            long a1 = stud - 1, b1 = stud + 1;
            print(stud + " ");
            if (a <= a1) {
                set.add(new Node(a, a1));
                set.add(new Node(a1, a));
            }
            if (b1 <= b) {
                set.add(new Node(b1, b));
                set.add(new Node(b, b1));
            }
        } else {
            // Check which one is closest
            long diff1 = Math.abs(stud - a), diff2 = Math.abs(b - stud);
            if (diff1 <= diff2) {
                print(a + " ");
                long a1 = a + 1;
                if (a1 <= b) {
                    set.add(new Node(a1, b));
                    set.add(new Node(b, a1));
                }
            } else  {
                print(b + " ");
                long b1 = b - 1;
                if (a <= b1) {
                    set.add(new Node(a, b1));
                    set.add(new Node(b1, a));
                }
            }
        }
    }

    static class Node {
        long val, adj;

        public Node(long v, long adj) {
            val = v;
            this.adj = adj;
        }

        public boolean isBetw(long num) {
            long a = val, b = adj;
            if (a > b)
                a = a ^ b ^ (b = a);
            return a <= num && num <= b;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new FinalExam(), "1").start();
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
