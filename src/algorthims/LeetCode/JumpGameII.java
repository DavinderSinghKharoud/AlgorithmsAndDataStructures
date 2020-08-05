/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */


public class  JumpGameII {

	//It is working but, time limit exceeded
	//O(n) space complexity and approximately O(n square) time complexity
	public static int jump1(int[] nums) {

		if( nums.length == 0 ) return 0;
        int[] dp = new int[nums.length];

        for( int i = nums.length - 2; i >= 0; i-- ){
			
			int reach = Math.min( nums.length - 1, nums[i] + i );
			int min = Integer.MAX_VALUE;
			for( int j = i + 1; j <= reach; j++ ){
				
				if( j == nums.length - 1 ){
					min = 0;
					continue;
				}
				
				if( dp[j] > 0 ){
					min = Math.min( dp[j], min );
				}
			}
			dp[i] = min + 1;
		}
		
		return dp[0];
    }
    
	public static void main (String[] args) {
		System.out.println( jump2( new int[]{
				1,0,1
		}));
	}
	
	
	//O(n) time complexity

	/**
	 * With the current number of steps, try to maintain the maximum index which is reachable. When you exceed the index, you have to increase the number of steps, and at that instance you can increase the maximum index reachable accordingly.
	 * @param nums
	 * @return
	 */
	public static int jump2(int[] nums) {
		if( nums.length == 0 ) return 0;
		int position = nums[0];
		int reach = nums[0];
		
		int jumps = 1;
		
		for( int i = 1; i<nums.length; i++ ){
			if( reach < i ) return -1;
			if( position < i ){
				jumps++;
				position = reach;
			}
			
			reach = Math.max( reach, nums[i]  + i );
		}
		
		return jumps;
	}
}

