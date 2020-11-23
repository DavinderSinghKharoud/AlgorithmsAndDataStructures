package algorithms.BinaryTreeHeight;

import java.util.Scanner;

/**
 * The height of a binary search tree is the number of edges between the tree's root and
 * its furthest leaf. You are given a pointer, root , pointing to the root of a binary search tree.
 * Complete the getHeight function provided in your editor so that it returns the height of the
 * binary search tree.
 */
public class BinaryTree {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int T = sc.nextInt();
        Node root = null;
        while (T-- > 0) {
            System.out.print("Enter the data: ");
            int data = sc.nextInt();
            root = insert(root, data);
        }
        int height = getHeight(root);
        System.out.println("Height of the tree: " + height);
        inOrderTraversal(root);
    }

    //InOrder Traversal
    private static void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.data);
        inOrderTraversal(root.right);

    }

    private static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;

    }


}
