package LeetCode;

import java.util.*;

public class RedundantConnectionII {

    public static void main(String[] args) {

        RedundantConnectionII obj = new RedundantConnectionII();
        // System.out.println(
        // Arrays.toString(obj.findRedundantDirectedConnection(new int[][] { { 1, 2 }, {
        // 1, 3 }, { 2, 3 } })));
//        System.out.println(Arrays.toString(
//                obj.findRedundantDirectedConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}})));
        System.out.println(Arrays.toString(
                obj.findRedundantDirectedConnection(new int[][]{{2, 1}, {3, 1}, {4, 2}, {1, 4}})));

    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;

        int cycleEdge = -1, twoParent = -1;
        int[] parent = new int[len + 1];
        Arrays.fill(parent, -1);

        for (int i = 0; i < len; i++) {
            int u = edges[i][0], v = edges[i][1];
            if (parent[v] != -1) {
                // Assume we removed this edge
                twoParent = i;
                break;
            } else {
                parent[v] = u;
            }
        }

        Union un = new Union(len);
        for (int i = 0; i < len; i++) {
            if (i == twoParent)
                continue; // As we are assuming we removed this edge
            int u = edges[i][0], v = edges[i][1];
            if (!un.unite(u, v)) {
                // THis edge is making the cycle
                cycleEdge = i;
                break;
            }
        }

        // If there is only a cycle exist
        if (twoParent == -1) {
            return edges[cycleEdge];
        }

        // If we accidently removed the wrong edge having two parents
        if (cycleEdge != -1) {
            // Both cases exist
            int v = edges[twoParent][1];
            int u = parent[v];
            return new int[]{u, v};
        }

        return edges[twoParent];

    }

    class Union {
        int[] parent, rank;

        public Union(int len) {
            parent = new int[len + 1];
            rank = new int[len + 1];
            Arrays.fill(parent, -1);
            Arrays.fill(rank, -1);
        }


        public int find(int node) {
            if (parent[node] == -1) {
                return node;
            }
            return parent[node] = find(parent[node]);
        }

        public boolean unite(int a, int b) {
            int parent1 = find(a), parent2 = find(b);
            if (parent1 == parent2)
                return false;
            if (rank[a] < rank[b]) {
                a = a ^ b ^ (b = a);
            }
            parent[b] = a;
            rank[a] += rank[b];
            rank[b] = 0;
            return true;
        }
    }

}
