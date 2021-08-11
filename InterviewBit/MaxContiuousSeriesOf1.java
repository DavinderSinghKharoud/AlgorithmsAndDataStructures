package InterviewBit;

import java.util.*;

/**
 * You are given with an array of 1s and 0s. And you are given with an integer M, which signifies number of flips allowed.
 * Find the position of zeros which when flipped will produce maximum continuous series of 1s.
 *
 * For this problem, return the indices of maximum continuous series of 1s in order.
 *
 * Example:
 *
 * Input :
 * Array = {1 1 0 1 1 0 0 1 1 1 }
 * M = 1
 *
 * Output :
 * [0, 1, 2, 3, 4]
 *
 * If there are multiple possible solutions, return the sequence which has the minimum start index.
 */
public class MaxContiuousSeriesOf1 {

    public static ArrayList<Integer> maxone(List<Integer> lst, int n) {
		
		int len = lst.size();

		ArrayList<Integer> res = new ArrayList<>();
		if( len == 0 ) return res;

		
		int start = 0;
		int zeroCount = 0;
		int max = -1;
		int resStart = 0;
		int resEnd = 0;

		for (int end = 0; end < len; end++) {

			if( lst.get(end) == 0 ){
				zeroCount++;
			}

			while ( zeroCount > n ){
				if( lst.get(start) == 0 ){
					zeroCount--;
				}
				start++;
			}

			if( max < end - start + 1 ){
				resStart = start;
				resEnd = end;
				max = end - start + 1;
			}

		}
		for (int index = resStart; index <= resEnd; index++) {

			res.add(index);
		}
		return res;
    }
    public static void main(String[] args) {

		System.out.println( maxone(Arrays.asList(1, 1, 0, 1, 1, 0, 0, 1, 1, 1), 1));
		//System.out.println( maxone(Arrays.asList(0, 1, 1, 1 ), 0));
    }
}
