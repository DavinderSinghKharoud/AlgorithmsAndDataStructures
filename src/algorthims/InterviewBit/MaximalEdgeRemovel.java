package algorthims.InterviewBit;

/**
 * Given an undirected tree with an even number of nodes. Consider each connection between a parent and child node to be an edge.
 * <p>
 * You need to remove maximum number of these edges, such that the disconnected subtrees that remain each have an even number of nodes.
 * <p>
 * Return the maximum number of edges you can remove.
 * <p>
 * <p>
 * <p>
 * Problem Constraints
 * 2 <= A <= 105
 * <p>
 * 1 <= B[i][0], B[i][1] <= A
 * <p>
 * Integer A will be even.
 * <p>
 * <p>
 * <p>
 * Input Format
 * First argument is an integer A denoting the number of nodes in the tree.
 * <p>
 * Second argument is a 2D array B of size (A-1) * 2, denoting the edge between nodes B[i][0] and B[i][1].
 * <p>
 * <p>
 * <p>
 * Output Format
 * Return an integer, denoting the maximum number of edges you can remove.
 * <p>
 * <p>
 * <p>
 * Example Input
 * Input 1:
 * <p>
 * A = 6
 * B = [
 * [1, 2]
 * [1, 3]
 * [1, 4]
 * [3, 5]
 * [4, 6]
 * ]
 * Input 2:
 * <p>
 * A = 2
 * B = [
 * [1, 2]
 * ]
 * <p>
 * <p>
 * Example Output
 * Output 1:
 * <p>
 * 2
 * Output 2:
 * <p>
 * 0
 * <p>
 * <p>
 * Example Explanation
 * Explanation 1:
 * <p>
 * 1
 * / | \
 * 2  3  4
 * |   \
 * 5    6
 * Maximum number of edges we can remove is 2, i.e (1, 3) and (1, 4)
 * Explanation 2:
 * <p>
 * We can't remove any edges.
 */

import java.util.*;

public class MaximalEdgeRemovel {

    static int res = 0;

    public static int solve(int nodes, int[][] arr) {
        if (nodes == 2) return 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] curr : arr) {
            List<Integer> lst = map.getOrDefault(curr[0], new ArrayList<>());
            lst.add(curr[1]);
            map.put(curr[0], lst);

            List<Integer> lst2 = map.getOrDefault(curr[1], new ArrayList<>());
            lst2.add(curr[0]);
            map.put( curr[1], lst2);
        }
        Set<Integer> visited = new HashSet<>();
        helper(map, visited, arr[0][0]);
        return res;

    }

    public static int helper(Map<Integer, List<Integer>> map, Set<Integer> visited, int node) {

        visited.add(node);
        int curr_count = 0;


        if (!map.containsKey(node)) return 1;

        for (int index = 0; index < map.get(node).size(); index++) {
            int child = map.get(node).get(index);

            if (!visited.contains(child)) {
                int totalNodes = helper(map, visited, child);

                //if there are even then
                if (totalNodes % 2 == 0) {
                    res++;
                } else {
                    curr_count += totalNodes;
                }
            }
        }

        return curr_count + 1;
    }

    public static void main(String[] args) {


        System.out.println(solve(6, new int[][]{{1, 2}, {1, 3}, {1, 7}, {2, 8}, {2, 10}
                , {3, 9}, {3, 4}, {4, 6}, {4, 5}}));
    }
}
