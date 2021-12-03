package Preparation;

import java.util.*;

public class TwoCityScheduling {
    int max = (int) (1e5);
    int[][][] dp;

    public static void main(String[] args) {
        TwoCityScheduling o = new TwoCityScheduling();
        System.out.println(o.twoCitySchedCost(new int[][]{
                {10, 20}, {30, 200}, {400, 50}, {30, 20}
        }));
    }

    /**
     * O(n Log n)
     * @param costs
     * @return
     */
    public int twoCitySchedCost2(int[][] costs) {
        int len = costs.length;
        int total = 0;
        for(int[] cost: costs) total += cost[0];
        int[] diff = new int[len];
        for(int i = 0; i < len; i++){
            diff[i] = costs[i][1] - costs[i][0];
        }
        Arrays.sort(diff);
        for(int i = 0;i < len/2; i++){
            total += diff[i];
        }
        return total;
    }

    /**
     * O(n ^ 3)
     **/
    public int twoCitySchedCost(int[][] costs) {
        int len = costs.length;
        dp = new int[len][len][len];
        for (int[][] state : dp) {
            for (int[] curr : state) {
                Arrays.fill(curr, -1);
            }
        }
        return find(costs, 0, 0, 0);
    }

    int find(int[][] costs, int index, int firstCity, int secondCity) {
        if (firstCity == secondCity && firstCity == costs.length / 2) return 0;
        if (Math.abs(firstCity - secondCity) > (costs.length - index)) return max;
        if (dp[index][firstCity][secondCity] != -1) return dp[index][firstCity][secondCity];

        int ans = costs[index][0] + find(costs, index + 1, firstCity + 1, secondCity);
        ans = Math.min(ans, costs[index][1] + find(costs, index + 1, firstCity, secondCity + 1));

        return dp[index][firstCity][secondCity] = ans;
    }
}
