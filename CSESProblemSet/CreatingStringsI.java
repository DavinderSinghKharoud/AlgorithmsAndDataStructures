
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * Given a string, your task is to generate all different strings that can be created using its characters.
 *
 * Input
 *
 * The only input line has a string of length n
 * n
 * . Each character is between a–z.
 *
 * Output
 *
 * First print an integer k
 * k
 * : the number of strings. Then print k
 * k
 *  lines: the strings in alphabetical order.
 *
 * Constraints
 * 1≤n≤8
 * 1
 * ≤
 * n
 * ≤
 * 8
 *
 * Example
 *
 * Input:
 * aabac
 *
 * Output:
 * 20
 * aaabc
 * aaacb
 * aabac
 * aabca
 * aacab
 * aacba
 * abaac
 * abaca
 * abcaa
 * acaab
 * acaba
 * acbaa
 * baaac
 * baaca
 * bacaa
 * bcaaa
 * caaab
 * caaba
 * cabaa
 * cbaaa
 */
public class CreatingStringsI {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();
    static int count = 0;
    public static void main(String[] args) {
		
		String user = fastReader.next();
		int[] arr = new int[26];
		for(char c: user.toCharArray() ){
			arr[c - 'a']++;
		}

		//out.println( findCount(user.length(), arr));
		//traverse( arr, new StringBuilder(), user.length());
		findPermutations( user.toCharArray(), 0 );
        out.close();
    }

	private static void findPermutations( char[] arr, int index ){
		
		Set<String> set = new HashSet<>();
		permute( arr, index, set );
		
		out.println( set.size() );
		for(String s: set ){
			out.println( s );
		}
		
	}
	
	private static void permute( char[] arr, int start, Set<String> set ){
		if( start == arr.length - 1 ){
			set.add( String.valueOf(arr) );
		}else{
			
			for(int index = start; index < arr.length; index++ ){
				swap( arr, start, index);
				permute( arr, start + 1, set );
				swap( arr, start, index );
			}
		}
	}
	
	private static void swap( char[] arr, int index1, int index2 ){
		char temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
    //O(n!) Time complexity and O(len) Space complexity
    private static int findCount(int length, int[] arr) {
        long total = findFactorial( length );
        for(int num: arr ){
            if( num > 1 ){
                total /= findFactorial( num );
            }
        }

        return (int) total;
    }

    private static long findFactorial(int num) {
        long fac = 1;
        while( num != 1 ){
            fac *= num--;
        }
        return fac;
    }


    static void traverse( int[] arr, StringBuilder sbr, int len ){
		if( sbr.length() == len ){
			out.println( sbr.toString() );
			count++;
		}else{
			
			for(int index = 0; index < arr.length; index++ ){
				if( arr[index] > 0 ){
					sbr.append((char)(index + 'a'));
					arr[index]--;
					traverse( arr, sbr, len );
					arr[index]++;
					sbr.setLength( sbr.length() - 1 );
				}
			}
		}
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
