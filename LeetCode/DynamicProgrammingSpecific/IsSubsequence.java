/*
Given a string s and a string t, check if s is subsequence of t.

FindGreatestCommonDivisor subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.

 

Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false
 * 
 */


public class IsSubsequence {
	
	//O(n * m) time complexity and O( min(n,m) ) space complexity
	public static boolean isSubsequence(String s, String t) {
		if( s.length() == 0 ) return true;
		return helper( s, t, 0, 0);
    }
    
    public static boolean helper( String s, String t, int index1, int index2 ){
		if( index1 == s.length() ){
			return true;
		}
		
		if( index2 == t.length() ){
			return false;
		}
		
		char c = s.charAt(index1);
		
		for( index2 = index2; index2 < t.length(); index2++ ){
			if( c == t.charAt(index2) ){
				return helper(s, t, index1 + 1, index2 + 1 );
			}
		}
		
		return false;
	}
	
	//O(n * m ) time complexity
	public static boolean isSubsequence2( String s, String t){
		if( s.length() == 0 ) return true;
		int j = 0;
		
		for( int i = 0; i < t.length() && j < s.length(); i++ ){
			if( s.charAt(j) == t.charAt(i) ){
				j++;
			}
		}
		
		if( j == s.length() ){
			return true;
		}
		
		return false;
	}
	
	//O(n * m ) time complexity and space complexity
	public static boolean isSubsequence3( String s, String t){
		int len1 = s.length();
		int len2 = t.length();
		
		boolean[][] dp = new boolean[ len1 + 1][len2 + 1];
		
		for( int col = 0; col <= len2; col++ ){
			dp[0][col] = true;
		}
		
		for( int row = 1; row <= len1; row++ ){
			for( int col = 1; col <= len2; col++ ){
				dp[row][col] = ( s.charAt(row - 1) == t.charAt(col - 1) && dp[row - 1][col - 1] ) || dp[ row ][ col - 1];
			}
		}
		
		return dp[len1][len2];
	}
	public static void main (String[] args) {
		
		System.out.println( isSubsequence3("abc", "ahbgdc"));
	}
}

