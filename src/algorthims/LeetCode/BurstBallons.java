package algorthims.LeetCode;/*
 Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * 
 */


public class BurstBallons {

	//O(n cube) time complexity and O(n square) space complexity
	public static int maxCoins(int[] nums) {

		int dp[][] = new int[nums.length][nums.length];

		for( int len = 1; len<=nums.length; len++ ){

			for (int i = 0; i <= nums.length - len; i++) {
				
				int j = i + len - 1;
				
				for( int k = i; k <= j; k++ ){
					
					//if there is a element on left/right of k then we will take that value
					int left = 1;
					int right = 1;
					
					if( i != 0 ){
						left = nums[ i - 1 ];
					}
					if( j != nums.length - 1){
						right = nums[ j + 1 ];
					}
					
					int before = 0;
					int after = 0;
					
					if( i != k ){
						before = dp[i][k - 1];
					}
					if( j != k ){
						after = dp[k + 1][j];
					}
					
					dp[i][j] = Math.max( left * nums[k] * right + before + after , dp[i][j] );
				}
			}
		}
		
		return dp[0][nums.length - 1];
    }

	public static void main (String[] args) {
		
		System.out.println( maxCoins( new int[]{
			3,1,5,8
		}));
	}
}




















