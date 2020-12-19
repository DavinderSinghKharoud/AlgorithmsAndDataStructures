package Algorithms.LeetCode;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * Output: 4
 */

public class MaximalSquare {

    //Brute Force O( (mn) square ) and No extra space needed
    public static int maximalSquare(char[][] matrix) {

        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;

        int maxLen = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (matrix[i][j] == '1') {
                    int tempLen = 1;
                    boolean flag = true;

                    while (i + tempLen < rows && j + tempLen < cols && flag) {

                        //checking cols for next row
                        for (int k = j; k <= tempLen + j; k++) {

                            if (matrix[i + tempLen][k] == '0') {
                                flag = false;
                                break;
                            }
                        }

                        //checking for rows
                        for (int k = i; k <= tempLen + i; k++) {

                            if (matrix[k][j + tempLen] == '0') {
                                flag = false;
                                break;
                            }
                        }

                        if (flag) {
                            tempLen++;
                        }
                    }

                    maxLen = Math.max(maxLen, tempLen);
                }
            }
        }

        return maxLen * maxLen;

    }

    public static void main(String[] args) {

        System.out.println(maximalSquare3(new char[][]{
                {'1', '1', '1', '0', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '0', '1'},
                {'1', '0', '0', '1', '0'}
        }));

    }

    // O(m * n ) time complexity and space complexity
    public static int maximalSquare2(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;
        int dp[][] = new int[rows + 1][cols + 1];
        int maxLen = 0;

        for (int i = 1; i <= rows; i++) {

            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {

                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }

        return maxLen * maxLen;

    }

    // O(m * n ) time complexity and O(n) space complexity
    public static int maximalSquare3(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }

}

