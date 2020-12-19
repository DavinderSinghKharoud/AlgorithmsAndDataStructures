/*
 Given a matrix M of size nxm and an integer K, find the maximum element in the K manhattan distance neighbourhood for all elements in nxm matrix. 
In other words, for every element M[i][j] find the maximum element M[p][q] such that abs(i-p)+abs(j-q) <= K.

Note: Expected time complexity is O(N*N*K)

Constraints:

1 <= n <= 300
1 <= m <= 300
1 <= K <= 300
0 <= M[i][j] <= 1000
Example:

Input:
M  = [[1,2,4],[4,5,8]] , K = 2

Output:
ans = [[5,8,8],[8,8,8]]

 */
import java.util.*;

public class KthManhattanDistanceNeighbourhood {
	
	public static int[][] solve(int range, int[][] arr) {
		int rowLen = arr.length;
		if( rowLen == 0 ){
			return arr;
		}
	
		int colLen = arr[0].length;
		
		int[][] copy = Arrays.stream(arr).map(int[]:: clone).toArray( int[][]:: new );
		
		for( int row = 0; row < rowLen; row++ ){
			
			for( int col = 0; col < colLen; col++ ){
				
				arr[row][col] = findMax2( copy, row, col, range, rowLen, colLen );
			}
		}
		
		return arr;
    }
    
    public static int findMax( int[][] copy, int row, int col, int range, int rowLen, int colLen ){
		int max = copy[row][col];
		int start = Math.max( 0, col - range );
		int end = Math.min( colLen - 1, col + range );
		int top = Math.max(0, row - range );
		int bottom = Math.min( rowLen - 1, row + range );

		for (int i = top; i <= bottom; i++) {
			for (int j = start; j <= end; j++) {

				if( Math.abs( i - row ) + Math.abs( j - col ) <= range ){

					max = Math.max( max, copy[i][j] );
				}

			}

		}

		return max;
	}

	public static int findMax2( int[][] copy, int row, int col, int range, int rowLen, int colLen ){
		int[][] direc = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
		int max = copy[row][col];
		int tempRow = row;
		int tempCol = col;
		int tempRange = range;
		//horizontal

		for( int[] dir : direc ){
			row = tempRow;
			col = tempCol;
			range = tempRange;
			while( row < rowLen && row >= 0 && col < colLen && col >= 0 && range >= 0){
				max = Math.max( copy[row][col], max );
				row += dir[0];
				col += dir[1];
				range--;
			}
		}

		return max;
	}
	public static void main (String[] args) {
		
		int[][] arr = solve2( 2,new int[][]{
			{1,2,4}, {4,5,8}
		});

//		int[][] arr = solve( 2,new int[][]{
//				{}
//		});
		for( int row = 0; row < arr.length; row++ ){
			for( int col = 0; col < arr[0].length; col++ ){
				System.out.println( arr[row][col] );
			}
		}
	}

	public static int[][] solve2(int range, int[][] arr) {
		int rowLen = arr.length;
		int colLen = arr[0].length;
		int[][][] dp = new int[range + 1][rowLen][colLen];

		for (int k = 0; k <= range; k++) {

			for (int i = 0; i < rowLen; i++) {
				for (int j = 0; j < colLen; j++) {

					if( k == 0 ){
						dp[0][i][j] = arr[i][j];
					}else{
						int curr = Integer.MIN_VALUE;

						if( i > 0 ) curr = Math.max( curr, dp[k - 1][i - 1][j] );
						if( j > 0 ) curr = Math.max( curr, dp[k - 1][i][j - 1]);
						if( i < rowLen - 1 ) curr = Math.max( curr, dp[k - 1][i + 1][j] );
						if ( j < colLen - 1 ) curr = Math.max( curr, dp[k - 1][i][j + 1] );

						dp[k][i][j] = Math.max(curr, dp[k - 1][i][j] );
					}

				}
			}
		}

		int[][] res = new int[rowLen][colLen];

		for (int row = 0; row < rowLen; row++) {
			for (int col = 0; col < colLen; col++) {
				res[row][col] = dp[range][row][col];
			}
		}
		return res;
	}
}

