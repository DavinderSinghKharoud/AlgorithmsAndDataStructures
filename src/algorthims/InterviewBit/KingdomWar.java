/*
 * KingdomWar.java
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


public class KingdomWar {

    //Kadane algorithm O( col square * rows ) time complexity and O( rows ) space complexity
    public static int solve(int[][] arr) {


        int max = Integer.MIN_VALUE;

        for (int L = 0; L < arr[0].length; L++) {

            int[] dp = new int[arr.length];
            for (int R = L; R < arr[0].length; R++) {
                //fill the dp
                for (int row = 0; row < arr.length; row++) {
                    dp[row] += arr[row][R];
                }

                max = Math.max(max, findMax(dp));
            }
        }

        return max;
    }

    public static int findMax(int[] a) {

        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;

    }


    public static void main(String[] args) {
        System.out.println(solve(new int[][]{
                {-200}
        }));
    }
    
    public static int solve2(int[][] arr) {
		
		int n = arr.length;
		int m = arr[0].length;
		int max = Integer.MIN_VALUE;
		
		if( n == 0 ){
			return -1;
		}
		
		int[][] dp = new int[n + 1][m + 1];
		
		for(int row = n - 1; row >= 0; row-- ){
			for( int col = m - 1; col >= 0; col-- ){
				
				dp[row][col] += arr[row][col];
				dp[row][col] += dp[row + 1][col];
				dp[row][col] += dp[row][col + 1];
				dp[row][col] -= dp[row + 1][col + 1];
				
				max = Math.max( max, dp[row][col] );
			}
		}
		
		return max;
	}
}

