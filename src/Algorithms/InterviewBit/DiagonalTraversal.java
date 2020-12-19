package Algorithms.InterviewBit;

/**
 * Problem Description
 * <p>
 * Consider lines of slope -1 passing between nodes.
 * <p>
 * Given a Binary Tree A containing N nodes, return all diagonal elements in a binary tree belonging to same line.
 * <p>
 * NOTE:
 * <p>
 * See Sample Explanation for better understanding.
 * Order does matter in the output.
 * To get the same order as in the output traverse the tree same as we do in pre-order traversal.
 * <p>
 * <p>
 * Problem Constraints
 * 0 <= N <= 105
 * <p>
 * <p>
 * <p>
 * Input Format
 * First and only Argument represents the root of binary tree A.
 * <p>
 * <p>
 * <p>
 * Output Format
 * Return a 1D array denoting the diagonal traversal of the tree.
 * <p>
 * <p>
 * <p>
 * Example Input
 * Input 1:
 * <p>
 * 1
 * /   \
 * 4      2
 * / \      \
 * 8   5      3
 * / \    /
 * 9   7  6
 * Input 2:
 * <p>
 * 11
 * /     \
 * 20      12
 * / \       \
 * 1   15      13
 * /  \     /
 * 2    17  16
 * \   /
 * 22 34
 * <p>
 * <p>
 * Example Output
 * Output 1:
 * <p>
 * [1, 2, 3, 4, 5, 7, 6, 8, 9]
 * Output 2:
 * <p>
 * [11, 12, 13, 20, 15, 17, 16, 1, 2, 22, 34]
 * <p>
 * <p>
 * Example Explanation
 * Explanation 1:
 * <p>
 * <p>
 * 1) Diagonal 1 contains [1, 2, 3]
 * 2) Diagonal 2 contains [4, 5, 7, 6]
 * 3) Diagonal 3 contains [8, 9]
 * <p>
 * <p>
 * NOTE:
 * The order in the output matters like for Example:
 * 6 and 7 belong to same diagonal i.e diagonal 2 but as 7 comes before 6 in pre-order traversal so 7 will be added to answer array first.
 * <p>
 * <p>
 * <p>
 * So concantenate all the array as return it as a single one.
 * Final output: [1, 2, 3, 4, 5, 7, 6, 8, 9]
 * <p>
 * Explanation 2:
 * <p>
 * <p>
 * 1) Diagonal 1 contains [11, 12, 13]
 * 2) Diagonal 2 contains [20, 15, 17, 16]
 * 3) Diagonal 2 contains [1, 2, 22, 34]
 * <p>
 * <p>
 * So concantenate all the array as return it as a single one.
 * Final output: [11, 12, 13, 20, 15, 17, 16, 1, 2, 22, 34]
 */

import java.util.*;

public class DiagonalTraversal {


    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(11);
        treeNode.left = new TreeNode(20);
        treeNode.right = new TreeNode(12);
        treeNode.right.right = new TreeNode(13);
        treeNode.right.right.left = new TreeNode(16);

        treeNode.left.right = new TreeNode(15);
        treeNode.left.right.left = new TreeNode(2);
        treeNode.left.right.left.right = new TreeNode(22);


        treeNode.left.right.right = new TreeNode(17);
        treeNode.left.right.right.left = new TreeNode(34);

        treeNode.left.left = new TreeNode(1);

        for (int num : solve2(treeNode)) {
            System.out.println(num);
        }
    }

    //O(n) time complexity
    public static int[] solve(TreeNode root) {
        Map<Integer, List<Integer>> diagonal = new HashMap<>();

        traverse2(root, diagonal, 1);

        List<Integer> lst = new ArrayList<>();
        for (int diagonal_order = 1; true; diagonal_order++) {
            if (!diagonal.containsKey(diagonal_order)) break;
            lst.addAll(diagonal.get(diagonal_order));
        }

        int[] res = new int[lst.size()];
        int index = 0;
        for (int num : lst) {
            res[index++] = num;
        }
        return res;

    }

    private static void traverse2(TreeNode root, Map<Integer, List<Integer>> diagonal, int diagonal_order) {
        if (root == null) return;

        List<Integer> curr = diagonal.get(diagonal_order);
        if (curr == null) {
            curr = new ArrayList<>();
        }
        curr.add(root.val);
        diagonal.put(diagonal_order, curr);

        traverse2(root.left, diagonal, diagonal_order + 1);
        traverse2(root.right, diagonal, diagonal_order);
    }

    public static int[] solve2(TreeNode root) {

        List<Integer> lst = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        addRights(queue, root);

        while ( !queue.isEmpty() ){
            TreeNode curr = queue.remove();
            lst.add(curr.val);

            TreeNode left = curr.left;
            addRights(queue, left);

        }

        int[] res = new int[lst.size()];
        for(int index = 0; index < lst.size(); index++){
            res[index] = lst.get(index);
        }

        return res;
    }

    private static void addRights(Queue<TreeNode> queue, TreeNode root) {
        while ( root !=  null){
            queue.add(root);
            root = root.right;
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
