package Algorithms.InterviewBit;

/**
 * Find the longest increasing subsequence of a given array of integers, A.
 *
 * In other words, find a subsequence of array in which the subsequence’s elements are in strictly increasing order, and in which the subsequence is as long as possible.
 * This subsequence is not necessarily contiguous, or unique.
 * In this case, we only care about the length of the longest increasing subsequence.
 *
 *
 * Input Format:
 *
 * The first and the only argument is an integer array A.
 * Output Format:
 *
 * Return an integer representing the length of the longest increasing subsequence.
 * Constraints:
 *
 * 1 <= length(A) <= 2500
 * 1 <= A[i] <= 2000
 * Example :
 *
 * Input 1:
 *     A = [1, 2, 1, 5]
 *
 * Output 1:
 *     3
 *
 * Explanation 1:
 *     The sequence : [1, 2, 5]
 *
 * Input 2:
 *     A = [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]
 *
 * Output 2:
 *     6
 *
 * Explanation 2:
 *     The sequence : [0, 2, 6, 9, 13, 15] or [0, 4, 6, 9, 11, 15] or [0, 4, 6, 9, 13, 15]
 */
public class LongestIncreasingSubsequence {

    public static int lis(final int[] lst) {

		int len = lst.length;
		int[] dp = new int[len];
		int res = -1;
		for(int index1 = 0; index1 < len; index1++ ){
			int curr = lst[index1];
			int max = 0;
			for( int index2 = 0; index2 < index1; index2++ ){
				if( lst[index2] < curr ){
					max = Math.max(max, dp[index2]);
				}
			}
			dp[index1] = max + 1;
			res = Math.max(res, dp[index1]);
		}
		
		return res;
    }
    public static void main(String[] args) {

		System.out.println(lis(new int[]{14, 24, 18, 46, 55, 53, 82, 18, 101, 20, 78, 35, 68, 9, 16, 93, 101, 85, 81, 28, 78}));

    }
}
