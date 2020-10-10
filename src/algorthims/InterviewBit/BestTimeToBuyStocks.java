package algorthims.InterviewBit;

import java.util.*;
public class BestTimeToBuyStocks {

	/**
	 * ay you have an array, A, for which the ith element is the price of a given stock on day i.
	 *
	 * Design an algorithm to find the maximum profit.
	 *
	 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
	 *
	 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	 * @return int
	 */
    public static int maxProfit(final int[] arr) {
		int res = 0;

		for( int index = 1; index < arr.length; index++ ){
			if( arr[index] > arr[index - 1] ){
				res += arr[index] - arr[index - 1];
			}
		}

		return res;


    }

	/**
	 * Say you have an array, A, for which the ith element is the price of a given stock on day i.
	 *
	 * Design an algorithm to find the maximum profit. You may complete at most 2 transactions.
	 *
	 * Return the maximum possible profit.
	 *
	 * Note: You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	 *
	 *
	 * Input Format:
	 *
	 * The first and the only argument is an integer array, A.
	 * Output Format:
	 *
	 * Return an integer, representing the maximum possible profit.
	 * Constraints:
	 *
	 * 1 <= length(A) <= 7e5
	 * 1 <= A[i] <= 1e7
	 * Examples:
	 *
	 * Input 1:
	 *     A = [1, 2, 1, 2]
	 *
	 * Output 1:
	 *     2
	 *
	 * Explanation 1:
	 *     Day 0 : Buy
	 *     Day 1 : Sell
	 *     Day 2 : Buy
	 *     Day 3 : Sell
	 *
	 * Input 2:
	 *     A = [7, 2, 4, 8, 7]
	 *
	 * Output 2:
	 *     6
	 *
	 * Explanation 2:
	 *     Day 1 : Buy
	 *     Day 3 : Sell
	 */
	public static int maxProfit2(final int[] arr) {

		int res = 0;
		int len = arr.length;

		if( len < 2 ) return 0;
		int[] begin = new int[len];
		int[] end = new int[len];

		int min = arr[0];
		//Max profit from start at current index
		for (int index = 1; index < len; index++) {
			min = Math.min(min, arr[index]);
			begin[index] = Math.max( begin[index - 1], arr[index] - min	);
		}

		int max = arr[len - 1];
		//Max profit from end at current index
		for (int index = len - 2; index >= 0; index--) {
			max = Math.max(max, arr[index]);
			end[index] = Math.max( end[index + 1], max - arr[index]	);
		}

		for (int index = 0; index < len - 1; index++) {
			res = Math.max( res, begin[index] + end[index]);
		}

		return res;

	}

	public static void main(String[] args) {

		System.out.println(maxProfit2(new int[]{0,0,3,1,4}));
    }
}
