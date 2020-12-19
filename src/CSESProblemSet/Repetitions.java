
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * You are given a DNA sequence: a string consisting of characters A, C, G, and T. Your task is to find the longest repetition in the sequence. This is a maximum-length substring containing only one type of character.
 *
 * Input
 *
 * The only input line contains a string of n
 * n
 *  characters.
 *
 * Output
 *
 * Print one integer: the length of the longest repetition.
 *
 * Constraints
 * 1≤n≤106
 * 1
 * ≤
 * n
 * ≤
 * 10
 * 6
 *
 * Example
 *
 * Input:
 * ATTCGGGA
 *
 * Output:
 * 3
 */
public class Repetitions {

    static PrintWriter out = new PrintWriter(System.out);
	static FastReader fastReader = new FastReader();
	
	//Time complexity O(n) and Space complexity O(1)
    public static void main(String[] args) {
        
		
		String input = fastReader.next();
		
		int max = 0;
		int count = 0;
		Character previous = null;
		for(char c: input.toCharArray() ){
			if( previous == null ){
				previous = c;
				count++;
			}else{
				if( c == previous ){
					count++;
				}else{
					max = Math.max( max, count );
					count = 1;
					previous = c;
				}
			}
		}
		
		out.print( Math.max( max, count) );
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
