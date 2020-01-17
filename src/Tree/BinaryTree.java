package Tree;

import java.io.Serializable;

public class BinaryTree {
    Node root;

    BinaryTree(){
        root = null;
    }

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
}
