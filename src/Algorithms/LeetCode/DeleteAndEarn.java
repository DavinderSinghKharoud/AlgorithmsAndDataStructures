package Algorithms.LeetCode;

/**
 * Given an array nums of integers, you can perform operations on the array.
 *
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
 *
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 *
 * Example 1:
 *
 * Input: nums = [3, 4, 2]
 * Output: 6
 * Explanation:
 * Delete 4 to earn 4 points, consequently 3 is also deleted.
 * Then, delete 2 to earn 2 points. 6 total points are earned.
 *
 *
 * Example 2:
 *
 * Input: nums = [2, 2, 3, 3, 3, 4]
 * Output: 9
 * Explanation:
 * Delete 3 to earn 3 points, deleting both 2's and the 4.
 * Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 * 9 total points are earned.
 *
 *
 * Note:
 *
 * The length of nums is at most 20000.
 * Each element nums[i] is an integer in the range [1, 10000].
 */
public class DeleteAndEarn {

    //Time complexity O(n) and Space complexity O(1000)
    public static int deleteAndEarn(int[] nums) {

        //We will modify and update the same array
        int[] dp = new int[1000];
        for(int num: nums ){
            dp[num - 1]++;
        }

        dp[1] = Math.max(dp[0], dp[1] * 2);
        for (int index = 2; index < dp.length; index++) {
            dp[index] = Math.max( dp[index - 2] + dp[index] * (index + 1), dp[index - 1]);
        }
        return dp[dp.length - 1];
    }


    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{1,1,1}));
    }
}
