package Algorithms.LeetCode.WeeklyContest250;

import java.util.*;

public class MaxNumberOfPoints {
    public static void main(String[] args) {
        MaxNumberOfPoints o = new MaxNumberOfPoints();
        System.out.println(o.maxPoints(new int[][]{
                {1, 2, 3}, {1, 5, 1}, {3, 1, 1}
        }));
    }

    public long maxPoints(int[][] points) {
        long res = 0;
        int n = points.length, m = points[0].length;
        long[] prev = new long[m];
        for (int i = 0; i < m; i++) {
            prev[i] = points[0][i];
        }

        for (int i = 1; i < n; i++) {
            long[] left = new long[m], right = new long[m];
            // left --> right
            left[0] = prev[0];
            for (int k = 1; k < m; k++) {
                left[k] = Math.max(left[k - 1] - 1, prev[k]);
            }
            right[m - 1] = prev[m - 1];
            for (int k = m - 2; k >= 0; k--) {
                right[k] = Math.max(right[k + 1] - 1, prev[k]);
            }

            // update the ans
            for (int k = 0; k < m; k++) {
                prev[k] = points[i][k] + Math.max(left[k], right[k]);
            }

        }
        for (long num : prev) {
            res = Math.max(res, num);
        }
        return res;
    }
}
