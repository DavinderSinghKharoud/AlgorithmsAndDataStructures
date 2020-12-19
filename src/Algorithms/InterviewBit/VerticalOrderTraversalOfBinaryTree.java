package Algorithms.InterviewBit;

import java.util.*;

/**
 * Problem Description
 *
 * Given a binary tree A consisting of N nodes, return a 2-D array denoting the vertical order traversal of A.
 *
 * Go through the example and image for more details.
 *
 * NOTE:
 *
 * If 2 or more Tree Nodes shares the same vertical level then the one with earlier occurence in the pre-order traversal of tree comes first in the output.
 * Row 1 of the output array will be the nodes on leftmost vertical line similarly last row of the output array will be the nodes on the rightmost vertical line.
 *
 *
 * Problem Constraints
 * 0 <= N <= 104
 *
 *
 *
 * Input Format
 * First and only argument is an pointer to root of the binary tree A.
 *
 *
 *
 * Output Format
 * Return a 2D array denoting the vertical order traversal of A.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *       6
 *     /   \
 *    3     7
 *   / \     \
 *  2   5     9
 * Input 2:
 *
 *            1
 *          /   \
 *         2     3
 *        / \
 *       4   5
 *
 *
 * Example Output
 * Output 1:
 *
 *  [
 *     [2],
 *     [3],
 *     [6, 5],
 *     [7],
 *     [9]
 *  ]
 * Output 2:
 *
 *  [
 *     [4],
 *     [2],
 *     [1, 5],
 *     [3]
 *  ]
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *
 *  Nodes on Vertical Line 1: 2
 *  Nodes on Vertical Line 2: 3
 *  Nodes on Vertical Line 3: 6, 5
 *     As 6 and 5 are on the same vertical level but as 6 comes first in the pre-order traversal of the tree so we will output 6 before 5.
 *  Nodes on Vertical Line 4: 7
 *  Nodes on Vertical Line 5: 9
 * Explanation 2:
 *
 *  Nodes on Vertical Line 1: 4
 *  Nodes on Vertical Line 2: 2
 *  Nodes on Vertical Line 3: 1, 5
 *  Nodes on Vertical Line 4: 3
 */
public class VerticalOrderTraversalOfBinaryTree {

    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {

        List<Node> lst = new ArrayList<>();

        int[] min_max = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};


        traverse(root, lst, min_max, 0, 0);
        Collections.sort(lst, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if( o1.x < o2.x){
                    return -1;
                }else if( o1.x > o2.x){
                    return 1;
                }else if( o1.y < o2.y ){
                    return -1;
                }else if( o1.y > o2.y ){
                    return 1;
                }else return Integer.compare(o1.val, o2.val);
            }
        });

        //int[][] res = new int[lst.size()][];
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < lst.size(); i++) {
            Node curr = lst.get(i);
            if( i == 0 ){
                List<Integer> bucket = new ArrayList<>();
                res.add(bucket);
                bucket.add(curr.val);
                continue;
            }
            Node prev = lst.get(i - 1);
            if( prev.x < curr.x ){
                res.add(new ArrayList<>());
            }

            List<Integer> bucket = res.get(res.size() - 1);
            bucket.add(curr.val);
        }

        int[][] a = new int[res.size()][];
        int index = 0;
        for(List<Integer> temp: res){
            int[] b = new int[temp.size()];
            for(int index2 = 0; index2 < temp.size(); index2++ ){
                b[index2] = temp.get(index2);
            }
            a[index] = b;
        }
        return res;
    }

    public static void traverse(TreeNode root, List<Node> lst, int[] min_max, int x, int y) {
        if (root == null) return;

        min_max[0] = Math.max(min_max[0], x);
        min_max[1] = Math.min(min_max[1], x);

        lst.add(new Node(root.val, x, y));
        traverse(root.left, lst, min_max, x - 1, y + 1);
        traverse(root.right, lst, min_max, x + 1, y + 1);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(9);

//        int[][] ans =
        System.out.println(verticalOrderTraversal2(root));
//        for (int[] curr : ans) {
//            for (int num : curr) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
//        }
    }

    public static List<List<Integer>> verticalOrderTraversal2(TreeNode root) {

        if( root == null ) return new ArrayList<>();
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        Queue<NodeLevel> queue = new LinkedList<>();

        queue.add(new NodeLevel(root, 0));

        while ( !queue.isEmpty() ){
            NodeLevel curr = queue.poll();
            map.putIfAbsent(curr.level, new ArrayList<>());
            map.get(curr.level).add(curr.node.val);

            if( curr.node.left != null ){
                queue.add(new NodeLevel(curr.node.left, curr.level - 1));
            }
            if( curr.node.right != null ){
                queue.add(new NodeLevel(curr.node.right, curr.level + 1));
            }

        }

        return new ArrayList<>(map.values());
    }

    static class NodeLevel{
        TreeNode node;
        int level;

        public NodeLevel( TreeNode node, int level){
            this.node = node;
            this.level = level;
        }
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

    static class Node {
        int x;
        int y;
        int val;

        public Node(int val, int x, int y) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
