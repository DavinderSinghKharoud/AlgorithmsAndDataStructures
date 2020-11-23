package algorithms.InterviewBit;

public class IdenticalBinaryTrees {

    public static int isSameTree(TreeNode first, TreeNode second) {

        if( first == null && second == null ){
			return 1;
		}
		
		if(first == null || second == null || first.val != second.val){
			return 0;
		}
		
		int left = isSameTree(first.left, second.left);
		int right = isSameTree(first.right, second.right);
		
		if( left == 0 || right == 0 ) return 0;
		
		return 1;
    }

    public static void main(String[] args) {

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);

        System.out.println( isSameTree(root1, root2));
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
