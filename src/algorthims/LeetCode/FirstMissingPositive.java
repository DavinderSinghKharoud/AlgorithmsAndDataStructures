package algorthims.LeetCode;/*
 Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
 * 
 */

import java.util.*;

public class FirstMissingPositive {

	//O( max num ) time complexity and O(n) space complexity
	public static int firstMissingPositive(int[] nums) {
		
	
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		Set<Integer> map = new HashSet<>();
		
		for( int num: nums ){
			map.add( num );
			min = Math.min( num, min );
			max = Math.max( num, max );
		}
		
		if( max < 1 ){
			return 1;
		}
		for( int i = 1; i<= max; i++ ){
			if( !map.contains(i) ){
				return i;
			}
		}
        
        return 0;
    }
	public static void main (String[] args) {
		
		System.out.println( firstMissingPositive( new int[]{
			7,8,9,11,12
		}));
	}
}














