package GoogleKickStart.RoundC2021;

import java.io.*;
import java.util.*;

public class BinaryOperator implements Runnable {

   Map<Pair, Integer> hashes = new HashMap<>();

   void solve() {
      int t = ri();
      mod = dprimes[dprimes.length - 1];
      for (int tt = 1; tt <= t; tt++) {
         print("Case #" + tt + ": ");
         int n = ri();
         Map<Integer, Integer> map = new HashMap<>();
         int id = 1;
         int ans[] = iArr(n);
         hashes.clear();
         for (int i = 0; i < n; i++) {
            String s = rs();
            int val = getVal(s, 0)[0];
            if (!map.containsKey(val))
               map.put(val, id++);
            ans[i] = map.get(val);

         }
         for (int i = 0; i < n; i++) {
            print(ans[i] + " ");
         }
         println("");
      }
   }

   int[] getVal(String s, int index) {
      if (s.charAt(index) == '(') {
         int[] left = getVal(s, index + 1);
         char ope = s.charAt(left[1]);
         int[] right = getVal(s, left[1] + 1);
         int nextIndex = right[1] + 1;
         int ans = -1;
         if (ope == '+') {
            ans = add(left[0], right[0]);
         } else if (ope == '-') {
            ans = sub(left[0], right[0]);
         } else if (ope == '*') {
            ans = mul(left[0], right[0]);
         } else {
            ans = hash(left[0], right[0]);
         }

         return new int[] { ans, nextIndex };
      } else {
         // If it is a number
         long num = 0;
         while (index < s.length() && Character.isDigit(s.charAt(index))) {
            num = ((num * 10) + (s.charAt(index++)) - '0') % mod;
         }
         return new int[] { (int) num, index };
      }
   }

   Random random = new Random();

   int hash(int a, int b) {
      Pair curr = new Pair(a, b);
      if (!hashes.containsKey(curr)) {
         hashes.put(curr, getRandom());
      }
      return hashes.get(curr);
   }

   int getRandom() {
      int ans = random.nextInt(dmax);
      for (int i = 0; i < 5; i++) {
         ans = mul(ans, random.nextInt(dmax));
      }
      return ans;
   }

   int mul(long a, long b) {
      return (int) ((a * b) % mod);
   }

   int add(long a, long b) {
      return (int) ((a + b) % mod);
   }

   int sub(long a, long b) {
      return (int) ((a - b) % mod);
   }

   class Pair {
      int a, b;

      public Pair(int a, int b) {
         this.a = a;
         this.b = b;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o)
            return true;
         if (o == null || getClass() != o.getClass())
            return false;
         Pair pair = (Pair) o;
         return a == pair.a && b == pair.b;
      }

      @Override
      public int hashCode() {
         return Objects.hash(a, b);
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new BinaryOperator(), "1").start();
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
