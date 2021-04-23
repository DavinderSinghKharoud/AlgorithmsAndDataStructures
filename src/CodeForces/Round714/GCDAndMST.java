package CodeForces.Round714;

import java.io.*;
import java.util.*;

public class GCDAndMST implements Runnable {

   void solve() throws IOException {
      int t = ri();
      while (t-- > 0) {
         int len = ri();
         int p = ri();

         int[][] nodes = new int[len][2];
         int[] arr = iArr(len);
         for (int i = 0; i < len; i++) {
            arr[i] = ri();
            nodes[i] = new int[] { arr[i], i };
         }

         Arrays.sort(nodes, Comparator.comparingInt(o -> o[0]));
         long ans = 0;

         UnionFind unionFind = new UnionFind(len);
         // Go through all the nodes and choose which one can be added
         for (int i = 0; i < len; i++) {
            int[] node = nodes[i];
            int cost = node[0], index = node[1];
            if (cost >= p)
               break; // We do not need to add edges further as the value will be more than standard p

            // Move left until
            // 1] If number is not divisible by current element then we do need to move
            // further because elements beyond does not matter
            // 2] If number is divisible and smaller then we do not need to move further
            // because all other elements beyond this will be divisible by this smaller
            // number
            // 3] If it is not connected
            if (unionFind.size(index) == 1) {
               for (int j = index - 1; j >= 0; j--) {
                  int v = unionFind.size( unionFind.findParent(j));
                  if (arr[j] % cost == 0) {
                     ans += cost;
                     unionFind.merge(j, index);
                  } else
                     break;
                  if (v > 1)
                     break;

               }

               // Move Right until
               // Same conditions above
               for (int j = index + 1; j < len; j++) {
                   int v = unionFind.size( unionFind.findParent(j));
                  if (arr[j] % cost == 0) {
                     ans += cost;
                     unionFind.merge(j, index);
                  } else
                     break;

                  if (v > 1)
                     break;
               }
            }
         }

         // Try all the linea nodes if possible
         for (int i = 0; i < len - 1; i++) {
            ans += unionFind.merge(i, i + 1) ? p : 0;
         }
         println(ans);
      }
   }

   static class UnionFind {
      int[] parent, size;

      public UnionFind(int len) {
         parent = iArr(len);
         Arrays.fill(parent, -1);
         size = iArr(len);
         Arrays.fill(size, 1);
      }

      int size(int node) {
         return size[node];
      }

      private int findParent(int node) {
         if (parent[node] == -1)
            return node;
         return parent[node] = findParent(parent[node]);
      }

      boolean merge(int node1, int node2) {
         int parent1 = findParent(node1), parent2 = findParent(node2);
         if (parent1 == parent2)
            return false;
         if (size[parent1] < size[parent2]) {
            parent1 = parent1 ^ parent2 ^ (parent2 = parent1);
         }

         parent[parent2] = parent1;
         size[parent1] += size[parent2];
         size[parent2] = 0;
         return true;
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new GCDAndMST(), "1").start();
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
