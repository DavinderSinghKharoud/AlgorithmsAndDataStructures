/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGame {

    public static void main(String[] args) {
        System.out.println( canJump( new int[]{
               2,0
        }) );
    }

    //Greedy More Efficient
    private static boolean checkReach(int[] nums) {
		
	    int len = nums.length;
	    int lastPos = len - 1;
		for( int i = len - 1; i>=0; i-- ){
			  if( i + nums[i] >= lastPos){
			  	lastPos = i;
			  }
		}

			return lastPos == 0;
      }

      //Dynamic Programming
      public static boolean canJump(int[] nums) {
		     int dp[] = new int[nums.length];

		     dp[nums.length - 1] = 1;

		  for (int i = nums.length - 1; i >= 0; i--) {
			      int reach = Math.min( i + nums[i], nums.length - 1);

			  for (int j = i + 1; j <= reach ; j++) {
				   if( dp[j] == 1 ){
				   	dp[i] = 1;
				   	break;
				   }
			  }
		  }

		  return dp[0] == 1;
	  }
}
