
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class TrailingZeroes {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    
    public static void main(String[] args) {
		
		int user = fastReader.nextInt();
		int count = 0;
		
		for( int num = 5; user/num > 0; num *= 5 ){
			count += user /num;
		}
		
		out.print( count );
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
