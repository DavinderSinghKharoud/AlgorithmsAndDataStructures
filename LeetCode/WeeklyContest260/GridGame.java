package LeetCode.WeeklyContest260;

public class GridGame {
   public static void main(String[] args) {
      GridGame o = new GridGame();
      System.out.println(o.gridGame(new int[][] { { 3, 3, 1 }, { 8, 5, 2 } }));
   }

   public long gridGame(int[][] grid) {
      int n = grid.length, m = grid[0].length;
      long upperSum = 0, lowerSum = 0;
      long currSumUp = 0;
      for (int i = 0; i < m; i++)
         upperSum += grid[0][i];
      long ans = Long.MAX_VALUE;
      for (int i = 0; i < m; i++) {
         currSumUp += grid[0][i];
         ans = Math.min(ans, Math.max(upperSum - currSumUp, lowerSum));
         lowerSum += grid[1][i];
      }
      return ans;
   }
}
