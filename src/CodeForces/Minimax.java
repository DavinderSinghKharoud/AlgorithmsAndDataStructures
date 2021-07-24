package CodeForces;

import java.io.*;
import java.util.*;

public class Minimax implements Runnable {

   void solve() {
      int t = ri();
      outer: while (t-- > 0) {
         String s = rs();
         int[] count = iArr(26);
         int len = s.length();
         int min = 'z';
         for (int i = 0; i < len; i++) {
            count[s.charAt(i) - 'a']++;
            min = min(min, s.charAt(i));
         }

         len = 26;
         // F(x) == 0
         for (int i = 0; i < len; i++) {
            if (count[i] == 1) {
               add(1, i);
               for (int j = 0; j < len; j++) {
                  if (j != i && count[j] > 0)
                     add(count[j], j);
               }
               sbr.append("\n");
               continue outer;
            }
         }

         // If all are same
         if (count[min - 'a'] == s.length()) {
            sbr.append(s).append("\n");
            continue;
         }

         if (count[gI(min)] <= s.length() / 2 + 1) {
            // We can make it f(x) == 1
            sbr.append((char) min);
            count[gI(min)]--;
            for (int i = 0; i < len; i++) {
               if (i > gI(min)) {
                  // Check other chars
                  char c = (char) (i + 'a');
                  while (count[i]-- > 0) {
                     if (count[gI(min)] > 0) {
                        sbr.append((char) min);
                        count[gI(min)]--;
                     }
                     sbr.append(c);
                  }
               }
            }
            if (count[gI(min)] > 0)
               sbr.append((char) min);
         } else {

            // We need to make max possible for f(x) as it min is more than half
            int sec = -1, third = -1;
            for (int i = 0; i < len; i++) {
               if (count[i] > 0 && i > gI(min)) {
                  if (sec == -1)
                     sec = i;
                  else if (third == -1)
                     third = i;
                  else
                     break;
               }
            }
            sbr.append((char) min).append((char) (sec + 'a'));
            count[gI(min)]--;
            count[sec]--;
            if (third != -1) {
               while (count[gI(min)]-- > 0)
                  sbr.append((char) min);
               sbr.append((char) (third + 'a'));
               count[third]--;
            } else {
               while (count[sec]-- > 0)
                  sbr.append((char) (sec + 'a'));
            }

            for (int i = 0; i < len; i++) {
               char c = (char) (i + 'a');
               while (count[i]-- > 0)
                  sbr.append(c);
            }

         }
          sbr.append("\n");
      }
       println(sbr);
   }

   static int gI(int i) {
      return i - 'a';
   }

   static void add(int count, int i) {
      char c = (char) (i + 'a');
      while (count-- > 0) {
         sbr.append(c);
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new Minimax(), "1", 1 << 31).start();
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
      solve();
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
