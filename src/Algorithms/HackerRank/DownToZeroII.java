package Algorithms.HackerRank;

/**
 * You are given  queries. Each query consists of a single number . You can perform any of the  operations on  in each move:
 * 1: If we take 2 integers  and  where , , then we can change
 * 2: Decrease the value of  by .
 * Determine the minimum number of moves required to reduce the value of  to .
 * Input Format
 * The first line contains the integer .
 * The next  lines each contain an integer, .
 * Constraints
 *
 *
 * Output Format
 * Output  lines. Each line containing the minimum number of moves required to reduce the value of  to .
 * Sample Input
 * 2
 * 3
 * 4
 * Sample Output
 * 3
 * 3
 * Explanation
 * For test case 1, We only have one option that gives the minimum number of moves.
 * Follow  ->  ->  -> . Hence,  moves.
 * For the case 2, we can either go  ->  ->  ->  ->  or  ->  ->  -> . The 2nd option is more optimal. Hence,  moves.
 */
public class DownToZeroII {

    // Note: If you want to use the same function again and again, build an total dp array and query.
    //Dynamic Programming O(n) time and space complexity
    static int downToZero(int n) {

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int num = 3; num <= n; num++) {

            dp[num] = dp[num - 1] + 1;
            int limit = (int) Math.sqrt(num);

            for (int factor = limit; factor > 1; factor--) {
                if (num % factor == 0) {
                    dp[num] = Math.min(dp[num], dp[num/factor] + 1);
                }
            }


        }
        return dp[n];

    }


    public static void main(String[] args) {

        System.out.println(downToZero(10));
    }
}
