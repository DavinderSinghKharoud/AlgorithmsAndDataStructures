import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.util.*;

public class Test {

    // for fast output printing  : use printwriter or stringbuilder
    // remember to close pw using pw.close()
    static StringBuilder sb = new StringBuilder();
    static long seive_size = (long) 1e6;
    static String alpha = "abcdefghijklmnopqrstuvwxyz";
    static ArrayList<Integer> primes = new ArrayList<>();
    static boolean[] seive_set = new boolean[(int) seive_size+1];
    static int n, m, k;
    static ArrayList<Integer>[] adj;
    static boolean[] vis;
    static ArrayDeque<Integer> q = new ArrayDeque<>();
    static ArrayDeque<Pair> qq = new ArrayDeque<>();
    static final long MOD = 998244353;
    static int[] dx = new int[] {1, 0, -1, 0, 1, -1, 1, -1};
    static int[] dy = new int[] {0, 1, 0, -1, -1, 1, 1, -1};
    static long[][] arr;
    static int[] rank;
    static int[] parent;

    static int[] a;
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        MyScanner sc = new MyScanner();
        n = sc.nextInt();
        int x = sc.nextInt();
        a = new int[n+1];
        boolean[] tails = new boolean[n+1];
        for(int i = 1; i <= n; i++) {
            int xx = sc.nextInt();
            a[xx] = i;
            if(xx == 0) {
                tails[i] = true;
            }
        }
        int offset = 1;
        boolean[] possible = new boolean[n+1];
        possible[0] = true;
        for(int i = 1; i <= n; i++) {
            if(tails[i]) {
                int len = 0;
                int pos = i;
                boolean flag = false;
                while(pos > 0) {
                    len++;
                    if(pos == x) {
                        offset = len;
                        flag = true;
                    }

                    pos = a[pos];
                }
                if(!flag) {
                    for(int j = n-len; j >= 0; j--) {
                        if(possible[j]) {
                            possible[j+len] = true;
                        }
                    }
                }

            }
        }
        for(int i = 0; i < n; i++) {
            if(possible[i]) {
                out.println((i + offset));
            }
        }
        out.close();
    }
    public static class Triplet implements Comparable<Triplet> {
        int x;
        int y;
        int z;
        Triplet(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public int compareTo(Triplet o) {
            return Integer.compare(this.x, o.x);
        }
    }
    public static class Pair implements Comparable<Pair>  {
        int f;
        int s;
        Pair(int f, int s) {
            this.f = f;
            this.s = s;
        }
        public int compareTo(Pair o) {
            return Integer.compare(this.f,  o.f);
        }
    }
    public static class LongPair implements Comparable<LongPair>{
        long f;
        long s;
        LongPair(long f, long s) {
            this.f = f;
            this.s = s;
        }
        public int compareTo(LongPair o) {
            return Long.compare(this.f, o.f);
        }
    }
    public static void init(int n) {
        adj = new ArrayList[n+1];
        vis = new boolean[n+1];
        parent = new int[n+1];
        rank = new int[n+1];
        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            parent[i] = i;
            rank[i] = 0;
        }
    }
    // print string "s" multiple times
    // prefer to use this function for multiple printing
    public static String mp(String s, int times) {
        return String.valueOf(new char[times]).replace("\0", s);
    }
    // take log with base 2
    public static long log2(long k) {
        return 63-Long.numberOfLeadingZeros(k);
    }

    // using lambda function for sorting
    public static void lambdaSort() {
        Arrays.sort(arr, (a, b) -> Double.compare(a[0], b[0]));
    }

    // (n choose k) = (n/k) * ((n-1) choose (k-1))
    public static long choose(long n, long k) {
        return (k == 0) ? 1 : (n*choose(n-1, k-1))/k;
    }

    // just for keeping gcd function for other personal purposes
    public static long gcd(long a, long b) {
        return (a == 0) ? b : gcd(b%a, a);
    }

    public static long max(long... as) {
        long max = Long.MIN_VALUE;
        for (long a : as) max = Math.max(a, max);
        return max;
    }

    public static long min(int... as) {
        long min = Long.MAX_VALUE;
        for (long a : as) min = Math.min(a, min);
        return min;
    }

    public static long modpow(long x, long n, long mod) {
        if(n == 0) return 1%mod;
        long u = modpow(x, n/2, mod);
        u = (u*u)%mod;
        if(n%2 == 1) u = (u*x)%mod;
        return u;
    }
    // ======================= binary search (lower and upper bound) =======================
    public static int lowerBound(long[] a, int x) {
        int lo = 0;
        int hi = a.length-1;
        int ans = -1;
        while(lo <= hi) {
            int mid = (lo+hi)/2;
            if(x < a[mid]) {
                hi = mid-1;
            } else if(x > a[mid]) {
                lo = mid+1;
            } else if(lo != hi) {
                hi = mid-1; 		// for first occurrence
                ans = mid;
            } else {
                return mid;
            }
        }
        return ans;
    }

    public static int upperBound(long[] a, long x) {
        int lo = 0;
        int hi = a.length-1;
        int ans = -1;
        while(lo <= hi) {
            int mid = (lo+hi)/2;
            if(x < a[mid]) {
                hi = mid-1;
            } else if(x > a[mid]) {
                lo = mid+1;
            } else if(lo != hi) {
                lo = mid+1;			// for last occurrence
                ans = mid;
            } else {
                return mid;
            }
        }
        return ans;
    }
    // ================================================================

    // ================== SEIVE OF ERATOSTHENES =======================
    // Complexity : O(N * log(log(N)))       ( almost O(N) )
    public static void generatePrimes() {
        // set.add(0);
        // set.add(1);
        Arrays.fill(seive_set, true);
        seive_set[0] = false;
        seive_set[1] = false;
        for(int i = 2; i <= seive_size; i++) {
            if(seive_set[i]) {
                for(long j = (long) i*i; j <= seive_size; j+=i)
                    seive_set[(int)j] = false;
                primes.add(i);
            }
        }
    }

    public static boolean isPrime(long N) {
        if(N <= seive_size) return seive_set[(int)N];
        for (int i = 0; i < (int)primes.size(); i++)
            if (N % primes.get(i) == 0) return false;
        return true;
    }
    // ===========================================================

    // ================ Permutation of String ====================
    public static void permute(String str) {
        permute(str, 0, str.length()-1);
    }
    public static void permute(String str, int l, int r)
    {
        if (l == r)
            System.out.println(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }
    public static String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
    // Union-find
    // static int[] parent, rank;
//	public static void makeSet(int n) {
//
//		parent = new int[n+1];
//		rank = new int[n+1];
//		for(int i = 1; i <= n; i++) {
//			parent[i] = i;
//			rank[i] = 0;
//		}
//	}
    public static int find(int u) {

        if(parent[u] == u)	return u;

        int v = find(parent[u]);
        parent[u] = v;
        return v;

    }
    public static boolean connected(int u, int v) {
        return find(u) == find(v);
    }
    public static void Union(int u, int v) {

        int x = find(u);				//root of u
        int y = find(v);				//root of v

        if(x == y)		return;
        if(rank[x] == rank[y]) {
            parent[y] = x;
            rank[x]++;
        }
        else if(rank[x] > rank[y]) {
            parent[y] = x;
        }
        else {
            parent[x] = y;
        }

    }

//	public static int dijkstra(int x, int y) {
//    	int[] dist = new int[n+1];
//    	Arrays.fill(dist, Integer.MAX_VALUE);
//    	PriorityQueue<Node> q = new PriorityQueue<>();
//    	q.add(new Node(x, 0));
//    	dist[x] = 0;
//    	while(!q.isEmpty()) {
//    		Node node = q.poll();
//    		int u = node.key;
//    		if(u == y) {
//    			break;
//    		}
//    		for(int v : res[u]) {
//    			if(dist[v] > dist[u]+count[u]) {
//    				dist[v] = dist[u] + count[u];
//    				q.add(new Node(v, dist[v]));
//    			}
//    		}
//    	}
//    	if(dist[y] == Integer.MAX_VALUE) {
//    		return -1;
//    	}
//    	return dist[y];
//    }

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;
        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {while (st == null || !st.hasMoreElements()) {
            try {st = new StringTokenizer(br.readLine());}
            catch (IOException e) {e.printStackTrace();}}
            return st.nextToken();}
        int nextInt() {return Integer.parseInt(next());}
        long nextLong() {return Long.parseLong(next());}
        double nextDouble() {return Double.parseDouble(next());}
        String nextLine(){String str = "";
            try {str = br.readLine();}
            catch (IOException e) {e.printStackTrace();}
            return str;}
        int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        int[] nextIntArray(int n, int delta) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt() + delta;
            return a;
        }

        long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }
    }

}