package LeetCode.WeeklyContest270;

/**
 * You are given the root of a binary tree with n nodes. Each node is uniquely
 * assigned a value from 1 to n. You are also given an integer startValue
 * representing the value of the start node s, and a different integer destValue
 * representing the value of the destination node t.
 * <p>
 * Find the shortest path starting from node s and ending at node t. Generate
 * step-by-step directions of such path as a string consisting of only the
 * uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific
 * direction:
 * <p>
 * 'L' means to go from a node to its left child node. 'R' means to go from a
 * node to its right child node. 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node
 * t.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6 Output:
 * "UURL" Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6. Example 2:
 * <p>
 * <p>
 * Input: root = [2,1], startValue = 2, destValue = 1 Output: "L" Explanation:
 * The shortest path is: 2 → 1.
 */
public class StepByStepDirectionsBinaryTree {
   public static void main(String[] args) {
      StepByStepDirectionsBinaryTree o = new StepByStepDirectionsBinaryTree();
      TreeNode five = new TreeNode(5);
      TreeNode one = new TreeNode(1);
      TreeNode three = new TreeNode(3);
      TreeNode two = new TreeNode(2);
      TreeNode six = new TreeNode(6);
      TreeNode four = new TreeNode(4);

      five.right = one;
      five.left = four;
      one.left = six;
      one.right = three;
      three.right = two;

      System.out.println(o.getDirections(five, 2, 5));
   }

   public String getDirections2(TreeNode root, int startValue, int destValue) {
      StringBuilder s = new StringBuilder(), d = new StringBuilder();
      find(root, startValue, s);
      find(root, destValue, d);

      s.reverse();
      d.reverse();
      int common = 0, len = Math.min(s.length(), d.length());
      int i = 0;
      while (common < len && s.charAt(common) == d.charAt(common))
         common++;

      String fromStart = "U".repeat(s.length() - common);
      String fromEnd = d.toString().substring(common);
      return fromStart + fromEnd;
   }

   boolean find(TreeNode node, int tar, StringBuilder path) {
      if (node.val == tar)
         return true;
      if (node.left != null && find(node.left, tar, path)) {
         path.append("L");
      } else if (node.right != null && find(node.right, tar, path)) {
         path.append("R");
      }
      return path.length() > 0;
   }

   StringBuilder ans = new StringBuilder();

   public String getDirections(TreeNode root, int startValue, int destValue) {
      find(root, startValue, destValue);
      return ans.toString();
   }

   TreeNode find(TreeNode root, int start, int des) {
      if (root == null)
         return null;
      TreeNode left = find(root.left, start, des);
      TreeNode right = find(root.right, start, des);

      // Case 1
      if (left != null && right != null) {
         if (left.val == start) {
            ans.append(getAllU(root.left, left.val));
            getDirec(root, right.val);
         } else {
            ans.append(getAllU(root.right, right.val));
            getDirec(root, left.val);
         }

      } else {
         if (left == null && right == null)
            return (root.val == start || root.val == des) ? root : null;
         TreeNode curr = (root.val == start || root.val == des) ? root : null;
         if (curr == null)
            return (left != null) ? left : right;

         // Case 2
         // One of the left or right is not null and current is also start or des
         if (curr.val == start) {
            getDirec(root, (left != null) ? left.val : right.val);
         } else {
            if (left != null) {
               ans.append(getAllU(root.left, left.val));
            } else {
               ans.append(getAllU(root.right, right.val));
            }

         }
      }
      return null;
   }

   String l = "L", r = "R";

   boolean getDirec(TreeNode node, int t) {
      if (node == null)
         return false;
      if (node.val == t)
         return true;
      ans.append(l);
      boolean isFound = getDirec(node.left, t);
      if (!isFound)
         ans.deleteCharAt(ans.length() - 1);
      else
         return true;

      ans.append(r);
      isFound = getDirec(node.right, t);
      if (!isFound)
         ans.deleteCharAt(ans.length() - 1);
      else
         return true;
      return false;
   }

   String getAllU(TreeNode node, int t) {
      if (node == null)
         return "";
      if (node.val == t) {
         return "U";
      }
      String resLeft = getAllU(node.left, t);
      String resRight = getAllU(node.right, t);
      ans.append(resLeft).append(resRight);
      return (resLeft.isEmpty()) ? resRight : resLeft;
   }

   static public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;

      TreeNode() {
      }

      TreeNode(int val) {
         this.val = val;
      }

      TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
      }
   }
}
