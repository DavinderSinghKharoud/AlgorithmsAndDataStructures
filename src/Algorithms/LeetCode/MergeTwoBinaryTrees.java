package Algorithms.LeetCode;


public class MergeTwoBinaryTrees {

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {


        if( t1 == null ){
            return t2;
        }
        if( t2 == null ){
            return t1;
        }

        t1.val = t1.val + t2.val;
        t1.left = mergeTrees( t1.left, t2.left);
        t1.right = mergeTrees( t1.right, t2.right);
        return t1;
    }

    static void inOrder(TreeNode node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    public static void main(String[] args) {

        TreeNode t1 = new TreeNode(2);
        t1.left = new TreeNode(1);
        t1.right = new TreeNode(3);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(3);


        inOrder( mergeTrees( t1, t2));


    }


//    public class Solution {
//        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//            if (t1 == null)
//                return t2;
//            Stack < TreeNode[] > stack = new Stack < > ();
//            stack.push(new TreeNode[] {t1, t2});
//            while (!stack.isEmpty()) {
//                TreeNode[] t = stack.pop();
//                if (t[0] == null || t[1] == null) {
//                    continue;
//                }
//                t[0].val += t[1].val;
//                if (t[0].left == null) {
//                    t[0].left = t[1].left;
//                } else {
//                    stack.push(new TreeNode[] {t[0].left, t[1].left});
//                }
//                if (t[0].right == null) {
//                    t[0].right = t[1].right;
//                } else {
//

//                }
//            }
//            return t1;
//        }
//    }

}
