package CodeForces.Round713;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.*;

public class PermutationsBySum implements Runnable {

   void solve() throws IOException {
      int t = ri();
      while (t-- > 0) {
         int len = ri(), l = ri(), r = ri(), target = ri();
         int x = r - l + 1;
         int minSum = (x * (x + 1)) / 2;
         int[] arr = iArr(x);
         for (int i = 0; i < x; i++) {
            arr[i] = i + 1;
         }

         boolean isFound = false;
         if(target < minSum){
            println(-1);
            continue;
         }
         if (minSum == target)
            isFound = true;
         // We will try increase minValue possible to largest value possible
         int i = arr.length - 1;
         while (i >= 0 && !isFound) {
            int limit = len - (arr.length - (i + 1));

            // Increase by 1 upto the limit;
            while (arr[i] < limit) {
               arr[i]++;
               minSum++;
               if (minSum == target) {
                  isFound = true;
                  break;
               }
            }

            i--;
         }

         if(!isFound){
            println(-1);
            continue;
         }
         Set<Integer> set = new HashSet<>();
         for (int num : arr) {
            set.add(num);
         }
         List<Integer> ans = new ArrayList<>();
         for (int j = 1; j <= len; j++) {
            if (!set.contains(j)) {
               ans.add(j);
            }
         }

         int j = 0;
         for (; j < (l - 1); j++) {
            sbr.append(ans.get(j)).append(" ");
         }
         for (int num : set) {
            sbr.append(num).append(" ");
         }
         for (; j < ans.size(); j++) {
            sbr.append(ans.get(j)).append(" ");
         }
         println(sbr.toString());
         sbr = new StringBuilder();
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new PermutationsBySum(), "1").start();
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
