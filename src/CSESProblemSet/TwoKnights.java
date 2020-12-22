
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class TwoKnights {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    
    public static void main(String[] args) {
		
		int n = fastReader.nextInt();
		
		for(int k = 1; k <= n; k++ ){
			long total = k * k;
			long secondPositions = (( total ) * ( total - 1 ) )/2;
			
			if( k >2 ){
				secondPositions -= 4 * ( k - 2 ) * ( k - 1 );
			}
			
			
			out.println( secondPositions );
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
