package algorthims.LeetCode;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 */

import java.util.*;

public class AllAnagramsinaString {

	//Time limit exceeded
	public static List<Integer> findAnagrams(String s, String p) {
		
		List<Integer> res = new ArrayList<>();
		int len = p.length();
		
		for( int i = 0; i<s.length(); i++ ){
		
			if( i + len - 1 < s.length() && check( s, p, i, i + len - 1 ) ){
				res.add( i );
			}
		}

		return res;
    }
    
    public static boolean check( String s, String p, int start, int end ){
		char []temp_s = s.substring( start, end + 1).toCharArray();
		char []temp_p = p.toCharArray();

		Arrays.sort( temp_s );
		Arrays.sort( temp_p );

		return new String( temp_s ).equals( new String( temp_p ) );
		
		
	}
    
	public static void main (String[] args) {

		System.out.print( findAnagrams(  "cbaebabacd" , "abc"));

	}
}

