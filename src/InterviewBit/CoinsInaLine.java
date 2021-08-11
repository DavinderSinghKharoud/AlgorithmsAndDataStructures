package InterviewBit;/*
 * CoinsInaLine.java
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

public class CoinsInaLine {
	
	public static int maxcoin1(int[] arr ) {
		return find1( arr, 0, arr.length - 1);
    }

	private static int find1(int[] arr, int start, int end) {

		if( start >= end ){
			return 0;
		}
		int sum1 = arr[start];
		if( arr[start + 1] > arr[end] ){
			sum1 += find1( arr, start + 2, end );
		}else {
			sum1 += find1( arr, start + 1, end - 1 );
		}

		int sum2 = arr[end];
		if( arr[start] > arr[end - 1] ){
			sum2 += find1( arr, start + 1, end - 1 );
		}else{
			sum2 += find1( arr, start, end - 2 );
		}
		return Math.max( sum1, sum2 );
	}

	public static void main (String[] args) {
		System.out.println( maxcoin2( new int[]{
				5, 4, 8, 10
		}));
	}

	public static int maxcoin2(int[] arr ) {
		if( arr == null || arr.length == 0 ){
			return 0;
		}
		int[][] dp = new int[arr.length][arr.length];
		for(int[] state: dp ){
			Arrays.fill( state, - 1 );
		}
		return find2( arr, dp, 0, arr.length - 1);
	}

	private static int find2(int[] arr, int[][] dp, int start, int end) {
		if( start > end ){
			return 0;
		}

		if( dp[start][end] == -1 ){
			int p = find2( arr, dp, start + 2, end );
			int q = find2( arr, dp, start + 1, end - 1 );
			int r = find2( arr, dp, start, end - 2 );

			int m = arr[start] + Math.min(p, q);
			int n = arr[end] + Math.min( q, r );

			dp[start][end] = Math.max(m, n);
		}

		return dp[start][end];
	}
}

