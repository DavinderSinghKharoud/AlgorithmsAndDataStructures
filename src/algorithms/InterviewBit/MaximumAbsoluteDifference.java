package algorithms.InterviewBit;

/**
 * You are given an array of N integers, A1, A2 ,…, AN. Return maximum value of f(i, j) for all 1 ≤ i, j ≤ N.
 * f(i, j) is defined as |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.
 *
 * For example,
 *
 * A=[1, 3, -1]
 *
 * f(1, 1) = f(2, 2) = f(3, 3) = 0
 * f(1, 2) = f(2, 1) = |1 - 3| + |1 - 2| = 3
 * f(1, 3) = f(3, 1) = |1 - (-1)| + |1 - 3| = 4
 * f(2, 3) = f(3, 2) = |3 - (-1)| + |2 - 3| = 5
 *
 * So, we return 5.
 */

import java.util.*;
public class MaximumAbsoluteDifference {

    public static int maxArr(List<Integer> lst) {
		
		int max1 = Integer.MIN_VALUE; //A(x) - x
		int min1 = Integer.MAX_VALUE; //A(y) + y
		
		int max2 = Integer.MIN_VALUE; // A(x) - x
		int min2 = Integer.MAX_VALUE; // A(y) - y
		
		
		for( int index = 0; index < lst.size(); index++ ){
			
			max1 = Math.max( max1, lst.get(index) + index);
			min1 = Math.min( min1, lst.get(index) + index);
			
			max2 = Math.max( max2, lst.get(index) - index);
			min2 = Math.min( min2, lst.get(index) - index);
		}
		
		return Math.max( Math.abs(max1 - min1), Math.abs(max2 - min2) );
        
    }
    public static void main(String[] args) {

		System.out.println( maxArr(Arrays.asList(1, 3, -1)));
    }
}
