package Algorithms.InterviewBit;

import java.util.*;

public class BSTIterator {

	Stack<TreeNode> stack;
	
    public BSTIterator(TreeNode root) {
		
		stack = new Stack<>();
		addLefts(root);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
		return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
		
		if( stack.isEmpty() ) return 0;
		
		TreeNode curr = stack.pop();
		addLefts(curr.right);
		
		return curr.val;
		
    }
    
    public void addLefts( TreeNode root ){
		while( root != null ){
			stack.add(root);
			root = root.left;
		}
	}

    public static void main(String[] args) {

        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false
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
