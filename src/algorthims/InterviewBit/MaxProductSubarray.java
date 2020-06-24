package algorthims.InterviewBit;/*
 Find the contiguous subarray within an array (containing at least one number) which has the largest product.
Return an integer corresponding to the maximum product possible.

Example :

Input : [2, 3, -2, 4]
Return : 6 

Possible with [2, 3]
 */

import java.util.*;
public class MaxProductSubarray {
	
	//O(N square) time complexity
	public static int maxProduct(final List<Integer> lst) {
		
		int len = lst.size();
		int max = Integer.MIN_VALUE;
		
		for(int left = 0; left < len; left++ ){
			max = Math.max( max, lst.get(left) );
			int total = lst.get(left);
			for(int right = left + 1; right < len; right++ ){
				total *= lst.get(right);
				max = Math.max( max, total );
			}
		}
		
		return max;
    }
    
	public static void main (String[] args) {
		List<Integer> lst = new ArrayList<>();
		lst.add(-10);lst.add(10);lst.add(-10);lst.add(10);
		
		System.out.println( maxProduct3(lst) );
	}

	// O(n) time and space complexity
	public static int maxProduct2(final List<Integer> lst) {
		int len = lst.size();
		int max = lst.get(0);
		
		int[][] dp = new int[len][2];
		dp[0][0] = lst.get(0);
		dp[0][1] = lst.get(0);
		
		for(int index = 1; index < len; index++ ){
			dp[index][0] = Math.max( lst.get(index), Math.max( lst.get(index) * dp[index - 1][0], lst.get(index) * dp[index - 1][1] ) );
			dp[index][1] = Math.min( lst.get(index), Math.min( lst.get(index) * dp[index - 1][0], lst.get(index) * dp[index - 1][1] ) );
			max = Math.max( dp[index][0], Math.max(max, dp[index][1] ) );
		}
		
		return max;	
	}

	// O(n) time space complexity
	public static int maxProduct3(final List<Integer> lst) {
		int len = lst.size();

		int max = lst.get(0);
		int min_product = lst.get(0);
		int max_product = lst.get(0);

		for(int index = 1; index < len; index++ ){
			int temp_max = Math.max( lst.get(index), Math.max( lst.get(index) * min_product, lst.get(index) * max_product ) );
			min_product = Math.min( lst.get(index), Math.min( lst.get(index) * min_product, lst.get(index) * max_product ) );
			max_product = temp_max;
			max = Math.max( min_product, Math.max(max, max_product ) );

		}

		return max;
	}
}













