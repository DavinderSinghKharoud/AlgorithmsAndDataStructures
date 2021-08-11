
import java.io.*;
import java.util.*;

public class FactoryMachines {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

		int count = fastReader.nextInt();
		
		int products = fastReader.nextInt();
		
		int[] arr = new int[count];
		for(int index = 0; index < count; index++ ){
			arr[index] = fastReader.nextInt();
		}


		long low = 1;
		long high = (long)1e18;
		
		while( low < high ){
			long mid = ( high - low )/2 + low;
			if( isValid(mid, arr, products)){
				high = mid;
			}else{
				low = mid + 1;
			}
		
		}
		
		out.print(low);

        out.close();
    }
    
    static boolean isValid(long time, int[] arr, int products){
		long total = 0;
		
		for(int num: arr ){
			total += (time/num);
			if( total >= products ) return true;
		}
		
		return total >= products;
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
