//package algorthims.LeetCode;


import java.util.*;

public class LowestCommonAncestorOfaBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static TreeNode ans;

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        backtrack(root, p, q);
        return ans;
    }

    private static boolean backtrack(TreeNode current, TreeNode p, TreeNode q) {
        if (current == null) return false;

        int left = backtrack(current.left, p, q) ? 1 : 0;
        int right = backtrack(current.right, p, q) ? 1 : 0;

        int mid = (current == p || current == q) ? 1 : 0;

        if (mid + left + right >= 2) ans = current;

        return mid + left + right > 0;

    }

    public static void main(String[] args) {


        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);

        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);

        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);

        root.right.right = new TreeNode(8);

        lowestCommonAncestor(root, root.left.left, root.right.left);

        System.out.println(ans.val);
    }
}
