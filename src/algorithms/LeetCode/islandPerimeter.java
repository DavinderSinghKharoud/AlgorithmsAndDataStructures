/*
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.



Example:

Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16
 */


public class islandPerimeter {
	
	public static int islandPerimeter(int[][] grid) {
		
		int rowCount = grid.length;
		int colCount = grid[0].length;
		int total = 0;
		if( rowCount == 0 ) return total;
		
		for( int row = 0; row < rowCount; row++ ){
			for( int col = 0; col < colCount; col++ ){
				
				if( grid[row][col] == 1 ){
					int left = ( col - 1 >= 0 && grid[row][col - 1] == 1 ) ? 0: 1;
					int right = ( col + 1 < colCount && grid[row][col + 1] == 1) ? 0: 1;
					
					int top = ( row - 1 >= 0 && grid[row - 1][col] == 1 ) ? 0: 1;
					int bottom = ( row + 1 < rowCount && grid[row + 1][col] == 1 ) ? 0: 1;
					
					total += left + right + top + bottom;
				}
				
			}
		}
		
		return total;
        
    }
	public static void main (String[] args) {
		
	}
}

