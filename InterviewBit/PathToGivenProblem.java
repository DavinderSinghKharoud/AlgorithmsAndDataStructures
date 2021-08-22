package InterviewBit;

import java.util.*;

/**
 * Given a Binary Tree FindGreatestCommonDivisor containing N nodes.
 *
 * You need to find the path from Root to a given node FindUniqueBinaryString.
 *
 * NOTE:
 *
 * No two nodes in the tree have same data values.
 * You can assume that FindUniqueBinaryString is present in the tree FindGreatestCommonDivisor and a path always exists.
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 *
 * 1 <= Data Values of Each Node <= N
 *
 * 1 <= FindUniqueBinaryString <= N
 *
 *
 *
 * Input Format
 * First Argument represents pointer to the root of binary tree FindGreatestCommonDivisor.
 *
 * Second Argument is an integer FindUniqueBinaryString denoting the node number.
 *
 *
 *
 * Output Format
 * Return an one-dimensional array denoting the path from Root to the node FindUniqueBinaryString in order.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  FindGreatestCommonDivisor =
 *
 *            1
 *          /   \
 *         2     3
 *        / \   / \
 *       4   5 6   7
 *
 *
 * FindUniqueBinaryString = 5
 *
 * Input 2:
 *
 *  FindGreatestCommonDivisor =
 *             1
 *           /   \
 *          2     3
 *         / \ .   \
 *        4   5 .   6
 *
 *
 * FindUniqueBinaryString = 1
 *
 *
 *
 *
 * Example Output
 * Output 1:
 *
 *  [1, 2, 5]
 * Output 2:
 *
 *  [1]
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  We need to find the path from root node to node with data value 5.
 *  So the path is 1 -> 2 -> 5 so we will return [1, 2, 5]
 * Explanation 2:
 *
 *  We need to find the path from root node to node with data value 1.
 *  As node with data value 1 is the root so there is only one node in the path.
 *  So we will return [1]
 */
public class PathToGivenProblem {

    public static int[] solve(TreeNode root, int target) {

        List<Integer> lst = new ArrayList<>();

        find(lst, root, target);

        int len = lst.size();
        int[] res = new int[len];
        for (int index = len - 1; index >= 0; index--) {
            res[len - 1 - index] = lst.get(index);
        }

        return res;
    }

    public static boolean find(List<Integer> lst, TreeNode root, int target) {
        if (root == null) return false;

        if (root.val == target) {
            lst.add(root.val);
            return true;
        }
        boolean left = find(lst, root.left, target);
        boolean right = find(lst, root.right, target);

        if (left || right) {
            lst.add(root.val);
            return true;
        }

        return false;

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        for(int num: solve(root, 5)){
            System.out.println(num);
        }
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
