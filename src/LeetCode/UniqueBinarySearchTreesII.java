/*
 Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */

import java.util.*;
public class UniqueBinarySearchTreesII {
	
	public static List<TreeNode> generateTrees(int n) {
        if( n == 0 ) {
            List<TreeNode> list = new ArrayList<>();
            return list;
        }
        ArrayList[][] dp = new ArrayList[n + 1][n + 1];
		return helper( 1, n, dp );

    }
		
    public static List<TreeNode> helper(int start, int end, List<TreeNode>[][] dp){
		List<TreeNode> list = new ArrayList<>();
		
		if( start > end ) {
		    list.add(null);
		    return list;
        }

		//for memorization
		if( dp[start][end] != null ){
		    return dp[start][end];
        }

		if( start == end ){
			list.add(new TreeNode(start) );
			return list;
		}
		
		for( int i = start; i <= end; i++ ){
			
			List<TreeNode> left = helper( start, i - 1, dp);
			List<TreeNode> right = helper( i + 1, end, dp);

			if( left.size() == 0 ){
			    for( TreeNode node: right ){
			        TreeNode root = new TreeNode(i);
			        root.right = node;
			        list.add(root);
                }
            }

            if( right.size() == 0 ){
                for( TreeNode node: left ){
                    TreeNode root = new TreeNode(i);
                    root.left = node;
                    list.add(root);
                }
            }

			for( TreeNode leftNode: left ){
				for( TreeNode rightNode: right ){
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
					root.right = rightNode;
                    list.add( root );
				}
			}
		}

		dp[start][end] = list;
		return list;
	}
	
	public static void main (String[] args) {
		 List<TreeNode> list = generateTrees(3);

		 for( TreeNode root: list ){
		     traverse(root);
             System.out.println("********");
         }
		 
	}
	
	public static void traverse( TreeNode root ){
		System.out.println( root.val );
		if( root.left != null ) traverse( root.left );
		
		if( root.right != null ) traverse( root.right );
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

