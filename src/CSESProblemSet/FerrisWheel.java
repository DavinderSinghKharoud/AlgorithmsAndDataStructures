
import java.io.*;
import java.util.*;

public class FerrisWheel {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

        int tests = fastReader.nextInt();
        int k = fastReader.nextInt();
        int[] arr = new int[tests];

        for (int i = 0; i < tests; i++) {
            arr[i] = fastReader.nextInt();
        }

        Arrays.sort(arr);

        int start = 0;
        int count = 0;
        for (int end = arr.length - 1; end >= 0 && start <= end; end--) {
            if (arr[start] + arr[end] <= k) {
                start++;
                count++;
            } else {
                count++;
            }

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
