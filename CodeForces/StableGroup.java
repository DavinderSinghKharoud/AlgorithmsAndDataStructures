package CodeForces;

import java.io.*;
import java.util.*;

public class StableGroup implements Runnable {

   void solve() {
      int n = ri();
      long k = rl(), x = rl();
      long[] arr = lArr(n);
      for (int i = 0; i < n; i++) {
         arr[i] = rl();
      }
      shuffleSort(arr);
      // Traverse and get the diff's for unstable groups
      List<Long> diffs = new ArrayList<>();
      for (int i = 1; i < n; i++) {
         if (arr[i] - arr[i - 1] > x) {
            diffs.add(arr[i] - arr[i - 1] - 1);
         }
      }

      Collections.sort(diffs);
      long groups = diffs.size() + 1;
      int index = 0;
      while (k > 0 && index < diffs.size()) {
         // Try to satisfy the least group
         long div = diffs.get(index) / x;
         if (k >= div) {
            k -= div;
            index++;
            groups--;
         } else
            break;
      }
      println(groups);
   }

   static void shuffleSort(long[] aa) {
      int n = aa.length;
      Random rand = new Random();
      for (int i = 1; i < n; i++) {
         int j = rand.nextInt(i + 1);
         long tmp = aa[i];
         aa[i] = aa[j];
         aa[j] = tmp;
      }
      Arrays.sort(aa);
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new StableGroup(), "1", 1 << 31).start();
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
