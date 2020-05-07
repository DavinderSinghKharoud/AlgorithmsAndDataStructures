package algorthims.LeetCode;

import java.util.Arrays;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class SlidingWindowMaximum {

	// O(n square ) time complexity
	public static int[] maxSlidingWindow(int[] nums, int k) {
        
        int len = nums.length;
        int []res = new int[ len - k + 1 ];
        
        if( len < k ){
			return res;
		}
		
        int start = 0, end = k;
        int i = 0;
        
		while( end < len + 1 ){
			int num = findMax( start, end, nums );
			res[i++] = num;
			start++;
			end++;
		}
		
		return res;
    }
    
    public static int findMax( int start, int end, int[] nums ){
		int max = nums[start];
		
		for(int index = start + 1; index<end; index++ ){
			max = Math.max( max, nums[index] );
		}
		
		return max;
	}
	
	public static void main (String[] args) {
		
		System.out.println(Arrays.toString(maxSlidingWindow(new int[]{
				1, 3, -1, -3, 5, 3, 6, 7
		}, 3)));
	}
}

