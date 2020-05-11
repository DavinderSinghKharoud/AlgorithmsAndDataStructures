package algorthims.LeetCode;

import java.util.*;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {

	//O(n square ) time complexity and O( s + t ) space complexity
	//time limit exceeded error
	public static String minWindow(String s, String t) {
        
        int left = 0;
        int right = Integer.MAX_VALUE;
        Map<Character, Integer> t_count = new HashMap<>();
        
        for( char c: t.toCharArray() ){
			t_count.put( c, t_count.getOrDefault( c, 0 ) + 1 );
		}
		
		for( int i = 0; i < s.length(); i++ ){
			
			Map<Character, Integer> s_count = new HashMap<>();
			int formed = 0;
			
			for( int j = i; j<s.length(); j++ ) {
				char c = s.charAt(j);
				s_count.put( c, s_count.getOrDefault( c, 0 ) + 1 );
				
				if( t_count.get(c) != null && s_count.get(c) == t_count.get(c) ){
					formed++;
				}

				//see if all the elements exists
				if( formed == t_count.size() ){
					
					if( right - left > j - i ){
						left = i;
						right = j;
					}
					
					break;
				}
			}
			
			
			
		}

		if( right == Integer.MAX_VALUE ){
			return "";
		}
		StringBuilder sbr = new StringBuilder();
		
		for( int  i = left; i<= right; i++  ){
			sbr.append( s.charAt(i) );
		}
		
		return sbr.toString();
        
    }
    
	public static void main (String[] args) {
		
		System.out.println( minWindow( "a", "aa"));
	}
}

