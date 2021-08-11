package LeetCode;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
	
	//O(n square) time complexity
	public static int lengthOfLongestSubstring(String s) {
		int max = 0;
		
		for( int i = 0; i<s.length(); i++ ){
		    Map<Character,Integer> map = new HashMap<>();
		    int count = 0;
		    
		    for( int j = i; j<s.length(); j++ ){
			if( map.get(s.charAt(j) ) != null ){break;}
			
			    map.put( s.charAt(j), 1 );
			    count++;	
			    
			    max = Math.max( max, count );
		    }
		    
		    map.clear();	
		}
		
		return max;
	}
	
	public static void main (String[] args) {
		System.out.println( lengthOfLongestSubstring3("dvdf"));
	}
	

	
	//O(n) More Efficient 
	public static int lengthOfLongestSubstring2(String s) {
	    
		Map<Character,Integer> map = new HashMap<>();
		int max = 0;
		int len = s.length();

		for (int i = 0, j = 0; i < len; i++) {
			if( map.containsKey(s.charAt(i))){
				j = Math.max(j, map.get(s.charAt(i)));

			}
			max = Math.max( max, i - j + 1);
			map.put( s.charAt(i), i + 1);
		}

		return max;
	}

	public static int lengthOfLongestSubstring3(String s) {
		int n = s.length(), ans = 0;
		int[] index = new int[128]; // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			i = Math.max(index[s.charAt(j)], i);
			ans = Math.max(ans, j - i + 1);
			index[s.charAt(j)] = j + 1;
		}
		return ans;
	}
}

