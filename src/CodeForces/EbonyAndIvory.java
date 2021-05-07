package CodeForces;

import java.io.*;
import java.util.*;

/**
 * Dante is engaged in a fight with "The Savior". Before he can fight it with
 * his sword, he needs to break its shields. He has two guns, Ebony and Ivory,
 * each of them is able to perform any non-negative number of shots.
 *
 * For every bullet that hits the shield, Ebony deals a units of damage while
 * Ivory deals b units of damage. In order to break the shield Dante has to deal
 * exactly c units of damage. Find out if this is possible.
 *
 * Input The first line of the input contains three integers a, b, c (1 ≤ a, b ≤
 * 100, 1 ≤ c ≤ 10 000) — the number of units of damage dealt by Ebony gun and
 * Ivory gun, and the total number of damage required to break the shield,
 * respectively.
 *
 * Output Print "Yes" (without quotes) if Dante can deal exactly c damage to the
 * shield and "No" (without quotes) otherwise. Can Also be solve easily with
 * simple iterative as limit is small.
 */
public class EbonyAndIvory implements Runnable {

   void solve() throws IOException {
      int a = ri(), b = ri(), c = ri();
      // We can only do c amount of damage if Gcd of c is divisible by gcd(a, b)
      if (a < b)
         a = b ^ a ^ (b = a);
      GCDS gcds = findSolMin(a, b, c, 0, 0);
      // int gcd = find(b, a);
      println((gcds != null) ? "YES" : "NO");
   }

   int find(int a, int b) {
      return (a == 0) ? b : find(b % a, a);
   }

   static GCDS eucleGcds(int a, int b) {
      if (b == 0) {
         return new GCDS(1, 0, a);
      }
      GCDS curr = eucleGcds(b, a % b);
      int x = curr.y;
      int y = curr.x - curr.y * (a / b);
      curr.x = x;
      curr.y = y;
      return curr;
   }

   /*******************************************/
   static GCDS findAnySolution(int a, int b, int c) {
      GCDS curr = eucleGcds(Math.abs(a), Math.abs(b));
      if (c % curr.gcd != 0)
         return null;
      curr.x *= (c / curr.gcd);
      curr.y *= (c / curr.gcd);
      if (a < 0)
         curr.x = -curr.x;
      if (b < 0)
         curr.y = -curr.y;
      return curr;
   }

   /**************************************************/
   static void shilfSol(GCDS obj, int a, int b, int count) {
      obj.x += count * b;
      obj.y -= count * a;
   }

   static GCDS findSolMin(int a, int b, int c, int min_x, int min_y) {
      GCDS curr = findAnySolution(a, b, c);
      if (curr == null)
         return null;
      a /= curr.gcd;
      b /= curr.gcd;

      int sign_a = (a > 0) ? 1 : -1;
      int sign_b = (b > 0) ? 1 : -1;

      shilfSol(curr, a, b, (min_x - curr.x) / b);
      if (curr.x < min_x) {
         shilfSol(curr, a, b, sign_b);
      }

      shilfSol(curr, a, b, -(min_y - curr.y) / a);
      if (curr.y < min_y) {
         shilfSol(curr, a, b, -sign_a);
      }
      if (curr.x < min_x || curr.y < min_y)
         return null;
      return curr;
   }

   static class GCDS {
      int x, y, gcd;

      public GCDS(int x, int y, int gcd) {
         this.y = y;
         this.x = x;
         this.gcd = gcd;
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new EbonyAndIvory(), "1").start();
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
