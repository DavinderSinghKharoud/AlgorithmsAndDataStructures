package CodeForces.Round716;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.*;

public class CutAnsStick implements Runnable {

    int[][] counts;

    // Use Randomized Algo (Time Complexity[nLog(n) + Q(Log(n)^2)]
    void solve() throws IOException {
        int n = ri(), q = ri();
        int[] arr = iArr(n);
        counts = new int[n][];
        int[] total = iArr(n);
        for (int i = 0; i < n; i++) {
            arr[i] = ri() - 1;
            total[arr[i]]++;
        }

        for (int i = 0; i < n; i++) {
            if (total[i] > 0) {
                counts[i] = new int[total[i]];
            }
        }

        for (int i = 0; i < n; i++) {
            int curr = arr[i];
            counts[curr][total[curr] - 1] = i;
            total[curr]--;
        }

        for (int i = 0; i < n; i++) {
            if (counts[i] != null) {
                Arrays.sort(counts[i]);
            }
        }

        while (q-- > 0) {
            int l = ri() - 1, r = ri() - 1;

            // Get 30 random numbers
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < 30; i++) {
                set.add(arr[getRandom(l, r)]);
            }

            // Check the count of each element and find max
            int max = 1;

            for (int ele : set) {
                int[] curr = counts[ele];
                int count = upperBound(curr, r) - lowerBound(curr, l);
                max = max(max, count);
            }

            int wid = r - l + 1;
            int ans = 0;
            int start = 0, end = max;
            while (start <= end) {
                int mid = (start + end) >> 1; // The number of partitions
                int rem = wid - mid;
                int leftOver = max - mid;
                int allowed = (rem + 1) / 2;
                if (allowed >= leftOver) {
                    ans = mid;
                    end = mid - 1;
                } else
                    start = mid + 1;
            }
            ans++;
            // Answer is just really max(max - (width - max), 1)
            sbr.append(ans).append("\n");
        }

