package LeetCode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times).
 * <p>
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Example 2:
 * <p>
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 * engaging multiple transactions at the same time. You must sell before buying again.
 */
public class BestTimetoBuyandSellStockII {

    //Brute Force ( O(n^n) )
    public static int maxProfit(int[] prices) {
        return calculateProfit(prices, 0);
    }

    private static int calculateProfit(int[] prices, int index) {
        if (index >= prices.length) {
            return 0;
        }

        int max = 0;

        for (int start = index; start < prices.length; start++) {
            int maxProfit = 0;

            for (int i = start + 1; i < prices.length; i++) {

                if (prices[start] < prices[i]) {
                    int profit = calculateProfit(prices, i + 1) + prices[i] - prices[start];

                    if (profit > maxProfit) {
                        maxProfit = profit;
                    }
                }

                if (maxProfit > max) {
                    max = maxProfit;
                }


            }
        }

        return max;
    }

    public static int maxProfit2(int[] prices) {

        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {

                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;

    }

    public static void main(String[] args) {

        System.out.println(maxProfit2(new int[]{
                7,1,5,3,6,4
        }));
    }
}
