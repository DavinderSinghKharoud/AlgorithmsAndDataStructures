/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 */

import java.util.*;
public class IntervalListIntersections {

	//O(M + N) time complexity and O(M + N) space complexity
	public static int[][] intervalIntersection(int[][] A, int[][] B) {
        
        int pointer1 = 0;
        int pointer2 = 0;
        List<int[]> list = new ArrayList<>();
        
        while( pointer1 < A.length && pointer2 < B.length ){
			
			checkIntersection( A[pointer1], B[pointer2], list );
			
			if( A[pointer1][1] < B[pointer2][1] ){
				pointer1++;
			}else { 
				pointer2++; 
				}
			
		}

		return list.toArray( new int[list.size()][2]);
    }
    
    public static void checkIntersection( int[] A, int[] B, List<int[]> list ){
		
			int low = Math.max( A[0], B[0] );
			int high = Math.min( A[1], B[1] );

			if( low <= high ) {
				list.add(new int[]{low, high});
			}
	}
	
	public static void main (String[] args) {
		int[][] res =  intervalIntersection(new int[][]{
			{0,2}, {5,10}, {13, 23}, { 24, 25 }
		}, new int[][]{
			{1, 5}, {8, 12}, {15, 24}, {25, 26}
		});

		for( int[] val: res ){
			System.out.println(val[0] +"," + val[1]);
		}
	}


	
	
}

