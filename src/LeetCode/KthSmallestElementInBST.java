package LeetCode;

import java.util.ArrayList;
import java.util.Stack;

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

    private static int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        while (true){

            while ( root!= null){
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if( --k == 0){
                return root.val;
            }
            root = root.right;
        }

    }
    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(kthSmallest2(root, 1));
    }
}
