package algorthims.LeetCode;

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

    public static void main(String[] args) {
        System.out.println(kthSmallest2(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8));
    }
}
