package algorthims.LeetCode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class BinaryTreeMaximumPathSum {

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
	public static void main (String[] args) {
			
			TreeNode root = new TreeNode(-10);
			root.left = new TreeNode( 9 );
			root.right = new TreeNode( 20 );
			root.right.left = new TreeNode( 15 );
			root.right.right = new TreeNode( 7 );
			
			System.out.println( maxPathSum( root ));
	}

	public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}

