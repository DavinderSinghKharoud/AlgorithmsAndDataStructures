/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.
Example :

Input :

    [  1 3 2
       4 3 1
       5 6 1
    ]

Output : 8
     1 -> 3 -> 2 -> 1 -> 1
 */


public class MinSumPathInMatrix {
	public static int minPathSum(int[][] arr) {
		int n = arr.length;
		int m = arr[0].length;
		
		
		int[][] dp = new int[n][m];
		
		for( int row = 0; row < n; row++ ){
			for( int col = 0; col < m; col++ ){
				if( row == 0 && col ==0 ){
					dp[0][0] = arr[0][0];
				}else if ( row == 0 ){
					dp[row][col] = arr[row][col] + dp[row][col - 1];
				}else if( col == 0 ){
					dp[row][col] = arr[row][col] + dp[row - 1][col];
				}else{
					dp[row][col] = Math.min( dp[row - 1][col], dp[row][col - 1] ) + arr[row][col];
				}
				
			}
		}
		
		
		return dp[n - 1][m - 1];
    }
	public static void main (String[] args) {
		System.out.println( minPathSum( new int[][]{
			{1, 3, 2}, {4, 3, 1}, {5, 6, 1}
		}));
	}
}

