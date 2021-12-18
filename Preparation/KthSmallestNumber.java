package Preparation;

import java.util.Arrays;
import java.util.Objects;

/**
 * 378. Kth Smallest Element in a Sorted Matrix
 * Medium
 *
 * 4963
 *
 * 215
 *
 * Add to List
 *
 * Share
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with complexity better than O(n2).
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * Example 2:
 *
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 *
 * Constraints:
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n2
 */
import java.util.*;

public class KthSmallestNumber {

    public static void main(String[] args) {
//        System.out.println(new KthSmallestNumber().kthSmallest(
//                new int[][]{
//                        {-5, -4},
//                        {-5, -4}
//                }, 2
//        ));

        System.out.println(new KthSmallestNumber().kthSmallest2(
                new int[][]{
                        {1, 5, 9}, {10, 11, 13}, {12, 13, 15}
                }, 3
        ));


    }

    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for (int j = 0; j <= n - 1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for (int i = 0; i < k - 1; i++) {
            Tuple t = pq.poll();
            if (t.x == n - 1) continue;
            pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
        }
        return pq.poll().val;
    }
}

class Tuple implements Comparable<Tuple> {
    int x, y, val;

    public Tuple(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Tuple that) {
        return this.val - that.val;
    }

    int n, m;
    int[][] matrix;

    public int kthSmallest(int[][] matrix, int k) {
        n = matrix.length;
        m = matrix[0].length;
        this.matrix = matrix;
        //Every number here is matrix[i][j] = i * m + j;
        int start = matrix[0][0], end = matrix[n - 1][m - 1];
        int ans = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            int lesserCount = getLesserCount(mid);
            System.out.println(mid + " " + lesserCount);
            if (lesserCount >= k) {
                ans = mid;
                end = mid - 1;
            } else start = mid + 1;
        }

        return ans;
    }

    int getLesserCount(int mid) {
        int col = m - 1;
        int count = 0;
        for (int row = 0; row < n && col >= 0; row++) {
            while (col >= 0 && matrix[row][col] > mid) col--;

            count += (col + 1);
        }
        return count;
    }

}
