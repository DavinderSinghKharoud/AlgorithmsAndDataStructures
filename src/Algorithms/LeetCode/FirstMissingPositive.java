package Algorithms.LeetCode;/*
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
		
		System.out.println( firstMissingPositive1( new int[]{
				3,4,-1,1
		}));
	}
	
	public static int firstMissingPositive1(int[] nums) {
		int len = nums.length;
		
		for( int i = 0; i< len; i++ ){
			int num = nums[i];

			if( num > 0 && nums[num - 1] != num && num < len ){
				swap( i , num - 1, nums );
				i--;
			}
		}
		
		for( int i = 0; i< len; i++ ){
			if( nums[i] != i + 1 ){
				return i + 1;
			}
		}
		
		return nums.length + 1;
	}
	
	private static void swap( int index1, int index2, int[] nums ){
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}
}














