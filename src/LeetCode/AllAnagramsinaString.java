package LeetCode;

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
		
			if( i + len - 1 < s.length() && check3( s, p, i, i + len - 1 ) ){
				res.add( i );
			}
		}

		return res;
    }
    //Time Limit Exceeded
    public static boolean check( String s, String p, int start, int end ){
		char []temp_s = s.substring( start, end + 1).toCharArray();
		char []temp_p = p.toCharArray();

		Arrays.sort( temp_s );
		Arrays.sort( temp_p );

		return new String( temp_s ).equals( new String( temp_p ) );
		
		
	}
    //Time Limit Exceeded
	public static boolean check2( String s, String p, int start, int end ){
		char []temp_s = s.substring( start, end + 1).toCharArray();
		char []temp_p = p.toCharArray();

		int[] count = new int[26];

		for( char c: temp_s ){
			count[ c - 'a' ]++;
		}

		StringBuilder sbr_s = new StringBuilder();

		for( int value: count ){
			sbr_s.append("#").append(value);
		}

		Arrays.fill( count, 0 );
		StringBuilder sbr_p = new StringBuilder();

		for (char c : temp_p) {
			count[ c - 'a' ]++;
		}

		for (int value : count) {
			sbr_p.append("#").append( value );
		}

		return sbr_s.toString().equals( sbr_p.toString() );

	}

	//accepted
	public static boolean check3( String s, String p, int start, int end ){

		int[] count = new int[26];

		for( int i = start; i<= end; i++ ){
			count[ s.charAt(i) - 'a' ]++;
		}

		for (int i = 0; i < p.length(); i++) {
			if( count[ p.charAt(i) - 'a' ] >= 1 ){
				count[ p.charAt(i) - 'a']--;
			}
		}

		for (int num: count ) {
			if( num != 0 ){
				return false;
			}
		}

		return true;

	}
	public static void main (String[] args) {

		System.out.print( findAnagrams2(  "cbaebabacd" , "abc"));

	}

	public static List<Integer> findAnagrams2(String s, String p) {

		List<Integer> res = new ArrayList<>();

		int[] char_count = new int[26];

		for( char c: p.toCharArray() ){
			char_count[ c - 'a' ] ++;
		}

		int left = 0;
		int right = 0;
		int count = p.length();

		while ( right < s.length() ){

			if( char_count[ s.charAt( right++)  - 'a']-- >= 1 ){
				count--;
			}

			if( count == 0 ) res.add( left );

			if( right - left == p.length() && char_count[ s.charAt(left ++ ) - 'a' ]++ >= 0 ){
				count++;
			}
		}

		return res;
	}

	}

