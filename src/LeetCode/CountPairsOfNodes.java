package LeetCode;

import java.util.*;

/**
 * You are given an undirected graph represented by an integer n, which is the number of nodes, and edges, where edges[i] = [ui, vi] which indicates that there is an undirected edge between ui and vi. You are also given an integer array queries.
 *
 * The answer to the jth query is the number of pairs of nodes (a, b) that satisfy the following conditions:
 *
 * a < b
 * cnt is strictly greater than queries[j], where cnt is the number of edges incident to a or b.
 * Return an array answers such that answers.length == queries.length and answers[j] is the answer of the jth query.
 *
 * Note that there can be repeated edges.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
 * Output: [6,5]
 * Explanation: The number of edges incident to at least one of each pair is shown above.
 * Example 2:
 *
 * Input: n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
 * Output: [10,10,9,8,6]
 */
public class CountPairsOfNodes {

    public static void main(String[] args) {

        int[][] arr = new int[7][];
        // String test =
        // "[[2,4],[5,1],[1,4],[1,2],[5,3],[2,1],[5,4],[1,5],[3,2],[3,4],[1,3],[2,5],[1,5],[2,5],[3,2],[1,4],[5,4]]";
        String test = "[[3,4],[1,2],[5,1],[6,7],[4,1],[5,2],[1,3]]";

        test = test.substring(1, test.length() - 1);
        test = test.replaceAll("\\[", "");
        test = test.replaceAll("]", "");

        int i = 0;
        int[] curr = new int[2];
        int a = -1, b = -1;
        String[] ac = test.split(",");
        for (String c : ac) {
            int num = Integer.parseInt(c);
            if (a == -1) {
                a = num;
            } else {
                b = num;
                curr[0] = a;
                curr[1] = b;
                a = -1;
                b = -1;
                arr[i++] = curr;
                curr = new int[2];
            }
        }
//      System.out.println(Arrays
//            .toString(countPairs2(7, arr, new int[] { 3, 0, 0, 2, 3, 2, 3, 0, 1, 2, 0, 1, 1, 0, 2, 0, 3, 0, 6, 5 })));

        // System.out.println(Arrays.toString(countPairs(5, arr, new int[]{11, 4, 4, 8,
        // 0, 0, 0, 0, 0, 1, 1, 8, 5, 2, 5, 12, 9, 11})));
//      System.out.println(Arrays.toString(countPairs2(5, new int[][] { { 4, 5 }, { 1, 3 }, { 1, 4 } },
//            new int[] { 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 2 })));

        System.out.println(Arrays.toString(countPairs2(5,
                new int[][]{{1, 5}, {1, 5}, {3, 4}, {2, 5}, {1, 3}, {5, 1}, {2, 3}, {2, 5}},
                new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(countPairs2(4,
                new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 3}, {2, 1}},
                new int[]{2, 3})));
    }

    public static int[] countPairs2(int n, int[][] edges, int[] queries) {

        int[] count = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();


        for (int[] edge : edges) {
            int a = edge[0] - 1, b = edge[1] - 1;
            count[a]++;
            count[b]++;
            if(a > b){
                b = a ^ b ^ (a = b);
            }
            map.put(a * 20000 + b, map.getOrDefault(a * 20000 + b, 0) + 1);
        }

        int[] arr = count.clone();
        shuffle(arr);
        Arrays.sort(arr);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];

            // Count all the pairs that can possibly have number of nodes greater than query
            int start = 0, end = arr.length - 1;
            while (start < end) {
                if (arr[start] + arr[end] > query) {
                    ans[i] += (end - start);
                    end--;
                } else {
                    start++;
                }
            }

            // Remove all the edges which cannot sum up to the value
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                int a = key / 20000, b = key % 20000;

                if (count[a] + count[b] > query && count[a] + count[b] - entry.getValue() <= query) {
                    ans[i]--;
                }

            }
        }
        return ans;
    }

    static void shuffle(int[] aa) {
        int n = aa.length;
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
    }

    public static int[] countPairs(int n, int[][] edges, int[] queries) {

        int[][] dp = new int[n][n];
        int[] count = new int[n];

        int queryMin = Integer.MAX_VALUE;
        for (int query : queries) {
            queryMin = Math.min(queryMin, query);
        }

        for (int[] edge : edges) {
            int a = edge[0] - 1, b = edge[1] - 1;
            dp[a][b]++;
            dp[b][a]++;
            count[a]++;
            count[b]++;
        }

        List<Integer> lst = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int total = count[i] + count[j];
                total -= dp[i][j];
                if (total >= queryMin) {
                    lst.add(total);
                }
            }
        }

        Collections.sort(lst);

        int[] answers = new int[queries.length];
        int len = lst.size();
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            int index = binarySearch(lst, query);

            if (index < 0 || index >= lst.size()) {
                answers[i] = (index == 0) ? len : 0;
            } else if (lst.get(index) == query) {
                answers[i] = len - (index + 1);
            } else {
                answers[i] = len - index;
            }
        }

        return answers;
    }

    public static int binarySearch(List<Integer> lst, int val) {
        int low = 0, high = lst.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (high + low) / 2;
            int curr = lst.get(mid);
            if (curr == val) {
                if (mid + 1 >= lst.size() || lst.get(mid + 1) > val) {
                    return mid;
                }
                low = mid + 1;
            } else if (curr < val) {
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return low;
    }
}
