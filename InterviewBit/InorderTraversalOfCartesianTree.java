package InterviewBit;

/**
 * Given an inorder traversal of a cartesian tree, construct the tree.
 *
 *  Cartesian tree : is a heap ordered binary tree, where the root is greater than all the elements in the subtree.
 *  Note: You may assume that duplicates do not exist in the tree.
 * Example :
 *
 * Input : [1 2 3]
 *
 * Return :
 *           3
 *          /
 *         2
 *        /
 *       1
 */

import java.util.*;
public class InorderTraversalOfCartesianTree {

	//Time complexity O(n square )
    public static TreeNode buildTree(ArrayList<Integer> lst) {
		
		return build( lst, 0, lst.size() - 1);
    }
    
    public static TreeNode build( ArrayList<Integer> lst, int start, int end ){
		
		if( start > end ){
			return null;
		}
		
		int maxIndex = getMax( lst, start, end );
		TreeNode curr = new TreeNode( lst.get(maxIndex) );
		
		curr.left = build( lst, start, maxIndex - 1);
		curr.right = build( lst, maxIndex + 1, end );
		
		return curr;
	}
	
	public static int getMax( ArrayList<Integer> lst, int start, int end ){
		int max = lst.get(start);
		int resIndex = start;
		
		for( int index = start + 1; index <= end; index++ ){
			if( max < lst.get(index) ){
				max = lst.get(index);
				resIndex = index;
			}
		}
		
		return resIndex;
	}

    public static void main(String[] args) {
    	ArrayList<Integer> lst = new ArrayList<>();
    	lst.add(1);lst.add(2);lst.add(3);
    	TreeNode node = buildTree( lst );

    	inOrderTraversal(node);
    }

	private static void inOrderTraversal(TreeNode node) {
    	if( node == null )return;

    	inOrderTraversal(node.left);
		System.out.println(node.val);
		inOrderTraversal(node.right);
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
