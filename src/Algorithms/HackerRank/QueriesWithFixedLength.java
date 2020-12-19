package Algorithms.HackerRank;

import java.util.*;

/**
 * Consider an -integer sequence, . We perform a query on  by using an integer, , to calculate the result of the following expression:
 * <p>
 * In other words, if we let , then you need to calculate .
 * Given  and  queries (each query consists of an integer, ), print the result of each query on a new line.
 * Input Format
 * The first line consists of two space-separated integers describing the respective values of  and .
 * The second line consists of  space-separated integers describing the respective values of .
 * Each of the  subsequent lines contains a single integer denoting the value of  for that query.
 * Constraints
 * <p>
 * <p>
 * <p>
 * <p>
 * Output Format
 * For each query, print an integer denoting the query's answer on a new line. After completing all the queries, you should have printed  lines.
 * Sample Input 0
 * 5 5
 * 33 11 44 11 55
 * 1
 * 2
 * 3
 * 4
 * 5
 * Sample Output 0
 * 11
 * 33
 * 44
 * 44
 * 55
 * Explanation 0
 * For , the answer is
 * <p>
 * .
 * For , the answer is
 * <p>
 * .
 * For , the answer is
 * <p>
 * .
 * For , the answer is
 * <p>
 * .
 * For , the answer is
 * <p>
 * .
 * Sample Input 1
 * 5 5
 * 1 2 3 4 5
 * 1
 * 2
 * 3
 * 4
 * 5
 * Sample Output 1
 * 1
 * 2
 * 3
 * 4
 * 5
 * Explanation 1
 * For each query, the "prefix" has the least maximum value among the consecutive subsequences of the same size.
 */
public class QueriesWithFixedLength {

    //Time complexity( queries * len ) and Space complexity O(queryLen)
    static int[] solve(int[] arr, int[] queries) {
        int[] res = new int[queries.length];
        for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
            int query = queries[queryIndex];

            res[queryIndex] = minSlidingWindow(arr, query);
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solve(new int[]{33, 11, 44, 11, 55}, new int[]{1, 2, 3, 4, 5})));
    }

    public static int minSlidingWindow(int[] nums, int k) {

        int len = nums.length;
        int min = Integer.MAX_VALUE;

        Deque<Integer> dequeue = new LinkedList<>();

        for (int index = 0; index < len; index++) {
            while (!dequeue.isEmpty() && dequeue.getLast() < nums[index]) {
                dequeue.removeLast();
            }

            dequeue.add(nums[index]);
            if (index >= k - 1) {
                min = Math.min(min, dequeue.peek());
                if (dequeue.peekFirst() == nums[index - (k - 1)]) dequeue.pop();
            }

        }

        return min;
    }
}
