//package algorthims.LeetCode;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 */
public class MaximumProductSubarray {
	
	public static int maxProduct(int[] nums) {
	    
	    if( nums.length == 0 ) return -1;
		int curr_max = nums[0];
		int max = nums[0];
		int curr_min = nums[0];
		
		for(int i = 1; i<nums.length; i++ ){
		    
		    int temp = curr_max;
		    curr_max = Math.max( Math.max( curr_max * nums[i], curr_min * nums[i]), nums[i] );
		    
		    curr_min = Math.min( Math.min( temp * nums[i], curr_min * nums[i]), nums[i] );
		    
		    if( curr_max > max ) max = curr_max;
		    
		    
		}
		
		return max;
	}
	
	public static void main (String[] args) {
		System.out.println( maxProduct( new int[]{
		   -2,0,-14
		}));
	}
}

