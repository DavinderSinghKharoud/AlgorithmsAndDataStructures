
import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * You are given an array of n
 * n
 *  integers. You want to modify the array so that it is increasing, i.e., every element is at least as large as the previous element.
 *
 * On each turn, you may increase the value of any element by one. What is the minimum number of turns required?
 *
 * Input
 *
 * The first input line contains an integer n
 * n
 * : the size of the array.
 *
 * Then, the second line contains n
 * n
 *  integers x1,x2,…,xn
 * x
 * 1
 * ,
 * x
 * 2
 * ,
 * …
 * ,
 * x
 * n
 * : the contents of the array.
 *
 * Output
 *
 * Print the minimum number of turns.
 *
 * Constraints
 * 1≤n≤2⋅105
 * 1
 * ≤
 * n
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * 1≤xi≤109
 * 1
 * ≤
 * x
 * i
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 5
 * 3 2 5 1 7
 *
 * Output:
 * 5
 * Introductory Problems
 *
 * Weird Algorithm
 * Missing Number
 * Repetitions
 * Increasing Array
 * Permutations
 * Number Spiral
 * Two Knights
 * Two Sets
 * ...
 * Your submissions
 *
 * 2020-12-19 06:02:13
 * 2020-12-19 05:18:18
 */
public class IncreasingArray {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

	//O(n) Time complexity and O(1) space complexity
    public static void main(String[] args) {

        int tests = fastReader.nextInt();
        long steps = 0;

        Long previous = null;
        while (tests-- > 0) {
            if (previous == null) {
                previous = fastReader.nextLong();
            } else {
                long next = fastReader.nextLong();
                if (next - previous < 0) {
                    steps += Math.abs( next - previous );
                }

                previous = Math.max(previous, next);
            }
        }

        out.print( steps );
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
