package algorithms.InterviewBit;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 * N Queens: Example 1
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens’ placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 *
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */

import java.util.*;
public class NQueens {

    public static ArrayList<ArrayList<String>> solveNQueens(int num) {
        
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if( num == 0 ) return res;
        char[][] dp = new char[num][num];
        
       
        for( int index1 = 0; index1 < num; index1++ ){
			char[] curr = new char[num];
			for( int index2 = 0; index2 < num; index2++ ){
				curr[index2] = '.';
			}
			
			dp[index1] = curr;
		}
		
		helper( 0,  num, dp, res);
		return res;
    }
    
    public static void helper( int row, int len, char[][] dp, ArrayList<ArrayList<String>> res ){

		if( row == len ){
			addArray(dp, res );
			return;
		}
		
		
		for( int col = 0; col < len; col++ ){
			
			if( canPlace(dp, row, col, len) ){
				dp[row][col] = 'Q';
				helper( row + 1, len, dp, res);
				dp[row][col] = '.';
	 		}
		}
		
		
	}
	
	public static boolean canPlace( char[][] dp , int row, int col, int len){
		int tempRow = row;
		int tempCol = col;
		//bottom
		while ( row > 0 && col >= 0 && col < len ){
			if( dp[row - 1][col] == 'Q' ){
				return false;
			}
			row--;
		}
		
		row = tempRow;
		col = tempCol;	
		//check diagonals
		while ( row - 1 >= 0 && col - 1 >= 0 && col < len ){
			if( dp[row - 1][col - 1] == 'Q' ){
				return false;
			}
			row--;
			col--;
		}
		row = tempRow;
		col = tempCol;
		while ( row - 1 >= 0 && col >= 0 && col + 1 < len ){
			if( dp[row - 1][col + 1] == 'Q' ){
				return false;
			}
			row--;
			col++;
		}
		
		return true;
	}

	public static void addArray( char[][] dp, ArrayList<ArrayList<String>> res	){
		
		ArrayList<String> lst = new ArrayList<>();

		for(char[] curr: dp ){
			StringBuilder sbr = new StringBuilder();
			for( char c: curr ){
				sbr.append(c);
			}
			lst.add(sbr.toString());

		}
		
		res.add(lst);
	}
    public static void main(String[] args) {

		System.out.println( solveNQueens(4) );
    }
}
