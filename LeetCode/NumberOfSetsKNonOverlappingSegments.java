package LeetCode;

/**
 * Given n points on a 1-RecoverTheOriginalArray plane, where the ith point (from 0 to n-1) is at x = i, find the number of ways we can draw exactly k non-overlapping line segments such that each segment covers two or more points. The endpoints of each segment must have integral coordinates. The k line segments do not have to cover all n points, and they are allowed to share endpoints.
 *
 * Return the number of ways we can draw k non-overlapping line segments. Since this number can be huge, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, k = 2
 * Output: 5
 * Explanation: The two line segments are shown in red and blue.
 * The image above shows the 5 different ways {(0,2),(2,3)}, {(0,1),(1,3)}, {(0,1),(2,3)}, {(1,2),(2,3)}, {(0,1),(1,2)}.
 * Example 2:
 *
 * Input: n = 3, k = 1
 * Output: 3
 * Explanation: The 3 ways are {(0,1)}, {(0,2)}, {(1,2)}.
 * Example 3:
 *
 * Input: n = 30, k = 7
 * Output: 796297179
 * Explanation: The total number of possible ways to draw 7 line segments is 3796297200. Taking this number modulo 109 + 7 gives us 796297179.
 */
public class NumberOfSetsKNonOverlappingSegments {
    int mod = (int) 1e9 + 7;

    public int numberOfSets(int n, int k) {
        if (n == 1) return 0;
        int[] sum = new int[n];
        int[] dp = new int[n];

        //Only go when n >= k
        for (int point = 1; point < n - k + 1; point++) {
            dp[0] = (point * (point + 1)) >> 1;
            sum[0] = add(sum[0], dp[0]);
            for (int count = 2; count <= k; count++) {
                dp[count - 1] = add(dp[count - 1], sum[count - 2]);
                sum[count - 1] = add(sum[count - 1], dp[count - 1]);
            }
        }

        return dp[k - 1];
    }

    int add(int a, int b) {
        return (a + b) % mod;
    }
}
