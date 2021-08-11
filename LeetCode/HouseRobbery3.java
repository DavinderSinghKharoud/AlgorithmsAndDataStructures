package LeetCode;

public class HouseRobbery3 {

    public static int rob(TreeNode root) {

        int[] result = dfs( root );
        return Math.max( result[0], result[1]);

    }

    private static int[] dfs(TreeNode root) {
        if( root == null ){
            return new int[2];
        }

        int []left = dfs( root.left );
        int []right = dfs( root.right );

        int []result = new int[2];
        result[0] =  root.val + left[1] + right[1];
        result[1] = Math.max( left[0], left[1]) + Math.max( right[0], right[1]);

        return result;
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(20);
        node.right = new TreeNode(5);
        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);
        node.right.right = new TreeNode(6);

        System.out.println( rob( node ));

    }
}
