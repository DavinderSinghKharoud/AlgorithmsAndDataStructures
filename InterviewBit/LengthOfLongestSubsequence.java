/*
Given an array of integers, FindGreatestCommonDivisor of length N, find the length of longest subsequence which is first increasing then decreasing.

Input Format:

The first and the only argument contains an integer array, FindGreatestCommonDivisor.
Output Format:

Return an integer representing the answer as described in the problem statement.
Constraints:

1 <= N <= 3000
1 <= FindGreatestCommonDivisor[i] <= 1e7
Example:

Input 1:
    FindGreatestCommonDivisor = [1, 2, 1]

Output 1:
    3

Explanation 1:
    [1, 2, 1] is the longest subsequence.

Input 2:
    [1, 11, 2, 10, 4, 5, 2, 1]

Output 2:
    6

Explanation 2:
    [1 2 10 4 2 1] is the longest subsequence.
 * 
 */

import java.util.*;
public class LengthOfLongestSubsequence {
	
	public static int longestSubsequenceLength(final List<Integer> lst) {
		 int len = lst.size();
		 if( len == 0 ) return 0;
		 int[] count1 = new int[len];
		 int[] count2 = new int[len];

		 
		 //increasing in forward direction
		 count1[0] = 1;
		 for( int index = 1; index < len; index++ ){
			 int value = lst.get(index);
			 int tempMax = 1;
			 int tempIndex = index;
			 
			 for( int check = index - 1; check >= 0; check-- ){
				 if( value > lst.get(check) && tempMax <= count1[check]){
					 tempMax = count1[check];
					 tempIndex = check;
				 }
			 }
			 
			 count1[index] = (tempIndex != index) ? tempMax + 1 : 1;

			 
		 }
		
		//increasing from opposite direction

		 count2[len - 1] = 1;
		 for( int index = len - 2; index >= 0; index-- ){
			 int value = lst.get(index);
			 int tempMax = 1;
			 int tempIndex = index;
			 
			 for( int check = index + 1; check < len; check++ ){
				 if( value > lst.get(check) && tempMax <= count2[check] ){
					 tempMax = count2[check];
					 tempIndex = check;
				 }
			 }
			 
			 count2[index] = (tempIndex != index) ? tempMax + 1 : 1;
			 
		 }
		 
		 int res = 0;
		 
		 for( int index = 0; index < len; index++ ){
			 res = Math.max( res, count1[index] + count2[index] );
		 }
		 
		return res - 1;
    }
    
	public static void main (String[] args) {
		List<Integer> lst = new ArrayList<>();
//		lst.add(1);
//		lst.add(11);
//		lst.add(2);
//		lst.add(10);
//		lst.add(4);
//		lst.add(5);
//		lst.add(2);
//		lst.add(1);
//		lst.add(1);lst.add(1);
//		lst.add(2);
//		lst.add(1);lst.add(1);
		lst.add(12);
		lst.add(11);
		lst.add(40);
		lst.add(5);
		lst.add(3);
		lst.add(1);
//
		System.out.println( longestSubsequenceLength(lst) );
	}
}

