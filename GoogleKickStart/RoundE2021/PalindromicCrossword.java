package GoogleKickStart.RoundE2021;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class PalindromicCrossword implements Runnable {

    Node[][] boundaries;
    char[][] arr;
    int n, m;

    //Time Complexity O(n)
    void solve() {
        int t = ri();
        for (int tt = 1; tt <= t; tt++) {
            print("Case #" + tt + ": ");
            n = ri();
            m = ri();
            arr = new char[n][m];
            boundaries = new Node[n][m];
            Queue<Coordinate> queue = new ArrayDeque<>();
            int initialCount = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = rs().toCharArray();
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] != '#' && arr[i][j] != '.') {
                        initialCount++;
                        queue.add(new Coordinate(i, j));
                    }
                }
            }

            // Find boundaries
            findBoundary();

            while (!queue.isEmpty()) {
                Coordinate curr = queue.poll();
                // Fill the palindrome
                // vertical
                int start = boundaries[curr.x][curr.y].startv.x;
                int end = boundaries[curr.x][curr.y].endv.x;
                int dis = min(Math.abs(start - curr.x), Math.abs(end - curr.x));
                if (arr[start + dis][curr.y] == '.') {
                    arr[start + dis][curr.y] = arr[curr.x][curr.y];
                    queue.add(new Coordinate(start + dis, curr.y));
                } else if (arr[end - dis][curr.y] == '.') {
                    arr[end - dis][curr.y] = arr[curr.x][curr.y];
                    queue.add(new Coordinate(end - dis, curr.y));
                }

                // horizontal
                start = boundaries[curr.x][curr.y].starth.y;
                end = boundaries[curr.x][curr.y].endh.y;
                dis = min(Math.abs(start - curr.y), Math.abs(end - curr.y));
                if (arr[curr.x][start + dis] == '.') {
                    arr[curr.x][start + dis] = arr[curr.x][curr.y];
                    queue.add(new Coordinate(curr.x, start + dis));
                } else if (arr[curr.x][end - dis] == '.') {
                    arr[curr.x][end - dis] = arr[curr.x][curr.y];
                    queue.add(new Coordinate(curr.x, end - dis));
                }
            }

            int count = 0;
            sbr = new StringBuilder();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    sbr.append(arr[i][j]);
                    if (arr[i][j] != '#' && arr[i][j] != '.') {
                        count++;
                    }
                }
                sbr.append("\n");
            }
            println(count - initialCount);
            println(sbr.toString());
        }
    }

    void findBoundary() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boundaries[i][j] = new Node();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '#') continue;
                if (boundaries[i][j].startv == null) {
                    // Find vertical boundary
                    // Move up
                    int startRow = i;
                    while (isValid(startRow - 1, j)) {
                        startRow--;
                    }
                    int endRow = i;
                    while (isValid(endRow + 1, j)) {
                        endRow++;
                    }

                    // Save the answer for all the characters in same vertical column
                    int row = startRow;
                    while (row <= endRow) {
                        boundaries[row][j].startv = new Coordinate(startRow, j);
                        boundaries[row][j].endv = new Coordinate(endRow, j);
                        row++;
                    }
                }
                if (boundaries[i][j].starth == null) {
                    // Find horizontal boundary
                    // Move left
                    int leftCol = j;
                    while (isValid(i, leftCol - 1)) {
                        leftCol--;
                    }
                    int rightCol = j;
                    while (isValid(i, rightCol + 1)) {
                        rightCol++;
                    }

                    // Save the answer for all the characters in same vertical column
                    int col = leftCol;
                    while (col <= rightCol) {
                        boundaries[i][col].starth = new Coordinate(i, leftCol);
                        boundaries[i][col].endh = new Coordinate(i, rightCol);
                        col++;
                    }
                }
            }
        }
    }

    boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m && arr[x][y] != '#';
    }

    static class Node {
        Coordinate startv, endv, starth, endh;
    }

    static class Coordinate {
        int x, y;

        public Coordinate(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new PalindromicCrossword(), "1").start();
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
