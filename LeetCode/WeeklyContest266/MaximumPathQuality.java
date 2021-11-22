package LeetCode.WeeklyContest266;

import java.util.*;

public class MaximumPathQuality {
    public static void main(String[] args) {
        MaximumPathQuality o = new MaximumPathQuality();
    }

    ArrayDeque<Node>[] tree;
    int[] values;
    int[] seen;
    int ans;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        seen = new int[values.length];
        this.values = values;
        tree = new ArrayDeque[values.length];
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if (tree[a] == null) tree[a] = new ArrayDeque<>();
            if (tree[b] == null) tree[b] = new ArrayDeque<>();
            tree[a].add(new Node(b, edge[2]));
            tree[b].add(new Node(a, edge[2]));
        }
        seen[0] = 1;
        find(0, 0, maxTime);
        return ans + values[0];
    }

    int find(int index, int collect, int maxTime) {
        if (maxTime < 0) return ans;
        if (index == 0) {
            ans = Math.max(ans, collect);
        }

        if (tree[index] != null) {
            for (Node child : tree[index]) {
                seen[child.index]++;
                int increment = (seen[child.index] == 1 ? values[child.index] : 0);
                find(child.index, collect + increment, maxTime - child.cost);
                seen[child.index]--;
            }
        }
        return collect;
    }

    static class Node {
        int index, cost;

        public Node(int i, int c) {
            index = i;
            cost = c;
        }
    }
}
