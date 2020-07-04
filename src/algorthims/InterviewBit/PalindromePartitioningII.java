/*
Given a string A, partition A such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of A.



Input Format:

The first and the only argument contains the string A.
Output Format:

Return an integer, representing the answer as described in the problem statement.
Constraints:

1 <= length(A) <= 501
Examples:

Input 1:
    A = "aba"

Output 1:
    0

Explanation 1:
    "aba" is already a palindrome, so no cuts are needed.

Input 2:
    A = "aab"
    
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

		System.out.println( minCut( "aabcb" ));
	}
}

