
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * Here 𝑚𝑖𝑛𝐷𝑖𝑔𝑖𝑡(𝑥)
 *  and 𝑚𝑎𝑥𝐷𝑖𝑔𝑖𝑡(𝑥)
 *  are the minimal and maximal digits in the decimal representation of 𝑥
 * x
 *  without leading zeroes. For examples refer to notes.
 *
 * Your task is calculate 𝑎𝐾
 * a
 * K
 *  for given 𝑎1
 * a
 * 1
 *  and 𝐾
 * K
 * .
 *
 * Input
 * The first line contains one integer 𝑡
 * t
 *  (1≤𝑡≤1000
 * 1
 * ≤
 * t
 * ≤
 * 1000
 * ) — the number of independent test cases.
 *
 * Each test case consists of a single line containing two integers 𝑎1
 * a
 * 1
 *  and 𝐾
 * K
 *  (1≤𝑎1≤1018
 * 1
 * ≤
 * a
 * 1
 * ≤
 * 10
 * 18
 * , 1≤𝐾≤1016
 * 1
 * ≤
 * K
 * ≤
 * 10
 * 16
 * ) separated by a space.
 *
 * Output
 * For each test case print one integer 𝑎𝐾
 * a
 * K
 *  on a separate line.
 */
public class SequenceWithDigits {

    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {

        FastReader fastReader = new FastReader();

		
		int tests = fastReader.nextInt();
		while( tests --> 0 ){

            out.println(find( fastReader.nextLong(), fastReader.nextLong()) );
		}
        out.close();
    }
    
    static long find( long a, long k ){

		while( k-- > 1 ){
			
			long max = a % 10;
			long min = a % 10;
			long temp = a;
			
			while( temp > 0 ){
				long last = temp % 10;
				min = Math.min(min, last );
				max = Math.max( max, last );
				temp /= 10;
			}
			
			if( min == 0 || max == 0 ){
				return a;
			}
			
			a = a + (min * max);
		}
		
		return a;
	
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
