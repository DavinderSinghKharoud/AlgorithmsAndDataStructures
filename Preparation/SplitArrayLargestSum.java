package Preparation;

import java.util.Arrays;

public class SplitArrayLargestSum {

    /**
     * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
     * <p>
     * Write an algorithm to minimize the largest sum among these m subarrays.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [7,2,5,10,8], m = 2
     * Output: 18
     * Explanation:
     * There are four ways to split nums into two subarrays.
     * The best way is to split it into [7,2,5] and [10,8],
     * where the largest sum among the two subarrays is only 18.
     * Example 2:
     * <p>
     * Input: nums = [1,2,3,4,5], m = 2
     * Output: 9
     * Example 3:
     * <p>
     * Input: nums = [1,4,4], m = 3
     * Output: 4
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 106
     * 1 <= m <= min(50, nums.length)
     *
     * @param args
     */
    public static void main(String[] args) {
        SplitArrayLargestSum splitArrayLargestSum = new SplitArrayLargestSum();
        System.out.println(splitArrayLargestSum.splitArray(new int[]{1, 2, 3, 4, 5}, 2));
    }

    int[] prefix;
    int[][] dp;
    int len;

    public int splitArray(int[] nums, int m) {
        len = nums.length;
        prefix = new int[len];
        dp = new int[len][m + 1];
        for (int[] state : dp) {
            Arrays.fill(state, -1);
        }
        prefix[0] = nums[0];
        for (int i = 1; i < len; i++) {
            prefix[i] += prefix[i - 1] + nums[i];
        }
        if (m == 1) return prefix[len - 1];
        return find(nums, 0, m);
    }

    int find(int[] nums, int index, int m) {
        if (m == 1) {
            return getSum(index, len - 1);
        } else {
            //try all
            if (index == len) return 0;
            if (dp[index][m] != -1) return dp[index][m];
            int min = Integer.MAX_VALUE;
            for (int i = index; i < len; i++) {
                int sum = getSum(index, i);
                sum = Math.max(sum, find(nums, i + 1, m - 1));
                min = Math.min(min, sum);
            }
            return dp[index][m] = min;
        }
    }

    int getSum(int a, int b) {
        if (a > b) return 0;
        if (a - 1 < 0) return prefix[b];
        return prefix[b] - prefix[a - 1];
    }

    /**
     * Binary search
     */
    public int splitArray2(int[] nums, int m) {
        len = nums.length;
        prefix = new int[len];

        prefix[0] = nums[0];
        for (int i = 1; i < len; i++) {
            prefix[i] += prefix[i - 1] + nums[i];
        }

        int start = 0, end = prefix[len - 1];
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isPossible(mid, nums, m)) {
                end = mid;
            } else start = mid + 1;
        }
        return end;
    }

    boolean isPossible(int max, int[] nums, int m) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] > max) return false;
            if (sum > max) {
                sum = nums[i];
                m--;
            }
            if (m <= 0) return false;
        }
        return true;
    }
}
