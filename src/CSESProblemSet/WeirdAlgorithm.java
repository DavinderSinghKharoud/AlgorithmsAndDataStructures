
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * Consider an algorithm that takes as input a positive integcr n
 * n	
 * . If n
 * n
 *  is even, the algorithm divides it by two, and if n
 * n
 *  is odd, the algorithm multiplies it by three and adds one. The algorithm repeats this, until n
 * n
 *  is one. For example, the sequence for n=3
 * n	
 * =
 * 3
 *  is as follows:
 * 3→10→5→16→8→4→2→1
 * 3
 * →
 * 10
 * →
 * 5
 * →
 * 16
 * →
 * 8
 * →
 * 4
 * →
 * 2
 * →
 * 1
 *
 * Your task is to simulate the execution of the algorithm for a given value of n
 * n
 * .
 *
 * Input
 *
 * The only input line contains an integer n
 * n
 * .
 *
 * Output
 *
 * Print a line that contains all values of n
 * n
 *  during the algorithm.
 *
 * Constraints
 * 1≤n≤106
 * 1
 * ≤
 * n
 * ≤
 * 10
 * 6
 *
 * Example
 *
 * Input:
 * 3
 *
 * Output:
 * 3 10 5 16 8 4 2 1
 */
public class WeirdAlgorithm {

    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {

        FastReader fastReader = new FastReader();
		
		long limit = fastReader.nextInt();
		out.print( limit + " ");
		
		while( limit != 1 ){
			if( (limit & 1) == 0){
				limit /= 2;
			}else{
				limit *= 3;
				limit++;
			}
			
			out.print(limit + " ");
		}
		
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
