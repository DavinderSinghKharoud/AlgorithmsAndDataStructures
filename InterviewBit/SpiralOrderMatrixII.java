package InterviewBit;

import java.util.*;

/**
 * Given an integer A, generate a square matrix filled with elements from 1 to A2 in spiral order.
 *
 *
 *
 * Input Format:
 *
 * The first and the only argument contains an integer, A.
 * Output Format:
 *
 * Return a 2-d matrix of size A x A satisfying the spiral order.
 * Constraints:
 *
 * 1 <= A <= 1000
 * Examples:
 *
 * Input 1:
 *     A = 3
 *
 * Output 1:
 *     [   [ 1, 2, 3 ],
 *         [ 8, 9, 4 ],
 *         [ 7, 6, 5 ]   ]
 *
 * Input 2:
 *     4
 *
 * Output 2:
 *     [   [1, 2, 3, 4],
 *         [12, 13, 14, 5],
 *         [11, 16, 15, 6],
 *         [10, 9, 8, 7]   ]
 */
public class SpiralOrderMatrixII {

    public static ArrayList<ArrayList<Integer>> generateMatrix(int n) {
		
		ArrayList<ArrayList<Integer>> res = new ArrayList();
		
		for( int row = 0; row < n; row++ ){
			
			ArrayList<Integer> lst = new ArrayList<>();
			for( int col = 0; col < n; col++ ){
				lst.add(0);
			}
			res.add(lst);
		}
		int startRow = 0;
		int endRow = n - 1;
		int startCol = 0;
		int endCol = n - 1;
		
		int num = 1;
		
		do{
			
			for( int col = startCol; col <= endCol; col++ ){
				res.get(startRow).set(col, num++);
			}
			
			for( int row = startRow + 1; row <= endRow; row++ ){
				res.get(row).set(endCol, num++);
			}
			
			for( int col = endCol - 1; col >= startCol; col-- ){
				res.get(endRow).set(col, num++);
			}
			
			for( int row = endRow - 1; row >= startRow + 1;  row-- ){
				res.get(row).set(startCol, num++);
			}
			
			startRow++;
			endRow--;
			startCol++;
			endCol--;
		} while( startRow <= endRow && startCol <= endCol );
    
		return res;
    }
    public static void main(String[] args) {

    	ArrayList<ArrayList<Integer>> res = generateMatrix(4);

    	for( ArrayList<Integer> lst: res ){
			System.out.println(lst);
		}
    }
}
