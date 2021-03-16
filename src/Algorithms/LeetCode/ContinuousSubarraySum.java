package Algorithms.LeetCode;

import java.util.*;

/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 *
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 */
public class ContinuousSubarraySum {
    public static void main(String[] args) {
        System.out.println(checkSubarraySum(new int[]{0, 0}, -1));
    }

    public static boolean checkSubarraySum(int[] nums, int k) {

        // if a%k == x and b%k == x then (a - b) %k = 0
        if (k == 0) {

            for(int i = 1; i < nums.length; i++){
                if(nums[i] == 0 && nums[i - 1] == 0){
                    return true;
                }
            }
            return false;
        }
        Set<Integer> set = new HashSet<>();
        int prefixSum = 0, sum = 0;
        for (int num : nums) {
             sum = (sum + num) % k;
            if (set.contains(sum))
                return true;
            set.add(prefixSum);
            prefixSum = sum;
        }

        return false;
    }
}
