package LeetCode.BiweeklyContest69;

public class StampingTheGrid {
   public static void main(String[] args) {
      StampingTheGrid o = new StampingTheGrid();
      System.out.println(o.possibleToStamp(
            new int[][] { { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 } }, 4, 3));
   }

   int n, m, stampHeight, stampWidth;

   public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
      int n = 0;

      n = grid.length;
      m = grid[0].length;
      this.stampHeight = stampHeight;
      this.stampWidth = stampWidth;

      int[][] validStampMarkedGrid = markValidStampsBottomRightCorner(grid);
      int[][] gridPrefixSum = get2DPrefixSum(validStampMarkedGrid);

      return isEveryEmptyCellCovered(gridPrefixSum, grid);
   }

   boolean isEveryEmptyCellCovered(int[][] gridPrefixSum, int[][] grid) {
      for (int row = 0; row < n; row++) {
         for (int col = 0; col < m; col++) {
            if (gridPrefixSum[row][col] == 0 && grid[row][col] == 0)
               return false;
         }
      }
      return true;
   }

   int[][] markValidStampsBottomRightCorner(int[][] grid) {
      int[][] markedGrid = new int[n][m];
      int[][] gridPrefixSum = get2DPrefixSum(grid);
      for (int row = 0; row <= n - stampHeight; row++) {
         for (int col = 0; col <= m - stampWidth; col++) {
            Point upperLeftCorner = new Point(row, col);
            Point bottomRightCorner = new Point(row + stampHeight - 1, col + stampWidth - 1);

            if (isGridEmpty(gridPrefixSum, upperLeftCorner, bottomRightCorner)) {
               markPoint(markedGrid, bottomRightCorner);
            }
         }
      }
      return markedGrid;
   }

   private void markPoint(int[][] markedGrid, Point point) {
      markedGrid[point.x][point.y]++;
      markedGrid[point.x + stampHeight - 1][point.y + stampWidth - 1]++;
      markedGrid[point.x + stampHeight - 1][point.y]--;
      markedGrid[point.x][point.y + stampWidth - 1]--;
   }

   private int[][] get2DPrefixSum(int[][] grid) {
      int[][] gridPrefixSum = new int[n][m];
      for (int row = 0; row < n; row++) {
         for (int col = 0; col < m; col++) {
            gridPrefixSum[row][col] = grid[row][col] + get2DSum(gridPrefixSum, new Point(row - 1, col))
                  + get2DSum(gridPrefixSum, new Point(row, col - 1))
                  - get2DSum(gridPrefixSum, new Point(row - 1, col - 1));
         }
      }
      return gridPrefixSum;
   }

   private boolean isGridEmpty(int[][] gridPrefixSum, Point upperLeftCorner, Point bottomRightCorner) {
      return get2DSum(gridPrefixSum, upperLeftCorner, bottomRightCorner) == 0;
   }

   int get2DSum(int[][] gridPrefixSum, Point upperLeftCorner, Point bottomRightCorner) {
      int totalSum = gridPrefixSum[bottomRightCorner.x][bottomRightCorner.y];
      totalSum -= get2DSum(gridPrefixSum, new Point(upperLeftCorner.x - 1, bottomRightCorner.y));
      totalSum -= get2DSum(gridPrefixSum, new Point(upperLeftCorner.y - 1, bottomRightCorner.x));
      return totalSum;
   }

   int get2DSum(int[][] gridPrefixSum, Point point) {
      if (point.isValid(n, m)) {
         return gridPrefixSum[point.x][point.y];
      }
      return 0;
   }

   private static class Point {
      int x, y;

      public Point(int x, int y) {
         this.x = x;
         this.y = y;
      }

      public boolean isValid(int row, int col) {
         return x >= 0 && y >= 0 && x < row && y < col;
      }
   }
}