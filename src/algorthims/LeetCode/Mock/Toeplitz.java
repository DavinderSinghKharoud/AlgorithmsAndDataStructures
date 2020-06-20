package algorthims.LeetCode.Mock;

public class Toeplitz {


    public static boolean isToeplitzMatrix(int[][] matrix) {
            int n = matrix.length;
            if( n == 0 ){
                return false;
            }
            int m = matrix[0].length;

            for(int row = 0; row < n; row++ ){
                int curr = matrix[row][0];
                int rowCount = row;
                int colCount = 0;
                while( rowCount < n && colCount < m ){
                    int value = matrix[rowCount++][colCount++];
                    if( value != curr){
                        return false;
                    }
                }
            }

            for( int col = 1; col < m; col++ ){
                int curr = matrix[0][col];
                int rowCount = 0;
                int colCount = col;

                while ( rowCount < n && colCount < m ){
                    int value = matrix[rowCount++][colCount++];
                    if( value != curr ){
                        return false;
                    }
                }
            }

            return true;
    }

    public static void main(String[] args) {

        System.out.println( isToeplitzMatrix(new int[][]{
                {1,2,3,4},
                {5,1,2,3},
                {9,5,1,2}
        }));

    }
}
