
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * Your task is to place eight queens on a chessboard so that no two queens are attacking each other. As an additional challenge, each square is either free or reserved, and you can only place queens on the free squares. However, the reserved squares do not prevent queens from attacking each other.
 *
 * How many possible ways are there to place the queens?
 *
 * Input
 *
 * The input has eight lines, and each of them has eight characters. Each square is either free (.) or reserved (*).
 *
 * Output
 *
 * Print one integer: the number of ways you can place the queens.
 *
 * Example
 *
 * Input:
 * ........
 * ........
 * ..*.....
 * ........
 * ........
 * .....**.
 * ...*....
 * ........
 *
 * Output:
 * 65
 */
public class ChessboardAndQueens {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    static int count = 0;
    
    public static void main(String[] args) {
		
		int len = 8;
		
		String[] arr = new String[len];
		for(int index = 0; index < len; index++ ){
			arr[index] = fastReader.next();
			
		}
		
		boolean[] col = new boolean[len];
		boolean[] dia1 = new boolean[len * len];
		boolean[] dia2 = new boolean[len * len];
		
		traverse( arr, col, dia1, dia2, 0 );
		
		out.print(count);
        out.close();
    }
    
    static void traverse( String[] arr, boolean[] col, boolean[] dia1, boolean[] dia2, int row ){
		if( row == arr.length ){
			count++;
		}else{
			
			for( int currCol = 0; currCol < col.length; currCol++ ){
				if( col[currCol] || dia1[row + currCol] || dia2[row - currCol - 1 + arr.length] || arr[row].charAt(currCol) == '*' ) continue;
				col[currCol] = dia1[row + currCol] = dia2[row - currCol - 1 + arr.length] = true;
				traverse( arr, col, dia1, dia2, row + 1 );
				col[currCol] = dia1[row + currCol] = dia2[row - currCol - 1 + arr.length] = false;
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
