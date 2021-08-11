package LeetCode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
**/

public class BestTimetoBuyandSellStockwithCooldown {

	//Time Limit exceeded
	//O( n ^ n )
	public static int maxProfit(int[] prices) {

		return helper( prices, 0 );
	}
	
	public static int helper( int[] prices, int index ){
		if( index >= prices.length ){
			return 0;
		}
		
		int max = 0;

		for (int buy = index; buy < prices.length; buy++) {

			int temp_max = 0;

			for( int sell = buy + 1; sell< prices.length; sell ++ ){
				if( prices[buy] < prices[sell] ){
					int profit = helper( prices, sell + 2 ) + prices[sell] - prices[buy];

					temp_max = Math.max( temp_max, profit );
				}
			}

			max = Math.max( max, temp_max );
		}

		return max;
	}
	
	public static void main (String[] args) {
		
		System.out.println( maxProfit2( new int[]{
			1,2,3,0,2
		} ) ) ;
	}

	/*
dp[i] refers to the max profit at prices[i]
temp refers to the min buy price before the point of i (I mean we can see all the transactions before this point as one transaction)
Due to cooldown, temp = Math.min(temp, prices[j] - dp[j-2]), however, the second day shouldn't consider cooldown*/
	//O(n) time complexity and space complexity
	public static int maxProfit2(int[] prices) {
		if( prices.length <= 1 ) return 0;
		int []dp = new int[ prices.length ];

		int temp = prices[0];

		for( int index = 1; index<prices.length; index++ ){
			//get the max of one index less and of current price minus minimum up to now
			dp[ index ] = Math.max( dp[ index - 1 ], prices[ index ] - temp );

			//if index is greater than 2, then we need to check the cool down period
			if( index >= 2 ){
				temp = Math.min( temp, prices[index] - dp[ index - 2 ] );
			}else{
				temp = Math.min( temp, prices[index] - dp[index - 1] );
			}
		}

		return dp[ prices.length - 1];
	}

}

