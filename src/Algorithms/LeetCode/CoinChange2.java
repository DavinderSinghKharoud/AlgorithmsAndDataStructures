package Algorithms.LeetCode;

import java.util.Arrays;

public class CoinChange2 {

    //O(n * amount) time complexity and O(n * m) space complexity
    public static int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for(int[] state: dp ){
            Arrays.fill( state, - 1 );
        }
        return helper( amount, coins, 0, dp );
    }

    private static int helper(int amount, int[] coins, int index, int[][] dp ) {

        if( amount == 0 ){
            return 1;
        }
        if( amount < 0 || index >= coins.length ){
            return 0;
        }

        if( dp[index][amount] != -1 ) return dp[index][amount];

        return dp[index][amount] = helper(amount - coins[index], coins, index, dp) + helper(amount, coins, index + 1, dp);
    }

    public static void main(String[] args) {
        System.out.println( change2(5, new int[]{
                1, 2, 5
        }));
    }

    //Up down approach O(n * amount) time and space complexity
    public static int change2(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        dp[0][0] = 1;

        for( int coin = 1; coin <= coins.length; coin++ ){
            for( int value = 0; value < amount + 1; value++ ){
                if( coins[coin - 1] > value ){
                    dp[coin][value] = dp[coin - 1][value];
                }else{
                    dp[coin][value] = dp[coin - 1][value] + dp[coin][value - coins[coin - 1]];
                }
            }
        }

        return dp[coins.length][amount];
    }

    //O( n * amount ) time complexity and O( amount ) space complexity
    public static int change3(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        dp[0] = 1;

        for( int coin: coins ){
            for( int value = coin; value < amount + 1; value++ ){
                dp[value] += dp[ value - coin ];
            }
        }

        return dp[amount];
    }
}
