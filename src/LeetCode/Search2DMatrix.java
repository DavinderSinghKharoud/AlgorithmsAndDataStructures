//package algorthims.LeetCode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]**/
class Search2DMatrix {
	
	//O(mn)
    private static boolean searchMatrix(int[][] matrix, int target) {
        
        for( int i = 0; i<matrix.length; i++ ){
			for( int j= 0; j< matrix[0].length; j++ ){
				if( matrix[i][j] == target){
					return true;
				}
			}
		}
		
		return false;
    }
    
    //O( m + n )
    private static boolean searchMatrix2(int[][] matrix, int target) {
             
		if( matrix.length == 0){
            return false;
        }
		
		return search( matrix, target, 0, matrix[0].length - 1);
    }
    
    private static boolean search( int[][] matrix, int target, int row, int col){
		if( row < 0 || row >= matrix.length || col >= matrix[0].length || col < 0){
			return false;
		}
		
		if( matrix[row][col] == target){
			return true;
		}
		
		if( matrix[row][col] > target ){
			return search( matrix, target, row, col - 1 );
		}
			
		return search( matrix, target, row + 1, col );
	}
	
    public static void main(String[] args) {


		Queue<Integer> que = new ArrayDeque<>();


        System.out.println( searchMatrix2( new int[][]{
			{1,   4,  7, 11, 15},
			{2,   5,  8, 12, 19},
			{10, 13, 14, 17, 24},
			{18, 21, 23, 26, 30}
		}, 12));
	}
}
