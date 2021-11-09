package Preparation;

import java.util.*;

/**
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 * <p>
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 * <p>
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 */
public class PathMaximumProbability_Dijikstra {
    ArrayDeque<Node>[] tree;
    double[] dp, succProb;

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        this.succProb = succProb;
        dp = new double[n];
        tree = new ArrayDeque[n];
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int a = edge[0], b = edge[1];
            if (tree[a] == null) tree[a] = new ArrayDeque<>();
            if (tree[b] == null) tree[b] = new ArrayDeque<>();
            tree[a].add(new Node(b, succProb[i]));
            tree[b].add(new Node(a, succProb[i]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(o2.val, o1.val);
        });

        pq.add(new Node(start, 1));
        dp[start] = 1;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (dp[curr.index] != curr.val) continue;
            // System.out.println(curr.index + " " + curr.val);
            if (tree[curr.index] != null) {
                for (Node child : tree[curr.index]) {
                    if (dp[child.index] < curr.val * child.val) {
                        dp[child.index] = curr.val * child.val;
                        pq.add(new Node(child.index, dp[child.index]));
                    }
                }
            }
        }
        return dp[end];
    }

    static class Node {
        int index;
        double val;

        public Node(int i, double v) {
            index = i;
            val = v;
        }
    }
}
