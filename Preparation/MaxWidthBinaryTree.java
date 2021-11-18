package Preparation;

import java.util.*;

/**
 * Given the root of a binary tree, return the maximum width of the given tree.
 *
 * The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of 32-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 *
 *
 * Input: root = [1,3,null,5,3]
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 *
 *
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 */
public class MaxWidthBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        long ans = 1;
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, root));
        while (!queue.isEmpty()) {
            int len = queue.size();
            long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                Node curr = queue.poll();
                min = Math.min(min, curr.val);
                max = Math.max(max, curr.val);
                if (curr.node.left != null) {
                    Node left = new Node(curr.val * 2, curr.node.left);
                    queue.add(left);
                }
                if (curr.node.right != null) {
                    Node right = new Node(curr.val * 2 + 1, curr.node.right);
                    queue.add(right);
                }
            }

            if (len > 1) {
                //System.out.println(min + " " + max);
                ans = Math.max(ans, max - min + 1);
            }
        }
        return (int) ans;
    }

    static class Node {
        TreeNode node;
        long val;

        public Node(long val, TreeNode node) {
            this.val = val;
            this.node = node;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
