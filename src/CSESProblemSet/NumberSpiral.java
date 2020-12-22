
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class NumberSpiral {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    
    public static void main(String[] args) {

		int tests = fastReader.nextInt();
		
		while( tests --> 0 ){
			find( fastReader.nextLong(), fastReader.nextLong() );
		}
		out.close();
    }

	static void find( long row, long col ){
		
		if( row > col ){
			if( ( row & 1 ) == 0 ){//If it is even
				out.print( row * row - col + 1);
			}else{
				row--;
				out.print( row * row + col );
			}
		}else{
			
			if( (col & 1) == 0 ){
				col --;
				out.print( col * col + row );
			}else{
				out.print( col * col - row + 1);
			}
		}
		
		out.print( " ");
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
