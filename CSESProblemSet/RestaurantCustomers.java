
import java.io.*;
import java.util.*;

/**
 * You are given the arrival and leaving times of n
 * n
 *  customers in a restaurant.
 *
 * What was the maximum number of customers?
 *
 * Input
 *
 * The first input line has an integer n
 * n
 * : the number of customers.
 *
 * After this, there are n
 * n
 *  lines that describe the customers. Each line has two integers a
 * a
 *  and b
 * b
 * : the arrival and leaving times of a customer.
 *
 * You may assume that all arrival and leaving times are distinct.
 *
 * Output
 *
 * Print one integer: the maximum number of customers.
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
 * 1≤a<b≤109
 * 1
 * ≤
 * a
 * <
 * b
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 3
 * 5 8
 * 2 4
 * 3 9
 *
 * Output:
 * 2
 */
public class RestaurantCustomers {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {
		
		
		int count = fastReader.nextInt();
		
		int[] arrival = new int[count];
		int[] leaved = new int[count];
		
		for(int i = 0; i < count; i++ ){
			arrival[i] = fastReader.nextInt();
			leaved[i] = fastReader.nextInt();
		}
		
		Arrays.sort(arrival);
		Arrays.sort(leaved);
		
		int a = 0, d = 0, res = 0;
		int max = 0;

		while( a < count && d < count ){
			int arrive = arrival[a];
			int depart = leaved[d];
			
			if( arrive == depart ){
				a++;
				d++;
			}else if( depart < arrive ){
				max--;
				d++;
			}else{
				max++;
				a++;
			}

			res = Math.max( res, max);
		}
		
		out.println(res);


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
