import java.io.*;
import java.util.*;

/**
 * A company has n
 * n
 *  employees with certain salaries. Your task is to keep track of the salaries and process queries.
 *
 * Input
 *
 * The first input line contains two integers n
 * n
 *  and q
 * q
 * : the number of employees and queries. The employees are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * The next line has n
 * n
 *  integers p1,p2,…,pn
 * p
 * 1
 * ,
 * p
 * 2
 * ,
 * …
 * ,
 * p
 * n
 * : each employee's salary.
 *
 * After this, there are q
 * q
 *  lines describing the queries. Each line has one of the following forms:
 * ! k
 * k
 *  x
 * x
 * : change the salary of employee k
 * k
 *  to x
 * x
 *
 * ? a
 * a
 *  b
 * b
 * : count the number of employees whose salary is between a…b
 * a
 * …
 * b
 *
 * Output
 *
 * Print the answer to each ? query.
 *
 * Constraints
 * 1≤n,q≤2⋅105
 * 1
 * ≤
 * n
 * ,
 * q
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * 1≤pi≤109
 * 1
 * ≤
 * p
 * i
 * ≤
 * 10
 * 9
 *
 * 1≤k≤n
 * 1
 * ≤
 * k
 * ≤
 * n
 *
 * 1≤x≤109
 * 1
 * ≤
 * x
 * ≤
 * 10
 * 9
 *
 * 1≤a≤b≤109
 * 1
 * ≤
 * a
 * ≤
 * b
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 5 3
 * 3 7 2 2 5
 * ? 2 3
 * ! 3 6
 * ? 2 3
 *
 * Output:
 * 3
 * 2
 */
public class SalaryQueries {

    static int[][] dp;
    static int[] arr;

    static void solve() throws IOException {

        int n = read.intNext(), q = read.intNext();

        int len = powOfTwo(n);
        dp = new int[2 * len + 1][2];
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = read.intNext();
        }

        constructTree(0, n - 1, 0);

        while (q-- > 0) {
            char c = read.read().charAt(0);
            if (c == '?') {
                sbr.append(query(0, n - 1, 0, read.intNext(), read.intNext())).append("\n");
            } else {
                update(0, n - 1, 0, read.intNext() - 1, read.intNext());
            }
        }

