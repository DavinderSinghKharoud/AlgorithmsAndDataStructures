
import java.io.*;
import java.util.*;

public class SlidingMedian {

    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();

    static void solve() throws IOException {

        int tests = fastReader.nextInt();

        int k = fastReader.nextInt();

        PriorityQueue<Integer> max = new PriorityQueue<>(k, Comparator.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>(k);

        boolean isOdd = (k & 1) == 1;
        int[] arr = new int[tests];

        for (int i = 0; i < tests; i++) {
            arr[i] = fastReader.nextInt();
        }

        for (int i = 0; i < k; i++) {
            add(max, min, arr[i]);
        }

        sbr.append(getMedian(max, min, isOdd)).append(" ");

        for (int i = k; i < tests; i++) {
            if (arr[i - k] <= max.peek()) {
                max.remove(arr[i - k]);
            } else {
                min.remove(arr[i - k]);
            }
            add(max, min, arr[i]);
            sbr.append(getMedian(max, min, isOdd)).append(" ");
        }

        print(sbr);
    }

    static int getMedian(PriorityQueue<Integer> max, PriorityQueue<Integer> min, boolean isOdd) {

        if (!isOdd) return max.peek();
        return (max.size() > min.size()) ? max.peek() : min.peek();
    }


    static void add(PriorityQueue<Integer> max, PriorityQueue<Integer> min, int num) {
        max.add(num);
        min.add(max.poll());

        if (min.size() > max.size()) max.add(min.poll());
    }

    private static void solve2() throws IOException {
        int n = fastReader.nextInt();
        int window = fastReader.nextInt();

        int[] input = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = fastReader.nextInt();

        }

        if (window == 1) {
            for (int i = 0; i < n; i++) {
                sbr.append(input[i]).append(" ");
            }
            print(sbr.toString());
            return;
        }
        TMultiset<Integer> lower = new TMultiset<>();
        TMultiset<Integer> higher = new TMultiset<>();

        int median = 0;

        for (int i = 0; i < n; i++) {
            //Add
            if (input[i] > median) {
                higher.add(input[i]);
            } else {
                lower.add(input[i]);
            }

            //Remove
            if (i >= window) {
                if (input[i - window] > median) {
                    higher.remove(input[i - window]);
                } else {
                    lower.remove(input[i - window]);
                }
            }

            median = balance(lower, higher);
            if (i >= window - 1) {
                sbr.append(median).append(" ");
            }

        }
        print(sbr);
    }

    private static int balance(TMultiset<Integer> lower, TMultiset<Integer> higher) {
        int diff = lower.size - higher.size();
        while (diff < 0 || diff > 1) {
            if (diff < 0) {
                int x = higher.firstKey();
                higher.remove(x);
                lower.add(x);
            } else {
                int x = lower.lastKey();
                lower.remove(x);
                higher.add(x);
            }
            diff = lower.size() - higher.size();
        }
        return lower.lastKey();
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        //solve();
        solve2();
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
        protected int size = 0;

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

            Integer result = super.remove(key);
            return result;
        }

        @java.lang.Override
        public int size() {
            return size;
        }
    }

    static void print(Object object) {
        out.print(object);
    }

    static void println(Object object) {
        out.println(object);
    }
}
