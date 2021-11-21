package Preparation;

/**
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.
 * <p>
 * The length of the path between two nodes is represented by the number of edges between them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,4,5,1,1,5]
 * Output: 2
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,4,5,4,4,5]
 * Output: 2
 */
public class LongestUniversalPath {
    public static void main(String[] args) {

    }

    int ans = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        find(root);
        return ans;
    }

    int find(TreeNode curr) {
        int max = 0;
        int any = 0;
        if (curr.left != null) {
            int left = find(curr.left);
            if (curr.val == curr.left.val) {
                max += left + 1;
                any = left + 1;
            }
        }

        if (curr.right != null) {
            int right = find(curr.right);
            if (curr.val == curr.right.val) {
                max += right + 1;
                any = Math.max(any, right + 1);
            }
        }
        ans = Math.max(ans, max);
        return any;
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
