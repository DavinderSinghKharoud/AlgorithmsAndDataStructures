package Algorithms.InterviewBit;

import java.util.*;

/**
 * Given any source point, (C, D) and destination point, (E, F) on a chess board, we need to find whether Knight can move to the destination or not.
 *
 * Knight's movements on a chess board
 *
 * The above figure details the movements for a knight ( 8 possibilities ).
 *
 * If yes, then what would be the minimum number of steps for the knight to move to the said point.
 * If knight can not move from the source point to the destination point, then return -1.
 *
 * Note: A knight cannot go out of the board.
 *
 *
 *
 * Input Format:
 *
 * The first argument of input contains an integer A.
 * The second argument of input contains an integer B.
 *     => The chessboard is of size A x B.
 * The third argument of input contains an integer C.
 * The fourth argument of input contains an integer D.
 *     => The Knight is initially at position (C, D).
 * The fifth argument of input contains an integer E.
 * The sixth argument of input contains an integer F.
 *     => The Knight wants to reach position (E, F).
 * Output Format:
 *
 * If it is possible to reach the destination point, return the minimum number of moves.
 * Else return -1.
 * Constraints:
 *
 * 1 <= A, B <= 500
 * Example
 *
 * Input 1:
 *     A = 8
 *     B = 8
 *     C = 1
 *     D = 1
 *     E = 8
 *     F = 8
 *
 * Output 1:
 *     6
 *
 * Explanation 1:
 *     The size of the chessboard is 8x8, the knight is initially at (1, 1) and the knight wants to reach position (8, 8).
 *     The minimum number of moves required for this is 6.
 */
public class KnightOnChessBoard {


    static int res = Integer.MAX_VALUE;

    public static void traverse(int row_count, int col_count, int x1, int y1, int x2, int y2, int steps, Map<String, Integer> map) {
        if (x1 == x2 && y1 == y2) {
            res = Math.min( res, steps);
            return;
        }

        if (x1 < 1 || y1 < 1 || x1 > row_count || y1 > col_count ) {
            return;
        }

        if( map.containsKey(x1 + " " + y1) && map.get(x1 + " " + y1) < steps ){
            return;
        }
        map.put(x1 + " " + y1, Math.min(map.getOrDefault(x1 + " " + y1, steps), steps) );

        //Move left
        traverse(row_count, col_count, x1 + 1, y1 + 2, x2, y2, steps + 1, map);
        traverse(row_count, col_count, x1 - 1, y1 + 2, x2, y2, steps + 1, map);

        //Move right
        traverse(row_count, col_count, x1 + 1, y1 - 2, x2, y2, steps + 1, map);
        traverse(row_count, col_count, x1 - 1, y1 - 2, x2, y2, steps + 1, map);

        //Move up
        traverse(row_count, col_count, x1 - 2, y1 + 1, x2, y2, steps + 1, map);
        traverse(row_count, col_count, x1 - 2, y1 - 1, x2, y2, steps + 1, map);

        //Move down
        traverse(row_count, col_count, x1 + 2, y1 + 1, x2, y2, steps + 1, map);
        traverse(row_count, col_count, x1 + 2, y1 - 1, x2, y2, steps + 1, map);

    }

    public static int knight(int A, int B, int C, int D, int E, int F) {
        traverse(A, B, C, D, E, F, 0, new HashMap<>());
        return ( res == Integer.MAX_VALUE )? -1: res;
    }

    public static void main(String[] args) {
        System.out.println(knight2(4, 7, 2, 6, 2, 4));
        //System.out.println(knight(8, 8, 1, 1, 8, 8));
    }

    //O(n * m ) time and space complexity
    public static int knight2(int row_count, int col_count, int C, int D, int E, int F) {
		
		Queue<Pair> queue = new LinkedList<>();
		queue.add( new Pair(C, D, 0) );
		int[][] direc = new int[][]{ {1, 2}, {-1, 2}, {-1, -2}, {1, -2}, {-2, 1}, {-2, -1}, {2, 1}, {2, -1} };
		Set<String> set = new HashSet<>();
        set.add(C + " " + D);
		while( !queue.isEmpty() ){
			Pair curr = queue.poll();
			
			if( curr.x == E && curr.y == F ){
				return curr.steps;
			}
			
			for( int[] dir: direc){
				int x = curr.x + dir[0];
				int y = curr.y + dir[1];
				if( x < 1 || y < 1 || x > row_count || y > col_count || set.contains(x + " " + y)){
					continue;
				}
				queue.add( new Pair(x, y, curr.steps + 1) );
				set.add(x + " " + y);
			}
		}
		
		return -1;
    }
    
    static class Pair{
		int x;
		int y;
		int steps;

		public Pair(int x, int y, int steps){
		    this.x = x;
		    this.y = y;
		    this.steps = steps;
        }
	}
}
