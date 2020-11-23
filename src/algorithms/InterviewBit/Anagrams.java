package algorithms.InterviewBit;

import java.util.*;

/**
 * Given an array of strings, return all groups of strings that are anagrams. Represent a group by a list of integers representing the index in the original list. Look at the sample case for clarification.
 *
 *  Anagram : a word, phrase, or name formed by rearranging the letters of another, such as 'spar', formed from 'rasp'
 *  Note: All inputs will be in lower-case.
 * Example :
 *
 * Input : cat dog god tca
 * Output : [[1, 4], [2, 3]]
 * cat and tca are anagrams which correspond to index 1 and 4.
 * dog and god are another set of anagrams which correspond to index 2 and 3.
 * The indices are 1 based ( the first element has index 1 instead of index 0).
 */
public class Anagrams {

    public static ArrayList<ArrayList<Integer>> anagrams(final List<String> lst) {

		Map<String,ArrayList<Integer>> map = new HashMap<>();
		
		for( int index = 0; index < lst.size(); index++ ){
			//We do not need sorting after if we use this technique
			char[] arr = lst.get(index).toCharArray();
			Arrays.sort(arr);
			String curr = new String(arr);

			if( map.get(curr) != null ){
				map.get(curr).add(index + 1);
			}else{
				ArrayList<Integer> temp = new ArrayList<>();
				temp.add(index + 1);
				map.put(curr, temp);
			}
		}

		//		res.sort(((o1, o2) -> {
//			int len = Math.min(o1.size(), o2.size());
//
//			for (int index = 0; index < len; index++) {
//				int num1 = o1.get(index);
//				int num2 = o2.get(index);
//
//				if (num1 == num2) continue;
//				if (num1 < num2) {
//					return -1;
//				} else {
//					return 1;
//				}
//			}
//
//			if( o1.size() == o2.size() ) return 0;
//			return (o1.size() > o2.size()) ? 1 : -1;
//		}));
		return new ArrayList<>(map.values());
    }
    
    public static String getStandard( String s ){
		char[] arr = new char[26];
		
		for( int index = 0; index < s.length(); index++ ){
			char c = s.charAt(index);
			arr[c - 'a']++;
		}
		
		StringBuilder sbr = new StringBuilder();
		
		for( char c: arr ){
			sbr.append(c);
		}
		
		return sbr.toString();
	}
    public static void main(String[] args) {

		System.out.println( anagrams(Arrays.asList("cat", "dog", "god", "tca")));
    }
}
