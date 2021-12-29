package LeetCode;

import java.util.TreeMap;

/**
 * You are given a 0-indexed integer array nums and an integer k.
 * <p>
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 * <p>
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
 * <p>
 * Return the maximum score you can get.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
 * Example 2:
 * <p>
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
 * Example 3:
 * <p>
 * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length, k <= 105
 * -104 <= nums[i] <= 104
 */
public class JumpGameVI {
    public static void main(String[] args) {
        System.out.println(new JumpGameVI().maxResult(
                new int[]{1, -5, -20, 4, -1, 3, -6, -3}, 0
        ));
    }

    //O(n log(n))
    public int maxResult(int[] nums, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int len = nums.length;
        if (len == 1) return nums[0];
        if (k == 0) return 0;

        int[] dp = new int[len];
        dp[len - 1] = nums[len - 1];
        treeMap.put(nums[len - 1], 1);
        int end = len - 1;

        for (int start = len - 2; start >= 0; start--) {
            int curr = nums[start];
            if (end - start > k) {
                remove(treeMap, dp[end--]);
            }
            dp[start] = curr + treeMap.lastKey();
            add(treeMap, dp[start]);
        }
        return dp[0];
    }

    private void add(TreeMap<Integer, Integer> treeMap, int val) {
        treeMap.put(val, treeMap.getOrDefault(val, 0) + 1);
    }

    void remove(TreeMap<Integer, Integer> treeMap, int val) {
        int count = treeMap.get(val) - 1;
        if (count == 0) treeMap.remove(val);
        else treeMap.put(val, count);
    }
}
