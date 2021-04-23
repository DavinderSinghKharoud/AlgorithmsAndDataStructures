package GoogleKickStart.RoundB2021.C;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.*;

public class Solution implements Runnable {

   MillerRabin millerRabin = new MillerRabin();

   void solve() throws IOException {
      int t = ri();
      for (int tt = 1; tt <= t; tt++) {
         long target = rl();

         long start = 0, end = (long) 1e9 + 1;
         long ans = 6;
         while (start <= end) {
            long diff = end - start;
            diff >>= 1;
            long mid = start + diff;

            long curr = evaluate(mid);
            if (curr <= target) {
               ans = max(ans, curr);
               start = mid + 1;
            } else {
               end = mid - 1;
            }
         }

         gPrint(tt, ans);

      }
   }

   long evaluate(long num) {
      long prime1 = findNextPrime(num);
      long prime2 = findNextPrime(prime1 + 1);
      return prime1 * prime2;
   }

   long findNextPrime(long num) {
      while (!millerRabin.isPrime(num)) {
         num++;
      }
      return num;
   }

   class MillerRabin {

      public boolean isPrime(long num) {
         if (num < 2)
            return false;

         int r = 0;
         long d = num - 1;
         while ((d & 1) == 0) {
            // Count the powers of two
            r++;
            d >>= 1;
         }

         for (int a : new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 }) {
            if (num == a)
               return true;
            if (checkComposite(num, a, d, r)) {
               return false;
            }
         }
         return true;
      }

      private boolean checkComposite(long num, long a, long d, int s) {
         long curr = binPower(a, d, num);
         if (curr == 1 || curr == num - 1)
            return false;

         for (int r = 1; r < s; r++) {
            curr = curr * curr % num;
            if (curr == num - 1)
               return false;
         }
         return true;
      }

      public long binPower(long base, long power, long mod) {
         long res = 1;
         base %= mod;
         while (power > 0) {
            if ((power & 1) == 1) {
               res = res * base % mod;
            }
            base = base * base % mod;
            power >>= 1;
         }
         return res;
      }

   }

   /************************************************************************************************************************************************/
   static void gPrint(int i, Object obj) {
      if (obj instanceof String) {
         println(String.format("Case #%d: %s", i, obj));
      } else {
         println(String.format("Case #%d: %d", i, obj));
      }
   }

   public static void main(String[] args) throws IOException {
      new Thread(null, new Solution(), "1").start();
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

   static int ri() throws IOException {
      return read.intNext();
   }

   static long rl() throws IOException {
      return Long.parseLong(read.read());
   }

   static String rs() throws IOException {
      return read.read();
   }

   static char rc() throws IOException {
      return rs().charAt(0);
   }

   static double rd() throws IOException {
      return read.doubleNext();
   }
}
