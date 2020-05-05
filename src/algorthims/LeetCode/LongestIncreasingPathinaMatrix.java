package algorthims.LeetCode;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathinaMatrix {
	
	static int[][] directions = { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };

	//we do need a boolean array ( like to skip the cycle ) because if we are moving forward it means previous value
	//is less and if we try again to check the previous value the if condition ( checking the number is greater than current ) will fail.
	//O(n cube not sure) time complexity and O(n) space complexity
	public static int longestIncreasingPath(int[][] matrix) {
		if( matrix.length == 0 || matrix[0].length == 0 ){
			return 0;
		}
        int rows = matrix.length;
        int cols = matrix[0].length;
        int res = 0;
        int[][] dp = new int[ rows ][ cols ];
        
        for( int row = 0; row < rows; row ++ ){
			for( int col = 0; col < cols; col++ ){
				
				res = Math.max( res, helper( matrix, row, col, dp ) );
			}
		}
		
		return res;
    }
    
    public static int helper( int[][] matrix, int row, int col, int[][] dp ){
	
		if( dp[row][col] != 0 ){
			return dp[row][col];
		}

		int max = 1;

		//let's move in four directions
		for( int[] dir: directions ){
			
			int r = dir[0] + row;
			int c = dir[1] + col;
			int temp_max = 1;
			
			if( r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length ){
				continue;
			}

			if( matrix[r][c] > matrix[row][col]){
				temp_max += helper( matrix, r, c, dp );
			}

			max = Math.max( max, temp_max );
		}
		dp[row][col] = max;
		
		return max;
		
	}
	public static void main (String[] args) {
		
		System.out.print( longestIncreasingPath( new int[][]{
			{9, 9, 4}, {6, 6, 8}, {2, 1, 1}
		}));
	}
}

