package LeetCode;

public class MaxBuyProfit_IV {

    public static void main(String[] args) {

        System.out.println(new MaxBuyProfit_IV().maxProfit(2, new int[]{
                3,2,6,5,0,3
        }));
    }
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        int[][] dp = new int[k + 1][len];

        /*
        dp[i][j] = max{
                            dp[i][j - 1] // we don't sell
                            prices[i] - maxDiff
                        }
                    maxDiff = max(dp[i - 1][j] - prices[j], max)
        */

        for(int transaction = 1; transaction <= k; transaction++){
            int maxDiff = -prices[0];
            for(int day = 1; day < len; day++){
                dp[transaction][day] = Math.max(
                        dp[transaction][day - 1],
                        prices[day] + maxDiff);

                maxDiff = Math.max(maxDiff, dp[transaction - 1][day]
                        - prices[day]);
            }
        }
        return dp[k][len - 1];
    }
}
