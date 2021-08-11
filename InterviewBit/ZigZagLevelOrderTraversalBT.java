package InterviewBit;

import java.util.*;

public class ZigZagLevelOrderTraversalBT {

    public static int[][] zigzagLevelOrder(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> lst = new ArrayList<>();
        boolean reverse = true;
        queue.add(root);

        while (!queue.isEmpty()) {

            int len = queue.size();
            reverse = !reverse;
            List<Integer> temp = new ArrayList<>();

            for (int count = 0; count < len; count++) {

                TreeNode currNode = queue.poll();
                temp.add(currNode.val);

                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }

                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }

            }

            if (reverse) {
                Collections.reverse(temp);
            }

            lst.add(temp);
        }

        int[][] res = new int[lst.size()][];

        for (int index = 0; index < lst.size(); index++) {
            List<Integer> curr = lst.get(index);
            int[] temp = new int[curr.size()];
            for (int i = 0; i < curr.size(); i++) {
                temp[i] = curr.get(i);
            }
            res[index] = temp;
        }
        return res;

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);


        int[][] lst = zigzagLevelOrder(root);

        for (int[] nums : lst) {
            for (int n : nums) {
                System.out.println(n);
            }
            System.out.println();
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
