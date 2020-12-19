package Algorithms.HackerRank;

import java.util.*;

/**
 * Victoria has a tree, , consisting of  nodes numbered from  to . Each edge from node  to  in tree  has an integer weight, .
 * Let's define the cost, , of a path from some node  to some other node  as the maximum weight () for any edge in the unique path from node  to node .
 * Victoria wants your help processing  queries on tree , where each query contains  integers,  and , such that . For each query, she wants to print the number of different paths in  that have a cost, , in the inclusive range .
 * It should be noted that path from some node  to some other node  is considered same as path from node  to  i.e  is same as .
 * Input Format
 * The first line contains  space-separated integers,  (the number of nodes) and  (the number of queries), respectively.
 * Each of the  subsequent lines contain  space-separated integers, , , and , respectively, describing a bidirectional road between nodes  and  which has weight .
 * The  subsequent lines each contain  space-separated integers denoting  and .
 * Constraints
 *
 *
 *
 *
 * Scoring
 *  for  of the test data.
 *  for  of the test data.
 * Output Format
 * For each of the  queries, print the number of paths in  having cost  in the inclusive range  on a new line.
 * Sample Input
 * 5 5
 * 1 2 3
 * 1 4 2
 * 2 5 6
 * 3 4 1
 * 1 1
 * 1 2
 * 2 3
 * 2 5
 * 1 6
 * Sample Output
 * 1
 * 3
 * 5
 * 5
 * 10
 * Explanation
 * :
 * :
 * :
 * :
 * ...etc.
 */
public class SuperMaximumCostQueries {

    static long[] solve(int[][] tree, int[][] queries) {

        //Cost values from low to high
        Arrays.sort(tree, Comparator.comparingInt(o -> o[2]));

        TreeMap<Integer, Long> map = new TreeMap<>();
        int[] parents = new int[tree.length + 2]; //Used for disjoint set

        Arrays.fill(parents, -1);

        for (int[] ints : tree) {
            int parent1 = find(ints[0], parents);
            int parent2 = find(ints[1], parents);
            int cost = ints[2];

            long number1 = -parents[parent1];
            long number2 = -parents[parent2];

            map.put(cost, map.getOrDefault(cost, 0L) + number1 * number2); //Total number of paths

            if (parents[parent1] >= parents[parent2]) {
                parents[parent2] += parents[parent1];
                parents[parent1] = parent2;
            } else {
                parents[parent1] += parents[parent2];
                parents[parent2] = parent1;
            }

        }

        long totalSum = 0;
        int index = 1;
        long[][] dp = new long[map.size() + 1][2]; //Where index 0 will contain cost and index 1 have total paths having that cost as maximum
        dp[0][0] = dp[0][1] = 0;

        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            totalSum += entry.getValue();
            dp[index][0] = entry.getKey();
            dp[index][1] = totalSum;
            index++;
        }


        long[] result = new long[queries.length];

        for (index = 0; index < queries.length; index++) {
            int left = lowerBound(queries[index][0], dp) - 1;
            int right = lowerBound(queries[index][1], dp);

            if (right >= dp.length) {
                --right;
            }
            if (dp[right][0] > queries[index][1]) {
                --right;
            }

            result[index] = dp[right][1] - dp[left][1];
        }

        return result;
    }

    static int find(int node, int[] parent) {
        while (parent[node] >= 0) {
            node = parent[node];
        }
        return node;
    }

    static int lowerBound(int target, long[][] dp) {
        int left = 0;
        int right = dp.length - 1;

        while (left <= right) {
            int middle = (left + right) >> 1;
            if (dp[middle][0] >= target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }


    public static void main(String[] args) {

        System.out.print(Arrays.toString(solve(new int[][]{{1, 2, 3}, {1, 4, 2}, {2, 5, 6}, {3, 4, 1}}, new int[][]{{1, 1}, {1, 2}, {2, 3}, {2, 5}, {1, 6}})));
    }
}
