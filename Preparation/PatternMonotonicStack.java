package Preparation;

import java.util.Stack;

public class PatternMonotonicStack {

    /**
     * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
     * <p>
     * Return true if there is a 132 pattern in nums, otherwise, return false.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,2,3,4]
     * Output: false
     * Explanation: There is no 132 pattern in the sequence.
     * Example 2:
     * <p>
     * Input: nums = [3,1,4,2]
     * Output: true
     * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
     * Example 3:
     * <p>
     * Input: nums = [-1,3,2,0]
     * Output: true
     * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
     * <p>
     * <p>
     * Constraints:
     * <p>
     * n == nums.length
     * 1 <= n <= 2 * 105
     * -109 <= nums[i] <= 109
     */
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        Stack<Integer> stack = new Stack<>();
        int s3 = Integer.MIN_VALUE;
        for (int i = len - 1; i >= 0; i--) {
            int curr = nums[i];
            if (curr < s3) return true;
            while (!stack.isEmpty() && stack.peek() < curr) {
                s3 = stack.pop();
            }
            stack.add(curr);
        }
        return false;
    }
}
