/*
Given a binary tree T, find the maximum path sum.

The path may start and end at any node in the tree.

Input Format:

The first and the only argument contains a pointer to the root of T, A.
Output Format:

Return an integer representing the maximum sum path.
Constraints:

1 <= Number of Nodes <= 7e4
-1000 <= Value of Node in T <= 1000
Example :

Input 1:

       1
      / \
     2   3

Output 1:
     6

Explanation 1:
    The path with maximum sum is: 2 -> 1 -> 3

Input 2:
    
       -10
       /  \
     -20  -30

Output 2:
    -10

Explanation 2
    The path with maximum sum is: -10
 */


public class MaxSumPathInBinaryTree {

    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        int[] max = {Integer.MIN_VALUE};
        helper(root, max);
        return max[0];
    }

    public static int helper(TreeNode root, int[] max) {

        if (root == null) {
            return 0;
        }

        int left = helper(root.left, max);
        int right = helper(root.right, max);
        int curr = Math.max( root.val, Math.max(root.val + left, root.val + right) );

        max[0] = Math.max( max[0], Math.max(curr, left + right + root.val ) );
        return curr;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(-20);
        root.right = new TreeNode(-30);


        System.out.println( maxPathSum(root) );
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

}

