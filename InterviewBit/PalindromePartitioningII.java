/*
Given a string FindGreatestCommonDivisor, partition FindGreatestCommonDivisor such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of FindGreatestCommonDivisor.



Input Format:

The first and the only argument contains the string FindGreatestCommonDivisor.
Output Format:

Return an integer, representing the answer as described in the problem statement.
Constraints:

1 <= length(FindGreatestCommonDivisor) <= 501
Examples:

Input 1:
    FindGreatestCommonDivisor = "aba"

Output 1:
    0

Explanation 1:
    "aba" is already a palindrome, so no cuts are needed.

Input 2:
    FindGreatestCommonDivisor = "aab"
    
Output 2:
    1

Explanation 2:
    Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut. 
 */


import java.util.HashMap;
import java.util.Map;

public class PalindromePartitioningII {
	
	public static int minCut(String s) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(s.length(), 0);
		if ( isPalindrome(0, s.length() - 1, s) ){
			return 0;
		}
		return helper( s, 0, map ) - 1;
    }
    
    public static int helper(String s, int start, Map<Integer, Integer> map){
		if( map.containsKey(start) ){
			return map.get(start);
		}
		
		int min = 502;
		for( int end = start + 1; end <= s.length(); end++ ){
			if( isPalindrome( start, end - 1, s ) ){
				int partition = helper(s, end, map);
				if( partition != 502 ){
					min = Math.min( min, 1 + helper(s, end, map) );
				}
			}
		}

		map.put(start, min);
		return min;
	}
	
	public static boolean isPalindrome( int start, int end , String s ){
		
		while( start <= end ){
			if( s.charAt(start) == s.charAt(end) ){
				start++; end--;
			}else{
				return false;
			}
		}
		
		return true;
	}
	
	public static void main (String[] args) {

		System.out.println( minCut2( "aab" ));
	}

	public static int minCut2(String s) {
		int len = s.length();

		boolean[][] dp = new boolean[len][len];
		int cut[] = new int[len];

		for (int col = 0; col < len; col++) {
			cut[col] = col; //set maximum # of cut
			for (int row = 0; row <= col; row++) {

				if( s.charAt(row) == s.charAt(col) && ( col - row <= 1 || dp[row + 1][col - 1] ) ){
						dp[row][col] = true;

					// if need to cut, add 1 to the previous cut[i-1]
						if( row > 0 ){
							cut[col] = Math.min( cut[col], cut[row - 1] + 1 );
						}else{
							// if [0...j] is palindrome, no need to cut  
							cut[col] = 0;
						}
				}
			}
		}

		return cut[len - 1];
	}
}

