/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * FindGreatestCommonDivisor region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */

public class SurroundedRegions {
	
	public static void solve(char[][] board) {

		if( board.length == 0 || board[0].length == 0 ){
			return;
		}

		int len_col = board[0].length;
		
		boolean [][]check = new boolean[board.length][len_col];
		
		for( int i = 0; i<board.length; i++ ){
		    
		    if( i == 0 || i == board.length - 1 ){
			
			for(int j = 0; j<board[0].length; j++ ){
			    
				if( board[i][j] == 'O' && !check[i][j] ){

					submerged( board, i, j, check );
				}
			}
				continue;
			}
		    
			if( board[i][0] == 'O' && !check[i][0] ){

				submerged( board, i, 0, check );
			}
		    
			if(  board[i][ len_col - 1] == 'O' && !check[i][len_col - 1] ){

				submerged( board, i, len_col - 1, check );
			}
		}
		
		for(int i = 0; i<board.length; i++ ){
			for( int j = 0; j<board[0].length; j++ ){
			
				if(!check[i][j]){
					board[i][j] = 'X';
				}else{
					board[i][j] = 'O';
				}
			}
		}
	}
	
	static int[][] direc = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
	public static void submerged( char[][] board, int row, int col, boolean [][]check ){
		
		
		
		for( int i = 0; i<direc.length; i++ ){
			check[row][col] = true;
			if( row + direc[i][0] < 0 || row + direc[i][0] >= board.length ||
					col + direc[i][1] < 0 || col + direc[i][1] >= board[0].length ){

				continue;
			}
			if( !check[row + direc[i][0] ][ col + direc[i][1] ] && board[ row + direc[i][0] ][ col + direc[i][1] ] == 'O' ){


				submerged( board, row + direc[i][0], col + direc[i][1], check );
			}
		}
		
		
	}
	
	public static void main (String[] args) {
		
		char[][] board = new char[][]{
				{'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O'}

		};
		solve( board );
		
		for(int i = 0; i<board.length; i++ ){
			System.out.println();
			for( int j = 0; j<board[0].length; j++ ){
			
				System.out.print( board[i][j] );
			}
		}
	}
}

