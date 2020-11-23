package algorithms.InterviewBit;

/**
 * Given a string s, partition s such that every string of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * For example, given s = "aab",
 * Return
 *
 *   [
 *     ["a","a","b"]
 *     ["aa","b"],
 *   ]
 *  Ordering the results in the answer : Entry i will come before Entry j if :
 * len(Entryi[0]) < len(Entryj[0]) OR
 * (len(Entryi[0]) == len(Entryj[0]) AND len(Entryi[1]) < len(Entryj[1])) OR
 * *
 * *
 * *
 * (len(Entryi[0]) == len(Entryj[0]) AND … len(Entryi[k] < len(Entryj[k]))
 * In the given example,
 * ["a", "a", "b"] comes before ["aa", "b"] because len("a") < len("aa")
 */

import java.util.*;
public class PalindromePartitioning {

    public static ArrayList<ArrayList<String>> partition(String a) {
		
		int len = a.length();
		
		ArrayList<ArrayList<String>> res = new ArrayList<>();
		
		if( len == 0 ) return res;
		
		ArrayList<String> curr = new ArrayList<>();
		
		helper( res, curr, 0, a );

//		Collections.sort(res, ((o1, o2) -> {
//			ArrayList<String> smaller = (o1.size() < o2.size()) ? o1: o2;
//			ArrayList<String> bigger = ( o1.size() < o2.size()) ? o2: o1;
//
//			for( int index1 = 0; index1 < smaller.size(); index1++ ){
//				int len1 = smaller.get(index1).length();
//				for( int index2 = index1; index2 < bigger.size(); index2++ ){
//					int len2 = bigger.get(index2).length();
//					if( len1 == len2 ) continue;
//					if( len1 > len2 ){
//						return Integer.compare(len1, len2);
//					}else{
//						return Integer.compare(len2, len1);
//					}
//				}
//			}
//			return 0;
//		}));
		return res;
    }
    
    public static void helper( ArrayList<ArrayList<String>> res, ArrayList<String> curr, int start, String a ){
		
		if( start >= a.length() ){
			res.add( new ArrayList<>(curr) );
			return;
		}
		
		for( int end = start; end < a.length(); end++ ){
			if( isPalindrome( a, start, end)){
				curr.add( a.substring(start, end + 1) );
				helper( res, curr, end + 1, a );
				curr.remove( curr.size() - 1);
			}
		}
	}
	
	public static boolean isPalindrome( String a, int start, int end ){
		
		while( start <= end ){
			if( a.charAt(start++) != a.charAt(end--) ){
				return false;
			}
			
		}
		
		return true;
	}

    public static void main(String[] args) {

		System.out.println( partition("aab"));
    }
}
