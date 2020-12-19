package Algorithms.LeetCode.Mock;

public class deleteNodeBST {

    //O( Log(n) ) time complexity and space complexity depend upon height of the binary tree
    //When we need to replace the key with the value, there are two options either we can replace with Maximum value from left tree or minimum value from right tree.
    public static TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) return root;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            //It means equal
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right == null) {
                root = root.left;
            } else if (root.left == null) {
                root = root.right;
            } else {
                //both left and right exists
                int leftMax = findMax(root.left);
                root.val = leftMax;
                root.left = deleteNode(root.left, leftMax);
            }

        }

        return root;
    }

    private static int findMax(TreeNode root) {
        if (root.right == null) {
            return root.val;
        }

        return findMax(root.right);
    }


    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(6);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.right = new TreeNode(7);

        TreeNode root = deleteNode(treeNode, 3);

        inOrder(root);

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

    static void inOrder(TreeNode node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

}