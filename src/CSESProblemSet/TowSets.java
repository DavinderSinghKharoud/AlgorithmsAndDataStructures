
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class TowSets {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    
    public static void main(String[] args) {


		int n = fastReader.nextInt();
		
		long total = (long) n * (n + 1)/ 2;
		if( ( total & 1 ) != 0 ){
			out.print("NO");
		}else{
			out.println("YES");
			List<Integer> lst1 = new ArrayList<>(), lst2 = new ArrayList<>();
			
			total /= 2;
			while( n > 0 ){
				if( total - n >= 0 ){
					lst1.add(n);
					total -= n;
				}else{
					lst2.add(n);
					
				}
				n--;
			}
			
			out.println(lst1.size());
			for(int num: lst1 ){
				out.print( num + " ");
			}
			out.println( "\n" + lst2.size());
			for(int num: lst2 ){
				out.print( num + " ");
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
