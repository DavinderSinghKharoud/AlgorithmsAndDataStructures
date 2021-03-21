package GoogleKickStart;

import java.io.*;
import java.util.*;

public class LShapedPlots implements Runnable {

    void solve() throws IOException {
        int t = ri();
        int[] ans = new int[t];

        int indexAns = 0;
        while (t-- > 0) {

            int r = ri(), c = ri();
            int[][] arr = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    arr[i][j] = ri();
                }
            }

            Node[][] dp = new Node[r][c];

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    dp[i][j] = new Node();
                    if (arr[i][j] == 1) {
                        dp[i][j].top = 1;
                        dp[i][j].bottom = 1;
                        dp[i][j].left = 1;
                        dp[i][j].right = 1;

                        // Check all sides
                        if (isValid(i - 1, j, r, c)) {
                            dp[i][j].top += dp[i - 1][j].top;
                        }

                        if (isValid(i, j - 1, r, c)) {
                            dp[i][j].left += dp[i][j - 1].left;
                        }
                    }
                }
            }

            // Check the right side
            for (int i = r - 1; i >= 0; i--) {
                for (int j = c - 1; j >= 0; j--) {
                    if (arr[i][j] == 1) {
                        // Check all sides
                        if (isValid(i + 1, j, r, c)) {
                            dp[i][j].bottom += dp[i + 1][j].bottom;
                        }

                        if (isValid(i, j + 1, r, c)) {
                            dp[i][j].right += dp[i][j + 1].right;
                        }
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (arr[i][j] == 1) {
                        Node curr = dp[i][j];
                        if (curr.left != 0) {
                            // Check the possibilities with left side
                            if (curr.top != 0) {
                                count += findPossibilities(curr.left, curr.top);
                            }

                            if (curr.bottom != 0) {
                                count += findPossibilities(curr.left, curr.bottom);
                            }
                        }

                        if (curr.right != 0) {

                            if (curr.top != 0) {
                                count += findPossibilities(curr.right, curr.top);
                            }
                            if (curr.bottom != 0) {
                                count += findPossibilities(curr.right, curr.bottom);
                            }
                        }

                    }
                }
            }

            ans[indexAns++] = count;

        }

        for (int i = 0; i < ans.length; i++) {
            println(String.format("Case #%d: %d", i + 1, ans[i]));
        }

    }

    int findPossibilities(int a, int b) {
        int count = 0;
        //Two possibilities
        if (a > b) {
            a = a ^ b ^ (b = a);
        }

        int temp1 = a, temp2 = b;

        // # 1
        if ((b & 1) == 1) {
            b--;
        }

        if (a >= 2 && b >= 4) {
            int half = b / 2;
            half = Math.min(half, a);
            count += half - 2 + 1;
        }

        // # 2
        a = temp1;
        b = temp2;

        if ((a & 1) == 1) {
            a--;
        }
        if (a >= 4 && b >= 2) {
            int half = a / 2;
            half = Math.min(half, b);
            count += half - 2 + 1;
        }
        return count;
    }

    public boolean isValid(int i, int j, int row, int col) {
        return i >= 0 && i < row && j >= 0 && j < col;
    }

    class Node {
        int top = 0, bottom = 0, left = 0, right = 0;
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new LShapedPlots(), "1").start();
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
