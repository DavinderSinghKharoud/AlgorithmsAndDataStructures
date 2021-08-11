
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class CoinPiles {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    
    public static void main(String[] args) {

		int tests = fastReader.nextInt();
		
		while( tests --> 0 ){
			long first = fastReader.nextLong();
			long second = fastReader.nextLong();
			
			if( first < second ){
				 first = first ^ second;
				 second = first ^ second;
				 first = first ^ second;
			 }
			
			if( first > 2 * second ){
				out.println("NO");
			}else{
				if( (first + second) % 3 == 0 ){
					out.println("YES");
				}else{
					out.println("NO");
				}
				
			}
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
