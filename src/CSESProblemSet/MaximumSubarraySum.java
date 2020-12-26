
import java.io.*;
import java.util.*;

public class MaximumSubarraySum {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

		int len = fastReader.nextInt();
		
		long max = -1;
		
		Long previous = null;
		
		
		while( len -- > 0 ){
			long curr = fastReader.nextLong();
			
			if( previous == null ){
				previous = curr;
				max = previous;
			}else{
				long best = Math.max( curr, previous + curr);
				previous = best;
				max = Math.max( max, best);
			}
		}
		
		out.print( max );
		
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
