package InterviewBit;

/**
 * Given two Binary Templates.Trees FindGreatestCommonDivisor and FindUniqueBinaryString, you need to merge them in a single binary tree.
 *
 * The merge rule is that if two nodes overlap, then sum of node values is the new value of the merged node.
 *
 * Otherwise, the non-null node will be used as the node of new tree.
 *
 *
 *
 * Problem Constraints
 * 1 <= Number of Nodes in FindGreatestCommonDivisor , FindUniqueBinaryString <= 105
 *
 *
 *
 * Input Format
 * First argument is an pointer to the root of binary tree FindGreatestCommonDivisor.
 *
 * Second argument is an pointer to the root of binary tree FindUniqueBinaryString.
 *
 *
 *
 * Output Format
 * Return a pointer to the root of new binary tree.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  FindGreatestCommonDivisor =   2
 *
 *       / \
 *
 *      1   4
 *
 *     /
 *
 *    5
 *
 *
 * FindUniqueBinaryString =   3
 *       / \
 *       6  1
 *       \   \
 *        2   7
 *
 * Input 2:
 *
 *  FindGreatestCommonDivisor =   12
 *
 *       / \
 *
 *      11  14
 *
 *
 * FindUniqueBinaryString =   3
 *       / \
 *       6  1
 *
 *
 *
 * Example Output
 * Output 1:
 *
 *      5
 *     / \
 *    7   5
 *   / \   \
 *  5   2   7
 * Output 2:
 *
 *    15
 *   / \
 *  17  15
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  After merging both the trees you get:
 *      5
 *     / \
 *    7   5
 *   / \   \
 *  5   2   7
 * Explanation 2:
 *
 *  After merging both the trees we get:
 *    15
 *   / \
 *  17  15
 */
public class MergeTwoBinaryTrees {

    public static TreeNode solve(TreeNode root1, TreeNode root2) {

		int curr;
		if( root1 != null && root2 != null ){
			curr = root1.val + root2.val;
		}else if( root1 == null && root2 == null){
			return null;
		}else if( root1 == null){
			curr = root2.val;
		}else{
		    curr = root1.val;
        }
		TreeNode root = new TreeNode(curr);
		
		root.left = solve( (root1 == null ) ? null : root1.left, (root2 == null ) ? null: root2.left );
		root.right = solve( (root1 == null ) ? null : root1.right, (root2 == null ) ? null: root2.right );
		
		return root;
    }

    public static void main(String[] args) {

        TreeNode root1 = new TreeNode(12);
        root1.left = new TreeNode(11);
        root1.right = new TreeNode(14);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(6);
        root2.right = new TreeNode(1);

        TreeNode res = solve( root1, root2);

        inOrder(res);
    }
    static void inOrder ( TreeNode node){

        if( node == null ){
            return;
        }

        inOrder( node.left );
        System.out.print( node.val + " ");
        inOrder( node.right );
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
