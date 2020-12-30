
import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.StringTokenizer;
import java.util.*;

public class GridPaths {

    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();


    static int count = 0;

    public static void main(String[] args) throws IOException {

        int len = 7;

        String path = fastReader.next();
        boolean[][] visited = new boolean[len][len];

        dfs(path, 0, 0, 0, visited);
        out.print(count);


        out.close();
    }

    static void dfs(String path, int index, int row, int col, boolean[][] visited) {
        //out.println( row + " " + col + " " + index);
        if (row == 6 && col == 0) {
            if (index == 48) {
                count++;
            }
            return;
        }


        char s = path.charAt(index);

        //If I hit the wall I cannot continue

        if ((row + 1 == 7 || (!canBeVisit(visited, row - 1, col) && !canBeVisit(visited, row + 1, col))) && canBeVisit(visited, row, col - 1) && canBeVisit(visited, row, col + 1)
                || (col + 1 == 7 || (!canBeVisit(visited, row, col + 1) && !canBeVisit(visited, row, col - 1))) && canBeVisit(visited, row - 1, col) && canBeVisit(visited, row + 1, col)
                || (row == 0 || (!canBeVisit(visited, row - 1, col) && !canBeVisit(visited, row + 1, col))) && canBeVisit(visited, row, col - 1) && canBeVisit(visited, row, col + 1)
                || (col == 0 || (!canBeVisit(visited, row, col + 1) && !canBeVisit(visited, row, col - 1))) && canBeVisit(visited, row + 1, col) && canBeVisit(visited, row - 1, col)) {
            return;
        }

        visited[row][col] = true;


        if (s == '?' || s == 'L') {
            if (canBeVisit(visited, row, col - 1)) {
                dfs(path, index + 1, row, col - 1, visited);
            }
        }
        if (s == '?' || s == 'R') {
            if (canBeVisit(visited, row, col + 1)) {
                dfs(path, index + 1, row, col + 1, visited);
            }
        }
        if (s == '?' || s == 'U') {
            if (canBeVisit(visited, row - 1, col)) {
                dfs(path, index + 1, row - 1, col, visited);
            }
        }
        if (s == '?' || s == 'D') {
            if (canBeVisit(visited, row + 1, col)) {
                dfs(path, index + 1, row + 1, col, visited);
            }
        }

        visited[row][col] = false;
    }

    static boolean canBeVisit(boolean[][] visited, int row, int col) {
        return row >= 0 && col >= 0 && row < 7 && col < 7 && !visited[row][col];
    }


    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {

            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;

        }

        public String next() throws IOException {

            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);

        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    static void shuffle(int[] aa, int n) {
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i]; aa[i] = aa[j]; aa[j] = tmp;
        }
    }
}
