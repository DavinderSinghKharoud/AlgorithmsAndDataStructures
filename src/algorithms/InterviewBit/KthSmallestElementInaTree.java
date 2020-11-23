package algorithms.InterviewBit;

public class KthSmallestElementInaTree {
    static int count = 0;

    public static int kthsmallest(TreeNode root, int k) {
        return (int)helper( root, k );
    }

    public static Integer helper( TreeNode root, int k ){

        if( root == null ) return null;

        Integer left = helper( root.left, k );
        if( left != null ) return left;

        count++;
        if( count == k ){
            return root.val;
        }
        Integer right = helper( root.right, k);
        if( right != null ) return right;

        return null;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);


        System.out.println(kthsmallest(root, 2));

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
