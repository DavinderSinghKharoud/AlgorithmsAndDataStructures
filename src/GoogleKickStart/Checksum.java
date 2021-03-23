package GoogleKickStart;

import java.io.*;
import java.util.*;

/**
 * Grace and Edsger are constructing a N×N
 * N
 * ×
 * N
 *  boolean matrix A
 * A
 * . The element in i
 * i
 * -th row and j
 * j
 * -th column is represented by Ai,j
 * A
 * i
 * ,
 * j
 * . They decide to note down the checksum (defined as bitwise XOR of given list of elements) along each row and column. Checksum of i
 * i
 * -th row is represented as Ri
 * R
 * i
 * . Checksum of j
 * j
 * -th column is represented as Cj
 * C
 * j
 * .
 * For example, if N=2
 * N
 * =
 * 2
 * , A=[1101]
 * A
 * =
 * [
 * 1	0	1	1
 * ]
 * , then R=[10]
 * R
 * =
 * [
 * 1	0
 * ]
 *  and C=[01]
 * C
 * =
 * [
 * 0	1
 * ]
 * .
 * Once they finished the matrix, Edsger stores the matrix in his computer. However, due to a virus, some of the elements in matrix A
 * A
 *  are replaced with −1
 * −
 * 1
 *  in Edsger's computer. Luckily, Edsger still remembers the checksum values. He would like to restore the matrix, and reaches out to Grace for help. After some investigation, it will take Bi,j
 * B
 * i
 * ,
 * j
 *  hours for Grace to recover the original value of Ai,j
 * A
 * i
 * ,
 * j
 *  from the disk. Given the final matrix A
 * A
 * , cost matrix B
 * B
 * , and checksums along each row (R
 * R
 * ) and column (C
 * C
 * ), can you help Grace decide on the minimum total number of hours needed in order to restore the original matrix A
 * A
 * ?
 * Input
 * The first line of the input gives the number of test cases, T
 * T
 * . T
 * T
 *  test cases follow.
 * The first line of each test case contains a single integer N
 * N
 * .
 * The next N
 * N
 *  lines each contain N
 * N
 *  integers representing the matrix A
 * A
 * . j
 * j
 * -th element on the i
 * i
 * -th line represents Ai,j
 * A
 * i
 * ,
 * j
 * .
 * The next N
 * N
 *  lines each contain N
 * N
 *  integers representing the matrix B
 * B
 * . j
 * j
 * -th element on the i
 * i
 * -th line represents Bi,j
 * B
 * i
 * ,
 * j
 * .
 * The next line contains N
 * N
 *  integers representing the checksum of the rows. i
 * i
 * -th element represents Ri
 * R
 * i
 * .
 * The next line contains N
 * N
 *  integers representing the checksum of the columns. j
 * j
 * -th element represents Cj
 * C
 * j
 * .
 * Output
 * For each test case, output one line containing Case #x
 * x
 * : y
 * y
 * , where x
 * x
 *  is the test case number (starting from 1) and y
 * y
 *  is the minimum number of hours to restore matrix A
 * A
 * .
 * Limits
 * Memory limit: 1 GB.
 * 1≤T≤100
 * 1
 * ≤
 * T
 * ≤
 * 100
 * .
 * −1≤Ai,j≤1
 * −
 * 1
 * ≤
 * A
 * i
 * ,
 * j
 * ≤
 * 1
 * , for all i,j
 * i
 * ,
 * j
 * .
 * 1≤Bi,j≤1000
 * 1
 * ≤
 * B
 * i
 * ,
 * j
 * ≤
 * 1000
 * , for i,j
 * i
 * ,
 * j
 *  where Ai,j=−1
 * A
 * i
 * ,
 * j
 * =
 * −
 * 1
 * , otherwise Bi,j=0
 * B
 * i
 * ,
 * j
 * =
 * 0
 * .
 * 0≤Ri≤1
 * 0
 * ≤
 * R
 * i
 * ≤
 * 1
 * , for all i
 * i
 * .
 * 0≤Cj≤1
 * 0
 * ≤
 * C
 * j
 * ≤
 * 1
 * , for all j
 * j
 * .
 * It is guaranteed that there exist at least one way to replace −1
 * −
 * 1
 *  in A
 * A
 *  with 0
 * 0
 *  or 1
 * 1
 * such that R
 * R
 *  and C
 * C
 *  as satisfied.
 * Test Set 1
 * Time limit: 20 seconds.
 * 1≤N≤4
 * 1
 * ≤
 * N
 * ≤
 * 4
 * .
 * Test Set 2
 * Time limit: 35 seconds.
 * 1≤N≤40
 * 1
 * ≤
 * N
 * ≤
 * 40
 * .
 * Test Set 3
 * Time limit: 35 seconds.
 */
public class Checksum implements Runnable {

    void solve() throws IOException {
        int t = ri();
        for (int tt = 0; tt < t; tt++) {
            print(String.format("Case #%d: ", tt + 1));
            int len = ri();
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    ri();
                }
            }

            List<Edge> lst = new ArrayList<>();
            long total = 0;

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    int cost = ri();
                    if (cost != 0) {
                        total += cost;
                        // Create a edge
                        lst.add(new Edge(i, len + j, cost));
                    }
                }
            }

            for (int i = 0; i < 2 * len; i++) {
                ri();
            }

            // Find the max spanning tree
            lst.sort((o1, o2) -> Integer.compare(o2.weight, o1.weight));
            int[] parent = new int[2 * len];
            int[] count = new int[2 * len];
            Arrays.fill(count, 1);
            Arrays.fill(parent, -1);
            long cost = 0;

            for (Edge edge : lst) {
                int a = edge.a, b = edge.b;
                if (find(a, parent) == find(b, parent))
                    continue;

                unite(a, b, parent, count);
                cost += edge.weight;
            }

            println(total - cost);
        }
    }

    void unite(int a, int b, int[] parent, int[] count) {
        a = find(a, parent);
        b = find(b, parent);
        if (count[a] < count[b]) {
            a = a ^ b ^ (b = a);
        }
        parent[b] = a;
        count[a] += count[b];
        count[b] = 0;
    }

    int find(int a, int[] parent) {
        if (parent[a] == -1)
            return a;
        parent[a] = find(parent[a], parent);
        return parent[a];
    }

    static class Edge {
        int a, b, weight;

        public Edge(int a1, int b1, int w) {
            a = a1;
            b = b1;
            weight = w;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new Checksum(), "1").start();
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

    static final class Comparators {
        public static final Comparator<int[]> pairIntArr = (x, y) -> x[0] == y[0] ? compare(x[1], y[1])
                : compare(x[0], y[0]);

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

    static int ri() throws IOException {
        return read.intNext();
    }

    static long rl() throws IOException {
        return Long.parseLong(read.read());
    }

    static String rs() throws IOException {
        return read.read();
    }

    static double rd() throws IOException {
        return read.doubleNext();
    }
}
