package LeetCode;

import java.util.*;

public class CountWaysToBuildRooms {

   public static void main(String[] args) {
      CountWaysToBuildRooms o = new CountWaysToBuildRooms();
      System.out.println(o.waysToBuildRooms(new int[]{-1,0,0,1,2}));
   }

   int mod = (int) 1e9 + 7;
   long[] fact;
   ArrayDeque<Integer>[] tree;
   long[] dp;
   int[] countNodes;

   public int waysToBuildRooms(int[] prevRoom) {
      int len = prevRoom.length;
      fact = new long[len];
      fact[0] = 1;
      tree = new ArrayDeque[len];
      dp = new long[len];
      countNodes = new int[len];
      for (int i = 1; i < len; i++) {
         fact[i] = mul(i, fact[i - 1]);
      }

      for (int i = 1; i < len; i++) {
         if (tree[prevRoom[i]] == null)
            tree[prevRoom[i]] = new ArrayDeque<>();
         tree[prevRoom[i]].add(i);
      }

      dfs(0);
      return (int) dp[0];
   }

   void dfs(int node) {
      if (tree[node] == null) {
         // If it is a leaf
         countNodes[node] = 1;
         dp[node] = 1;
         return;
      }

      for (int child : tree[node]) {
         // Get the cound of all the nodes
         dfs(child);
         countNodes[node] += countNodes[child];
      }

      // Find the dp
      dp[node] = 1;
      int total = countNodes[node];
      for (int child : tree[node]) {
         dp[node] = mul(dp[node], mul(dp[child], nCr(total, countNodes[child])));
         total -= countNodes[child];
      }
      countNodes[node]++;
   }

   long nCr(int n, int r) {
      long ans = fact[n];
      long denom = mul(fact[n - r], fact[r]);
      return mul(ans, pow(denom, mod - 2));
   }

   long pow(long num, long n) {
      long ans = 1;
      while (n > 0) {
         if ((n & 1) == 1) {
            // Odd
            ans = mul(ans, num);
         }
         num = mul(num, num);
         n >>= 1;
      }
      return ans;
   }

   long mul(long a, long b) {
      return (a * b) % mod;
   }
}
