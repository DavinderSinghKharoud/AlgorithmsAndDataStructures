//package algorthims.LeetCode;

import java.util.*;

public class NumberOfInslands{

      public static int numIslands(char[][] grid) {
		  int count = 0;

		  for( int i = 0; i < grid.length; i++ ){
			for(  int j = 0; j<grid[0].length; j++ ){
			      if( grid[ i ][ j ] == '1' ){
				    submerged( grid, i, j );
				    count++;
			      }
			}
		  }

		  return count;
      }

      private static int[][] direc = new int[][]{ { 1 , 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

      private static void submerged( char[][] grid, int row, int col ){
		  if(  row<0 || row>=grid.length || col<0 || col>= grid[0].length || grid[row][col] == '0' ){
			return;
		  }

		  grid[ row ][ col ] = '0';
		  for( int i = 0 ;  i < direc.length; i++ ) {
			      submerged( grid, row + direc[i][0], col + direc[i][1] );
		  }
      }
      public static void main(String[] args){
	    char [][] grid = new char[][]{
			 {'1','1','0','0','0'},
			 {'1','1','0','0','0'},
			 {'0','0','1','0','0'},
			 {'0','0','0','1','1'}
	    };
		   System.out.println( numIslands( grid ) );
      }
      }
