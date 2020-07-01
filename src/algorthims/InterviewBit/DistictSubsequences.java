/*
 * DistictSubsequences.java
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


import java.util.HashMap;
import java.util.Map;

public class DistictSubsequences {

	//Time limit exceeded
	public static int numDistinct(String a, String b) {
		int m = a.length();
		int n = b.length();
		if( m < n ){ return 0;}
		Map<String, Integer> map = new HashMap<>();
		return helper( a, b, 0, new StringBuilder(), map );
		
    }
    
    public static int helper(String a, String b, int index, StringBuilder curr, Map<String, Integer> map){
		if( curr.toString().equals( b ) ){
			return 1;
		}

		if( index >= a.length() ){
			return 0;
		}

		if( map.containsKey(curr.toString() + "," + index) ){
			return map.get(curr.toString() + "," + index);
		}
		int res1 = helper( a, b, index + 1, curr.append( a.charAt(index) ), map) ;
		curr.deleteCharAt(curr.length() - 1);
		int res2 = helper( a, b, index + 1, curr, map);

		map.put(curr.toString() + "," + index, res1 + res2);
		return res1 + res2;
		
	}
	public static void main (String[] args) {

		System.out.println( numDistinct2("geeksforgeeks", "ge"));
	}

	public static int numDistinct2(String S, String T) {
		int m = T.length();
		int n = S.length();

		if (m > n) return 0;

		int[][] dp = new int[m + 1][n + 1];

		for (int col = 0; col <= n; col++) {
			dp[0][col] = 1;
		}

		for (int row = 1; row <= m; row++) {
			for (int col = 1; col <= n; col++) {

				if( T.charAt(row - 1) != S.charAt( col - 1 ) ){
					dp[row][col] = dp[row][col - 1];
				}else{

					dp[row][col] = dp[row][col - 1] + dp[row - 1][col - 1];
				}
			}
		}

		return dp[m][n];
	}
}

