package LeetCode;

import java.util.*;

public class StoneGameVII {

   public static void main(String[] args) {
      StoneGameVII o = new StoneGameVII();
      System.out.println(o.stoneGameVII(new int[]{7,90,5,1,100,10,10,2}));
   }

   int[][] dp = new int[1001][1001];

   public int stoneGameVII(int[] stones) {
      return solve(stones, 0, stones.length - 1, Arrays.stream(stones).sum());
   }

   int solve(int[] stones, int start, int end, int sum) {
      if (stones.length == 0 || start > end)
         return 0;
      if (dp[start][end] != 0)
         return dp[start][end];
      int left = sum - stones[start] - solve(stones, start + 1, end, sum - stones[start]);
      int right = sum - stones[end] - solve(stones, start, end - 1, sum - stones[end]);

      return dp[start][end] = Math.max(left, right);
   }
}
