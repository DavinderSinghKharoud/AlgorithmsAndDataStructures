package Algorithms.InterviewBit;

import java.util.*;

/**
 * Given N x M character matrix A of O's and X's, where O = white, X = black.
 *
 * Return the number of black shapes. A black shape consists of one or more adjacent X's (diagonals not included)
 *
 *
 *
 * Input Format:
 *
 *     The First and only argument is a N x M character matrix.
 * Output Format:
 *
 *     Return a single integer denoting number of black shapes.
 * Constraints:
 *
 *     1 <= N,M <= 1000
 *     A[i][j] = 'X' or 'O'
 * Example:
 *
 * Input 1:
 *     A = [ OOOXOOO
 *           OOXXOXO
 *           OXOOOXO  ]
 * Output 1:
 *     3
 * Explanation:
 *     3 shapes are  :
 *     (i)    X
 *          X X
 *     (ii)
 *           X
 *     (iii)
 *           X
 *           X
 * Note: we are looking for connected shapes here.
 *
 * XXX
 * XXX
 * XXX
 * is just one single connected black shape.
 */
public class BlackShapes {

	// O( m * n ) time and space complexity
    public static int black(ArrayList<String> lst) {
		
		
		Set<String> set = new HashSet<>();
		int count = 0;
		for( int row = 0; row < lst.size(); row++ ){
			String str = lst.get(row);
			
			for( int col = 0; col < str.length(); col++ ){
				
				if( str.charAt(col) == 'X' && !set.contains(row + " " + col) ){
					count++;
					submerge( lst, row, col, set );
				}
			}
		}
		
		return count;
    }
    
    public static void submerge( ArrayList<String> lst, int row, int col, Set<String> set ){
		if( row < 0 || row >= lst.size() || col < 0 || col >= lst.get(row).length() ) {
			return;
		}
		
		if( lst.get(row).charAt(col) == 'O' || set.contains( row + " " + col )){
			return;
		}
		
		set.add( row + " " + col );
		
		submerge( lst, row + 1, col, set );
		submerge( lst, row - 1, col, set );
		submerge( lst, row, col + 1, set );
		submerge( lst, row, col - 1, set );
		
		
	}
    public static void main(String[] args) {
		ArrayList<String> lst = new ArrayList<>();
		lst.add("OOOXOOO");
		lst.add("OOXXOXO");
		lst.add("OXOOOXO");

		System.out.println( black( lst ));
    }
}
