package CodeForces;

import java.io.*;
import java.util.*;

public class LostTree implements Runnable {

    void solve() {
        int n = ri();
        Set<Node> set = new HashSet<Node>();
        int[] first = iArr(n);
        ask(1);
        int odds = 0, even = 0;
        for (int i = 0; i < n; i++) {
            first[i] = ri();
            if (first[i] == 1)
                set.add(new Node(1, i + 1));
            if (first[i] % 2 == 0) {
                even++;
            } else
                odds++;
        }

        List<Integer> queries = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (first[i] != 0) {
                if (even > odds) {
                    if (first[i] % 2 != 0)
                        queries.add(i + 1);
                } else if (first[i] % 2 == 0)
                    queries.add(i + 1);
            }
        }

        for (int query : queries) {
            ask(query);
            int[] curr = iArr(n);
            for (int i = 0; i < n; i++) {
                curr[i] = ri();
                if (curr[i] == 1)
                    set.add(new Node(query, i + 1));
            }
        }

        for (Node curr : set) {
            pans(curr.a, curr.b);
        }
    }

    void ask(int a) {
        System.out.println("?" + a);
    }

    void pans(int a, int b) {
        System.out.println(a + " " + b);
    }

    static class Node {
        int a, b;

        public Node(int a, int b) {
            if (a > b)
                a = a ^ b ^ (b = a);
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return a == node.a && b == node.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new LostTree(), "1", 1 << 31).start();
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
