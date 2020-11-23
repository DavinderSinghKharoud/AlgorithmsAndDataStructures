package algorithms.InterviewBit;/*
 Given two strings A and B, find the minimum number of steps required to convert A to B. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character


Input Format:

The first argument of input contains a string, A.
The second argument of input contains a string, B.
Output Format:

Return an integer, representing the minimum number of steps required.
 */


public class EditDistance {
	
	public static int minDistance(String a, String b) {
		int len1 = a.length();
		int len2 = b.length();
		
		int[][] dp = new int[len1 + 1][len2 + 1];
		
		for(int count = 1; count <= len1; count++ ){
			dp[count][0] = count;
		}	
		
		for(int count = 1; count <= len2; count++ ){
			dp[0][count] = count;
		}
		
		for(int row = 0; row < len1; row++ ){
			
			char c1 = a.charAt(row);
			for(int col = 0; col < len2; col++ ){
				
				char c2 = b.charAt(col);
					
				if( c1 == c2 ){
					dp[row + 1][col + 1] = dp[row][col];
				}else{
					
					dp[row + 1][col + 1] = Math.min( dp[row][col], Math.min( dp[row + 1][col], dp[row][col + 1] ) ) + 1;
				}
			}
		}
		
		return dp[len1][len2];
    }
	public static void main (String[] args) {

		System.out.println( minDistance("aaa", "a"));


	}
}

