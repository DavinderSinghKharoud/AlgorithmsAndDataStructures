package algorthims.LeetCode;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        //inOrder(root);

        TreeNode inv = invertTree(root);

        inOrder(inv);

    }

    static void inOrder(TreeNode node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    private static TreeNode invertTree(TreeNode root) {

        if( root == null ){
            return null;
        }

        TreeNode right = invertTree( root.right );
        TreeNode left = invertTree( root.left );

        root.left = right;
        root.right = left;

        return root;
    }
}


