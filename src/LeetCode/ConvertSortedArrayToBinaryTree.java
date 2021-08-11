package LeetCode;

/*
Given an array where elements are sorted in ascending order, convert it to a height
 balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which
 the depth of the two subtrees of every node never differ by more than 1.

 Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 */
public class ConvertSortedArrayToBinaryTree {

    public static TreeNode sortedArrayToBST(int[] nums) {
        if( nums == null || nums.length == 0 ){
            return null;
        }

        TreeNode root = createBinaryTree( nums, 0, nums.length - 1);
        return root;
    }

    private static TreeNode createBinaryTree(int[] nums, int low, int high) {

        if( low > high ){
            return  null;
        }

        int mid = (low + high)/2;

        TreeNode node = new TreeNode( nums[mid] );
        node.left = createBinaryTree( nums, low,mid - 1);
        node.right = createBinaryTree( nums, mid + 1, high);

        return node;
    }


    static void inOrder ( TreeNode node){

        if( node == null ){
            return;
        }

        inOrder( node.left );
        System.out.print( node.val + " ");
        inOrder( node.right );
    }

    public static void main(String[] args) {

        TreeNode root = sortedArrayToBST( new int[]{
                -10,-3,0,5,9
        });

        inOrder( root );


    }
}
