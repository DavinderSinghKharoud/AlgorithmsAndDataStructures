
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class AppleDivision {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    
    public static void main(String[] args) {
	
		int size = fastReader.nextInt();
		int[] arr = new int[size];
		long total = 0;	
		for(int index = 0; index < size; index++ ){
			arr[index] = fastReader.nextInt();
			total += arr[index];
		}


		int ans = Integer.MAX_VALUE;
		//Create all the subsets usign bit mask
		
		for(int index = 0; index < ( 1 << size); index++ ){
			long curr = 0;
			
			for(int index2 = 0; index2 < arr.length; index2++ ){
				if( ( index & (1 << index2) ) != 0 ){
					curr += arr[index2];
				}
			}

			ans = (int) Math.min( ans, Math.abs((total - curr) - curr));
		}
		
		out.print(ans);
		
		
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
