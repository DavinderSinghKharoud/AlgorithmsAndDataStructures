package LeetCode.WeeklyContest270;

import java.util.*;

/**
 * My SubmissionsBack to Contest
 * User Accepted: 3451
 * User Tried: 3742
 * Total Accepted: 3523
 * Total Submissions: 5097
 * Difficulty: Easy
 * RingsAndRods distinct string is a string that is present only once in an array.
 * <p>
 * Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are fewer than k distinct strings, return an empty string "".
 * <p>
 * Note that the strings are considered in the order in which they appear in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = ["d","b","c","b","c","a"], k = 2
 * Output: "a"
 * Explanation:
 * The only distinct strings in arr are "d" and "a".
 * "d" appears 1st, so it is the 1st distinct string.
 * "a" appears 2nd, so it is the 2nd distinct string.
 * Since k == 2, "a" is returned.
 * Example 2:
 * <p>
 * Input: arr = ["aaa","aa","a"], k = 1
 * Output: "aaa"
 * Explanation:
 * All strings in arr are distinct, so the 1st string "aaa" is returned.
 * Example 3:
 * <p>
 * Input: arr = ["a","b","a"], k = 3
 * Output: ""
 * Explanation:
 * The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= arr.length <= 1000
 * 1 <= arr[i].length <= 5
 * arr[i] consists of lowercase English letters.
 */
public class kthDistinctString {
    public String kthDistinct(String[] arr, int k) {
        int len = arr.length;
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            String curr = arr[i];
            Integer index = map.get(curr);
            if (index == null) {
                map.put(curr, i);
                ans[i] = 1;
            } else {
                ans[index]++;
                ans[i] = -1;
            }
        }

        int curr = 0;
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 1) {
                curr++;
                if (curr == k) return arr[i];
            }
        }
        return "";
    }
}
