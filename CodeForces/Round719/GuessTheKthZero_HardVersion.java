package CodeForces.Round719;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class GuessTheKthZero_HardVersion implements Runnable {

   void solve() throws IOException {
      int n = ri(), t = ri();
      Map<Pair, Integer> map = new HashMap<>();
      while (t-- > 0) {
         int k = ri();
         int start = 1, end = n;
         while (start < end) {
            int mid = (end + start) >>> 1;
            Pair curr = new Pair(start, mid);
            if (!map.containsKey(curr)) {
               int sum = ask(start, mid);
               map.put(curr, sum);
            }
            int sum = map.get(curr);
            int zeroes = mid - start + 1 - (sum);
            if (zeroes < k) {
               start = mid + 1;
               k -= zeroes;
            } else {
               end = mid;
               map.put(curr, map.get(curr) + 1); // Because 0 value will be changed to 1 after each query
            }
         }
         System.out.println("! " + start);
         System.out.flush();
      }
   }

   int ask(int l, int r) {
      System.out.println("? " + l + " " + r);
      return ri();
   }

   class Pair {
      int l, r;

      public Pair(int ll, int rr) {
         l = ll;
         r = rr;
      }

      // Important for HashMap Implementation
      @Override
      public boolean equals(Object o) {
         if (this == o)
            return true;
         if (o == null || getClass() != o.getClass())
            return false;
         Pair pair = (Pair) o;
         return l == pair.l && r == pair.r;
      }

      @Override
      public int hashCode() {
         return Objects.hash(l, r);
      }
   }

   /************************************************************************************************************************************************/
   // We will fenwick tree to save the changes

   public void solve2() throws IOException {
      int n = ri(), t = ri();
      int[] save = new int[n + 1];
      Arrays.fill(save, -1);
      Fenwick tree = new Fenwick(new int[n]);
      Function<Integer, Integer> query = (end) -> {
         if (save[end] != -1)
            return end - (save[end] + tree.query(end));
         int sum = ask(1, end);
         save[end] = sum - tree.query(end);
         return end - sum;
      };

      while (t-- > 0) {
         int k = ri();
         int start = 1, end = n;
         while (start < end) {
            int mid = (end + start) >>> 1;
            if (query.apply(mid) < k) {
               start = mid + 1;
            } else {
               end = mid;
            }
         }
         System.out.println("! " + start);
         tree.update(start, 1);
      }
   }

   public class Fenwick {
      int[] prefix;
      int len;

      public Fenwick(int[] arr) {
         len = arr.length;
         prefix = new int[len + 1];
      }

      public void update(int index, int val) {
         index++;
         while (index <= len) {
            prefix[index] += val;
            index += (index & -index);
         }
      }

      public int query(int index) {
         index++;
         int sum = 0;
         while (index > 0) {
            sum += prefix[index];
            index -= (index & -index);
         }
         return sum;
      }

   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new GuessTheKthZero_HardVersion(), "1").start();
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
         // solve();
         solve2();
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

      public int intNext() {
         try {
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
         } catch (Exception e) {
            System.out.println(e.toString());
         }
         System.exit(1);
         return 1;
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

   static int ri() {
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
