package algorthims.LeetCode;

import java.util.Arrays;

public class CoinChange {

    public static int coinChange(int[] coins, int amount) {


        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {

            for (int j = 0; j < coins.length; j++) {
                int checkIndex = i - coins[j];
                if ( coins[j] <= i) {
                    int bestExist = dp[checkIndex];
                    dp[i] = Math.min(dp[i], bestExist + 1);
                }
            }

        }

        return dp[amount] > amount ? -1: dp[amount];
    }

    public static void main(String[] args) {

        System.out.println( coinChange(new int[]{
                2
        }, 3) );
    }
}
