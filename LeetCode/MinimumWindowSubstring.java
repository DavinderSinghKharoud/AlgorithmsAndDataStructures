package LeetCode;

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
		
		System.out.println( minWindow3( "ADOBECODEBANC", "ABC"));
	}


	//Sliding window approach
	//O(n) time complexity
	public static String minWindow2(String s, String t) {
		
		if( t.length() == 0 ){
			return "";
		}
		
		//for t
		int[] freq1 = new int[128];
		//for s
		int[] freq2 = new int[128];
		
		//adding all the characters of t to array
		t.chars().forEach( c -> freq1[ c ] ++ );
		
		int start = -1, end = -1;
		
		//
		for( int left = 0, right = 0; right < s.length(); right++ ){
			
			freq2[ s.charAt(right) ]++;
			
			//if it contain all the elements
			while( isOk( freq1, freq2 ) ){
				if( start == -1 || end - start > right - left ){
					start = left;
					end = right;
				}
				
				//remove the left character and increase the left boundary
				freq2[s.charAt(left) ]--;
				left++;
			}
			
			
		}
		
		return start == -1 ? "": s.substring( start, end + 1);
	}
	
	private static boolean isOk( int[] freq1, int[] freq2 ){
		
		//verify if all the character of t are in s string
		for( int i = 0; i<128; i++ ){
			if( freq1[i] > freq2[i] ){
				return false;
			}
		}
		
		return true;
	}

	//Sliding window approach
	//more efficient
	public static String minWindow3(String s, String t) {
		char[] ca1 = s.toCharArray(), ca2 = t.toCharArray();
		if (ca2.length > ca1.length) {
			return "";
		}
		int[] m = new int[128];
		for (char c : ca2) {
			m[c]++;
		}
		int left = 0, right = Integer.MAX_VALUE;
		for (int l = 0, r = 0, curr = ca2.length; r < ca1.length; r++) {
			if (m[ca1[r]]-- > 0) {
				curr--;
			}
			while (curr == 0) {
				if (right - left > r - l) {
					left = l;
					right = r;
				}
				if (++m[ca1[l++]] > 0) {
					curr++;
				}
			}
		}
		return right == Integer.MAX_VALUE ? "" : s.substring(left, right + 1);
	}

}












