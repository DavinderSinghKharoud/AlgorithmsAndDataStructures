import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class PairedPayment implements Runnable {

    List<Edge>[] tree;
    long[] dis, dis2;
    long INF = (long) 1e15;

    //Time limit exceeded
    void solve() throws IOException {
        int n = read.intNext(), q = read.intNext();
        dis = new long[n + 1];
        dis2 = new long[n + 1];
        Arrays.fill(dis, INF);
        Arrays.fill(dis2, INF);
        tree = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            tree[i]= new ArrayList<>();
        }
        while (q-- > 0) {
            int a = read.intNext(), b = read.intNext(), w = read.intNext();

            tree[a].add(new Edge(b, w));
            tree[b].add(new Edge(a, w));

        }

        dis[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.cost > dis[curr.node]) continue;

            for (Edge adj1 : tree[curr.node]) {
                if (dis2[adj1.node] > adj1.cost) {
                    for (Edge adj2 : tree[adj1.node]) {

                        if (adj2.node != curr.node) {
                            long w =  (adj1.cost + adj2.cost) * (adj1.cost + adj2.cost) + dis[curr.node];
                            if (dis[adj2.node] > w) {
                                dis[adj2.node] = w;
                                pq.add(new Edge(adj2.node, w));
                            }
                        }
                    }
                }
                dis2[adj1.node] = adj1.cost;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dis[i] == INF) {
                sbr.append(-1);
            } else {
                sbr.append(dis[i]);
            }
            sbr.append(" ");
        }

        print(sbr);
    }

    static class Edge implements Comparable<Edge> {
        int node;
        long cost;

        public Edge(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(cost, o.cost);
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new PairedPayment(), "1").start();
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


