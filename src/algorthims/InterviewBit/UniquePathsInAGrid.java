/*
 Given a grid of size m * n, lets assume you are starting at (1,1) and your goal is to reach (m,n). At any instance, if you are on (x,y), you can either go to (x, y + 1) or (x + 1, y).

Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Example :
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.
 * 
 */

import java.util.*;
public class UniquePathsInAGrid {
	
	public static int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> lst) {
		
		int n = lst.size();
		if( n == 0 ){
			return -1;
		}
		int m = lst.get(0).size();

		int[][] dp = new int[n][m];

		if( lst.get(0).get(0) == 1 ){
			return 0;
		}else{
			dp[0][0] = 1;
		}
		for(int row = 1; row < n; row++ ){
			dp[row][0] = ( lst.get(row).get(0) == 0 )? dp[row - 1][0]: 0;
		}

		for(int col = 1; col < m; col++ ){
			dp[0][col] = ( lst.get(0).get(col) == 0 ) ? dp[0][col - 1]: 0;
		}
		
		for( int row = 1; row < n; row++ ){
			for(int col = 1; col <m; col++ ){
				int num = lst.get(row).get(col);
				if( num == 1 ){
					dp[row][col] = 0;
				}else {
					
					dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
				}
			}
		}
		
		return dp[n-1][m-1];
    }
	public static void main (String[] args) {

		ArrayList<ArrayList<Integer>> lst = new ArrayList<>();

		ArrayList<Integer> temp1 = new ArrayList<>();
		temp1.add(0);temp1.add(0);temp1.add(0);
		ArrayList<Integer> temp2 = new ArrayList<>();
		temp2.add(0);temp2.add(1);temp2.add(0);
		ArrayList<Integer> temp3 = new ArrayList<>();
		temp3.add(0);temp3.add(0);temp3.add(0);

		lst.add(temp1);
		lst.add(temp2);
		lst.add(temp3);

		System.out.println( uniquePathsWithObstacles( lst ));
	}
}

