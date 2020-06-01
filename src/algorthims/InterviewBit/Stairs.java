/*
 * Stairs.java
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


import java.util.ArrayList;
import java.util.Arrays;

public class Stairs {
	
	public static int climbStairs(int num) {
		if( num == 1 ) return 1;
		int[] dp = new int[num + 1];
		Arrays.fill(dp, -1);
		return helper( dp, num );

    }
    
    public static int helper( int[] dp, int stair ){
		if( dp[stair] != -1 ) return dp[stair];
		if( stair <= 1 ){
			return 1;
		}

		int count1 = helper(dp,stair - 1);
		int count2 = helper( dp,stair - 2);
		dp[stair] = count1 + count2;
		return count1 + count2;
	}
	public static void main (String[] args) {
		System.out.println( climbStairs(4) );
	}
}

