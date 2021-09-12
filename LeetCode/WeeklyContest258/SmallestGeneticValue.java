package LeetCode.WeeklyContest258;

import java.util.*;

public class SmallestGeneticValue {
    public static void main(String[] args) {
        SmallestGeneticValue o = new SmallestGeneticValue();
        System.out.println(
                Arrays.toString(o.smallestMissingValueSubtree(new int[]{-1, 0, 1, 0, 3, 3}, new int[]{5, 4, 6, 2, 1, 3})));
    }

    int[] ans;
    ArrayDeque<Integer>[] tree;
    boolean[] vis;
    int[] nums;

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        this.nums = nums;
        int len = parents.length;
        tree = new ArrayDeque[len];
        ans = new int[len];
        vis = new boolean[100_007];
        Arrays.fill(ans, 1); // The nodes on path from root to 1 will only have ans > 1, else it will be 1
        for (int i = 0; i < tree.length; i++)
            tree[i] = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                tree[parents[i]].add(i);
            }
        }

        int missing = 1;
        int index = -1;
        for (int i = 0; i < len; i++)
            if (nums[i] == 1) {
                index = i;
                break;
            }
        while (index >= 0) {
            dfs(index);
            while (vis[missing]) {
                missing++;
            }
            ans[index] = missing;
            index = parents[index];
        }
        return ans;
    }

    void dfs(int node) {
        if (!vis[nums[node]]) {
            for (int child : tree[node]) {
                dfs(child);
            }
            vis[nums[node]] = true;
        }
    }
}
