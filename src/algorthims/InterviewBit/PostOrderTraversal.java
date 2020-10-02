package algorthims.InterviewBit;

import java.util.*;

/**
 * Given a binary tree, return the postorder traversal of its nodes’ values.
 *
 * Example :
 *
 * Given binary tree
 *
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [3,2,1].
 */
public class PostOrderTraversal {

    public static int[] postorderTraversal(TreeNode root) {

        Stack<Integer> helper = new Stack<>();
        
        Stack<TreeNode> stack = new Stack<>();
        
        stack.add(root);
        
        while( !stack.isEmpty() ){
			TreeNode curr = stack.pop();
			helper.add(curr.val);
			
			if( curr.left != null ){
				stack.add(curr.left);
			}
			
			if( curr.right != null ){
				stack.add(curr.right);
			}
			
		}
		
		int[] res = new int[helper.size()];
		int index = 0;
		while( !helper.isEmpty() ){
			res[index++] = helper.pop();
		}
		return res;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int[] res = postorderTraversal(root);
        for(int num: res){
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
