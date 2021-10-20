package GoogleKickStart.RoundG;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class StayHydrated implements Runnable {

   void solve2() {
      int t = ri();
      for (int tt = 1; tt <= t; tt++) {
         print("Case #" + tt + ": ");
         int k = ri();
         List<Integer> xs = new ArrayList<>(), ys = new ArrayList<>();
         for (int i = 0; i < k; i++) {
            xs.add(ri());
            ys.add(ri());
            xs.add(ri());
            ys.add(ri());
         }
         Collections.sort(xs);
         Collections.sort(ys);
         println(xs.get(k - 1) + " " + ys.get(k - 1));
      }
   }

   // Only solve first test case
   void solve() {
      int t = ri();
      for (int tt = 1; tt <= t; tt++) {
         print("Case #" + tt + ": ");
         int k = ri();
         Rec[] arr = new Rec[k];
         for (int i = 0; i < k; i++) {
            arr[i] = new Rec(ri(), ri(), ri(), ri());
         }

         int man = Integer.MAX_VALUE;
         int ansx = Integer.MAX_VALUE;
         int ansy = Integer.MAX_VALUE;
         // Try every point possible

         for (int i = -100; i <= 100; i++) {
            for (int j = -100; j <= 100; j++) {
               int dis = 0;
               for (Rec rec : arr) {
                  dis += distance(rec, i, j);
               }
               if (dis < man) {
                  man = dis;
                  ansx = i;
                  ansy = j;
               } else if (dis == man) {
                  if (ansx == i) {
                     if (ansy > j) {
                        ansy = j;
                     }
                  } else if (ansx > i) {
                     ansx = i;
                     ansy = j;
                  }
               }

            }
         }
         println(ansx + " " + ansy);

      }

   }

   int distance(Rec rec, int x, int y) {
      boolean isInside = (x >= rec.x1 && x <= rec.x2) && (y >= rec.y1 && y <= rec.y2);
      if (isInside)
         return 0;
      int min = dmax;
      // Find dis from all corners
      // left
      int dis = Math.abs(rec.x1 - x);
      if (y >= rec.y1 && y <= rec.y2)
         min = min(min, dis);
      // left bottom
      dis = Math.abs(rec.y1 - y);
      if (x >= rec.x1 && x <= rec.x2)
         min = min(min, dis);
      // right side
      dis = Math.abs(rec.x2 - x);
      if (y >= rec.y1 && y <= rec.y2)
         min = min(min, dis);
      // up
      dis = Math.abs(rec.y2 - y);
      if (x >= rec.x1 && x <= rec.x2)
         min = min(min, dis);
      // Try all corners;
      min = min(min, manhatten(rec.x1, rec.y1, x, y)); // left bottom
      min = min(min, manhatten(rec.x2, rec.y1, x, y));
      min = min(min, manhatten(rec.x2, rec.y2, x, y));
      min = min(min, manhatten(rec.x1, rec.y2, x, y));
      return min;
   }

   int manhatten(int x, int y, int x1, int y1) {
      return Math.abs(x - x1) + Math.abs(y - y1);
   }

   static class Rec {
      int x1, y1, x2, y2;

      public Rec(int xx, int yy, int xxx, int yyy) {
         x1 = xx;
         y1 = yy;
         x2 = xxx;
         y2 = yyy;
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new StayHydrated(), "1").start();
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
