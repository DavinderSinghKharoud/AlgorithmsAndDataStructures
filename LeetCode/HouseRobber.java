package LeetCode;
/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint
 * stopping you from robbing each of them is that adjacent houses have security
 * system connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 */

import java.util.Arrays;

public class HouseRobber {
    static int []record;

    public static int rob(int[] nums) {

        record = new int[ nums.length + 1];

        Arrays.fill( record, 0 ,record.length,-1);

        return Math.max( solve( nums, 0), solve( nums, 1));
    }

    private static int solve(int[] nums, int index) {

        if( index>= nums.length){
            return 0;
        }

        if( record[index] != -1){
            return record[index];
        }

        int odd = solve( nums, index + 2);
        int even = solve( nums, index + 3);

        record[index] = nums[index] + Math.max( odd, even);
        return record[index];
    }

    public static void main(String[] args) {

        System.out.println( rob(new int[]{
                1,2,3,1
        }));
    }
}
