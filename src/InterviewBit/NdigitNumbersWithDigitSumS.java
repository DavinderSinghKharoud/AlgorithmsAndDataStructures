/*
 * NdigitNumbersWithDigitSumS.java
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

public class NdigitNumbersWithDigitSumS {
	
	//working but time limit exceeded
	public static long solve(int n, int sum) {
		
		if( sum == 0 || n == 0 ) return 0;
		int[][] dp = new int [n + 1][sum + 1];

		for( int[] row: dp ){
			Arrays.fill( row, -1);
		}

		int ans = 0;
		for (int i = 1; i<= 9; i++) {
			if( sum - i >= 0 ){
				ans = (ans + helper(n - 1, sum - i, dp) ) % 1000000007;
			}
		}
		return ans ;
		
    }
    
    public static int helper( int n, int sum, int[][] dp ){
		if( sum == 0 && n == 0 ){
			return 1;
		}

		if( n <= 0 || sum < 0 ){
			return 0;
		}

		if( dp[n][sum] != -1 ){
			return dp[n][sum];
		}


		int temp = 0;

		for (int i = 0; i <= 9; i++) {
			if( sum - i >= 0 ){
				temp = (temp + helper(n - 1, sum - i, dp) ) % 1000000007;
			}

		}

		dp[n][sum] = temp % 1000000007;
		return dp[n][sum];
	}


	public static void main (String[] args) {

		System.out.println( solve(75, 22) );
	}



}

