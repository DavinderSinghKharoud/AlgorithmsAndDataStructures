package GoogleKickStart.RoundG;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class BananaBunches implements Runnable {

   void solve() {
      int t = ri();
      dmax = (int) (1e6);
      for (int tt = 1; tt <= t; tt++) {
         print("Case #" + tt + ": ");

         int n = ri(), k = ri();
         int[] arr = iArr(n);
         for (int i = 0; i < n; i++)
            arr[i] = ri();

         int[] best = iArr(k + 1);
         Arrays.fill(best, dmax);
         best[0] = 0;

         int ans = dmax;
         for (int index = n - 1; index >= 0; index--) {
            // Move to the left and try to see if any possibility exist
            int sum = 0;
            for (int j = index; j >= 0; j--) {
               sum += arr[j];
               if (sum <= k) {
                  ans = min(ans, index - j + 1 + best[k - sum]);
               }else break;
            }

            // Save all the possible sums for the right
            sum = 0;
            for (int j = index; j < n; j++) {
               sum += arr[j];
               if (sum <= k) {
                  best[sum] = min(best[sum], j - index + 1);
               }else break;
            }
         }

         println((ans > 5000) ? -1 : ans);
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new BananaBunches(), "1").start();
   }

   static PrintWriter out = new PrintWriter(System.out);
   static Reader read = new Reader();
   static StringBuilder sbr = new StringBuilder();
   static int mod = (int) 1e9 + 7;
   static int dmax = Integer.MAX_VALUE;
   static long lmax = Long.MAX_VALUE;
   static int dmin = Integer.MIN_VALUE;
   static long lmin = Long.MIN_VALUE;
   static int[] dprimes = new int[] { 1, 11, 101, 1087, 99991, 100001, 1000003, 15485863, 999999937 };

   @Override
   public void run() {
      solve();
      out.close();
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
            } else
               throw new InputMismatchException();
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
            } else
               throw new InputMismatchException();
         }
         if (n == '.') {
            n = scan();
            double temp = 1;
            while (!isWhiteSpace(n)) {
               if (n >= '0' && n <= '9') {
                  temp /= 10;
                  doub += (n - '0') * temp;
                  n = scan();
               } else
                  throw new InputMismatchException();
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

   // Gives strict lowerBound that previous number would be smaller than the target
   int lowerBound(int[] arr, int val) {
      int l = 0, r = arr.length - 1;
      while (l < r) {
         int mid = (r + l) >> 1;
         if (arr[mid] >= val) {
            r = mid;
         } else
            l = mid + 1;
      }
      return l;
   }

   // Gives strict upperBound that next number would be greater than the target
   int upperBound(int[] arr, int val) {
      int l = 0, r = arr.length - 1;
      while (l < r) {
         int mid = (r + l + 1) >> 1;
         if (arr[mid] <= val) {
            l = mid;
         } else
            r = mid - 1;
      }
      return l;
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

   static int ri() {
      try {
         return read.intNext();
      } catch (IOException e) {
         e.printStackTrace();
      }
      System.exit(2);
      return -1;
   }

   static long rl() {
      try {
         return Long.parseLong(read.read());
      } catch (IOException e) {
         e.printStackTrace();
      }
      System.exit(2);
      return -1;
   }

   static String rs() {
      try {
         return read.read();
      } catch (IOException e) {
         e.printStackTrace();
      }
      System.exit(2);
      return "";
   }

   static char rc() {
      return rs().charAt(0);
   }

   static double rd() {
      try {
         return read.doubleNext();
      } catch (IOException e) {
         e.printStackTrace();
      }
      System.exit(2);
      return -1;
   }
}
