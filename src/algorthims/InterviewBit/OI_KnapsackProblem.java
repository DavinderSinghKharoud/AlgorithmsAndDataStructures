package algorthims.InterviewBit;

public class OI_KnapsackProblem {

	/**
	 * Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.
	 *
	 * Also given an integer C which represents knapsack capacity.
	 *
	 * Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.
	 *
	 * NOTE:
	 *
	 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
	 *
	 *
	 * Problem Constraints
	 * 1 <= N <= 103
	 *
	 * 1 <= C <= 103
	 *
	 * 1 <= A[i], B[i] <= 103
	 *
	 *
	 *
	 * Input Format
	 * First argument is an integer array A of size N denoting the values on N items.
	 *
	 * Second argument is an integer array B of size N denoting the weights on N items.
	 *
	 * Third argument is an integer C denoting the knapsack capacity.
	 *
	 *
	 *
	 * Output Format
	 * Return a single integer denoting the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.
	 *
	 *
	 *
	 * Example Input
	 * Input 1:
	 *
	 *  A = [60, 100, 120]
	 *  B = [10, 20, 30]
	 *  C = 50
	 * Input 2:
	 *
	 *  A = [10, 20, 30, 40]
	 *  B = [12, 13, 15, 19]
	 *  C = 10
	 *
	 *
	 * Example Output
	 * Output 1:
	 *
	 *  220
	 * Output 2:
	 *
	 *  0
	 *
	 *
	 * Example Explanation
	 * Explanation 1:
	 *
	 *  Taking items with weight 20 and 30 will give us the maximum value i.e 100 + 120 = 220
	 * Explanation 2:
	 *
	 *  Knapsack capacity is 10 but each item has weight greater than 10 so no items can be cons
	 */
    public static void main(String[] args) {

		System.out.println( solve(new int[]{60, 100, 120}, new int[]{10, 20, 30}, 50));
    }
    
    
    public static int solve(int[] prices, int[] weights, int target) {
		
			int len = prices.length;
			
			int[] dp = new int[target + 1];
			
			for( int weightCount = 0; weightCount < len; weightCount++ ){
				
				int weight = weights[weightCount];
				
				for( int total = target; total >= weight; total-- ){
					dp[total] = Math.max( dp[total], prices[weightCount] + dp[total - weight] );
				}


			}
			
		return dp[target];
	}
}
