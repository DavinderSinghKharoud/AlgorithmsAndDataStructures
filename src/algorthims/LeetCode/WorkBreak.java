package algorthims.LeetCode;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
 **/

import java.util.*;

public class WorkBreak {

	//Time limit Exceed Error
	public static boolean wordBreak(String s, List<String> wordDict) {


		Map<String, Boolean> map = new HashMap<>();

		for (String word : wordDict) {
			map.put(word, true);
		}
		return check(s, wordDict, map, 0);
	}

	public static boolean check(String s, List<String> wordDict, Map<String, Boolean> map, int start) {

		if (start == s.length()) {
			return true;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = start; i < s.length(); i++) {
			sb.append(s.charAt(i));

			if (map.get(sb.toString()) != null) {

				if (check(s, wordDict, map, i + 1)) return true;

			}
		}
		return false;
	}

		public static void main(String[] args ) {
			List<String> lst = new ArrayList<>();
			lst.add("leet");
			lst.add("code");

			System.out.println(wordBreak2("leetcode", lst));

		}
		
    public static boolean wordBreak2(String s, List<String> wordDict) {
	int len = s.length();
	boolean dp[] = new boolean[ len + 1];
	Set<String> set = new HashSet<>( wordDict );
	dp[0] = true;
	
	for( int low = 0; low<len; low++ ){
	    if( !dp[low] ) continue;
	    
	    for( int high = low + 1; high<=len; high ++ ){
		String str = s.substring( low, high );
		if( set.contains( str )){ dp[high] = true; }
		
	    }
	}
	
	return dp[ len ];
	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}



