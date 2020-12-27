
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

		long max = 1;
		while (true){
		    long sum = 0;
		    for(int num: arr) sum += max/num;
		    if( sum >= products) break;
		    max *= 2;
        }
		int time = 0;

		for(long jump = max/2; jump >= 1; jump /= 2 ){
			while ( jump + time <= max){
			    long sum = 0;
			    for(int num: arr) sum += ( jump + time)/num;
			    if( sum >= products) break;
			    time += jump;
            }
		}

		out.print(time + 1);

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
