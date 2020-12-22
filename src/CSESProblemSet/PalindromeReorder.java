
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * Given a string, your task is to reorder its letters in such a way that it becomes a palindrome (i.e., it reads the same forwards and backwards).
 *
 * Input
 *
 * The only input line has a string of length n
 * n
 *  consisting of characters A–Z.
 *
 * Output
 *
 * Print a palindrome consisting of the characters of the original string. You may print any valid solution. If there are no solutions, print "NO SOLUTION".
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
 * AAAACACBA
 *
 * Output:
 * AACABACAA
 */
public class PalindromeReorder {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    
    public static void main(String[] args) {
		
		create( fastReader.next() );
        out.close();
    }

    //O(n) Time complexity but String concatination make it worst, O(1) Space complexity
    static void create( String s ){
		int oddCount = 0;
		int[] arr = new int[26];
		
		for(char c: s.toCharArray() ){
			arr[c - 'A']++;
		}
		
		List<Character> odd = new ArrayList<>(), even = new ArrayList<>();
		
		for(int index = 0; index < arr.length; index++ ){
				if( arr[index] % 2 != 0 ){
				    oddCount++;
				    if( oddCount > 1 ){
				        out.println("NO SOLUTION");return;
                    }
                    
                    while( arr[index] -- > 0 ){
						odd.add((char)(index + 'A') );
					}
				}else{
					arr[index] /= 2;
					while( arr[index] --> 0 ){
						even.add( (char) (index + 'A' ));
					}
				}
		
		}
		
		for(char c: even ){
			out.print(c);
		}
		
		for(char c: odd){
			out.print(c);
		}
		
		for(int index = even.size() - 1; index >= 0;index-- ) out.print(even.get(index)	);
		
		
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
