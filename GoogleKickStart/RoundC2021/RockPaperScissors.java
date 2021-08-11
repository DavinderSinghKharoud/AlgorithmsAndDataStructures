package GoogleKickStart.RoundC2021;

import java.io.*;
import java.util.*;

public class RockPaperScissors implements Runnable {

   int limit = 60;
   char[][][] best;
   // dp[days][r][s]
   double[][][] dp;
   boolean[][][] vis;
   int w, e;

   void solve() {
      int t = ri();
      long target = ri();

      for (int tt = 1; tt <= t; tt++) {
         print("Case #" + tt + ": ");
         sbr = new StringBuilder();
         best = new char[limit][limit][limit];
         dp = new double[limit][limit][limit];
         vis = new boolean[limit][limit][limit];
         w = ri();
         e = ri();
         solve(0, 0, 0);
         int days = 0, r = 0, s = 0;

         while (days < limit) {
            char c = best[days++][r][s];
            sbr.append(c);
            if (c == 'R')
               r++;
            else if (c == 'S')
               s++;
         }
         println(sbr.toString());
      }
   }

   double solve(int days, int r, int s) {
      if (days == limit)
         return 0;
      if (vis[days][r][s])
         return dp[days][r][s];
      vis[days][r][s] = true;
      int p = days - r - s;

      double pr, ps, pp, ans = 0;
      char c = '&';

      if (days == 0) {
         pr = ps = pp = (w + e) / 3.0;
      } else {
         pr = (double) r / days;
         ps = (double) s / days;
         pp = (double) p / days;
      }

      // Find the max probability
      // Rock
      double temp = solve(days + 1, r + 1, s) + pp * w * 1D + ps * e * 1D;
      if (temp > ans) {
         ans = temp;
         c = 'R';
      }

      // Scissor
      temp = solve(days + 1, r, s + 1) + pr * w * 1D + pp * e * 1D;
      if (temp > ans) {
         ans = temp;
         c = 'S';
      }

      // Paper
      temp = solve(days + 1, r, s) + ps * w * 1D + pr * e * 1D;
      if (temp > ans) {
         ans = temp;
         c = 'P';
      }

      best[days][r][s] = c;
      return dp[days][r][s] = ans;
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new RockPaperScissors(), "1").start();
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
