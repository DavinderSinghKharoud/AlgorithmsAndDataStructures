package Algorithms.LeetCode;

import java.util.*;

/**
 * Given an integer array nums, return the sum of floor(nums[i] / nums[j]) for all pairs of indices 0 <= i, j < nums.length in the array. Since the answer may be too large, return it modulo 109 + 7.
 *
 * The floor() function returns the integer part of the division.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,9]
 * Output: 10
 * Explanation:
 * floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
 * floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
 * floor(5 / 2) = 2
 * floor(9 / 2) = 4
 * floor(9 / 5) = 1
 * We calculate the floor of the division for every pair of indices in the array then sum them up.
 * Example 2:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 49
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class SumOfFlooredPairs {

    public static void main(String[] args) {
        SumOfFlooredPairs o = new SumOfFlooredPairs();
        System.out.println(o.sumOfFlooredPairs(new int[]{1, 1, 1, 2, 4}));
    }

    int mod = (int) 1e9 + 7, limit = (int) 1e5;

    public int sumOfFlooredPairs(int[] nums) {
        long res = 0;
        int len = nums.length;

        int[] prefix = new int[limit + 1], count = new int[limit + 1];
        for (int num : nums) {
            count[num]++;
        }

        for (int i = 1; i <= limit; i++) {
            prefix[i] = prefix[i - 1] + count[i];
        }

        long oneRes = -1;
        for (int i = 0; i < len; i++) {
            int curr = nums[i];
            if (curr == 1 && oneRes != -1) {
                res = add(res, oneRes);
                continue;
            }
            long sum = 0;
            // We try every bucket for each number
            for (int j = 1; curr * j <= limit; j++) {
                int lower = curr * j - 1, upper = curr * (j + 1) - 1;
                sum = add(sum, mul(prefix[Math.min(limit, upper)] - prefix[lower], j));
            }
            if( curr == 1){
                oneRes = sum;
            }
            res = add(res, sum);
        }
        return (int) res;
    }

    public long add(long a, long b) {
        return (a + b) % mod;
    }

    public long mul(long a, long b) {
        return (a * b) % mod;
    }
}
