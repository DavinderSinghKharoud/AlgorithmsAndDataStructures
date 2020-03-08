package algorthims.LeetCode;

public class BestTimetoBuyandSellStockII {

    //Brute Force
    public static int maxProfit(int[] prices) {
        return calculateProfit( prices, 0);
    }

    private static int calculateProfit(int[] prices, int index) {
        if( index >= prices.length ){
            return 0;
        }

        int max = 0;

        for( int start = index; start<prices.length; start++ ){
           int maxProfit = 0;

           for( int i = start + 1; i<prices.length; i++ ){

               if( prices[start] < prices[i] ){
                   int profit = calculateProfit( prices, i + 1) + prices[i] - prices[start];

                   if( profit > maxProfit ){
                       maxProfit = profit;
                   }
               }

               if( maxProfit > max ){
                   max = maxProfit;
               }


           }
        }

        return max;
    }

    public static void main(String[] args) {

        System.out.println( maxProfit(new int[]{
                1,2,3,4,5
        }));
    }
}
