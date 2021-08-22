package InterviewBit;

import java.util.*;

/**
 * You are given an array (zero indexed) of N non-negative integers, A0, A1 ,…, AN-1.
 * Find the minimum sub array Al, Al+1 ,…, Ar so if we sort(in ascending order) that sub array, then the whole array should get sorted.
 * If FindGreatestCommonDivisor is already sorted, output -1.
 *
 * Example :
 *
 * Input 1:
 *
 * FindGreatestCommonDivisor = [1, 3, 2, 4, 5]
 *
 * Return: [1, 2]
 *
 * Input 2:
 *
 * FindGreatestCommonDivisor = [1, 2, 3, 4, 5]
 *
 * Return: [-1]
 * In the above example(Input 1), if we sort the subarray A1, A2, then whole array FindGreatestCommonDivisor should get sorted.
 */
public class MaximumUnsortedArray {

    public static ArrayList<Integer> subUnsort(List<Integer> lst) {
		
		int len = lst.size();
		
		ArrayList<Integer> res = new ArrayList<>();
		
		int start = len - 1;
		int end = 0;
		
		for( int index = 0; index < len - 1; index++ ){
			
			if( lst.get(index) > lst.get(index + 1) ){
				start = Math.min( start, index );
				end = Math.max( end, index + 1);
			}
		}
		
		if( start ==
		 len - 1 || end == 0){
			res.add(-1);
			return res;
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for( int index = start; index <= end; index++ ){
			max = Math.max( lst.get(index), max );
			min = Math.min( lst.get(index), min );
		}
		
		int resStart = start;
		int resEnd = end;
		
		
		start--;
		while( start >= 0 ){
			int num = lst.get(start);
			if( num > min){
				resStart = start;
			}
			start--;
		}
		
		end--;
		while( end < len ){
			int num = lst.get(end);
			if( num < max  ){
				resEnd = end;
			}
			end++;
		}
		
		res.add(resStart);
		res.add(resEnd);
		
		return res;
    }

    public static void main(String[] args) {

		System.out.println( subUnsort(Arrays.asList(1, 1, 2, 3, 3, 4, 8, 9, 11, 9, 11, 12, 12, 11, 9, 14, 19, 20, 20)));
    }
}
