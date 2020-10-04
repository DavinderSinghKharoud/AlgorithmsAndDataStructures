package algorthims.InterviewBit;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TwoSumBinaryTree {

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

    public static int t2Sum(TreeNode root, int target) {

        Set<Integer> set = new HashSet<>();
        return (helper(root, target, set)) ? 1 : 0;
    }

    //O(n) time and space complexity
    public static boolean helper(TreeNode root, int target, Set<Integer> set) {
        if (root == null) return false;

        int curr = root.val;

        if (set.contains(target - curr)) return true;
        set.add(curr);

        return helper(root.left, target, set) || helper(root.right, target, set);
    }


    //O(n) time complexity and O(height) space complexity
    public static int t2Sum2(TreeNode root, int target) {
            if (root == null) return 0;
            if( root.left == null && root.right == null ) return 0;

            return (traverse(root, target, new Stack<>())) ? 1 : 0;
        }

        private static boolean traverse(TreeNode root, int target, Stack<Integer> stack) {

            if (root == null) return false;
            if (traverse(root.left, target, stack)) return true;

            int remaining = target - root.val;

            if (remaining >= 0 && stack.isEmpty()) {
                stack.push(remaining);
            } else if (remaining >= 0) {
                if (stack.peek() == root.val) return true;
                else {
                    while (!stack.isEmpty() && stack.peek() < root.val) stack.pop();
                    if (!stack.isEmpty() && stack.peek() == root.val) return true;
                    stack.push(remaining);
                }


            }
            return traverse(root.right, target, stack);
        }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        //root.left = new TreeNode(1);
        root.right = new TreeNode(10);

        System.out.println(t2Sum2(root, 11));
    }
}
