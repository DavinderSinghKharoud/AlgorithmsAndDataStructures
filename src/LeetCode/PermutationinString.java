package LeetCode;/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False
 */

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 */

public class PermutationinString {
	
	public static boolean checkInclusion(String s1, String s2) {
		if( s1.length() > s2.length() ){ return false;}
		char[] count1 = new char[26];
		
		for( int index = 0; index < s1.length(); index++ ){
			count1[ s1.charAt(index) - 'a' ]++;
		}
		
		for( int index = 0; index<= s2.length() - s1.length(); index++ ){
			
			char[] count2 = new char[26];
			
			for( int j = 0; j < s1.length(); j++ ){
				count2[ s2.charAt( index + j )  - 'a']++;
			}
			
			if( matches(count1, count2) ){
				return true;
			}
		}
		
		return false;
    }
    
    public static boolean matches( char[] map1, char[] map2 ){
		for( int i = 0; i<26; i++ ){
			if(map1[i] != map2[i] ){
				return false;
			}
		}
		
		return true;
	}
	public static void main (String[] args) {
		
		System.out.println( checkInclusion( "adc", "dcda"));
	}
}

