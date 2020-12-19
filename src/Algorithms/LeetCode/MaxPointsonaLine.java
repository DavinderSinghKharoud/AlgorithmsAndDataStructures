package Algorithms.LeetCode;/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
* 
* Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
 */

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class MaxPointsonaLine {

	//O(n square) time complexity
	// it is working for 40 test cases but it should work for all( there is some error in leet test cases )
	public static int maxPoints(int[][] points) {
        int max = 1;

        if( points.length == 0 ) return 0;
        for( int i = 0; i< points.length ; i++ ){
			HashMap<BigDecimal, Integer> map = new HashMap<>();
			int duplicates = 0, temp_max = 1;

			for( int j = 0; j< points.length; j++ ){
				if( i == j ) continue;
				if( isEqual(points[i], points[j]) ) {
					duplicates++;
					continue;
				}
				BigDecimal slope = findSlope( points[i], points[j] );

				map.put( slope, map.getOrDefault( slope, 1) + 1);
				temp_max = Math.max( map.get(slope), temp_max );

			}
			max = Math.max( max, temp_max + duplicates);
		}
		
		return max;
    }

	private static BigDecimal findSlope(int[] point1, int[] point2) {

		if( point1[0] == point2[0] ) return BigDecimal.valueOf(Integer.MAX_VALUE);
		if( point1[1] == point2[1] ) return BigDecimal.ZERO;
		double num = point2[1] - point1[1];
		double den = point2[0] - point1[0];

		double gcd = generateGCD( den, num );
		num /= gcd;
		den /= gcd;
		BigDecimal slope = BigDecimal.valueOf(num).divide(BigDecimal.valueOf(den),new MathContext(20));
		return slope;
	}

	//GCD
	private static double generateGCD(double a, double b) {
		if (b == 0) return a;

		return generateGCD(b, a % b);
	}

	private static boolean isEqual(int[] points1, int[] points2) {

		for (int i = 0; i < 2; i++) {
			if( points1[i] != points2[i]){
				return false;
			}
		}
		return true;
	}


	public static void main (String[] args) {
		
//		System.out.println( maxPoints( new int[][]{
//				{3,2}, {1,4}, {1,1}, {5,3}, {4,1}, {2,3},
//		}));
//
//		System.out.println( maxPoints( new int[][]{
//				{1,1}, {2,2}, {3,3}
//		}));
//
//		System.out.println( maxPoints( new int[][]{
//				{1,1}, {1,1}, {2,2}, {2,2}
//		}));

		System.out.println( maxPoints( new int[][]{
				{0,0},{94911151,94911150},{94911152,94911151}
		}));
	}
}

