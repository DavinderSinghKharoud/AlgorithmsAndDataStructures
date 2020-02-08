package algorthims.LeetCode;


import Tree.Node;

import java.util.ArrayDeque;
import java.util.Queue;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        inOrder(root);

        TreeNode inv = invertTree( root );

        inOrder( inv );

    }

    static void inOrder ( TreeNode node){

        if( node == null ){
            return;
        }

        inOrder( node.left );
        System.out.print( node.val + " ");
        inOrder( node.right );
    }

    private static TreeNode invertTree(TreeNode root) {

        TreeNode invert = new TreeNode(root.val);
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add( root );

        while( !queue.isEmpty()){
            TreeNode extracted = queue.remove();
            int value = extracted.val;

            if( extracted.left != null ){
                queue.add( extracted.left);
            }
            if( extracted.right != null ){
                queue.add( extracted.right );
            }

            insert( invert, value);

        }
        return invert;
    }

    private static TreeNode insert(TreeNode root, int value){

        if( root == null){
            root = new TreeNode( value );
            return root;
        }

        if( value <= root.val ){
            root.right = insert( root.right , value);
        }else if( value > root.val){
            root.left = insert( root.left, value);
        }

        return root;
    }
}


