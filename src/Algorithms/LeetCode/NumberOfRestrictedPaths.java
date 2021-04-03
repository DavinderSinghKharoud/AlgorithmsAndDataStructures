package Algorithms.LeetCode;

import java.util.*;

/**
 * There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.
 *
 * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.
 *
 * The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
 *
 * Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
 * Output: 3
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
 * 1) 1 --> 2 --> 5
 * 2) 1 --> 2 --> 3 --> 5
 * 3) 1 --> 3 --> 5
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
 * Output: 1
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * n - 1 <= edges.length <= 4 * 104
 * edges[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= weight <= 105
 * There is at most one edge between any two nodes.
 * There is at least one path between any two nodes.
 */
public class NumberOfRestrictedPaths {

    public static void main(String[] args) {

        NumberOfRestrictedPaths obj = new NumberOfRestrictedPaths();
        System.out.println(obj.countRestrictedPaths(5, new int[][]{{1, 2, 3}, {1, 3, 3}, {2, 3, 1}, {1, 4, 2},
                {5, 2, 2}, {3, 5, 1}, {5, 4, 10}}));
    }

    long[] distance;
    int n;
    ArrayDeque<int[]>[] tree;
    boolean[] vis;
    int[] dp;

    public int countRestrictedPaths(int n, int[][] edges) {
        this.n = n;
        distance = new long[n];
        tree = new ArrayDeque[n];
        Arrays.fill(distance, Integer.MAX_VALUE);

        Arrays.setAll(tree, o -> new ArrayDeque<>());
        for (int[] edge : edges) {
            int a = edge[0] - 1, b = edge[1] - 1, w = edge[2];
            tree[a].add(new int[]{b, w});
            tree[b].add(new int[]{a, w});
        }

        // Dijikstra for shortest path
        vis = new boolean[n];
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));
        distance[n - 1] = 0;
        pq.add(new long[]{0, n - 1});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int node = (int) curr[1];

            if (vis[node])
                continue;
            vis[node] = true;

            for (int[] adj : tree[node]) {
                int b = adj[0], w = adj[1];
                if (distance[node] + w < distance[b]) {
                    distance[b] = distance[node] + w;
                    pq.add(new long[]{distance[b], b});
                }
            }
        }

        dp = new int[n];
        Arrays.fill(dp, -1);
        return traverse(0);
    }

    int limit = (int) 1e9 + 7;


    public int traverse(int node) {
        if (dp[node] != -1) return dp[node];

        if (node == n - 1)
            return 1;
        long currDis = distance[node];
        int count = 0;
        for (int[] adj : tree[node]) {
            int curr = adj[0];
            if (distance[curr] < currDis) {
                count += traverse(curr);
                if (count >= limit) {
                    count -= limit;
                }
            }
        }
        dp[node] = count;
        return count;
    }
}
