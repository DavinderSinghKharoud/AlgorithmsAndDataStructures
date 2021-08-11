
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
    
    //O( nlog(n) )
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
	System.out.println( increasingTriplet2( new int[] {
	   0,4,2,1,0,-1,-3
	} ));
    }
    
    //O(n)
    public static boolean increasingTriplet2(int[] nums) {
	if( nums.length < 3 ) return false;
	
	int first = nums[0];
	int second = Integer.MAX_VALUE;
	
	for( int num : nums ){
	    
	    if( num < first ){ 
		first = num;
		continue;
	    } 
	    else{
		if( num != first ){ 
		second = Math.min( second, num );
		}
	    }
	    
	    if( num > second ) return true;
	}
	
	return false;
	
	
    }
}
