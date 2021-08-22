package InterviewBit;

import java.util.*;

public class UniqueBinarySearchTree {

    /**
     Given FindGreatestCommonDivisor, generate all structurally unique BST’s (binary search trees) that store values 1…FindGreatestCommonDivisor.

     Input Format:

     The first and the only argument of input contains the integer, FindGreatestCommonDivisor.
     Output Format:

     Return a list of tree nodes representing the generated BST's.
     Constraints:

     1 <= FindGreatestCommonDivisor <= 15
     Example:

     Input 1:
     FindGreatestCommonDivisor = 3

     Output 1:

     1         3     3      2      1
     \       /     /      / \      \
     3     2     1      1   3      2
     /     /       \                 \
     2     1         2                 3
     */

    //O( n is to power n ) time complexity
    public static ArrayList<TreeNode> generateTrees(int num) {

        return helper(1, num);
    }

    public static ArrayList<TreeNode> helper(int start, int end) {
        if (start > end) {
            return null;
        }

        ArrayList<TreeNode> lst = new ArrayList<>();

        if (start == end) {
            lst.add(new TreeNode(start));
            return lst;
        }

        for (int count = start; count <= end; count++) {
            ArrayList<TreeNode> lefts = helper(start, count - 1);
            ArrayList<TreeNode> rights = helper(count + 1, end);
            if (lefts == null) {
                for (TreeNode right : rights) {

                    TreeNode curr = new TreeNode(count);
                    curr.right = right;
                    lst.add(curr);
                }
                continue;
            }

            if (rights == null) {
                for (TreeNode left : lefts) {

                    TreeNode curr = new TreeNode(count);
                    curr.left = left;
                    lst.add(curr);
                }
                continue;
            }
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {

                    TreeNode curr = new TreeNode(count);
                    curr.left = left;
                    curr.right = right;
                    lst.add(curr);
                }
            }
        }

        return lst;
    }

    public static void main(String[] args) {

        ArrayList<TreeNode> lst = generateTrees(3);

        for (TreeNode curr : lst) {
            System.out.println(curr.val);
        }
    }

    //I think so, O(n square ) time and space complexity
    public static ArrayList<TreeNode> generateTrees2(int num) {
        ArrayList[][] dp = new ArrayList[num + 1][ num + 1];
        return helper2(1, num, dp);
    }

    public static ArrayList<TreeNode> helper2(int start, int end, ArrayList<TreeNode>[][] dp) {
        if (start > end) {
            return null;
        }

        ArrayList<TreeNode> lst = new ArrayList<>();

        if (start == end) {
            lst.add(new TreeNode(start));
            return lst;
        }

        if( dp[start][end] != null ){
            return dp[start][end];
        }

        for (int count = start; count <= end; count++) {
            ArrayList<TreeNode> lefts = helper(start, count - 1);
            ArrayList<TreeNode> rights = helper(count + 1, end);
            if (lefts == null) {
                for (TreeNode right : rights) {

                    TreeNode curr = new TreeNode(count);
                    curr.right = right;
                    lst.add(curr);
                }
                continue;
            }

            if (rights == null) {
                for (TreeNode left : lefts) {

                    TreeNode curr = new TreeNode(count);
                    curr.left = left;
                    lst.add(curr);
                }
                continue;
            }
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {

                    TreeNode curr = new TreeNode(count);
                    curr.left = left;
                    curr.right = right;
                    lst.add(curr);
                }
            }
        }

        dp[start][end] = lst;
        return lst;
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
