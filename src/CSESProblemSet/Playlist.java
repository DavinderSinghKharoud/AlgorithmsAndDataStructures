
import java.io.*;
import java.util.*;

public class Playlist {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

		int len = fastReader.nextInt();
		
		int res = 0;
		int[] arr = new int[len];
		Set<Integer> set = new HashSet<>();
		int j = 0;
		
		for(int i = 0; i < len; i ++ ){
			arr[i] = fastReader.nextInt();
		}
		
		for(int index = 0; index < len; index++ ){
			while( set.contains(arr[index]) ){
				set.remove(arr[j]);
				j++;
			}
			set.add( arr[index] );
			res = Math.max( res, set.size() );
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
