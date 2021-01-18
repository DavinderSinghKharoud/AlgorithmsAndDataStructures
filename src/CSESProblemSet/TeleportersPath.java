import java.io.*;
import java.util.*;

/**
 * A game has n
 * n
 *  levels and m
 * m
 *  teleportes between them. You win the game if you move from level 1
 * 1
 *  to level n
 * n
 *  using every teleporter exactly once.
 *
 * Can you win the game, and what is a possible way to do it?
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the number of levels and teleporters. The levels are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * Then, there are m
 * m
 *  lines describing the teleporters. Each line has two integers a
 * a
 *  and b
 * b
 * : there is a teleporter from level a
 * a
 *  to level b
 * b
 * .
 *
 * Output
 *
 * Print m+1
 * m
 * +
 * 1
 *  integers: the sequence in which you visit the levels during the game. You can print any valid solution.
 *
 * If there are no solutions, print "IMPOSSIBLE".
 *
 * Constraints
 * 2≤n≤105
 * 2
 * ≤
 * n
 * ≤
 * 10
 * 5
 *
 * 1≤m≤2⋅105
 * 1
 * ≤
 * m
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * 1≤a,b≤n
 * 1
 * ≤
 * a
 * ,
 * b
 * ≤
 * n
 *
 * Example
 *
 * Input:
 * 5 6
 * 1 2
 * 1 3
 * 2 4
 * 2 5
 * 3 1
 * 4 2
 *
 * Output:
 * 1 3 1 2 4 2 5
 */
public class TeleportersPath {

    static List<Deque<Integer>> arr = new ArrayList<>();


    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        for (int i = 0; i < n; i++) {
            arr.add(new LinkedList<>());
        }

        int[] in = new int[n];
        int[] out = new int[n];
        int edgeCount = m;
        while (m-- > 0) {
            int a = fastReader.intNext() - 1, b = fastReader.intNext() - 1;
            arr.get(a).add(b);
            in[b]++;
            out[a]++;
        }

        for (int i = 0; i < n; i++) {
            if ((i == 0 && Math.abs(in[i] - out[i]) != 1) || (i == n - 1 && Math.abs(in[i] - out[i]) != 1)) {
                print("IMPOSSIBLE");
                return;
            }
            if (i != 0 && i != n - 1 && in[i] - out[i] != 0) {
                print("IMPOSSIBLE");
                return;
            }

        }

        List<Integer> res = new ArrayList<>();
        dfs(0, res);
        if (res.size() != edgeCount + 1) {
            print("IMPOSSIBLE");

        } else {
            for (int i = res.size() - 1; i >= 0; i--) {
                sbr.append(res.get(i) + 1).append(" ");

            }
            print(sbr);
        }


    }


    static void dfs(int u, List<Integer> res) {

        while (!arr.get(u).isEmpty()) {
            int v = arr.get(u).pollLast();
            dfs(v, res);

        }
        res.add(u);

    }

    static void solve2() throws Exception {
        int n = fastReader.intNext();
        int m = fastReader.intNext();

        for (int i = 0; i < n; i++) {
            arr.add(new LinkedList<>());
        }

        int[] in = new int[n];
        int[] out = new int[n];
        int edgeCount = m;
        while (m-- > 0) {
            int a = fastReader.intNext() - 1, b = fastReader.intNext() - 1;
            arr.get(a).add(b);
            in[b]++;
            out[a]++;
        }

        for (int i = 0; i < n; i++) {
            if ((i == 0 && Math.abs(in[i] - out[i]) != 1) || (i == n - 1 && Math.abs(in[i] - out[i]) != 1)) {
                print("IMPOSSIBLE");
                return;
            }
            if (i != 0 && i != n - 1 && in[i] - out[i] != 0) {
                print("IMPOSSIBLE");
                return;
            }

        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayDeque<Integer> res = new ArrayDeque<>();
        stack.add(0);
        while ( !stack.isEmpty() ){
            int u = stack.getFirst();
            if( arr.get(u).size() == 0){
                res.addFirst(u);
                stack.removeFirst();
            }else{
                int v = arr.get(u).remove();
                stack.addFirst(v);
            }
        }

        if (res.size() != edgeCount + 1) {
            print("IMPOSSIBLE");

        } else {
            for(int num: res){
                print(num + 1 + " ");
            }
        }
    }

    private static void solve3() throws IOException {
        int n = fastReader.intNext();
        int m = fastReader.intNext();

        for (int i = 0; i < n; i++) {
            arr.add(new LinkedList<>());
        }

        int[] in = new int[n];
        int[] out = new int[n];
        int edgeCount = m;
        while (m-- > 0) {
            int a = fastReader.intNext() - 1, b = fastReader.intNext() - 1;
            arr.get(a).add(b);
            in[b]++;
            out[a]++;
        }

        for (int i = 0; i < n; i++) {
            if ((i == 0 && Math.abs(in[i] - out[i]) != 1) || (i == n - 1 && Math.abs(in[i] - out[i]) != 1)) {
                print("IMPOSSIBLE");
                return;
            }
            if (i != 0 && i != n - 1 && in[i] - out[i] != 0) {
                print("IMPOSSIBLE");
                return;
            }

        }

        Stack<Integer> stack = new Stack<>();
        Deque<Integer> res = new ArrayDeque<>();
        stack.add(0);
        while ( !stack.isEmpty() ){
           while ( !arr.get(stack.peek()).isEmpty()){
               stack.add(arr.get(stack.peek()).poll());
           }
           res.add(stack.pop());
        }

        if (res.size() != edgeCount + 1) {
            print("IMPOSSIBLE");

        } else {
            while ( !res.isEmpty()){
                sbr.append(res.pollLast() + 1).append(' ');
            }
            print(sbr);
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws Exception {

        //solve();
        //solve2();
        solve3();
        out.close();
    }


    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
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


