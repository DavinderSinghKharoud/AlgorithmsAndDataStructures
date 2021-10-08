package LeetCode;

public class BestTimeToBuyAndSellStockIII {

    public static void main(String[] args) {

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
        ans = 0;
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
