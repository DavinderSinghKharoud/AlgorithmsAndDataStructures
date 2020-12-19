package Algorithms.LeetCode;


public class ValidateBinarySearch {

	public static boolean isValidBST(TreeNode root) {

		if( root == null ){
			return true;
		}
		return helper( root, null, null );
	}

	private static boolean helper( TreeNode root, Integer lower, Integer upper ) {

		if (root == null) {
			return true;
		}

		int val = root.val;
		if( lower != null && val <= lower ) return false;
		if( upper != null && val >= upper ) return false;

		if( !helper( root.right, val, upper ) ) return false;
		if( !helper( root.left, lower, val )) return false;

		return true;
	}

	public static void main(String[] args) {
			TreeNode root = new TreeNode(10);
			root.left = new TreeNode(5);
			root.right = new TreeNode(15);
			root.right.left = new TreeNode(6);
			root.right.right = new TreeNode(20);

		System.out.println( isValidBST( root ) );


	}


	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}