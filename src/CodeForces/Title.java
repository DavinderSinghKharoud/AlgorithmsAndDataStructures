package CodeForces;

import java.io.*;
import java.util.*;

public class Title implements Runnable {

   void solve() throws IOException {
      int k = ri();
      StringBuilder s = new StringBuilder(rs());

      boolean[] vis = new boolean[k];
      ArrayDeque<Character> stack = new ArrayDeque<>();
      for (int i = 0; i < s.length(); i++) {
         if (s.charAt(i) != '?') {

            vis[s.charAt(i) - 'a'] = true;
         }
      }

      for (int i = 0; i < vis.length; i++) {
         if (!vis[i])
            stack.addLast((char) (i + 'a'));
      }

      // Start from the middle and put the greatest character not visited. when all
      // characters used, put a afterwards
      int mid = s.length() / 2;
      if (s.length() % 2 == 0) {
         mid--;
      }
      while (mid >= 0) {
         char c = s.charAt(mid);
         int opposite = s.length() - mid - 1;
         char second = s.charAt(opposite);
         if (c == '?' && second == '?') {
            char replaced = 'a';
            if (!stack.isEmpty()) {
               replaced = stack.pollLast();
            }
            s = new StringBuilder(s.substring(0, mid) + replaced + s.substring(mid + 1, s.length()));
         } else if (c == '?') {
            s = new StringBuilder(s.substring(0, mid) + second + s.substring(mid + 1, s.length()));
         } else if (c != '?' && second != '?') {
            if (c != second) {
               println("IMPOSSIBLE");
               return;
            }
         }
         mid--;
      }

      if (!stack.isEmpty()) {

         println("IMPOSSIBLE");
         return;
      }
      mid = s.length() / 2;
      if (s.length() % 2 == 0) {
         mid--;
      }
      for (int i = mid + 1; i < s.length(); i++) {
         if (s.charAt(i) == '?') {
            int opposite = s.length() - i - 1;
            s = new StringBuilder(s.substring(0, i) + s.charAt(opposite) + s.substring(i + 1, s.length()));
         }
      }

      println(s);
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new Title(), "1").start();
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
