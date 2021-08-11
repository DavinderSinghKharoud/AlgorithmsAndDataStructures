package CodeForces;

import java.io.*;
import java.util.StringTokenizer;

/**
 *
 Scrooge McDuck keeps his most treasured savings in a home safe with a combination lock. Each time he wants to put there the treasures that he's earned fair and square, he has to open the lock.


 The combination lock is represented by n rotating disks with digits from 0 to 9 written on them. Scrooge McDuck has to turn some disks so that the combination of digits on the disks forms a secret combination. In one move, he can rotate one disk one digit forwards or backwards. In particular, in one move he can go from digit 0 to digit 9 and vice versa. What minimum number of actions does he need for that?

 Input
 The first line contains a single integer n (1 ≤ n ≤ 1000) — the number of disks on the combination lock.

 The second line contains a string of n digits — the original state of the disks.

 The third line contains a string of n digits — Scrooge McDuck's combination that opens the lock.

 Output
 Print a single integer — the minimum number of moves Scrooge McDuck needs to open the lock.
 */
public class CombinationLock {

    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {

        FastReader fastReader = new FastReader();

        int disks = fastReader.nextInt();
        String start = fastReader.nextLine();
        String end = fastReader.nextLine();

        int steps = 0;
        for (int index = 0; index < start.length(); index++) {
            int char1 = start.charAt(index) - '0';
            int char2 = end.charAt(index) - '0';
            if( char1 < char2 ){
                int temp  = char1;
                char1 = char2;
                char2 = temp;
            }
            steps += Math.min( char1 - char2, char2 + 1 + ( 9 - char1) );

        }
        out.print(steps);
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
