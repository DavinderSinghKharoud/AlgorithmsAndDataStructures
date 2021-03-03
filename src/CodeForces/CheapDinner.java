import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class CheapDinner implements Runnable {
	long[] first, second, drinks, deserts;

		void solve() throws IOException {
			
			int m = read.intNext(), m2 = read.intNext(), m3 = read.intNext(), m4 = read.intNext();
			first = lArr(m); second = lArr(m2); drinks = lArr(m3); deserts = lArr(m4);


			for (int i = 0; i < m; i++) {
				first[i] = read.intNext();
			}
			
			for( int i = 0; i < m2; i++ ){
				second[i] = read.intNext();
			}

			for( int i = 0; i < m3; i++ ){
				drinks[i] = read.intNext();
			}

			for( int i = 0; i < m4; i++ ){
				deserts[i] = read.intNext();
			}

			List<Integer>[] cannotForm = new List[second.length];
			int len = read.intNext();

			Arrays.fill(cannotForm, new ArrayList<>());
			for( int i = 0; i < second.length; i++ ){
				int a = read.intNext() - 1, b = read.intNext() - 1;
				cannotForm[b].add(a);

			}

			SparseTableMin table = new SparseTableMin(first);
			long[] res = new long[second.length];
			Arrays.fill(res, Long.MAX_VALUE);
			for( int i = 0; i < second.length; i++ ){
					List<Integer> curr = cannotForm[i];
					int previous = 0;
					for(int node: curr){
						res[i] = min(res[i], table.rangeMinQuery(node - 1, 0));
						previous = node;

					}

			}

			for( int i = 0; i < second.length; i++ ){
				print(res[i] + " ");
			}


			
		
		}


		/************************************************************************************************************************************************/
		public static void main(String[] args) throws IOException {
				new Thread(null, new CheapDinner(), "1").start();
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


			public class SparseTableMin {

					long[][] dp;

					public SparseTableMin(long[] arr){
							int log = log2(arr.length);

							dp = new long[arr.length][log + 1];

							for( int i = 0; i < arr.length; i++ ){
									dp[i][0] = arr[i];
							}

							//construct a sparse table

							for( int i = 1; i <= log ; i++ ){
									for(int j = 0; j + (1 << i) <= arr.length; j++){
											dp[j][i] = Math.min(dp[j][i - 1], dp[j + (1 << (i - 1) )][i - 1]);
									}
							}

					}

					public long rangeMinQuery(int low, int high ){
							if( low > high ) return Long.MAX_VALUE;
							int len = high - low + 1;
							int k = log2(len);

							if( dp[low][k] <= dp[high - (1 << k) + 1][k] ){
									return dp[low][k];
							}

							return dp[high - ( 1<< k) + 1][k];
					}

					private int log2(int len) {
							 return 64 - Long.numberOfLeadingZeros(len);
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

