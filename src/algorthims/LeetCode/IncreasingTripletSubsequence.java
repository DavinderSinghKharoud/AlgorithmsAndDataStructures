/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
* */
import java.util.*;

public class IncreasingTripletSubsequence{
    
    public static boolean increasingTriplet(int[] nums) {
	int[] dp = new int[nums.length];
	int len = 0;
	
	
	for( int num: nums ){
	    int index = Arrays.binarySearch( dp, 0, len, num );
	    
	    if( index < 0 ){
		index = -( index + 1 );
	    }
	    
	    dp[index] = num;
	    if( index == len ){
		len ++;
	    }
	}  
		
	if( len >= 3 ) return true;
	else return false;
        
    }
    
    public static void main(String[] args) {
	System.out.println( increasingTriplet( new int[] {
	   0,4,2,1,0,-1,-3
	} ));
    }
}
