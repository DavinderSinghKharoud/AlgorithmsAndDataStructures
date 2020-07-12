/*
 * SearchforaRange.java
 Given a sorted array of integers A(0 based index) of size N, find the starting and ending position of a given integar B in array A.

Your algorithm’s runtime complexity must be in the order of O(log n).

Return an array of size 2, such that first element = starting position of B in A and second element = ending position of B in A, if B is not found in A return [-1, -1].



Input Format

The first argument given is the integer array A.
The second argument given is the integer B.
Output Format

 Return an array of size 2, such that first element = starting position of B in A and second element = ending position of B in A, if B is not found in A return [-1, -1].
Constraints

1 <= N <= 10^6
1 <= A[i], B <= 10^9
For Example

Input 1:
    A = [5, 7, 7, 8, 8, 10]
    B = 8
Output 1:
    [3, 4]
Explanation 1:
    First occurence of 8 in A is at index 3
    Second occurence of 8 in A is at index 4
    ans = [3, 4]

Input 2:
    A = [5, 17, 100, 111]
    B = 3
Output 2:
    [-1, -1]
 */

import java.util.*;

public class SearchforaRange {
	
	public static ArrayList<Integer> searchRange(final List<Integer> A, int key) {
		
		int len = A.size();
		ArrayList<Integer> res = new ArrayList<>();
		
		//find the startig position
		int start = 0;
		int end = len - 1;
		
		while( start <= end ){
			int mid = ( end - start )/2 + start;
			int num = A.get(mid);
			
			if( num == key ){
				//if it is unique
				if( (mid - 1 < 0 || A.get(mid - 1) != key ) && ( mid + 1 >= len || A.get(mid + 1) != key ) ){
					res.add(mid);res.add(mid);
					return res;
				}else if( (mid - 1 < 0) || A.get(mid - 1) != key ){
					res.add(mid);
					break;
				}else{
					end = mid - 1;
				} 
				
			}else if( key < num ){
					end = mid - 1;
			}else{
				start = mid + 1;
			}
		}
		
		if( res.size() != 1 ){
			res.add(-1);res.add(-1);
			return res;
		}
		
		//find the ending position
		start = 0;
		end = len - 1;
		
		while( start <= end ){
			int mid = ( end - start )/2 + start;
			int num = A.get(mid);
			
			if( num == key ){
				if( (mid + 1 >= len) || A.get(mid + 1) != key ){
					res.add(mid);
					break;
				}else{
					start = mid + 1;
				} 
				
			}else if( key < num ){
					end = mid - 1;
			}else{
				start = mid + 1;
			}
		}
		
		return res;
		
	}
		
	public static void main (String[] args) {

		System.out.println( searchRange(Arrays.asList(5, 17, 100, 111), 3));
	}
}

