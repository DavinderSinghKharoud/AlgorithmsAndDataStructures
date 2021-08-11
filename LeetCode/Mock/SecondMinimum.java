package LeetCode.Mock;

/**
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 *
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead.
 *
 * Example 1:
 *
 * Input:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 *
 *
 * Example 2:
 *
 * Input:
 *     2
 *    / \
 *   2   2
 *
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 */

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SecondMinimum {
    //O(number of nodes in the tree) time complexity and O(2) space complexity
    public static int findSecondMinimumValue(TreeNode root) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        if (root == null) {
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        maxHeap.add(root.val);
        while (!queue.isEmpty()) {

            TreeNode curr = queue.poll();
            if (curr.left != null) {
                queue.add(curr.left);
                if( !maxHeap.contains(curr.left.val) ){
                    maxHeap.add(curr.left.val);
                }

            }
            if (curr.right != null) {
                queue.add(curr.right);
                if( !maxHeap.contains(curr.right.val) ){
                    maxHeap.add(curr.right.val);
                }
            }

            if (maxHeap.size() > 2) {
                while (maxHeap.size() > 2) {
                    maxHeap.poll();
                }
            }
        }


        return (maxHeap.size() < 2) ? -1: maxHeap.peek();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println(findSecondMinimumValue2(root));
    }

    static int min1;
    static long ans = Long.MAX_VALUE;

    public static void dfs(TreeNode root) {
        if (root != null) {
            if (min1 < root.val && root.val < ans) {
                ans = root.val;
            } else if (min1 == root.val) {
                dfs(root.left);
                dfs(root.right);
            }
        }
    }
    public static int findSecondMinimumValue2(TreeNode root) {
        min1 = root.val;
        dfs(root);
        return ans < Long.MAX_VALUE ? (int) ans : -1;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }
}
