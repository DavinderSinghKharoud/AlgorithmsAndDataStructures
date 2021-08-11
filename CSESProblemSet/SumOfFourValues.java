
import java.io.*;
import java.util.*;

public class SumOfFourValues {

    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();

    public static void main(String[] args) throws IOException {
		
		int count = fastReader.nextInt();
		int target = fastReader.nextInt();
		
		int[][] arr = new int[count][2];
		
		for(int index = 0; index < count; index++ ){
			arr[index] = new int[]{ fastReader.nextInt(), index + 1 };
		}

		Arrays.sort(arr, Comparator.comparingInt( o -> o[0]));
		
		boolean found = false;
		
		for(int index1 = 0; index1 < count; index1++ ){
			long temp1 = target - arr[index1][0];
			
			for(int index2 = index1 + 1; index2 < count; index2++ ){
				long temp2 = temp1 - arr[index2][0];
				
				for(int end = count - 1, start = index2 + 1; start < end; end -- ){
					
					while( start < end - 1 && arr[start][0] + arr[end][0] < temp2 ) start++;
					
					if( arr[start][0] + arr[end][0] == temp2 ){
						out.print( arr[index1][1] + " " + arr[index2][1] + " " + arr[start][1] + " " + arr[end][1] );
						found = true;
						break;
					}
				}
				
				if(found) break;
			}
			if(found) break;
		}
		
		if(!found) out.print("IMPOSSIBLE");

        out.close();
    }

/************************************************************************************************************************************************/
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
            int second =aa[i][1];

            aa[i][0] = aa[j][0];
            aa[i][1] = aa[j][1];

            aa[j][0] = first;
            aa[j][1] = second;
        }
    }
}
