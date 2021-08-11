
import java.io.*;
import java.util.*;

/**
 * You are given an array of n
 * n
 *  integers. Your task is to calculate for each window of k
 * k
 *  elements, from left to right, the minimum total cost of making all elements equal.
 *
 * You can increase or decrease each element with cost x
 * x
 *  where x
 * x
 *  is the difference between the new and the original value. The total cost is the sum of such costs.
 *
 * Input
 *
 * The first input line contains two integers n
 * n
 *  and k
 * k
 * : the number of elements and the size of the window.
 *
 * Then there are n
 * n
 *  integers x1,x2,…,xn
 * x
 * 1
 * ,
 * x
 * 2
 * ,
 * …
 * ,
 * x
 * n
 * : the contents of the array.
 *
 * Output
 *
 * Output n−k+1
 * n
 * −
 * k
 * +
 * 1
 *  values: the costs.
 *
 * Constraints
 * 1≤k≤n≤2⋅105
 * 1
 * ≤
 * k
 * ≤
 * n
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * 1≤xi≤109
 * 1
 * ≤
 * x
 * i
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 8 3
 * 2 4 3 5 8 1 2 1
 *
 * Output:
 * 2 2 5 7 7 1
 */
public class SlidingCost {

    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();

    static void solve() throws IOException {

        int n = fastReader.nextInt();
        int window = fastReader.nextInt();

        int[] input = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = fastReader.nextInt();

        }


        TMultiset<Integer> lower = new TMultiset<>();
        TMultiset<Integer> higher = new TMultiset<>();

        int median = 0;
        long[] sums = new long[2];

        for (int i = 0; i < n; i++) {

            //Add
            if (input[i] > median) {
                higher.add(input[i]);
                sums[1] += input[i];
            } else {
                lower.add(input[i]);
                sums[0] += input[i];
            }

            //Remove
            if (i >= window) {
                if (input[i - window] > median) {
                    higher.remove(input[i - window]);
                    sums[1] -= input[i - window];
                } else {
                    lower.remove(input[i - window]);
                    sums[0] -= input[i - window];
                }

            }

            median = balance(lower, higher, sums);

            if (i >= window - 1) {
                sbr.append(findBalance(median, sums, higher.size(), lower.size())).append(" ");
            }

        }
        print(sbr);


    }

    static long findBalance(int median, long[] sums, int higher, int lower) {
        return Math.abs(sums[1] - higher * median + (lower * median - sums[0]));

    }

    private static int balance(TMultiset<Integer> lower, TMultiset<Integer> higher, long[] sums) {
        int diff = lower.size - higher.size();
        while (diff < 0 || diff > 1) {
            if (diff < 0) {
                int x = higher.firstKey();

                sums[1] -= x;
                sums[0] += x;

                higher.remove(x);
                lower.add(x);
            } else {
                int x = lower.lastKey();
                sums[0] -= x;
                sums[1] += x;

                lower.remove(x);
                higher.add(x);
            }
            diff = lower.size() - higher.size();
        }
        return lower.lastKey();
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
        out.close();
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
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
    }

    static void shuffle(int[][] aa, int n) {
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

    /**
     * Tree Multiset utility class *
     */
    static class TMultiset<T extends Number> extends TreeMap<T, Integer> {
        private int size = 0;

        void add(T value) {
            Integer count = get(value);
            size++;
            if (count == null) {
                put(value, 1);
            } else {
                put(value, count + 1);
            }
        }

        @SuppressWarnings(value = "unchecked")
        @Override
        public Integer remove(Object key) {
            if (!containsKey(key)) {
                return null;
            }

            size--;

            Integer value = get(key);
            if (value > 1) {
                return put((T) key, value - 1);
            }

            return super.remove(key);
        }

        @java.lang.Override
        public int size() {
            return size;
        }
    }

    /**
     * It is a HashMap
     */
    static class HMap<T> extends HashMap<T, Integer> {
        void add(T key) {
            Integer count = get(key);
            put(key, count == null ? 1 : count + 1);
        }

        @SuppressWarnings(value = "unchecked")
        @Override
        public Integer remove(Object key) {
            if (!containsKey(key)) return null;

            int count = get(key);
            if (count > 1) return put((T) key, count - 1);

            return super.remove(key);
        }
    }

    static void print(Object object) {
        out.print(object);
    }

    static void println(Object object) {
        out.println(object);
    }
}
