package Algorithms.LeetCode; /**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 **/

import java.util.*;

public class WordBreakII {

    static List<String> res ;
    static Map<Integer, Boolean> map;
    //O(n square ) time complexity and O(m + n square) space complexity according to me
    public static List<String> wordBreak(String s, List<String> wordDict) {

        res = new ArrayList<>();
        map = new HashMap<>();
        //to get the maximum length of the string in wordDict, so that we can stop the recursion
		int max = 0;
        Set<String> set = new HashSet<>(wordDict);
		
		for( String curr: wordDict ){
			max = Math.max( max, curr.length() );
		}
	
		build( s, set,"", 0, max );

        return res;
    }


    public static boolean build(String s, Set<String> set, String curr, int index, int max){
		
		if( index == s.length() ){
			res.add( curr.trim() );
			return true;
		}
		
		if( map.containsKey( index ) && !map.get( index ) ){ return false;}
		
		boolean check = false;
		
		for( int i = index + 1; i <= s.length() && i - index <= max; i++ ){
			String sub = s.substring( index, i );
			if( set.contains( sub ) && build(s, set, curr + " " +sub , i, max )){
				check = true;
			}
		}
		
		//if the index cannot lead to the result, we do not need to check that again in recursion
		if( !check ){ map.put( index, false ); }
		
		return true;
	}

    public static void main(String[] args) {

        System.out.println( wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }
}

