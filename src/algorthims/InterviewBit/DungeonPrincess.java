/*
 * DungeonPrincess.java
 * 
 * Copyright 2020 Davinder singh kharoud <davindersinghkharoud@Davinders-MacBook-Pro.local>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */


import java.util.Arrays;

public class DungeonPrincess {

	//O(n * m ) time and space complexity
	public static int calculateMinimumHP(int[][] arr) {
		int n = arr.length;
		int m = arr[0].length;
		
		int[][] dp = new int[n + 1][m + 1];
		for(int[] state: dp ){
			Arrays.fill(state, Integer.MAX_VALUE);
		}
		dp[n][m - 1] = 1;
		dp[n - 1][m] = 1;
		for(int row = n - 1; row >= 0; row-- ){
			for( int col = m - 1; col >= 0; col-- ){
				
				int min = Math.min( dp[row + 1][col], dp[row][col + 1] ) - arr[row][col];
				
				dp[row][col] = Math.max(1, min );
			}
		}
	
	return dp[0][0];
    }
    
	public static void main (String[] args) {
		
		System.out.println( calculateMinimumHP( new int[][]{
			{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}
		}));
	}
}

