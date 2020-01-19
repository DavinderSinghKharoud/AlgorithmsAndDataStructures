package Tree;

public class Traversal {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
          tree.root = new Node(2);
//        tree.root.left = new Node(2);
//        tree.root.right = new Node(3);
//        tree.root.left.left = new Node(4);
//        tree.root.left.right = new Node(5);

        tree.insertNode(tree.root, 1);
        tree.insertNode(tree.root, 3 );
        tree.insertNode( tree.root, 4);
        tree.insertNode( tree.root, 2);


        System.out.println("Preorder traversal: ");
        tree.preOrder( tree.root );

        System.out.println("\nInorder traversal: ");
        tree.inOrder( tree.root );

        System.out.println("\nPostorder traversal:");
        tree.postOrder( tree.root );

        System.out.println("\nSearching Node: ");
        System.out.print( tree.searchNode( tree.root, 7) );

        System.out.print("\nHeight of the tree: ");
        System.out.println( tree.getHeight( tree.root ));
    }


}

