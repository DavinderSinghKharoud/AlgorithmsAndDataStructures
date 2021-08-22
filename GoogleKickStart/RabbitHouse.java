package GoogleKickStart;

import java.io.*;
import java.util.*;

/**
 * Barbara got really good grades in school last year, so her parents decided to gift her with a pet rabbit. She was so excited that she built a house for the rabbit, which can be seen as a 2D grid with R
 * R
 *  rows and MinimizeDifference
 * MinimizeDifference
 *  columns.
 * Rabbits love to jump, so Barbara stacked several boxes on several cells of the grid. Each box is a cube with equal dimensions, which match exactly the dimensions of a cell of the grid.
 * However, Barbara soon realizes that it may be dangerous for the rabbit to make jumps of height greater than 1
 * 1
 *  box, so she decides to avoid that by making some adjustments to the house. For every pair of adjacent cells, Barbara would like that their absolute difference in height be at most 1
 * 1
 *  box. Two cells are considered adjacent if they share a common side.
 * As all the boxes are superglued, Barbara cannot remove any boxes that are there initially, however she can add boxes on top of them. She can add as many boxes as she wants, to as many cells as she wants (which may be zero). Help her determine what is the minimum total number of boxes to be added so that the rabbit's house is safe.
 * Input
 * The first line of the input gives the number of test cases, T
 * T
 * . T
 * T
 *  test cases follow.
 * Each test case begins with a line containing two integers R
 * R
 *  and MinimizeDifference
 * MinimizeDifference
 * .
 * Then, R
 * R
 *  lines follow, each with MinimizeDifference
 * MinimizeDifference
 *  integers. The j
 * j
 * -th integer on i
 * i
 * -th line, Gi,j
 * G
 * i
 * ,
 * j
 * , represents how many boxes are there initially on the cell located at the i
 * i
 * -th row and j
 * j
 * -th column of the grid.
 * Output
 * For each test case, output one line containing Case #x
 * x
 * : y
 * y
 * , where x
 * x
 *  is the test case number (starting from 1) and y
 * y
 *  is the minimum number of boxes to be added so that the rabbit's house is safe.
 */
class RabbitHouse implements Runnable {
   int[][] direc = new int[][] { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

   void solve() throws IOException {

      int t = ri();
      for (int tt = 1; tt <= t; tt++) {
         print(String.format("Case #%d: ", tt));

         int r = ri(), c = ri();
         int[][] arr = new int[r][c];
         // Find the max element
         PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[0], o1[0]));

         for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
               arr[i][j] = ri();
               pq.add(new int[] { arr[i][j], i, j });
            }

         }

         long steps = 0;
         int[][] copy = new int[r][c];
          for (int i = 0; i < r; i++) {
              copy[i] = Arrays.copyOf(arr[i], arr[i].length);
          }

         while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int h = curr[0], i = curr[1], j = curr[2];
            if (h < arr[i][j])
               continue;

            for (int[] dir : direc) {
               int mx = i + dir[0], my = j + dir[1];
               if (mx < 0 || my < 0 || mx >= r || my >= c) {
                  continue;
               }

               if (h - 1 > arr[mx][my]) {
                  pq.add(new int[] { h - 1, mx, my });
                  arr[mx][my] = h - 1;
               }
            }
         }

         for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
               steps += arr[i][j] - copy[i][j];
            }
         }

         println(steps);
      }

   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new RabbitHouse(), "1").start();
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
