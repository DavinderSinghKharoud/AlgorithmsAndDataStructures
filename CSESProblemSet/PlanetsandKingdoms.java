import java.io.*;
import java.util.*;

/**
 * FindGreatestCommonDivisor game has n
 * n
 *  planets, connected by m
 * m
 *  teleporters. Two planets a
 * a
 *  and b
 * b
 *  belong to the same kingdom exactly when there is a route both from a
 * a
 *  to b
 * b
 *  and from b
 * b
 *  to a
 * a
 * . Your task is to determine for each planet its kingdom.
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the number of planets and teleporters. The planets are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * After this, there are m
 * m
 *  lines describing the teleporters. Each line has two integers a
 * a
 *  and b
 * b
 * : you can travel from planet a
 * a
 *  to planet b
 * b
 *  through a teleporter.
 *
 * Output
 *
 * First print an integer k
 * k
 * : the number of kingdoms. After this, print for each planet a kingdom label between 1
 * 1
 *  and k
 * k
 * . You can print any valid solution.
 *
 * Constraints
 * 1≤n≤105
 * 1
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
 * 2 3
 * 3 1
 * 3 4
 * 4 5
 * 5 4
 *
 * Output:
 * 2
 * 1 1 1 2 2
 */
public class PlanetsandKingdoms {

    static void solve() throws IOException {
		
		int n = fastReader.intNext();
		int m =fastReader.intNext();
		
		
		List<List<Integer>> adj1 = new ArrayList<>(), adj2 = new ArrayList<>();
		
		for(int i = 0; i<= n; i++ ){
			adj1.add(new ArrayList<>());
			adj2.add(new ArrayList<>());
		}
		
		while( m--> 0 ){
			int a = fastReader.intNext();
			int b = fastReader.intNext();
			adj1.get(a).add(b);
			adj2.get(b).add(a);
		}
		
		Stack<Integer> st = new Stack<>();
		boolean[] vis = new boolean[n + 1];
		
		for(int i = 1; i <= n; i++ ){
			if( !vis[i] ){
				dfs1( vis, st, adj1, i);
			}
		}
		
		int[] ans = new int[n + 1];
		Arrays.fill(vis, false);
		int room = 0;
		
		while( !st.isEmpty() ){
			int node = st.pop();
			if( !vis[node]){
				room += 1;
				dfs2( vis, adj2, room, node, ans);
			}
		
		}
		
		println(room);
		for(int val: ans ){
			if( val== 0) continue;
			print(val + " ");
		}
		
    }

	static void dfs2( boolean[] vis, List<List<Integer>> adj, int room, int node, int[] res ){
		vis[node] = true;
		for(int x: adj.get(node)){
			if( !vis[x] ){
				dfs2( vis, adj, room, x, res);
			}
		}
		res[node] = room;
	}
	
	static void dfs1( boolean[] vis, Stack<Integer> st, List<List<Integer>> adj, int node ){
		vis[node] = true;

		for(int x: adj.get(node) ){
			if( !vis[x]){
				dfs1( vis,st, adj, x);
			}
		}
        st.add(node);

	}

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
        out.close();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;static long lmin = Long.MIN_VALUE;

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


