package algorithms.LeetCode;

import java.util.*;

/**
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 * <p>
 * Return the maximum possible length of s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * Example 2:
 * <p>
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * Example 3:
 * <p>
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lower case English letters.
 */
public class MaximumLengthConcatenatedStringUnique {

    static int max = 0;

    //O( number of unique Strings ) Time Complexity and O(1) Space Complexity
    public static int maxLength(List<String> arr) {
        dfs(arr, 0, "");
        return max;

    }

    private static void dfs(List<String> arr, int start, String s) {
        if (!isUnique(s)) return;
        max = Math.max(max, s.length());

        for (int index = start; index < arr.size(); index++) {
            dfs(arr, index + 1, s + arr.get(index));
        }

    }

    private static boolean isUnique(String s) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            if (arr[c - 'a'] > 0) return false;
            arr[c - 'a']++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.print(maxLength(Arrays.asList("a", "abc", "d", "de", "def")));
    }


    //Although the time complexity and space complexity is same as above, it is faster.
    public int maxLength2(List<String> arr) {
        List<Integer> maskList = new ArrayList<>();

        for (String s : arr) {
            int mask = 0;
            for (int index = 0; index < s.length(); index++) {
                int flag = 1 << (s.charAt(index) - 'a');
                //Check if it contains duplicate
                if ((mask & flag) != 0) {
                    mask = 0; //it means there is a duplicate
                    break;
                }
                mask |= flag;
            }

            //Only add valid unique mask
            if (mask != 0) maskList.add(mask);
        }

        traverse(0, 0, maskList);
        return max;
    }

    private void traverse(int target, int mask, List<Integer> maskList) {
        //Count the number of 1's which represents the unique characters
        max = Math.max(max, Integer.bitCount(mask));
        for (int index = target; index < maskList.size(); index++) {
            if ((maskList.get(index) & mask) != 0) continue;
            traverse(index + 1, mask | maskList.get(index), maskList);
        }
    }
}
