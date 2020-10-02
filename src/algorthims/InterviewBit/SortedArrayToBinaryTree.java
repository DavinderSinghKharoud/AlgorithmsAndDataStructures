package algorthims.InterviewBit;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 *  Balanced tree : a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example :
 *
 *
 * Given A : [1, 2, 3]
 * A height balanced BST  :
 *
 *       2
 *     /   \
 *    1     3
 */
public class SortedArrayToBinaryTree {
    public static void main(String[] args) {

        TreeNode root = sortedArrayToBST(new int[]{1, 2, 3, 4, 5});

        inOrder(root);
    }
    
    public static TreeNode sortedArrayToBST(final int[] arr) {
		
		return helper( arr, 0, arr.length - 1 );

    }

    public static TreeNode helper( int[] arr, int start, int end ){
		if( start == end ){
			return new TreeNode(arr[start]);
		}
		if( start > end ) return null;


		int mid = (end - start)/2 + start;

		TreeNode curr = new TreeNode(arr[mid]);

		curr.left = helper( arr, start, mid - 1 );
		curr.right = helper( arr, mid + 1, end );

		return curr;
	}

    static void inOrder ( TreeNode node){

        if( node == null ){
            return;
        }

        inOrder( node.left );
        System.out.print( node.val + " ");
        inOrder( node.right );
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
