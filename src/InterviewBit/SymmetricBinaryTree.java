package InterviewBit;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * Example :
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * The above binary tree is symmetric.
 * But the following is not:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
 */
public class SymmetricBinaryTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left =  new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println( isSymmetric(root) );
    }

    public static int isSymmetric(TreeNode root) {
		
		return (helper( root, root ))? 1: 0;
    }
    
    public static boolean helper( TreeNode original, TreeNode copy ){
		if( original == null && copy == null ) return true;
		if(original == null || copy == null || original.val != copy.val){
			return false;
		}
		
		return helper( original.left, copy.right ) && helper( original.right, copy.left);
		
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
