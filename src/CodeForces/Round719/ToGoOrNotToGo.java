package CodeForces.Round719;

import java.io.*;
import java.util.*;

public class ToGoOrNotToGo implements Runnable {
   int[][] arr;
   boolean[][] vis;
   int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
   int n, m;
   long w;

   void solve() throws IOException {
      n = ri();
      m = ri();
      w = ri();

      arr = new int[n][m];
      vis = new boolean[n][m];

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            arr[i][j] = ri();
         }
      }

      // Try simple bfs to reach the end point if possible
      long ans = bfs(0, 0, n - 1, m - 1);

      // Another possibility would be to try portals
      // We find the shortest path from starting to all portals and vice versa for
      // ending

      long startShortest = bfsShortest(0, 0);
      long endShortest = bfsShortest(n - 1, m - 1);
      if (startShortest == Long.MAX_VALUE || endShortest == Long.MAX_VALUE) {
         println((ans == Long.MAX_VALUE) ? -1 : ans);
         return;
      }
      ans = Math.min(ans, startShortest + endShortest);
      println(ans);
   }

   long bfsShortest(int i, int j) {
      vis = new boolean[n][m];
      long shortest = Long.MAX_VALUE;

      ArrayDeque<Node> queue = new ArrayDeque<>();
      queue.add(new Node(i, j, 0));
      while (!queue.isEmpty()) {
         Node curr = queue.pollFirst();
         if (arr[curr.i][curr.j] > 0) {
            // If it is a portal
            shortest = Math.min(curr.dis + arr[curr.i][curr.j], shortest);
         }
         for (int[] dir : dirs) {
            int x = curr.i + dir[0], y = curr.j + dir[1];
            if (isValid(x, y) && !vis[x][y] && arr[x][y] != -1) {
               vis[x][y] = true;
               queue.add(new Node(x, y, curr.dis + w));
            }
         }
      }
      return shortest;
   }

   long bfs(int i1, int j1, int i2, int j2) {
      ArrayDeque<Node> queue = new ArrayDeque<>();
      queue.add(new Node(i1, j1, 0));
      while (!queue.isEmpty()) {
         Node curr = queue.pollFirst();
         if (curr.i == i2 && curr.j == j2)
            return curr.dis;
         for (int[] dir : dirs) {
            int x = curr.i + dir[0], y = curr.j + dir[1];
            if (isValid(x, y) && !vis[x][y] && arr[x][y] != -1) {
               vis[x][y] = true;
               queue.add(new Node(x, y, curr.dis + w));
            }
         }
      }
      return Long.MAX_VALUE;
   }

   boolean isValid(int i, int j) {
      return i >= 0 && j >= 0 && i < n && j < m;
   }

   class Node {
      int i, j;
      long dis = 0;

      public Node(int ii, int jj, long d) {
         i = ii;
         j = jj;
         dis = d;
      }

   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new ToGoOrNotToGo(), "1").start();
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
