package Tree;

import algorthims.LeetCode.DiameterOfBinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    Node root;
    int height;

    BinaryTree(){
        root = null;
    }

    /**
     * Traversing methods (inOrder, preOrder, and postOrder)
     * @param node
     */
    void inOrder ( Node node){

        if( node == null ){
            return;
        }

        inOrder( node.left );
        System.out.print( node.key + " ");
        inOrder( node.right );
    }

    void preOrder ( Node node){

        if( node == null ){
            return;
        }

        System.out.print( node.key + " ");
        preOrder( node.left );
        preOrder( node.right );
    }

    void postOrder( Node node ){
        if( node == null ){
            return;
        }

        postOrder( node.left );
        postOrder( node.right );
        System.out.print( node.key + " ");
    }

    public static class TreeNodeBinary {
        int val;

        TreeNodeBinary left;
        TreeNodeBinary right;

        TreeNodeBinary(int x) {
            val = x;
        }
    }

    //BFS Binary Tree
    public List<List<Integer>> levelOrder(TreeNodeBinary root) {

        List<List<Integer>> result = new ArrayList<>();
        if( root == null ){
            return result;
        }
        Queue<TreeNodeBinary> queue = new ArrayDeque<>();
        queue.add( root );

        while ( !queue.isEmpty() ){
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for( int i = 0; i < size; i++ ){
                TreeNodeBinary node = queue.remove();
                currentLevel.add( node.val );

                if( node.left != null ){
                    queue.add( node.left );
                }
                if( node.right != null ){
                    queue.add( node.right );
                }

            }

            result.add( currentLevel );
        }

        return result;



    }

    //Search node
    String searchNode(Node root, int key){

        if( root == null){
            return "not found";
        }

        if( root.key == key ){
            return "found";
        }

        if( root.key > key ){
            return searchNode( root.left, key);
        }

        return searchNode( root.right, key);

    }
    //Insert the node
    Node insertNode( Node root, int key){

        if( root == null){
            root = new Node( key );
            return root;
        }

        if( key < root.key ){
            root.left = insertNode( root.left , key);
        }else if( key > root.key){
            root.right = insertNode( root.right, key);
        }

        return root;
    }


    //Get height of the tree
    int getHeight( Node root){

        if( root == null){
            return 0;
        }


        return 1 + Math.max( getHeight( root.left ), getHeight( root.right ));
    }

    //Print leaf nodes
    void getLeafs( Node node){

        if( node == null ){
            return;
        }

        if( node.left == null && node.right == null){
            System.out.print( node.key +" " );
        }


        getLeafs( node.left );
        getLeafs( node.right );

    }

}
