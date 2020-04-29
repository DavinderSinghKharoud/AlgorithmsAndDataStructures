package algorthims.LeetCode;

import java.util.*;

/**
 *Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 */
public class FlattenBinaryTreetoLinkedList {

    public static void flatten(TreeNode root) {

        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push( root );

        while ( !stack.isEmpty() ) {
            TreeNode curr = stack.pop();

            if (curr.right != null) {
                stack.push(curr.right);
            }

            if (curr.left != null) {
                stack.push(curr.left);
            }

            if (!stack.isEmpty()) {
                curr.right = stack.peek();
            }

            curr.left = null;
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        flatten2(root);

        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }

    
 public static void flatten2(TreeNode root) {
        if(root == null)
            return;
        
        TreeNode left = root.left;
        flatten(left);
        TreeNode right = root.right;
        flatten(right);
        //After this we assume the L and R child are right aligned lists.
        
        //If we didn't have a left child, root is already done.
        if(left == null)
            return;
        //If not then make the L child the right,
        // and append the R child to the end of the L list.
        else {
            root.right = left;
            root.left = null;
            if(right != null) {
                    while(left.right != null) {
                    left = left.right;
                }
                left.right = right;
            }
        }
    }
		
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

