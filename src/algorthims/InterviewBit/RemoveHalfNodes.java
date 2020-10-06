package algorthims.InterviewBit;

import Tree.Node;

/**
 * Problem Description
 *
 * Given a binary tree A with N nodes.
 *
 * You have to remove all the half nodes and return the final binary tree.
 *
 * NOTE:
 *
 * Half nodes are nodes which have only one child.
 * Leaves should not be touched as they have both children as NULL.
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 *
 *
 *
 * Input Format
 * First and only argument is an pointer to the root of binary tree A.
 *
 *
 *
 * Output Format
 * Return a pointer to the root of the new binary tree.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *            1
 *          /   \
 *         2     3
 *        /
 *       4
 *
 * Input 2:
 *
 *             1
 *           /   \
 *          2     3
 *         / \     \
 *        4   5     6
 *
 *
 * Example Output
 * Output 1:
 *
 *            1
 *          /    \
 *         4      3
 * Output 2:
 *
 *             1
 *           /   \
 *          2     6
 *         / \
 *
 *        4   5
 *
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  The only half node present in the tree is 2 so we will remove this node.
 * Explanation 2:
 *
 *  The only half node present in the tree is 3 so we will remove this node.
 */
public class RemoveHalfNodes {

    public static TreeNode solve(TreeNode root) {
		
		if( root == null ) return null;
		
		if( root.left == null && root.right != null ) return solve( root.right );
		
		if( root.right == null && root.left != null ) return solve( root.left );
		
		root.left = solve( root.left );
		root.right = solve( root.right );
		
		return root;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        TreeNode res = solve(root);
        inOrder(res);

    }

    static void inOrder ( TreeNode node){

        if( node == null ){
            return;
        }

        inOrder( node.left );
        System.out.print( node.val + " ");
        inOrder( node.right );
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
