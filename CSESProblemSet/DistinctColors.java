import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class DistinctColors implements Runnable {

    int n, dcolor[], colors[], tree[][], dstart[], dend[], bit[];
    long[] res;
    int timer = 0;

    List<List<int[]>> queries = new ArrayList<>();
    void solve() throws IOException {
        n = read.intNext();

        dcolor = iArr(n);
        res = lArr(n);
        dstart = iArr(n);
        dend = iArr(n);
        colors = iArr(n);
        bit = iArr(n + 1);

        CreateTree cTree = new CreateTree(n);

        for (int i = 0; i < n; i++) {
            dcolor[i] = read.intNext();
            queries.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int a = read.intNext() - 1, b = read.intNext() - 1;
            cTree.addEdge(a, b, i);
        }

        tree = cTree.create();

        dfs(0, -1);


        //Build queries
        for(int i = 0; i < n; i++ ){
            queries.get(dend[i] - 1).add(new int[]{dstart[i], i});
            //Update the color
            colors[ dstart[i]] = dcolor[i];
        }

        Map<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++ ){
            update(i, 1);

            if( map.containsKey(colors[i])){
                update( map.get(colors[i]), -1);
            }

            map.put(colors[i], i);

            //Solve all the queries which have i as right hand side
            for(int[] q: queries.get(i)){
                res[q[1]] = query(i) - query( q[0] - 1);
            }
        }

        for(long val: res ){
            sbr.append(val).append(' ');
        }


        print(sbr.toString());

    }

    long query(int index ){
        index++;
        long res = 0;
        for(; index > 0; index -= index & -index){
            res += bit[index];
        }
        return res;
    }
    void update(int index, int val){
        index++;
        for(; index <= n; index += index & -index){
            bit[index] += val;
        }

    }

    void dfs(int pos, int parent ){
        dstart[pos] = timer++;

        for(int child: tree[pos]){
            if( child != parent){
                dfs(child, pos);
            }
        }

        dend[pos] = timer;
    }
    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new DistinctColors(), "1").start();
    }

    static class CreateTree {
        int[] count, from, to;
        int len;

        public CreateTree(int len) {
            len--;
            count = iArr(len + 1);
            from = iArr(len);
            to = iArr(len);
            this.len = len;
        }

        void addEdge(int a, int b, int index) {
            from[index] = a;
            to[index] = b;
            count[from[index]]++;
            count[to[index]]++;
        }

        int[][] create() {
            int[][] arr = new int[len + 1][];

            for (int i = 0; i < len + 1; i++) {
                arr[i] = new int[count[i]];
            }
            for (int i = 0; i < len; ++i) {
                arr[from[i]][--count[from[i]]] = to[i];
                arr[to[i]][--count[to[i]]] = from[i];
            }
            return arr;
        }
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
            solve();
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
}