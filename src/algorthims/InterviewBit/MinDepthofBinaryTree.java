package algorthims.InterviewBit;

public class MinDepthofBinaryTree {

    public static int minDepth(TreeNode root) {
		if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        if (root.left == null) {
            return minDepth(root.right) + 1;
        } else if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;

        
    }

    public static void main(String[] args) {

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
