/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:

Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:

Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 * 
 */


public class MinCostClimbingStairs {

	//O(n) time and space complexity
	public static int minCostClimbingStairs1(int[] cost) {
		int len = cost.length;
		if( len == 0 ){
			return 0;
		}
		if( len == 1){
			return cost[0];
		}
		int[] dp = new int[len];
		dp[0] = cost[0];
		dp[1] = cost[1];

		for (int i = 2; i < len; i++) {
			dp[i] = cost[i] + Math.min( dp[i - 1], dp[i - 2] );
		}

		return Math.min( dp[len - 1], dp[ len - 2] );
    }

	public static void main (String[] args) {
		
		System.out.println( minCostClimbingStairs2( new int[]{
			0, 1, 1, 0
		}) );
	}

	//O(n) time complexity and O(1) space complexity
	public static int minCostClimbingStairs2(int[] cost) {
		int len = cost.length;
		for( int index = 2; index < len; index++ ){
			cost[index] += Math.min( cost[index - 1], cost[index - 2] );
		}

		return Math.min( cost[ len - 1 ], cost[ len - 2]);
	}
}

