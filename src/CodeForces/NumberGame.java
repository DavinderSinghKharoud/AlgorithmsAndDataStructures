package CodeForces;

import java.io.*;
import java.util.*;

/**
 * Case 1 1 : n is odd Here Ashishgup can divide 𝑛 n by itself, since it is odd
 * and hence 𝑛𝑛=1 n n = 1 , and FastestFinger loses. Here 𝑛=1 n = 1 is an
 * exception.
 * 
 * Case 2 2 : 𝑛 n is even and has no odd divisors greater than 1 1 Here 𝑛 n is
 * of the form 2𝑥 2 x . As 𝑛 n has no odd divisors greater than 1 1 ,
 * Ashishgup is forced to subtract it by 1 1 making 𝑛 n odd. So if 𝑥>1 x > 1 ,
 * FastestFinger wins. For 𝑥=1 x = 1 , 𝑛−1 n − 1 is equal to 1 1 , so
 * Ashishgup wins.
 * 
 * Case 3 3 : 𝑛 n is even and has odd divisors If 𝑛 n is divisible by 4 4 then
 * Ashishgup can divide 𝑛 n by its largest odd factor after which 𝑛 n becomes
 * of the form 2𝑥 2 x where 𝑥>1 x > 1 , so Ashishgup wins.
 * 
 * Otherwise 𝑛 n must be of the form 2⋅𝑝 2 ⋅ p , where 𝑝 p is odd. If 𝑝 p is
 * prime, Ashishgup loses since he can either reduce 𝑛 n by 1 1 or divide it by
 * 𝑝 p both of which would be losing for him. If 𝑝 p is not prime then 𝑝 p
 * must be of the form 𝑝1⋅𝑝2 p 1 ⋅ p 2 where 𝑝1 p 1 is prime and 𝑝2 p 2 is
 * any odd number >1 > 1 . Ashishgup can win by dividing 𝑛 n by 𝑝2 p 2 .
 */
public class NumberGame implements Runnable {

   void solve() throws IOException {

      int t = ri();
      while (t-- > 0) {
         int n = ri();

         boolean loose = (n == 1);

         if (n > 2 && n % 2 == 0) {
            if ((n & (n - 1)) == 0) {
               // If it is exact power of two
               loose = true;
            } else if ((n % 4) != 0 && checkPrime(n / 2)) {
               loose = true;
            }
         }

         if (loose) {
            bWin();
         } else {
            aWin();
         }
      }
   }

   boolean checkPrime(int n) {

      for (int i = 2; i <= (int) Math.sqrt(n); i++) {
         if (n % i == 0) {
            return false;
         }
      }
      return true;
   }

   void aWin() {
      println("Ashishgup");
   }

   void bWin() {
      println("FastestFinger");
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new NumberGame(), "1").start();
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

   static final class Comparators {
      public static final Comparator<int[]> pairIntArr = (x, y) -> x[0] == y[0] ? compare(x[1], y[1])
            : compare(x[0], y[0]);

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

   static int ri() throws IOException {
      return read.intNext();
   }

   static long rl() throws IOException {
      return Long.parseLong(read.read());
   }

   static String rs() throws IOException {
      return read.read();
   }

   static double rd() throws IOException {
      return read.doubleNext();
   }
}
