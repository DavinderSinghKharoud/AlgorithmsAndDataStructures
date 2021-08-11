package InterviewBit;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 *
 * Example :
 *
 * Given the below binary tree and sum = 22,
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 *
 * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
 */
public class PathSum {

    public static int hasPathSum(TreeNode root, int target) {

        return (traverse(root, target)) ? 1 : 0;
    }

    public static boolean traverse(TreeNode root, int target) {

        if (root == null) return false;
        if (target - root.val == 0 && root.left == null && root.right == null) return true;

        return traverse(root.left, target - root.val) || traverse(root.right, target - root.val);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1000);
        root.left = new TreeNode(200);
//        root.right = new TreeNode(8);
//        root.left.left = new TreeNode(11);
//        root.right.left = new TreeNode(13);
//        root.right.right = new TreeNode(4);
//        root.left.left.right = new TreeNode(2);

        System.out.println(hasPathSum(root, 22));
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
