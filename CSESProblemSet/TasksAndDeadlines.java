
import java.io.*;
import java.util.*;

public class TasksAndDeadlines {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

		int tests  = fastReader.nextInt();
		
		int[][] arr = new int[tests][2];
		
		for(int index = 0; index < tests; index++ ){
			arr[index] = new int[]{fastReader.nextInt(), fastReader.nextInt()};
		}
		
		Arrays.sort(arr, Comparator.comparingInt( o -> o[0]));
		
		long res = 0;
		long time = 0;
		for( int[] curr: arr){
			time += curr[0];
			res += ( curr[1] - time);
		}
		
		out.print(res);
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
