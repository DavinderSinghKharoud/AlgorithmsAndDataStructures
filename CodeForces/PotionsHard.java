
import java.io.*;
import java.util.*;

/**
 * This is the hard version of the problem. The only difference is that in this version 𝑛≤200000
 * n
 * ≤
 * 200000
 * . You can make hacks only if both versions of the problem are solved.
 *
 * There are 𝑛
 * n
 *  potions in a line, with potion 1
 * 1
 *  on the far left and potion 𝑛
 * n
 *  on the far right. Each potion will increase your health by 𝑎𝑖
 * a
 * i
 *  when drunk. 𝑎𝑖
 * a
 * i
 *  can be negative, meaning that potion will decrease will health.
 *
 * You start with 0
 * 0
 *  health and you will walk from left to right, from first potion to the last one. At each potion, you may choose to drink it or ignore it. You must ensure that your health is always non-negative.
 *
 * What is the largest number of potions you can drink?
 *
 * Input
 * The first line contains a single integer 𝑛
 * n
 *  (1≤𝑛≤200000
 * 1
 * ≤
 * n
 * ≤
 * 200000
 * ) — the number of potions.
 *
 * The next line contains 𝑛
 * n
 *  integers 𝑎1
 * a
 * 1
 * , 𝑎2
 * a
 * 2
 * , ... ,𝑎𝑛
 * a
 * n
 *  (−109≤𝑎𝑖≤109
 * −
 * 10
 * 9
 * ≤
 * a
 * i
 * ≤
 * 10
 * 9
 * ) which represent the change in health after drinking that potion.
 *
 * Output
 * Output a single integer, the maximum number of potions you can drink without your health becoming negative.
 */
public class PotionsHard implements Runnable {

    void solve() {
        int n = ri();
        int[] arr = iArr(n);
        for (int i = 0; i < n; i++)
            arr[i] = ri();
        long sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0;
        if (arr[0] >= 0) {
            sum += arr[0];
            res = 1;
        }

        for (int i = 1; i < n; i++) {
            int curr = arr[i];
            if (curr >= 0) {
                res++;
                sum += curr;
            } else {
                // If it is negative
                if (sum + curr >= 0) {
                    pq.add(curr);
                    res++;
                    sum += curr;
                } else if (pq.isEmpty()) {
                    if (sum + curr >= 0) {
                        res++;
                        sum += curr;
                        pq.add(curr);
                    }
                } else {
                    int peek = pq.peek();
                    if (peek < curr) {
                        int poll = pq.poll();
                        sum -= poll;
                        sum += curr;
                        pq.add(curr);
                    }
                }
            }
        }
        println(res);
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new PotionsHard(), "1", 1 << 31).start();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;
    static int[] dprimes = new int[] { 1, 11, 101, 1087, 99991, 100001, 1000003, 15485863, 999999937 };

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
