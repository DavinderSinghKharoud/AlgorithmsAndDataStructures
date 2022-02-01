package LeetCode.BiweeklyContest69;

/**
 * You are given an m x n binary matrix grid where each cell is either 0 (empty) or 1 (occupied).
 *
 * You are then given stamps of size stampHeight x stampWidth. We want to fit the stamps such that they follow the given restrictions and requirements:
 *
 * Cover all the empty cells.
 * Do not cover any of the occupied cells.
 * We can put as many stamps as we want.
 * Stamps can overlap with each other.
 * Stamps are not allowed to be rotated.
 * Stamps must stay completely inside the grid.
 * Return true if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * Output: true
 * Explanation: We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.
 * Example 2:
 *
 *
 * Input: grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * Output: false
 * Explanation: There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[r].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 2 * 105
 * grid[r][c] is either 0 or 1.
 * 1 <= stampHeight, stampWidth <= 105
 * Accepted
 * 2.5K
 * Submissions
 */
public class StampingTheGrid {
    public static void main(String[] args) {
        StampingTheGrid o = new StampingTheGrid();
        System.out.println(o.possibleToStamp(
                new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}
                }, 4, 3));
    }

    int n, m, stampHeight, stampWidth;

    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
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
                if (gridPrefixSum[row + 1][col + 1] == 0 && grid[row][col] == 0)
                    return false;
            }
        }
        return true;
    }

    int[][] markValidStampsBottomRightCorner(int[][] grid) {
        int[][] markedGrid = new int[n + 1][m + 1];
        int[][] gridPrefixSum = get2DPrefixSum(grid);
        for (int row = 0; row <= n - stampHeight; row++) {
            for (int col = 0; col <= m - stampWidth; col++) {
                Point upperLeftCorner = new Point(row, col);
                Point bottomRightCorner = new Point(row + stampHeight, col + stampWidth);

                if (isGridEmpty(gridPrefixSum, upperLeftCorner, bottomRightCorner)) {
                    markPoint(markedGrid, upperLeftCorner);
                }
            }
        }
        return markedGrid;
    }

    private void markPoint(int[][] markedGrid, Point point) {
        markedGrid[point.x][point.y]++;
        markedGrid[point.x + stampHeight][point.y + stampWidth]++;
        markedGrid[point.x + stampHeight][point.y]--;
        markedGrid[point.x][point.y + stampWidth]--;
    }

    private int[][] get2DPrefixSum(int[][] grid) {
        int[][] gridPrefixSum = new int[n + 1][m + 1];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                gridPrefixSum[row + 1][col + 1] = grid[row][col]
                        + get2DSum(gridPrefixSum, new Point(row, col + 1))
                        + get2DSum(gridPrefixSum, new Point(row + 1, col))
                        - get2DSum(gridPrefixSum, new Point(row, col));
            }
        }
        return gridPrefixSum;
    }

    private boolean isGridEmpty(int[][] gridPrefixSum, Point upperLeftCorner, Point bottomRightCorner) {
        return get2DSum(gridPrefixSum, upperLeftCorner, bottomRightCorner) == 0;
    }

    int get2DSum(int[][] gridPrefixSum, Point upperLeftCorner, Point bottomRightCorner) {
        int totalSum = gridPrefixSum[bottomRightCorner.x][bottomRightCorner.y];
        totalSum -= get2DSum(gridPrefixSum, new Point(upperLeftCorner.x, bottomRightCorner.y));
        totalSum -= get2DSum(gridPrefixSum, new Point(bottomRightCorner.x, upperLeftCorner.y));
        return totalSum + gridPrefixSum[upperLeftCorner.x][upperLeftCorner.y];
    }

    int get2DSum(int[][] gridPrefixSum, Point point) {
        if (point.isValid(gridPrefixSum.length, gridPrefixSum[0].length)) {
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
