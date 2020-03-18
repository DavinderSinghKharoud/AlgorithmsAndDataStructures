package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KthSmallestElementSortedMatrix {
    public static int kthSmallest(int[][] matrix, int k) {


        List<Integer> lst = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                lst.add(matrix[i][j]);
            }
        }

        Collections.sort(lst);
        return lst.get( k - 1);
    }

    public static void main(String[] args) {
        System.out.println( kthSmallest(new int[][]{
                { 1,  5,  9},
                {10, 11, 13},
                {12, 13, 15}
        },8));
    }
}
