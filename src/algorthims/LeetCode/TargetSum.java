//package algorthims.LeetCode;

import java.util.*;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example 1:
 * <p>
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 **/

public class TargetSum {

    public static int findTargetSumWays(int[] nums, int S) {

        Map<String, Integer> dp = new HashMap<>();

        return helper(nums, 0, S, dp);

    }

    public static int helper(int[] nums, int index, int sum, Map<String, Integer> dp) {

        if (index == nums.length) {

            return sum == 0 ? 1 : 0;
        }

        if (dp.get(index + "," + sum) != null) {
            return dp.get(index + "," + sum);
        }

        int add = helper(nums, index + 1, sum + nums[index], dp);
        int subtract = helper(nums, index + 1, sum - nums[index], dp);

        dp.put(index + "," + sum, add + subtract);

        return dp.get(index + "," + sum);
    }

    public static void main(String[] args) {

        System.out.println(findTargetSumWays2(new int[]{
                1, 1, 1, 1, 1
        }, 3));
    }

    public static int findTargetSumWays2(int[] nums, int S) {

        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;

        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000 ; sum++) {

                if( dp[ sum + 1000] > 0 ){

                    next[ sum + 1000 + nums[i]] += dp[ sum + 1000];
                    next[ sum + 1000 - nums[i]] += dp[ sum + 1000];

                }
            }

            dp = next;
        }

        return S > 1000? 0: dp[ S + 1000];
    }
}
