package algorithms.LeetCode;

import java.util.Arrays;
import java.util.List;

/**
 * A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.
 *
 * You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:
 *
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.
 */
public class BinaryMatrixInteractive {

    public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int row_len = binaryMatrix.dimensions().get(0);
        int col_len = binaryMatrix.dimensions().get(1);

        for( int i = 0; i < col_len; i++ ){
            int index = binarySearch( binaryMatrix, i, row_len );
            if( index != -1 ){
                return i;
            }
        }

        return -1;
    }

    private static int binarySearch(BinaryMatrix binaryMatrix, int col, int end) {

        int start = 0;
        while ( start < end ){
            int mid = (start + end )/2;
            if( binaryMatrix.get(col, mid) == 1 ){
                return mid;
            }else if( binaryMatrix.get(col, mid) < 1 ){
                    start = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BinaryMatrix binaryMatrix = new BinaryMatrix(2,4);

        binaryMatrix.put(0,0, 1);
        binaryMatrix.put( 0, 1, 1);
        binaryMatrix.put( 0, 2, 1);
        binaryMatrix.put( 0, 3, 1);

        binaryMatrix.put(1,0, 0);
        binaryMatrix.put( 1, 1, 0);
        binaryMatrix.put( 1, 2, 1);
        binaryMatrix.put( 1, 3, 1);

        System.out.println( leftMostColumnWithOne( binaryMatrix ));
    }

    // This is the BinaryMatrix's API interface.
    // You should not implement it, or speculate about its implementation
     static class BinaryMatrix {
        int[][] matrix;
        int row_len;
        int col_len;

        public BinaryMatrix( int row, int col ){
            matrix = new int[row][col];
            row_len = row;
            col_len = col;
        }
        public void put( int row, int col, int value ){
            matrix[row][col] = value;
        }
        public int get(int row, int col){
            return matrix[row][col];
        };

        public List<Integer> dimensions() {
            return Arrays.asList(row_len, col_len);
        }


    }
}
