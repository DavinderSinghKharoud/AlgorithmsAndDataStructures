package Algorithms.InterviewBit;

import java.util.*;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers.
 *
 * Assume that there will only be one solution
 *
 * Example:
 * given array S = {-1 2 1 -4},
 * and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
 */
public class ThreeSum {

    public static int threeSumClosest(List<Integer> lst, int target) {
		
		int len = lst.size();
		
		Collections.sort(lst);
		
		int min = Integer.MAX_VALUE;
		int res = 100000;
		
		for( int index = 0; index < len - 2; index++ ){
			
			int curr = lst.get(index);
			
			int start = index + 1;
			int end = len - 1;
			
			int remaining = target - curr;
			
			while( start < end ){
				int val = lst.get(start) + lst.get(end);
				
				if( val + curr == target ) return target;

				if( min > (Math.abs( (val + curr) - target )) ){
					min = Math.abs((val + curr) - target);
					res = val + curr;
				}

				if( val < remaining ){
					start++;
				}else{
					end--;
				}
				
			}
			
			
		}
		
		return res;
	

    }
    public static void main(String[] args) {

		System.out.println( threeSumClosest(Arrays.asList(-1, 2, 1, -4), 1));
    }
}
