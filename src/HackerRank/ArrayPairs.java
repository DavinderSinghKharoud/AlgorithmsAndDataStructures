package HackerRank;

import java.util.*;

/**
 * Consider an array of  integers, . Find and print the total number of  pairs such that  where .
 * Input Format
 * The first line contains an integer, , denoting the number of elements in the array.
 * The second line consists of  space-separated integers describing the respective values of .
 * Constraints
 *
 *
 * Scoring
 *  for  of the test cases.
 *  for  of the test cases.
 *  for  of the test cases.
 * Output Format
 * Print a long integer denoting the total number  pairs satisfying  where .
 * Sample Input
 * 5
 * 1 1 2 4 2
 * Sample Output
 * 8
 * Explanation
 * There are eight pairs of indices satisfying the given criteria: , , , , , , , and . Thus, we print  as our answer.
 */
public class ArrayPairs {

    //Time complexity O( n^2 Log(n) ) and space complexity of O(n)
    static long solve(int[] arr) {
        int res = 0;
        int len = arr.length;
        PriorityQueue<Integer> pq;

        for (int index1 = 0; index1 < len; index1++) {
            int curr = arr[index1];
            pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int index2 = index1 + 1; index2 < len; index2++) {
                int num = arr[index2];
                pq.add(num);

                if (curr * num <= pq.peek()) res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {


        System.out.println(solve2(new int[]{1, 1, 2, 4, 2}));
    }

    //Time complexity O( n^2 ) and space complexity O(1)
    static long solve2(int[] arr) {
        int res = 0;
        int max;
        int len = arr.length;
        for (int index1 = 0; index1 < len; index1++) {
            int curr = arr[index1];
            max = arr[index1];
            for (int index2 = index1 + 1; index2 < len; index2++) {
                int num = arr[index2];
                if (num > max) max = num;

                if (curr * num <= max) res++;
            }
        }
        return res;
    }


}
