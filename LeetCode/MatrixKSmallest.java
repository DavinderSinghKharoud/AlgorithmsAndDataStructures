package LeetCode;

import java.util.*;

/**
 * You are given an m x n matrix mat that has its rows sorted in non-decreasing order and an integer k.
 *
 * You are allowed to choose exactly one element from each row to form an array.
 *
 * Return the kth smallest array sum among all possible arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 5
 * Output: 7
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
 * Example 2:
 *
 * Input: mat = [[1,3,11],[2,4,6]], k = 9
 * Output: 17
 * Example 3:
 *
 * Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * Output: 9
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.  
 */
public class MatrixKSmallest {

    public static void main(String[] args) {
        System.out.println(new MatrixKSmallest().kthSmallest(
                new int[][]{{1,10,10}, {1,4,5}, {2,3,6}}, 7
        ));
    }

    public int kthSmallest(int[][] mat, int k) {
        List<Integer> prev = new ArrayList<>();
        for (int i = mat.length - 1; i >= 0; i--) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int j = 0; j < mat[0].length; j++) {
                int curr = mat[i][j];
                if (prev.isEmpty()) {
                    pq.add(mat[i][j]);
                } else {
                    for (int num : prev) {
                        pq.add(num + curr);
                        if (pq.size() > k) pq.poll();
                    }
                }
            }
            prev.clear();
            while (!pq.isEmpty()) {
                prev.add(pq.poll());
            }
        }
        return prev.get(0);
    }

}