        print(sbr.toString());
    }

    int lowerBound(int[] arr, int val) {
        int l = -1, r = arr.length;
        while (l + 1 < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] >= val)
                r = mid;
            else
                l = mid;
        }
        return r;
    }

    int upperBound(int[] arr, int val) {
        int l = -1, r = arr.length;
        while (l + 1 < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] <= val) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l + 1;
    }

    /************************************************************************************************************************************************/
    // Time Complexity [ O( (N + Q) Sqrt(N) ) ]
    void solveMoAlgo() throws IOException {
        int n = ri(), q = ri();
        int[] arr = iArr(n);
        Query[] queries = new Query[q];
        for (int i = 0; i < n; i++) {
            arr[i] = ri();
        }
        for (int i = 0; i < q; i++) {
            queries[i] = new Query(ri() - 1, ri(), i);
        }

        int sqr = (int) Math.sqrt(q);
        // Sort the queries
        Arrays.sort(queries, (o1, o2) -> {
            if (o1.l / sqr != o2.l / sqr)
                return Integer.compare(o1.l / sqr, o2.l / sqr);
            // To make it even more faster
            // If div by block is odd then sort the right index in ascending order
            // else descending order
            if ((o1.l / sqr & 1) == 1)
                return Integer.compare(o1.r, o2.r);
            return Integer.compare(o2.r, o1.r);
        });

        int leftPointer = 0, rightPointer = 0;
        int[] counts = iArr(n + 1);

        for (Query query : queries) {
            int l = query.l, r = query.r;
            while (leftPointer > query.l) {
                leftPointer--;
                counts[arr[leftPointer]]++;
            }

            while (rightPointer < query.r) {
                counts[arr[rightPointer]]++;
                rightPointer++;
            }

            while (rightPointer > query.r) {
                rightPointer--;
                counts[arr[rightPointer]]--;
            }

            while (leftPointer < query.l) {
                counts[arr[leftPointer]]--;
                leftPointer++;
            }

            // Choose 30 random numbers and find max frequency
            int maxFreq = 1;
            for (int i = 0; i < 30; i++) {
                maxFreq = max(maxFreq, counts[arr[getRandom(l, r - 1)]]);
            }

            query.res = max(1, maxFreq - (r - l - maxFreq));
        }
        int[] ans = iArr(q);
        for (Query qu : queries) {
            ans[qu.index] = qu.res;
        }

        Arrays.stream(ans).forEach(o -> sbr.append(o).append("\n"));
        println(sbr.toString());
    }

    class Query {
        int l, r, index, res = 1;

        public Query(int l, int r, int i) {
            this.l = l;
            this.r = r;
            this.index = i;
        }
    }

    /************************************************************************************************************************************************/
    int[] arr;
    //Time complexity [ O(n) + O(Q * (Log(n)) ^ 2)]
    void solveSegmentTree() throws IOException {
        int n = ri(), q = ri();
        int[] total = iArr(n + 1);
        arr = iArr(n);
        for (int i = 0; i < n; i++) {
            arr[i] = ri();
            total[arr[i]]++;
        }

        counts = new int[n + 1][];
        for (int i = 1; i < total.length; i++) {
            if (total[i] != 0) {
                counts[i] = new int[total[i]];
            }
        }

        for (int i = 0; i < n; i++) {
            counts[arr[i]][total[arr[i]] - 1] = i;
            total[arr[i]]--;
        }

        for (int[] count : counts) {
            if (count != null)
                Arrays.sort(count);
        }

        Seg seg = new Seg(arr);

        while (q-- > 0) {
            int l = ri() - 1, r = ri() - 1;
            int wid = r - l + 1;
            int max = seg.query(l, r, 0, n - 1, 0);
            sbr.append(max(1, max - (wid - max))).append("\n");
        }
        print(sbr.toString());
    }

    int count(int l, int r, int val) {
        if(counts[val] == null) return -1;
        int up =  upperBoundSeg(counts[val], r);
        int low = lowerBoundSeg(counts[val], l);
        return up - low + 1;
    }


    int lowerBoundSeg(int[] arr, int val) {
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

    int upperBoundSeg(int[] arr, int val) {
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

    class Seg {
        int[] tree;
        int[] arr;

        public Seg(int[] arr) {
            this.arr = arr;
            tree = new int[(getSize(arr.length) * 2 + 1)];
            build(0, arr.length - 1, 0);
        }

        void build(int l, int r, int pos) {
            if (l == r) {
                tree[pos] = arr[l];
            } else {
                int mid = l + (r - l)/2;
                int p = pos * 2;
                build(l, mid, p + 1);
                build(mid + 1, r, p + 2);
                tree[pos] = (count(l, r, tree[p + 1]) > count(l, r, tree[p + 2])) ? tree[p + 1] : tree[p + 2];
            }
        }

        int query(int l, int r, int start, int end, int pos) {
            if (start > r || end < l)
                return 0;
            if (start >= l && end <= r) {
                return count(l, r, tree[pos]);
            } else {
                int mid = (start + end) >> 1;
                pos <<= 1;
                return max(query(l, r, start, mid, pos + 1), query(l, r, mid + 1, end, pos + 2));
            }
        }

        int getSize(int len) {
            if (len < 2) return 1;

            if ((len & (len - 1)) == 0) return len;

            while ((len & (len - 1)) != 0) {
                len = len & (len - 1);
            }

            return len << 1;
        }
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new CutAnsStick(), "1").start();
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
            // solve();
            // solveMoAlgo();
            solveSegmentTree();
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

    static char rc() throws IOException {
        return rs().charAt(0);
    }

    static double rd() throws IOException {
        return read.doubleNext();
    }

    static int getRandom(int l, int r) {
        return l + (int) (Math.random() * (r - l + 1));
    }

    static int lowerbound(ArrayList<Integer> arr, int target) {
        int l = 0;
        int r = arr.size() - 1;
        int mid;
        while (l < r) {
            mid = (l + r) >> 1;
            if (arr.get(mid) >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    static int upperbound(ArrayList<Integer> arr, int target) {
        int l = 0;
        int r = arr.size() - 1;
        int mid;
        while (l < r) {
            mid = (l + r + 1) >> 1;
            if (arr.get(mid) > target)
                r = mid - 1;
            else
                l = mid;
        }
        return l;
    }
}
