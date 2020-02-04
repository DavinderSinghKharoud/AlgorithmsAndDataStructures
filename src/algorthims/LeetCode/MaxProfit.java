package algorthims.LeetCode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction
 * (i.e., buy one and sell one share of the stock), design an algorithm to find
 * the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 */

public class MaxProfit {

    //Brute Force
    public static int maxProfit(int[] prices) {

        int buy = Integer.MIN_VALUE;
        int sell = Integer.MAX_VALUE;
        int maxprofit = 0;

        for( int i = 0; i<prices.length; i++){

            buy = prices[i];

            for( int j = i+1; j<prices.length; j++){

                sell = prices[j];

                if( Math.abs( buy - sell ) >= maxprofit && buy < sell ){
                    maxprofit = Math.abs( buy - sell );
                }
            }
        }

        return  maxprofit;
    }

    //Faster
    public static int MaxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
    public static void main(String[] args) {

        System.out.println(MaxProfit(new int[]{
                7,1,5,3,6,4
        }) );
    }
}
