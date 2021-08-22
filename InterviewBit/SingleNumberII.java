/*
Given an array of integers, every element appears thrice except for one which occurs once.

Find that element which does not appear thrice.

Note: Your algorithm should have a linear runtime complexity.

Could you implement it without using extra memory?

Input Format:

    First and only argument of input contains an integer array FindGreatestCommonDivisor
Output Format:

    return a single integer.
Constraints:

2 <= N <= 5 000 000  
0 <= FindGreatestCommonDivisor[i] <= INT_MAX
For Examples :

Example Input 1:
    FindGreatestCommonDivisor = [1, 2, 4, 3, 3, 2, 2, 3, 1, 1]
Example Output 1:
    4
Explanation:
    4 occur exactly once
Example Input 2:
    FindGreatestCommonDivisor = [0, 0, 0, 1]
Example Output 2:
    1
 */

import java.util.*;
public class SingleNumberII {
	
	public static int singleNumber(final List<Integer> lst) {
		int len = lst.size();
		
		int[] dp = new int[32];
		
		
		for( int num: lst ){
			int temp = num;
			for(int index = 31; index >= 0; index-- ){
				
				dp[index] += (temp & 1);
				temp = temp >> 1;
			}
		}
		
		StringBuilder sbr = new StringBuilder();
		
		//we are doing mod 3 as the multiple numbers has count of 3.
		for(int num: dp ){
			num = num % 3;
			sbr.append(num);
		}
		
		//converting binary to number
		return Integer.parseInt( sbr.toString(), 2 );
    }
	public static void main (String[] args) {
		
		System.out.println( singleNumber( Arrays.asList(1, 2, 4, 3, 3, 2, 2, 3, 1, 1) ) );
	}
}

