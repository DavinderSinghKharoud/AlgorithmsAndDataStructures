
import java.io.*;
import java.util.*;

public class ReadingBooks {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

		int tests = fastReader.nextInt();
		int[] arr = new int[tests];
		long sum = 0;
		
		for( int index = 0; index < tests; index++ ){
			arr[index] = fastReader.nextInt();
			sum += arr[index];
		}
		
		Arrays.sort(arr);
		
		out.print( Math.max( sum, 2 * (arr[tests - 1]) ));

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
