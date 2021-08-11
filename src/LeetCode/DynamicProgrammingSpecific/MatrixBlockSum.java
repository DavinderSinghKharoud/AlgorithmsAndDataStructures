/*
Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 

Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]
 */


import java.util.Arrays;

public class MatrixBlockSum {

    public static int[][] matrixBlockSum(int[][] mat, int k) {

        int[][] partialSum = new int[mat.length][mat[0].length];
        int[][] result = new int[mat.length][mat[0].length];
        int col = mat[0].length;
        int row = mat.length;
        partialSum[0][0] = mat[0][0];
        //filling first row
        for (int i = 1; i < col; i++) {
            partialSum[0][i] = partialSum[0][i - 1] + mat[0][i];
        }
        //filling first column
        for (int i = 1; i < row; i++) {
            partialSum[i][0] = partialSum[i - 1][0] + mat[i][0];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                partialSum[i][j] = partialSum[ i - 1 ][j] + partialSum[i][j - 1] - partialSum[i - 1][j - 1] + mat[i][j];
            }
        }

        for( int m = 0; m < row; m++) {
            for(int n = 0; n<col ; n++) {
                int startRow = Math.max(0,m-k);
                int startCol = Math.max(0, n-k);

                int endRow = Math.min(row-1,m+k);
                int endCol = Math.min(col-1, n+k);
                if(startRow == 0 && startCol == 0) {
                    result[m][n] = partialSum[endRow][endCol];
                } else if(startRow == 0) {
                    result[m][n] = partialSum[endRow][endCol] -
                            partialSum[endRow][startCol-1];

                }
                else if(startCol == 0 ) {
                    result[m][n] = partialSum[endRow][endCol] -
                            partialSum[startRow-1][endCol] ;
                } else {
                    result[m][n] =  partialSum[endRow][endCol] - partialSum[endRow][startCol-1]
                            - partialSum[startRow-1][endCol] +
                            partialSum[startRow-1][startCol-1];
                }


            }
        }

        return result;
    }



    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(matrixBlockSum(new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        }, 1)));
    }

    public static int[][] matrixBlockSum2(int[][] matr, int k) {

        int n = matr.length;
        int m = matr[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int rmin = Math.max(i - k, 0);
                int rmax = i + k >= n ? n - 1 : i + k;
                int cmin = Math.max(j - k, 0);
                int cmax = j + k >= m ? m - 1 : j + k;
                int sum = 0;
                for (int p = rmin; p <= rmax; p++) {
                    for (int q = cmin; q <= cmax; q++) {
                        sum += matr[p][q];
                    }
                }
                res[i][j] = sum;
            }
        }

        return res;
    }
}

