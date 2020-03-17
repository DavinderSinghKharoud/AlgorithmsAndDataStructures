package algorthims.LeetCode;

import java.util.ArrayList;

public class KthSmallestElementInBST {


    public static int kthSmallest(TreeNode root, int k) {

        ArrayList<Integer> lst = backTrack(root, new ArrayList<Integer>());
        return lst.get( k-1);
    }

    private static ArrayList<Integer> backTrack(TreeNode root, ArrayList<Integer> lst) {

        if (root == null) {
            return lst;
        }
        backTrack(root.left, lst);
        lst.add(root.val);
        backTrack(root.right, lst);
        return lst;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(kthSmallest(root, 1));
    }
}
