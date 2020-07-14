/*
Given an array of integers A, find and return the peak element in it.
An array element is peak if it is NOT smaller than its neighbors. 
For corner elements, we need to consider only one neighbor. 
For example, for input array {5, 10, 20, 15}, 20 is the only peak element.

Following corner cases give better idea about the problem.

1) If input array is sorted in strictly increasing order, the last element is always a peak element. 
For example, 5 is peak element in {1, 2, 3, 4, 5}.
2) If input array is sorted in strictly decreasing order, the first element is always a peak element. 
10 is the peak element in {10, 9, 8, 7, 6}.
Note: It is guranteed that the answer is unique.
* 
* The only argument given is the integer array A.
Output Format

Return the peak element.
Constraints

1 <= length of the array <= 100000
1 <= A[i] <= 10^9 
For Example

Input 1:
    A = [1, 2, 3, 4, 5]
Output 1:
    5

Input 2:
    A = [5, 17, 100, 11]
Output 2:
    100
 */

import java.util.*;

public class FindAPeakElement {
	
	public static int solve(List<Integer> lst) {
		int len = lst.size();
		
		if( len == 1 ) return lst.get(0);
		
		int start = 0, end = len - 1;
		
		while( start <= end ){
			int mid = ( end - start )/2 + start;
			
			int num = lst.get(mid);
			
			if( (mid - 1 < 0 || lst.get(mid - 1) <= num ) && ( mid + 1 >= len || lst.get(mid + 1) <= num) ){
				return num;
				
			}else if( (mid - 1 < 0 || lst.get(mid - 1) <= num ) && ( mid + 1 >= len || lst.get(mid + 1) >= num) ){
				
				start = mid + 1;
				
			}else{
				end = mid - 1;
			}
		}
		
		return -1;
    }
	public static void main (String[] args) {

		System.out.println( solve( Arrays.asList(5, 17, 100, 11)));
		
	}
}

