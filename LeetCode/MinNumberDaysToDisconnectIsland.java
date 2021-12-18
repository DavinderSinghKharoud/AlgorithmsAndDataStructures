package LeetCode;

import java.util.*;

/**
 * You are given an m x n binary grid grid consisting where 1 represents land and 0 represents water. An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.
 * <p>
 * The grid is said to be connected if we have exactly one island, otherwise is said disconnected.
 * <p>
 * In one day, we are allowed to change any single land cell (1) into a water cell (0).
 * <p>
 * Return the minimum number of days to disconnect the grid.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 2
 * Explanation: We need at least 2 days to get a disconnected grid.
 * Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
 * Example 2:
 * <p>
 * Input: grid = [[1,1]]
 * Output: 2
 * Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 30
 * grid[i][j] is either 0 or 1.
 */
public class MinNumberDaysToDisconnectIsland {

    public static void main(String[] args) {
        System.out.println(new MinNumberDaysToDisconnectIsland().minDays(
                new int[][]{
                        {1, 1}
                }
        ));
    }

    int n, m;
    int[][] direc = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    int[][] grid;

    public int minDays(int[][] grid) {
        n = grid.length;
        this.grid = grid;
        m = grid[0].length;

        if (isDisconnected(grid)) return 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (isDisconnected(grid)) return 1;
                    grid[i][j] = 1;
                }
            }
        }

        return 2;
    }

    boolean isDisconnected(int[][] grid) {
        int[][] copy = Arrays.stream(grid).map(int[]::clone).toArray(int[][]::new);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copy[i][j] == 1) {
                    count++;
                    submerge(i, j, copy);
                }
            }
        }
        return count != 1;
    }

    void submerge(int i, int j, int[][] grid) {
        grid[i][j] = 0;
        for (int[] dir : direc) {
            int modx = dir[0] + i, mody = dir[1] + j;
            if (isValid(modx, mody) && grid[modx][mody] == 1) submerge(modx, mody, grid);
        }
    }

    boolean isValid(int a, int b) {
        return a >= 0 && b >= 0 && a < n && b < m;
    }
}

