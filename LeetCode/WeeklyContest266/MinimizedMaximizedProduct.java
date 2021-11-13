package LeetCode.WeeklyContest266;

import java.util.*;

/**
 * You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:
 *
 * nums.length == n
 * nums[i] is a positive integer where 0 <= i < n.
 * abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
 * The sum of all the elements of nums does not exceed maxSum.
 * nums[index] is maximized.
 * Return nums[index] of the constructed array.
 *
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, index = 2,  maxSum = 6
 * Output: 2
 * Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
 * There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].
 * Example 2:
 *
 * Input: n = 6, index = 1,  maxSum = 10
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= maxSum <= 109
 * 0 <= index < n
 */
public class MinimizedMaximizedProduct {
    public static void main(String[] args) {
        MinimizedMaximizedProduct o = new MinimizedMaximizedProduct();
        System.out.println(o.minimizedMaximum(6, new int[]{11, 6}));
    }

    public int minimizedMaximum(int n, int[] quantities) {
        int len = quantities.length;
        if (n == len) {
            return Arrays.stream(quantities).max().getAsInt();
        }

        long sum = Arrays.stream(quantities).asLongStream().sum();
        long start = 0, end = sum;

        while (start < end) {
            long mid = start + (end - start) / 2;

            if (isPossible(mid, n, quantities)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return (int) end;
    }

    boolean isPossible(long mid, long n, int[] quantities) {

        for (int quantity : quantities) {
            if (quantity <= mid) {
                n--;
            } else {
                long div = quantity / mid;
                n -= div;
                if (div * mid != quantity) n--;
            }
            if (n < 0)
                return false;
        }

        return true;
    }
}
