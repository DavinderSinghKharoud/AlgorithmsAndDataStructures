package Preparation;

import java.util.*;

/**
 * You are given an integer array nums with no duplicates. FindingThreeDigitEvenNumbers maximum binary tree can be built recursively from nums using the following algorithm:
 * <p>
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [3,2,1,6,0,5]
 * Output: [6,3,5,null,2,0,null,null,1]
 * Explanation: The recursive calls are as follow:
 * - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
 * - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
 * - Empty array, so no child.
 * - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
 * - Empty array, so no child.
 * - Only one element, so child is a node with value 1.
 * - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
 * - Only one element, so child is a node with value 0.
 * - Empty array, so no child.
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [3,2,1]
 * Output: [3,null,2,null,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * All integers in nums are unique.
 */
public class MaximumBinaryTree {

    public static void main(String[] args) {
        System.out.println(new MaximumBinaryTree().constructMaximumBinaryTree(
                new int[]{3, 2, 1, 6, 0, 5}
        ));
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int num : nums) {
            TreeNode curr = new TreeNode(num);
            while (!stack.isEmpty() && stack.peekFirst().val < num) {
                curr.left = stack.pollFirst();
            }
            if (!stack.isEmpty()) {
                stack.peekFirst().right = curr;
            }
            stack.addFirst(curr);
        }
        return stack.peekLast();
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
