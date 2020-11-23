package algorithms.LeetCode;


import java.util.Arrays;

/**
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
 */
public class ConstructBinarySearchTreefromPreorderTraversal {

    public static TreeNode bstFromPreorder(int[] preorder) {
	
	int[] preorder_temp = preorder.clone();
	Arrays.sort( preorder );
	return helper( preorder_temp, preorder,0, 0, preorder.length - 1);
            
    }

    public static TreeNode helper( int[] preorder, int[] inorder,int pre_start, int in_start, int in_end){
		
	    if( pre_start > preorder.length - 1 || in_start > in_end ){
		return null;
	    }
	    
	    TreeNode node = new TreeNode( preorder[pre_start] );
	    
	    int index = 0;
	    
	    for( int i = in_start; i <= in_end; i++ ){
		if( inorder[i] == node.val ){
		    index = i;
		    break;
		} 
	    }
	    
	    node.left = helper( preorder, inorder, pre_start + 1, in_start, index - 1);
	    //over here when we need to find the right part in preorder array, sometimes we do not know that left side does not exist it only has right side
        //so for to get correct answer we need to subtract in_start index of inorder traversal, just to make sure left part is subtracted.
	    node.right = helper( preorder, inorder,pre_start + index - in_start + 1, index + 1, in_end );
	    
	    return node;
    }
    public static void main(String[] args) {

        TreeNode root = bstFromPreorder( new int[]{
                3,1,2,4
        });

	inOrder( root );
    }

    static void inOrder ( TreeNode node){

        if( node == null ){
            return;
        }

        inOrder( node.left );
        System.out.print( node.val + " ");
        inOrder( node.right );
    }


      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

}
