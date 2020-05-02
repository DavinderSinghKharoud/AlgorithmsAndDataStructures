package Graphs;

/**
 * There are N network nodes, labelled 1 to N.
 * <p>
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 * <p>
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 */

import java.util.Arrays;

public class BellMan_Ford_Network_Delay_check {

    //O( m * n ) time complexity and O(n) space complexity
    public static int networkDelayTime(int[][] times, int N, int K) {

        int edges = times.length;

        int dp[] = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int time = 0;

        dp[K] = 0;

        for (int i = 0; i < N; i++) {

            for (int[] edge : times) {

                if (dp[edge[0]] != Integer.MAX_VALUE) {
                    dp[edge[1]] = Math.min(dp[edge[1]], dp[edge[0]] + edge[2]);
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            time = Math.max(dp[i], time);
        }

        return time != Integer.MAX_VALUE ? time : -1;
    }

    public static void main(String[] args) {

        System.out.println(networkDelayTime(new int[][]{
                {2, 1, 1}, {2, 3, 1}, {3, 4, 1}
        }, 4, 2));

    }
}
