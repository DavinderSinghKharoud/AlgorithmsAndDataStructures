package algorithms.InterviewBit;

import java.util.*;

/**
 * Find the contiguous subarray within an array, A of length N which has the largest sum.
 *
 * Input Format:
 *
 * The first and the only argument contains an integer array, A.
 * Output Format:
 *
 * Return an integer representing the maximum possible sum of the contiguous subarray.
 * Constraints:
 *
 * 1 <= N <= 1e6
 * -1000 <= A[i] <= 1000
 * For example:
 *
 * Input 1:
 *     A = [1, 2, 3, 4, -10]
 *
 * Output 1:
 *     10
 *
 * Explanation 1:
 *     The subarray [1, 2, 3, 4] has the maximum possible sum of 10.
 *
 * Input 2:
 *     A = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 *
 * Output 2:
 *     6
 *
 * Explanation 2:
 *     The subarray [4,-1,2,1] has the maximum possible sum of 6.
 */
public class MaxSumContiguousSubarray {

    public static int maxSubArray(final List<Integer> lst) {
		int len = lst.size();
		
		if ( len == 0 ) return 0;	
		int[] dp = new int[len];
		
		dp[0] = lst.get(0);

		int res = Integer.MIN_VALUE;
		for( int  index = 1; index < len; index++ ){
			int value = lst.get(index);
			
			dp[index] = Math.max( value, dp[index - 1] + value );
			res = Math.max(res, dp[index]);
		}
		
		return res;
    }
    public static void main(String[] args) {

		System.out.println( maxSubArray(Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4)));
    }
}
