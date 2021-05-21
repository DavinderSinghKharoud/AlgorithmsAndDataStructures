package CodeForces.Round720;

import java.io.*;
import java.util.*;

public class NastiaPlaysWithATree implements Runnable {

   ArrayDeque<int[]> tree[];
   boolean[] isDeleted, dp;
   int answer = 0;

   boolean[] used;
   List<Integer> leaves = new ArrayList<>();
   List<int[]> bamboos = new ArrayList<>();

   void solve() throws IOException {
      int t = ri();
      while (t-- > 0) {
         int n = ri();
         tree = new ArrayDeque[n];
         leaves = new ArrayList<>();
         bamboos = new ArrayList<>();
         isDeleted = new boolean[n];
         dp = new boolean[n];

         Arrays.setAll(tree, o -> new ArrayDeque<>());
         for (int i = 0; i < n - 1; i++) {
            int a = ri() - 1, b = ri() - 1;
            tree[a].add(new int[] { b, i });
            tree[b].add(new int[] { a, i });
         }

         /**
          * First, solve the problem for all child vertices of 𝑣 v . Then define the
          * value 𝑐𝑣 c v as the number of the children and the value 𝑝𝑣 p v as the
          * ancestor for vertex 𝑣 v .
          *
          * There are 3 cases:
          *
          * If 𝑐𝑣<=1 c v <= 1 , then we don't remove anything. If 𝑐𝑣=2 c v = 2 , then
          * we remove the edge (𝑝𝑣,𝑣) ( p v , v ) if 𝑝𝑣 p v exists. If 𝑐𝑣>2 c v >
          * 2 , then we remove the edge (𝑝𝑣,𝑣) ( p v , v ) if 𝑝𝑣 p v exists and any
          * 𝑐−2 c − 2 existing edges from 𝑣 v to one of the children vertex.
          */
         dfs(0, -1);
         // if (answer == 0) {
         // println(0);
         // continue;
         // }
         used = new boolean[n];
         for (int i = 0; i < n; i++) {
            if (!used[i]) {
               dfs2(i, i);
               if (leaves.size() == 2) {
                  bamboos.add(new int[] { leaves.get(0), leaves.get(1) });
               } else if (leaves.size() == 1) {
                  bamboos.add(new int[] { leaves.get(0), leaves.get(0) });
               }
               leaves.clear();
            }
         }

         List<Edge> deletedEdges = new ArrayList<>(), addedEdges = new ArrayList<>();
         for (int node = 0; node < n; node++) {
            for (int[] child : tree[node]) {
               if (isDeleted[child[1]]) {
                  // Make sure we only select the edge single time
                  if (child[0] < node) {
                     deletedEdges.add(new Edge(node, child[0]));
                  }
               }
            }
         }

         for (int i = 1; i < bamboos.size(); i++) {
            addedEdges.add(new Edge(bamboos.get(i - 1)[0], bamboos.get(i)[1]));
         }

         sbr.append(addedEdges.size()).append("\n");
         for (int i = 0; i < addedEdges.size(); i++) {

            printNodes(deletedEdges.get(i).x + 1, deletedEdges.get(i).y + 1, addedEdges.get(i).x + 1,
                  addedEdges.get(i).y + 1);
         }
         print(sbr.toString());
         sbr = new StringBuilder();
      }
   }

   void printNodes(int x, int y, int a, int b) {
      sbr.append(x).append(" ");
      sbr.append(y).append(" ");
      sbr.append(a).append(" ");
      sbr.append(b).append("\n");
   }

   void dfs(int node, int parent) {
      int size = tree[node].size() - ((parent != -1) ? 1 : 0);
      for (int[] child : tree[node]) {
         if (child[0] == parent)
            continue;
         dfs(child[0], node);
         if (dp[child[0]]) {
            size--;
            answer++;
            isDeleted[child[1]] = true;
         }
      }

      if (size >= 2) {
         dp[node] = true;
         for (int[] child : tree[node]) {
            int adj = child[0];
            if (adj == parent)
               continue;
            if (size <= 2)
               break;
            if (!dp[adj]) {
               --size;
               answer++;
               isDeleted[child[1]] = true;
            }
         }

      }
   }

   void dfs2(int node, int root) {
      used[node] = true;
      int numberOfChildren = 0;
      for (int[] child : tree[node]) {
         int adj = child[0];
         if (used[adj] || isDeleted[child[1]])
            continue;
         numberOfChildren++;
         dfs2(adj, root);
      }

      if (node == root && numberOfChildren == 1) {
         // Root node and one children
         leaves.add(node);
      } else if (numberOfChildren == 0) {
         // End node having no childrens
         leaves.add(node);
      }
   }

   class Edge {
      int x, y;

      public Edge(int xx, int yy) {
         x = xx;
         y = yy;
      }
   }

   /************************************************************************************************************************************************/
   public static void main(String[] args) throws IOException {
      new Thread(null, new NastiaPlaysWithATree(), "1").start();
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
