package LeetCode.WeeklyContest250;

import java.util.*;

public class MaxGeneticQuery {
   public static void main(String[] args) {
       MaxGeneticQuery o = new MaxGeneticQuery();
       System.out.println(Arrays.toString(o.maxGeneticDifference(new int[]{-1, 0, 1, 1},
               new int[][]{{0, 2}, {3, 2}, {2, 5}})));
   }

   ArrayDeque<int[]>[] quer;
   ArrayDeque<Integer>[] tree;
   int[] ans;
   Bit bitRoot = new Bit();

   public int[] maxGeneticDifference(int[] parents, int[][] queries) {
      int len = parents.length;
      ans = new int[queries.length];
      tree = new ArrayDeque[len];
      quer = new ArrayDeque[parents.length];
      int root = -1;
      for (int i = 0; i < len; i++) {
         if (parents[i] != -1) {
            if (tree[parents[i]] == null)
               tree[parents[i]] = new ArrayDeque<>();
            tree[parents[i]].add(i);
         } else
            root = i;
      }

      for (int i = 0; i < queries.length; i++) {
         int[] query = queries[i];
         int num = query[0], val = query[1];
         if (quer[num] == null)
            quer[num] = new ArrayDeque<>();
         quer[num].add(new int[] { val, i });
      }
      dfs(root);
      return ans;
   }

   void dfs(int node) {
      bitRoot.update(node, 1);

      // Answer all the queries if exists
      if (quer[node] != null) {
         for (int[] query : quer[node]) {
            ans[query[1]] = bitRoot.query(query[0]);
         }
      }

      // Check all the childrens
      if (tree[node] != null) {
         for (int child : tree[node]) {
            dfs(child);
         }
      }

      bitRoot.update(node, -1);
   }

   static class Bit {
      int count = 0;
      Bit[] children = new Bit[2];

      public void update(int num, int count) {
         Bit curr = this;
         for (int i = 17; i >= 0; i--) {
            int bit = ( num >> i) & 1;
            if (curr.children[bit] == null)
               curr.children[bit] = new Bit();
            curr = curr.children[bit];
            curr.count += count;
         }
      }

      public int query(int num) {
         Bit curr = this;
         int ans = 0;
         for (int i = 17; i >= 0; i--) {
            int bit = ( num >> i) & 1;
            // Opposite for xor
            if (curr.children[1 - bit] != null && curr.children[1 - bit].count > 0) {
               ans |= (1 << i);
               curr = curr.children[1 - bit];
            } else
               curr = curr.children[bit];
         }
         return ans;
      }
   }
}
