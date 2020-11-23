package algorithms.LeetCode;
/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
**/
import java.util.*;

public class LongestConsecutiveSequence {

	//time complexity O(n) and O( n square ) space complexity
	public static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for( int num: nums ){
			map.put( num, map.getOrDefault(num, 0) + 1 );
		}
		
		for(int num: nums ){
			if( map.get( num ) != null && map.get(num) > 0 ){
				int temp_len = 1;
				int left = num - 1;
				int right = num + 1;
				
				while( map.get(left) != null && map.get(left) > 0 ){
					map.put( left, map.get(left) - 1);
					temp_len++;
					left--;
				}
				
				while( map.get(right) != null && map.get(right) > 0 ){
					map.put( right, map.get(right) - 1);
					temp_len++;
					right++;
				}
				
				res = Math.max( res, temp_len );
			}
		}
		
		return res;
    }
    
	public static void main (String[] args) {
		
		System.out.println( longestConsecutive2( new int[]{
			100, 4, 200, 1, 3, 2
		}));

		//we can also use the sorting to solve the problem in O( n log(n) ) time
	}

	//O(n) time and space complexity
	public static int longestConsecutive2(int[] nums) {
		Set<Integer> set = new HashSet<>();

		int res = 0;
		for( int num: nums ){
			set.add( num );
		}

		for(int num: nums ){
			if( !set.contains( num - 1 ) ){
				int temp_len = 1;
				int right = num + 1;

				while( set.contains(right) ){
					temp_len++;
					right++;
				}

				res = Math.max( res, temp_len );
			}
		}

		return res;
	}

}

