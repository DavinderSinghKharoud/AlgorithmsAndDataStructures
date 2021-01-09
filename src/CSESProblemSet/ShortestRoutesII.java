import java.io.*;
import java.util.*;

/**
 * There are n
 * n
 *  cities and m
 * m
 *  roads between them. Your task is to process q
 * q
 *  queries where you have to determine the length of the shortest route between two given cities.
 *
 * Input
 *
 * The first input line has three integers n
 * n
 * , m
 * m
 *  and q
 * q
 * : the number of cities, roads, and queries.
 *
 * Then, there are m
 * m
 *  lines describing the roads. Each line has three integers a
 * a
 * , b
 * b
 *  and c
 * c
 * : there is a road between cities a
 * a
 *  and b
 * b
 *  whose length is c
 * c
 * . All roads are two-way roads.
 *
 * Finally, there are q
 * q
 *  lines describing the queries. Each line has two integers a
 * a
 *  and b
 * b
 * : determine the length of the shortest route between cities a
 * a
 *  and b
 * b
 * .
 *
 * Output
 *
 * Print the length of the shortest route for each query. If there is no route, print −1
 * −
 * 1
 *  instead.
 *
 * Constraints
 * 1≤n≤500
 * 1
 * ≤
 * n
 * ≤
 * 500
 *
 * 1≤m≤n2
 * 1
 * ≤
 * m
 * ≤
 * n
 * 2
 *
 * 1≤q≤105
 * 1
 * ≤
 * q
 * ≤
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
 * 1≤c≤109
 * 1
 * ≤
 * c
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 4 3 5
 * 1 2 5
 * 1 3 9
 * 2 3 3
 * 1 2
 * 2 1
 * 1 3
 * 1 4
 * 3 2
 *
 * Output:
 * 5
 * 5
 * 8
 * -1
 * 3
 * Graph Algorithms
 *
 * ...
 * Round Trip
 * Monsters
 * Shortest Routes I
 * Shortest Routes II
 * High Score
 * Flight Discount
 * Cycle Finding
 * Flight Routes
 * ...
 * Your submissions
 *
 * 2021-01-09 18:47:25
 * 2021-01-09 18:44:37
 * 2021-01-09 18:32:40
 * 2021-01-09 18:32:24
 * 2021-01-09 18:30:20
 * 2021-01-09 18:29:34
 * 2021-01-09 18:26:43
 * 2021-01-09 18:21:26
 */
public class ShortestRoutesII {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int)1e9 + 7;
    static long dmax = (long) 1e15 ;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {
		int n = fastReader.intNext();
		int m = fastReader.intNext();
		int q = fastReader.intNext();
		
		long[][] dis = new long[n][n];;
		for(int i = 0; i < n; i++ ){
			Arrays.fill( dis[i], dmax);
		}
		while( m --> 0 ){
			int a = fastReader.intNext();
			int b = fastReader.intNext();
			int w = fastReader.intNext();
			--a;--b;
			dis[a][b] = Math.min(w, dis[a][b]);
			dis[b][a] = Math.min(w, dis[b][a]);
		}
		
		//Use Floyd Warshall to create dp of shortest path between each node
		
		
		
		for(int a = 0; a < n; a++ ){
			dis[a][a] = 0;
		}
		
		for(int intermediate = 0; intermediate < n; intermediate++ ){
			for(int b = 0; b < n; b++ ){
				
				for(int a = 0; a < n; a ++ ){
						dis[a][b] = Math.min( dis[a][b], dis[a][intermediate] + dis[intermediate][b]);	
				}
			}
		}
		
		while( q --> 0 ){
			int a = fastReader.intNext();
			int b = fastReader.intNext();
			--a;--b;
			println( (dis[a][b] == dmax)? -1: dis[a][b] );
		}
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
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


