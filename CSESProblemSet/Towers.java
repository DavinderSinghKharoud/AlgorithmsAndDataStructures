
import java.io.*;
import java.util.*;

/**
 * You are given n
 * n
 *  cubes in a certain order, and your task is to build towers using them. Whenever two cubes are one on top of the other, the upper cube must be smaller than the lower cube.
 *
 * You must process the cubes in the given order. You can always either place the cube on top of an existing tower, or begin a new tower. What is the minimum possible number of towers?
 *
 * Input
 *
 * The first input line contains an integer n
 * n
 * : the number of cubes.
 *
 * The next line contains n
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
 * : the sizes of the cubes.
 *
 * Output
 *
 * Print one integer: the minimum number of towers.
 *
 * Constraints
 * 1≤n≤2⋅105
 * 1
 * ≤
 * n
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
 * Example
 *
 * Input:
 * 5
 * 3 8 2 1 5
 *
 * Output:
 * 2
 */
public class Towers {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		int count = fastReader.nextInt();
		int res = 0;
		while( count --> 0 ){
			int curr = fastReader.nextInt();
			
			Integer low = map.higherKey(curr);
			if( low == null ){
				map.put(curr, map.getOrDefault(curr, 0) + 1);
				res++;
			}else{
				int value = map.get(low);
				if( value == 1 ){
					map.remove(low);
				}else{
					map.put( low, value - 1);
				}
				map.put(curr, 1);
			}
		}
		
		
		out.print( res);

        out.close();
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
