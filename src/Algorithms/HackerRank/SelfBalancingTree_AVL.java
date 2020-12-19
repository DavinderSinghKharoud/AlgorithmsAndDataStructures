package Algorithms.HackerRank;

/**
 * An AVL tree (Georgy Adelson-Velsky and Landis' tree, named after the inventors) is a self-balancing binary search tree. In an AVL tree, the heights of the two child subtrees of any node differ by at most one; if at any time they differ by more than one, rebalancing is done to restore this property.
 * We define balance factor for each node as :
 * balanceFactor = height(left subtree) - height(right subtree)
 * The balance factor of any node of an AVL tree is in the integer range [-1,+1]. If after any modification in the tree, the balance factor becomes less than −1 or greater than +1, the subtree rooted at this node is unbalanced, and a rotation is needed.
 *
 * You are given a pointer to the root of an AVL tree. You need to insert a value into this tree and perform the necessary rotations to ensure that it remains balanced.
 */

public class SelfBalancingTree_AVL {


    public static void main(String[] args) {
        Node root = new Node();
        root.val = 3;

        root = insert(root, 2);
        root = insert(root, 4);
        root = insert(root, 5);
        root = insert(root, 6);

        preOrder(root);
    }

    /**
     * AVL tree is self balancing binary tree. Difference of height of left or right subtree
     * * cannot be greater than one.
     * *
     * * There are four different use cases to insert into AVL tree
     * * left left - needs ones right rotation
     * * left right - needs one left and one right rotation
     * * right left - needs one right and one left rotation
     * * right right - needs one left rotation
     * *
     * * Follow rotation rules to keep tree balanced.
     * *
     * * At every node we will also keep height of the tree so that we don't
     * * have to recalculate values again.
     * *
     * * Runtime complexity to insert into AVL tree is O(logn).
     */
    static Node insert(Node root, int val) {

        if (root == null) { //We create a new end after finding the correct position
            Node newNode = new Node();
            newNode.val = val;
            return newNode;
        }

        if (root.val <= val) {
            root.right = insert(root.right, val);
        } else {
            root.left = insert(root.left, val);
        }


        //We need to check the height difference
        int balance = getHeight(root.left) - getHeight(root.right);

        if (balance > 1) { //There can be two cases for left-left or left-right

            if (getHeight(root.left.left) >= getHeight(root.left.right)) { //L-L
                root = rightRotate(root);
            } else {//L-R
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }

        } else if (balance < -1) { //There can be two cases for right-right or right-left
            if (getHeight(root.right.right) >= getHeight(root.right.left)) {
                root = leftRotate(root);
            } else {

                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }

        //Just update the height
        root.ht = setHeight(root);

        return root;


    }

    /**
     * https://www.baeldung.com/wp-content/uploads/2020/02/L-Large-1.png
     */
    static Node leftRotate(Node root) {
        Node newNode = root.right;
        root.right = newNode.left;
        newNode.left = root;

        root.ht = setHeight(root);
        newNode.ht = setHeight(newNode);

        return newNode;
    }

    /**
     * https://www.baeldung.com/wp-content/uploads/2020/02/R-Large-1.png
     */
    static Node rightRotate(Node root) {
        Node newNode = root.left;
        root.left = newNode.right;
        newNode.right = root;

        root.ht = setHeight(root);
        newNode.ht = setHeight(newNode);

        return newNode;
    }

    public static int setHeight(Node root) {
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public static int getHeight(Node root) {
        if (root == null) return -1;

        return root.ht;
    }

    static void preOrder(Node node) {

        if (node == null) {
            return;
        }

        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    static class Node {
        int val;    //Value
        int ht;        //Height
        Node left;    //Left child
        Node right;
    }
}
