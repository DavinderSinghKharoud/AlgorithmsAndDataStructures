package LeetCode.WeeklyContest270;

import java.util.*;

/**
 * You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.
 * <p>
 * Return any valid arrangement of pairs.
 * <p>
 * Note: The inputs will be generated such that there exists a valid arrangement of pairs.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
 * Output: [[11,9],[9,4],[4,5],[5,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 9 == 9 = start1
 * end1 = 4 == 4 = start2
 * end2 = 5 == 5 = start3
 * Example 2:
 * <p>
 * Input: pairs = [[1,3],[3,2],[2,1]]
 * Output: [[1,3],[3,2],[2,1]]
 * Explanation:
 * This is a valid arrangement since end-1 always equals start.
 * end0 = 3 == 3 = start1
 * end1 = 2 == 2 = start2
 * The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
 * Example 3:
 * <p>
 * Input: pairs = [[1,2],[1,3],[2,1]]
 * Output: [[1,2],[2,1],[1,3]]
 * Explanation:
 * This is a valid arrangement since end-1 always equals starting.
 * end0 = 2 == 2 = start1
 * end1 = 1 == 1 = start2
 */
public class ValidArrangementOfPairs {
    public static void main(String[] args) {
        ValidArrangementOfPairs o = new ValidArrangementOfPairs();
    }


    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, Stack<Integer>> tree = new HashMap<>();
        //Eulerian path
        Map<Integer, Integer> in = new HashMap<>();
        Map<Integer, Integer> out = new HashMap<>();

        for (int[] edge : pairs) {
            int u = edge[0], v = edge[1];
            in.put(v, in.getOrDefault(v, 0) + 1);
            out.put(u, out.getOrDefault(u, 0) + 1);
            if (tree.get(u) == null) tree.put(u, new Stack<>());
            tree.get(u).add(v);
        }

        int start = -1;
        for (int num : tree.keySet()) {
            if (out.getOrDefault(num, 0) - in.getOrDefault(num, 0) == 1) start = num;
        }

        if (start == -1) //Eulerian circuit
        {
            for (int key : tree.keySet()) {
                start = key;
                break;
            }
        }

        Stack<int[]> ans = new Stack<>();
        eulerainPath(tree, start, ans);

        int[][] res = new int[pairs.length][2];
        int i = 0;
        while (!ans.isEmpty()) {
            res[i++] = ans.pop();
        }
        return res;
    }

    void eulerainPath(Map<Integer, Stack<Integer>> tree, int node, Stack<int[]> ans) {
        if (tree.get(node) == null) return;
        while (!tree.get(node).isEmpty()) {
            int child = tree.get(node).pop();
            eulerainPath(tree, child, ans);

            ans.add(new int[]{node, child});
        }
    }
}
