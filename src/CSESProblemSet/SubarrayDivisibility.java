
import java.io.*;
import java.util.*;

/**
 * Time limit: 1.00 s Memory limit: 512 MB
 * Given an array of n
 * n
 *  integers, your task is to count the number of subarrays where the sum of values is divisible by n
 * n
 * .
 *
 * Input
 *
 * The first input line has an integer n
 * n
 * : the size of the array.
 *
 * The next line has n
 * n
 *  integers a1,a2,…,an
 * a
 * 1
 * ,
 * a
 * 2
 * ,
 * …
 * ,
 * a
 * n
 * : the contents of the array.
 *
 * Output
 *
 * Print one integer: the required number of subarrays.
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
 * −109≤ai≤109
 * −
 * 10
 * 9
 * ≤
 * a
 * i
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 5
 * 3 1 2 7 4
 *
 * Output:
 * 1
 */
public class SubarrayDivisibility {

    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();

    static void solve() throws IOException {
		
		int count = fastReader.nextInt();
		
		Map<Long,Integer> map = new HashMap<>();
		map.put(0L, 1);
		int divisor = count;
		long sum = 0;
		long res = 0;
		
		
		while( count --> 0 ){
			int curr = fastReader.nextInt();
			
			sum += curr;
			
			long mod = (( sum % divisor ) + divisor) % divisor;
			
			if( map.containsKey( mod ) )  res += map.get(mod );
			
			map.put(mod, map.getOrDefault(mod, 0) + 1);
		}
		
		print(res);
    }	


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
        out.close();
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {

            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;

        }

        public String next() throws IOException {

            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);

        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    static void shuffle(int[] aa, int n) {
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
    }

    static void shuffle(int[][] aa, int n) {
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int first = aa[i][0];
            int second = aa[i][1];
            aa[i][0] = aa[j][0];
            aa[i][1] = aa[j][1];
            aa[j][0] = first;
            aa[j][1] = second;
        }
    }

    static void print(Object object) {
        out.print(object);
    }

    static void println(Object object) {
        out.println(object);
    }
}
