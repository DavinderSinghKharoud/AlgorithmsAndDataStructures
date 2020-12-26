
import java.io.*;
import java.util.*;

public class SumofTwoValues {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

		int tests = fastReader.nextInt();
		int target = fastReader.nextInt();
		
		int[][] arr = new int[tests][2];
		
		for(int i = 0; i < tests; i++ ){
			arr[i] = new int[]{ fastReader.nextInt(), i};
		}
		
		Arrays.sort(arr, Comparator.comparingInt( o -> o[0]));
		
		int start = 0, end = tests - 1;
		boolean found = false;
		while( start < end ){
			int sum = arr[start][0] + arr[end][0];
			
			if( sum > target ){
				end--;
			}else if( sum < target ){
				start++;
			}else{
				found = true;
				out.print((arr[start][1]  + 1 )+ " ");
				out.print((arr[end][1] + 1 ) );
				break;
			}
		}
		
		if( !found ) out.print( "IMPOSSIBLE");
		
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
