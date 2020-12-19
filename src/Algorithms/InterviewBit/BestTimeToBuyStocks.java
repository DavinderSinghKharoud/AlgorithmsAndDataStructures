package Algorithms.InterviewBit;

public class BestTimeToBuyStocks {

    /**
     * ay you have an array, A, for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit.
     * <p>
     * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     * <p>
     * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     *
     * @return int
     */
    public static int maxProfit(final int[] arr) {
        int res = 0;

        for (int index = 1; index < arr.length; index++) {
            if (arr[index] > arr[index - 1]) {
                res += arr[index] - arr[index - 1];
            }
        }

        return res;


    }

    /**
     * Say you have an array, A, for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete at most 2 transactions.
     * <p>
     * Return the maximum possible profit.
     * <p>
     * Note: You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * <p>
     * <p>
     * Input Format:
     * <p>
     * The first and the only argument is an integer array, A.
     * Output Format:
     * <p>
     * Return an integer, representing the maximum possible profit.
     * Constraints:
     * <p>
     * 1 <= length(A) <= 7e5
     * 1 <= A[i] <= 1e7
     * Examples:
     * <p>
     * Input 1:
     * A = [1, 2, 1, 2]
     * <p>
     * Output 1:
     * 2
     * <p>
     * Explanation 1:
     * Day 0 : Buy
     * Day 1 : Sell
     * Day 2 : Buy
     * Day 3 : Sell
     * <p>
     * Input 2:
     * A = [7, 2, 4, 8, 7]
     * <p>
     * Output 2:
     * 6
     * <p>
     * Explanation 2:
     * Day 1 : Buy
     * Day 3 : Sell
     */
    public static int maxProfit2(final int[] arr) {

        int res = 0;
        int len = arr.length;

        if (len < 2) return 0;
        int[] begin = new int[len];
        int[] end = new int[len];

        int min = arr[0];
        //Max profit from start at current index
        for (int index = 1; index < len; index++) {
            min = Math.min(min, arr[index]);
            begin[index] = Math.max(begin[index - 1], arr[index] - min);
        }

        int max = arr[len - 1];
        //Max profit from end at current index
        for (int index = len - 2; index >= 0; index--) {
            max = Math.max(max, arr[index]);
            end[index] = Math.max(end[index + 1], max - arr[index]);
        }

        for (int index = 0; index < len - 1; index++) {
            res = Math.max(res, begin[index] + end[index]);
        }

        return res;

    }

    /**
     * Say you have an array, A, for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction (i.e, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Return the maximum possible profit.
     *
     *
     *
     * Problem Constraints
     * 0 <= len(A) <= 7e5
     *
     * 1 <= A[i] <= 1e7
     *
     *
     *
     * Input Format
     * The first and the only argument is an array of integers, A.
     *
     *
     *
     * Output Format
     * Return an integer, representing the maximum possible profit.
     *
     *
     *
     * Example Input
     * Input 1:
     *
     *  A = [1, 2]
     * Input 2:
     *
     *  A = [1, 4, 5, 2, 4]
     *
     *
     * Example Output
     * Output 1:
     *  1
     * Output 2:
     *
     *  4
     *
     *
     * Example Explanation
     * Explanation 1:
     *
     *  Buy the stock on day 0, and sell it on day 1.
     * Explanation 2:
     *
     *  Buy the stock on day 0, and sell it on day 2.
     */

    //O(n) time complexity
    public static int maxProfit3(final int[] arr) {
        int res = 0;
        if (arr.length == 0) return 0;
        int min = arr[0];

        for (int index = 1; index < arr.length; index++) {

            min = Math.min(min, arr[index]);
            res = Math.max(res, arr[index] - min);
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(maxProfit3(new int[]{1, 4, 5, 2, 4}));
    }
}
