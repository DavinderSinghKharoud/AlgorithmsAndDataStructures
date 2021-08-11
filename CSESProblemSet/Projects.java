import java.io.*;
import java.util.*;

/**
 * There are n
 * n
 * projects you can attend. For each project, you know its starting and ending days and the amount of money you would get as reward. You can only attend one project during a day.
 * <p>
 * What is the maximum amount of money you can earn?
 * <p>
 * Input
 * <p>
 * The first input line contains an integer n
 * n
 * : the number of projects.
 * <p>
 * After this, there are n
 * n
 * lines. Each such line has three integers ai
 * a
 * i
 * , bi
 * b
 * i
 * , and pi
 * p
 * i
 * : the starting day, the ending day, and the reward.
 * <p>
 * Output
 * <p>
 * Print one integer: the maximum amount of money you can earn.
 * <p>
 * Constraints
 * 1≤n≤2⋅105
 * 1
 * ≤
 * n
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 * <p>
 * 1≤ai≤bi≤109
 * 1
 * ≤
 * a
 * i
 * ≤
 * b
 * i
 * ≤
 * 10
 * 9
 * <p>
 * 1≤pi≤109
 * 1
 * ≤
 * p
 * i
 * ≤
 * 10
 * 9
 * <p>
 * Example
 * <p>
 * Input:
 * 4
 * 2 4 4
 * 3 6 6
 * 6 8 2
 * 5 7 3
 * <p>
 * Output:
 * 7
 */
public class Projects {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {

        int count = fastReader.intNext();

        int[][] arr = new int[count][3];

        for (int i = 0; i < count; i++) {
            arr[i] = new int[]{fastReader.intNext(), fastReader.intNext(), fastReader.intNext()};

        }

        Arrays.sort(arr, Comparator.comparingInt(o -> o[1]));

        long[] dp = new long[count];
        dp[0] = arr[0][2];

        for (int i = 1; i < count; i++) {

            long include = arr[i][2];
            int lower = binarySearch(arr, i);
            if (lower != -1) {
                include += dp[lower];
            }

            long exclude = dp[i - 1];

            dp[i] = Math.max(include, exclude);
        }

        print(dp[count - 1]);

    }

    static int binarySearch(int[][] arr, int i) {

        int low = 0, high = i - 1;

        while (low < high) {
            int mid = (high + low) / 2;
            if (arr[mid][1] < arr[i][0]) {
                if (mid + 1 < i && arr[mid + 1][1] < arr[i][0]) {
                    low = mid + 1;
                } else {
                    return mid;
                }
            } else {
                high = mid - 1;
            }
        }

        return (arr[low][1] < arr[i][0]) ? low : -1;
    }

    private static void solve2() throws IOException {
        int n = fastReader.intNext();
        long[][] p = new long[n][3];
        for(int i = 0; i < n; i++) {
            p[i] = new long[] {fastReader.intNext(),fastReader.intNext(),fastReader.intNext()};
        }
        Arrays.sort(p, Comparator.comparing(arr -> arr[1]));
        TreeMap<Long, Integer> map = new TreeMap<>();
        for(int i = 0; i < n; i++) map.put(p[i][1], i);
        long[] dp = new long[n];
        long[] max = new long[n];
        long ans = 0L;
        for(int i = 0; i < n; i++) {
            Map.Entry<Long, Integer> entry = map.lowerEntry(p[i][0]);
            if(entry == null) dp[i] = p[i][2];
            else dp[i] = max[entry.getValue()] + p[i][2];
            ans = Math.max(ans, dp[i]);
            max[i] = ans;
        }
        println(ans);

    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        //solve();
        solve2();
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

        public long longNext() throws IOException {
            int c = scan();
            while (isWhiteSpace(c))
                c = scan();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = scan();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = scan();
            } while (!isWhiteSpace(c));
            return res * sgn;
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
    static class TMultiset<T extends Object> extends TreeMap<T, Integer> {
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


    static int min(Object... objects) {
        int min = Integer.MAX_VALUE;

        for (Object num : objects) {
            min = Math.min(min, (Integer) num);
        }
        return min;
    }

    static int max(Object... objects) {
        int max = Integer.MIN_VALUE;

        for (Object num : objects) {
            max = Math.max(max, (Integer) num);
        }
        return max;
    }
}


