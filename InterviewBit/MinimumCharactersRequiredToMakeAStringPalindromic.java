/*
 * MinimumCharactersRequiredToMakeAStringPalindromic.java
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
 * MERCHANTABILITY or FITNESS FOR FindGreatestCommonDivisor PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */


public class MinimumCharactersRequiredToMakeAStringPalindromic{

	//Time complexity O( n square )
	public static int solve(String s) {
		
		int len = s.length();
		if( len <= 1 ) return 0;
		int count = 0;
		
		for( int index = len - 1; index >= 0; index-- ){
			if( isPalindrome(s, index) ){
				return count;
			}
			count++;
		}
		
		return count;
    }
    
    public static boolean isPalindrome( String s, int end ){
		int start = 0;
		
		while( start < s.length() && end >= 0 && start <= end ){
			if( s.charAt(start++) != s.charAt(end--) ){
				return false;
			}
		}
		return true;
	}
	public static void main (String[] args) {

		System.out.println(solve2("abede"));
	}

	// KMP String pattern matching ( Time and space complexity O(n) )
	public static int solve2(String s) {
		int len = s.length();
		
		if( len == 0 ) return 0;
		
		StringBuilder sbr = new StringBuilder(s);
		sbr = sbr.reverse();
		
		s += ("#") + sbr.toString();

		int[] dp = new int[len * 2 + 1];
		
		int start = 0, end = 1;
		
		while( end < len * 2 + 1){
			
			if( s.charAt(start) == s.charAt(end) ){
				dp[end] = start + 1;
				start++;
				end++;
			}else{
				if( start == 0 ) end++;
				start = ( start - 1 < 0 ) ? 0: dp[start - 1];

			}
		}

		return len - dp[ dp.length - 1];
	}
}

