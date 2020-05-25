package algorthims.LeetCode;

public class RotateImage {

    public static void rotate(int[][] matrix) {

        //Do the transpose
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //Then just flip the matrix horizontally( two sides approach and meet in the middle )
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
//                {1, 2},
//                {3, 4}
        };

        rotate( mat );

        for( int i = 0; i<mat.length; i++ ){
            for( int j = 0; j<mat[0].length; j++){
                System.out.print(mat[i][j]+", ");
            }
            System.out.println();
        }

    }
}
