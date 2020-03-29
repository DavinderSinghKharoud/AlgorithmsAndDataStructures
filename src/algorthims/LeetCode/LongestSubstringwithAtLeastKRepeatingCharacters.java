
/**
 * 95. Longest Substring with At Least K Repeating Characters
Medium

1150

98

Add to List

Share
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.**/

public class LongestSubstringwithAtLeastKRepeatingCharacters{
     public static int longestSubstring(String s, int k) {
        
	if( s.length() < k){
	    return 0;
	}
	int[] alp = new int[26];
	int count = 0;
	
	for( char c: s.toCharArray() ){
	    int temp = ++alp[ c - 'a'];
	    if( temp == 1 ) count++;
	    if( temp == k ) count--;
	}
	
	if( count == 0 ) return s.length();
	int maxLen = 0;
	StringBuilder sb = new StringBuilder();
	
	for( char c: s.toCharArray() ){
	    
	    if( alp[ c - 'a' ] < k ){
		maxLen = Math.max( maxLen, longestSubstring(sb.toString(), k ));
		sb = new StringBuilder();
	    }else{
		sb.append( c );
	    }
	    
	}
	
	
    maxLen = Math.max( maxLen, longestSubstring( sb.toString(), k) );
    return maxLen;
    }
    public static void main(String[] args) {
	
	System.out.println( longestSubstring("aaabbcbbeccd", 2));
	    
    }
}
