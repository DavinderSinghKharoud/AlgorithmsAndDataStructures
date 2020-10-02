package algorthims.InterviewBit;

public class BinaryBalancedTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);

        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        System.out.println(isBalanced(root));

    }


    public static int isBalanced(TreeNode root) {


        return (helper(root) == 0)? 0: 1;
    }

    public static int helper(TreeNode root) {
        if (root == null) {
            return 1;
        }

        int leftHeight = helper(root.left);

        int rightHeight = helper(root.right);

        if( leftHeight == 0 || rightHeight == 0 ){
            return 0;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return 0;
        }

        return Math.max(leftHeight, rightHeight) + 1;
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
