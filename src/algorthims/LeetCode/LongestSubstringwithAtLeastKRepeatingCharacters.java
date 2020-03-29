import java.util.*;
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
	
	System.out.println( longestSubstring2("aaabbcbbeccd", 2));
	    
    }
    
    //More efficient
    
     public static int longestSubstring2(String s, int k) {

		 char[] c = s.toCharArray();
		 return help(c, 0, c.length - 1, k);
	 }

	public static int help(char[] c, int left, int right, int k) {
		if (right < left) return 0;
		int[] count = new int[256];
		for (int i = left; i <= right; i ++ ) {
			count[c[i]] ++ ;
		}
		HashSet<Character> set = new HashSet<>();
		for (int i = 0; i < 256; i ++ ) {
			if (count[i] > 0 && count[i] < k ) set.add((char)i);
		}
		if (set.isEmpty()) return right - left + 1;

		int max = 0;
		int start = left;
		for (int i = left; i <= right; i ++ ) {
			if (set.contains(c[i]) ) {
				max = Math.max(max, help(c, start, i - 1, k));
				start = i + 1;
			}
			else if (i == right) {
				max = Math.max(max, help(c, start, i, k));
			}
		}
		return max;
	}
}