        print(sbr.toString());

    }

    private static int powOfTwo(int n) {
        if (n == 0) return 1;
        if ((n & (n - 1)) == 0) return n;

        while ((n & (n - 1)) > 0) {
            n = n & (n - 1);
        }
        return n << 1;
    }

    static int query(int l, int h, int pos, int ql, int qh) {
        if (l > h) return 0;

        if (l == h && dp[pos][0] >= ql && dp[pos][0] <= qh) {
            return 1;
        }

        if (dp[pos][0] > qh || dp[pos][1] < ql) return 0;
        int mid = l + (h - l) / 2;

        return query(l, mid, 2 * pos + 1, ql, qh) + query(mid + 1, h, 2 * pos + 2, ql, qh);
    }

    static void update(int l, int h, int pos, int tar, int value) {
        if (tar < l || tar > h) return;
        if (l == h) {
            dp[pos] = new int[]{value, value};
            return;
        }

        int mid = l + (h - l) / 2;
        if (tar <= mid) {
            update(l, mid, 2 * pos + 1, tar, value);
        } else {
            update(mid + 1, h, 2 * pos + 2, tar, value);
        }

        dp[pos] = getLimits(dp[2 * pos + 1], dp[2 * pos + 2]);
    }

    static void constructTree(int l, int h, int pos) {
        if (l == h) {
            dp[pos] = new int[]{arr[l], arr[l]};
            return;
        }

        int mid = l + (h - l) / 2;

        constructTree(l, mid, 2 * pos + 1);
        constructTree(mid + 1, h, 2 * pos + 2);

        dp[pos] = getLimits(dp[2 * pos + 1], dp[2 * pos + 2]);
    }

    static int[] getLimits(int[] a, int[] b) {
        return new int[]{min(a[0], b[0]), max(a[1], b[1])};
    }


    /**
     * Used Index Compression
     */
    static int[] seg;
    static int[] employeeToSalary;

    private static void solve2() throws IOException {
        int n = read.intNext(), q = read.intNext();
        employeeToSalary = new int[n];
        Compression compression = new Compression(n + q);
        int[][] queries = new int[q][3];

        for (int i = 0; i < n; i++) {
            employeeToSalary[i] = read.intNext() - 1;
            compression.add(employeeToSalary[i]);
        }

        for (int i = 0; i < q; i++) {
            char c = read.read().charAt(0);
            if (c == '!') {
                queries[i] = new int[]{0, read.intNext() - 1, read.intNext() - 1};
                compression.add(queries[i][2]);
            } else {
                queries[i] = new int[]{1, read.intNext() - 1, read.intNext() - 1};
            }
        }

        compression.compress();

        seg = new int[powOfTwo(compression.size) * 2 + 1];

        int[] salaryIndex = new int[compression.size];
        for (int i = 0; i < n; i++) {
            int index = compression.getCeil(employeeToSalary[i]);
            salaryIndex[index]++;
            employeeToSalary[i] = index; //Save the corresponding compressed index
        }

        constructTree2(0, compression.size - 1, 0, salaryIndex);

        for (int[] query : queries) {
            if (query[0] == 0) { //Apply update

                updateSeg(0, compression.size - 1, 0, employeeToSalary[query[1]], -1);
                int index = compression.getCeil(query[2]);
                updateSeg(0, compression.size - 1, 0, index, 1);
                employeeToSalary[query[1]] = index;
            } else {
                sbr.append(querySeg(0, compression.size - 1, 0, compression.getCeil(query[1]), compression.getFloor(query[2]))).append("\n");

            }
        }

        print(sbr);
    }

    private static void updateSeg(int l, int h, int pos, int tar, int val) {
        if (tar < l || tar > h) return;
        if (l == h) {
            seg[pos] += val;
            return;
        }

        int mid = l + (h - l) / 2;

        updateSeg(l, mid, 2 * pos + 1, tar, val);
        updateSeg(mid + 1, h, 2 * pos + 2, tar, val);
        seg[pos] = seg[2 * pos + 1] + seg[2 * pos + 2];
    }

    private static int querySeg(int l, int h, int pos, int ql, int qh) {
        if (l > h || l > qh || h < ql) return 0;
        if (l >= ql && h <= qh) return seg[pos];
        int mid = l + (h - l) / 2;

        return querySeg(l, mid, 2 * pos + 1, ql, qh) + querySeg(mid + 1, h, 2 * pos + 2, ql, qh);
    }


    private static void constructTree2(int l, int h, int pos, int[] salaryIndex) {
        if (l == h) {
            seg[pos] = salaryIndex[l];
            return;
        }

        int mid = l + (h - l) / 2;
        constructTree2(l, mid, 2 * pos + 1, salaryIndex);
        constructTree2(mid + 1, h, 2 * pos + 2, salaryIndex);

        seg[pos] = seg[2 * pos + 1] + seg[2 * pos + 2];
    }

    static class Compression {
        int[] nums;
        int size;

        public Compression(int n) {
            nums = new int[n];
            size = 0;
        }

        public void add(int n) {
            nums[size++] = n;
        }

        public void compress() {
            nums = Arrays.copyOf(nums, size);
            shuffle(nums, size);
            Arrays.sort(nums);

            int last = 0;
            size = 0;
            for (int curr : nums) {
                if (size == 0 || curr != last) { //remove duplicates
                    nums[size++] = curr;
                    last = curr;
                }
            }

        }

        public int getCeil(int n) {
            int index = Arrays.binarySearch(nums, 0, size, n);
            return (index >= 0) ? index : -(index + 1);
        }

        public int getFloor(int n) {
            int index = Arrays.binarySearch(nums, 0, size, n);
            return (index >= 0) ? index : -(index + 2);
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


