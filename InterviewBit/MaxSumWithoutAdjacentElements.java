/*
Given a 2 x N grid of integer, FindGreatestCommonDivisor, choose numbers such that the sum of the numbers
is maximum and no two chosen numbers are adjacent horizontally, vertically or diagonally, and return it.

Note: You can choose more than 2 numbers.

Input Format:

The first and the only argument of input contains a 2d matrix, FindGreatestCommonDivisor.
Output Format:

Return an integer, representing the maximum possible sum.
Constraints:

1 <= N <= 20000
1 <= FindGreatestCommonDivisor[i] <= 2000
Example:

Input 1:
    FindGreatestCommonDivisor = [   [1]
            [2]    ]

Output 1:
    2

Explanation 1:
    We will choose 2.

Input 2:
    FindGreatestCommonDivisor = [   [1, 2, 3, 4]
            [2, 3, 4, 5]    ]
    
Output 2:
    We will choose 3 and 5. 
 */

import java.util.*;

public class MaxSumWithoutAdjacentElements {

    //Time limit exceeded but correct
    public static int adjacent(ArrayList<ArrayList<Integer>> arr) {

        int rows = arr.size();
        if (rows == 0) return 0;

        return helper(arr, 0, 0);
    }

    public static int helper(ArrayList<ArrayList<Integer>> arr, int total, int col) {
        if (col >= arr.get(0).size()) {
            return total;
        }

        int include1 = helper(arr, total + arr.get(0).get(col), col + 2);
        int include2 = helper(arr, total + arr.get(0).get(col), col + 2);
        int max = Math.max(include1, include2);

        int exclude1 = helper(arr, total, col + 1);
        max = Math.max(max, exclude1);

        int include3 = helper(arr, total + arr.get(1).get(col), col + 2);
        int include4 = helper(arr, total + arr.get(1).get(col), col + 2);
        max = Math.max(max, Math.max(include3, include4));

        return max;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        ArrayList<Integer> temp1 = new ArrayList<>();
        temp1.add(74);temp1.add(37);temp1.add(82);temp1.add(1);
        ArrayList<Integer> temp2 = new ArrayList<>();
        temp2.add(66);temp2.add(38);temp2.add(16);temp2.add(1);

        arr.add(temp1);
        arr.add(temp2);
        System.out.println(adjacent2(arr));
    }

    public static int adjacent2(ArrayList<ArrayList<Integer>> arr) {
        int rows = arr.size();
        if (rows == 0) {
            return 0;
        }
        int cols = arr.get(0).size();

        int inc = Math.max(arr.get(0).get(0), arr.get(1).get(0) );
        int exc = 0, exc_new;

        for(int index = 1; index < cols; index++ ){

            // Update max_sum on including or
            // excluding of previous column
            exc_new = Math.max( inc, exc );

            // Include current column. Add maximum element
            // from both row of current column
            inc = exc + Math.max( arr.get(0).get(index), arr.get(1).get(index) );

            // If current column doesn't to be included
            exc = exc_new;
        }

        return Math.max( inc, exc );
    }
}

