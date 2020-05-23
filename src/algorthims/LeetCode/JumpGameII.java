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
	public static int jump(int[] nums) {

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
		System.out.println( jump( new int[]{
			2,1,1,2,3,5
		}));
	}
}

