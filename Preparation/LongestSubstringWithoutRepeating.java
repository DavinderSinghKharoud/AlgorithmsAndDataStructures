package Preparation;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 *
 * Input: s = ""
 * Output: 0
 */
public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int start = 0;
        int[] arr = new int[129];
        int a = 'a';
        int ans = 0;
        for (int end = 0; end < len; end++) {
            char c = s.charAt(end);
            arr[c]++;

            while (areDuplicate(arr) && start < end) {
                arr[s.charAt(start++)]--;
            }

            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }

    boolean areDuplicate(int[] arr) {
        for (int num : arr) {
            if (num > 1) return true;
        }
        return false;
    }
}
