package algorithms.InterviewBit;

import java.util.*;

public class RootToLeafPathsWithSum {

    public static int[][] pathSum(TreeNode root, int target) {

		ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
		
		traverse(root, target, lst, new ArrayList<>());
		
		int[][] res = new int[lst.size()][];
		int resIndex = 0;
		for( ArrayList<Integer> curr: lst ){
			int[] temp = new int[curr.size()];
			int index = 0;
			for(int num: curr ){
				temp[index++] = num;
			}
			res[resIndex++] = temp;
		}
		
		return res;
    }
    public static void traverse(TreeNode root, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> lst) {

        if (root == null) return;
        if (target - root.val == 0 && root.left == null && root.right == null){
            lst.add(root.val);
			res.add(new ArrayList<>(lst));
			lst.remove(lst.size() - 1);
			return;
		}

		lst.add(root.val);
        traverse( root.left, target - root.val, res, lst);
        traverse( root.right, target - root.val, res, lst);
        lst.remove( lst.size() - 1);
        
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4 );
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.right = new TreeNode(2);

        int[][] res = pathSum(root, 22);

        for( int[] curr: res ){
            for(int num: curr){
                System.out.println(num);
            }
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
