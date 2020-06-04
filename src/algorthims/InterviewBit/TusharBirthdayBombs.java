/*
 It’s Tushar’s birthday today and he has N friends.

Friends are numbered [0, 1, 2, …., N-1] and i-th friend have a positive strength B[i].

Today being his birthday, his friends have planned to give him birthday bombs (kicks :P).

Tushar’s friends know Tushar’s pain bearing limit and would hit accordingly.

If Tushar’s resistance is denoted by A (>=0) then find the lexicographically smallest order of friends to kick Tushar so that the cumulative kick strength (sum of the strengths of friends who kicks) doesn’t exceed his resistance capacity and total no. of kicks hit are maximum.

Also note that each friend can kick unlimited number of times (If a friend hits x times, his strength will be counted x times)

Return the lexicographically smallest array of maximum length where the ith index represents the index of the friend who will hit.

Note:

1. [a1, a2, ...., am] is lexicographically smaller than [b1, b2, .., bm]  if a1 < b1 or (a1 = b1 and a2 < b2) ... .
2. Input cases are such that the length of the answer does not exceed 100000.
Input Format:

The first argument contains an integer, A of length N.
The second argument contains an array of integers, B.
Output Format:

Return an array of integer, as described in the problem statement.
Constraints:

1 <= N <= 100
1 <= A <= 15000000
1 <= B[i] <= 3000
Examples:

Input 1:
    A = 12
    B = [3, 4]

Output 1:
    [0, 0, 0, 0]

Explanation 1:
    [1, 1, 1] is also a possible answer.

Input 2:
    A = 11
    B = [6, 8, 5, 4, 7]

Output 2:
    [0, 2]

Explanation 2:
    [2, 3], [2, 2] and [3, 3] are also possible answers.
 */

import java.util.*;
public class TusharBirthdayBombs {

	static List<Integer> lst = new ArrayList<>();

	//Greedy Solution
	public static int[] solve(int r, int[] arr) {
		

		
		helper(r, arr, new ArrayList<>() );
		
		int[] res = new int[ lst.size() ];
		int i = 0;
		for( int index: lst ){
			res[i++] = index;
		}
		
		return res;
		
		}

	public static void helper(int r, int[] arr, ArrayList<Integer> temp) {

		if( r == 0 ){
			if( lst.size() <= temp.size() ){
				lst = compareLists(lst, temp);
			}
		}
		for (int index = 0; index < arr.length; index++) {

			if( r - arr[index] >= 0 ){
				temp.add(index);
				helper(r - arr[index], arr, temp );
				temp.remove(temp.size() - 1);
			}
		}
	}

	private static List<Integer> compareLists(List<Integer> lst, ArrayList<Integer> temp) {
		if( temp.size() > lst.size() ){
			return new ArrayList<>(temp);
		}

		for (int index = 0; index < lst.size(); index++) {
			if( lst.get(index) < temp.get(index) ){
				return lst;
			}else{
				return new ArrayList<>(temp);
			}
		}
		return lst;
	}

	public static void main (String[] args) {
		int[] res = solve2(11, new int[]{
				8, 8, 6, 5, 4
		});
		
		for( int index: res ){
			System.out.println( index );
		}
	}

	/**
	 * R = 11, S = [6, 8, 5, 4, 7]
	 * In this case:
	 * Max no. of kicks = 11/4 = 2.
	 * answer = [3,3] (if we do not consider the lexicographic order)
	 * But answer may be [0,3] or [0,2] or [2,3] as they also have the same length.
	 * Here, only required friends to consider are newS = [6,5,4] as we will rather choose friend with strength 6 than choosing a friend with strength 8, and rather choose any of [6,5,4] than choosing friend with strength 7. (Give it a thought, it’s true because our answer should be lexicographically smallest)
	 * @param r
	 * @param arr
	 * @return
	 */
	public static int[] solve2(int r, int[] arr) {
		int minValue = arr[0];
		int minIndex = 0;

		for( int index = 0; index < arr.length; index++ ){
			if( minValue > arr[index] ){
				minValue = arr[index];
				minIndex = index;
			}
		}

		int len = r / minValue;

		int[] res = new int[len];

		for( int count = 0; count < len; count++ ){
			res[count] = minIndex;
		}

		for(int index = 0; index < minIndex; index++ ){
			compareArr( res, index, r, arr );
		}
		return res;

	}

	private static void compareArr(int[] res, int index, int strength, int[] arr ) {
		int sum = 0;
		for(int i = 0; i < res.length; i++ ){
			sum += arr[res[i]];
		}
		int i = 0;

		//comparing each value to check if we can replace the index
		for (int j = index; j < arr.length && i < res.length; j++) {

			while( i < res.length && sum - arr[res[i]] + arr[j] <= strength && res[i] > j ){
				sum += arr[j] - arr[res[i]];
				res[i++] = j;

			}
		}

	}
	}

