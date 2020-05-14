
import java.util.*;

/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */
public class WordSearch {
    
	
	public static boolean helper( char[][] board, boolean[][] visited, int row, int col, String word, int index, int[][] dir ){
		    
		    if( row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col] ){
			return false;
		    }

		    
		    if( board[row][col] != word.charAt(index)){
			return false;
		    }

		   if( index == word.length() - 1){
			return true;
	        }
		    visited[row][col] = true;
		    
		    for(int[] move: dir ){
			boolean found = helper( board, visited, row + move[0], col + move[1], word, index + 1, dir );
				if( found ) return true;
		    }

			visited[row][col] = false;
		    
		    return false;
	}
	public static boolean exist(char[][] board, String word) {

		boolean[][] visited = new boolean[board.length][board[0].length];
		int[][] dir = new int[][]{
				{1, 0}, {0, 1}, {-1, 0},{0, -1}
		};


		for( int i = 0; i<board.length; ++i ){
			for( int j = 0; j<board[0].length; ++j ){

				if( board[i][j] == word.charAt(0) );
				boolean found = helper( board, visited, i, j, word, 0, dir );

				if( found ) return true;

			}
		}


		return false;
	}

	public static void main (String[] args) {
		
		char[][] board = new char[][]{
		    {'A','B','C','E'},
		    {'S','F','C','S'},
		    {'A','D','E','E'}
		};

		char[][] board1 = new char[][]{
				{'a'}
		};
		
		String word = "ABCCED";
		String word1 = "a";
		
		System.out.println( exist2( board, word ));
	}
	

	private static boolean dfs(int row, int col, char[][] board, String word, int index){
		/* when we are going out of the matrix then return false */
		if(row<0 || row>=board.length || col<0 || col>=board[0].length || word.charAt(index)!=board[row][col])
			return false;

		/* since we found the word so return true*/
		if(word.length()-1==index){
			return true;
		}
		char temp = board[row][col];
		board[row][col] = '*';

		boolean found =
				dfs(row-1,col,board,word,index+1)|| /*top*/
						dfs(row,col+1,board,word,index+1) || /*right*/
						dfs(row+1,col,board,word,index+1) || /*bottom*/
						dfs(row,col-1,board,word,index+1); /* left */

    /* will cause TLE as every condition will be checked here

       boolean top = dfs(row-1,col,board,word,index+1);
       boolean right = dfs(row,col+1,board,word,index+1);
       boolean bottom = dfs(row+1,col,board,word,index+1);
       boolean left = dfs(row,col-1,board,word,index+1);
       if(top|| right || bottom || left)
           return true;
     */

		board[row][col] = temp;
		if(found){
			return true;
		}

		return false;
	}

	public static boolean exist2(char[][] board, String word) {
		for(int row=0;row<board.length;row++){
			for(int col=0; col<board[0].length;col++){
				if(word.charAt(0) == board[row][col]){
					if(dfs(row,col,board,word,0)){
						return true;
					}
				}
			}
		}
		return false;
	}
}

