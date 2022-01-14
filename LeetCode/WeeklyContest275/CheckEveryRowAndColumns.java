package LeetCode.WeeklyContest275;

import java.util.*;

/**
 * An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).
 *
 * Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
 * Output: true
 * Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
 * Hence, we return true.
 */
public class CheckEveryRowAndColumns {
    public static void main(String[] args) {
        CheckEveryRowAndColumns o = new CheckEveryRowAndColumns();
        System.out.println(o.checkValid(new int[][]{
                {1, 1}, {1, 1}
        }));
    }

    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        boolean[] vis = new boolean[n + 1];
        for (int[] value : matrix) {
            Arrays.fill(vis, false);
            for (int j = 0; j < n; j++) {
                vis[value[j]] = true;
            }

            for (int j = 1; j <= n; j++) {
                if (!vis[j])
                    return false;
            }
        }

        for (int col = 0; col < n; col++) {
            Arrays.fill(vis, false);
            for (int[] ints : matrix) {
                vis[ints[col]] = true;
            }
            for (int j = 1; j <= n; j++) {
                if (!vis[j])
                    return false;
            }

        }
        return true;
    }
}
