import java.io.*;
import java.util.*;

/**
 * There are n
 * n
 * hotels on a street. For each hotel you know the number of free rooms. Your task is to assign hotel rooms for groups of tourists. All members of a group want to stay in the same hotel.
 * <p>
 * The groups will come to you one after another, and you know for each group the number of rooms it requires. You always assign a group to the first hotel having enough rooms. After this, the number of free rooms in the hotel decreases.
 * <p>
 * Input
 * <p>
 * The first input line contains two integers n
 * n
 * and m
 * m
 * : the number of hotels and the number of groups. The hotels are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 * <p>
 * The next line contains n
 * n
 * integers h1,h2,…,hn
 * h
 * 1
 * ,
 * h
 * 2
 * ,
 * …
 * ,
 * h
 * n
 * : the number of free rooms in each hotel.
 * <p>
 * The last line contains m
 * m
 * integers r1,r2,…,rm
 * r
 * 1
 * ,
 * r
 * 2
 * ,
 * …
 * ,
 * r
 * m
 * : the number of rooms each group requires.
 * <p>
 * Output
 * <p>
 * Print the assigned hotel for each group. If a group cannot be assigned a hotel, print 0 instead.
 * <p>
 * Constraints
 * 1≤n,m≤2⋅105
 * 1
 * ≤
 * n
 * ,
 * m
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 * <p>
 * 1≤hi≤109
 * 1
 * ≤
 * h
 * i
 * ≤
 * 10
 * 9
 * <p>
 * 1≤ri≤109
 * 1
 * ≤
 * r
 * i
 * ≤
 * 10
 * 9
 * <p>
 * Example
 * <p>
 * Input:
 * 8 5
 * 3 2 4 1 5 5 2 6
 * 4 4 7 1 1
 * <p>
 * Output:
 * 3 5 0 1 1
 */
public class HotelQueries {

    static int[] dp;
    static int[] arr;
    static boolean isFound = false;

    static void solve() throws IOException {

        int n = read.intNext(), m = read.intNext();

        arr = new int[n];

        dp = new int[1 << 19];

        for (int i = 0; i < n; i++) {
            arr[i] = read.intNext();
        }

        constructTree(0, n - 1, 0);

        while (m-- > 0) {
            int p = read.intNext();
            isFound = false;
            query(0, n - 1, 0, p);
            if (!isFound) {
                println(0);
            }
        }
    }


    static void query(int l, int h, int pos, int target) {

        if (l > h || dp[pos] < target) return;

        if (l == h) {
            isFound = true;
            println(l + 1);
            int diff = dp[pos] - target;
            if (diff < 0) {
                dp[pos] = 0;
            } else {
                dp[pos] = diff;
            }
            return;
        }

        int mid = l + (h - l) / 2;
        if (dp[2 * pos + 1] >= target) {
            query(l, mid, 2 * pos + 1, target);
            dp[pos] = max(dp[2 * pos + 1], dp[2 * pos + 2]);
            return;
        }
        query(mid + 1, h, 2 * pos + 2, target);
        dp[pos] = max(dp[2 * pos + 1], dp[2 * pos + 2]);

    }

    static void constructTree(int l, int h, int pos) {
        if (l == h) {
            dp[pos] = arr[l];
            return;
        }

        int mid = l + (h - l) / 2;

        constructTree(l, mid, 2 * pos + 1);
        constructTree(mid + 1, h, 2 * pos + 2);
        dp[pos] = max(dp[2 * pos + 1], dp[2 * pos + 2]);
    }

    static void solve2() throws IOException {
        int n = read.intNext(), m = read.intNext();

        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int h = read.intNext();
            TreeSet<Integer> curr = map.getOrDefault(h, new TreeSet<>());
            curr.add(i + 1);
            map.put(h, curr);
        }

        while (m-- > 0) {
            int p = read.intNext();

            Integer hotel = map.ceilingKey(p);
            if (hotel == null) {
                println(0);
                continue;
            }
            TreeSet<Integer> set = map.get(hotel);
            int index = set.first();
            println(index);
            set.remove(index);

            int diff = hotel - p;
            if (diff != 0) {
                TreeSet<Integer> curr = map.getOrDefault(diff, new TreeSet<>());
                curr.add(index);
                //map.put(diff, curr);
            }

            if( map.get(hotel).size() == 0){
                map.remove(hotel);
            }
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        //solve();
        solve2();
        out.close();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;

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


