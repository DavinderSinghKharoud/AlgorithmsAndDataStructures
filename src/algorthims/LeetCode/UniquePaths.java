package algorthims.LeetCode;

public class UniquePaths {
    public static int uniquePaths(int m, int n) {
        int[][] board = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = 0;
            }
        }
        board[0][0] = 1;
        board[n - 1][m - 1] = 2;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        int routes = countRoutes(board, 0, 0, 0);
        return routes;

    }

    private static int countRoutes(int[][] board, int row, int col, int routes) {

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return 0;
        }

        if (board[row][col] == 2) {
            routes++;
            return routes;
        }

        int down = countRoutes(board, row + 1, col, routes);
        int right = countRoutes(board, row, col + 1, routes);
        return down + right;
    }


    public static int uniquePaths2(int m, int n) {

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {

        System.out.println(uniquePaths2(3, 2));
    }
}
