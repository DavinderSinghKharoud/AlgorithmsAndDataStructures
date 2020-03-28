package algorthims.LeetCode;
/*
 * A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
* */

public class PeakElement {
	
	//O(n)
	    private static int findPeakElement1(int[] nums) {
				  
		for (int i = 0; i < nums.length - 1; i++) {
		  if (nums[i] > nums[i + 1])
                return i;
		  }
        return nums.length - 1;
	    }
	    
	    //O( log(n) );
	    private static int findPeakElement2(int[] nums) {
			
			return search( nums, 0, nums.length - 1 );
	    }
	    
	    private static int search( int[] nums, int start, int end){
			
			if( start == end ){
				return start;
			}
			
			int mid = ( start + end)/2;
			int num = nums[ mid ];
			
			if(num < nums[ mid + 1 ] ){
				return search( nums, mid + 1, end);
			}
				
			return search( nums, start, mid );
			
			
		}
		
	    public static void main(String[] args) {
			 System.out.println( findPeakElement2( new int[] {
			       1,2,3,2}) );
		}
      
      }
