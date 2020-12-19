package Algorithms.LeetCode;//package algorthims.LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * Medium
 *
 * 3990
 *
 * 126
 *
 * Add to List
 *
 * Share
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 *
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 *
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class SubarraySumEqualsK {

    //Brute Force
    public static int subarraySum(int[] nums, int k) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if( sum == k ) count++;
            for (int j = i + 1; j < nums.length; j++) {
                if( sum + nums[j] == k ){
                    count++;
                }
                sum += nums[j];

            }
        }

        return count;
    }

    public static int subarraySum2(int[] nums, int k) {
		
		int result = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap();
		map.put( 0, 1 );
		
		for( int i = 0; i<nums.length; i++ ){
			
			sum += nums[i];
			
			if( map.containsKey( sum - k ) ){
				result += map.get( sum - k);
			}
			
			map.put( sum, map.getOrDefault( sum, 0 ) + 1 );
			
		}
			
        return result;
    }
    public static void main(String[] args) {
        System.out.println( subarraySum( new int[]{
                1,1,1
        }, 2));
    }
}
