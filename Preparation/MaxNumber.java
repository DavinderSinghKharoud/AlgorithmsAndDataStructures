package Preparation;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -231 <= nums[i] <= 231 - 1
 */
public class MaxNumber {
    public int majorityElement(int[] nums) {
        int max = nums[0], count = 0;
        for (int num : nums) {
            if (max == num) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    max = num;
                    count = 1;
                }
            }
        }
        return max;
    }
}
