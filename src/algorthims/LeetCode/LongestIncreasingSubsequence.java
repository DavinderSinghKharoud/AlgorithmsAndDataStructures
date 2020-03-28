//package algorthims.LeetCode;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. **/

public class LongestIncreasingSubsequence{

    public static int lengthOfLIS(int[] nums) {
		if( nums.length == 1){
			return 1;
		}

		if( nums.length == 0 ){
			return 0;
		}
        int[] dp = new int[ nums.length ];
	    int len = 0;
	
	
	for( int i = 1; i<nums.length; i++ ){
	    
	    for( int j = 0; j< i; j++ ){
		
		if( nums[i] > nums[j] && dp[j] + 1 > dp[i]  ) {
		    dp[i] = dp[j] + 1; 
		    len = Math.max( len, dp[i]);
		}
		
	    }
	}
	
	return len + 1;
        
    }
    public static void main(String[] args) {
	    
	    System.out.println( lengthOfLIS( new int[] {
		10,22,9,33,21,50,41,60,80
	    }) );
    }
	 
}
