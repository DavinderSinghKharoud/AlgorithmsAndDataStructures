import java.util.*;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]**/

public class SetMatrixZeroes{
	
	public static void setZeroes(int[][] matrix) {
        
        int[][] copy = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
		
		
			for( int row = 0; row<matrix.length; row++ ){
				for( int col = 0; col<matrix[0].length; col++){
						
					if( copy[row][col] == 0 ){
						
						putZeroes( matrix, row, col);
	
						
				}
			}
		}
		
    }
    
    public static void putZeroes( int[][] matrix, int row, int col ){
		
		for(int i = 0; i<matrix[0].length; i++ ){
			matrix[row][i] = 0;
		}
		
		for( int j = 0; j<matrix.length; j++ ){
			matrix[j][col] = 0;
		}
		
	}
	
	
    public static void main(String[] args) {
			
			int[][] matrix = new int[][]{
				{1,1,1},
				{1,0,1},
				{1,1,1}
			};
			
			setZeroes( matrix );
			
			for( int i = 0; i<matrix.length; i++ ){
				for( int j = 0; j<matrix[0].length; j++ ){
					System.out.print( matrix[i][j]);
				}
				
				System.out.println();
			}
			}

}
