package HackerRank;

import java.io.*;
import java.util.*;

public class ShortestSubSegment implements Runnable {

   void solve() {
      String root = rline();
      String s = root.toLowerCase();
      s = removeSpecialChar(s);
      s = removeExtraSpaces(s);
      int k = ri();
      Set<String> words = new HashSet<>();
      for (int i = 0; i < k; i++) {
         words.add(rs().toLowerCase());
      }

      List<String> lst = new ArrayList<>();
      sbr = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == ' ') {
            lst.add(sbr.toString());
            sbr.delete(0, sbr.length());
         } else
            sbr.append(c);
      }
      lst.add(sbr.toString());

      int start = 0;
      int ansA = -1, ansB = -1, min = dmax;
      Map<String, Integer> map = new HashMap<>();
      for (int end = 0; end < lst.size(); end++) {
         String word = lst.get(end);
         if (words.contains(word)) {
            map.put(word, map.getOrDefault(word, 0) + 1);
         }
         if (map.size() == words.size()) {

            while (map.size() >= words.size() && start <= end) {
               // Save the answer
               if (map.size() == words.size()) {
                  if (min > end - start + 1) {
                     min = end - start + 1;
                     ansA = start;
                     ansB = end;
                  }
               }
               // Remove the one from start
               String startingWord = lst.get(start);
               if (words.contains(startingWord)) {
                  // remove it
                  int count = map.get(startingWord) - 1;
                  if (count == 0)
                     map.remove(startingWord);
                  else
                     map.put(startingWord, count);
               }
               start++;
            }

         }
      }

      root = removeSpecialChar(root);
      root = removeExtraSpaces(root);
      lst.clear();
      sbr = new StringBuilder();
      for (int i = 0; i < root.length(); i++) {
         char c = root.charAt(i);
         if (c == ' ') {
            lst.add(sbr.toString());
            sbr.delete(0, sbr.length());
         } else
            sbr.append(c);
      }
      lst.add(sbr.toString());

      if (ansA != -1) {
         sbr = new StringBuilder();
         for (int i = ansA; i <= ansB; i++) {
            sbr.append(lst.get(i)).append(" ");
         }
         println(sbr);
      } else {
         println("NO SUBSEGMENT FOUND");
      }

   }

   String removeSpecialChar(String s) {
      sbr = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == ' ' || (c >= 'a' && c <= 'z' || (c >= 'A' && c <= 'Z'))) {
            sbr.append(c);
         }
      }
      return sbr.toString();
   }

   String removeExtraSpaces(String s) {
      sbr = new StringBuilder(s);
      if (sbr.charAt(0) == ' ')
         sbr.deleteCharAt(0);
      if (sbr.charAt(sbr.length() - 1) == ' ')
         sbr.deleteCharAt(sbr.length() - 1);
      s = sbr.toString();
      for (int i = 1; i < s.length(); i++) {
         if (s.charAt(i) == ' ' && s.charAt(i - 1) == ' ') {
            sbr.deleteCharAt(i);
         }
      }
      return sbr.toString();
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new ShortestSubSegment(), "1", 1 << 21).start();
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

      public String rline() throws IOException {
         StringBuilder sb = new StringBuilder();
         int n = scan();
         while (n != '\n') {
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

   static String rline() {
      try {
         return read.rline();
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
