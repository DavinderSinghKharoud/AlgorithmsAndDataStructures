package Preparation;

import java.util.*;

/**
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * FindingThreeDigitEvenNumbers subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 *
 * Example 1:
 *
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 * Example 2:
 *
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 */
public class NumberMatchingSubsequence {
    Map<Character, List<String>> map;

    /**
     * O(n * 50) we only can traverse 50 words for each character
     */
    public int numMatchingSubseq(String s, String[] words) {
        int ans = 0;
        int len = s.length();
        map = new HashMap<>();

        for (String word : words) {
            char first = word.charAt(0);
            map.putIfAbsent(first, new ArrayList<>());
            map.get(first).add(word);
        }

        for (int index = 0; index < len; index++) {
            char curr = s.charAt(index);
            if (map.containsKey(curr)) {
                List<String> wordLst = map.get(curr);
                map.put(curr, new ArrayList<>());
                for (String word : wordLst) {
                    if (word.length() == 1) {
                        ans++;
                    } else {
                        word = word.substring(1);
                        char firstChar = word.charAt(0);
                        map.putIfAbsent(firstChar, new ArrayList<>());
                        map.get(firstChar).add(word);
                    }
                }
            }
        }

        return ans;
    }
}
