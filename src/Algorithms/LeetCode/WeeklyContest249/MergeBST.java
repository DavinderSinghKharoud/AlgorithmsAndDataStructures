package Algorithms.LeetCode.WeeklyContest249;

import java.util.*;

public class MergeBST {
    public static void main(String[] args) {

        MergeBST o = new MergeBST();
        TreeNode a = new TreeNode(2, new TreeNode(1), null);
        TreeNode b = new TreeNode(3, new TreeNode(2), new TreeNode(5));
        TreeNode c = new TreeNode(5, new TreeNode(4), null);


        TreeNode node = o.canMerge(Arrays.asList(a, b, c));
        System.out.println(node.toString());
    }

    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        for (TreeNode node : trees) {
            map.put(node.val, node);
            count.put(node.val, count.getOrDefault(node.val, 0) + 1);
            if (node.left != null) {
                count.put(node.left.val, count.getOrDefault(node.left.val, 0) + 1);
            }
            if (node.right != null) {
                count.put(node.right.val, count.getOrDefault(node.right.val, 0) + 1);
            }
        }

        // The unique node can be taken as root node of a tree
        for (TreeNode node : trees) {
            if (count.getOrDefault(node.val, -1) == 1) {
                // Take as a root node
                TreeNode root = map.get(node.val);
                if (root == null)
                    return null;
                map.remove(node.val);
                boolean isPoss = traverse(root, map, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (isPoss && map.size() == 0)
                    return root;
                break;
            }
        }
        return null;
    }

    boolean traverse(TreeNode node, Map<Integer, TreeNode> map, int min, int max) {
        if (node == null)
            return true;
        if (node.val <= min || node.val >= max)
            return false;
        // Check if it is a leaf node
        if (node.left == null && node.right == null && map.containsKey(node.val)) {
            // We can have matching
            TreeNode match = map.get(node.val);
            node.left = match.left;
            node.right = match.right;
            map.remove(node.val);
        }

        return traverse(node.left, map, min, node.val) && traverse(node.right, map, node.val, max);
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

}
