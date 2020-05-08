
/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.**/

import java.util.*;

public class CountofSmallerNumbersAfterSelf {
	
	//O( n square ) time complexity
	public static  List<Integer> countSmaller(int[] nums) {
        
        int count = 0, len = nums.length;
        List<Integer> lst = new ArrayList<>();
        
        for( int i = 0; i<len; i++ ){
			count = 0;
			int num = nums[i];
			
			for( int j = i + 1; j<len; j++ ){
				if( nums[j] < num ){
					count++;
				}		
			}
			
			lst.add( count );
			
		}
        
        return lst;
    }
    
	public static void main (String[] args) {
		
		System.out.println( countSmaller( new int[] {
			5,2,6,1
		}));
	}
}

