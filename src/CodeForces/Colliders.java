package CodeForces;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Colliders implements Runnable {

   void solve() throws IOException {
      int n = ri(), m = ri();
      boolean[] on = new boolean[n + 1];
      int[] parent = iArr(n + 1);
      boolean[] gcds = new boolean[n + 1];

      Function<Integer, List<Integer>> getGcd = num -> {
         List<Integer> res = new ArrayList<>();
         if (num == 1) {
            res.add(1);
            return res;
         }
         int limit = (int) Math.sqrt(num) + 1;

         for (int i = 2; i <= limit; i++) {
            while (num % i == 0) {
               res.add(i);
               num /= i;
            }
         }
         if (num > 1)
            res.add(num);
         return res;
      };

      while (m-- > 0) {
         char c = rc();
         int curr = ri();
         if (c == '+') {
            if (on[curr]) {
               sbr.append("Already on").append("\n");
            } else {
               List<Integer> gcd = getGcd.apply(curr);
               boolean found = false;
               for (int a : gcd) {
                  if (gcds[a]) {
                     // We have found some number which does not have gcd with this number as 1
                     printConflict(parent[a]);
                     found = true;
                     break;
                  }
               }

               if (!found) {
                  // It is not conflicting with anyone
                  for (int a : gcd) {
                     gcds[a] = true;
                     parent[a] = curr;
                     on[curr] = true;
                  }
                  sbr.append("Success").append("\n");
               }
            }
         } else {
            if (!on[curr]) {
               sbr.append("Already off").append("\n");
            } else {
               List<Integer> gcd = getGcd.apply(curr);
               for (int a : gcd) {
                  gcds[a] = false;
                  parent[a] = 0;
               }
               on[curr] = false;
               sbr.append("Success").append("\n");
            }
         }
      }
      print(sbr);
   }

   void printConflict(int n) {
      sbr.append("Conflict with ").append(n).append("\n");
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new Colliders(), "1").start();
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
