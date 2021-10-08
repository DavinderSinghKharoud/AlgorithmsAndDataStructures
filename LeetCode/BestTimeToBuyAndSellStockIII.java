package LeetCode;

public class BestTimeToBuyAndSellStockIII {

    public static void main(String[] args) {
        System.out.println(new BestTimeToBuyAndSellStockIII().maxProfit2(new int[]{3,3,5,0,0,3,1,4}));
    }

    public int maxProfit2(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0, buy2 = Integer.MIN_VALUE, sell2 = 0;
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[] left = new int[len];
        int[] right = new int[len];

        int min = prices[0];
        int ans = 0;
        for(int i = 1; i < len; i++){
            min = Math.min(prices[i], min);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        int max = prices[len - 1];
        for(int i = len - 2; i >= 0; i--){
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i],  max - prices[i]);
        }

        ans = Math.max(right[0], left[len - 1]);
        for(int i = 1; i < len - 1; i++){
            ans = Math.max(ans, left[i - 1] + right[i]);
        }
        return ans;
    }
}
