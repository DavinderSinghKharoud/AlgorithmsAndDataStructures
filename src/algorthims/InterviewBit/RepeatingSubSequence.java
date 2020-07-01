/*
Given a string A, find length of the longest repeating sub-sequence such that the two subsequence don’t have same string character at same position,

i.e., any i’th character in the two subsequences shouldn’t have the same index in the original string.

NOTE: Sub-sequence length should be greater than or equal to 2.



Problem Constraints
1 <= |A| <= 100



Input Format
The first and the only argument of input contains a string A.



Output Format
Return an integer, 0 or 1:

    => 0 : False
    => 1 : True


Example Input
Input 1:

 A = "abab"
Input 2:

 A = "abba"

 */


public class RepeatingSubSequence {
	
	public static int anytwo(String s) {
		
		int len = s.length();
		
		if( len == 0 ) return 0;
		
		int[][] dp = new int[len + 1][len + 1];
		
		for(int row = 1; row <= len; row++ ){
			
			for(int col = 1; col <= len; col++ ){
				
				if( s.charAt(row - 1) == s.charAt(col - 1) && row != col ){
					dp[row][col] = 1 + dp[row - 1][col - 1];
				}else{
					
					dp[row][col] = Math.max( dp[row - 1][col], dp[row][col - 1] );
				}
			}
		}
		
		return (dp[len][len] > 1) ? 1: 0 ;
	}
	
	public static void main (String[] args) {
		
		System.out.println( anytwo("aaaa"));
	}
}

