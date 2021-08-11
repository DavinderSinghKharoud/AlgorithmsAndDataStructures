package CodeForces;
import java.io.*;
import java.util.*;

/**
 *
 You want to build a fence that will consist of 𝑛
 n
 equal sections. All sections have a width equal to 1
 1
 and height equal to 𝑘
 k
 . You will place all sections in one line side by side.

 Unfortunately, the ground beneath the fence is not flat. For simplicity, you can think that the ground level under the 𝑖
 i
 -th section is equal to ℎ𝑖
 h
 i
 .

 You should follow several rules to build the fence:

 the consecutive sections should have a common side of length at least 1
 1
 ;
 the first and the last sections should stand on the corresponding ground levels;
 the sections between may be either on the ground level or higher, but not higher than 𝑘−1
 k
 −
 1
 from the ground level ℎ𝑖
 h
 i
 (the height should be an integer);
 One of possible fences (blue color) for the first test case
 Is it possible to build a fence that meets all rules?

 Input
 The first line contains a single integer 𝑡
 t
 (1≤𝑡≤104
 1
 ≤
 t
 ≤
 10
 4
 ) — the number of test cases.

 The first line of each test case contains two integers 𝑛
 n
 and 𝑘
 k
 (2≤𝑛≤2⋅105
 2
 ≤
 n
 ≤
 2
 ⋅
 10
 5
 ; 2≤𝑘≤108
 2
 ≤
 k
 ≤
 10
 8
 ) — the number of sections in the fence and the height of each section.

 The second line of each test case contains 𝑛
 n
 integers ℎ1,ℎ2,…,ℎ𝑛
 h
 1
 ,
 h
 2
 ,
 …
 ,
 h
 n
 (0≤ℎ𝑖≤108
 0
 ≤
 h
 i
 ≤
 10
 8
 ), where ℎ𝑖
 h
 i
 is the ground level beneath the 𝑖
 i
 -th section.

 It's guaranteed that the sum of 𝑛
 n
 over test cases doesn't exceed 2⋅105
 2
 ⋅
 10
 5
 .

 Output
 For each test case print YES if it's possible to build the fence that meets all rules. Otherwise, print NO.

 You may print each letter in any case (for example, YES, Yes, yes, yEs will all be recognized as positive answer).
 */
@SuppressWarnings("unchecked")
public class BuildingAFence implements Runnable {

		void solve() throws IOException {
			int t = read.intNext();

			while(t--> 0){
				int n = read.intNext(), k = read.intNext();
				int[] arr = iArr(n);

				for(int i = 0; i < n; i++){
					arr[i] = read.intNext();
				}

				long[][] dp = new long[n][2];

				dp[0] = new long[]{arr[0], arr[0]};
				boolean isPossible = true;
				
				for(int i = 1; i < n; i++){
					
					long minPossible= max((long)arr[i], dp[i - 1][0] + 1 - k);
					long maxPossible= min(arr[i] + k - 1, dp[i - 1][1] + k - 1);
					dp[i] = new long[]{minPossible, maxPossible};

					if(minPossible > maxPossible){
						isPossible = false;
						break;
					}
				}

				if( isPossible && dp[n - 1][0] <= arr[n - 1] ){
					println("yes");
				}else{
					println("NO");
				}
				
			}
		}


		/************************************************************************************************************************************************/
		public static void main(String[] args) throws IOException {
			new Thread(null, new BuildingAFence(), "1").start();
		}

		static PrintWriter out = new PrintWriter(System.out);
		static Reader read = new Reader();
		static StringBuilder sbr = new StringBuilder();
		static int mod = (int) 1e9 + 7;
		static int dmax = Integer.MAX_VALUE;
		static long lmax = Long.MAX_VALUE;
		static int dmin = Integer.MIN_VALUE;
		static long lmin = Long.MIN_VALUE;

		@Override
		public void run() {
			try {
				solve();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		static class Reader {
			private byte[] buf = new byte[1024];
			private int index;
			private InputStream in;
			private int total;

			public Reader() {
				in = System.in;
			}

			public int scan() throws IOException {
				if (total < 0)
					throw new InputMismatchException();
				if (index >= total) {
					index = 0;
					total = in.read(buf);
					if (total <= 0)
						return -1;
				}
				return buf[index++];
			}

			public int intNext() throws IOException {
				int integer = 0;
				int n = scan();
				while (isWhiteSpace(n))
					n = scan();
				int neg = 1;
				if (n == '-') {
					neg = -1;
					n = scan();
				}
				while (!isWhiteSpace(n)) {
					if (n >= '0' && n <= '9') {
						integer *= 10;
						integer += n - '0';
						n = scan();
					} else throw new InputMismatchException();
				}
				return neg * integer;
			}

			public double doubleNext() throws IOException {
				double doub = 0;
				int n = scan();
				while (isWhiteSpace(n))
					n = scan();
				int neg = 1;
				if (n == '-') {
					neg = -1;
					n = scan();
				}
				while (!isWhiteSpace(n) && n != '.') {
					if (n >= '0' && n <= '9') {
						doub *= 10;
						doub += n - '0';
						n = scan();
					} else throw new InputMismatchException();
				}
				if (n == '.') {
					n = scan();
					double temp = 1;
					while (!isWhiteSpace(n)) {
						if (n >= '0' && n <= '9') {
							temp /= 10;
							doub += (n - '0') * temp;
							n = scan();
						} else throw new InputMismatchException();
					}
				}
				return doub * neg;
			}

			public String read() throws IOException {
				StringBuilder sb = new StringBuilder();
				int n = scan();
				while (isWhiteSpace(n))
					n = scan();
				while (!isWhiteSpace(n)) {
					sb.append((char) n);
					n = scan();
				}
				return sb.toString();
			}

			private boolean isWhiteSpace(int n) {
				if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
					return true;
				return false;
			}
		}

		static void shuffle(int[] aa) {
			int n = aa.length;
			Random rand = new Random();
			for (int i = 1; i < n; i++) {
				int j = rand.nextInt(i + 1);
				int tmp = aa[i];
				aa[i] = aa[j];
				aa[j] = tmp;
			}
		}

		static void shuffle(int[][] aa) {
			int n = aa.length;
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

		static final class Comparators {
			public static final Comparator<int[]> pairIntArr =
				(x, y) -> x[0] == y[0] ? compare(x[1], y[1]) : compare(x[0], y[0]);

			private static final int compare(final int x, final int y) {
				return Integer.compare(x, y);
			}
		}


		static void print(Object object) {
			out.print(object);
		}

		static void println(Object object) {
			out.println(object);
		}


		static int[] iArr(int len) {
			return new int[len];
		}

		static long[] lArr(int len) {
			return new long[len];
		}

		static long min(long a, long b) {
			return Math.min(a, b);
		}

		static int min(int a, int b) {
			return Math.min(a, b);
		}

		static long max(Long a, Long b) {
			return Math.max(a, b);
		}

		static int max(int a, int b) {
			return Math.max(a, b);
		}
}

