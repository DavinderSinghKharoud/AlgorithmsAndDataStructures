package LeetCode;

public class BestTimeToBuyStockFee {

    public static void main(String[] args) {

        System.out.println(new BestTimeToBuyStockFee().maxProfit(
                new int[]{1,3,2,8,4,9}, 2
        ));
    }

    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[] buy = new int[len], sell = new int[len];
        buy[0] = -prices[0] - fee;
        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i] - fee);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[len - 1];
    }
}
