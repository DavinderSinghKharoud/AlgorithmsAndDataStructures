package algorithms.InterviewBit;

public class LeastCommonAncestor {
    static boolean first,second;

    static TreeNode traverse(TreeNode root,int n1,int n2){
        if(root==null)
            return null;
        TreeNode left=traverse(root.left,n1,n2);
        TreeNode right=traverse(root.right,n1,n2);
        if(n1==root.val)
            first = true;
        if(n2==root.val)
            second = true;
        if(n1==root.val || n2==root.val)
            return root;
        if(left!=null && right!=null)
            return root;
        if(left==null && right==null)
            return null;

        return ( left != null ) ? left: right;
    }

    //O(n) time and space complexity
    public static int lca(TreeNode root, int n1, int n2) { //Works even if the numbers do not exist inside the tree

        first = false;
        second = false;
        TreeNode res=traverse(root,n1,n2);


        if( !first || !second )
            return -1;
        return ( res == null ) ? -1: res.val;

    }
    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);

        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);

        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);

        root.right.right = new TreeNode(8);
        System.out.println( lca( root, 5, 1));
    }
    
    
    
  static class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) {
       val = x;
       left=null;
       right=null;
      }
  }
}
