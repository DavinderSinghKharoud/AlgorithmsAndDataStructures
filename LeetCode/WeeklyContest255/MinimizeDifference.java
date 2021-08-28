package LeetCode.WeeklyContest255;

import java.util.*;

public class MinimizeDifference {
   public static void main(String[] args) {
      MinimizeDifference o = new MinimizeDifference();
      System.out.println(o.minimizeTheDifference(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }, 13));

   }

   int ans = Integer.MAX_VALUE;
   int n, m, target;
   int[][] dp, mat;
   int len = 70 * 70 + 1;

   public int minimizeTheDifference(int[][] mat, int target) {
      n = mat.length;
      m = mat[0].length;
      dp = new int[n][len];
      this.mat = mat;
      this.target = target;
      for (int[] arr : dp)
         Arrays.fill(arr, -1);
      find(0, 0);
      return ans;
   }

   int find(int row, int sum) {
      if (row == n) {
         ans = Math.min(ans, Math.abs(target - sum));
         return ans;
      } else {
         if (dp[row][sum] != -1)
            return dp[row][sum];
         if (sum >= target && (sum - target) >= ans)
            return Integer.MAX_VALUE;
         // Try every number
         int min = Integer.MAX_VALUE;
         for (int col = 0; col < m; col++) {
            int diff = find(row + 1, sum + mat[row][col]);
            min = Math.min(min, diff);
         }

         return dp[row][sum] = min;
      }
   }

}
