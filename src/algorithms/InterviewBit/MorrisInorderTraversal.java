package algorithms.InterviewBit;

public class MorrisInorderTraversal {


    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);

        inOrder(root);
    }

    /**
     * Time complexity O(n) and Space complexity O(1)
     */
    public static void inOrder( TreeNode root ){

        TreeNode current = root;

        while ( current != null ){
            if( current.left == null ){
                System.out.println(current.val + "");
                current = current.right;
            }else {

                TreeNode predecessor = current.left;

                while ( predecessor.right != current && predecessor.right != null){
                    predecessor = predecessor.right;
                }

                if( predecessor.right == null ){
                    predecessor.right = current;
                    current = current.left;
                }else{
                    predecessor.right = null;
                    System.out.println(current.val + "");
                    current = current.right;
                }
            }
        }

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
