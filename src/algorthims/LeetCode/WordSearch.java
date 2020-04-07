package algorthims.LeetCode;

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
    
	public static boolean exist(char[][] board, String word) {
        
		boolean[][] visited = new boolean[board.length][board[0].length];
		int[][] dir = new int[][]{
		    {1, 0}, {0, 1}, {-1, 0},{0, -1}
		};
		
		Map<Character,List<int[]>> map = new HashMap<>();
		
		for( int i = 0; i<board.length; ++i ){
		    for( int j = 0; j<board[0].length; ++j ){
			List<int[]> lst = map.getOrDefault( board[i][j], new ArrayList<>() ); 
			int[] index = new int[]{
			    i, j
			};
			
			lst.add( index );
			map.put( board[i][j], lst );
			
		    }
		}
		
		
		    if( map.get(word.charAt(0)) != null ){
				List<int[]> lst = map.get( word.charAt(0) );

				for( int[] arr: lst ){
					boolean found = helper( board, visited, arr[0], arr[1], word, 0, dir );

					if( found ) return true;
				}
		}

		    
		    
		return false;
	}
	
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
		
		System.out.println( exist( board, word ));
	}
}

