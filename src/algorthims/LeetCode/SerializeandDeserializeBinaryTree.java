package algorthims.LeetCode;


import java.util.*;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"**/

public class SerializeandDeserializeBinaryTree {
	
	 // Encodes a tree to a single string. O(n)
    public static String serialize(TreeNode root) {
    	if( root == null ){
    		return "X,";
		}

    	String left_serialize = serialize( root.left);
    	String right_serialize = serialize( root.right );

    	return root.val + "," + left_serialize + right_serialize;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        
        Queue<String> queue = new LinkedList<>(  Arrays.asList( nodes ));
        
        return deserialize_helper( queue );
		
    }
    
    public static TreeNode deserialize_helper( Queue<String> queue ){
		
		String str = queue.remove();
		
		if( str.equals( "X" ) ){
			return null;
		}
		
		TreeNode node = new TreeNode( Integer.valueOf(str) );
		
		node.left = deserialize_helper( queue );
		node.right = deserialize_helper( queue );
		
		return node;
	}
    
	public static void main (String[] args) {
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		
		System.out.print( serialize( root ));
	}
	
	public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}

