
import java.io.*;
import java.util.*;

public class MovieFestival {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

		int tests = fastReader.nextInt();
		
		int[][] arr = new int[tests][2];
		
		for(int i = 0; i < tests; i++ ){
			arr[i] = new int[]{fastReader.nextInt(), fastReader.nextInt() };
		}
		
		Arrays.sort( arr, Comparator.comparingInt( o -> o[1]));
		
		int end = -1;
		int count = 0;
		for(int[] time: arr ){
			if( end == -1 ){
				end = time[1];
				
			}else{
				if( time[0] < end ){
					continue;
				}
				end = time[1];
			}
			count++;
		}
		
		out.println(count);
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
