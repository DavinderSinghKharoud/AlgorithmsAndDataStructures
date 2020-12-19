/*
There are two sorted arrays A and B of size m and n respectively.

Find the median of the two sorted arrays ( The median of the array formed by merging both the arrays ).

The overall run time complexity should be O(log (m+n)).

Sample Input

A : [1 4 5]
B : [2 3]

Sample Output

3
 NOTE: IF the number of elements in the merged array is even, then the median is the average of n / 2 th and n/2 + 1th element. 
For example, if the array is [1 2 3 4], the median is (2 + 3) / 2.0 = 2.5  
 */

import java.util.*;

public class MedianOfArray {
	
	public static double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
		
		int len1 = a.size();
		int len2 = b.size();
		
		boolean isEven = ( len1 + len2 ) % 2 == 0;
		
		//bigger list
		List<Integer> Y = ( len1 > len2 ) ? a: b;
		//smaller list
		List<Integer> X = ( len1 > len2 ) ? b: a;
		
		int start = 0;
		int end = X.size();
		
		while( start <= end ){
			//Partition for X
			int mid = (end - start)/2 + start;
			//To make two halves, make the partition such that the index that partitioning array A[] + the index that partitioning array B[] are equal to the total number of elements plus one divided by 2, i.e. (n + m + 1) / 2 (+1 is, if the total number of elements is odd).
			int partitionY = ( len1 + len2 + 1 )/2 - mid;
			
			int maxLeftX = ( mid == 0 ) ? Integer.MIN_VALUE: X.get(mid - 1);
			int maxLeftY = ( partitionY == 0 ) ? Integer.MIN_VALUE: Y.get(partitionY - 1);
			
			int minRightX = ( mid == X.size() ) ? Integer.MAX_VALUE: X.get(mid);
			int minRightY = ( partitionY == Y.size() ) ? Integer.MAX_VALUE: Y.get(partitionY);
			
			
			if( maxLeftX <= minRightY && maxLeftY <= minRightX ){
				if( isEven ){
					return (double)(Math.max( maxLeftX, maxLeftY) + Math.min( minRightX, minRightY) )/ 2 ;
				}else{
					return Math.max( maxLeftX, maxLeftY );
				}
			}
			else if( maxLeftX > minRightY ){
				end = mid - 1;
			}else{
				start = mid + 1;
			}
			
			
		}
		
		throw new IllegalArgumentException();
    }
	public static void main (String[] args) {

		System.out.println( findMedianSortedArrays(Arrays.asList(1, 4, 5), Arrays.asList(2, 3)));
	}
}

