
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class GridPaths {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    
	static int count = 0;
    public static void main(String[] args) {
		
		int len = 7;
		
		String path = fastReader.next();
		boolean[][] visited = new boolean[len][len];
		
		dfs( path, 0, 0, 0 , visited );
		
		out.print(count);
        out.close();
    }
    
    static int[][] direc = new int[][]{ {1, 0}, {0, 1}, {-1, 0},{0, -1} };
    
    static void dfs( String path,int index, int row, int col,  boolean[][] visited ){
		
		if( index > path.length() && row == visited.length - 1 && col == 0 ) {
			count++;
			return;
		}
		
		
		if( row < 0 || col < 0 || row >= visited.length || col >= visited.length || visited[row][col] || index >= path.length() ) return;
		
		visited[row][col] = true;
		char c = path.charAt(index);
		if( c != '?' ){
		    int modR = row;
		    int modC = col;
			if( c == 'R' ) modC++;
			else if( c == 'D' ) modR++;
			else if( c == 'U' ) modR--;
			else modC--;
			
			dfs( path, index + 1, modR, modC, visited );
		}else{
			for( int[] dir: direc ){
				dfs( path, index + 1, row + dir[0], col + dir[1], visited );
			}
		}
		
		visited[row][col] = false;
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
