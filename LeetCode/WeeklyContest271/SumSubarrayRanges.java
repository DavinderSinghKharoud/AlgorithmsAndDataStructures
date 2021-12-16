package LeetCode.WeeklyContest271;

import java.util.*;

/**
 * You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
 * <p>
 * Return the sum of all subarray ranges of nums.
 * <p>
 * NumbersAscending subarray is a contiguous non-empty sequence of elements within an array.
 */
public class SumSubarrayRanges {
    public static void main(String[] args) {
        SumSubarrayRanges o = new SumSubarrayRanges();
    }

    public long subArrayRanges(int[] nums) {
        long sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int min = nums[i], max = nums[i];
            for (int j = i + 1; j < len; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                sum += (max - min);
            }
        }
        return sum;
    }
}
