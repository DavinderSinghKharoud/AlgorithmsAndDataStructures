package algorithms.InterviewBit;


import java.util.*;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p>
 * Note: You may assume that duplicates do not exist in the tree.
 * Example :
 * <p>
 * Input :
 * Inorder : [2, 1, 3]
 * Postorder : [2, 3, 1]
 * <p>
 * Return :
 * 1
 * / \
 * 2   3
 */
public class BinaryTreeFromInorderAndPostOrderTraversal {

    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(9);
        arr.add(3);
        arr.add(15);
        arr.add(20);
        arr.add(7);

        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add(9);
        arr1.add(15);
        arr1.add(7);
        arr1.add(20);
        arr1.add(3);

        TreeNode root = buildTree(arr, arr1);

        inOrder(root);
    }
    /**
     * Traversing methods (inOrder, preOrder, and postOrder)
     * @param node
     */
    static void inOrder ( TreeNode node){

        if( node == null ){
            return;
        }

        inOrder( node.left );
        System.out.print( node.val + " ");
        inOrder( node.right );
    }

    public static TreeNode buildTree(ArrayList<Integer> inOrder, ArrayList<Integer> postOrder) {

        return helper(inOrder, postOrder, postOrder.size() - 1, 0, inOrder.size() - 1);
    }

    public static TreeNode helper(ArrayList<Integer> inOrder, ArrayList<Integer> postOrder, int startPostOrder, int startInOrder, int endInOrder) {

        if (startPostOrder >= postOrder.size() || startInOrder > endInOrder) {
            return null;
        }

        TreeNode curr = new TreeNode(postOrder.get(startPostOrder));
        int index = findIndex(inOrder, postOrder.get(startPostOrder), startInOrder, endInOrder);

        curr.left = helper(inOrder, postOrder, startPostOrder - (endInOrder - index + 1), startInOrder, index - 1);
        curr.right = helper(inOrder, postOrder, startPostOrder - 1, index + 1, endInOrder);
        return curr;
    }

    public static int findIndex(ArrayList<Integer> lst, int num, int startInOrder, int endInOrder) {

        for (int index = startInOrder; index <= endInOrder; index++) {
            if (num == lst.get(index)) {
                return index;
            }
        }

        return -1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }
}
