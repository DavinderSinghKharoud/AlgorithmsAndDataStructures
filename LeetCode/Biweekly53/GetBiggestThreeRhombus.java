package LeetCode.Biweekly53;

import java.util.*;

/**
 * You are given an m x n integer matrix grid​​​.
 *
 * FindGreatestCommonDivisor rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:
 *
 *
 * Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.
 *
 * Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.
 */
public class GetBiggestThreeRhombus {
    public static void main(String[] args) {
        int[][] grid = new int[5][5];
        grid[0] = new int[]{3, 4, 5, 1, 3};
        grid[1] = new int[]{3, 3, 4, 2, 3};
        grid[2] = new int[]{20, 30, 200, 40, 10};
        grid[3] = new int[]{1, 5, 5, 4, 1};
        grid[4] = new int[]{4, 3, 2, 2, 5};

        GetBiggestThreeRhombus o = new GetBiggestThreeRhombus();
        System.out.println(Arrays.toString(o.getBiggestThree(grid)));
    }

    int[][] grid;

    public int[] getBiggestThree(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        this.grid = grid;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                set.add(grid[i][j]);
                for (int row = i + 2; row < n; row += 2) {
                    // Try every possible
                    int meetRow = (i + row) / 2;
                    int meetColLeft = j - (meetRow - i);
                    int meetColRight = j + (meetRow - i);
                    if (isValid(meetRow, meetColLeft) && isValid(meetRow, meetColRight)) {
                        int sum = rightSum(i, j, meetRow, meetColRight);
                        sum += rightSum(meetRow, meetColLeft, row, j);

                        sum += leftSum(i + 1, j - 1, meetRow - 1, meetColLeft + 1);
                        sum += leftSum(meetRow + 1, meetColRight - 1, row - 1, j + 1);
                        set.add(sum);
                    }
                }
            }
        }

        return set.stream().sorted(Collections.reverseOrder()).limit(3).mapToInt(Integer::intValue).toArray();
    }

    int leftSum(int x1, int y1, int x2, int y2) {
        if (!isValid(x1, y1) || !isValid(x2, y2) || x1 > x2) return 0;
        int sum = 0;
        while (x1 != x2 && y1 != y2) {
            sum += grid[x1++][y1--];
        }
        sum += grid[x1][y2];

        return sum;
    }

    int rightSum(int x1, int y1, int x2, int y2) {
        if (!isValid(x1, y1) || !isValid(x2, y2) || x1 > x2) return 0;
        int sum = 0;
        while (x1 != x2 && y1 != y2) {
            sum += grid[x1++][y1++];
        }
        sum += grid[x1][y2];
        return sum;
    }

    boolean isValid(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
