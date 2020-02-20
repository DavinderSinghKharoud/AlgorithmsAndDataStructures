package algorthims.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {

    static int count = 0;
    static Map<Integer, Integer> map = new HashMap<>();

    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return count;
        }
        doPathSum(root, sum, 0);
        return count;
    }

    public static void doPathSum(TreeNode root, int sum, int pathSum) {
        pathSum += root.val;  //pathSum = The sum of val from root to current node
        if (pathSum == sum) {
            count++;
        }
        count += map.getOrDefault(pathSum-sum, 0);
        map.put(pathSum, map.getOrDefault(pathSum, 0)+1);
        if (root.left != null) {
            doPathSum(root.left, sum, pathSum);
        }
        if (root.right != null) {
            doPathSum(root.right, sum, pathSum);
        }
        map.put(pathSum, map.get(pathSum) - 1);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode( 10 );
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);

        System.out.println( pathSum(root, 8) );

    }
}
