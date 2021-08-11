import java.io.*;
import java.util.*;

/**
 * A game has n
 * n
 *  rooms and m
 * m
 *  tunnels between them. Each room has a certain number of coins. What is the maximum number of coins you can collect while moving through the tunnels when you can freely choose your starting and ending room?
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the number of rooms and tunnels. The rooms are numbered 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * .
 *
 * Then, there are n
 * n
 *  integers k1,k2,…,kn
 * k
 * 1
 * ,
 * k
 * 2
 * ,
 * …
 * ,
 * k
 * n
 * : the number of coins in each room.
 *
 * Finally, there are m
 * m
 *  lines describing the tunnels. Each line has two integers a
 * a
 *  and b
 * b
 * : there is a tunnel from room a
 * a
 *  to room b
 * b
 * . Each tunnel is a one-way tunnel.
 *
 * Output
 *
 * Print one integer: the maximum number of coins you can collect.
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
 * 1≤ki≤109
 * 1
 * ≤
 * k
 * i
 * ≤
 * 10
 * 9
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
 * 4 4
 * 4 5 2 7
 * 1 2
 * 2 1
 * 1 3
 * 2 4
 *
 * Output:
 * 16
 */
public class CoinCollector {

	static int n, m;
	static int[] coins, dp;
    static void solve() throws IOException {
		
		n = fastReader.intNext();
		m = fastReader.intNext();
		
		coins = new int[n]; dp = new int[n];
		boolean[] vis = new boolean[n];
		
		for(int i = 0; i < n; i++ ){
			coins[i] = fastReader.intNext();
		}
		
		ArrayList<Integer> [] adj = new ArrayList[n];
		ArrayList<Integer>[] adjrev = new ArrayList[n];
		while( m--> 0 ){
			int a = fastReader.intNext();
			int b = fastReader.intNext();
			--a; --b;
			if( adj[a] == null ) adj[a] = new ArrayList<>();
			if( adjrev[b] == null) adjrev[b] = new ArrayList<>();
			adj[a].add(b);
			adjrev[b].add(a);
			
		}
		
		Stack<Integer> st = new Stack<>();
		for(int i = 0; i < n; i++ ){
			if( !vis[i] ) dfs( vis, adj,st, i);
		}
		
		int group = 0;
		long[] sum = new long[n];
		int[] groupsId = new int[n];
		vis = new boolean[n];
		while( !st.isEmpty() ){
			int curr = st.pop();
			if( !vis[curr] ) dfs2( vis, sum, group++, groupsId ,adjrev, curr, coins);
		}
		
		
		List<List<Integer>> adj3 = new ArrayList<>();
		for(int i = 0; i< n; i++ ){
			adj3.add(new ArrayList<>());
		}
		//Creating a DAG of a components
		for(int i = 0; i < n; i++ ){
			if( adj[i] != null ){
				for(int x: adj[i]){
					if( groupsId[x] != groupsId[i]){
						adj3.get(groupsId[i]).add(groupsId[x]);
					}
				}
			}
		}
		
		long res = Long.MIN_VALUE;
		long[] dp = new long[group];
		Arrays.fill(dp, -1);
		for(int i = 0; i< group; i++ ){
			res = Math.max( dfs3( sum, dp, adj3, i), res);
		}
		
		print(res);
		
    }
    
    static long dfs3( long[] groupSum,  long[] dp, List<List<Integer>> adj3, int id ){
		if( dp[id] != -1 ) return dp[id];
		long res = 0;
		for(int x: adj3.get(id)){
			res = Math.max( res, dfs3( groupSum, dp, adj3, x));
		}
		return dp[id] = res + groupSum[id];
	}
    
    static void dfs2( boolean[] vis, long[] sum, int group, int[] groupId, ArrayList<Integer>[] adj, int node, int[] coins){
		vis[node] = true;
		sum[group] += coins[node];
		groupId[node] = group;
		if(  adj[node] != null ){
			for(int x: adj[node]){
				if( !vis[x]){
					dfs2( vis, sum, group, groupId, adj, x, coins);
				}
			}
		}
		
	}
    
    static void dfs( boolean[] vis, ArrayList<Integer>[] adj, Stack<Integer> st, int node){
		vis[node] = true;
		if( adj[node] != null ){
			for(int x: adj[node]){
				if( !vis[x]) dfs( vis, adj, st, x);
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


