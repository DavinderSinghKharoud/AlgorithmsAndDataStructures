package algorthims.LeetCode;/*
 * WildCardMatching.java
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

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 *
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 *
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * Example 5:
 *
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */
public class WildCardMatching {

	//O(sp) time complexity and space complexity
	public static boolean isMatch(String s, String p) {
		if( p.length() == 0 && s.length() == 0) return true;
		int len1 = s.length();
		int len2 = p.length();
		boolean[][] dp = new boolean[len1 + 1][len2 + 1];
		dp[0][0] = true;
		for(int i = 1; i<=len2; i++ ){
			if(i == 1 && p.charAt(i-1) == '*' ){
				dp[0][i] = true;
				continue;
			}
			if( p.charAt(i-1) == '*' ){
				dp[0][i] = dp[0][i-1];
			}
		}
		
		
		for( int i = 1 ; i<=len1; i++ ){
			for( int j = 1; j<=len2; j++ ){
				
				if( s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?' ){
					dp[i][j] = dp[i-1][j-1];
					
				}else if( p.charAt(j-1) == '*' ){
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				}
				
			}
		}
		
		return dp[len1][len2];
        
    }
	public static void main (String[] args) {
		
		System.out.println( isMatch( "", "*"));
	}
}

