/*
Given an index k, return the kth row of the Pascal’s triangle.

Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1] from previous row R - 1.

Example:

Input : k = 3

Return : [1,3,3,1]
 NOTE : k is 0 based. k = 0, corresponds to the row [1]. 
Note:Could you optimize your algorithm to use only O(k) extra space?
 */

import java.util.*;

public class KPascalTriangle {
	
	public static ArrayList<Integer> getRow(int a) {
		int[] previous = new int[2];
		previous[0] = 1;
		previous[1] = 1;
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		if( a == 0 ){
			res.add(1);
			return res;
		}else if( a == 1 ){
			res.add(1);
			res.add(1);
			return res;
		}
		int[] dp = new int[0];
		for(int num = 2; num <= a; num++ ){
			dp = new int[num + 1];
			dp[0] = 1;
			for(int count = 1; count < num; count++ ){
				dp[count] = previous[count - 1] + previous[count];
 			}
 			dp[num] = 1;
			
			previous = dp;
		}
		
		for(int num: dp ){
			res.add(num);
		}
		
		return res;
    }
	
	public static void main (String[] args) {

		System.out.println( getRow(0) );
	}
}

