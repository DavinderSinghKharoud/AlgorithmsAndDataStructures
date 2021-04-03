package Algorithms.LeetCode;

import java.io.*;
import java.util.*;

public class KSimilarString implements Runnable {

   void solve() throws IOException {

      KSimilarString obj = new KSimilarString();
      System.out.println(obj.kSimilarity("aabc", "abca"));
   }

   public int kSimilarity(String s1, String s2) {
      int len = s1.length();
      if (s1.equals(s2))
         return 0;

      StringBuilder target = new StringBuilder(s2);
      Queue<Node> queue = new LinkedList<>();
      queue.add(new Node(new StringBuilder(s1), 0));

      Set<String> vis = new HashSet<>();
      while (!queue.isEmpty()) {
         int size = queue.size();

         for (int i = 0; i < size; i++) {
            Node curr = queue.poll();

            // Try swapping nodes that are not equal
            int index = 0;
            while (index < len && curr.sbr.charAt(index) == s2.charAt(index)) {
               index++;
            }
            char needed = target.charAt(index);
            // Find the other character which can be raplaced

            for (int j = index + 1; j < len; j++) {
               if (curr.sbr.charAt(j) == needed) {
                  // It means we can replace this
                  StringBuilder swapped = getSwapped(curr.sbr, index, j);
                  if(swapped.toString().equals(target.toString())) return curr.swaps + 1;
                  if (!vis.contains(swapped.toString())) {
                     queue.add(new Node(swapped, curr.swaps + 1));
                     vis.add(swapped.toString());
                  }
               }
            }
         }
      }

      return 0;
   }

   StringBuilder getSwapped(StringBuilder sbr, int i, int j) {
      StringBuilder ans = new StringBuilder(sbr);
      char c = sbr.charAt(i);
      ans.replace(i, i + 1, String.valueOf(sbr.charAt(j)));
      ans.replace(j, j + 1, String.valueOf(c));
      return ans;
   }

   class Node {
      StringBuilder sbr;
      int swaps = 0;

      public Node(StringBuilder s, int val) {
         sbr = s;
         swaps = val;
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new KSimilarString(), "1").start();
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
