/*
 * CountSquareSubmatriceswithAllOnes.java
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


public class CountSquareSubmatriceswithAllOnes {
	
	public static int countSquares(int[][] matrix) {
		int len1 = matrix.length;
		int len2 = matrix[0].length;
		int res = 0;

		int[][] dp = new int[len1][len2];
		
		//first row 
		for( int col = 0; col < len2; col++ ){
			dp[0][col] = matrix[0][col];
			res += dp[0][col];
		}
		
		//first column
		for( int row = 1; row < len1; row++ ){
			dp[row][0] = matrix[row][0];
			res += dp[row][0];
		}
		
		
		for( int row = 1; row < len1; row++ ){
			for( int col = 1; col < len2; col++ ){
				if( matrix[row][col] == 0 ) {
					dp[row][col] = 0;
					continue;
				}
				dp[row][col] =  Math.min( dp[row - 1][col], Math.min( dp[row - 1][col - 1], dp[row][col - 1] ) ) + 1;
				res += dp[row][col];
			}
		}
		
		return res;
        
    }
	public static void main (String[] args) {
		
		System.out.println( countSquares( new int[][]{
			{1, 0, 1}, {1, 1, 0}, {1, 1, 0}
		}));
	}
}

