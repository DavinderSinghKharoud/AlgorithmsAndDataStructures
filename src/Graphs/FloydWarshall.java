package Graphs;

import java.util.Arrays;

/**
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 */
public class FloydWarshall {
    public static int networkDelayTime(int[][] times, int N, int K) {
        int[][] dp = new int[N + 1][N + 1];

        for( int[] row: dp ){
            Arrays.fill(row, Integer.MAX_VALUE/2);
        }
        int res = -1;

        for (int i = 1; i <= N; i++) {
            dp[i][i] = 0;
        }

        for( int[] edge: times ){
            int source = edge[0];
            int target = edge[1];
            int weight = edge[2];

            dp[source][target] = weight;
        }

        for (int i = 1; i < N; i++) {

            for (int j = 1; j < N; j++) {
                for (int k = 1; k < N; k++) {

                    dp[j][k] = Math.min( dp[j][k], dp[j][i] + dp[i][k] );
                }
            }
        }


            for (int index = 1; index <= N; index++) {
                if( index == K ) continue;
                res = Math.max( res, dp[K][index] );

        }
        return (res == Integer.MAX_VALUE/2 ) ? -1 : res;

    }
    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][]{
                {1,2,1}, {2, 1,3}
        }, 2, 2));
    }
}
