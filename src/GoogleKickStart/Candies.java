package GoogleKickStart;

import java.io.*;
import java.util.*;

public class Candies implements Runnable {

   void solve() throws IOException {
      int t = ri();
      for (int tt = 1; tt <= t; tt++) {
         int len = ri(), q = ri();
         int[] arr = iArr(len);
         long[] ms = lArr(len), prefix = lArr(len);
         for (int i = 0; i < len; i++) {
            arr[i] = ri();
         }

         preCalculate(arr, prefix, ms);
         long ans = 0;
         while (q-- > 0) {
            char c = rc();
            int l = ri() - 1, r = ri() - 1;
            if (c == 'Q') {
               boolean isOdd = (l & 1) == 1;
               long curr = ms[r];
               if (l - 1 >= 0)
                  curr -= ms[l - 1];
               // Minus the prefix sum * count
               curr -= (l) * (prefix[r] - ((l - 1 >= 0) ? prefix[l - 1] : 0));
               if (isOdd)
                  ans -= curr;
               else
                  ans += curr;
            } else {
               arr[l] = r + 1;
               preCalculate(arr, prefix, ms);
            }
         }
         println(String.format("Case #%s: %s", tt, ans));
      }
   }

   void preCalculate(int[] arr, long[] prefix, long[] ms) {
      int len = arr.length;
      for (int i = 0; i < len; i++) {
         if (i == 0) {
            ms[i] = prefix[i] = arr[i];
         } else {
            ms[i] = 1;
            prefix[i] = arr[i];
            if ((i & 1) == 1) {
               ms[i] = -1;
               prefix[i] *= -1;
            }
            ms[i] *= (long) (i + 1) * arr[i];
            ms[i] += ms[i - 1];

            // For prefix sum
            prefix[i] += prefix[i - 1];
         }
      }

   }

   /************************************************************************************************************************************************/

   void solveSegTree() throws IOException {
      int t = ri();
      for (int tt = 1; tt <= t; tt++) {
         int n = ri(), q = ri();
         long[] prefix = lArr(n), ms = lArr(n);
         int[] arr = iArr(n);
         for (int i = 0; i < n; i++) {
            arr[i] = ri();
            int negation = ((i & 1) == 1) ? -1 : 1;
            ms[i] = (long) (i + 1) * arr[i] * negation;

            // Fill the prefix sum
            prefix[i] = negation * arr[i];
         }

         SegmentTree segMs = new SegmentTree(ms), segPrefix = new SegmentTree(prefix);
         long score = 0;
         while (q-- > 0) {
            char c = rc();
            if (c == 'Q') {
               int l = ri() - 1, r = ri() - 1;
               int negation = ((l & 1) == 1) ? -1 : 1;
               long ans = segMs.query(l, r);

               long pref = l * segPrefix.query(l, r);
               score += negation * (ans - pref);
            } else {
               // For update
               int index = ri() - 1, val = ri();
               int negation = ((index & 1) == 1) ? -1 : 1;
               segMs.update(index, negation * (index + 1) * val);
               segPrefix.update(index, negation * val);
            }
         }

         println(String.format("Case #%s: %s", tt, score));
      }
   }

   class SegmentTree {

      long[] arr;
      long[] nodes;
      int n;

      public SegmentTree(long[] nodes) {
         arr = new long[(getSize(nodes.length) * 2 + 1)];
         n = nodes.length;
         this.nodes = nodes;
         construct(0, n - 1, 0);
      }

      void update(int node, int value) {
         update(0, n - 1, 0, node, value);
      }

      private void update(int l, int h, int pos, int node, int value) {
         if (l == h) {
            arr[pos] = value;
         } else {
            int mid = l + (h - l) / 2;
            int p = pos << 1;
            if (node <= mid) {
               update(l, mid, p + 1, node, value);
            } else {
               update(mid + 1, h, p + 2, node, value);
            }

            arr[pos] = arr[p + 1] + arr[p + 2];
         }

      }

      long query(int left, int right) {
         return query(0, n - 1, 0, left, right);
      }

      private long query(int start, int end, int pos, int left, int right) {
         if (start > right || end < left)
            return 0;

         if (start >= left && end <= right) {
            return arr[pos];
         } else {
            int mid = start + (end - start) / 2;
            int p = pos << 1;
            return query(start, mid, p + 1, left, right) + query(mid + 1, end, p + 2, left, right);
         }
      }

      void construct(int l, int h, int pos) {
         if (l == h) {
            arr[pos] = nodes[l];
         } else {
            int mid = l + (h - l) / 2;
            int p = pos << 1;
            construct(l, mid, p + 1);
            construct(mid + 1, h, p + 2);

            arr[pos] = arr[p + 1] + arr[p + 2];
         }
      }

      int getSize(int len) {
         if (len < 2)
            return 1;

         if ((len & (len - 1)) == 0)
            return len;

         while ((len & (len - 1)) != 0) {
            len = len & (len - 1);
         }

         return len << 1;
      }

   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new Candies(), "1").start();
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
         solveSegTree();
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
