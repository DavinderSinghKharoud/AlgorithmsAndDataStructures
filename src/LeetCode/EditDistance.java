/*
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 * 
 */


public class EditDistance {

	//O(n square) time complexity and space complexity
	public static int minDistance(String word1, String word2) {
        
        int[][] dp = new int[ word1.length() + 1 ][ word2.length() + 1];
        
        for( int i = 0; i<= word1.length(); i++ ){
			dp[i][0] = i;
		}
		
		for( int i = 0; i<= word2.length(); i++ ){
			dp[0][i] = i;
		}
		
		for( int i = 0; i< word1.length(); i++ ){
			char c1 = word1.charAt(i);
			for( int j = 0; j< word2.length(); j++ ){
				char c2 = word2.charAt(j);
				
				if( c1 == c2 ){
					dp[i + 1][j + 1] = dp[i][j];
				}else{
					dp[i + 1][j + 1] = Math.min( Math.min( dp[i][j], dp[i + 1][j]), dp[i][j + 1] ) + 1;
				}
			}
		}
		
		return dp[ word1.length() ][ word2.length() ];
    }
	public static void main (String[] args) {
		System.out.println( minDistance("", "a"));
	}
}

