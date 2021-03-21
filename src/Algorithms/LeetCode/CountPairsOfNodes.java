package Algorithms.LeetCode;

import java.util.*;

public class CountPairsOfNodes {

    public static void main(String[] args) {

        int[][] arr = new int[7][];
        // String test = "[[2,4],[5,1],[1,4],[1,2],[5,3],[2,1],[5,4],[1,5],[3,2],[3,4],[1,3],[2,5],[1,5],[2,5],[3,2],[1,4],[5,4]]";
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
        System.out.println(Arrays.toString(countPairs(7, arr, new int[]{3, 0, 0, 2, 3, 2, 3, 0, 1, 2, 0, 1, 1, 0, 2, 0, 3, 0, 6, 5})));

        // System.out.println(Arrays.toString(countPairs(5, arr, new int[]{11, 4, 4, 8, 0, 0, 0, 0, 0, 1, 1, 8, 5, 2, 5, 12, 9, 11})));
        System.out.println(Arrays.toString(countPairs(5, new int[][]{{4, 5}, {1, 3}, {1, 4}}, new int[]{0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 2})));

        System.out.println(Arrays.toString(countPairs(5, new int[][]{{1, 5}, {1, 5}, {3, 4}, {2, 5}, {1, 3}, {5, 1}, {2, 3}, {2, 5}}, new int[]{1, 2, 3, 4, 5})));
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
            } else high = mid - 1;
        }
        return low;
    }
}
