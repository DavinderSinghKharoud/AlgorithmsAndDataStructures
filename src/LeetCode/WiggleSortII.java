package LeetCode;

import java.util.*;

public class WiggleSortII {
    
	public static void wiggleSort(int[] nums) {

		int[] vals = Arrays.copyOf(nums, nums.length);
		Arrays.sort( vals );

		int index = nums.length - 1;

		//for odd position
		for (int i = 1; i < nums.length; i+=2) {
			nums[i] = vals[index--];
		}

		//for even
		for (int i = 0; i < nums.length; i+=2 ) {
			nums[i] = vals[index--];
		}

		
	}

	public static void main (String[] args) {
		    
		    int[] nums = new int[]{
					1, 3, 2, 2, 3, 1
		    };
		    
		    wiggleSort( nums );
		    
		    for( int num: nums ){
			System.out.println( num );
		    }
	}
}

