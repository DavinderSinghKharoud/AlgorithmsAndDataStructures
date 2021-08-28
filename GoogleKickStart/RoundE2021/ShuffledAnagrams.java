package GoogleKickStart.RoundE2021;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class ShuffledAnagrams implements Runnable {
   int a = 'a';

   void solve() {
      int t = ri();
      for (int tt = 1; tt <= t; tt++) {
         System.out.print("Case #" + tt + ": ");
         String s = rs();
         Queue<Integer>[] count = new ArrayDeque[26];
         for (int i = 0; i < count.length; i++)
            count[i] = new ArrayDeque<>();
         int len = s.length();
         int max = 0;
         for (int i = 0; i < len; i++) {
            count[s.charAt(i) - a].add(i);
            max = max(max, count[s.charAt(i) - a].size());
         }
         if (max > len / 2) {
            System.out.println("IMPOSSIBLE");
            continue;
         }
         PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.index.size(), o1.index.size()));
         for (int i = 0; i < 26; i++) {
            if (count[i].size() > 0) {
               Node node = new Node((char) (i + a));
               node.index = count[i];
               pq.add(node);
            }
         }

         sbr = new StringBuilder(s);
         while (pq.size() > 1) {
            Node highest = pq.poll();
            Node prev = pq.poll();
            int settle = min(highest.index.size(), prev.index.size());
            // swap the characters
            for (int i = 0; i < settle; i++) {
               sbr.setCharAt(highest.index.poll(), prev.c);
               sbr.setCharAt(prev.index.poll(), highest.c);
            }
            if (highest.index.size() > 0)
               pq.add(highest);
            if (prev.index.size() > 0)
               pq.add(prev);
         }

         if (pq.size() > 0) {
            // Satisfy the odd one left
            Node node = pq.poll();
            for (int i = 0; i < len && node.index.size() > 0; i++) {
               char c = s.charAt(i);
               if (c != node.c && (sbr.charAt(i) != node.c)) {
                  // swap
                  sbr.setCharAt(node.index.poll(), sbr.charAt(i));
                  sbr.setCharAt(i, node.c);
               }
            }
         }

         for (int i = 0; i < len; i++) {
            if (s.charAt(i) == sbr.charAt(i)) {
               throw new InputMismatchException();
            }
         }
         System.out.println(sbr.toString());
         // println(sbr.toString());
      }

   }

   static class Node {
      char c;
      Queue<Integer> index = new ArrayDeque<>();

      public Node(char cc) {
         c = cc;
      }
   }

   /************************************************************************************************************************************************/

   void solve2() {
      int t = ri();
      for (int tt = 1; tt <= t; tt++) {
         print("Case #" + tt + ": ");
         String s = rs();
         int len = s.length();
         int[] count = iArr(26);
         for (int i = 0; i < len; i++)
            count[s.charAt(i) - a]++;

         int max = Arrays.stream(count).max().getAsInt();
         if (max > len / 2) {
            println("IMPOSSIBLE");
            continue;
         }

         String sorted = s.chars().sorted()
               .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
         String rotate = rotate(sorted);

         ArrayDeque<Integer>[] locations = new ArrayDeque[26];
         Arrays.setAll(locations, o -> new ArrayDeque());
         for (int i = 0; i < len; i++) {
            locations[s.charAt(i) - a].add(i);
         }

         sbr = new StringBuilder(s);
         for (int i = 0; i < len; i++) {
            char first = sorted.charAt(i), second = rotate.charAt(i);
            sbr.setCharAt(locations[first - a].pop(), second);

         }
          for (int i = 0; i < len; i++) {
              if (s.charAt(i) == sbr.charAt(i)) {
                  throw new InputMismatchException();
              }
          }
         println(sbr);
      }
   }

   String rotate(String s) {
      int half = (s.length()) / 2;
      sbr = new StringBuilder();
      for (int i = half; i < s.length(); i++) {
         sbr.append(s.charAt(i));
      }
      for (int i = 0; i < half; i++) {
         sbr.append(s.charAt(i));
      }
      return sbr.toString();
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new ShuffledAnagrams(), "1").start();
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
      //solve();
       solve2();
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
