import java.io.*;
import java.util.*;

/**
 * Your task is to maintain a list of arrays which initially has a single array. You have to process the following types of queries:
 * Set the value a
 * a
 *  in array k
 * k
 *  to x
 * x
 * .
 * Calculate the sum of values in range [a,b]
 * [
 * a
 * ,
 * b
 * ]
 *  in array k
 * k
 * .
 * Create a copy of array k
 * k
 *  and add it to the end of the list.
 * Input
 *
 * The first input line has two integers n
 * n
 *  and q
 * q
 * : the array size and the number of queries.
 *
 * The next line has n
 * n
 *  integers t1,t2,…,tn
 * t
 * 1
 * ,
 * t
 * 2
 * ,
 * …
 * ,
 * t
 * n
 * : the initial contents of the array.
 *
 * Finally, there are q
 * q
 *  lines describing the queries. The format of each line is one of the following: "1 k
 * k
 * a
 * a
 *  x
 * x
 * ", "2 k
 * k
 * a
 * a
 *  b
 * b
 * " or "3 k
 * k
 * ".
 *
 * Output
 *
 * Print the answer to each sum query.
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
 * 1≤ti,x≤109
 * 1
 * ≤
 * t
 * i
 * ,
 * x
 * ≤
 * 10
 * 9
 *
 * 1≤a≤b≤n
 * 1
 * ≤
 * a
 * ≤
 * b
 * ≤
 * n
 *
 * Example
 *
 * Input:
 * 5 6
 * 2 3 1 2 5
 * 3 1
 * 2 1 1 5
 * 2 2 1 5
 * 1 2 2 5
 * 2 1 1 5
 * 2 2 1 5
 *
 * Output:
 * 13
 * 13
 * 13
 * 15
 */
public class RangeQueriesandCopies {

    int n;
    List<Node> trees = new ArrayList<>();
    int[] arr;

    void solve() throws IOException {

        n = read.intNext();
        int q = read.intNext();
        arr = new int[n];
        trees.add(new Node(null, null, 0));

        for (int i = 0; i < n; i++) {
            arr[i] = read.intNext();
        }

        construct(0, n - 1, trees.get(0));


        for (int i = 0; i < q; i++) {
            int type = read.intNext();
            if (type == 3) {
                int index = read.intNext() - 1;
                Node curr = trees.get(index);
                trees.add(new Node(curr.left, curr.right, curr.sum));
            } else if (type == 2) {
                int k = read.intNext() - 1;

                sbr.append(query(0, n - 1, read.intNext() - 1, read.intNext() - 1, trees.get(k))).append("\n");
            } else {
                int k = read.intNext() - 1;

                trees.set(k, update(0, n - 1, read.intNext() - 1, read.intNext(), trees.get(k)) );
            }
        }
        print(sbr);
    }

    Node update(int l, int h, int tar, int value, Node curr) {

        if (l == h) {
            return new Node(null, null, value);
        }
        int mid = l + (h - l) / 2;
        Node newNode = new Node(curr.left, curr.right, curr.sum);

        if (tar <= mid) {
            newNode.left = update(l, mid, tar, value, curr.left);

        } else {
            newNode.right = update(mid + 1, h, tar, value, curr.right);
        }

        newNode.sum = newNode.left.sum + newNode.right.sum;
        return newNode;
    }

    long query(int l, int h, int ql, int qh, Node curr) {
        if (ql > h || qh < l) return 0;

        if (l >= ql && h <= qh) return curr.sum;

        int mid = l + (h - l) / 2;
        return query(l, mid, ql, qh, curr.left) + query(mid + 1, h, ql, qh, curr.right);
    }

    void construct(int l, int h, Node curr) {
        if (l == h) {
            curr.sum = arr[l];

        } else {
            int mid = l + (h - l) / 2;
            curr.left = new Node(null, null, 0);
            curr.right = new Node(null, null, 0);

            construct(l, mid, curr.left);
            construct(mid + 1, h, curr.right);

            curr.sum = curr.left.sum + curr.right.sum;
        }
    }

    static class Node {
        long sum;
        Node left, right;

        public Node(Node l, Node r, long sum) {
            this.sum = sum;
            left = l;
            right = r;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        RangeQueriesandCopies object = new RangeQueriesandCopies();
        object.solve();
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


    static long min(Long... objects) {
        long min = Integer.MAX_VALUE;

        for (Long num : objects) {
            min = Math.min(min, num);
        }
        return min;
    }

    static int min(Integer... objects) {
        int min = Integer.MAX_VALUE;

        for (Integer num : objects) {
            min = Math.min(min, num);
        }
        return min;
    }

    static long max(Long... objects) {
        long max = Long.MIN_VALUE;

        for (Long num : objects) {
            max = Math.max(max, num);
        }
        return max;
    }

    static int max(Integer... objects) {
        int max = Integer.MIN_VALUE;

        for (Integer num : objects) {
            max = Math.max(max, num);
        }
        return max;
    }
}


