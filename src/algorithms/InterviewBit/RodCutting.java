package algorithms.InterviewBit;

/**
 * There is a rod of length N lying on x-axis with its left end at x = 0 and right end at x = N. Now, there are M weak points on this rod denoted by positive integer values(all less than N) A1, A2, …, AM. You have to cut rod at all these weak points. You can perform these cuts in any order. After a cut, rod gets divided into two smaller sub-rods. Cost of making a cut is the length of the sub-rod in which you are making a cut.
 *
 * Your aim is to minimise this cost. Return an array denoting the sequence in which you will make cuts. If two different sequences of cuts give same cost, return the lexicographically smallest.
 *
 * Notes:
 *
 * Sequence a1, a2 ,…, an is lexicographically smaller than b1, b2 ,…, bm, if and only if at the first i where ai and bi differ, ai < bi, or if no such i found, then n < m.
 * N can be upto 109.
 * For example,
 *
 * N = 6
 * A = [1, 2, 5]
 *
 * If we make cuts in order [1, 2, 5], let us see what total cost would be.
 * For first cut, the length of rod is 6.
 * For second cut, the length of sub-rod in which we are making cut is 5(since we already have made a cut at 1).
 * For third cut, the length of sub-rod in which we are making cut is 4(since we already have made a cut at 2).
 * So, total cost is 6 + 5 + 4.
 *
 * Cut order          | Sum of cost
 * (lexicographically | of each cut
 *  sorted)           |
 * ___________________|_______________
 * [1, 2, 5]          | 6 + 5 + 4 = 15
 * [1, 5, 2]          | 6 + 5 + 4 = 15
 * [2, 1, 5]          | 6 + 2 + 4 = 12
 * [2, 5, 1]          | 6 + 4 + 2 = 12
 * [5, 1, 2]          | 6 + 5 + 4 = 15
 * [5, 2, 1]          | 6 + 5 + 2 = 13
 *
 *
 * So, we return [2, 1, 5].
 */

import java.util.ArrayList;

public class RodCutting {

    static ArrayList<Integer> result;
    static int[] cuts;
    static int[][] parent;
    public static ArrayList<Integer> rodCut(int rod, ArrayList<Integer> scores) {
        int n = scores.size() + 2;
        cuts = new int[n];
        cuts[0] = 0;
        for (int i = 0; i < scores.size(); i++) {
            cuts[i + 1] = scores.get(i);
        }
        cuts[n - 1] = rod;

        long[][] dp = new long[n][n];
        parent = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int s = 0; s < n - len; s++) {
                int e = s + len;
                    for (int k = s + 1; k < e; k++) {
                    long sum = cuts[e] - cuts[s] + dp[s][k] + dp[k][e];
                    if (dp[s][e] == 0 || sum < dp[s][e]) {
                        dp[s][e] = sum;
                        parent[s][e] = k;
                    }
                }
            }
        }

        result = new ArrayList<>();
        backTrack(0, n - 1);

        return result;
    }

    private static void backTrack(int s, int e) {
        if (s + 1 >= e) {
            return;
        }

        result.add(cuts[parent[s][e]]);
        backTrack(s, parent[s][e]);
        backTrack(parent[s][e], e);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(5);
        //arrayList.add(5);
        System.out.println( rodCut(6, arrayList ));
    }
}
