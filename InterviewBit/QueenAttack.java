package InterviewBit;

import java.util.*;
public class QueenAttack {

	/**
	 * On a N * M chessboard, where rows are numbered from 1 to N and columns from 1 to M, there are queens at some cells. Return a N * M array FindGreatestCommonDivisor, where FindGreatestCommonDivisor[i][j] is number of queens that can attack cell (i, j). While calculating answer for cell (i, j), assume there is no queen at that cell.
	 *
	 * Notes:
	 *
	 * Queen is able to move any number of squares vertically, horizontally or diagonally on a chessboard. FindGreatestCommonDivisor queen cannot jump over another queen to attack a position.
	 * You are given an array of N strings, each of size M. Each character is either a 1 or 0 denoting if there is a queen at that position or not, respectively.
	 * Expected time complexity is worst case O(N*M).
	 * For example,
	 *
	 * Let chessboard be,
	 * [0 1 0]
	 * [1 0 0]
	 * [0 0 1]
	 *
	 * where a 1 denotes a queen at that position.
	 *
	 * Cell (1, 1) is attacked by queens at (2, 1), (1,2) and (3,3).
	 * Cell (1, 2) is attacked by queen at (2, 1). Note that while calculating this, we assume that there is no queen at (1, 2).
	 * Cell (1, 3) is attacked by queens at (3, 3) and (1, 2).
	 * and so on...
	 *
	 * Finally, we return matrix
	 * [3, 1, 2]
	 * [1, 3, 3]
	 * [2, 3, 0]
	 * @param arr
	 * @return
	 */
	//O(n * m (n + m ) ) time complexity and O(n * m ) space complexity
	public static ArrayList<ArrayList<Integer>> queenAttack(ArrayList<String> arr) {
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		
		int n = arr.size();
		if( n == 0 ){
			return result;
		}
		int m = arr.get(0).length();
		
		int[][] dp = new int[n][m];
		
		for( int row = 0; row < n; row++ ){
			for(int col = 0; col < m; col++ ){
				if( arr.get(row).charAt(col) == '1' ){
					traverse2( n, m, dp, row, col, arr );
				}
			}
		}
		
		for(int row = 0; row < n; row++ ){
			ArrayList<Integer> temp = new ArrayList<>();
			
			for(int col = 0; col < m; col++ ){
				temp.add( dp[row][col] );
			}
			
			result.add(temp);
		}
		
		return result;
    }

	public static void traverse2(int n, int m, int[][] dp, int row, int col, ArrayList<String> arr){
		int[][] direc = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1} };

		for(int[] dir: direc ){
			int tempRow = row + dir[0];
			int tempCol = col + dir[1];
			while ( tempRow >= 0 && tempCol >= 0 && tempRow < n && tempCol < m ){
				dp[tempRow][tempCol] += 1;
				if( arr.get(tempRow).charAt(tempCol) == '1' ){
					break;
				}
				tempRow += dir[0];
				tempCol += dir[1];
			}
		}

	}
		public static void traverse(int n, int m, int[][] dp, int row, int col, ArrayList<String> arr){
		int tempRow = row;
		int tempCol = col;
		//left
		col--;
		while ( row >= 0 && col >= 0 && row < n && col < m ){
			if( arr.get(row).charAt(col) == '1' ){
				dp[row][col] += 1;
				break;
			}
			dp[row][col--] += 1;
		}
		row = tempRow;
		col = tempCol;
		col++;
		//right
		while ( row >= 0 && col >= 0 && row < n && col < m ){
			if( arr.get(row).charAt(col) == '1' ){
				dp[row][col] += 1;
				break;
			}
			dp[row][col++] += 1;
		}
		row = tempRow;
		col = tempCol;
		row--;
		//vertical top
		while ( row >= 0 && col >= 0 && row < n && col < m ){
			if( arr.get(row).charAt(col) == '1' ){
				dp[row][col] += 1;
				break;
			}
			dp[row--][col] += 1;
		}
		row = tempRow;
		col = tempCol;
		row++;
		//vertical bottom
		while ( row >= 0 && col >= 0 && row < n && col < m ){
			if( arr.get(row).charAt(col) == '1' ){
				dp[row][col] += 1;
				break;
			}
			dp[row++][col] += 1;
		}
		row = tempRow;
		col = tempCol;
		row++;col--;
		//diagonal bottom left
		while ( row >= 0 && col >= 0 && row < n && col < m ){
			if( arr.get(row).charAt(col) == '1' ){
				dp[row][col] += 1;
				break;
			}
			dp[row++][col--] += 1;
		}
		row = tempRow;
		col = tempCol;
		row++;col++;
		//diagonal bottom right
		while ( row >= 0 && col >= 0 && row < n && col < m ){
			if( arr.get(row).charAt(col) == '1' ){
				dp[row][col] += 1;
				break;
			}
			dp[row++][col++] += 1;
		}
		row = tempRow;
		col = tempCol;
		row--;col--;
		//diagonal top left
		while ( row >= 0 && col >= 0 && row < n && col < m ){
			if( arr.get(row).charAt(col) == '1' ){
				dp[row][col] += 1;
				break;
			}
			dp[row--][col--] += 1;
		}
		row = tempRow;
		col = tempCol;
		row--;col++;
		//diagonal top right
		while ( row >= 0 && col >= 0 && row < n && col < m ){
			if( arr.get(row).charAt(col) == '1' ){
				dp[row][col] += 1;
				break;
			}
			dp[row--][col++] += 1;
		}

		
	}


	public static void main(String[] args) {

		ArrayList<String> arr = new ArrayList<>();
		arr.add("111");
		arr.add("111");
		arr.add("111");
        ArrayList<ArrayList<Integer>> res = queenAttack( arr );
        
        for( int row = 0; row < res.size(); row++ ){
			for(int col = 0; col < res.get(0).size(); col++ ){
				System.out.print( res.get(row).get(col) );
			}
			System.out.println();
		}
    }
}
