package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class SameBinaryTree {

    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if( p == null && q == null ) return true;
        if( (p == null && q != null) || ( p != null && q == null ) ) return false;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add( p );
        queue2.add( q );
        while( !queue1.isEmpty() && !queue2.isEmpty() ){
            TreeNode curr1 = queue1.remove();
            TreeNode curr2 = queue2.remove();

            if( curr1.val == Integer.MAX_VALUE && curr2.val == Integer.MAX_VALUE ){
                continue;
            }else if( curr1.val == Integer.MAX_VALUE || curr2.val == Integer.MAX_VALUE ){
                return false;
            }
            if( curr1.val != curr2.val ){
                return false;
            }

            if( curr1.left == null ){ queue1.add( new TreeNode(Integer.MAX_VALUE ) ); }else{ queue1.add( curr1.left ); }
            if( curr1.right == null ){ queue1.add( new TreeNode(Integer.MAX_VALUE ) ); }else{ queue1.add( curr1.right ); }
            if( curr2.left == null ){ queue2.add( new TreeNode(Integer.MAX_VALUE ) ); }else{ queue2.add( curr2.left ); }
            if( curr2.right == null ){ queue2.add( new TreeNode(Integer.MAX_VALUE ) ); }else{ queue2.add( curr2.right ); }

        }

        return queue1.size() == queue2.size();
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);

        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.right = new TreeNode(2);

        System.out.println( isSameTree( treeNode, treeNode1));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}




