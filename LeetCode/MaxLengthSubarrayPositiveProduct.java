package LeetCode;

/**
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 * <p>
 * CheckAllAsAppearsBs subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 * <p>
 * Return the maximum length of a subarray with positive product.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 * Example 2:
 * <p>
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 * Example 3:
 * <p>
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class MaxLengthSubarrayPositiveProduct {
    public int getMaxLen(int[] nums) {
        int len = nums.length;
        Integer lastNegative = null;
        int negatives = 0;
        int ans = 0;

        int start = 0;
        for (int end = 0; end < len; end++) {
            int curr = nums[end];
            int temp = 0;
            if (curr > 0) {
                if (negatives % 2 == 0) {
                    temp = end - start + 1;
                } else {
                    temp = end - lastNegative;
                }
            } else if (curr < 0) {
                negatives++;
                if (lastNegative == null) lastNegative = end;
                if (negatives % 2 == 0) {
                    temp = end - start + 1;
                } else {
                    temp = end - lastNegative;
                }
            } else {
                //Reset at 0
                start = end + 1;
                lastNegative = null;
                negatives = 0;
            }
            ans = Math.max(ans, temp);
        }
        return ans;
    }
}
