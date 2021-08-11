package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiagonalTraverse {

    public static int[] findDiagonalOrder(int[][] matrix) {

        int rowLen = matrix.length;
        if (rowLen == 0) return new int[0];
        int colLen = matrix[0].length;
        int[] res = new int[rowLen * colLen];

        int index = 0;
        boolean isUp = true;
        for (int row = 0; row < rowLen; row++) {
            index = traverse(matrix, res, row, 0, isUp, index);
            isUp = !isUp;
        }

        for (int col = 1; col < colLen; col++) {
            index = traverse(matrix, res, rowLen - 1, col, isUp, index);
            isUp = !isUp;
        }
        return res;
    }

    private static int traverse(int[][] matrix, int[] res, int row, int col, boolean isUp, int index) {
        List<Integer> lst = new ArrayList<>();

        while ( row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length ){
            int curr = matrix[row][col];
            if( isUp ){
                res[index++] = curr;
            }else{
                lst.add(curr);
            }
            row--;col++;
        }

        if( !isUp ){
            for (int index1 = lst.size() - 1; index1 >= 0; index1--) {
                res[index++] = lst.get(index1);
            }
        }

        return index;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
        //System.out.println(Arrays.toString(findDiagonalOrder(new int[][]{{3}, {2}})));

    }

}
