
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 9
 *
 *
 */
public class QueenPlacement {

    static PrintWriter out = new PrintWriter(System.out);

	static int count = 0;
    public static void main(String[] args) {

        FastReader fastReader = new FastReader();

		int len = fastReader.nextInt();
		boolean[] col = new boolean[len];
		boolean[] dia1 = new boolean[len * len], dia2 = new boolean[len * len];
		
		search( 0, col, dia1, dia2 );
		out.println(count);
		out.close();
    }
    
    static void search( int row, boolean[] col, boolean[] dia1, boolean[] dia2 ){
		if( row == col.length ){
			count++;
		}else{
			
			for(int currCol = 0; currCol < col.length; currCol++ ){
				if( col[currCol] || dia1[row + currCol] || dia2[row - currCol + col.length - 1]) continue;
				col[currCol] = dia1[row + currCol] = dia2[row - currCol + col.length - 1] = true;
				search( row + 1, col, dia1, dia2 );
				col[currCol] = dia1[row + currCol] = dia2[row - currCol + col.length - 1] = false;
			}
			
			
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
