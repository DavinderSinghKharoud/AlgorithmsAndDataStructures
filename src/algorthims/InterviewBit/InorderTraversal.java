package algorthims.InterviewBit;

import java.util.*;
public class InorderTraversal {

    public static void main(String[] args) {

    	TreeNode root = new TreeNode(1);
    	root.left = new TreeNode(2);
    	root.right = new TreeNode(3);
    	root.left.left = new TreeNode(4);
    	root.left.right = new TreeNode(5);

		System.out.println( inorderTraversal(root) );
    }

    public static ArrayList<Integer> inorderTraversal(TreeNode root) {
		
		ArrayList<Integer> res = new ArrayList<>();
		
		Stack<TreeNode> stack = new Stack<>();
		
		addLeft(root, stack);
		
		while( !stack.isEmpty() ){
			TreeNode curr = stack.pop();
			res.add(curr.val);
			
			if( curr.right != null ){
				addLeft(curr.right, stack);
			}
		}
		
		return res;
		
    }
    
    public static void addLeft( TreeNode root, Stack<TreeNode> stack ){
		
		while( root != null ){
			stack.add(root);
			root = root.left;
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
