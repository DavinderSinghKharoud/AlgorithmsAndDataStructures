package InterviewBit;

import java.util.*;

/**
 * Given a Binary Tree FindGreatestCommonDivisor consisting of N nodes.
 *
 * You need to find all the cousins of node FindUniqueBinaryString.
 *
 * NOTE:
 *
 * Siblings should not be considered as cousins.
 * Try to do it in single traversal.
 * You can assume that Node FindUniqueBinaryString is there in the tree FindGreatestCommonDivisor.
 * Order doesn't matter in the output.
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 *
 * 1 <= FindUniqueBinaryString <= N
 *
 *
 *
 * Input Format
 * First Argument represents the root of binary tree FindGreatestCommonDivisor.
 *
 * Second Argument is an integer FindUniqueBinaryString denoting the node number.
 *
 *
 *
 * Output Format
 * Return an integer array denoting the cousins of node FindUniqueBinaryString.
 *
 * NOTE: Order doesn't matter.
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
 *  [6, 7]
 * Output 2:
 *
 *  []
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  Cousins of Node 5 are Node 6 and 7 so we will return [6, 7]
 *  Remember Node 4 is sibling of Node 5 and do not need to return this.
 * Explanation 2:
 *
 *  Since Node 1 is the root so it doesn't have any cousin so we will return an empty array.
 */
public class CousinsInaBinaryTree {

    public static int[] solve(TreeNode root, int target) {
		
		Queue<TreeNode> queue = new LinkedList<>();
		List<Integer> lst = new ArrayList<>();
		boolean isFound = false;
		queue.add(root);
		
		while( !queue.isEmpty() ){
			
			int len = queue.size();
			lst.clear();
			for( int count = 0; count < len; count++ ){
				TreeNode curr = queue.poll();

				if( curr.val == target ){
					isFound = true;
				}else{
					lst.add(curr.val);
				}
				
				if( curr.left != null){
					if(curr.right == null || curr.right.val != target){
						queue.add(curr.left);
					}
				}
				
				if( curr.right != null ){
					if(curr.left == null || curr.left.val != target){
						queue.add(curr.right);
					}
				}
				
				
			}
			
			if( isFound ) break;
		}
		
		int[] res = new int[lst.size()];
		
		for( int index = 0; index < lst.size(); index++ ){
			res[index] = lst.get(index);
		}
		
		return res;
		
    }

    public static void main(String[] args) {

    	TreeNode root = new TreeNode(1);
    	root.left = new TreeNode(2);
    	root.right = new TreeNode(3);
    	root.left.left = new TreeNode(4);
    	root.left.right = new TreeNode(5);
    	root.right.left = new TreeNode(6);
    	root.right.right = new TreeNode(7);

		for( int num: solve(root, 1)){
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
