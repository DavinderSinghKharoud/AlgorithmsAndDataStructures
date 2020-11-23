package algorithms.InterviewBit;

/**
 * Problem Description
 *
 * Given a 2D integer array A of size  N * N  representing a triangle of numbers.
 *
 * Find the maximum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * NOTE:
 *
 * Adjacent cells to cell (i,j) are only (i+1,j) and (i+1,j+1)
 * Row i contains i integer and n-i zeroes for all i in [1,n] where zeroes represents empty cells.
 *
 *
 * Problem Constraints
 * 0 <= N <= 1000
 *
 * 0 <= A[i][j] <= 1000
 *
 *
 *
 * Input Format
 * First and only argument is an 2D integer array A of size N * N.
 *
 *
 *
 * Output Format
 * Return a single integer denoting the maximum path sum from top to bottom in the triangle.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = [
 *         [3, 0, 0, 0]
 *         [7, 4, 0, 0]
 *         [2, 4, 6, 0]
 *         [8, 5, 9, 3]
 *      ]
 * Input 2:
 *
 *  A = [
 *         [8, 0, 0, 0]
 *         [4, 4, 0, 0]
 *         [2, 2, 6, 0]
 *         [1, 1, 1, 1]
 *      ]
 *
 *
 * Example Output
 * Output 1:
 *
 *  23
 * Output 2:
 *
 *  19
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  Given triangle looks like:  3
 *                              7 4
 *                              2 4 6
 *                              8 5 9 3
 *         So max path is (3 + 7 + 4 + 9) = 23
 * Explanation 1:
 *
 *  Given triangle looks like:  8
 *                              4 4
 *                              2 2 6
 *                              1 1 1 1
 *         So max path is (8 + 4 + 6 + 1) = 19
 */
public class MaximumPathInATriangle {

    public static int solve(int[][] arr) {

        int len1 = arr.length;
        int len2 = arr[0].length;

        if( len1 == 1 ) return arr[0][0];
        int[][] dp = new int[len1][len2];
        int res = -1;

        dp[0][0] = arr[0][0];
        for (int row = 1; row < len1; row++) {

            for (int col = 0; col < len2; col++) {
                int curr = arr[row][col];
                dp[row][col] = Math.max(dp[row - 1][col] + curr, (col == 0 )? 0: dp[row - 1][col - 1] + curr);
                res = Math.max(res, dp[row][col]);
            }
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(solve(new int[][]{ {108} }));
    }
}
