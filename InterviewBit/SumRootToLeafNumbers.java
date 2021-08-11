package InterviewBit;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers % 1003.
 *
 * Example :
 *
 *     1
 *    / \
 *   2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 *
 * Return the sum = (12 + 13) % 1003 = 25 % 1003 = 25.
 */
public class SumRootToLeafNumbers {

    public static int sumNumbers(TreeNode root) {

            return traverse( root, 0 );
        }


        public static int traverse( TreeNode root, int sum ){

            if( root == null ) return 0;

            sum = (sum * 10 + root.val) % 1003;

            //Check if it is leaf node
            if( root.left == null && root.right == null ){
                return sum;
            }

            return (traverse(root.left, sum) % 1003) + (traverse(root.right, sum) % 1003);
        }
	

    public static void main(String[] args) {


        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println( sumNumbers(root) );
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
