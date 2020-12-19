package Algorithms.InterviewBit;

/**
 * iven an array of integers A. There is a sliding window of size B which
 * is moving from the very left of the array to the very right.
 * You can only see the w numbers in the window. Each time the sliding window moves
 * rightwards by one position. You have to find the maximum for each window.
 * The following example will give you more clarity.
 *
 * The array A is [1 3 -1 -3 5 3 6 7], and B is 3.
 *
 * Window position	Max
 * ———————————-	————————-
 * [1 3 -1] -3 5 3 6 7	3
 * 1 [3 -1 -3] 5 3 6 7	3
 * 1 3 [-1 -3 5] 3 6 7	5
 * 1 3 -1 [-3 5 3] 6 7	5
 * 1 3 -1 -3 [5 3 6] 7	6
 * 1 3 -1 -3 5 [3 6 7]	7
 * Return an array C, where C[i] is the maximum value of from A[i] to A[i+B-1].
 *
 * Note: If B > length of the array, return 1 element with the max of the array.
 */

import java.util.*;
public class SlidingWindowMaximum {
    public static void main(String[] args) {

		System.out.println( slidingMaximum(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), 2));
    }

    public static ArrayList<Integer> slidingMaximum(final List<Integer> lst, int range) {
			int len = lst.size();


			ArrayList<Integer> res = new ArrayList<>();
			if( len == 0 ) return res;

			if( range > len ) {
				int max = lst.get(0);
				for( int num: lst ){
					max = Math.max( max, num );
				}
				res.add(max);
				return res;
			}


			Deque<Integer> deque = new ArrayDeque<>();

			//first time creating a queue
			for(int index = 0; index < range; index++ ){

				while( !deque.isEmpty() && lst.get(deque.peekLast()) < lst.get(index) ){
					deque.removeLast();
				}
				deque.addLast(index);

			}

			res.add(lst.get(deque.peekFirst()));
			int start = 1;
			//check for remaining numbers
			for( int index = range; index < len; index++ ){


				removeExtra( deque, start);
				while( !deque.isEmpty() && lst.get(deque.getLast()) < lst.get(index) ){
					deque.removeLast();
				}
				deque.addLast(index);

				res.add(lst.get(deque.peekFirst()));
				start++;
			}
			return res;

    }

    private static void removeExtra(Deque<Integer> deque, int start){
    	while ( !deque.isEmpty() && deque.peekFirst() < start ){
    		deque.removeFirst();
		}
	}
}
