package Preparation;

import java.util.Arrays;

/**
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 *
 * Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 *
 * It is guaranteed that there will be an answer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 * Example 2:
 *
 * Input: nums = [44,22,33,11,1], threshold = 5
 * Output: 44
 * Example 3:
 *
 * Input: nums = [21212,10101,12121], threshold = 1000000
 * Output: 1
 * Example 4:
 *
 * Input: nums = [2,3,5,7,11], threshold = 11
 * Output: 3
 */
public class FindSmallestDivisor {
    public static void main(String[] args) {
        System.out.println(new FindSmallestDivisor().smallestDivisor(new int[]{
                2,3,5,7,11
        }, 11));
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int start = 0, end = Arrays.stream(nums).max().getAsInt();
        while (start < end) {
            int mid = start + (end - start) / 2;
           // System.out.println(mid);
            if (isPossGreater(mid, nums, threshold)) {
                start = mid + 1;
            } else end = mid;
        }

        return start;
    }

    boolean isPossGreater(int div, int[] nums, int threshold) {
        //return true if sum is greater than threshold
        int sum = 0;
        for (int num : nums) {
            int rem = (int) Math.ceil((double) num / div);
            sum += rem;
            if (sum > threshold) return true;
        }
        return false;
    }
}
