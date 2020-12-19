package Algorithms.LeetCode;

import java.util.*;

public class KthSmallestElementSortedMatrix {
    public static int kthSmallest1(int[][] matrix, int k) {


        List<Integer> lst = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                lst.add(matrix[i][j]);
            }
        }

        Collections.sort(lst);
        return lst.get(k - 1);
    }

    public static int kthSmallest2(int[][] matrix, int k) {


        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxHeap.add( matrix[i][j]);
                if( maxHeap.size() > k){
                    maxHeap.remove();
                }
            }
        }

        return maxHeap.remove();
    }

    public static int kthSmallest3(int[][] nums, int k) {
        int N = nums.length;
        int low = nums[0][0], high = nums[N - 1][N - 1];
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0, j = N - 1;
            for (int[] num : nums) {
                while (j >= 0 && num[j] > mid) {
                    j--;
                }
                count += (j + 1);
            }
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    public static void main(String[] args) {
        System.out.println(kthSmallest3(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8));
    }
}
