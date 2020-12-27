
import java.io.*;
import java.util.*;

public class StickLengths {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

		int len = fastReader.nextInt();
		
		int[] arr = new int[len];
		
		for(int i = 0; i < len; i++ ){
			arr[i] = fastReader.nextInt();
			
		}
		
        Arrays.sort(arr);
        int mid = arr[len/2];
        
        long diff = 0;
        for(int num: arr ){
			diff += (long)Math.abs(num - mid);
		}
		
		out.print( diff );
        
        
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
