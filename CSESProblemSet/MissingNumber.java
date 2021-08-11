
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * You are given all numbers between 1,2,…,n
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * n
 *  except one. Your task is to find the missing number.
 *
 * Input
 *
 * The first input line contains an integer n
 * n
 * .
 *
 * The second line contains n−1
 * n
 * −
 * 1
 *  numbers. Each number is distinct and between 1
 * 1
 *  and n
 * n
 *  (inclusive).
 *
 * Output
 *
 * Print the missing number.
 *
 * Constraints
 * 2≤n≤2⋅105
 * 2
 * ≤
 * n
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * Example
 *
 * Input:
 * 5
 * 2 3 1 5
 *
 * Output:
 * 4
 */
public class MissingNumber {

    static PrintWriter out = new PrintWriter(System.out);
	static FastReader fastReader = new FastReader();
	
    public static void main(String[] args) {
		
		int len = fastReader.nextInt();
		
		//Solution1( len, fastReader );
		//Solution2( len );
		Solution3(len);
		
        out.close();
    }
    
    static void Solution3( int len ){
		
		int xor = len;
		for(int index = 1; index < len; index++ ){
			xor ^= index;
		    xor ^= fastReader.nextInt();
			
		}
		
		out.print(xor);
	}
	//Time complexity O(n) and Space compelxity O(1)
	static void Solution2( int len ){
		//Using Gauss formula find total sum of natural numbers up to len 
		long expectedSum = (long) len * ( len + 1 )/2;
		long sum = 0;
		while( len -- > 1 ){
			sum += fastReader.nextInt();
		}
		
		out.print( expectedSum - sum );
	}
	
	//O(n) Time and Space complexity
	static void Solution1( int len, FastReader fastReader ){
		int[] arr = new int[len + 1];
		len--;
		while( len-- > 0 ){
			arr[fastReader.nextInt()]++;
		}
		
		for(int index = 1; index < arr.length; index++ ){
			if( arr[index] == 0 ) {
                out.print(index);
                break;
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
