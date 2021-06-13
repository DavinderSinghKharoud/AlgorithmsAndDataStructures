package CodeForces;

import java.io.*;
import java.util.*;

public class PlayOffTournament implements Runnable {

   Node[] arr;
   String s;

   void solve() {
      int n = ri();
      s = rs();
      int len = s.length();
      arr = new Node[len];

      constructGraph();
      int t = ri();
      while (t-- > 0) {
         int index = ri();
         arr[index - 1].c = rc();
         update(arr[index - 1]);
         println(arr[arr.length - 1].count);
      }
   }

   void update(Node node) {
      if (node.left == null & node.right == null) {
         node.count = (node.c == '?') ? 2 : 1;
      } else {
         if (node.c == '?') {
            node.count = node.left.count + node.right.count;
         } else if (node.c == '1') {
            node.count = node.right.count;
         } else {
            node.count = node.left.count;
         }
      }
      if (node.parent != null)
         update(node.parent);
   }

   void constructGraph() {
      int currIndex = s.length() - 1;
      ArrayDeque<Node> queue = new ArrayDeque<>();
      arr[currIndex] = new Node();
      queue.add(arr[currIndex]);

      int count = 0;
      while (!queue.isEmpty()) {
         int len = queue.size();
         currIndex -= (count == 0) ? 0 : (1 << count);
         for (int i = 0; i < len; i++) {
            Node curr = queue.poll();
            curr.c = s.charAt(currIndex + i);
            arr[currIndex + i] = curr;
            if (currIndex != 0) {
               curr.left = new Node();
               curr.left.parent = curr;
               curr.right = new Node();
               curr.right.parent = curr;
               queue.add(curr.left);
               queue.add(curr.right);
            }
         }
         count++;
      }

      dfsConstruct(arr[s.length() - 1]);
   }

   void dfsConstruct(Node node) {
      if (node.left == null && node.right == null) {
         node.count = (node.c == '?') ? 2 : 1;
      } else {
         dfsConstruct(node.left);
         dfsConstruct(node.right);

         if (node.c == '?') {
            node.count = node.left.count + node.right.count;
         } else if (node.c == '1') {
            node.count = node.right.count;
         } else {
            node.count = node.left.count;
         }
      }
   }

   class Node {
      char c;
      long count;
      Node left, right, parent;
   }

   /************************************************************************************************************************************************/
   char[] str;

   public void solve2() {

      int k = ri();
      s = rs();
      int len = s.length();
      str = s.toCharArray();
      reverse(str);

      int n = 1 << k;
      int[] count = iArr(2 * n);
      Arrays.fill(count, 1);

      for (int i = n - 2; i >= 0; i--) {
         update(i, count);
      }

      int t = ri();
      while (t-- > 0) {
         int index = ri();
         char c = rc();
         int pos = n - index - 1;
         str[pos] = c;
         while (pos >= 0) {
            update(pos, count);
            if(pos == 0) break;
            pos = (pos - 1) / 2;
         }
         println(count[0]);
      }
   }

   void update(int index, int[] count) {
      char c = str[index];
      if (c == '0') {// Left
         count[index] = count[2 * index + 2];
      } else if (c == '1') {// Right
         count[index] = count[2 * index + 1];
      } else {
         count[index] = count[2 * index + 1] + count[2 * index + 2];
      }
   }

   void reverse(char[] arr) {
      int start = 0, end = arr.length - 1;
      while (start <= end) {
         char c = arr[start];
         arr[start] = arr[end];
         arr[end] = c;
         start++; end--;
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new PlayOffTournament(), "1", 1 << 31).start();
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
      // solve();
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
