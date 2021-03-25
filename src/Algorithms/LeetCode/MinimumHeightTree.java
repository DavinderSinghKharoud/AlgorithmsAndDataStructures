package Algorithms.LeetCode;

import java.util.*;

public class MinimumHeightTree {

   public static void main(String[] args) {

      MinimumHeightTree minimumHeightTree = new MinimumHeightTree();
      //System.out.println(minimumHeightTree.findMinHeightTrees(4, new int[][] { { 1, 0 }, { 1, 2 }, { 1, 3 } }));
      System.out.println(minimumHeightTree.findMinHeightTrees(3, new int[][] { { 1, 0 }, { 0, 2 }}));
      //System.out.println(minimumHeightTree.findMinHeightTrees(8, new int[][] { { 0,1 }, { 1, 2 }, {  2,3 }, { 1, 4 }
            //  , { 4, 5 }, { 1, 6 }, { 6, 7 }}));

   }

   List<Integer> res = new ArrayList<>();

   public List<Integer> findMinHeightTrees(int n, int[][] edges) {
      if (n == 1) {
         res.add(0);
         return res;
      }

      List<Integer>[] tree = new List[n];
      int[] count = new int[n];

      Arrays.setAll(tree, o -> new ArrayList<>());
      for (int[] edge : edges) {
         int u = edge[0], v = edge[1];
         tree[u].add(v);
         tree[v].add(u);
         count[u]++;
         count[v]++;
      }

      Queue<Integer> queue = new LinkedList<>();
      Set<Integer> set = new HashSet<>();

      for (int i = 0; i < n; i++) {
         if (count[i] == 1) {
            // Leaf node
            set.add(i);
            queue.add(i);
         }
      }


      while (queue.size() > 1 && set.size() != n) {
         int len = queue.size();
         for (int i = 0; i < len; i++) {
            int curr = queue.poll();
            for (int adj : tree[curr]) {
               count[adj]--;
               if ( (count[adj] == 0 || count[adj] == 1)  && !set.contains(adj)) {
                  queue.add(adj);
                  set.add(adj);
               }
            }
         }
      }

      while (!queue.isEmpty()) {
         res.add(queue.poll());
      }
      return res;
   }

}
