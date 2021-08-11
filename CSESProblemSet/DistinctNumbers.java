

import java.io.*;
import java.util.*;

/**
 * You are given a list of n
 * n
 *  integers, and your task is to calculate the number of distinct values in the list.
 *
 * Input
 *
 * The first input line has an integer n
 * n
 * : the number of values.
 *
 * The second line has n
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
 * .
 *
 * Output
 *
 * Print one integers: the number of distinct values.
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
 * 2 3 2 2 3
 *
 * Output:
 * 2
 */
public class DistinctNumbers {

    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {

        FastReader fastReader = new FastReader();
        
        int tests = fastReader.nextInt();
        List<Integer> lst = new ArrayList<>();
        while( tests --> 0 ){
			lst.add( fastReader.nextInt());
		}
		
		Collections.sort(lst);
		
		int count = 1;
		int previous = lst.get(0);
		for(int num: lst ){
			if( previous == num ) continue;
			count++;
			previous = num;
		}
		
		out.print(count);

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
