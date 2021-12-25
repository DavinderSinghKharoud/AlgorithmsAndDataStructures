package LeetCode.BiweeklyContest59;

import java.util.*;

public class WaysToArriveDestination {
   public static void main(String[] args) {
      WaysToArriveDestination o = new WaysToArriveDestination();
      System.out.println(o.countPaths(7, new int[][] { { 0, 6, 7 }, { 0, 1, 2 }, { 1, 2, 3 }, { 1, 3, 3 }, { 6, 3, 3 },
            { 3, 5, 1 }, { 6, 5, 1 }, { 2, 5, 1 }, { 0, 4, 5 }, { 4, 6, 2 } }));
   }

   int mod = (int) 1e9 + 7;

   public int countPaths(int n, int[][] roads) {
      int paths = 0;
      long[] dis = new long[n];
      long[] ways = new long[n];
      Arrays.fill(dis, Long.MAX_VALUE);
      ways[0] = 1;
      dis[0] = 0;

      ArrayDeque<Edge>[] tree = new ArrayDeque[n];
      for (int[] road : roads) {
         int a = road[0], b = road[1], time = road[2];
         if (tree[a] == null)
            tree[a] = new ArrayDeque<>();
         if (tree[b] == null)
            tree[b] = new ArrayDeque<>();
         tree[a].add(new Edge(b, time));
         tree[b].add(new Edge(a, time));
      }

      PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.time));
      pq.add(new Node(0, 0));

      while (!pq.isEmpty()) {
         Node curr = pq.poll();
         if (tree[curr.node] != null) {
            for (Edge neigh : tree[curr.node]) {
               long total = curr.time + neigh.time;
               if (total < dis[neigh.node]) {
                  dis[neigh.node] = total;
                  ways[neigh.node] = ways[curr.node];
                  pq.add(new Node(neigh.node, total));
               } else if (total == dis[neigh.node]) {
                  ways[neigh.node] = (ways[neigh.node] + ways[curr.node]) % mod;
               }
            }
         }
      }
      return (int) (ways[n - 1]);
   }

   static class Node {
      int node;
      long time;

      public Node(int n, long t) {
         node = n;
         time = t;
      }
   }

   static class Edge {
      int node;
      int time;

      public Edge(int n, int time) {
         node = n;
         this.time = time;
      }

   }
}
