package Algorithms.InterviewBit;

//Check the video tommorow https://www.youtube.com/watch?v=wGXB9OWhPTg
import java.util.*;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Tell us the 2 values swapping which the tree will be restored.
 *
 *  Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * Example :
 *
 *
 * Input :
 *          1
 *         / \
 *        2   3
 *
 * Output :
 *        [1, 2]
 *
 * Explanation : Swapping 1 and 2 will change the BST to be
 *          2
 *         / \
 *        1   3
 * which is a valid BST
 */
public class RecoverBinarySearchTree {


    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        //root.left.left = new TreeNode(4);
        //root.right.left = new TreeNode(2);

        System.out.println( recoverTree(root));
    }

    static TreeNode first = null;
    static TreeNode second = null;
    static TreeNode prev = null;

    //Time and space complexity O(n)
    public static ArrayList<Integer> recoverTree(TreeNode root) {

		ArrayList<Integer> res = new ArrayList<>();
		inorderTraversal(root);

		if( first != null && second != null ){
		    res.add(first.val);
		    res.add(second.val);
        }else{
		    res.add(first.val);
		    res.add(prev.val);
        }
		Collections.sort(res);
		return res;
    }


    public static void inorderTraversal(TreeNode root) {
		
	    if( root == null ) return;

	    inorderTraversal(root.left);

	    if( prev != null ){
	        if( root.val < prev.val ){
	            if( first == null ) first = prev; // 3 15 4 18 6 --> as a pairs 15 exist as first val and 18 will be exist as second val
	            second = root;
            }
        }

	    prev = root;
	    inorderTraversal(root.right);
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
