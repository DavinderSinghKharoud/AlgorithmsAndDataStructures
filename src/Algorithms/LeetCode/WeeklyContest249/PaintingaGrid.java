package Algorithms.LeetCode.WeeklyContest249;

import java.util.*;

public class PaintingaGrid {
   public static void main(String[] args) {

      PaintingaGrid o = new PaintingaGrid();
      System.out.println(o.colorTheGrid(3, 3));
   }

   int mod = (int) 1e9 + 7;

   int[][] dp;

   public int colorTheGrid(int m, int n) {
      dp = new int[1001][1024];

      return fill(m, n, 0, 0, 0, 0);
   }

   int fill(int m, int n, int i, int j, int curr, int prev) {
      if (i == m) {
         return fill(m, n, 0, j + 1, 0, curr);
      }
      if (j == n)
         return 1; // It is possible

      if (i == 0 && dp[j][prev] != 0)
         return dp[j][prev];

      int res = 0;
      //we use 2 * i because of 3 which has bitwise 11
      int up = (i == 0) ? 0 : (curr >> ((i - 1) * 2) & 3);
      int left = (prev >> (i * 2)) & 3;

      for (int color = 1; color <= 3; color++) {
         if (color != up && color != left) {
            res = (res + fill(m, n, i + 1, j, curr + (color << (i * 2)), prev)) % mod;
         }
      }
      if (i == 0) {
         dp[j][prev] = res;
      }
      return res;
   }

}
