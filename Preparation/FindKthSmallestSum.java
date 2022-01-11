package Preparation;

import LeetCode.Template.D;

import java.util.*;

/**
 * You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.
 *
 * You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.
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
 * Example 4:
 *
 * Input: mat = [[1,1,10],[2,2,9]], k = 7
 * Output: 12
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat.length[i]
 * 1 <= m, n <= 40
 * 1 <= k <= min(200, n ^ m)
 * 1 <= mat[i][j] <= 5000
 * mat[i] is a non decreasing array.
 * Accepted
 */
public class FindKthSmallestSum {
    public static void main(String[] args) {
        System.out.println(new FindKthSmallestSum().kthSmallest(
                new int[][]{
                        {1, 3, 11}, {2, 4, 6},{2, 4, 6}
                }, 9
        ));
    }
    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length, m = mat[0].length;
        List<Integer> lst = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int col = 0; col < m; col++){
            pq.add(mat[0][col]);
        }

        for(int row = 1; row < n; row++){
            PriorityQueue<Integer> npq = new PriorityQueue<>(Collections.reverseOrder());
            for(int sum: pq){
                for(int col = 0; col < m; col++){
                    npq.add(sum + mat[row][col]);
                    if(npq.size() > k) npq.remove();
                }
            }
            pq = npq;
        }

        return pq.poll();
    }
}
