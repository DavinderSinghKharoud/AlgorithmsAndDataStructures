package algorthims.LeetCode;

import java.util.*;


public class SpiralMatrix {
	
	public static List<Integer> spiralOrder(int[][] matrix) {


		List<Integer> lst = new ArrayList<>();

		if( matrix.length == 0 ) return lst;
		int startRow = 0; 
		int endRow = matrix.length;
		
		int startCol = 0;
		int endCol = matrix[0].length;
		
		while( startRow <= endRow - 1 && startCol <= endCol - 1){
			
			for(int i = startCol; i < endCol; i++ ){
				lst.add( matrix[startRow][i]);
			}
			
			for( int i = startRow + 1; i< endRow; i++ ){
				lst.add( matrix[i][endCol - 1]);
			}
			
				for( int i = endCol - 2; i >= startCol; i-- ){
					if( endRow - startRow >1 ){
						lst.add( matrix[endRow - 1][i]);
					}
			}
			
			for( int i = endRow - 2; i >= startRow + 1; i-- ){
				if( endCol - startCol > 1){
					lst.add( matrix[i][startCol]);
				}
			}
			
			startRow++;
			endRow--;
			startCol++;
			endCol--;
			
		}
        
	    return lst;
	}	
	
	
	public static void main (String[] args) {
			System.out.println( spiralOrder( new int[][]{
			    { 2,5,8},
			    {4,0,-1}
			}));
	}
}

