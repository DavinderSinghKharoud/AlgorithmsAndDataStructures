

import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * FindGreatestCommonDivisor permutation of integers 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 *  is called beautiful if there are no adjacent elements whose difference is 1
 * 1
 * .
 *
 * Given n
 * n
 * , construct a beautiful permutation if such a permutation exists.
 *
 * Input
 *
 * The only input line contains an integer n
 * n
 * .
 *
 * Output
 *
 * Print a beautiful permutation of integers 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 * . If there are several solutions, you may print any of them. If there are no solutions, print "NO SOLUTION".
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
 * Example 1
 *
 * Input:
 * 5
 *
 * Output:
 * 4 2 5 3 1
 *
 * Example 2
 *
 * Input:
 * 3
 *
 * Output:
 * NO SOLUTION
 */
 class Permutations2 {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    
    
    //Time complexity O(n) and Space complexity O(n)
    public static void main(String[] args) {

		int test = fastReader.nextInt();
		
		if( test == 1) out.print(1);
		else if( test <= 3 ) out.print( "NO SOLUTION");
		else addNumbers( test );
		
		
		
        out.close();
    }
    
    static void addNumbers( int test ){
		for(int num = 2; num <= test; num+=2 ){
			out.print( num  + " ");
		}
			
		for(int num = 1; num <= test; num += 2 ){
			out.print( num + " ");
		}
		
		
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
