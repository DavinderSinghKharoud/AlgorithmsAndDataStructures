package CodeForces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Bachgold problem is very easy to formulate. Given a positive integer n represent it as a sum of maximum possible number of prime numbers. One can prove that such representation exists for any integer greater than 1.
 *
 * Recall that integer k is called prime if it is greater than 1 and has exactly two positive integer divisors — 1 and k.
 *
 * Input
 * The only line of the input contains a single integer n (2 ≤ n ≤ 100 000).
 *
 * Output
 * The first line of the output contains a single integer k — maximum possible number of primes in representation.
 *
 * The second line should contain k primes with their sum equal to n. You can print them in any order. If there are several optimal solution, print any of them.
 */
public class BachgoldProblem {

    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {

        FastReader fastReader = new FastReader();


        int user = fastReader.nextInt();
        int count2s;
        int count3s = 0;
        if ((user & 1) == 0) {
            count2s = user / 2;
        } else {
            count2s = (user - 3) / 2;
            count3s = 1;
        }

        out.println( count2s + count3s);
        while ( count2s --> 0 ){
            out.print("2 ");
        }

        while ( count3s --> 0 ){
            out.print("3");
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

