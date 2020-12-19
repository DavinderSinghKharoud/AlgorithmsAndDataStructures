package Algorithms.InterviewBit;

import java.util.*;

/**
 * Given a binary tree
 *
 *     struct TreeLinkNode {
 *       TreeLinkNode *left;
 *       TreeLinkNode *right;
 *       TreeLinkNode *next;
 *     }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *  Note:
 * You may only use constant extra space.
 * Example :
 *
 * Given the following binary tree,
 *
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 * After calling your function, the tree should look like:
 *
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 *  Note 1: that using recursion has memory over
 */
public class PopulateNextRightPointersTree {

    public static void connect(TreeLinkNode root) {
		
		if( root == null ) return;
		Queue<TreeLinkNode> queue = new LinkedList<>();
		
		queue.add( root );
		
		while( !queue.isEmpty() ){
			
			int len = queue.size();
			
			for( int count = 0; count < len; count++ ){
				TreeLinkNode curr = queue.poll();
				
				if( queue.peek() != null && count != len - 1 ){
					curr.next = queue.peek();
				}
				
				if( curr.left != null ){
					queue.add( curr.left );
				}
				
				if( curr.right != null ){
					queue.add( curr.right );
				}
			}
		}
		
		
    }

    public static void main(String[] args) {

    	TreeLinkNode root = new TreeLinkNode(1);
    	root.left = new TreeLinkNode(2);
    	root.right = new TreeLinkNode(3);
    	root.left.left = new TreeLinkNode(4);
    	root.left.right = new TreeLinkNode(5);
    	root.right.left = new TreeLinkNode(6);
    	root.right.right = new TreeLinkNode(7);

    	connect(root);

		System.out.println(root.right.next);
    }

    static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }
}
