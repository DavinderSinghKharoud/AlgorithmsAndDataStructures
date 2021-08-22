package InterviewBit;

import java.util.*;

/**
 * Given an 2D integer array FindGreatestCommonDivisor of size N x 2 denoting time intervals of different meetings.
 *
 * Where:
 *
 * FindGreatestCommonDivisor[i][0] = start time of the ith meeting.
 * FindGreatestCommonDivisor[i][1] = end time of the ith meeting.
 * Find the minimum number of conference rooms required so that all meetings can be done.
 *
 *
 *
 * Problem Constraints
 * 1 <= N <= 10
 *
 * 0 <= FindGreatestCommonDivisor[i][0] < FindGreatestCommonDivisor[i][1] <= 2 * 109
 *
 *
 *
 * Input Format
 * The only argument given is the matrix FindGreatestCommonDivisor.
 *
 *
 *
 * Output Format
 * Return the minimum number of conference rooms required so that all meetings can be done.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  FindGreatestCommonDivisor = [      [0, 30]
 *             [5, 10]
 *             [15, 20]
 *      ]
 *
 * Input 2:
 *
 *  FindGreatestCommonDivisor =  [     [1, 18]
 *             [18, 23]
 *             [15, 29]
 *             [4, 15]
 *             [2, 11]
 *             [5, 13]
 *       ]
 *
 *
 * Example Output
 * Output 1:
 *
 *  2
 * Output 2:
 *
 *  4
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  Meeting one can be done in conference room 1 form 0 - 30.
 *  Meeting two can be done in conference room 2 form 5 - 10.
 *  Meeting one can be done in conference room 2 form 15 - 20 as it is free in this interval.
 * Explanation 2:
 *
 *  Meeting one can be done in conference room 1 from 1 - 18.
 *  Meeting five can be done in conference room 2 from 2 - 11.
 *  Meeting four can be done in conference room 3 from 4 - 15.
 *  Meeting six can be done in conference room 4 from 5 - 13.
 *  Meeting three can be done in conference room 2 from 15 - 29 as it is free in this interval.
 *  Meeting two can be done in conference room 4 from 18 - 23 as it is free in this interval.
 */
public class MeetingRooms {

	//O(n Log n) time complexity and O(n) space complexity
    public static int solve(int[][] arr) {

		Arrays.sort(arr, (Comparator.comparingInt(o -> o[0])));

		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
		priorityQueue.add( arr[0][1]);

		for (int index = 1; index < arr.length; index++) {

			if( arr[index][0] >= priorityQueue.peek() ){
				priorityQueue.poll();
			}
			priorityQueue.add(arr[index][1]);
		}
		return priorityQueue.size();
    }

    public static void main(String[] args) {
		System.out.println( solve(new int[][]{ {0, 30}, {5, 10}, {15, 20} }));
    }
}
